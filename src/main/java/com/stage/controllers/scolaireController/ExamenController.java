package com.stage.controllers.scolaireController;

import com.stage.models.scolaire.Examen;
import com.stage.service.scolaireService.ExamenService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "api/v1/Examens")
@CrossOrigin("http://localhost:****")
public class ExamenController {

    private final ExamenService examenService;

    public ExamenController(ExamenService examenService) {
        this.examenService = examenService;
    }

    @PostMapping
    public ResponseEntity<String> addExamen(@RequestBody @Valid Examen examen) {
        try {
            if (examen != null) {
                examenService.addExamen(examen);
                return ResponseEntity.status(HttpStatus.CREATED).body("Examen a été enregistrée");
            }
            else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Examen n'a pas été enregistée");
            }
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getExamen(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        Map<String, Object> response = new HashMap<>();
        try {
            response = examenService.getAllExamens(pageNo, pageSize);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        catch (Exception e) {
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Map<String, Object>> findExamenById(
            @PathVariable("id") Long id
    ) {
        Map<String, Object> response = new HashMap<>();
        try {
            if (id != null) {
                response = examenService.findExamenById(id);
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
    public ResponseEntity<Map<String, Object>> updateExamen(
            @PathVariable("id") Long id,
            @RequestBody Examen examen
    ) {
        Map<String, Object> response = new HashMap<>();
        try {
            if (id != null) {
                examenService.updateExamen(id, examen);
                response.put("message", "Examen a été modifiée");
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
            else {
                response.put("message","Examen n'a pas été modifiée" );
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response );
            }
        }
        catch (Exception e) {
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<String> deleteExamenById(
            @PathVariable("id") Long id
    ) {
        try {
            if (id != null) {
                examenService.deleteExamen(id);
                return ResponseEntity.status(HttpStatus.OK).body("Examen a été supprimée");
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Examen n'a pas été supprimée");
            }
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("message : " + e.getMessage());
        }
    }
}
