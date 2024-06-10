package RhemaApp.Rhema.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @OneToMany(mappedBy = "created_by", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Conti> conti;

    @OneToMany(mappedBy = "created_by", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Song> songs;
}
