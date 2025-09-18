package mx.com.accounts.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.com.accounts.domain.entity.AccountTypeEntity;


@Repository
public interface AccountTypeRepository extends JpaRepository<AccountTypeEntity, Long> {

	AccountTypeEntity findByAccountTypeId(Long accountTypeId);
	
}
