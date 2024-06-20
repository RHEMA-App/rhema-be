package RhemaApp.Rhema.controller;

import RhemaApp.Rhema.entity.Song;
import RhemaApp.Rhema.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping
    public List<Song> searchSongs(@RequestParam(required = false) String query) {
        if (query == null) {
            return songService.getAllSongs();
        } else {
            return songService.searchSongs(query);
        }
    }

    @GetMapping("/{songId}")
    public Song getSong(@PathVariable Long songId) {
        return songService.getSongById(songId);
    }

    @PostMapping
    public Song createSong(@RequestBody Song song) {
        return songService.saveSong(song);
    }

    @PatchMapping("/{songId}")
    public Song updateSong(@PathVariable Long songId, @RequestBody Song songDetails) {
        return songService.updateSong(songId, songDetails);
    }

    @DeleteMapping("/{songId}")
    public void deleteSong(@PathVariable Long songId) {
        songService.deleteSong(songId);
    }
}
