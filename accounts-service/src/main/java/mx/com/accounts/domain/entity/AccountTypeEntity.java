package mx.com.accounts.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "account_type")
public class AccountTypeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "account_type_id")
	private Long accountTypeId;
	
	@Column(name = "dsc_account", nullable = false, unique = true)
    private String descAccount;

    @Column(name = "active", nullable = false)
    private Boolean active;
	
}
