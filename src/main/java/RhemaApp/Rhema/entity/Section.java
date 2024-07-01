package RhemaApp.Rhema.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    @Lob
    @Column(columnDefinition = "TEXT")
    private String positionJson;

    @ManyToOne
    @JoinColumn(name = "song_id")
    @JsonBackReference
    private Song song;

    // Position 객체 -> JSON 문자열 변환
    public void setPosition(Position position) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        this.positionJson = mapper.writeValueAsString(position);
    }

    // JSON 문자열 -> Position 객체 변환
    public Position getPosition() throws JsonProcessingException {
        if (this.positionJson == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(this.positionJson, Position.class);
    }
}