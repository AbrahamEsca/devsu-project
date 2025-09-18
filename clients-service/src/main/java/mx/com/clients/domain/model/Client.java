package mx.com.clients.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Client extends Person {

	private String clientId;

	private String password;

	private Boolean active;

	public Client(String clientId, String password, String name, String gender, Integer age,
			String identification, String address, String phone) {
		super(name, gender, age, identification, address, phone);
		this.clientId = clientId;
		this.password = password;
		this.active = true;
	}

}
