package RhemaApp.Rhema.service;

import RhemaApp.Rhema.entity.Section;
import RhemaApp.Rhema.entity.Song;
import RhemaApp.Rhema.repository.SectionRepository;
import RhemaApp.Rhema.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SongService {

    private final SongRepository songRepository;
    private final SectionRepository sectionRepository;

    @Autowired
    public SongService (SongRepository songRepository, SectionRepository sectionRepository) {
        this.songRepository = songRepository;
        this.sectionRepository = sectionRepository;
    }

    //모든 노래 조회
    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }

    //노래 ID로 조회
    public Song getSongById(Long songId) {
        return songRepository.findById(songId)
                .orElseThrow(() -> new RuntimeException("조회된 정보가 없습니다."));
    }

    //노래 저장
    public Song saveSong(Song song) {
        song.setCreated_at(new Date());

        List<Section> saveSection = new ArrayList<>();
        for (Section section : song.getSections()) {
            section.setSong(song);
            saveSection.add(sectionRepository.save(section));
        }
        song.setSections(saveSection);

        return songRepository.save(song);
    }

    //노래 업데이트
    public Song updateSong(Long songId, Song songDetails) {
        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new RuntimeException("조회된 정보가 없습니다."));

        song.setName(songDetails.getName());
        song.setLink(songDetails.getLink());
        song.setUpdated_at(new Date());

        return songRepository.save(song);
    }

    //노래 삭제
    public void deleteSong(Long songId) {
        songRepository.deleteById(songId);
    }
}


