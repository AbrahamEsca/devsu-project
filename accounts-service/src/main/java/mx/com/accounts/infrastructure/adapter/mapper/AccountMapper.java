package mx.com.accounts.infrastructure.adapter.mapper;

import mx.com.accounts.domain.entity.AccountEntity;
import mx.com.accounts.domain.model.Account;

public class AccountMapper {
	
    public static AccountEntity toEntity(Account c){
    	AccountEntity ce = new AccountEntity();
    	
    	ce.setAccountId(c.getAccountId());
        ce.setClientId(c.getClientId());
        ce.setAccountNumber(c.getAccountNumber());
        ce.setAccountTypeId(c.getAccountTypeId());
        ce.setInitialBalance(c.getInitialBalance());
        ce.setCurrentBalance(c.getCurrentBalance());
        ce.setActive(c.getActive());
        
        return ce;
    }
    
    public static Account toDomain(AccountEntity ce) {
        Account c = new Account(ce.getAccountId(), ce.getClientId(), ce.getAccountNumber(),
                ce.getAccountTypeId(), ce.getAccountTypeEntity().getDescAccount(), ce.getInitialBalance(), ce.getCurrentBalance(), ce.getActive());
        
        return c;
    }
    
}
