package myproject.CharityGivingWebApp.repository;

import org.springframework.data.repository.CrudRepository;

import myproject.CharityGivingWebApp.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

	User findByUsernameAndPassword(String username, String password);
	
}
