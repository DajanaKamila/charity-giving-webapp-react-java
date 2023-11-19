package myproject.CharityGivingWebApp.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import myproject.CharityGivingWebApp.dtos.UserDTO;
import myproject.CharityGivingWebApp.dtos.UserMapper;
import myproject.CharityGivingWebApp.exceptions.UserNotFoundException;
import myproject.CharityGivingWebApp.model.User;
import myproject.CharityGivingWebApp.repository.UserRepository;
import myproject.CharityGivingWebApp.security.JwtIssuer;
import myproject.CharityGivingWebApp.security.LoginResponse;
import myproject.CharityGivingWebApp.security.UserPrincipal;

@Service
public class AuthService {

    private final JwtIssuer jwtIsuer;
    private final AuthenticationManager authenticationManager;
    private UserMapper userMapper;
    private UserRepository userRepo;

    public AuthService(JwtIssuer jwtIsuer, AuthenticationManager authenticationManager, UserMapper userMapper,
            UserRepository userRepo) {
        this.jwtIsuer = jwtIsuer;
        this.authenticationManager = authenticationManager;
        this.userMapper = userMapper;
        this.userRepo = userRepo;
    }

    public LoginResponse attemptLogin(String email, String password) { 
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
    
    		SecurityContextHolder.getContext().setAuthentication(authentication);
		var principal = (UserPrincipal) authentication.getPrincipal();

		var roles = principal.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

		User user = userRepo.findByEmail(email);

        if (user != null) {
            UserDTO dto = userMapper.mapToDto(user);
			var token = jwtIsuer.issue(principal.getUserId(), principal.getUsername(), roles);
			return new LoginResponse(token, dto);
        }

        throw new UserNotFoundException("User not found");
     
    }

}
