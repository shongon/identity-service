package identity_service.controller;

import identity_service.dto.ApiResponse;
import identity_service.dto.user.request.CreateUserRequest;
import identity_service.dto.user.request.UpdateUserRequest;
import identity_service.model.User;
import identity_service.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/create")
    public ApiResponse<User> createUser(@RequestBody @Valid CreateUserRequest request) {
        return ApiResponse.success(userService.createUser(request));
    }

    @GetMapping("/view-all")
    public ApiResponse<List<User>> viewAllUsers() {
        return ApiResponse.success(userService.getAllUsers());
    }

    @GetMapping("/view/{username}")
    public ApiResponse<User> getUserByUsername(@PathVariable String username) {
        return ApiResponse.success(userService.getUserByUsername(username));
    }

    @PutMapping("/update/{username}")
    public ApiResponse<User> updateUser(@PathVariable String username, @RequestBody UpdateUserRequest request) {
        return ApiResponse.success(userService.updateUser(username, request));
    }

    @DeleteMapping("/delete/{username}")
    public ApiResponse<String> deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
        return ApiResponse.success("User deleted");
    }
}
