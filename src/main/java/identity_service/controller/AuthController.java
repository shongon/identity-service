package identity_service.controller;

import com.nimbusds.jose.JOSEException;
import identity_service.dto.ApiResponse;
import identity_service.dto.auth.request.AuthenticationRequest;
import identity_service.dto.auth.request.IntrospectRequest;
import identity_service.dto.auth.response.AuthenticationResponse;
import identity_service.dto.auth.response.IntrospectResponse;
import identity_service.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;


@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ApiResponse<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest) {
        var result = authService.authenticate(authenticationRequest);
        return ApiResponse.success("Login successfully!",result);
    }

    // Valid token return
    @PostMapping("/introspect")
    public ApiResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest introspectRequest) throws ParseException, JOSEException {
        var validation = authService.introspect(introspectRequest);
        return ApiResponse.success("Introspect successfully!",validation);
    }
}
