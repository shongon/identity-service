package identity_service.dto.user.request;

import identity_service.utils.CustomAnnotation.MininumAge.MinimumAge;
import jakarta.validation.constraints.NotNull;
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

public class UpdateUserRequest {

    @NotNull
    String firstName;

    @NotNull
    String lastName;

    @NotNull
    @MinimumAge(value = 18, message = "INVALID_AGE")
    LocalDate dob;
}
