package Knowledge.WebApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import Knowledge.WebApp.model.User;
    public interface UserRepository extends JpaRepository<User, Long> {
        User findByLogin(String login);
    }

