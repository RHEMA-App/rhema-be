package RhemaApp.Rhema.entity;

import jakarta.persistence.*;
import lombok.Data;


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

    @OneToMany
    private List<Song> songs;

    @ManyToOne
    @JoinColumn(name = "created_by_id")
    private User created_by;

    private Date created_at;
    private Date updated_at;
}

