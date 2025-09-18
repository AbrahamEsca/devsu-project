package mx.com.clients.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.com.clients.domain.entity.ClientEntity;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

	ClientEntity findByClientId(String clientId);

	boolean existsByClientId(String clientId);

}
