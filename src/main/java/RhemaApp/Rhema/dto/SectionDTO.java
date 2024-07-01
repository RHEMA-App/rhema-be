package RhemaApp.Rhema.dto;

import RhemaApp.Rhema.entity.Position;
import org.jetbrains.annotations.NotNull;

public class SectionDTO {

    @NotNull("Key 값을 입력해주세요")
    private String key;
    private Position position;

    public String getKey() {
        return key;
    }

    public Position getPosition() {
        return position;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
