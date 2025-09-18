package mx.com.accounts.infrastructure.adapter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import mx.com.accounts.application.port.out.ClientCachePort;
import mx.com.accounts.application.port.out.ClientEventPort;
import mx.com.accounts.domain.model.ClientEvent;

@Component
@AllArgsConstructor
public class ClientEventConsumer implements ClientEventPort {
	
	private static final Logger LOGGER = LogManager.getLogger(ClientEventConsumer.class);
	
	private final ClientCachePort clientCachePort;

    @KafkaListener(topics = "client-events", groupId = "accounts-service")
    public void consumeClientCreated(ClientEvent clientEvent) {
    	LOGGER.info("Start of the consumeClientCreated method in the event class");
    	
        if (clientEvent.getActive()) {
        	clientCachePort.put(clientEvent.getClientId(), clientEvent.getName());
        }
    }

}
