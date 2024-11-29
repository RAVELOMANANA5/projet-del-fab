package com.stage.controllers.scolaireController;

import com.stage.models.scolaire.Matiere;
import com.stage.service.scolaireService.MatiereService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "api/v1/Matieres")
@CrossOrigin("http://localhost:****")
public class MatiereController {

    private final MatiereService matiereService;

    public MatiereController(MatiereService matiereService) {
        this.matiereService = matiereService;
    }

    @PostMapping
    public ResponseEntity<String> addMatiere(@RequestBody @Valid Matiere matiere) {
        try {
            if (matiere != null) {
                matiereService.addMatiere(matiere);
                return ResponseEntity.status(HttpStatus.CREATED).body("Matiere a été enregistrée");
            }
            else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Matiere n'a pas été enregistée");
            }
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getMatiere(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        Map<String, Object> response = new HashMap<>();
        try {
            response = matiereService.getAllMatieres(pageNo, pageSize);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        catch (Exception e) {
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Map<String, Object>> findMatiereById(
            @PathVariable("id") Long id
    ) {
        Map<String, Object> response = new HashMap<>();
        try {
            if (id != null) {
                response = matiereService.findMatiereById(id);
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
    public ResponseEntity<Map<String, Object>> updateMatiere(
            @PathVariable("id") Long id,
            @RequestBody Matiere matiere
    ) {
        Map<String, Object> response = new HashMap<>();
        try {
            if (id != null) {
                matiereService.updateMatiere(id, matiere);
                response.put("message", "Matiere a été modifiée");
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
            else {
                response.put("message","Matiere n'a pas été modifiée" );
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response );
            }
        }
        catch (Exception e) {
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<String> deleteMatiereById(
            @PathVariable("id") Long id
    ) {
        try {
            if (id != null) {
                matiereService.deleteMatiere(id);
                return ResponseEntity.status(HttpStatus.OK).body("Matiere a été supprimée");
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Matiere n'a pas été supprimée");
            }
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("message : " + e.getMessage());
        }
    }
}
