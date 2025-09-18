package mx.com.clients.domain.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import mx.com.clients.application.port.in.ClientUseCase;
import mx.com.clients.application.port.out.ClientEventPort;
import mx.com.clients.application.port.out.ClientPort;
import mx.com.clients.common.util.ClientUtils;
import mx.com.clients.common.util.EncryptUtils;
import mx.com.clients.domain.exception.ClientAlreadyExistsException;
import mx.com.clients.domain.exception.ClientNotFoundException;
import mx.com.clients.domain.model.Client;
import mx.com.clients.domain.model.ClientEvent;
import mx.com.clients.infrastructure.api.dto.ClientRequestDTO;
import mx.com.clients.infrastructure.api.dto.ClientResponseDTO;

@Service
@AllArgsConstructor
public class ClientService implements ClientUseCase {
	
	private static final Logger LOGGER = LogManager.getLogger(ClientService.class);
	
	private final ClientPort clientPort;
	
	private final ClientEventPort clientEventPort;
	
	/**
	 * Description: Method to create a client
	 * 
	 * @param clientRequestDTO request body with the new client data
	 * 
	 * @return ClientResponseDTO the method response
	 * 
	 **/
	@Override
	public ClientResponseDTO createClient(ClientRequestDTO clientRequestDTO) {
		LOGGER.info("Start of the createClient method in the service class");

		String clientId = ClientUtils.generateClientId();
		
		if (clientPort.existsClient(clientId)) {
			throw new ClientAlreadyExistsException("El cliente ya esta registrado");
		}
		
        String hashPass = EncryptUtils.hashPassword(clientRequestDTO.getPassword()); //Encrypt password
        Client client   = new Client(clientId, hashPass, clientRequestDTO.getName(), clientRequestDTO.getGender(), clientRequestDTO.getAge(),
        		clientRequestDTO.getIdentification(), clientRequestDTO.getAddress(), clientRequestDTO.getPhone());
        
        client = clientPort.saveClient(client); //Save client
        
        ClientEvent clientEvent = new ClientEvent(client.getClientId(), client.getName(), client.getActive());
        clientEventPort.publishClientCreated(clientEvent); //Publish event
        
        return new ClientResponseDTO(client.getPersonId(), client.getClientId(), client.getName(), client.getActive());
	}
	
	/**
	 * Description: Method to get a client by ID
	 * 
	 * @param clientId the ID of the client to retrieve
	 * 
	 * @return ClientResponseDTO the method response
	 * 
	 **/
	@Override
	public ClientResponseDTO getClientById(String clientId) {
		LOGGER.info("Start of the getClientById method in the service class");

		Client client = clientPort.loadByClientId(clientId); //Load by client
		if(client == null) {
			new ClientNotFoundException("No se encontro cliente");
		}
		
		return new ClientResponseDTO(client.getPersonId(), client.getClientId(), client.getName(), client.getActive());
	}
	
	/**
	 * Description: Method to update a client by ID
	 * 
	 * @param clientId the ID of the client to update
	 * 
	 * @param clientRequestDTO request body with the client data
	 * 
	 * @return ClientResponseDTO the method response
	 * 
	 **/
	@Override
    public ClientResponseDTO updateClient(String clientId, ClientRequestDTO clientRequestDTO) {
		LOGGER.info("Start of the updateClient method in the service class");
		
		Client client = clientPort.loadByClientId(clientId);
		if(client == null) {
			new ClientNotFoundException("No se encontro cliente");
		}
		
		String hashPass = EncryptUtils.hashPassword(clientRequestDTO.getPassword());
		Client clientAux = new Client(clientId, hashPass, clientRequestDTO.getName(), clientRequestDTO.getGender(), clientRequestDTO.getAge(),
        		clientRequestDTO.getIdentification(), clientRequestDTO.getAddress(), clientRequestDTO.getPhone());
		clientAux.setPersonId(client.getPersonId());
		clientAux.setActive(clientRequestDTO.isActive());
        
        client = clientPort.saveClient(clientAux);
		
		return new ClientResponseDTO(client.getPersonId(), client.getClientId(), client.getName(), client.getActive());
    }
	
	/**
	 * Description: Method to delete a client by ID
	 * 
	 * @param clientId the ID of the client to delete
	 * 
	 * @return void
	 * 
	 **/
	@Override
    public void deleteClient(String clientId) {
		LOGGER.info("Start of the deleteClient method in the service class");
		
		Client client = clientPort.loadByClientId(clientId);
		if(client == null) {
			new ClientNotFoundException("No se encontro cliente");
		}
		
		clientPort.deleteById(client.getPersonId());
    }

}
