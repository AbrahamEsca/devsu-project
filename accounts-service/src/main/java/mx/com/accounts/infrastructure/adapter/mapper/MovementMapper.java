package mx.com.accounts.infrastructure.adapter.mapper;

import mx.com.accounts.domain.entity.MovementEntity;
import mx.com.accounts.domain.model.Movement;

public class MovementMapper {
	
    public static MovementEntity toEntity(Movement m){
    	MovementEntity me = new MovementEntity();
    	
    	me.setMovementId(m.getMovementId());
    	me.setAccountId(m.getAccountId());
    	me.setMovementType(m.getMovementType());
    	me.setMovementDate(m.getMovementDate());
    	me.setAmount(m.getAmount());
    	me.setBalance(m.getBalance());
        
        return me;
    }
    
    public static Movement toDomain(MovementEntity me) {
    	Movement m = new Movement(me.getMovementId(), me.getAccountId(), me.getMovementType(), me.getMovementDate(), me.getAmount(), me.getBalance());
        
        return m;
    }
    
}
