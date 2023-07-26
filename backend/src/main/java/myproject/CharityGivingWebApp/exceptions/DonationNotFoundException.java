package myproject.CharityGivingWebApp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DonationNotFoundException extends RuntimeException {
	
	static final long serialVersionUID = 1L;
	
	public DonationNotFoundException(String message) {
		super(message);
	}

}
