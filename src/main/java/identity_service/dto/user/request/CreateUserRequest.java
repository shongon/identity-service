package identity_service.dto.user.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import identity_service.utils.CustomAnnotation.MininumAge.MinimumAge;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateUserRequest {

    @NotNull
    @Size(min = 3, message = "INVALID_USERNAME")
    String username;

    @NotNull
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&#^])[a-zA-Z\\d@$!_%*?&#^\\s]{8,}$", message = "INVALID_PASSWORD")
    String password;

    @NotNull
    String firstName;

    @NotNull
    String lastName;

    @NotNull
    @MinimumAge(value = 18, message = "INVALID_AGE")
    LocalDate dob;

    @JsonFormat(pattern = "HH:mm:ss'_'dd/MM/yyyy")
    LocalDateTime createdAt;
}
