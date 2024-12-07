package identity_service.exception.user;

import identity_service.exception.handler.BaseCustomException;
import identity_service.exception.handler.ErrorCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExistedUsernameException extends RuntimeException implements BaseCustomException {
    private ErrorCode errorCode;
    public ExistedUsernameException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    @Override
    public ErrorCode getErrorCode() { return errorCode; }
}
