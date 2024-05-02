package my.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="SCHOOL")
@Data
public class School{

	@Id
	@Column(name="CODE")
	private String code;
	
	@Column(name="NAME")
	private String name;
	
}
