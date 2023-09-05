package myproject.CharityGivingWebApp.service;

import myproject.CharityGivingWebApp.model.Donation;
import myproject.CharityGivingWebApp.model.Fundraising;

public interface FundraisingService {

	Fundraising saveFundraising(Fundraising fundraising);

	Fundraising findFundraisingById(Long id);

	Iterable<Fundraising> findAllFundraisings();

	// void addDonationToFundraising(Fundraising fundraising, Donation donation);

	Iterable<Donation> findDonationsOfFundraising(Long id);

}
