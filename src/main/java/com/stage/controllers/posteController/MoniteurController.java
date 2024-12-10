package com.stage.controllers.posteController;

import com.stage.models.poste.Moniteur;
import com.stage.service.posteService.MoniteurService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/moniteurs")
public class MoniteurController {

    private final MoniteurService moniteurService;

    public MoniteurController(MoniteurService moniteurService) {
        this.moniteurService = moniteurService;
    }

    @PostMapping
    public ResponseEntity<String> addMoniteur(@RequestBody @Valid Moniteur moniteur) {
        try {
            if (moniteur != null) {
                moniteurService.addMoniteur(moniteur);
                return ResponseEntity.status(HttpStatus.CREATED).body("Moniteur a été enregistrée");
            }
            else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Moniteur n'a pas été enregistée");
            }
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getMoniteurs(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        Map<String, Object> response;
        try {
            response = moniteurService.getAllMoniteurs(pageNo, pageSize);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Map<String, Object>> findMoniteurById(
            @PathVariable("id") Long id
    ) {
        Map<String, Object> response;
        try {
            if (id != null) {
                response = moniteurService.findMoniteurById(id);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        }
        catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<Map<String, Object>> updateMoniteur(
            @PathVariable("id") Long id,
            @RequestBody Moniteur moniteur
    ) {
        Map<String, Object> response = new HashMap<>();
        try {
            if (id != null) {
                moniteurService.updateMoniteur(id, moniteur);
                response.put("message", "Moniteur a été modifiée");
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
            else {
                Map<String, Object> error = new HashMap<>();
                error.put("message1","Moniteur n'a pas été modifiée" );
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error );
            }
        }
        catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<String> deleteMoniteurById(
            @PathVariable("id") Long id
    ) {
        try {
            if (id != null) {
                moniteurService.deleteMoniteur(id);
                return ResponseEntity.status(HttpStatus.OK).body("Moniteur a été supprimée");
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Moniteur n'a pas été supprimée");
            }
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("message : " + e.getMessage());
        }
    }

    @GetMapping(value = "/withDetail")
    public ResponseEntity<Map<String, Object>> getAllMonWithInspectPost(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        Map<String, Object> response;
        try {
            response = moniteurService.getMoniteursWithDetails(pageNo, pageSize);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}
