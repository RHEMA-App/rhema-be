package RhemaApp.Rhema.repository;

import RhemaApp.Rhema.entity.Conti;
import RhemaApp.Rhema.entity.ContiSong;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContiSongRepository extends JpaRepository <ContiSong, Long> {
    void deleteByContiId(Long contiId);
}
