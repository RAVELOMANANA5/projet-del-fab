package com.stage.controllers.posteController;

import com.stage.models.poste.Commune;
import com.stage.service.posteService.CommuneService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/Communes")
public class CommuneController {
    private final CommuneService communeService;

    public CommuneController(CommuneService communeService) {
        this.communeService = communeService;
    }

    @PostMapping
    public ResponseEntity<Map<String, java.lang.Object>> addCommune(@RequestBody @Valid Commune commune) {
        Map<String, java.lang.Object> message = new HashMap<>();
        try {
            if (commune != null) {
                communeService.addCommune(commune);
                message.put("status", "succèss");
                return new ResponseEntity<>(message, HttpStatus.CREATED);
            }
            else {
                message.put("status", "erreur");
                return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception e) {
            message.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllCommunes(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize) {
        Map<String, Object> message = new HashMap<>();
        try {
            message = communeService.getCommunes(pageNo, pageSize);
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        catch (Exception e) {
            message.put("message", e.getMessage());
            return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Map<String, Object>> findCommuneById(@PathVariable("id") Long id) {
        Map<String, Object> message = new HashMap<>();
        try {
            message = communeService.findCommuneById(id);
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        catch (Exception e) {
            message.put("message", e.getMessage());
            return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<Map<String, Object>> updateCommune(
            @PathVariable("id") Long id,
            @RequestBody Commune commune
    ) {
        Map<String, Object> response = new HashMap<>();
        try {
            if (id != null) {
                communeService.updateCommune(id, commune);
                response.put("message", "Commune a été modifiée");
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
            else {
                response.put("message","Commune n'a pas été modifiée" );
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response );
            }
        }
        catch (Exception e) {
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<String> deleteCommuneById(
            @PathVariable("id") Long id
    ) {
        try {
            if (id != null) {
                communeService.deleteCommune(id);
                return ResponseEntity.status(HttpStatus.OK).body("Commune a été supprimée");
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Commune n'a pas été supprimée");
            }
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("message : " + e.getMessage());
        }
    }
}
