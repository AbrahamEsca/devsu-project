package mx.com.clients.infrastructure.api.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientRequestDTO {
	
	@NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede tener más de 200 caracteres")
    private String name;
	
	@NotBlank(message = "El sexo es obligatorio")
	@Size(max = 1, message = "El sexo no puede tener más de 1 caracter")
	@Pattern(regexp = "^[FM]$", message = "El sexo solo puede ser 'F' o 'M'")
    private String gender;
	
	@NotNull(message = "La edad es obligatoria")
    @Min(value = 1, message = "La edad mínima debe ser 1")
    @Max(value = 120, message = "La edad máxima permitida es 120")
    private Integer age;
	
	@NotBlank(message = "La identificacion es obligatoria")
    @Size(max = 50, message = "La identificacion no puede tener más de 50 caracteres")
    private String identification;
	
	@NotBlank(message = "La direccion es obligatoria")
    @Size(max = 200, message = "La direccion no puede tener más de 200 caracteres")
    private String address;
	
	@NotBlank(message = "El telefono es obligatorio")
    @Size(min = 10, max = 10, message = "El telefono deber ser de 10 caracteres")
    private String phone;
	
	@NotBlank(message = "La password es obligatoria")
    @Size(min = 1, max = 10, message = "La password no puede tener más de 10 caracteres")
    private String password;
	
    private boolean active;

}
