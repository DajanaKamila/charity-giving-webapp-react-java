package myproject.CharityGivingWebApp.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import myproject.CharityGivingWebApp.views.Views;

@Entity
@Table(name = "Charity_Users")
public class User {

	@JsonView(Views.UserBasicView.class)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@JsonView(Views.UserBasicView.class)
	@NotBlank(message = "Email is a required field.")
	@Column(unique = true)
	private String email;

	@JsonView(Views.UserBasicView.class)
	@NotBlank(message = "Password is a required field.")
	@Column
	private String password;

	@JsonView(Views.UserBasicView.class)
	@NotBlank(message = "First name is a required field.")
	@Column
	private String firstName;

	@JsonView(Views.UserBasicView.class)
	@NotBlank(message = "Last name is a required field.")
	@Column
	private String lastName;

	@JsonIgnoreProperties({ "founder", "donations" })
	@OneToMany(mappedBy = "founder", cascade = CascadeType.ALL)
	private List<Fundraising> fundraisings;

	@JsonIgnoreProperties({ "donor", "fundraising", "fundraisings" })
	@OneToMany(mappedBy = "donor", cascade = CascadeType.ALL)
	private List<Donation> donations;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	public User() {
	}

	public User(String email, String password, String firstName, String lastName, List<Fundraising> fundraisings,
			List<Donation> donations, Set<Role> roles) {
		super();
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.fundraisings = fundraisings;
		this.donations = donations;
		this.roles = roles;
	}

	public void addFundraising(Fundraising fundraising) {
		fundraisings.add(fundraising);
	}

	public void addDonation(Donation donation) {
		donations.add(donation);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
	
	public void setId(Long id) {
		this.id = id;
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

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	

}


