package mx.com.accounts.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movement {

	private Long movementId;

	private Long accountId;
	
	private String movementType;

	private LocalDateTime movementDate;

	private BigDecimal amount;

	private BigDecimal balance;

	public Movement(Long accountId, String movementType, BigDecimal amount) {
		this.accountId = accountId;
		this.movementType = movementType;
		this.amount = amount;
	}

}
