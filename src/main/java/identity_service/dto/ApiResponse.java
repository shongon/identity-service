package identity_service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;


@Getter
@Builder
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) // Ignore null fields when return response
public class ApiResponse <T> {
    private final int code;
    private final String message;
    private final T result;

    public static <T> ApiResponse<T> success(T result) {
        return new ApiResponse<>(200, "Success", result);
    }

    public static <T> ApiResponse<T> success(String message, T result) {
        return new ApiResponse<>(200, message, result);
    }

    public static <T> ApiResponse<T> error(int code, String message) {
        return new ApiResponse<>(code, message, null);
    }

}
