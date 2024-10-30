package kata.preproj.repository;

import kata.preproj.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// CRUD func with fancy annotation provided by Spring
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
