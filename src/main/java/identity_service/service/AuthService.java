package identity_service.service;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import identity_service.dto.auth.request.AuthenticationRequest;
import identity_service.dto.auth.request.IntrospectRequest;
import identity_service.dto.auth.response.AuthenticationResponse;
import identity_service.dto.auth.response.IntrospectResponse;
import identity_service.exception.ApplicationException;
import identity_service.exception.handler.ErrorCode;
import identity_service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthService {
    UserRepository userRepository;

    @NonFinal
    @Value("${jwt.secretKey}")
    protected String SECRET_KEY;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_FOUND));

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean authenticated =  passwordEncoder.matches(request.getPassword(), user.getPassword());

        if(!authenticated)
            throw new ApplicationException(ErrorCode.UNAUTHENTICATED);

        var token = generateToken(request.getUsername());

        return AuthenticationResponse.builder()
                .token(token)
                .loginTime(LocalDateTime.now())
                .build();
    }

    public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException {
        var token = request.getToken();

        JWSVerifier  verifier = new MACVerifier(SECRET_KEY.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        // Return verify with type boolean
        var verified = signedJWT.verify(verifier);

        if (!verified)
            throw  new ApplicationException(ErrorCode.UNVERIFIED);

        return IntrospectResponse.builder()
                .valid(expiryTime.after(new Date()))
                .validTime(LocalDateTime.now())
                .build();
    }

    // Generate JWT token
    private String generateToken(String username) {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);

        // Create body contain key values
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(username) // represent for user already login
                .issuer("shongon.com") // specific who this issuance was published by, usually is domain name
                .issueTime(new Date()) // Issuance date - Ngày phát hành
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli() // expire after 1 hour
                ))
                .build();

        // Pass body to Payload
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(jwsHeader, payload);

        // Hash (sign) jwsObject
        try {
            jwsObject.sign(new MACSigner(SECRET_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Cannot create token ", e);
            throw new RuntimeException(e);
        }
    }


}
