package mx.com.accounts.domain.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import mx.com.accounts.application.port.in.MovementUseCase;
import mx.com.accounts.application.port.out.AccountPort;
import mx.com.accounts.application.port.out.ClientCachePort;
import mx.com.accounts.application.port.out.MovementPort;
import mx.com.accounts.domain.exception.AccountNotFoundException;
import mx.com.accounts.domain.exception.InsufficientFundsException;
import mx.com.accounts.domain.exception.MovementNotFoundException;
import mx.com.accounts.domain.model.Account;
import mx.com.accounts.domain.model.Movement;
import mx.com.accounts.infrastructure.api.dto.AccountReportResponseDTO;
import mx.com.accounts.infrastructure.api.dto.MovementReportResponseDTO;
import mx.com.accounts.infrastructure.api.dto.MovementRequestDTO;
import mx.com.accounts.infrastructure.api.dto.MovementResponseDTO;
import mx.com.accounts.infrastructure.api.dto.ReportResponseDTO;

@Service
@AllArgsConstructor
public class MovementService implements MovementUseCase {

	private static final Logger LOGGER = LogManager.getLogger(MovementService.class);

	private final MovementPort movementPort;
	
	private final AccountPort accountPort;
	
	private final ClientCachePort clientCachePort;

	/**
	 * Description: Method to create a movement
	 * 
	 * @param movementRequestDTO request body with the new movement data
	 * 
	 * @return MovementResponseDTO the method response
	 * 
	 **/
	@Override
	@Transactional
	public MovementResponseDTO createMovement(MovementRequestDTO movementRequestDTO) {
		LOGGER.info("Start of the createMovement method in the service class");

		Account account = accountPort.loadByAccountId(movementRequestDTO.getAccountId());
		if(account == null) {
			throw new MovementNotFoundException("No se encontro cuenta");
		}

		var currentBalance = account.getCurrentBalance();
		var amount = movementRequestDTO.getAmount();
		if (amount.compareTo(BigDecimal.ZERO) < 0) {
		    var retiro = amount.abs();
		    if (currentBalance.subtract(retiro).compareTo(BigDecimal.ZERO) < 0) {
		        throw new InsufficientFundsException("Saldo no disponible");
		    }
		    currentBalance = currentBalance.subtract(retiro);
		} else {
			currentBalance = currentBalance.add(amount);
		}

		account.setCurrentBalance(currentBalance);
		
		account = accountPort.saveAccount(account);
        
		Movement movement = new Movement(movementRequestDTO.getAccountId(), movementRequestDTO.getMovementType(), movementRequestDTO.getAmount());
		movement.setMovementDate(LocalDateTime.now());
		movement.setBalance(account.getCurrentBalance());
		
		movement = movementPort.saveMovement(movement);

		return new MovementResponseDTO(movement.getMovementId(), movement.getAccountId(), movement.getMovementType(), movement.getMovementDate(), movement.getAmount(), movement.getBalance());
	}

	/**
	 * Description: Method to get movements by ID
	 * 
	 * @param accountId the ID of the movements to retrieve
	 * 
	 * @return List<MovementResponseDTO> the method response
	 * 
	 **/
	@Override
	public List<MovementResponseDTO> getMovementsByAccountId(Long accountId) {
		LOGGER.info("Start of the getMovementsByAccountId method in the service class");

		List<Movement> movementList = movementPort.loadByAccountId(accountId);
		if(movementList.isEmpty()) {
			throw new MovementNotFoundException("No se encontraron movimientos");
		}

		List<MovementResponseDTO> movementResponseDTOList = movementList.stream()
				.map(movement -> new MovementResponseDTO(movement.getMovementId(), movement.getAccountId(), movement.getMovementType(), movement.getMovementDate(), movement.getAmount(), movement.getBalance()))
				.toList();

		return movementResponseDTOList;
	}

	/**
	 * Description: Method to update a movement by ID
	 * 
	 * @param movementId the ID of the movement to update
	 * 
	 * @param movementRequestDTO request body with the movement data
	 * 
	 * @return MovementResponseDTO the method response
	 * 
	 **/
	@Override
	public MovementResponseDTO updateMovement(Long movementId, MovementRequestDTO movementRequestDTO) {
		LOGGER.info("Start of the updateMovement method in the service class");

		Movement movement = movementPort.loadByMovementId(movementId);
		if(movement == null) {
			throw new MovementNotFoundException("No se encontro movimiento");
		}

		Movement movementAux = new Movement(movement.getMovementId(), movement.getAccountId(), movementRequestDTO.getMovementType(), movement.getMovementDate(), movementRequestDTO.getAmount(), movement.getBalance());

		movement = movementPort.saveMovement(movementAux);

		return new MovementResponseDTO(movement.getMovementId(), movement.getAccountId(), movement.getMovementType(), movement.getMovementDate(), movement.getAmount(), movement.getBalance());
	}
	
	/**
	 * Description: Method to generate reports by ID and dates
	 * 
	 * @param clientId the ID of the movements to generate
	 * 
	 * @param startDate the date of the report to generate
	 * 
	 * @param endDate the date of the report to generate
	 * 
	 * @param token the token for get client name
	 * 
	 * @return ReportResponseDTO the method response
	 * 
	 **/
	@Override
	public ReportResponseDTO generateReport(String clientId, String startDate, String endDate, String token) {
		LOGGER.info("Start of the generateReport method in the service class");
		
		String nameClient = clientCachePort.get(clientId);
		if(nameClient == null) {
			nameClient = accountPort.getClientNameById(clientId, token);
		}
		
		List<Account> accountList = accountPort.loadByClientId(clientId);
		if(accountList.isEmpty()) {
			throw new AccountNotFoundException("No se encontraron cuentas");
		}
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		LocalDate start = LocalDate.parse(startDate, formatter);
		LocalDate end   = LocalDate.parse(endDate, formatter);
		LocalDateTime startDateTime = start.atTime(0, 0, 0);
		LocalDateTime endDateTime   = end.atTime(23, 59, 59);
		
		List<AccountReportResponseDTO> accountReportResponseDTOList = new ArrayList<>();
		accountList.forEach(account -> {
			List<Movement> movementList = movementPort.loadByAccountIdAndDate(account.getAccountId(), startDateTime, endDateTime);

			List<MovementReportResponseDTO> movementReportResponseDTOList = movementList.stream()
					.map(movement -> new MovementReportResponseDTO(movement.getMovementType(), movement.getMovementDate(), movement.getAmount(), movement.getBalance()))
					.toList();
			
			var accountReportResponse = new AccountReportResponseDTO(account.getAccountNumber(), account.getDescAccountType(), 
					account.getInitialBalance(), account.getCurrentBalance(), movementReportResponseDTOList);
			
			accountReportResponseDTOList.add(accountReportResponse);
		});
		
		return new ReportResponseDTO(nameClient, accountReportResponseDTOList);
	}

}
