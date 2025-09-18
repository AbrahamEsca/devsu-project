package mx.com.accounts.application.port.in;

public interface JwtUseCase {

	String generateToken(String clientId);
	
	String extractClientId(String token);

    boolean validateToken(String token);
    
}
