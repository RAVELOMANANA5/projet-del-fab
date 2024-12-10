package com.stage.controllers.scolaireController;

import com.stage.models.scolaire.Eleve;
import com.stage.service.scolaireService.EleveService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "api/v1/Eleves")
public class EleveController {

    private final EleveService eleveService;

    public EleveController(EleveService eleveService) {
        this.eleveService = eleveService;
    }

    @PostMapping
    public ResponseEntity<String> addEleve(@RequestBody @Valid Eleve eleve) {
        try {
            if (eleve != null) {
                eleveService.addEleve(eleve);
                return ResponseEntity.status(HttpStatus.CREATED).body("Eleve a été enregistrée");
            }
            else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Eleve n'a pas été enregistée");
            }
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getEleves(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        Map<String, Object> response = new HashMap<>();
        try {
            response = eleveService.getAllEleves(pageNo, pageSize);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        catch (Exception e) {
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Map<String, Object>> findEleveById(
            @PathVariable("id") Long id
    ) {
        Map<String, Object> response = new HashMap<>();
        try {
            if (id != null) {
                response = eleveService.findEleveById(id);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        }
        catch (Exception e) {
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<Map<String, Object>> updateEleve(
            @PathVariable("id") Long id,
            @RequestBody Eleve eleve
    ) {
        Map<String, Object> response = new HashMap<>();
        try {
            if (id != null) {
                eleveService.updateEleve(id, eleve);
                response.put("message", "Eleve a été modifiée");
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
            else {
                response.put("message1","Eleve n'a pas été modifiée" );
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response );
            }
        }
        catch (Exception e) {
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<String> deleteEleveById(
            @PathVariable("id") Long id
    ) {
        try {
            if (id != null) {
                eleveService.deleteEleve(id);
                return ResponseEntity.status(HttpStatus.OK).body("Eleve a été supprimée");
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Eleve n'a pas été supprimée");
            }
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("message : " + e.getMessage());
        }
    }
}
