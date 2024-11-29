package com.stage.controllers.posteController;

import com.stage.models.poste.Inspecteur;
import com.stage.service.posteService.InspecteurService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "api/v1/Inspecteurs")
@CrossOrigin("http://localhost:****")
public class InspecteurController {

    private final InspecteurService inspecteurService;

    public InspecteurController(InspecteurService inspecteurService) {
        this.inspecteurService = inspecteurService;
    }

    @PostMapping
    public ResponseEntity<String> addInspecteur(@RequestBody @Valid Inspecteur inspecteur) {
        try {
            if (inspecteur != null) {
                inspecteurService.addInspecteur(inspecteur);
                return ResponseEntity.status(HttpStatus.CREATED).body("Inspecteur a été enrégistrée");
            }
            else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Inspecteur non presente");
            }
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getInspecteurs(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        Map<String, Object> allInspecteurs;
        try {
            allInspecteurs = inspecteurService.getAllInspecteurs(pageNo, pageSize);
            return ResponseEntity.status(HttpStatus.OK).body(allInspecteurs);
        }
        catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Map<String, Object>> findInspecteurById(
            @PathVariable("id") Long id
    ) {
        Map<String, Object> message = new HashMap<>();
        try {
            if (id != null) {
                message = inspecteurService.getInspecteurById(id);
                return ResponseEntity.status(HttpStatus.OK).body(message);
            }
            else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
        }
        catch (Exception e) {
            message.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    @GetMapping(value = "/withDetail")
    public ResponseEntity<Map<String, Object>> getInspecteurWithDetails(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        Map<String, Object> message = new HashMap<>();
        try {
            message = inspecteurService.getInspecteursWithDetails(pageNo, pageSize);
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }
        catch (Exception e) {
            message.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<Map<String, Object>> updateInspecteur(
            @PathVariable("id") Long id,
            @RequestBody Inspecteur inspecteur
    ) {
        Map<String, Object> message = new HashMap<>();
        try {
            if (id != null) {
                message.put("message", "Inspecteur a été modifiée");
                inspecteurService.updateInspecteur(id, inspecteur);
                return ResponseEntity.status(HttpStatus.OK).body(message);
            }
            else {
                message.put("message", "Inspecteur n'a pas été modifiée");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
            }
        }
        catch (Exception e) {
            message.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Map<String, Object>> deleteInspecteur(
            @PathVariable("id") Long id
    ) {
        Map<String, Object> message = new HashMap<>();
        try {
            if (id != null) {
                inspecteurService.deleteInspecteur(id);
                message.put("message", "Inspecteur a été supprimée");
                return ResponseEntity.status(HttpStatus.OK).body(message);
            }
            else {
                message.put("message", "Inspecteur n'a pas été supprimée");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
            }
        } 
        catch (Exception e) {
            message.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }
}
