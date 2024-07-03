package RhemaApp.Rhema.dto;


import jakarta.validation.constraints.Pattern;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SongRequestDTO {
        @NotNull("곡 명을 입력해주세요")
        private String name;
        @NotNull("link를 입력해주세요")
        private String link;
        private String score;
        @Pattern(regexp = "^[A-G]$", message = "알맞는 키 값을 입력해주세요")
        private String key;
        private List<SectionDTO> sections;

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


