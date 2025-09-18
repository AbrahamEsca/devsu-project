package mx.com.clients.infrastructure.api.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequestDTO {
	
	@NotBlank(message = "El clientId es obligatorio")
    private String clientId;

}
