package identity_service.dto.user.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateUserResponse {
    @JsonFormat(pattern = "HH:mm:ss' | 'dd-MM-yyyy")
    LocalDateTime createdAt;
}
