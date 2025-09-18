package mx.com.accounts.domain.exception;

public class MovementNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public MovementNotFoundException(String message) {
        super(message);
    }
    
}
