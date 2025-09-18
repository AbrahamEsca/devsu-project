package mx.com.accounts.infrastructure.api.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponseDTO {
	    
	private Long accountId;
	
	private String clientId;
	
    private String accountNumber;
    
    private String accountType;
    
    private BigDecimal initialBalance;
    
    private BigDecimal currentBalance;

    private boolean active;

}
