package controller;

import dto.CreateContiRequestDTO;
import entity.Conti;
import service.ContiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/conti")
public class ContiController {

    @Autowired
    private ContiService contiService;

    @GetMapping("/dates")
    public List<Date> getContiDates() {
        return contiService.getAllContiDates();
    }

    @PostMapping("/conti")
    public Conti createConti(@RequestBody CreateContiRequestDTO request) {
        return contiService.saveConti(request);
    }

    @PatchMapping("/conti")
    public Conti updateConti(@RequestBody CreateContiRequestDTO request) {
        return contiService.updateConti(request);
    }


    @DeleteMapping("/conti")
    public void deleteConti(@RequestParam Long contiId) {
        contiService.deleteConti(contiId);
    }
}

