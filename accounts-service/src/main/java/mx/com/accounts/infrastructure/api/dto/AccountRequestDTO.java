package mx.com.accounts.infrastructure.api.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequestDTO {
	
	@NotBlank(message = "El clientId es obligatorio")
    @Size(max = 20, message = "El clientId no puede tener más de 20 caracteres")
    private String clientId;
	
	@NotNull(message = "El tipo de cuenta es obligatoria")
    private Long accountTypeId;
	
	@NotNull(message = "El monto inicial es obligatorio")
    private BigDecimal initialBalance;
	
	@NotNull(message = "El monto actual es obligatorio")
    private BigDecimal currentBalance;
	
    private boolean active;

}
