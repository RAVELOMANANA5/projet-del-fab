package com.stage.controllers.scolaireController;

import com.stage.models.scolaire.Matiere;
import com.stage.models.scolaire.ResultatExamen;
import com.stage.service.scolaireService.ResultatExamenService;
import com.stage.service.scolaireService.impl.ResultatExamenServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "api/v1/ResultatExamens")
@CrossOrigin("http://localhost:****")
public class ResultatExamenController {

    private final ResultatExamenService resultatExamenService;
    private final ResultatExamenServiceImpl resultatExamenServiceImpl;

    public ResultatExamenController(ResultatExamenService resultatExamenService,
                                    ResultatExamenServiceImpl resultatExamenServiceImpl) {
        this.resultatExamenService = resultatExamenService;
        this.resultatExamenServiceImpl = resultatExamenServiceImpl;
    }

    @PostMapping
    public ResponseEntity<String> addResultatExamen(@Validated @RequestBody ResultatExamen resultatExamen) {
        try {
            if (resultatExamen != null) {
                resultatExamenService.addResultatExamen(resultatExamen);
                return ResponseEntity.status(HttpStatus.CREATED).body("ResultatExamen a été enregistrée");
            }
            else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ResultatExamen n'a pas été enregistée");
            }
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllResultatExamen(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        Map<String, Object> response = new HashMap<>();
        try {
            response = resultatExamenService.getAllResultatExamens(pageNo, pageSize);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        catch (Exception e) {
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping(value = "/result")
    public ResponseEntity<Map<String, Object>> findElevesWithMoyenneAndRang() {
        Map<String, Object> response = new HashMap<>();
        try {
            response = resultatExamenServiceImpl.findElevesWithMoyenneAndRang();
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        catch (Exception e) {
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<Map<String, Object>> updateResultatExamen(
            @PathVariable("id") Long id,
            @RequestBody ResultatExamen resultatExamen
    ) {
        Map<String, Object> response = new HashMap<>();
        try {
            if (id != null) {
                resultatExamenService.updateResultatExamen(id, resultatExamen);
                response.put("message", "ResultatExamen a été modifiée");
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
            else {
                response.put("message","ResultatExamen n'a pas été modifiée" );
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response );
            }
        }
        catch (Exception e) {
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
