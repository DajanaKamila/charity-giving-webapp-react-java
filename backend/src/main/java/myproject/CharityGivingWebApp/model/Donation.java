package myproject.CharityGivingWebApp.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import myproject.CharityGivingWebApp.views.Views;

@Entity
public class Donation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(Views.DonationView.class)
	private Long id;

	@NotNull(message = "Amount is a required field.")
	@Column
	@JsonView(Views.DonationView.class)
	private BigDecimal amount;

	@Column
	@JsonView(Views.DonationView.class)
	private String comment;

	@Column
	@JsonView(Views.DonationView.class)
	private boolean isAnonymous;

	@JsonIgnoreProperties({ "donations", "fundraisings" })
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "donor_id")
	@JsonView(Views.DonationView.class)
	private User donor;

	@JsonIgnoreProperties({ "donations", "founder" })
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "fundraising_id")
	@JsonView(Views.DonationView.class)
	private Fundraising fundraising;

	public Donation() {}

	public Donation(@NotNull(message = "Amount is a required field.") BigDecimal amount, String comment,
			boolean isAnonymous, User donor, Fundraising fundraising) {
		super();
		this.amount = amount;
		this.comment = comment;
		this.isAnonymous = isAnonymous;
		this.donor = donor;
		this.fundraising = fundraising;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public User getDonor() {
		return donor;
	}

	public void setDonor(User donor) {
		this.donor = donor;
	}

	public Fundraising getFundraising() {
		return fundraising;
	}

	public void setFundraising(Fundraising fundraising) {
		this.fundraising = fundraising;
	}

	public Long getId() {
		return id;
	}

	public boolean isIsAnonymous() {
		return isAnonymous;
	}

	public void setIsAnonymous(boolean isAnonymous) {
		this.isAnonymous = isAnonymous;
	}

}
