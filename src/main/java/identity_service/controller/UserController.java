package identity_service.controller;

import identity_service.dto.ApiResponse;
import identity_service.dto.user.request.CreateUserRequest;
import identity_service.dto.user.request.UpdateUserRequest;
import identity_service.dto.user.response.CreateUserResponse;
import identity_service.dto.user.response.UpdateUserResponse;
import identity_service.dto.user.response.ViewUserResponse;
import identity_service.model.User;
import identity_service.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/myInfo")
    public ApiResponse<ViewUserResponse> getMyInfo(){
        return ApiResponse.success(userService.getMyInfo());
    }

    // Admin API controller
    @GetMapping("/view-all")
    public ApiResponse<List<User>> viewAllUsers() {
        return ApiResponse.success(userService.getAllUsers());
    }

    @DeleteMapping("/delete/{username}")
    public ApiResponse<String> deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
        return ApiResponse.success("User deleted!",null);
    }

    // User API controller
    @PostMapping("/register")
    public ApiResponse<CreateUserResponse> createUser(@RequestBody @Valid CreateUserRequest request) {
        return ApiResponse.success("User created!",userService.createUser(request));
    }

    @GetMapping("/view/{username}")
    public ApiResponse<ViewUserResponse> getUserByUsername(@PathVariable String username) {
        return ApiResponse.success(userService.getUserByUsername(username));
    }

    @PutMapping("/update/{username}")
    public ApiResponse<UpdateUserResponse> updateUser(@PathVariable String username, @RequestBody @Valid UpdateUserRequest request) {
        return ApiResponse.success("User updated!", userService.updateUser(username, request));
    }
}
