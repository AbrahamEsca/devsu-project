package mx.com.accounts.application.port.in;

import java.util.List;

import mx.com.accounts.infrastructure.api.dto.MovementRequestDTO;
import mx.com.accounts.infrastructure.api.dto.MovementResponseDTO;
import mx.com.accounts.infrastructure.api.dto.ReportResponseDTO;

public interface MovementUseCase {

	MovementResponseDTO createMovement(MovementRequestDTO movementRequestDTO);
	
	List<MovementResponseDTO> getMovementsByAccountId(Long accountId);

	MovementResponseDTO updateMovement(Long movementId, MovementRequestDTO movementRequestDTO);
	
	ReportResponseDTO generateReport(String clientId, String startDate, String endDate, String token);

}
