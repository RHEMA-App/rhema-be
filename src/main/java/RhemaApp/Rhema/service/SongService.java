package RhemaApp.Rhema.service;

import RhemaApp.Rhema.dto.ResponseDTO;
import RhemaApp.Rhema.dto.SectionDTO;
import RhemaApp.Rhema.dto.SongRequestDTO;
import RhemaApp.Rhema.dto.SongResponseDTO;
import RhemaApp.Rhema.entity.Position;
import RhemaApp.Rhema.entity.Section;
import RhemaApp.Rhema.entity.Song;
import RhemaApp.Rhema.repository.SectionRepository;
import RhemaApp.Rhema.repository.SongRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SongService {

    @Autowired
    private final SongRepository songRepository;
    private final SectionRepository sectionRepository;

    public SongService(SongRepository songRepository, SectionRepository sectionRepository) {
        this.songRepository = songRepository;
        this.sectionRepository = sectionRepository;
    }

    //전체 노래 조회
    @Transactional
    public ResponseDTO<List<SongResponseDTO>> getAllSongs() throws JsonProcessingException {
        List<Song> songs = songRepository.findAll();
        return createResponseDTO(songs);
    }

    //키워드 노래 조회
    @Transactional
    public ResponseDTO<List<SongResponseDTO>> searchSongs(String keyword) throws JsonProcessingException {
        List<Song> songs = songRepository.findByNameContaining(keyword);
        return createResponseDTO(songs);
    }

    //노래 ID로 조회
    @Transactional
    public Song getSongById(Long songId) {
        return songRepository.findById(songId)
                .orElseThrow(() -> new RuntimeException("조회된 정보가 없습니다."));
    }

    private ResponseDTO<List<SongResponseDTO>> createResponseDTO(List<Song> songs) {
        List<SongResponseDTO> songResponseDTOs = new ArrayList<>();
        for (Song song : songs) {
            List<SectionDTO> sections = new ArrayList<>();
            songResponseDTOs.add(new SongResponseDTO(song));
        }
        return ResponseDTO.success(songResponseDTOs);
    }

    private SongResponseDTO toResponseDTO(Song song, List<SectionDTO> sections) {
        return new SongResponseDTO(song);
    }




    //노래 저장
    public SongResponseDTO saveSong(SongRequestDTO songRequestDTO) throws JsonProcessingException {
        Song song = new Song();
        song.setName(songRequestDTO.getName());
        song.setLink(songRequestDTO.getLink());
        song.setScore(songRequestDTO.getScore());
        song.setKey(songRequestDTO.getKey());
        song.setCreated_at(new Date());

        List<Section> sections = new ArrayList<>();
        for (SectionDTO sectionDTO : songRequestDTO.getSections()) {
            Section section = new Section();
            section.setKey(sectionDTO.getKey());
            Position position = new Position();
            position.setMinutes(sectionDTO.getPosition().getMinutes());
            position.setSeconds(sectionDTO.getPosition().getSeconds());
            section.setPosition(position);
            section.setSong(song);
            sections.add(section);
        }
        song.setSections(sections);

        Song saveSong = songRepository.save(song);
        sectionRepository.saveAll(sections);


        SongResponseDTO responseDTO = new SongResponseDTO(saveSong);

        return responseDTO;
    }

    //노래 업데이트
    @Transactional
    public SongResponseDTO updateSong( Long songId, SongRequestDTO songRequestDTO) throws JsonProcessingException {
        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new RuntimeException("조회된 정보가 없습니다."));


        song.setName(songRequestDTO.getName());
        song.setLink(songRequestDTO.getLink());
        song.setScore(songRequestDTO.getScore());
        song.setKey(songRequestDTO.getKey());
        song.setUpdated_at(new Date());

        List<Section> existingSections = song.getSections();

        for (SectionDTO sectionDTO : songRequestDTO.getSections()) {
            for (Section existingSection : existingSections) {
                if (existingSection.getKey().equals(sectionDTO.getKey())) {
                    Position position = existingSection.getPosition();
                    if (position == null) {
                        position = new Position();
                        existingSection.setPosition(position);
                    }
                    position.setMinutes(sectionDTO.getPosition().getMinutes());
                    position.setSeconds(sectionDTO.getPosition().getSeconds());
                }
            }
        }
            songRepository.save(song);
            sectionRepository.saveAll(existingSections);

        return new SongResponseDTO(song);
    }


    //노래 삭제
    @Transactional
        public String deleteSong (Long songId){
            Song song = songRepository.findById(songId)
                    .orElseThrow(() -> new RuntimeException("조회된 정보가 없습니다."));

            sectionRepository.deleteBySong(song);
            songRepository.deleteById(songId);
            return "노래가 성공적으로 삭제되었습니다.";
        }
    }

