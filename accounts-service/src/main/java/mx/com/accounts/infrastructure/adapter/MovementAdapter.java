package mx.com.accounts.infrastructure.adapter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import mx.com.accounts.application.port.out.MovementPort;
import mx.com.accounts.domain.entity.MovementEntity;
import mx.com.accounts.domain.model.Movement;
import mx.com.accounts.domain.repository.MovementRepository;
import mx.com.accounts.infrastructure.adapter.mapper.MovementMapper;

@Component
@AllArgsConstructor
public class MovementAdapter implements MovementPort {
	
	private static final Logger LOGGER = LogManager.getLogger(MovementAdapter.class);
	
	private final MovementRepository movementRepository;
	
	/**
	 * Description: Method to save movement
	 * 
	 * @param movement body with the new movement data
	 * 
	 * @return Movement the method response
	 * 
	 **/
	@Override
	public Movement saveMovement(Movement movement) {
		LOGGER.info("Start of the saveMovement method in the service class");

		var saved = movementRepository.save(MovementMapper.toEntity(movement));
		
        return MovementMapper.toDomain(saved);
	}
	
	/**
	 * Description: Method to load movements by ID
	 * 
	 * @param accountId the ID of the movements to retrieve
	 * 
	 * @return List<Movement> the method response
	 * 
	 **/
	@Override
	public List<Movement> loadByAccountId(Long accountId) {
		LOGGER.info("Start of the loadByAccountId method in the service class");

		return movementRepository.findByAccountId(accountId)
				.stream()
				.map(MovementMapper::toDomain)
				.collect(Collectors.toList());
	}
	
	/**
	 * Description: Method to load movements by ID and Date
	 * 
	 * @param accountId the ID of the movements to retrieve
	 * 
	 * @return List<Movement> the method response
	 * 
	 **/
	@Override
	public List<Movement> loadByAccountIdAndDate(Long accountId, LocalDateTime starDate, LocalDateTime endDate) {
		LOGGER.info("Start of the loadByAccountIdAndDate method in the service class");

		return movementRepository.findByAccountIdAndMovementDateBetween(accountId, starDate, endDate)
				.stream()
				.map(MovementMapper::toDomain)
				.collect(Collectors.toList());
	}
	
	/**
	 * Description: Method to load movement by ID
	 * 
	 * @param movementId the ID of the movement to retrieve
	 * 
	 * @return Movement the method response
	 * 
	 **/
	@Override
	public Movement loadByMovementId(Long movementId) {
		LOGGER.info("Start of the loadByMovementId method in the service class");

		MovementEntity movementEntity = movementRepository.findByMovementId(movementId);
		Movement       movement       = null;
		if(movementEntity != null) {
			movement = MovementMapper.toDomain(movementEntity);
		}
	    
	    return movement;
	}

}
