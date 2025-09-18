package mx.com.accounts.application.port.out;

import mx.com.accounts.domain.model.ClientEvent;

public interface ClientEventPort {

	void consumeClientCreated(ClientEvent clientEvent);

}
