package myproject.CharityGivingWebApp.security;

import myproject.CharityGivingWebApp.dtos.UserDTO;

public class LoginResponse {

    private final String accessToken;
    private final UserDTO userDTO;

    public LoginResponse(String accessToken, UserDTO userDTO) {
        this.accessToken = accessToken;
        this.userDTO = userDTO;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public static LoginResponseBuilder builder() {
        return new LoginResponseBuilder();
    }

    public static class LoginResponseBuilder {
        private String accessToken;
        private UserDTO userDTO;

        public LoginResponseBuilder accessToken(String accessToken) {
            this.accessToken = accessToken;
            return this;
        }

        public LoginResponseBuilder userDTO(UserDTO userDTO) {
            this.userDTO = userDTO;
            return this;
        }

        public LoginResponse build() {
            return new LoginResponse(accessToken, userDTO);
        }
    }

}
