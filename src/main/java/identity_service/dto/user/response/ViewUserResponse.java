package identity_service.dto.user.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    String password;

    String firstName;

    String lastName;

    LocalDate dob;

    @JsonFormat(pattern = "HH:mm:ss' | 'dd-MM-yyyy")
    LocalDateTime createdAt;

    @JsonFormat(pattern = "HH:mm:ss' | 'dd-MM-yyyy")
    LocalDateTime updatedAt;
}
