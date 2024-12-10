package com.stage.controllers.posteController;

import com.itextpdf.text.DocumentException;
import com.stage.models.poste.Poste;
import com.stage.repository.posteRepository.PosteRepository;
import com.stage.service.posteService.PosteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/Postes")
public class PosteController {

    private final PosteService posteService;
    private final PosteRepository posteRepository;

    public PosteController(PosteService posteService, PosteRepository posteRepository) {
        this.posteService = posteService;
        this.posteRepository = posteRepository;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> addPoste(@RequestBody @Valid Poste poste) {
        Map<String, Object> message = new HashMap<>();
        try {
            if (poste != null) {
                posteService.addPoste(poste);
                message.put("status", "succèss");
                return new ResponseEntity<>(message, HttpStatus.CREATED);
            }
            else {
                message.put("status", "echec");
                return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception e) {
            message.put("status", "erreur : " + e.getMessage());
            return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllPostes(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize
    ) {
        Map<String, Object> message = new HashMap<>();
        try {
            message = posteService.getAllPostes(pageNo, pageSize);
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        catch (Exception e) {
            message.put("status", "erreur : " + e.getMessage());
            return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Map<String, Object>> findPosteById(@PathVariable("id") Long id) {
        Map<String, Object> message = new HashMap<>();
        try {
            message = posteService.findPosteById(id);
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        catch (Exception e) {
            message.put("status", "erreur : " + e.getMessage());
            return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/pdf/{id}")
    public ResponseEntity<byte[]> generatePostePdf(@PathVariable("id") Long id) {
        try {
            byte[] pdf = posteService.generatePostePdf(id);
            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.CONTENT_TYPE, "application/pdf");
            headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=poste.pdf");
            return new ResponseEntity<>(pdf, headers, HttpStatus.OK);
        }
        catch (DocumentException | IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/by-year")
    public ResponseEntity<Map<String, Object>> findPaginatedPostsByOpeningYear(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        Map<String, Object> message = new HashMap<>();
        try {
            message = posteService.findAllByAnOuverPoste(pageNo, pageSize);
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        catch (Exception e) {
            message.put("status", "erreur : " + e.getMessage());
            return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/count-eleves-by-class")
    public ResponseEntity<Map<String, Object>> getCountElevesByClass(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        Map<String, Object> message = new HashMap<>();
        try {
            message = posteService.getCountElevesByClass(pageNo, pageSize);
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        catch (Exception e) {
            message.put("status", "erreur : " + e.getMessage());
            return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/count-postes-by-class")
    public ResponseEntity<Map<String, Object>> getCountPostesByClass(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        Map<String, Object> message = new HashMap<>();
        try {
            message = posteService.getCountPostesByClass(pageNo, pageSize);
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        catch (Exception e) {
            message.put("status", "erreur : " + e.getMessage());
            return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<Map<String, Object>> updatePoste(
            @PathVariable("id") Long id,
            @RequestBody Poste poste
    ) {
        Map<String, Object> message = new HashMap<>();
        try {
            if (poste != null) {
                posteService.updatePoste(id, poste);
                message.put("message", "Poste à été modifiée");
                return new ResponseEntity<>(message, HttpStatus.OK);
            }
            else {
                message.put("status", "fail");
                return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception e) {
            message.put("status", "erreur : " + e.getMessage());
            return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Map<String, Object>> deletePoste(@PathVariable("id") Long id) {
        Map<String, Object> message = new HashMap<>();
        try {
            posteService.deletePoste(id);
            message.put("message", "Poste a été supprimé.");
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        catch (Exception e) {
            message.put("status", "erreur : " + e.getMessage());
            return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
