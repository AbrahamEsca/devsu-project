package mx.com.accounts.domain.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.com.accounts.domain.entity.MovementEntity;

@Repository
public interface MovementRepository extends JpaRepository<MovementEntity, Long> {

	MovementEntity findByMovementId(Long movementId);

	List<MovementEntity> findByAccountId(Long accountId);
	
	List<MovementEntity> findByAccountIdAndMovementDateBetween(Long accountId, LocalDateTime starDate, LocalDateTime endDate);

}
