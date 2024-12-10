package com.stage.controllers.scolaireController;

import com.stage.models.scolaire.AnnScolaire;
import com.stage.service.scolaireService.AnnScolaireService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "api/v1/Ann-scolaire")
@CrossOrigin("http://localhost:****")
public class AnnScolaireController {

    private final AnnScolaireService annScolaireService;

    public AnnScolaireController(AnnScolaireService annScolaireService) {
        this.annScolaireService = annScolaireService;
    }

    @PostMapping
    public ResponseEntity<String> addAnnScolaire(@RequestBody @Valid AnnScolaire annScolaire) {
        try {
            if (annScolaire != null) {
                annScolaireService.addAnnScolaire(annScolaire);
                return ResponseEntity.status(HttpStatus.CREATED).body("AnnScolaire a été enregistrée");
            }
            else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("AnnScolaire n'a pas été enregistée");
            }
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAnnScolaire(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        Map<String, Object> response = new HashMap<>();
        try {
            response = annScolaireService.getAllAnnScolaire(pageNo, pageSize);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        catch (Exception e) {
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Map<String, Object>> findAnnScolaireById(
            @PathVariable("id") Long id
    ) {
        Map<String, Object> response = new HashMap<>();
        try {
                response = annScolaireService.findAnnScolaireById(id);
                return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        catch (Exception e) {
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
