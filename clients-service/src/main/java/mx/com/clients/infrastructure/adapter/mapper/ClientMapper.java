package mx.com.clients.infrastructure.adapter.mapper;

import mx.com.clients.domain.entity.ClientEntity;
import mx.com.clients.domain.model.Client;

public class ClientMapper {
	
    public static ClientEntity toEntity(Client c){
    	ClientEntity ce = new ClientEntity();
    	
        ce.setPersonId(c.getPersonId());
        ce.setName(c.getName());
        ce.setGender(c.getGender());
        ce.setAge(c.getAge());
        ce.setIdentification(c.getIdentification());
        ce.setAddress(c.getAddress());
        ce.setPhone(c.getPhone());
        ce.setClientId(c.getClientId());
        ce.setPassword(c.getPassword());
        ce.setActive(c.getActive());
        
        return ce;
    }
    
    public static Client toDomain(ClientEntity ce) {
        Client c = new Client(ce.getClientId(), ce.getPassword(),
                ce.getName(), ce.getGender(), ce.getAge(),
                ce.getIdentification(), ce.getAddress(), ce.getPhone());
        c.setPersonId(ce.getPersonId());
        c.setActive(ce.getActive());
        
        return c;
    }
    
}
