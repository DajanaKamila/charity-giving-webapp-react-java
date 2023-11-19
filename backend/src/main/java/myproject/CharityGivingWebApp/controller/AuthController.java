package myproject.CharityGivingWebApp.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import myproject.CharityGivingWebApp.security.LoginRequest;
import myproject.CharityGivingWebApp.security.LoginResponse;
import myproject.CharityGivingWebApp.service.AuthService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        super();
        this.authService = authService;
    }

    /**
     * Authenticates a user based on their credentials and returns a login response.
     * 
     * @param request - The LoginRequest object containing the user's email and
     *                password.
     * @return - A LoginResponse object containing an access token and a user DTO.
     */
    @Operation(summary = "Log in a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User successfully logged in"),
            @ApiResponse(responseCode = "404", description = "User not found or authentication failed")
    })

    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Validated LoginRequest request) {
        return authService.attemptLogin(request.getEmail(), request.getPassword());
    }

}
