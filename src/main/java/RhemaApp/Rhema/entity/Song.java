package RhemaApp.Rhema.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="song_id")
    private Long id;

    private String link;
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User created_by;

    @ManyToOne
    @JoinColumn(name = "conti_id")
    private Conti conti;

    private Date created_at;
    private Date updated_at;

    @OneToMany(mappedBy = "song", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Section> sections;
}

