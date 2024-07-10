package RhemaApp.Rhema.controller;


import RhemaApp.Rhema.dto.SongRequestDTO;
import RhemaApp.Rhema.dto.SongResponseDTO;
import RhemaApp.Rhema.entity.Song;
import RhemaApp.Rhema.global.s3.S3FileService;
import RhemaApp.Rhema.service.SongService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/songs")
public class SongController {

    private final SongService songService;
    private final S3FileService s3FileService;

    @Autowired
    public SongController (SongService songService, S3FileService s3FileService) {
        this.songService = songService;
        this.s3FileService = s3FileService;
    }

    //노래 조회
    @GetMapping
    public List<Song> getAllSongs() {return songService.getAllSongs();
    }

    //노래 ID로 조회
    @GetMapping("/{songId}")
    public Song getSong(@PathVariable Long songId) {
        return songService.getSongById(songId);
    }

    //노래 등록
    @PostMapping
    public SongResponseDTO createSong(@RequestBody @Valid SongRequestDTO songDTO) throws JsonProcessingException {
        return songService.saveSong(songDTO);
    }

    //노래 업데이트
    @PatchMapping("/{songId}")
    public SongResponseDTO updateSong(@PathVariable("songId") Long songId, @RequestBody @Valid SongRequestDTO songRequestDTO) throws JsonProcessingException {
        return songService.updateSong(songId, songRequestDTO);
    }

    //노래 삭제
    @DeleteMapping("/{songId}")
    public void deleteSong(@PathVariable Long songId) {
        songService.deleteSong(songId);
    }

    //S3 Presigned url 요청
    @GetMapping("/score/{fileName}")
    public Map<String, String> getSongPresignedUrl(@PathVariable(name="fileName") String fileName) {
        return s3FileService.getPresignedUrl("/scores", fileName);
    }
}
