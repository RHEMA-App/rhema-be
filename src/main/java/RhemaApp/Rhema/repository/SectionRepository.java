package RhemaApp.Rhema.repository;

import RhemaApp.Rhema.entity.Section;
import RhemaApp.Rhema.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectionRepository extends JpaRepository <Section, Long> {
    void deleteBySong(Song song);
}
