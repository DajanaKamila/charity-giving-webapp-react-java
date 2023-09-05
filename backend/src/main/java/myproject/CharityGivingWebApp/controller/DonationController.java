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

import myproject.CharityGivingWebApp.exceptions.DonationNotFoundException;
import myproject.CharityGivingWebApp.model.Donation;
import myproject.CharityGivingWebApp.service.DonationService;

@RestController
@RequestMapping("api/v1/donations")
public class DonationController {

	private DonationService donationService;

	public DonationController(DonationService donationService) {
		super();
		this.donationService = donationService;
	}

	@PostMapping("")
	public ResponseEntity<?> saveDonation(@RequestBody Donation donation, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			Map<String, String> errors = new HashMap<>();
			for (FieldError error : bindingResult.getFieldErrors()) {
				errors.put(error.getField(), error.getDefaultMessage());
			}
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(this.donationService.saveDonation(donation), HttpStatus.CREATED);
	}

	@GetMapping("")
	public ResponseEntity<?> getAllDonations() {
		Iterable<Donation> donationsDB = this.donationService.findAllDonations();
		if (donationsDB == null) {
			throw new DonationNotFoundException("There are no saved donations");
		}
		return new ResponseEntity<>(donationsDB, HttpStatus.OK);
	}

	// @GetMapping("")
	// public ResponseEntity<?> getDonationsByFundraising() {
	// Iterable<Donation> donationsDB = this.donationService.
	// }

	@GetMapping("/{id}")
	public ResponseEntity<?> findDonationById(@PathVariable Long id) {
		Donation donationDB = this.donationService.findDonationById(id);
		if (donationDB == null) {
			throw new DonationNotFoundException("The donation with the given ID does not exist");
		}
		return new ResponseEntity<>(donationDB, HttpStatus.OK);
	}

}
