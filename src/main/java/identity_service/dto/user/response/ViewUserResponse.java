package identity_service.dto.user.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import identity_service.model.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ViewUserResponse {
    String username;

    String firstName;

    String lastName;

    LocalDate dob;

    User.Role roles;

    @JsonFormat(pattern = "HH:mm:ss' | 'dd-MM-yyyy")
    LocalDateTime createdAt;

    @JsonFormat(pattern = "HH:mm:ss' | 'dd-MM-yyyy")
    LocalDateTime updatedAt;
}
