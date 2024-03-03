package infrastructure.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * This data transfer object class encapsulates the response data sent to clients upon successful user registration,
 * including the status code and user details.
 */
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class UserResponse {

    /**
     * The HTTP status code returned to the client.
     */
    @JsonProperty
    private int statusCode;

    /**
     * A message describing the result of the operation.
     */
    @JsonProperty
    private String message;

    /**
     * The unique identifier of the user after successful registration.
     */
    @JsonProperty
    private Long userId;

    /**
     * The name of the user after successful registration.
     */
    @JsonProperty
    private String userName;

    /**
     * The email of the user after successful registration.
     */
    @JsonProperty
    private String userEmail;

    /**
     * Constructor for creating a UserResponse object with status code, message, and user details.
     *
     * @param statusCode the HTTP status code
     * @param message    the result message
     * @param userId     the unique identifier of the user
     * @param userName   the name of the user
     * @param userEmail  the email of the user
     */
    public UserResponse(int statusCode, String message, Long userId, String userName, String userEmail) {
        this.statusCode = statusCode;
        this.message = message;
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
    }

    // Custom serialization logic can be implemented here if needed
}
