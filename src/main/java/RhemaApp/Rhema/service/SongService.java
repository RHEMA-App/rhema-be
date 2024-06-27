package RhemaApp.Rhema.service;

import RhemaApp.Rhema.dto.SectionDTO;
import RhemaApp.Rhema.dto.SongDTO;
import RhemaApp.Rhema.entity.Section;
import RhemaApp.Rhema.entity.Song;
import RhemaApp.Rhema.repository.SectionRepository;
import RhemaApp.Rhema.repository.SongRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
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
    public Song saveSong(SongDTO songDTO) throws JsonProcessingException {
        Song song = new Song();
        song.setName(songDTO.getName());
        song.setLink(songDTO.getLink());
        song.setScore(songDTO.getScore());
        song.setCreated_at(new Date());
        song.setCreated_at(new Date());

        List<Section> sections = new ArrayList<>();
        for (SectionDTO sectionDTO : songDTO.getSections()) {
            Section section = new Section();
            section.setKey((sectionDTO.getKey()));
            section.setPosition(sectionDTO.getPosition());
            section.setSong(song);
            sections.add(section);
        }
        song.setSections(sections);

        Song saveSong = songRepository.save(song);
        sectionRepository.saveAll(sections);

        return saveSong;
    }

    //노래 업데이트
    public Song updateSong(Long songId, SongDTO songDTO) throws JsonProcessingException {
        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new RuntimeException("조회된 정보가 없습니다."));

        song.setName(songDTO.getName());
        song.setLink(songDTO.getLink());
        song.setScore(songDTO.getScore());
        song.setUpdated_at(new Date());

        sectionRepository.deleteBySong(song);
        List<Section> sections = new ArrayList<>();
        for (SectionDTO sectionDTO : songDTO.getSections()) {
            Section section = new Section();
            section.setKey(sectionDTO.getKey());
            section.setPosition(sectionDTO.getPosition());
            section.setSong(song);
            sections.add(section);
        }
        song.setSections(sections);

        Song updateSong = songRepository.save(song);
        sectionRepository.saveAll(sections);

        return updateSong;
    }

    //노래 삭제
    public void deleteSong(Long songId) {
        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new RuntimeException("조회된 정보가 없습니다."));
    sectionRepository.deleteBySong(song);
    songRepository.deleteById(songId);
    }
}


