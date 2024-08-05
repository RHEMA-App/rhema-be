package RhemaApp.Rhema.service;

import RhemaApp.Rhema.dto.CreateContiRequestDTO;
import RhemaApp.Rhema.entity.Conti;
import RhemaApp.Rhema.entity.ContiSong;
import RhemaApp.Rhema.entity.Song;
import RhemaApp.Rhema.entity.User;
import RhemaApp.Rhema.repository.ContiRepository;
import RhemaApp.Rhema.repository.ContiSongRepository;
import RhemaApp.Rhema.repository.SongRepository;
import RhemaApp.Rhema.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContiService {

    private final ContiRepository contiRepository;
    private final SongRepository songRepository;
    private final UserRepository userRepository;
    private final ContiSongRepository contiSongRepository;

    @Autowired
    public ContiService(ContiRepository contiRepository, SongRepository songRepository, UserRepository userRepository, ContiSongRepository contiSongRepository) {
        this.contiRepository = contiRepository;
        this.songRepository = songRepository;
        this.userRepository = userRepository;
        this.contiSongRepository = contiSongRepository;
    }

//    public List<String> getAllContiDates(String sort) {
//        return contiRepository.findAll()
//                .stream()
//                .map(Conti::getDate)
//                .collect(Collectors.toList());
//    }

    public Conti saveConti(CreateContiRequestDTO request) {
        Conti conti = new Conti();
        conti.setDate(request.getDate());

        Optional<User> createdBy = userRepository.findById(request.getCreatedBy());
        createdBy.ifPresent(conti::setCreated_by);

        conti.setCreated_at(new Date());
        conti.setUpdated_at(new Date());

        List<Song> songs = songRepository.findAllById(request.getSongIds());
        for (Song song : songs) {
            ContiSong contiSong = new ContiSong();
            contiSong.setSong(song);
            conti.addContiSong(contiSong);
        }

        return contiRepository.save(conti);
    }

    @Transactional
    public boolean updateConti(Long contiId, CreateContiRequestDTO request) throws JsonProcessingException {
        Optional<Conti> optionalConti = contiRepository.findById(contiId);
        if (optionalConti.isPresent()) {
            Conti conti = optionalConti.get();

            contiSongRepository.deleteByContiId(contiId);

            conti.setDate(request.getDate());
            conti.setUpdated_at(new Date());

            List<Song> songs = songRepository.findAllById(request.getSongIds());
            for (Song song : songs) {
                ContiSong contiSong = new ContiSong();
                contiSong.setSong(song);
                contiSong.setConti(conti);
                conti.addContiSong(contiSong);
            }

            contiRepository.save(conti);
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public String deleteConti(Long contiId) {
        Conti conti = contiRepository.findById(contiId)
                .orElseThrow(()-> new RuntimeException("조회된 정보가 없습니다."));

            contiSongRepository.deleteByContiId(contiId);
            contiRepository.deleteById(contiId);
            return "콘티가 정상적으로 삭제되었습니다.";
    }


    @Transactional
    public List<String> getContiDates(String sort) {
        Sort.Direction direction = "desc".equalsIgnoreCase(sort) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sortByDate = Sort.by(direction, "date");

        List<Conti> contis = contiRepository.findAll(sortByDate);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return contis.stream()
                .map(conti -> dateFormat.format(conti.getDate()))
                .collect(Collectors.toList());
    }
}
