package identity_service.controller;

import identity_service.dto.ApiResponse;
import identity_service.dto.auth.request.AuthenticationRequest;
import identity_service.dto.auth.response.AuthenticationResponse;
import identity_service.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ApiResponse<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest) {
        boolean result = authService.authenticate(authenticationRequest);
        return result
                ? ApiResponse.success("Login successfully!",
                AuthenticationResponse.builder()
                        .loginTime(LocalDateTime.now())
                        .build())
                : ApiResponse.error(400, "Username or password is incorrect.");

    }
}
