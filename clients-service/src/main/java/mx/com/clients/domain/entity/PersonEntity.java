package mx.com.clients.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
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
@Table(name = "person")
@Inheritance(strategy = InheritanceType.JOINED)
public class PersonEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "person_id")
	private Long personId;
	
	@Column(name = "name", nullable = false)
	private String name;
	
    @Column(name = "gender", nullable = false)
    private String gender;
    
    @Column(name = "age")
    private Integer age;

    @Column(name = "identification", nullable = false, unique = true)
    private String identification;

    @Column(name = "address")
    private String address;
    
    @Column(name = "phone")
    private String phone;

}
