package identity_service.dto.user.request;

import identity_service.utils.CustomAnnotation.MininumAge.MinimumAge;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateUserRequest {

    @NotNull
    @Size(min = 3, message = "INVALID_USERNAME")
    String username;

    @NotNull
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&#^])[a-zA-Z\\d@$!%*?&#^\\s]{8,}$", message = "INVALID_PASSWORD")
    String password;

    @NotNull
    String firstName;

    @NotNull
    String lastName;

    @NotNull
    @MinimumAge(value = 18, message = "INVALID_AGE")
    LocalDate dob;
}
