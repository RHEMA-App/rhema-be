package RhemaApp.Rhema.dto;

import RhemaApp.Rhema.entity.Section;
import RhemaApp.Rhema.entity.Song;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;
import java.util.List;

public class SongResponseDTO {
        private Long id;
        private String name;
        private String link;
        private String score;
        private String key;
        private List<SectionDTO> sections;

        //생성자
    public SongResponseDTO(Song song, List<SectionDTO> sections) {
        this.id = song.getId();
        this.name = song.getName();
        this.score = song.getScore();
        this.key = song.getKey();
        this.sections = new ArrayList<>();
    }

        //Getter and Setter
        public Long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getLink() {
            return link;
        }

        public String getScore() {
            return score;
        }

        public String getKey() {
            return key;
        }

        public List<SectionDTO> getSections() {
            return sections;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public void setSections(List<SectionDTO> sections) {
            this.sections = sections;
        }
    }
