package RhemaApp.Rhema.dto;

import java.util.List;

public class SongDTO {
    private String name;
    private String link;
    private String score;
    private List<SectionDTO> sections;

    public String getName() {
        return  name;
    }

    public String getLink() {
        return link;
    }

    public String getScore() {
        return score;
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

    public void setSections(List<SectionDTO> sections) {
        this.sections = sections;
    }
}
