package mx.com.clients.infrastructure.adapter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import mx.com.clients.application.port.out.ClientPort;
import mx.com.clients.domain.entity.ClientEntity;
import mx.com.clients.domain.model.Client;
import mx.com.clients.domain.repository.ClientRepository;
import mx.com.clients.infrastructure.adapter.mapper.ClientMapper;

@Component
@AllArgsConstructor
public class ClientAdapter implements ClientPort {
	
	private static final Logger LOGGER = LogManager.getLogger(ClientAdapter.class);
	
	private final ClientRepository clientRepository;
	
	/**
	 * Description: Method to save a client
	 * 
	 * @param client body with the new client data
	 * 
	 * @return Client the method response
	 * 
	 **/
	@Override
	public Client saveClient(Client client) {
		LOGGER.info("Start of the saveClient method in the service class");

		var saved = clientRepository.save(ClientMapper.toEntity(client));
		
        return ClientMapper.toDomain(saved);
	}
	
	/**
	 * Description: Method to load a client by ID
	 * 
	 * @param clientId the ID of the client to retrieve
	 * 
	 * @return Client the method response
	 * 
	 **/
	@Override
	public Client loadByClientId(String clientId) {
		LOGGER.info("Start of the loadByClientId method in the service class");

		ClientEntity clientEntity = clientRepository.findByClientId(clientId);
		Client       client       = null;
		if(clientEntity != null) {
			client = ClientMapper.toDomain(clientEntity);
		}
	    
	    return client;
	}
	
	/**
	 * Description: Method to delete a client by ID
	 * 
	 * @param personId the ID of the client to delete
	 * 
	 * @return void
	 * 
	 **/
	@Override
    public void deleteById(Long personId) {
		LOGGER.info("Start of the deleteById method in the service class");
	    
		clientRepository.deleteById(personId);
    }
	
	/**
	 * Description: Method to delete a client by ID
	 * 
	 * @param clientId the ID of the client to delete
	 * 
	 * @return boolean the method response
	 * 
	 **/
	@Override
    public boolean existsClient(String clientId) {
		LOGGER.info("Start of the existsClient method in the service class");
		
		return clientRepository.existsByClientId(clientId);
    }

}
