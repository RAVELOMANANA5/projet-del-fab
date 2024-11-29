package com.stage.controllers.posteController;

import com.stage.models.poste.Secteur;
import com.stage.service.posteService.SecteurService;
import com.stage.service.posteService.impl.SecteurServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "api/v1/Secteurs")
@CrossOrigin("http://localhost:****")
public class SecteurController {

    private final SecteurService secteurService;
    private final SecteurServiceImpl secteurServiceImpl;

    public SecteurController(SecteurService secteurService, SecteurServiceImpl secteurServiceImpl) {
        this.secteurService = secteurService;
        this.secteurServiceImpl = secteurServiceImpl;
    }

    @PostMapping
    public ResponseEntity<String> ajouterSecteur(@RequestBody @Valid Secteur secteur) {
        try {
            if (secteur != null) {
                secteurService.addSecteur(secteur);
                return ResponseEntity.status(HttpStatus.CREATED).body("Secteur a été enregistée");
            }
            else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Zone n'a pas été enregistée");
            }
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllSecteurs(
            @RequestParam(defaultValue = "0") int paneNo,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        Map<String, Object> response;
        try {
            response = secteurService.getSecteurs(paneNo, pageSize);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping(value = "/zone/{nomZone}")
    public ResponseEntity<Map<String, Object>> findSecteurByNomZone(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize,
            @PathVariable("nomZone") String nomZone
            ) {
        Map<String, Object> response;
        try {
            response = secteurService.findSecteurByNomZone(pageNo, pageSize, nomZone);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Map<String, Object>> findSecteurById(@PathVariable("id") Long id) {
        Map<String, Object> response;
        try {
            if (id != null) {
                response = secteurServiceImpl.findSecteurById(id);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
            else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<Map<String, Object>> updateSecteur(
            @PathVariable("id") Long id,
            @RequestBody Secteur secteur
    ) {
        Map<String, Object> response = new HashMap<>();
        try {
            if (id != null) {
                secteurService.updateSecteur(id, secteur);
                response.put("message", "Secteur a été modifiée");
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
            else {
                Map<String, Object> error = new HashMap<>();
                error.put("message","Secteur n'a pas été modifiée" );
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
    public ResponseEntity<String> deleteSecteurById(
            @PathVariable("id") Long id
    ) {
        try {
            if (id != null) {
                secteurService.deleteSecteur(id);
                return ResponseEntity.status(HttpStatus.OK).body("Secteur a été supprimée");
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Secteur n'a pas été supprimée");
            }
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("message : " + e.getMessage());
        }
    }
}
