package myproject.CharityGivingWebApp.exceptions;

public class RoleNotFoundExceptionResponse {
	
	private String message;

	public RoleNotFoundExceptionResponse(String message) {
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
