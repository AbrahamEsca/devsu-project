package mx.com.accounts.infrastructure.api;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import mx.com.accounts.application.port.in.AccountUseCase;
import mx.com.accounts.common.constants.ApiConstants;
import mx.com.accounts.infrastructure.api.dto.AccountRequestDTO;
import mx.com.accounts.infrastructure.api.dto.AccountResponseDTO;

@Validated
@AllArgsConstructor
@RestController
@RequestMapping(ApiConstants.BASE_PATH)
@Tag(name = "Accounts", description = "Accounts Administration API")
public class AccountController {
	
	private static final Logger LOGGER = LogManager.getLogger(AccountController.class);
	
	private final AccountUseCase accountUseCase;
	
	/**
	 * Description: Service to create a account by ID
	 * 
	 * @param request the HTTP servlet request
	 * 
	 * @param accountRequestDTO request body list with the new account data
	 * 
	 * @return ResponseEntity the service response
	 * 
	 **/
	@PostMapping(value = ApiConstants.ACCOUNTS_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Create a new account")
	public ResponseEntity<AccountResponseDTO> createAccount(HttpServletRequest request, 
			@Valid @RequestBody AccountRequestDTO accountRequestDTO) {
		LOGGER.info("Start of the createAccount method in the controller class");

		Collections.list(request.getHeaderNames()).stream()
		.forEach(header -> LOGGER.info("Header: {} = {}", header, request.getHeader(header)));

		AccountResponseDTO accountResponseDTO = accountUseCase.createAccount(accountRequestDTO);

		return new ResponseEntity<>(accountResponseDTO, HttpStatus.CREATED);
	}
	
	/**
	 * Description: Service to get a list to accounts by ID
	 * 
	 * @param request the HTTP servlet request
	 * 
	 * @param clientId the ID of the accounts to retrieve
	 * 
	 * @return ResponseEntity the service response
	 * 
	 **/
	@GetMapping(value = ApiConstants.ACCOUNTS_ID_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Get an accounts by ID")
    public ResponseEntity<List<AccountResponseDTO>> getAccountsById(HttpServletRequest request, @PathVariable("id") String clientId) {
		LOGGER.info("Start of the getAccountsById method in the controller class");
		
		Collections.list(request.getHeaderNames()).stream()
        .forEach(header -> LOGGER.info("Header: {} = {}", header, request.getHeader(header)));
		
        return ResponseEntity.ok(accountUseCase.getAccountsById(clientId));
    }
	
	
	/**
	 * Description: Service to update an account by ID
	 * 
	 * @param request the HTTP servlet request
	 * 
	 * @param accountId the ID of the account to update
	 * 
	 * @param accountRequestDTO request body with the new account data
	 * 
	 * @return ResponseEntity the service response
	 * 
	 **/
	@PutMapping(value = ApiConstants.ACCOUNTS_ID_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Update an account by ID")
    public ResponseEntity<AccountResponseDTO> updateAccount(HttpServletRequest request, @PathVariable("id") Long accountId,
                                                              @Valid @RequestBody AccountRequestDTO accountRequestDTO) {
		LOGGER.info("Start of the updateAccount method in the controller class");
		
		Collections.list(request.getHeaderNames()).stream()
        .forEach(header -> LOGGER.info("Header: {} = {}", header, request.getHeader(header)));
		
        AccountResponseDTO accountResponseDTO = accountUseCase.updateAccount(accountId, accountRequestDTO);
        
        return ResponseEntity.ok(accountResponseDTO);
    }

}
