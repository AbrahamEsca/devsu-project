package mx.com.accounts.application.port.out;

import java.time.LocalDateTime;
import java.util.List;

import mx.com.accounts.domain.model.Movement;

public interface MovementPort {

	Movement saveMovement(Movement movement);
	
	List<Movement> loadByAccountId(Long accountId);
	
	List<Movement> loadByAccountIdAndDate(Long accountId, LocalDateTime startDate, LocalDateTime endDate);
	
	Movement loadByMovementId(Long movementId);

}
