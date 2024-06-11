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
    private Long id;

    private String link;
    private String name;

    @ManyToOne
    @JoinColumn(name = "created_by_id")
    private User created_by;

    @ManyToOne
    @JoinColumn(name = "conti_id")
    private Conti conti;

    private Date created_at;
    private Date updated_at;

    private Section[] section; /*없으면 null이 아닌 비어있는 Array로*/
}

