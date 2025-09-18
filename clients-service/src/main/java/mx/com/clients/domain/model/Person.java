package mx.com.clients.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Person {
	
	private Long personId;
	
    private String name;
    
    private String gender;
    
    private Integer age;
    
    private String identification;
    
    private String address;
    
    private String phone;
    
    public Person(String name, String gender, Integer age, String identification, String address, String phone) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.identification = identification;
        this.address = address;
        this.phone = phone;
    }

}
