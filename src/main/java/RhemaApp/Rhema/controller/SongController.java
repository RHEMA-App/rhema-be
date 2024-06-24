package RhemaApp.Rhema.controller;

import RhemaApp.Rhema.dto.SongRequestDto;
import RhemaApp.Rhema.dto.SongResponseDto;
import RhemaApp.Rhema.entity.Song;
import RhemaApp.Rhema.service.SongService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/songs")
public class SongController {

    private final SongService songService;
    @Autowired
    public SongController (SongService songService) {
        this.songService = songService;
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

    //노래 생성
    @PostMapping
    public ResponseEntity<SongResponseDto> createSong(@RequestBody SongRequestDto songRequestDto) throws JsonProcessingException {
        return ResponseEntity.ok().body(songService.saveSong(songRequestDto));
    }

    //노래 업데이트
    @PatchMapping("/{songId}")
    public Song updateSong(@PathVariable Long songId, @RequestBody Song songDetails) {
        return songService.updateSong(songId, songDetails);
    }

    //노래 삭제
    @DeleteMapping("/{songId}")
    public void deleteSong(@PathVariable Long songId) {
        songService.deleteSong(songId);
    }
}
