package infrastructure.adapters;

import application.ports.out.UserRepository;
import domain.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import infrastructure.adapters.UserJpaRepository;

@Component
public class UserRepositoryAdapter implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    @Autowired
    public UserRepositoryAdapter(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public User save(User user) throws DataAccessException {
        return userJpaRepository.save(user);
    }

    @Override
    public Optional<User> findById(Long id) throws DataAccessException {
        return userJpaRepository.findById(id);
    }

    @Override
    public void delete(User user) throws DataAccessException {
        userJpaRepository.delete(user);
    }

    @Override
    public void deleteById(Long id) throws DataAccessException {
        userJpaRepository.deleteById(id);
    }

    @Override
    public List<User> findAll() {
        return userJpaRepository.findAll();
    }
}
