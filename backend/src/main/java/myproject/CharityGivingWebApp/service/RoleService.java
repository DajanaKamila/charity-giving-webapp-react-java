package myproject.CharityGivingWebApp.service;

import myproject.CharityGivingWebApp.model.Role;

public interface RoleService {
	
	Role findRoleById(Long id);
	Role findRoleByName(String name);

}
