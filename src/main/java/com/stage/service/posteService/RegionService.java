package com.stage.service.posteService;

import com.stage.models.poste.Region;

import java.util.Map;

public interface RegionService {
    void addRegion(Region region);
    Map<String, Object> getRegions(int pageNo, int pageSize);
    Map<String, Object> findRegionById(Long id);
    boolean updateRegion(Long id, Region region);
    boolean deleteRegion(Long id);
}
