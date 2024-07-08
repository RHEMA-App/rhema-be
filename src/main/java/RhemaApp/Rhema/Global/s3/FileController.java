package RhemaApp.Rhema.Global.s3;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @GetMapping("/api/v1/file/presigned-url/{fileName}")
    public Map<String, String> getPresignedUrl(
            @PathVariable(name = "fileName") String fileName) {
        return fileService.getPresignedUrl("images", fileName);
    }
}
