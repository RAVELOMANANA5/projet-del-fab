package com.stage.service.posteService;

import com.stage.models.poste.Cisco;

import java.util.Map;

public interface CiscoService {
    void addCisco(Cisco cisco);
   Map<String, Object> getAllCisco(int pageNo, int pageSize);
   Map<String, Object> findCiscoById(Long id);
   boolean updateCisco(Long id, Cisco cisco);
   boolean deleteCisco(Long id);
}
