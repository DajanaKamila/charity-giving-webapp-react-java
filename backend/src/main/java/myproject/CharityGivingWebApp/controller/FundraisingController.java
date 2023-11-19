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

import jakarta.validation.Valid;
import myproject.CharityGivingWebApp.exceptions.FundraisingNotFoundException;
import myproject.CharityGivingWebApp.model.Fundraising;
import myproject.CharityGivingWebApp.model.User;
import myproject.CharityGivingWebApp.service.FundraisingService;
import myproject.CharityGivingWebApp.service.UserService;
import myproject.CharityGivingWebApp.views.Views;

@RestController
@RequestMapping("api/v1/fundraisings")
public class FundraisingController {

	private FundraisingService fundraisingService;
	private UserService userService; 

	public FundraisingController(FundraisingService fundraisingService, UserService userService) {
		super();
		this.fundraisingService = fundraisingService;
		this.userService = userService; 
	}

	@PostMapping("/")
//	@JsonView(Views.FundraisingView.class)
	public ResponseEntity<?> saveFundraising(@Valid @RequestBody Fundraising fundraising, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			Map<String, String> errors = new HashMap<>();
			for (FieldError error : bindingResult.getFieldErrors()) {
				errors.put(error.getField(), error.getDefaultMessage());
			}
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
		
		User founder = userService.findUserById(fundraising.getFounder().getId());
		
		if (founder == null) {
			return ResponseEntity.badRequest().body("Error: Founder does not exist!");
		}
		
		fundraising.setFounder(founder);
		
		Fundraising savedFundraising = fundraisingService.saveFundraising(fundraising);
		return new ResponseEntity<>(savedFundraising, HttpStatus.CREATED);
	}

	@GetMapping("/")
	@JsonView(Views.FundraisingView.class)
	public ResponseEntity<?> getAllFundraisings() {
		Iterable<Fundraising> fundraisingsDB = this.fundraisingService.findAllFundraisings();
		if (fundraisingsDB == null) {
			throw new FundraisingNotFoundException("There are no saved fundraisings");
		}
		return new ResponseEntity<>(fundraisingsDB, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	@JsonView(Views.FundraisingView.class)
	public ResponseEntity<?> findFundraisingById(@PathVariable Long id) {
		Fundraising fundraisingDB = this.fundraisingService.findFundraisingById(id);
		if (fundraisingDB == null) {
			throw new FundraisingNotFoundException("The fundraising with the given ID does not exist");
		}
		return new ResponseEntity<>(fundraisingDB, HttpStatus.OK);
	}

//	@GetMapping("{id}/donations")
//	public ResponseEntity<?> getDonationsByFundraising(@PathVariable Long id) {
//		Iterable<Donation> donationsDB = this.fundraisingService.findDonationsOfFundraising(id);
//		if (donationsDB == null) {
//			throw new DonationNotFoundException("There are no saved donations for this fundraising");
//		}
//		return new ResponseEntity<>(donationsDB, HttpStatus.OK);
//	}



}
