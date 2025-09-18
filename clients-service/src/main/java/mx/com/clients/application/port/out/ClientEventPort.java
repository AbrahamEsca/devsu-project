package mx.com.clients.application.port.out;

import mx.com.clients.domain.model.ClientEvent;

public interface ClientEventPort {

	void publishClientCreated(ClientEvent clientEvent);

}
