package mx.com.accounts.infrastructure.adapter;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.AllArgsConstructor;
import mx.com.accounts.application.port.out.AccountPort;
import mx.com.accounts.domain.entity.AccountEntity;
import mx.com.accounts.domain.model.Account;
import mx.com.accounts.domain.repository.AccountRepository;
import mx.com.accounts.domain.repository.AccountTypeRepository;
import mx.com.accounts.infrastructure.adapter.mapper.AccountMapper;

@Component
@AllArgsConstructor
public class AccountAdapter implements AccountPort {
	
	private static final Logger LOGGER = LogManager.getLogger(AccountAdapter.class);
	
	private final AccountRepository accountRepository;
	
	private final AccountTypeRepository accountTypeRepository;
	
	private final WebClient webClient;
	
	/**
	 * Description: Method to save account
	 * 
	 * @param account body with the new account data
	 * 
	 * @return Account the method response
	 * 
	 **/
	@Override
	public Account saveAccount(Account account) {
		LOGGER.info("Start of the saveAccount method in the service class");

		var saved = accountRepository.save(AccountMapper.toEntity(account));
		var accountType = accountTypeRepository.findByAccountTypeId(saved.getAccountTypeId());
		saved.setAccountTypeEntity(accountType);
		
        return AccountMapper.toDomain(saved);
	}
	
	/**
	 * Description: Method to load accounts by ID
	 * 
	 * @param clientId the ID of the accounts to retrieve
	 * 
	 * @return List<Account> the method response
	 * 
	 **/
	@Override
	public List<Account> loadByClientId(String clientId) {
		LOGGER.info("Start of the loadByClientId method in the service class");

		return accountRepository.findByClientId(clientId)
				.stream()
				.map(AccountMapper::toDomain)
				.collect(Collectors.toList());
	}
	
	/**
	 * Description: Method to load account by ID
	 * 
	 * @param accountId the ID of the account to retrieve
	 * 
	 * @return Account the method response
	 * 
	 **/
	@Override
	public Account loadByAccountId(Long accountId) {
		LOGGER.info("Start of the loadByAccountId method in the service class");

		AccountEntity accountEntity = accountRepository.findByAccountId(accountId);
		Account       account       = null;
		if(accountEntity != null) {
			account = AccountMapper.toDomain(accountEntity);
		}
	    
	    return account;
	}
	
	/**
	 * Description: Method to exists account by account number
	 * 
	 * @param accountNumber the account number of the account to delete
	 * 
	 * @return boolean the method response
	 * 
	 **/
	@Override
    public boolean existsAccount(String accountNumber) {
		LOGGER.info("Start of the existsAccount method in the service class");
		
		return accountRepository.existsByAccountNumber(accountNumber);
    }
	
	/**
	 * Description: Method to get client name
	 * 
	 * @param clientId the Id of client
	 * 
	 * @param token the token service
	 * 
	 * @return String the method response
	 * 
	 **/
	@Override
    public String getClientNameById(String clientId, String token) {
        return webClient.get()
                .uri("/clients/{id}", clientId)
                .header("Authorization", token)
                .retrieve()
                .bodyToMono(ClientNameResponse.class)
                .block()
                .getName();
    }

	private static class ClientNameResponse {
		private String name;
		public String getName() { 
			return name; 
		}
	}

}
