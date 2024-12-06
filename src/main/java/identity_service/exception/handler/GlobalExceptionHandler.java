package identity_service.exception.handler;

import identity_service.dto.ApiResponse;
import identity_service.exception.user.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({
            // Common exceptions
            IllegalArgumentException.class,
            RuntimeException.class,
            //User
            UserNotFoundException.class
    })
    ResponseEntity<ApiResponse<?>> handleCustomExceptions(RuntimeException ex) {
        if (ex instanceof BaseCustomException customEx) {
            ErrorCode errorCode = customEx.getErrorCode();
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error(errorCode.getCode(), errorCode.getMessage()));
        }

        // Handle unexpected exceptions gracefully
        return ResponseEntity.status(500)
                .body(ApiResponse.error(500, "Internal Server Error: " + ex.getMessage()));
    }
}
