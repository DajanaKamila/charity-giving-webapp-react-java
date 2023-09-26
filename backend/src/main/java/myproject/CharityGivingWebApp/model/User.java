package myproject.CharityGivingWebApp.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "Charity_Users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank(message = "Username is a required field.")
	@Column(unique = true)
	private String username;

	@NotBlank(message = "Password is a required field.")
	@Column
	private String password;

	@NotBlank(message = "First name is a required field.")
	@Column
	private String firstName;

	@NotBlank(message = "Last name is a required field.")
	@Column
	private String lastName;

	@JsonIgnoreProperties({ "founder", "donations" })
	@OneToMany(mappedBy = "founder", cascade = CascadeType.ALL)
	private List<Fundraising> fundraisings;

	@JsonIgnoreProperties({ "donor", "fundraising", "fundraisings" })
	@OneToMany(mappedBy = "donor", cascade = CascadeType.ALL)
	private List<Donation> donations;

	public void addFundraising(Fundraising fundraising) {
		fundraisings.add(fundraising);
	}

	public void addDonation(Donation donation) {
		donations.add(donation);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getId() {
		return id;
	}

	public List<Fundraising> getFundraisings() {
		return fundraisings;
	}

	public void setFundraisings(List<Fundraising> fundraisings) {
		this.fundraisings = fundraisings;
	}

	public List<Donation> getDonations() {
		return donations;
	}

	public void setDonations(List<Donation> donations) {
		this.donations = donations;
	}

}
