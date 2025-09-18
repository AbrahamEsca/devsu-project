package mx.com.accounts.domain.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {
	
	private Long accountId;
	
    private String clientId;

    private String accountNumber;
    
	private Long accountTypeId;
	
	private String descAccountType;
	
	private BigDecimal initialBalance;
	
	private BigDecimal currentBalance;

    private Boolean active;
    
    public Account(String clientId, String accountNumber, Long accountTypeId, BigDecimal initialBalance, BigDecimal currentBalance, Boolean active) {
    	this.clientId = clientId;
    	this.accountNumber = accountNumber;
    	this.accountTypeId = accountTypeId;
    	this.initialBalance = initialBalance;
    	this.currentBalance = currentBalance;
    	this.active = active;
    }

}
