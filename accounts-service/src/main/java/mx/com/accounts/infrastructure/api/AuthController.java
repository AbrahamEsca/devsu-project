package mx.com.accounts.infrastructure.api;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import mx.com.accounts.application.port.in.JwtUseCase;
import mx.com.accounts.infrastructure.api.dto.AuthRequestDTO;
import mx.com.accounts.infrastructure.api.dto.AuthResponseDTO;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
@Tag(name = "Auth", description = "Authentication API")
public class AuthController {

    private final JwtUseCase jwtUseCase;

    /**
	 * Description: Service to login
	 * 
	 * @param requestDTO request body with the clientId
	 * 
	 * @return ResponseEntity the service response
	 * 
	 **/
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthRequestDTO requestDTO) {
    	if ("cred-api-client".equals(requestDTO.getClientId())) {
    		String token = jwtUseCase.generateToken(requestDTO.getClientId());
    		return ResponseEntity.ok(new AuthResponseDTO(token));
    	} else {
    		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("ClientId inválido");
    	}
    }
    
}
