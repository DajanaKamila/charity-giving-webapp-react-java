package myproject.CharityGivingWebApp.service;

import org.springframework.stereotype.Service;

import myproject.CharityGivingWebApp.model.Donation;
import myproject.CharityGivingWebApp.model.Fundraising;
import myproject.CharityGivingWebApp.model.User;
import myproject.CharityGivingWebApp.repository.UserRepository;

@Service
public class UserServiceImp implements UserService {

	private UserRepository userRepo;
	
	public UserServiceImp(UserRepository userRepo) {
		super();
		this.userRepo = userRepo;
	}
	
	/**
	 * Save User object in the database. 
	 * @param user - User to be saved 
	 * @return - user that was saved
	 */
	@Override
	public User saveUser(User user) {
		return this.userRepo.save(user);
	}

	/**
	 * Find all User objects available in database. 
	 * @return - iterable of User objects
	 */
	@Override
	public Iterable<User> findAllUsers() {
		return this.userRepo.findAll();
	}
	
	/**
	 * Find User in the database by ID. 
	 * @param id - id of a User to be found. 
	 * @return - found User or - if User does not exist - null
	 */
	@Override
	public User findUserById(Long id) {
		return this.userRepo.findById(id).orElse(null);
	}
	
	/**
	 * Find User by username (String) and password (String). 
	 * @return - found User with given username and password.
	 */
	@Override
	public User findUserByUsernameAndPassword(String username, String password) {
		return this.userRepo.findByUsernameAndPassword(username, password);
	}

	/**
	 * Add Fundraising to List<Fundraising> of the given User. Set User as a founder in Fundrising.
	 * @param user - User who creates fundraising
	 * @param fundraising - fundraising to be added to the user
	 */
	@Override
	public void addFundraisingToUser(User user, Fundraising fundraising) {
		fundraising.setFounder(user);
		user.getFundraisings().add(fundraising);
		userRepo.save(user);
	}
	
	/**
	 * Add Donation to List<Donation> of the given User. Set User as a donor in Donation.
	 * @param user - User who makes donation
	 * @param donation - donation to be added to the user
	 */
	@Override
	public void addDonationToUser(User user, Donation donation) {
		donation.setDonor(user);
		user.getDonations().add(donation);
		userRepo.save(user);
	}


}
