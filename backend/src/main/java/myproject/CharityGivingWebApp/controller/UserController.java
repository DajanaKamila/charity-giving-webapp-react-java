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

import myproject.CharityGivingWebApp.exceptions.UserNotFoundException;
import myproject.CharityGivingWebApp.model.Donation;
import myproject.CharityGivingWebApp.model.Fundraising;
import myproject.CharityGivingWebApp.model.User;
import myproject.CharityGivingWebApp.service.UserService;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

	private UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@PostMapping("")
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

	@GetMapping("")
	public ResponseEntity<?> getAllUsers() {
		Iterable<User> usersDB = this.userService.findAllUsers();
		if (usersDB == null) {
			throw new UserNotFoundException("There are no saved users");
		}
		return new ResponseEntity<>(usersDB, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findUserById(@PathVariable Long id) {
		User userDB = this.userService.findUserById(id);
		if (userDB == null) {
			throw new UserNotFoundException("The user with given ID does not exist");
		}
		return new ResponseEntity<>(userDB, HttpStatus.OK);
	}

	@PostMapping("/{id}/fundraisings")
	public ResponseEntity<?> addFundraisingToUser(@PathVariable Long id, @RequestBody Fundraising fundraising) {
		User user = userService.findUserById(id);
		if (user == null) {
			throw new UserNotFoundException("The user with given ID does not exist");
		}
		userService.addFundraisingToUser(user, fundraising);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("/{id}/donations")
	public ResponseEntity<?> addDonationToUser(@PathVariable Long id, @RequestBody Donation donation) {
		User user = userService.findUserById(id);
		if (user == null) {
			throw new UserNotFoundException("The user with given ID does not exist");
		}
		userService.addDonationToUser(user, donation);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody User user) {
		User foundUser = userService.findUserByUsernameAndPassword(user.getUsername(), user.getPassword());
		if (foundUser == null) {
			throw new UserNotFoundException("Invalid username or password");
		}
		return new ResponseEntity<>(foundUser, HttpStatus.OK);
	}

}
