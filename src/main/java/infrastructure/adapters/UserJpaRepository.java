package infrastructure.adapters;

import domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Long> {
    // Custom methods for User entity can be added here
}
