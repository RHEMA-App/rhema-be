package RhemaApp.Rhema.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO<T> {
    private boolean ok;
    private T msg;

    public static <T> ResponseDTO<T> success(T msg) {
        return new ResponseDTO<>(true, msg);
    }

    public static ResponseDTO<String> error(String msg) {
        return new ResponseDTO<>(false, msg);
    }
}
