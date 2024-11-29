package com.stage.service.posteService;

import com.stage.dtos.posteDTOS.ZoneDTO;
import com.stage.models.poste.Zone;

import java.util.Map;

public interface ZoneService {
    void addZone(Zone zone);
    Map<String, Object> getZones(int pageNo, int pageSize);
    Map<String, Object> findZonesByNomRegion(int pageNo, int pageSize, String region);
    ZoneDTO findZoneByID(Long id);
    boolean updateZone(Long id, Zone zone);
    boolean deleteZone(Long id);
}
