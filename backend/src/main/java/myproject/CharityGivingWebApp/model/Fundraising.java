package myproject.CharityGivingWebApp.model;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Fundraising {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	private String name;

	@Column(length = 5000)
	private String description;

	@Column
	private BigDecimal goal;

	@JsonIgnoreProperties({ "donations", "fundraisings" })
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "founder_id")
	private User founder;

	@JsonIgnoreProperties({ "donor", "fundraisings", "fundraising" })
	@OneToMany(mappedBy = "fundraising", cascade = CascadeType.ALL)
	private List<Donation> donations;

	public Fundraising() {}
	
	public Fundraising(String name, String description, BigDecimal goal, User founder, List<Donation> donations) {
		super();
		this.name = name;
		this.description = description;
		this.goal = goal;
		this.founder = founder;
		this.donations = donations;
	}

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
