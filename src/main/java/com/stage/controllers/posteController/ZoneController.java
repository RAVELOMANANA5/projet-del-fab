package com.stage.controllers.posteController;

import com.stage.dtos.posteDTOS.ZoneDTO;
import com.stage.models.poste.Zone;
import com.stage.service.posteService.impl.ZoneServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "api/v1/Zones")
public class ZoneController {

    private final ZoneServiceImpl zoneService;

    public ZoneController(ZoneServiceImpl zoneService) {
        this.zoneService = zoneService;
    }

    @PostMapping
    public ResponseEntity<String> addZone(@RequestBody @Valid Zone zone) {
        try {
            if (zone != null) {
                zoneService.addZone(zone);
                return ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body("Zone a été enregistée");
            }
            else {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("Zone n'a pas été enregistée");
            }
        }
        catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getZones(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize)
    {
        Map<String, Object> response;
        try {
            response = zoneService.getZones(pageNo, pageSize);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(response);
        }
        catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Map<String, Object>> findZoneById(@PathVariable("id") Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            if (id != null) {
                ZoneDTO zoneByID = zoneService.findZoneByID(id);
                response.put("zone", zoneByID);
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

    @GetMapping("/region/{nomRegion}")
    public ResponseEntity<Map<String, Object>> getZonesByRegion(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize,
            @PathVariable("nomRegion") String nomRegion
    ) {
        Map<String, Object> response;
        try {
            response = zoneService.findZonesByNomRegion(pageNo, pageSize, nomRegion);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(response);
        }
        catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<Map<String, Object>> updateZone(
            @PathVariable("id") Long id,
            @RequestBody Zone zone
    ) {
        Map<String, Object> response = new HashMap<>();
        try {
            if (id != null) {
                zoneService.updateZone(id, zone);
                response.put("message", "Zone a été modifiée");
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
            else {
                Map<String, Object> error = new HashMap<>();
                error.put("message","Zone n'a pas été modifiée" );
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
    public ResponseEntity<String> deleteZoneById(
            @PathVariable("id") Long id
    ) {
        try {
            if (id != null) {
                zoneService.deleteZone(id);
                return ResponseEntity.status(HttpStatus.OK).body("Zone a été supprimée");
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Zone n'a pas été supprimée");
            }
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("message : " + e.getMessage());
        }
    }
}
