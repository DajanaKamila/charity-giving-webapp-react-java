package myproject.CharityGivingWebApp.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.annotation.Resource;
import myproject.CharityGivingWebApp.exceptions.UserNotFoundException;
import myproject.CharityGivingWebApp.model.User;
import myproject.CharityGivingWebApp.service.FundraisingService;
import myproject.CharityGivingWebApp.service.RoleService;
import myproject.CharityGivingWebApp.service.UserService;
import myproject.CharityGivingWebApp.views.Views;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

	private UserService userService;

	@Resource
	private FundraisingService fundraisingService;
	
	@Resource 
	private RoleService roleService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@PostMapping("/")
	@JsonView(Views.UserBasicView.class)
	public ResponseEntity<?> saveUser(@RequestBody User user, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			Map<String, String> errors = new HashMap<>();
			for (FieldError error : bindingResult.getFieldErrors()) {
				errors.put(error.getField(), error.getDefaultMessage());
			}
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		} 
		
		return new ResponseEntity<>(this.userService.saveUser(user), HttpStatus.CREATED);
	}

	@GetMapping("/")
	@JsonView(Views.UserBasicView.class)
	public ResponseEntity<?> getAllUsers() {
		Iterable<User> usersDB = this.userService.findAllUsers();
		if (usersDB == null) {
			throw new UserNotFoundException("There are no saved users");
		}
		return new ResponseEntity<>(usersDB, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@JsonView(Views.UserBasicView.class)
	public ResponseEntity<?> findUserById(@PathVariable Long id) {
		User userDB = this.userService.findUserById(id);
		if (userDB == null) {
			throw new UserNotFoundException("The user with given ID does not exist");
		}
		return new ResponseEntity<>(userDB, HttpStatus.OK);
	}

//	@PostMapping("/{id}/fundraisings")
//	public ResponseEntity<?> addFundraisingToUser(@PathVariable Long id, @RequestBody Fundraising fundraising) {
//		User user = userService.findUserById(id);
//		if (user == null) {
//			throw new UserNotFoundException("The user with given ID does not exist");
//		}
//		userService.addFundraisingToUser(user, fundraising);
//		return new ResponseEntity<>(HttpStatus.OK);
//	}

//	@PostMapping("/{userId}/{fundraisingId}/donations")
//	public ResponseEntity<?> addDonationToUser(@PathVariable Long userId, @PathVariable Long fundraisingId,
//			@RequestBody Donation donation) {
//		User user = userService.findUserById(userId);
//		if (user == null) {
//			throw new UserNotFoundException("The user with given ID does not exist");
//		}
//
//		Fundraising fundraising = fundraisingService.findFundraisingById(fundraisingId);
//
//		if (fundraising == null) {
//			throw new FundraisingNotFoundException("The fundraising with given ID does not exist");
//		}
//
//		donation.setFundraising(fundraising);
//
//		userService.addDonationToUser(user, donation);
//		return new ResponseEntity<>(HttpStatus.OK);
//	}

}
