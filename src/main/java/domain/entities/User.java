package domain.entities;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String email;

    private String password;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Version
    private Long version;

    private LocalDateTime lastLogin;

    private Set<String> roles = new HashSet<>();

    private boolean isActive;

    private static final Clock CLOCK = Clock.systemDefaultZone();

    public User(String name, String email, String password) {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidUserException("Name cannot be null or empty");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new InvalidUserException("Email cannot be null or empty");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new InvalidUserException("Password cannot be null or empty");
        }
        this.name = name;
        this.email = email;
        this.password = password; // Removed encryption for dependency compliance
        this.createdAt = LocalDateTime.now(CLOCK);
        this.updatedAt = LocalDateTime.now(CLOCK);
        this.isActive = true;
        validate();
    }

    public void login() {
        this.lastLogin = LocalDateTime.now(CLOCK);
    }

    public Set<String> getRoles() {
        return Collections.unmodifiableSet(roles);
    }

    public void validate() {
        if (!email.contains("@")) {
            throw new InvalidUserException("Email must contain '@'");
        }
        if (password.length() < 8) {
            throw new InvalidUserException("Password must be at least 8 characters long");
        }
        // Additional validation rules can be implemented here
    }

    // Custom exception class
    public static class InvalidUserException extends RuntimeException {
        public InvalidUserException(String message) {
            super(message);
        }
    }
}
