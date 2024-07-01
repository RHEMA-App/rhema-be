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
    public SongDTO.SongResponseDTO saveSong(SongDTO.SongRequestDTO songRequestDTO) throws JsonProcessingException {
        Song song = new Song();
        song.setName(songRequestDTO.getName());
        song.setLink(songRequestDTO.getLink());
        song.setScore(songRequestDTO.getScore());
        song.setCreated_at(new Date());

        List<Section> sections = new ArrayList<>();
        for (SectionDTO sectionDTO : songRequestDTO.getSections()) {
            Section section = new Section();
            section.setKey((sectionDTO.getKey()));
            section.setPosition(sectionDTO.getPosition());
            section.setSong(song);
            sections.add(section);
        }
        song.setSections(sections);

        Song saveSong = songRepository.save(song);
        sectionRepository.saveAll(sections);

        SongDTO.SongResponseDTO responseDTO = new SongDTO.SongResponseDTO();
        responseDTO.setId(saveSong.getId());
        responseDTO.setName(saveSong.getName());
        responseDTO.setLink(saveSong.getLink());
        responseDTO.setScore(saveSong.getScore());
        responseDTO.setKey(saveSong.getKey());
        responseDTO.setSections(songRequestDTO.getSections());

        return responseDTO;
    }

    //노래 업데이트
    public SongDTO.SongResponseDTO updateSong(Long songId, SongDTO.SongRequestDTO songRequestDTO) throws JsonProcessingException {
        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new RuntimeException("조회된 정보가 없습니다."));

        song.setName(songRequestDTO.getName());
        song.setLink(songRequestDTO.getLink());
        song.setScore(songRequestDTO.getScore());
        song.setUpdated_at(new Date());

        sectionRepository.deleteBySong(song);
        List<Section> sections = new ArrayList<>();
        for (SectionDTO sectionDTO : songRequestDTO.getSections()) {
            Section section = new Section();
            section.setKey(sectionDTO.getKey());
            section.setPosition(sectionDTO.getPosition());
            section.setSong(song);
            sections.add(section);
        }
        song.setSections(sections);

        Song updateSong = songRepository.save(song);
        sectionRepository.saveAll(sections);

        SongDTO.SongResponseDTO responseDTO = new SongDTO.SongResponseDTO();
        responseDTO.setId(updateSong.getId());
        responseDTO.setName(updateSong.getName());
        responseDTO.setLink(updateSong.getLink());
        responseDTO.setScore(updateSong.getScore());
        responseDTO.setKey(updateSong.getKey());
        responseDTO.setSections(songRequestDTO.getSections());

        return responseDTO;
    }

    //노래 삭제
    public void deleteSong(Long songId) {
        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new RuntimeException("조회된 정보가 없습니다."));
    sectionRepository.deleteBySong(song);
    songRepository.deleteById(songId);
    }
}


