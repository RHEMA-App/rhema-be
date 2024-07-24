package RhemaApp.Rhema.entity;

import jakarta.persistence.*;
import lombok.Data;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Conti {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "conti_id")
    private Long id;

    private Date date;

    @OneToMany(mappedBy = "conti", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ContiSong> contiSongs = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User created_by;

    private Date created_at;
    private Date updated_at;

    public void addContiSong(ContiSong contiSong) {
        contiSongs.add(contiSong);
        contiSong.setConti(this);
    }
}

