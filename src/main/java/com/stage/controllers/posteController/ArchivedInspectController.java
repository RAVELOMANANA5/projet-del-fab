package com.stage.controllers.posteController;

import com.stage.service.posteService.ArchivedInspectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/ArchivedInspecteur")
public class ArchivedInspectController {

    private final ArchivedInspectService archivedInspectService;

    public ArchivedInspectController(ArchivedInspectService archivedInspectService) {
        this.archivedInspectService = archivedInspectService;
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Map<String, Object>> archivedMoniteur(@PathVariable("id") Long id) {
        Map<String, Object> message = new HashMap<>();
        try {
            archivedInspectService.archivedInspecteur(id);
            message.put("message", "Inspecteur archivé");
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        catch (Exception e) {
            message.put("message", e.getMessage());
            return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
