package RhemaApp.Rhema.dto;

import java.util.List;
import java.util.Date;

public class CreateContiRequestDTO {

    private Date date;  //콘티 날짜
    private List<Long> songIds;     //곡의 id 목록
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

