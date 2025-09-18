package mx.com.accounts.infrastructure.api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovementReportResponseDTO {
	
    private String movementType;
    
    private LocalDateTime movementDate;
    
    private BigDecimal amount;
    
    private BigDecimal balance;

}
