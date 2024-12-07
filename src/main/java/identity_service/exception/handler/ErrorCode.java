package identity_service.exception.handler;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    INVALID_KEY (400, "Invalid message key"),
    //User
    USER_NOT_FOUND(400,"User not found!"),
    EXISTED_USERNAME(400,"Username already exists!"),
    INVALID_USERNAME(400,"Username must be at least 3 characters."),
    INVALID_PASSWORD(400,"Password must be at least 8 characters and contain at least one lowercase letter, one uppercase letter, one digit, and one special character."),
    INVALID_AGE(400,"User must be at least 18 years old."),
    ;

    private final int code;
    private final String message;
}
