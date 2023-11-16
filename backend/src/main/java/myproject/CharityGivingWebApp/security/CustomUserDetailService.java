package myproject.CharityGivingWebApp.security;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import myproject.CharityGivingWebApp.model.User;
import myproject.CharityGivingWebApp.service.UserService;

@Component
public class CustomUserDetailService implements UserDetailsService {

	private final UserService userService; 

	public CustomUserDetailService(UserService userService) {
		super();
		this.userService = userService;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.findUserByEmail(username);
		
	    List<SimpleGrantedAuthority> authorities = user.getRoles().stream()
	            .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName().name()))
	            .collect(Collectors.toList());
		
		return UserPrincipal.builder()
					.userId(user.getId())
					.email(user.getEmail())
					.authorities(authorities)
					.password(user.getPassword())
					.build();
	}
	
}
