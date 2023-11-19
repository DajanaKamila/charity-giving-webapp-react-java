package myproject.CharityGivingWebApp.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import myproject.CharityGivingWebApp.model.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {

	Optional<Role> findByName(String name);
	
}
