package mx.com.accounts.infrastructure.api.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovementRequestDTO {
	
	@NotNull(message = "El accountId es obligatorio")
    private Long accountId;
	
	@NotNull(message = "El tipo de movimiento es obligatorio")
    private String movementType;
	
	@NotNull(message = "El monto es obligatorio")
    private BigDecimal amount;

}
