package identity_service.exception.handler;

import identity_service.dto.ApiResponse;
import identity_service.exception.ApplicationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    // Handler custom exceptions
    @ExceptionHandler({
            ApplicationException.class
    })
    ResponseEntity<ApiResponse<?>> handleCustomExceptions(RuntimeException ex) {
        if (ex instanceof BaseCustomException customEx) {
            ErrorCode errorCode = customEx.getErrorCode();
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error(errorCode.getCode(), errorCode.getMessage()));
        }

        // Handle unexpected exceptions gracefully
        return ResponseEntity.internalServerError()
                .body(ApiResponse.error(500, "Internal Server Error: " + ex.getMessage()));
    }

    // Handler validation exception
    @ExceptionHandler (value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> handlingValidation(MethodArgumentNotValidException ex) {
        String enumKey = ex.getBindingResult().getFieldError().getDefaultMessage();

        ErrorCode errorCode = ErrorCode.INVALID_KEY;

        try {
            errorCode = ErrorCode.valueOf(enumKey);
        } catch (IllegalArgumentException ignored) {}

        return ResponseEntity.badRequest().body(ApiResponse.error(errorCode.getCode(), errorCode.getMessage()));
    }
}
