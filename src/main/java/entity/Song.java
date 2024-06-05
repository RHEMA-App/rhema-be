package entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

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

    @ManyToOne
    //@JoinColumn(name = "key_id")
    //private Key key;

    private Date created_at;
    private Date updated_at;

    @Enumerated(EnumType.STRING)
    private Section section;


}

