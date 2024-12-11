package identity_service.mapper;

import identity_service.dto.user.request.CreateUserRequest;
import identity_service.dto.user.request.UpdateUserRequest;
import identity_service.dto.user.response.CreateUserResponse;
import identity_service.dto.user.response.UpdateUserResponse;
import identity_service.dto.user.response.ViewUserResponse;
import identity_service.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;


// Sử dụng mapper để map các dữ liệu từ dto đến model
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING) // định nghĩa mapper sử dụng trong Spring
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "updatedAt", ignore = true)
    User toCreateUser(CreateUserRequest request); // Nhận vào 1 kiểu CreateUserRequest (dto) trả về kiểu User (model)

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
    void updateUser(@MappingTarget User user, UpdateUserRequest request); // map data từ UpdateUserRequest đến User


    ViewUserResponse toViewUserResponse(User user); //Nhận vào 1 kiểu User (model) trả về kiểu ViewUserResponse (dto)

    UpdateUserResponse toUpdateUserResponse(User user);

    CreateUserResponse toCreateUserResponse(User user);
}