package RhemaApp.Rhema.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "section_id")
    private Long id;

    private String key;

    @ManyToOne
    @JoinColumn(name = "song_id")
    private Song song;

}