package mx.com.accounts.infrastructure.api.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountReportResponseDTO {
	
    private String accountNumber;
    
    private String accountType;
    
    private BigDecimal initialBalance;
    
    private BigDecimal currentBalance;
    
    private List<MovementReportResponseDTO> movements;

}
