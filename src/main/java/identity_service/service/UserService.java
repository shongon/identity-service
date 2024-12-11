package identity_service.service;

import identity_service.dto.user.request.CreateUserRequest;
import identity_service.dto.user.request.UpdateUserRequest;
import identity_service.dto.user.response.CreateUserResponse;
import identity_service.dto.user.response.UpdateUserResponse;
import identity_service.dto.user.response.ViewUserResponse;
import identity_service.exception.handler.ErrorCode;
import identity_service.exception.ApplicationException;
import identity_service.mapper.UserMapper;
import identity_service.model.User;
import identity_service.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;

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
        return userMapper.toViewUserResponse(userRepository.findByUsername(username)
                .orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_FOUND)));
    }

    @Transactional
    public CreateUserResponse createUser (CreateUserRequest request){
        if (userRepository.existsByUsername(request.getUsername()))
            throw new ApplicationException(ErrorCode.EXISTED_USERNAME);

        User user = userMapper.toCreateUser(request);

        return userMapper.toCreateUserResponse(userRepository.save(user));
    }

    @Transactional
    public UpdateUserResponse updateUser(String username, UpdateUserRequest request){
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_FOUND));

        userMapper.updateUser(user, request);

        return userMapper.toUpdateUserResponse(userRepository.save(user));
    }


}
