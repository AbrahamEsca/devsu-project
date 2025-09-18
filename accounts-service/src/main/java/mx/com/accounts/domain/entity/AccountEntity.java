package mx.com.accounts.domain.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account")
public class AccountEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "account_id")
	private Long accountId;
	
	@Column(name = "client_id", nullable = false, unique = true)
    private String clientId;

    @Column(name = "account_number", nullable = false)
    private String accountNumber;
    
	@Column(name = "account_type_id", nullable = false)
	private Long accountTypeId;
	
	@ManyToOne
	@JoinColumn(name = "account_type_id", referencedColumnName = "account_type_id", insertable = false, updatable = false)
	private AccountTypeEntity accountTypeEntity;
	
	@Column(name = "initial_balance", nullable = false)
	private BigDecimal initialBalance;
	
	@Column(name = "current_balance", nullable = false)
	private BigDecimal currentBalance;

    @Column(name = "active", nullable = false)
    private Boolean active;
	
}
