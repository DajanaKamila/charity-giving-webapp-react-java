package myproject.CharityGivingWebApp.exceptions;

public class UserNotFoundExceptionResponse {

	private String message;

	public UserNotFoundExceptionResponse(String message) {
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
