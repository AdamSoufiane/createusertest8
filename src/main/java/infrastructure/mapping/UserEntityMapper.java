package infrastructure.mapping;

import domain.entities.User;
import application.exceptions.InvalidUserException;
import infrastructure.rest.UserResponse;
import lombok.NonNull;

public class UserEntityMapper {

    public User toUserEntity(@NonNull UserRequest userRequest) throws InvalidUserException {
        return new User(
                userRequest.getName(),
                userRequest.getEmail(),
                userRequest.getPassword()
        );
    }

    public UserResponse toUserResponse(@NonNull User user) {
        return new UserResponse(
                200,
                "User data retrieved successfully",
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }
}
