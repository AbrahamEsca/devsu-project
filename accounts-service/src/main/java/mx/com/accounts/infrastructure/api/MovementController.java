package mx.com.accounts.infrastructure.api;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import mx.com.accounts.application.port.in.MovementUseCase;
import mx.com.accounts.common.constants.ApiConstants;
import mx.com.accounts.infrastructure.api.dto.MovementRequestDTO;
import mx.com.accounts.infrastructure.api.dto.MovementResponseDTO;
import mx.com.accounts.infrastructure.api.dto.ReportResponseDTO;

@Validated
@AllArgsConstructor
@RestController
@RequestMapping(ApiConstants.BASE_PATH)
@Tag(name = "Movements", description = "Movements Administration API")
public class MovementController {
	
	private static final Logger LOGGER = LogManager.getLogger(MovementController.class);
	
	private final MovementUseCase movementUseCase;
	
	/**
	 * Description: Service to create a movement by ID
	 * 
	 * @param request the HTTP servlet request
	 * 
	 * @param movementRequestDTO request body list with the new movement data
	 * 
	 * @return ResponseEntity the service response
	 * 
	 **/
	@PostMapping(value = ApiConstants.MOVEMENTS_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Create a new movement")
	public ResponseEntity<MovementResponseDTO> createMovement(HttpServletRequest request, 
			@Valid @RequestBody MovementRequestDTO movementRequestDTO) {
		LOGGER.info("Start of the createMovement method in the controller class");

		Collections.list(request.getHeaderNames()).stream()
		.forEach(header -> LOGGER.info("Header: {} = {}", header, request.getHeader(header)));

		MovementResponseDTO movementResponseDTO = movementUseCase.createMovement(movementRequestDTO);

		return new ResponseEntity<>(movementResponseDTO, HttpStatus.CREATED);
	}
	
	/**
	 * Description: Service to get a list to movements by ID
	 * 
	 * @param request the HTTP servlet request
	 * 
	 * @param accountId the ID of the movements to retrieve
	 * 
	 * @return ResponseEntity the service response
	 * 
	 **/
	@GetMapping(value = ApiConstants.MOVEMENTS_ID_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Get a movements by accountId")
    public ResponseEntity<List<MovementResponseDTO>> getMovementsByAccountId(HttpServletRequest request, @PathVariable("id") Long accountId) {
		LOGGER.info("Start of the getMovementsByAccountId method in the controller class");
		
		Collections.list(request.getHeaderNames()).stream()
        .forEach(header -> LOGGER.info("Header: {} = {}", header, request.getHeader(header)));
		
        return ResponseEntity.ok(movementUseCase.getMovementsByAccountId(accountId));
    }
	
	
	/**
	 * Description: Service to update a movement by ID
	 * 
	 * @param request the HTTP servlet request
	 * 
	 * @param movementId the ID of the account to update
	 * 
	 * @param movementRequestDTO request body with the new movement data
	 * 
	 * @return ResponseEntity the service response
	 * 
	 **/
	@PutMapping(value = ApiConstants.MOVEMENTS_ID_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Update a movement by ID")
    public ResponseEntity<MovementResponseDTO> updateMovement(HttpServletRequest request, @PathVariable("id") Long movementId,
                                                              @Valid @RequestBody MovementRequestDTO movementRequestDTO) {
		LOGGER.info("Start of the updateMovement method in the controller class");
		
		Collections.list(request.getHeaderNames()).stream()
        .forEach(header -> LOGGER.info("Header: {} = {}", header, request.getHeader(header)));
		
		MovementResponseDTO movementResponseDTO = movementUseCase.updateMovement(movementId, movementRequestDTO);
        
        return ResponseEntity.ok(movementResponseDTO);
    }
	
	/**
	 * Description: Service to get a list to movements by ID
	 * 
	 * @param request the HTTP servlet request
	 * 
	 * @param accountId the ID of the movements to retrieve
	 * 
	 * @return ResponseEntity the service response
	 * 
	 **/
	@GetMapping(value = ApiConstants.REPORTS_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Generate report by clientId and dates")
    public ResponseEntity<ReportResponseDTO> generateReport(HttpServletRequest request, @RequestParam String clientId, 
    		@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") String startDate, 
    		@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") String endDate) {
		LOGGER.info("Start of the generateReport method in the controller class");
		
		Collections.list(request.getHeaderNames()).stream()
        .forEach(header -> LOGGER.info("Header: {} = {}", header, request.getHeader(header)));
		
		String token = request.getHeader("Authorization");
		
        return ResponseEntity.ok(movementUseCase.generateReport(clientId, startDate, endDate, token));
    }

}
