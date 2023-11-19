//package myproject.CharityGivingWebApp.service;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import java.util.List;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import myproject.CharityGivingWebApp.model.Donation;
//import myproject.CharityGivingWebApp.model.Fundraising;
//import myproject.CharityGivingWebApp.model.User;
//import myproject.CharityGivingWebApp.repository.UserRepository;
//
//@SpringBootTest
//class TestUserService {
//	
//	@InjectMocks
//	UserServiceImp userService;
//	
//	@Mock
//	UserRepository mockUserRepo;
//	
//	@Mock 
//	User mockUser;
//	
//	@Mock
//	Fundraising mockFundraising;
//	
//	@Mock
//	Donation mockDonation;
//	
//	@Mock
//	List<Fundraising> mockFundraisingList;
//	
//	@Mock
//	List<Donation> mockDonationsList;
//
//	@Test
//	@DisplayName("Save user")
//	void test_addUser_callsSaveUserFromUserRepo_returnsUserToBeSaved() {
//		mockUser = new User();
//
//		when(mockUserRepo.save(mockUser)).thenReturn(mockUser);
//		
//		User actual = userService.saveUser(mockUser);
//		assertThat(actual).isEqualTo(mockUser);
//		verify(mockUserRepo, times(1)).save(mockUser);
//	}
//	
//	@Test
//	@DisplayName("Find all users")
//	void test_findAllUsers_callsFindAllUsersFromUserRepo() {
//		userService.findAllUsers();
//		verify(mockUserRepo).findAll();
//	}
//	
//	@Test 
//	@DisplayName("Find user by ID")
//	void test_findById_callsFindByIdFromUserRepo() {
//		userService.findUserById(1l);
//		verify(mockUserRepo, times(1)).findById(1l);
//	}
//	
//	@Test 
//	@DisplayName("Find user by username and password")
//	void test_findByUsernameAndPassword_callsFindByUsernameAndPasswordFromUserRepo() {
//		userService.findUserByUsernameAndPassword("login", "password");
//		verify(mockUserRepo, times(1)).findByUsernameAndPassword("login", "password");
//	}
//	
//	@Test
//	@DisplayName("Add fundraising to user")
//	void test_addFundraisingToUser_setsFounderAndAddsFundraisingToUser() {
//		when(mockUser.getFundraisings()).thenReturn(mockFundraisingList);
//		userService.addFundraisingToUser(mockUser, mockFundraising);
//		
//		verify(mockFundraising).setFounder(mockUser);
//		verify(mockUser.getFundraisings(),times(1)).add(mockFundraising);
//		verify(mockUserRepo, times(1)).save(mockUser);
//	}
//	
//	@Test
//	@DisplayName("Add donation to user")
//	void test_addDonationToUser_setsDonorAndAddsDonationToUser() {
//		when(mockUser.getDonations()).thenReturn(mockDonationsList);
//		userService.addDonationToUser(mockUser, mockDonation);
//		
//		verify(mockDonation).setDonor(mockUser);
//		verify(mockUser.getDonations(),times(1)).add(mockDonation);
//		verify(mockUserRepo, times(1)).save(mockUser);
//	}
//	
//
//}
