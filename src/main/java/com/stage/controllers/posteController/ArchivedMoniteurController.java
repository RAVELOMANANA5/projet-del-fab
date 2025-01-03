package com.stage.controllers.posteController;

import com.stage.service.posteService.ArchivedMoniteurService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/ArchivedMoniteur")
public class ArchivedMoniteurController {

    private final ArchivedMoniteurService archivedMoniteurService;

    public ArchivedMoniteurController(ArchivedMoniteurService archivedMoniteurService) {
        this.archivedMoniteurService = archivedMoniteurService;
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Map<String, Object>> archivedMoniteur(@PathVariable("id") Long id) {
        Map<String, Object> message = new HashMap<>();
        try {
            archivedMoniteurService.archiverMoniteur(id);
            message.put("message", "Moniteur archivé avec succès");
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        catch (Exception e) {
            message.put("message", e.getMessage());
            return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getMoniteurs(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        Map<String, Object> response;
        try {
            response = archivedMoniteurService.getAllArchivedMoniteurs(pageNo, pageSize);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}
