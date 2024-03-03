package application.services;

import application.ports.in.RegisterUserUseCase;
import application.ports.out.UserRepository;
import domain.entities.User;
import domain.exceptions.InvalidUserException;
import infrastructure.rest.UserRequest;
import infrastructure.rest.UserResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RegisterUserService implements RegisterUserUseCase {

    private static final Logger logger = LoggerFactory.getLogger(RegisterUserService.class);
    private final UserRepository userRepository;

    public RegisterUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponse registerUser(UserRequest userRequest) throws InvalidUserException {
        try {
            User user = new User(userRequest.getName(), userRequest.getEmail(), userRequest.getPassword());
            user.validate();
            User savedUser = userRepository.save(user);
            return new UserResponse(savedUser.getId(), savedUser.getName(), savedUser.getEmail(), savedUser.isActive());
        } catch (InvalidUserException e) {
            logger.error("User registration failed: ", e);
            throw e;
        }
    }
}
