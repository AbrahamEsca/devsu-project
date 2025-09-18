package mx.com.clients.infrastructure.api;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import mx.com.clients.application.port.in.ClientUseCase;
import mx.com.clients.common.constants.ApiConstants;
import mx.com.clients.infrastructure.api.dto.ClientRequestDTO;
import mx.com.clients.infrastructure.api.dto.ClientResponseDTO;

@Validated
@AllArgsConstructor
@RestController
@RequestMapping(ApiConstants.BASE_PATH)
@Tag(name = "Clients", description = "Clients Administration API")
public class ClientController {
	
	private static final Logger LOGGER = LogManager.getLogger(ClientController.class);
	
	private final ClientUseCase clientUseCase;
	
	/**
	 * Description: Service to create a client by ID
	 * 
	 * @param request the HTTP servlet request
	 * 
	 * @param clientRequestDTO request body list with the new client data
	 * 
	 * @return ResponseEntity the service response
	 * 
	 **/
	@PostMapping(value = ApiConstants.CLIENTS_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Create a new client")
	public ResponseEntity<ClientResponseDTO> createClient(HttpServletRequest request, 
			@Valid @RequestBody ClientRequestDTO clientRequestDTO) {
		LOGGER.info("Start of the createClient method in the controller class");

		Collections.list(request.getHeaderNames()).stream()
		.forEach(header -> LOGGER.info("Header: {} = {}", header, request.getHeader(header)));

		ClientResponseDTO clientResponseDTO = clientUseCase.createClient(clientRequestDTO);

		return new ResponseEntity<>(clientResponseDTO, HttpStatus.CREATED);
	}
	
	/**
	 * Description: Service to get a client by ID
	 * 
	 * @param request the HTTP servlet request
	 * 
	 * @param clientId the ID of the client to retrieve
	 * 
	 * @return ResponseEntity the service response
	 * 
	 **/
	@GetMapping(value = ApiConstants.CLIENTS_ID_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Get an client by ID")
    public ResponseEntity<ClientResponseDTO> getClientById(HttpServletRequest request, @PathVariable("id") String clientId) {
		LOGGER.info("Start of the getClientById method in the controller class");
		
		Collections.list(request.getHeaderNames()).stream()
        .forEach(header -> LOGGER.info("Header: {} = {}", header, request.getHeader(header)));
		
        return ResponseEntity.ok(clientUseCase.getClientById(clientId));
    }
	
	
	/**
	 * Description: Service to update a client by ID
	 * 
	 * @param request the HTTP servlet request
	 * 
	 * @param clientId the ID of the client to update
	 * 
	 * @param clientRequestDTO request body with the new client data
	 * 
	 * @return ResponseEntity the service response
	 * 
	 **/
	@PutMapping(value = ApiConstants.CLIENTS_ID_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Update a client by ID")
    public ResponseEntity<ClientResponseDTO> updateClient(HttpServletRequest request, @PathVariable("id") String clientId,
                                                              @Valid @RequestBody ClientRequestDTO clientRequestDTO) {
		LOGGER.info("Start of the updateClient method in the controller class");
		
		Collections.list(request.getHeaderNames()).stream()
        .forEach(header -> LOGGER.info("Header: {} = {}", header, request.getHeader(header)));
		
        ClientResponseDTO clientResponseDTO = clientUseCase.updateClient(clientId, clientRequestDTO);
        
        return ResponseEntity.ok(clientResponseDTO);
    }
	
	/**
	 * Description: Service to delete a client by ID
	 * 
	 * @param request the HTTP servlet request
	 * 
	 * @param clientId the ID of the client to delete
	 * 
	 * @return ResponseEntity the service response
	 * 
	 **/
	@DeleteMapping(value = ApiConstants.CLIENTS_ID_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Delete a client by ID")
    public ResponseEntity<Void> deleteClient(HttpServletRequest request, @PathVariable("id") String clientId) {
		LOGGER.info("Start of the deleteClient method in the controller class");
		
		Collections.list(request.getHeaderNames()).stream()
        .forEach(header -> LOGGER.info("Header: {} = {}", header, request.getHeader(header)));
		
		clientUseCase.deleteClient(clientId);
        
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
