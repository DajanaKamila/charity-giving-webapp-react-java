package myproject.CharityGivingWebApp.service;

import org.springframework.stereotype.Service;

import myproject.CharityGivingWebApp.model.Role;
import myproject.CharityGivingWebApp.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

	private RoleRepository roleRepo;
	
	public RoleServiceImpl(RoleRepository roleRepo) {
		super();
		this.roleRepo = roleRepo;
	}

	@Override
	public Role findRoleById(Long id) {
		return this.roleRepo.findById(id).orElse(null);
	}

	@Override
	public Role findRoleByName(String name) {
		return this.roleRepo.findByName(name).orElse(null);
	}

}
