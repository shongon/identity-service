package identity_service.controller;

import identity_service.dto.ApiResponse;
import identity_service.dto.user.request.CreateUserRequest;
import identity_service.model.User;
import identity_service.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/users/create")
    public ApiResponse<User> createUser(@RequestBody CreateUserRequest request) {
        return ApiResponse.success(userService.createUser(request));
    }
}
