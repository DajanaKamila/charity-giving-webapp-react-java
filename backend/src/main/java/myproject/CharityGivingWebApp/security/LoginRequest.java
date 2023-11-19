package myproject.CharityGivingWebApp.security;

public class LoginRequest {

	private String email;
	private String password;
	
    public LoginRequest() {
    }

    public static LoginRequestBuilder builder() {
        return new LoginRequestBuilder();
    }
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public static class LoginRequestBuilder {
        private String username;
        private String password;

        public LoginRequestBuilder username(String username) {
            this.username = username;
            return this;
        }

        public LoginRequestBuilder password(String password) {
            this.password = password;
            return this;
        }

        public LoginRequest build() {
            LoginRequest loginRequest = new LoginRequest();
            loginRequest.email = this.username;
            loginRequest.password = this.password;
            return loginRequest;
        }
    }
	
}
