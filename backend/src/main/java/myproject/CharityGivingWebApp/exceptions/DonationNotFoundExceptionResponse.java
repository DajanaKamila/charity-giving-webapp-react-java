package myproject.CharityGivingWebApp.exceptions;

public class DonationNotFoundExceptionResponse {

	private String message;

	public DonationNotFoundExceptionResponse(String message) {
		super();
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}	
	
}
