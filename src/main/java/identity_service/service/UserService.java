package identity_service.service;

import identity_service.dto.user.request.CreateUserRequest;
import identity_service.dto.user.request.UpdateUserRequest;
import identity_service.exception.handler.ErrorCode;
import identity_service.exception.user.ExistedUsernameException;
import identity_service.exception.user.UserNotFoundException;
import identity_service.model.User;
import identity_service.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User createUser (CreateUserRequest request){
        User user = new User();

        if (userRepository.existsByUsername(request.getUsername()))
            throw new ExistedUsernameException(ErrorCode.EXISTED_USERNAME);

        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDob(request.getDob());

        return userRepository.save(user);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserByUsername(String username){
        if (!userRepository.existsByUsername(username))
            throw new UserNotFoundException(ErrorCode.USER_NOT_FOUND);

        return userRepository.findByUsername(username);
    }

    public User updateUser(String username, UpdateUserRequest request){
        User user = getUserByUsername(username);

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDob(request.getDob());

        return userRepository.save(user);
    }

    public void deleteUser(String username){
        if (!userRepository.existsByUsername(username))
            throw new UserNotFoundException(ErrorCode.USER_NOT_FOUND);
        userRepository.deleteByUsername(username);
    }
}
