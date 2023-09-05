package myproject.CharityGivingWebApp.service;

import myproject.CharityGivingWebApp.model.Donation;

public interface DonationService {

	Donation saveDonation(Donation donation);

	Donation findDonationById(Long id);

	Iterable<Donation> findAllDonations();

}
