package mx.com.accounts.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.com.accounts.domain.entity.AccountEntity;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

	AccountEntity findByAccountId(Long accountId);
	
	List<AccountEntity> findByClientId(String clientId);

	boolean existsByAccountNumber(String accountNumber);

}
