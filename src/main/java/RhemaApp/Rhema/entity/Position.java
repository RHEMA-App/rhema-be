package RhemaApp.Rhema.entity;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Position {
    private int minutes;
    private int seconds;

    public Position() {
    }

    public Position(int minutes, int seconds) {
        this.minutes = minutes;
        this.seconds = seconds;
    }


    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    // toJson 메서드: 객체를 JSON 문자열로 직렬화
    public String toJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }

    // fromJson 메서드: JSON 문자열을 객체로 역직렬화
    public static Position fromJson(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, Position.class);
    }
}