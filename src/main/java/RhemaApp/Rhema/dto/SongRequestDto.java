package RhemaApp.Rhema.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class SongRequestDto {
    private String name;
    private String link;
    private String key;
    private String score;
    private List<SectionDto> sections;
}
