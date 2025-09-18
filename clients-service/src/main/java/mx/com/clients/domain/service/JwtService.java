package mx.com.clients.domain.service;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import mx.com.clients.application.port.in.JwtUseCase;

@Service
public class JwtService implements JwtUseCase {
	
	private static final Logger LOGGER = LogManager.getLogger(ClientService.class);

	private final String SECRET = "8a3f764a-4c2f-4c6c-a9c6-3e9a5d7e8c4d";
	
    private final long EXPIRATION_TIME = 86400000; // TTL

    /**
	 * Description: Method to generate token
	 * 
	 * @param clientId the clientId to generate token
	 * 
	 * @return String the method response
	 * 
	 **/
    public String generateToken(String clientId) {
    	LOGGER.info("Start of the generateToken method in the service class");
    	
        return Jwts.builder()
                .setSubject(clientId)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    /**
	 * Description: Method to generate token
	 * 
	 * @param token the token to extract clientId
	 * 
	 * @return String the method response
	 * 
	 **/
    public String extractClientId(String token) {
    	LOGGER.info("Start of the extractClientId method in the service class");
    	
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody().getSubject();
    }

    /**
	 * Description: Method to generate token
	 * 
	 * @param token the token to validate token
	 * 
	 * @return String the method response
	 * 
	 **/
    public boolean validateToken(String token) {
    	LOGGER.info("Start of the validateToken method in the service class");
    	
        try {
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
    
}
