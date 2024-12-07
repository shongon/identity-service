package identity_service.repository;

import identity_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByUsername(String username);

    User findByUsername(String username);

    void deleteByUsername(String username);

}
