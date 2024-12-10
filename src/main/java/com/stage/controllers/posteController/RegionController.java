package com.stage.controllers.posteController;

import com.stage.models.poste.Moniteur;
import com.stage.models.poste.Region;
import com.stage.service.posteService.impl.RegionServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "api/v1/Regions")
public class RegionController {

    private RegionServiceImpl regionService;

    public RegionController(RegionServiceImpl regionService) {
        this.regionService = regionService;
    }

    @PostMapping
    public ResponseEntity<String> ajouterRegion(@RequestBody @Valid Region region) {
        try {
            if (region != null) {
                regionService.addRegion(region);
                return ResponseEntity.status(HttpStatus.CREATED).body("Region a été enregistée");
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erreur de sauvegardage");
            }
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllRegion(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        Map<String, Object> allRegions = new HashMap<>();
        try {
            allRegions = regionService.getRegions(pageNo, pageSize);
            return ResponseEntity.status(HttpStatus.OK).body(allRegions);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(allRegions);
        }
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Map<String, Object>> findRegionById(
            @PathVariable("id") Long id
    ) {
        Map<String, Object> response;
        try {
            if (id != null) {
                response = regionService.findRegionById(id);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        }
        catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<Map<String, Object>> updateRegion(
            @PathVariable("id") Long id,
            @RequestBody Region region
    ) {
        Map<String, Object> response = new HashMap<>();
        try {
            if (id != null) {
                regionService.updateRegion(id, region);
                response.put("message", "Region a été modifiée");
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
            else {
                Map<String, Object> error = new HashMap<>();
                error.put("message","Region n'a pas été modifiée" );
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
    public ResponseEntity<String> deleteRegionById(
            @PathVariable("id") Long id
    ) {
        try {
            if (id != null) {
                regionService.deleteRegion(id);
                return ResponseEntity.status(HttpStatus.OK).body("Region a été supprimée");
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Region n'a pas été supprimée");
            }
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("message : " + e.getMessage());
        }
    }

}
