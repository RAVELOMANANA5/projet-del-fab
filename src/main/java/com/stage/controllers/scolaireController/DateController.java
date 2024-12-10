package com.stage.controllers.scolaireController;

import com.stage.models.scolaire.Date;
import com.stage.service.scolaireService.DateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "api/v1/Dates")
public class DateController {

    private final DateService dateService;

    public DateController(DateService dateService) {
        this.dateService = dateService;
    }

    @PostMapping
    public ResponseEntity<String> addDate(@Validated @RequestBody Date date) {
        try {
            if (date != null) {
                dateService.addDate(date);
                return ResponseEntity.status(HttpStatus.CREATED).body("Date a été enregistrée");
            }
            else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Date n'a pas été enregistée");
            }
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getDates(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        Map<String, Object> response = new HashMap<>();
        try {
            response = dateService.getAllDates(pageNo, pageSize);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        catch (Exception e) {
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Map<String, Object>> findDateById(
            @PathVariable("id") Long id
    ) {
        Map<String, Object> response = new HashMap<>();
        try {
            if (id != null) {
                response = dateService.findDateById(id);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        }
        catch (Exception e) {
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<Map<String, Object>> updateDate(
            @PathVariable("id") Long id,
            @Validated @RequestBody Date date
    ) {
        Map<String, Object> response = new HashMap<>();
        try {
            if (id != null) {
                dateService.updateDate(id, date);
                response.put("message", "Date a été modifiée");
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
            else {
                response.put("message1","Date n'a pas été modifiée" );
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response );
            }
        }
        catch (Exception e) {
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<String> deleteDate(
            @PathVariable("id") Long id
    ) {
        try {
            if (id != null) {
                dateService.deleteDate(id);
                return ResponseEntity.status(HttpStatus.OK).body("Date a été supprimée");
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Date n'a pas été supprimée");
            }
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("message : " + e.getMessage());
        }
    }

}
