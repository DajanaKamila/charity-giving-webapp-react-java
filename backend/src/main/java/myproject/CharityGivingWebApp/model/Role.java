package myproject.CharityGivingWebApp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {
	
	@Id
	private int id;
	
	@Enumerated(EnumType.STRING)
	@Column(length=20)
	private ERole name;
	
	public Role() {
		
	}
	
	public Role(ERole name) {
		this.name = name;
	}

	public ERole getName() {
		return name;
	}

	public void setName(ERole name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

}
