package mx.com.accounts.common.util;

import java.security.SecureRandom;

public class AccountUtils {

	private static final SecureRandom RANDOM = new SecureRandom();
	
    private static final int LENGTH = 11;

    public static String generateAccountNumber() {
        StringBuilder sb = new StringBuilder(LENGTH);
        for (int i = 0; i < LENGTH; i++) {
            int digit = RANDOM.nextInt(10); // 0-9
            sb.append(digit);
        }
        return sb.toString();
    }
    
}
