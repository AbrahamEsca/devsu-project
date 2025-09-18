package mx.com.accounts.infrastructure.adapter;

import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import mx.com.accounts.application.port.out.ClientCachePort;

@Component
@AllArgsConstructor
public class ClientCacheAdapter implements ClientCachePort {
	
	private static final Logger LOGGER = LogManager.getLogger(ClientCacheAdapter.class);
	
	private final ConcurrentHashMap<String, String> clientCache = new ConcurrentHashMap<>();

    public void put(String clientId, String name) {
    	LOGGER.info("Start of the put method in the cache class");
    	
        clientCache.put(clientId, name);
    }

    public String get(String clientId) {
    	LOGGER.info("Start of the get method in the cache class");
    	
        return clientCache.get(clientId);
    }
    
}
