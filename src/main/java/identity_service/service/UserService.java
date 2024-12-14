package identity_service.service;

import identity_service.dto.user.request.CreateUserRequest;
import identity_service.dto.user.request.UpdateUserRequest;
import identity_service.dto.user.response.CreateUserResponse;
import identity_service.dto.user.response.UpdateUserResponse;
import identity_service.dto.user.response.ViewUserResponse;
import identity_service.exception.handler.ErrorCode;
import identity_service.exception.ApplicationException;
import identity_service.model.User;
import identity_service.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    // Admin services
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void deleteUser(String username){
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_FOUND));

        userRepository.delete(user);
    }

    // User services
    public ViewUserResponse getUserByUsername(String username){
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_FOUND));

       return ViewUserResponse.builder()
               .username(user.getUsername())
               .firstName(user.getFirstName())
               .lastName(user.getLastName())
               .dob(user.getDob())
               .roles(user.getRoles())
               .createdAt(user.getCreatedAt())
               .updatedAt(user.getUpdatedAt())
               .build();
    }

    @Transactional
    public CreateUserResponse createUser (CreateUserRequest request){
        if (userRepository.existsByUsername(request.getUsername()))
            throw new ApplicationException(ErrorCode.EXISTED_USERNAME);

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDob(request.getDob());
        user.setRoles(User.Role.USER);
        user.setCreatedAt(LocalDateTime.now());

        userRepository.save(user);

        return CreateUserResponse.builder()
                .createdAt(user.getCreatedAt())
                .build();
    }

    @Transactional
    public UpdateUserResponse updateUser(String username, UpdateUserRequest request){
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_FOUND));

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDob(request.getDob());
        user.setRoles(User.Role.valueOf(request.getRoles().toString().toUpperCase()));
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);

        return UpdateUserResponse.builder()
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
