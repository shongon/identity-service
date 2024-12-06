package identity_service.controller;

import identity_service.dto.ApiResponse;
import identity_service.dto.user.request.CreateUserRequest;
import identity_service.dto.user.request.UpdateUserRequest;
import identity_service.model.User;
import identity_service.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/create")
    public ApiResponse<User> createUser(@RequestBody CreateUserRequest request) {
        return ApiResponse.success(userService.createUser(request));
    }

    @GetMapping("/view-all")
    public ApiResponse<List<User>> viewAllUsers() {
        return ApiResponse.success(userService.getAllUsers());
    }

    @GetMapping("/view/{id}")
    public ApiResponse<User> getUserById(@PathVariable String id) {
        return ApiResponse.success(userService.getUserById(id));
    }

    @PutMapping("/update/{id}")
    public ApiResponse<User> updateUser(@PathVariable String id, @RequestBody UpdateUserRequest request) {
        return ApiResponse.success(userService.updateUser(id, request));
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<String> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return ApiResponse.success("User deleted");
    }
}
