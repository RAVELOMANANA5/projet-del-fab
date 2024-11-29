package com.stage.controllers.posteController;

import com.stage.models.poste.Cisco;
import com.stage.service.posteService.CiscoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/Cisco")
@CrossOrigin("http://localhost:****")
public class CiscoController {

    private final CiscoService ciscoService;

    public CiscoController(CiscoService ciscoService) {
        this.ciscoService = ciscoService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> addCisco(@RequestBody @Valid Cisco cisco) {
        Map<String, Object> message = new HashMap<>();
        try {
            ciscoService.addCisco(cisco);
            message.put("message", "Cisco a été enrégistrée");
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        }
        catch (Exception e) {
            message.put("message", e.getMessage());
            return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllCisco(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        Map<String, Object> message = new HashMap<>();
        try {
            message = ciscoService.getAllCisco(pageNo, pageSize);
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        catch (Exception e) {
            message.put("message", e.getMessage());
            return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Map<String, Object>> findCiscoById(@PathVariable("id") Long id) {
        Map<String, Object> message = new HashMap<>();
        try {
            message = ciscoService.findCiscoById(id);
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        catch (Exception e) {
            message.put("message", e.getMessage());
            return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<Map<String, Object>> updateCisco(
            @PathVariable("id") Long id,
            @RequestBody Cisco cisco
    ) {
        Map<String, Object> response = new HashMap<>();
        try {
            if (id != null) {
                ciscoService.updateCisco(id, cisco);
                response.put("message", "Cisco a été modifiée");
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
            else {
                Map<String, Object> error = new HashMap<>();
                error.put("message","Cisco n'a pas été modifiée" );
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
    public ResponseEntity<String> deleteCiscoById(
            @PathVariable("id") Long id
    ) {
        try {
            if (id != null) {
                ciscoService.deleteCisco(id);
                return ResponseEntity.status(HttpStatus.OK).body("Cisco a été supprimée");
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cisco n'a pas été supprimée");
            }
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("message : " + e.getMessage());
        }
    }
}
