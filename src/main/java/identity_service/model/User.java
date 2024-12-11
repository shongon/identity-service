package identity_service.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    String id;

    @Column(name = "username", unique = true, nullable = false)
    String username;

    @Column(name = "password", nullable = false)
    String password;

    @Column(name = "first_name", nullable = false)
    String firstName;

    @Column(name = "last_name", nullable = false)
    String lastName;

    @Column(name = "dob", nullable = false)
    LocalDate dob;

    // Thời gian tạo người dùng
    @CreatedDate
    @JsonFormat(pattern = "HH:mm:ss' | 'dd-MM-yyyy")
    @Column(name = "created_at", nullable = false, updatable = false)
    LocalDateTime createdAt;

    // Thời gian cập nhật cuối cùng
    @LastModifiedDate
    @JsonFormat(pattern = "HH:mm:ss' | 'dd-MM-yyyy")
    @Column(name = "updated_at")
    LocalDateTime updatedAt;
}
