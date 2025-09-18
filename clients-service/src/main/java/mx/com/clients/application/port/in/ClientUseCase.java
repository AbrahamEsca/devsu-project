package mx.com.clients.application.port.in;

import mx.com.clients.infrastructure.api.dto.ClientRequestDTO;
import mx.com.clients.infrastructure.api.dto.ClientResponseDTO;

public interface ClientUseCase {

	ClientResponseDTO createClient(ClientRequestDTO clientRequestDTO);
	
	ClientResponseDTO getClientById(String clientId);

	ClientResponseDTO updateClient(String clientId, ClientRequestDTO clientRequestDTO);

	void deleteClient(String clientId);

}
