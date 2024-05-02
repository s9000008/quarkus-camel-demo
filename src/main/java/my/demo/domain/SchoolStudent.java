package my.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="SCHOOL_STUDENT")
@Data
public class SchoolStudent {

	@Id
	@Column(name="CODE")
	private String code;
	
	@Column(name="SCHOOL_CODE")
	private String schoolCode;
	
	@Column(name="NAME")
	private String name;
	
	
	
	
	
}
