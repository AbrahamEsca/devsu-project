package mx.com.clients.common.util;

import java.time.LocalDate;
import java.util.UUID;

import mx.com.clients.common.constants.ClientConstants;

public class ClientUtils {

	public static String generateClientId() {
        String prefix = ClientConstants.CLIENTE_PREFIX;
        String date = LocalDate.now().toString().replace("-", "");
        String random = UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        
        return prefix + "-" + date + "-" + random;
    }
    
}
