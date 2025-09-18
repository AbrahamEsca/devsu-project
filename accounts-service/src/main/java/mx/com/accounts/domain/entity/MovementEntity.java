package mx.com.accounts.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
@Table(name = "movement")
public class MovementEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "movement_id")
	private Long movementId;
	
	@Column(name = "accountId", nullable = false)
    private Long accountId;

    @Column(name = "movement_type", nullable = false)
    private String movementType;
    
	@Column(name = "movement_date", nullable = false)
	private LocalDateTime movementDate;
	
	@Column(name = "amount", nullable = false)
	private BigDecimal amount;

	@Column(name = "balance", nullable = false)
	private BigDecimal balance;

}
