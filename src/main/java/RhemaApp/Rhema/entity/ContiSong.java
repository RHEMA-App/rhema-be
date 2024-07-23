package RhemaApp.Rhema.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
public class ContiSong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contiSong_id")
    private Long id;

    @Getter
    @ManyToOne
    @JoinColumn(name = "conti_id")
    private Conti conti;

    @Getter
    @ManyToOne
    @JoinColumn(name = "song_id")
    private Song song;

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    public void setConti(Conti conti) {
        this.conti = conti;
    }

    public void setSong(Song song) {
        this.song = song;
    }
}
