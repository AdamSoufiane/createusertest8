package infrastructure.rest;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.UUID;
import java.util.regex.Pattern;

@Getter
@Setter
@Component
public class UserRequest {

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
        Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private static final Pattern VALID_PASSWORD_REGEX =
        Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{12,}$"); // Updated to require at least 12 characters

    private UUID userId;
    private String name;
    private String email;
    private String password;

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserRequest(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public UserRequest(String name, String email, String password, BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userId = UUID.randomUUID();
        setName(name);
        setEmail(email);
        setPassword(password);
    }

    public void setUserId(UUID userId) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID must not be null");
        }
        this.userId = userId;
    }

    public void setName(String name) {
        validateName(name);
        this.name = name;
    }

    public void setEmail(String email) {
        validateEmail(email);
        this.email = email;
    }

    public void setPassword(String password) {
        validatePassword(password);
        this.password = passwordEncoder.encode(password);
    }

    public void validateUserRequest() {
        validateName(this.name);
        validateEmail(this.email);
        validatePassword(this.password);
        if (this.userId == null) {
            throw new UserRequestValidationException("User ID must not be null");
        }
    }

    private void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new UserRequestValidationException("Name must not be null or empty");
        }
    }

    private void validateEmail(String email) {
        if (email == null || !VALID_EMAIL_ADDRESS_REGEX.matcher(email).matches()) {
            throw new UserRequestValidationException("Email must be a valid format and not null");
        }
    }

    private void validatePassword(String password) {
        if (password == null || !VALID_PASSWORD_REGEX.matcher(password).matches()) {
            throw new UserRequestValidationException("Password must meet the strength requirements and not be null");
        }
    }

    public static class UserRequestValidationException extends RuntimeException {
        private static final long serialVersionUID = 1L;

        public UserRequestValidationException(String message) {
            super(message);
        }
    }

}