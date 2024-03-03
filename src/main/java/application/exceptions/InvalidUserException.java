package application.exceptions;

public class InvalidUserException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidUserException() {
        super();
    }

    public InvalidUserException(String message) {
        super(message);
    }

    public InvalidUserException(String message, Throwable cause) {
        super(message, cause);
    }
}