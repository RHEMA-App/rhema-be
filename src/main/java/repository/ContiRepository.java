package repository;

import entity.Conti;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContiRepository extends JpaRepository<Conti, Long> {
}
