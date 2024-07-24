package RhemaApp.Rhema.controller;

import RhemaApp.Rhema.dto.CreateContiRequestDTO;
import RhemaApp.Rhema.dto.ResponseDTO;
import RhemaApp.Rhema.entity.Conti;
import RhemaApp.Rhema.service.ContiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/conti")
public class ContiController {

    private final ContiService contiService;
    @Autowired
    public ContiController (ContiService contiService) {
        this.contiService = contiService;
    }

    @PostMapping
    public ResponseDTO<String> createConti(@RequestBody CreateContiRequestDTO request) {
        try {
            Conti conti = contiService.saveConti(request);
            return ResponseDTO.success("콘티가 성공적으로 생성되었습니다.");
        } catch (Exception e) {
            return ResponseDTO.error("콘티 생성 실패 : " + e.getMessage());
        }
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

