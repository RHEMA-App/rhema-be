package RhemaApp.Rhema.service;

import RhemaApp.Rhema.dto.SectionDto;
import RhemaApp.Rhema.dto.SongRequestDto;
import RhemaApp.Rhema.dto.SongResponseDto;
import RhemaApp.Rhema.entity.Section;
import RhemaApp.Rhema.entity.Song;
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

    @Autowired
    public SongService (SongRepository songRepository) {
        this.songRepository = songRepository;
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
    public SongResponseDto saveSong(SongRequestDto songRequestDto) throws JsonProcessingException {
        System.out.println("songRequestDto key : " + songRequestDto.getSections().get(0).getKey());
        System.out.println("songRequestDto position : " + songRequestDto.getSections().get(2).getPosition().getSeconds());

        Song song = new Song();
        song.setName(songRequestDto.getName());
        song.setLink(songRequestDto.getLink());
        song.setScore(songRequestDto.getScore());

        List<Section> sections = new ArrayList<>();

        for(SectionDto sectionDto : songRequestDto.getSections()) {
            Section section = new Section();
            section.setSong(song);
            section.setKey(sectionDto.getKey());
            section.setPosition(sectionDto.getPosition());
            System.out.println("position : " + sectionDto.getPosition().getMinutes());
            System.out.println("position : " + sectionDto.getPosition().getSeconds());
            System.out.println("position : " + section.getPositionJson());
            sections.add(section);
        }

        song.setSections(sections);

        songRepository.save(song);

        System.out.println("song.id : " + song.getLink());

        SongResponseDto songResponseDto = new SongResponseDto();

//        songResponseDto.setName(song.getName());
//        songResponseDto.setLink(song.getLink());
//        songResponseDto.setScore(song.getScore());
//
//        List<SectionDto> sectionDtos = new ArrayList<>();
//
//        for(Section section : song.getSections()) {
//            SectionDto sectionDto = new SectionDto();
//            sectionDto.setKey(section.getKey());
//            sectionDto.setPosition(section.getPosition());
//            sectionDtos.add(sectionDto);
//        }
//
//        songResponseDto.setSections(sectionDtos);

        return songResponseDto;
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


