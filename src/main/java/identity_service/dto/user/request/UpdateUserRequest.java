package identity_service.dto.user.request;


import identity_service.model.User;
import identity_service.utils.CustomAnnotation.MininumAge.MinimumAge;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
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

    User.Role roles;

    LocalDateTime updatedAt;
}
