package RhemaApp.Rhema.service;

import RhemaApp.Rhema.dto.CreateContiRequestDTO;
import RhemaApp.Rhema.entity.Conti;
import RhemaApp.Rhema.entity.Song;
import RhemaApp.Rhema.entity.User;
import RhemaApp.Rhema.repository.ContiRepository;
import RhemaApp.Rhema.repository.SongRepository;
import RhemaApp.Rhema.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContiService {

    @Autowired
    private ContiRepository contiRepository;

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Date> getAllContiDates() {
        return contiRepository.findAll()
                .stream()
                .map(Conti::getDate)
                .collect(Collectors.toList());
    }

    public Conti saveConti(CreateContiRequestDTO request) {
        Conti conti = new Conti();
        conti.setDate(request.getDate());

        List<Song> songs = songRepository.findAllById(request.getSongIds());
        conti.setSongs(songs);

        Optional<User> createdBy = userRepository.findById(request.getCreatedBy());
        createdBy.ifPresent(conti::setCreated_by);

        conti.setCreated_at(new Date());
        conti.setUpdated_at(new Date());

        return contiRepository.save(conti);
    }

    public Conti updateConti(CreateContiRequestDTO request) {
        Conti conti = contiRepository.findById(request.getCreatedBy())
                .orElseThrow(() -> new RuntimeException("Conti not found"));

        conti.setDate(request.getDate());
        List<Song> songs = songRepository.findAllById(request.getSongIds());
        conti.setSongs(songs);
        conti.setUpdated_at(new Date());

        return contiRepository.save(conti);
    }

    public void deleteConti(Long contiId) {
        contiRepository.deleteById(contiId);
    }
}
