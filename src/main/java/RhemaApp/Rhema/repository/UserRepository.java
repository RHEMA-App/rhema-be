package RhemaApp.Rhema.repository;

import RhemaApp.Rhema.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
