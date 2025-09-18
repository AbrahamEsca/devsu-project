package mx.com.accounts.domain.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import mx.com.accounts.application.port.in.AccountUseCase;
import mx.com.accounts.application.port.out.AccountPort;
import mx.com.accounts.common.util.AccountUtils;
import mx.com.accounts.domain.exception.AccountNotFoundException;
import mx.com.accounts.domain.model.Account;
import mx.com.accounts.infrastructure.api.dto.AccountRequestDTO;
import mx.com.accounts.infrastructure.api.dto.AccountResponseDTO;

@Service
@AllArgsConstructor
public class AccountService implements AccountUseCase {

	private static final Logger LOGGER = LogManager.getLogger(AccountService.class);

	private final AccountPort accountPort;

	/**
	 * Description: Method to create a account
	 * 
	 * @param accountRequestDTO request body with the new account data
	 * 
	 * @return AccountResponseDTO the method response
	 * 
	 **/
	@Override
	public AccountResponseDTO createAccount(AccountRequestDTO accountRequestDTO) {
		LOGGER.info("Start of the createAccount method in the service class");

		String accountNumber = null;
		do {
		    accountNumber = AccountUtils.generateAccountNumber();
		} while (accountPort.existsAccount(accountNumber));

		if (!accountRequestDTO.getInitialBalance().equals(accountRequestDTO.getCurrentBalance())) {
		    throw new IllegalArgumentException("El saldo inicial y el saldo actual deben ser iguales al crear la cuenta");
		}
		
		Account account = new Account(accountRequestDTO.getClientId(), accountNumber, accountRequestDTO.getAccountTypeId(), accountRequestDTO.getInitialBalance(), accountRequestDTO.getCurrentBalance(), accountRequestDTO.isActive());

		account = accountPort.saveAccount(account);

		return new AccountResponseDTO(account.getAccountId(), account.getClientId(), account.getAccountNumber(), account.getDescAccountType(), account.getInitialBalance(), account.getCurrentBalance(), account.getActive());
	}

	/**
	 * Description: Method to get accounts by ID
	 * 
	 * @param clientId the ID of the accounts to retrieve
	 * 
	 * @return List<AccountResponseDTO> the method response
	 * 
	 **/
	@Override
	public List<AccountResponseDTO> getAccountsById(String clientId) {
		LOGGER.info("Start of the getAccountsById method in the service class");

		List<Account> accountList = accountPort.loadByClientId(clientId);
		if(accountList.isEmpty()) {
			throw new AccountNotFoundException("El cliente no tiene registrada ninguna cuenta");
		}

		List<AccountResponseDTO> accountResponseDTOList = accountList.stream()
				.map(account -> new AccountResponseDTO(account.getAccountId(), account.getClientId(), account.getAccountNumber(), 
						account.getDescAccountType(), account.getInitialBalance(), account.getCurrentBalance(), account.getActive()))
				.toList();

		return accountResponseDTOList;
	}

	/**
	 * Description: Method to update account by ID
	 * 
	 * @param accountId the ID of the account to update
	 * 
	 * @param accountRequestDTO request body with the account data
	 * 
	 * @return AccountResponseDTO the method response
	 * 
	 **/
	@Override
	public AccountResponseDTO updateAccount(Long accountId, AccountRequestDTO accountRequestDTO) {
		LOGGER.info("Start of the updateAccount method in the service class");

		Account account = accountPort.loadByAccountId(accountId);
		if(account == null) {
			throw new AccountNotFoundException("No se encontro la cuenta");
		}

		Account accountAux = new Account(account.getClientId(), account.getAccountNumber(), account.getAccountTypeId(), accountRequestDTO.getInitialBalance(), accountRequestDTO.getCurrentBalance(), accountRequestDTO.isActive());
		accountAux.setAccountId(account.getAccountId());

		account = accountPort.saveAccount(accountAux);

		return new AccountResponseDTO(account.getAccountId(), account.getClientId(), account.getAccountNumber(), account.getDescAccountType(), account.getInitialBalance(), account.getCurrentBalance(), account.getActive());

	}

}
