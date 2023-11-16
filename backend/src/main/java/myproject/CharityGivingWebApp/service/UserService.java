package myproject.CharityGivingWebApp.service;

import myproject.CharityGivingWebApp.model.Donation;
import myproject.CharityGivingWebApp.model.Fundraising;
import myproject.CharityGivingWebApp.model.User;

public interface UserService {

	User saveUser(User user);

	Iterable<User> findAllUsers();

	User findUserById(Long id);

	// void addFundraisingToUser(User user, Fundraising fundraising);

	void addDonationToUser(User user, Donation donation);

	public User findUserByEmail(String email);

}
