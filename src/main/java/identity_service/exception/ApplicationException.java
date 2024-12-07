package identity_service.exception;

import identity_service.exception.handler.BaseCustomException;
import identity_service.exception.handler.ErrorCode;
import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException implements BaseCustomException {
    private final ErrorCode errorCode;

    public ApplicationException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    @Override
    public ErrorCode getErrorCode() { return errorCode; }
}
