package Knowledge.WebApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import Knowledge.WebApp.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
       Optional <User> findByLogin(String login);
    }

