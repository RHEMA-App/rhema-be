package RhemaApp.Rhema.controller;

import RhemaApp.Rhema.dto.CreateContiRequestDTO;
import RhemaApp.Rhema.dto.ResponseDTO;
import RhemaApp.Rhema.entity.Conti;
import RhemaApp.Rhema.service.ContiService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/conti")
public class ContiController {

    private final ContiService contiService;

    @Autowired
    public ContiController(ContiService contiService) {
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

    @PatchMapping("/{contiid}")
    public ResponseEntity<ResponseDTO<String>> updateConti(@PathVariable("contiid") Long contiid, @RequestBody CreateContiRequestDTO request) throws JsonProcessingException {
        boolean success = contiService.updateConti(contiid, request);
        if (success) {
            return ResponseEntity.ok(ResponseDTO.success("콘티 수정이 완료되었습니다."));
        } else {
            return ResponseEntity.ok(ResponseDTO.error("콘티를 조회할 수 없습니다."));
        }
    }


    @DeleteMapping
    public ResponseDTO<String> deleteConti(@RequestParam("contiId") Long contiId) {
        try {
            contiService.deleteConti(contiId);
            return ResponseDTO.success("콘티 삭제 완료");
        } catch (RuntimeException e) {
            return ResponseDTO.error("콘티 삭제 실패");
        }
    }
}

