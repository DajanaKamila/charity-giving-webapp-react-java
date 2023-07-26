package myproject.CharityGivingWebApp.model;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Fundraising {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank(message = "Name is a required field.")
	@Column
	private String name;

	@Column
	private String description;

	@Column
	private BigDecimal goal;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "founder_id")
	private User founder;
	
	@JsonIgnore
	@OneToMany(mappedBy = "fundraising",  cascade = CascadeType.ALL)
	private List<Donation> donations;
	
	public void addDonation(Donation donation) {
		donations.add(donation);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getGoal() {
		return goal;
	}

	public void setGoal(BigDecimal goal) {
		this.goal = goal;
	}

	public Long getId() {
		return id;
	}
	
	public User getFounder() {
	    return founder;
	}

	public void setFounder(User founder) {
	    this.founder = founder;
	}

	public List<Donation> getDonations() {
		return donations;
	}

	public void setDonations(List<Donation> donations) {
		this.donations = donations;
	}
	
	
}