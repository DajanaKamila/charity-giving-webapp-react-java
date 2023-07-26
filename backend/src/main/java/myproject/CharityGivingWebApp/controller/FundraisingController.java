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

import jakarta.validation.Valid;
import myproject.CharityGivingWebApp.exceptions.FundraisingNotFoundException;
import myproject.CharityGivingWebApp.model.Fundraising;
import myproject.CharityGivingWebApp.service.FundraisingService;

@RestController
@RequestMapping("api/v1/fundraisings")
public class FundraisingController {

	private FundraisingService fundraisingService;

	public FundraisingController(FundraisingService fundraisingService) {
		super();
		this.fundraisingService = fundraisingService;
	}
	
	@PostMapping("")
	public ResponseEntity<?> saveFundraising(@RequestBody Fundraising fundraising, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			Map<String, String> errors = new HashMap<>();
			for (FieldError error : bindingResult.getFieldErrors()) {
				errors.put(error.getField(), error.getDefaultMessage());
			}
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(this.fundraisingService.saveFundraising(fundraising), HttpStatus.CREATED);
	}
	
	@GetMapping("")
	public ResponseEntity<?> getAllFundraisings() {
		Iterable<Fundraising> fundraisingsDB = this.fundraisingService.findAllFundraisings();
		if (fundraisingsDB == null) {
			throw new FundraisingNotFoundException("There are no saved fundraisings");
		}
		return new ResponseEntity<>(fundraisingsDB, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findFundraisingById(@PathVariable Long id) {
		Fundraising fundraisingDB = this.fundraisingService.findFundraisingById(id);
		if (fundraisingDB == null) {
			throw new FundraisingNotFoundException("The fundraising with the given ID does not exist");
		}
		return new ResponseEntity<>(fundraisingDB, HttpStatus.OK);
	}
	
}
