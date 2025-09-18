package mx.com.clients.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientEvent {

	private String clientId;

	private String name;

	private Boolean active;

}
