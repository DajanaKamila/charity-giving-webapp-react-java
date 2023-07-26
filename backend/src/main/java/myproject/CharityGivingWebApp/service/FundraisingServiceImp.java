package myproject.CharityGivingWebApp.service;

import org.springframework.stereotype.Service;

import myproject.CharityGivingWebApp.model.Donation;
import myproject.CharityGivingWebApp.model.Fundraising;
import myproject.CharityGivingWebApp.repository.FundraisingRepository;

@Service
public class FundraisingServiceImp implements FundraisingService {

	private FundraisingRepository fundraisingRepo;

	public FundraisingServiceImp(FundraisingRepository fundraisingRepo) {
		super();
		this.fundraisingRepo = fundraisingRepo;
	}
	
	/**
	 * Save Fundraising in database. 
	 * @return - fundraising to be saved. 
	 */
	@Override
	public Fundraising saveFundraising(Fundraising fundraising) {
		return this.fundraisingRepo.save(fundraising);
	}

	/**
	 * Find Fundraising by given id. 
	 * @return - Fundraising found by given ID. 
	 */
	@Override
	public Fundraising findFundraisingById(Long id) {
		return this.fundraisingRepo.findById(id).orElse(null);
	}

	/**
	 * Find all Fundraising objects in the database. 
	 * @return - iterable of Fundrising objects found in database. 
	 */
	@Override
	public Iterable<Fundraising> findAllFundraisings() {
		return this.fundraisingRepo.findAll();
	}

	/**
	 * Add Donation to List<Donation> of the given Fundraising. Set Fundraising as a fundraising in Donation.
	 * @param fundraising - fundraising for which the donation was made
	 * @param donation - donation to be added to the fundraising
	 */
	@Override
	public void addDonationToFundraising(Fundraising fundraising, Donation donation) {
		donation.setFundraising(fundraising);
		fundraising.getDonations().add(donation);
		fundraisingRepo.save(fundraising);
	}

}
