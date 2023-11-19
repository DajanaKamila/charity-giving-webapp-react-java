//package myproject.CharityGivingWebApp.repository;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertNull;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import myproject.CharityGivingWebApp.model.User;
//
//@DataJpaTest
//class TestUserRepository {
//	
//	@Autowired
//	UserRepository userRepo;
//	
//	private User user;
//
//	@Test
//	@DisplayName("Find user by username and password")
//	void test_findByUsernameAndPassword_returnUserWithGivenUsernameAndPassword() {
//		user = new User();
//		user.setUsername("username");
//		user.setPassword("password");
//		user.setFirstName("John");
//		user.setLastName("Doe");
//		
//		userRepo.save(user);
//		User actual = userRepo.findByUsernameAndPassword("username", "password");
//		
//		assertThat(actual.getUsername()).isEqualTo(user.getUsername());
//		assertThat(actual.getPassword()).isEqualTo(user.getPassword());
//		assertThat(actual.getFirstName()).isEqualTo(user.getFirstName());
//		assertThat(actual.getLastName()).isEqualTo(user.getLastName());
//	}
//	
//	@Test
//	@DisplayName("Find user by username and password")
//	void test_findByUsernameAndPassword_returnNullWhenUserNotExist() {
//		User user = userRepo.findByUsernameAndPassword("username", "password");
//		assertNull(user);
//	}
//	
//
//}
