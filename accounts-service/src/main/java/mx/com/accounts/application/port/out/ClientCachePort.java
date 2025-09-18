package mx.com.accounts.application.port.out;

public interface ClientCachePort {

	void put(String clientId, String name);

    String get(String clientId);

}
