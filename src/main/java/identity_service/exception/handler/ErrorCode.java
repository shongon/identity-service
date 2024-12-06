package identity_service.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ErrorCode {
    //User
    USER_NOT_FOUND(400,"User not found!")

    ;

    private int code;
    private String message;
}
