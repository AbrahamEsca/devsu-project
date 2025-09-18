package mx.com.accounts.infrastructure.api.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportResponseDTO {
	
	private String nameClient;
	
	private List<AccountReportResponseDTO> accounts;

}
