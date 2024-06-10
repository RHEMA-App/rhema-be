package RhemaApp.Rhema.dto;

import java.util.List;
import java.util.Date;

public class CreateContiRequestDTO {

    private Date date;
    private List<Long> songIds;
    private Long createdBy;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Long> getSongIds() {
        return songIds;
    }

    public void setSongIds(List<Long> songIds) {
        this.songIds = songIds;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }
}

