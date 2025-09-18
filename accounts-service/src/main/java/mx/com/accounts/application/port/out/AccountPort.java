package mx.com.accounts.application.port.out;

import java.util.List;

import mx.com.accounts.domain.model.Account;

public interface AccountPort {

	Account saveAccount(Account account);
	
	List<Account> loadByClientId(String clientId);
	
	Account loadByAccountId(Long accountId);
	
	boolean existsAccount(String accountNumber);
	
	String getClientNameById(String clientId, String token);

}
