package RhemaApp.Rhema.controller;

import RhemaApp.Rhema.dto.CreateContiRequestDTO;
import RhemaApp.Rhema.entity.Conti;
import RhemaApp.Rhema.service.ContiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/conti")
public class ContiController {

    @Autowired
    private ContiService contiService;

    @GetMapping("/test")
    public String test() {
        return "test";
    }

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

