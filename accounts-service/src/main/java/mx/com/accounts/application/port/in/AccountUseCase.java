package mx.com.accounts.application.port.in;

import java.util.List;

import mx.com.accounts.infrastructure.api.dto.AccountRequestDTO;
import mx.com.accounts.infrastructure.api.dto.AccountResponseDTO;

public interface AccountUseCase {

	AccountResponseDTO createAccount(AccountRequestDTO accountRequestDTO);
	
	List<AccountResponseDTO> getAccountsById(String clientId);

	AccountResponseDTO updateAccount(Long accountId, AccountRequestDTO accountRequestDTO);

}
