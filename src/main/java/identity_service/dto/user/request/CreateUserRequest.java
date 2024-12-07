package identity_service.dto.user.request;

import identity_service.utils.CustomAnnotation.MininumAge.MinimumAge;
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

    @Size(min = 3, message = "Username must be at least 3 characters")
    String username;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&#^])[a-zA-Z\\d@$!%*?&#^\\s]{8,}$",
            message = "Password must be at least 8 characters and contain at least one lowercase letter, one uppercase letter, one digit, and one special character.")
    String password;

    String firstName;

    String lastName;

    @MinimumAge(value = 18, message = "User must be at least 18 years old")
    LocalDate dob;
}
