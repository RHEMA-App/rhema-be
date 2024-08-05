package RhemaApp.Rhema.repository;

import RhemaApp.Rhema.entity.Conti;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContiRepository extends JpaRepository<Conti, Long> {
    List<Conti> findAll(Sort sort);
}
