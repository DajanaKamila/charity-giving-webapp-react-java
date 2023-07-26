package myproject.CharityGivingWebApp.service;

import org.springframework.stereotype.Service;

import myproject.CharityGivingWebApp.model.Donation;
import myproject.CharityGivingWebApp.repository.DonationRepository;

@Service
public class DonationServiceImp implements DonationService {

	private DonationRepository donationRepo;

	public DonationServiceImp(DonationRepository donationRepo) {
		super();
		this.donationRepo = donationRepo;
	}

	/**
	 * Save Donation object in the database. 
	 * @param donation - Donation to be saved 
	 * @return - donation that was saved
	 */
	@Override
	public Donation saveDonation(Donation donation) {
		return this.donationRepo.save(donation);
	}

	/**
	 * Find Donation in the database by it's ID. 
	 * @param id - id of a Donation to be found. 
	 * @return - found Donation or - if Donation does not exist - null
	 */
	@Override
	public Donation findDonationById(Long id) {
		return this.donationRepo.findById(id).orElse(null);
	}

	/**
	 * Find all Donation objects available in database. 
	 * @return - iterable of Donation objects
	 */
	@Override
	public Iterable<Donation> findAllDonations() {
		return this.donationRepo.findAll();
	}

}
