package mx.com.clients.application.port.out;

import mx.com.clients.domain.model.Client;

public interface ClientPort {

	Client saveClient(Client client);
	
	Client loadByClientId(String clientId);

	void deleteById(Long personId);
	
	boolean existsClient(String clientId);

}
