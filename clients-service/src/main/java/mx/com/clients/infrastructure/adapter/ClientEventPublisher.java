package mx.com.clients.infrastructure.adapter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import mx.com.clients.application.port.out.ClientEventPort;
import mx.com.clients.domain.model.ClientEvent;

@Component
@AllArgsConstructor
public class ClientEventPublisher implements ClientEventPort {
	
	private static final Logger LOGGER = LogManager.getLogger(ClientEventPublisher.class);
	
	private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publishClientCreated(ClientEvent event) {
    	LOGGER.info("Start of the publishClientCreated method in the event class");
    	
        kafkaTemplate.send("client-events", event.getClientId(), event);
    }

}
