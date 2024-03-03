package application.exceptions;

public class UserRequestValidationException extends RuntimeException {

    public UserRequestValidationException(String message) {
        super(message);
    }

    public UserRequestValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}