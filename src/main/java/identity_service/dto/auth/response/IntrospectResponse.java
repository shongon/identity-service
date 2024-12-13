package identity_service.dto.auth.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IntrospectResponse {
    boolean valid;

    @JsonFormat(pattern = "HH:mm:ss' | 'dd-MM-yyyy")
    LocalDateTime validTime;
}
