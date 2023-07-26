package myproject.CharityGivingWebApp.exceptions;

public class FundraisingNotFoundExceptionResponse {

	private String message;

	public FundraisingNotFoundExceptionResponse(String message) {
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
