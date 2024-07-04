package RhemaApp.Rhema.service;

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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SongService {

    private final SongRepository songRepository;
    private final SectionRepository sectionRepository;

    @Autowired
    public SongService(SongRepository songRepository, SectionRepository sectionRepository) {
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

//        SongResponseDTO responseDTO = new SongResponseDTO(saveSong, songRequestDTO.getSections());
//        responseDTO.setId(saveSong.getId());
//        responseDTO.setName(saveSong.getName());
//        responseDTO.setLink(saveSong.getLink());
//        responseDTO.setScore(saveSong.getScore());
//        responseDTO.setKey(saveSong.getKey());
//        responseDTO.setSections(songRequestDTO.getSections());

        SongResponseDTO responseDTO = new SongResponseDTO(saveSong, songRequestDTO.getSections());

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
//        List<SectionDTO> newSections = songRequestDTO.getSections();

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

                    // section.setPosition(position);
                    //section.setSong(song);

                    //if (section.getId() == null) {
                    //  existingSections.add(section);
                }
            }
        }


            songRepository.save(song);
            sectionRepository.saveAll(existingSections);

        return new SongResponseDTO(song, songRequestDTO.getSections());
    }


        //노래 삭제
        public void deleteSong (Long songId){
            Song song = songRepository.findById(songId)
                    .orElseThrow(() -> new RuntimeException("조회된 정보가 없습니다."));

            sectionRepository.deleteBySong(song);
            songRepository.deleteById(songId);
        }
    }

