package com.stage.service.posteService;

import com.stage.models.poste.Commune;

import java.util.Map;

public interface CommuneService {
    void addCommune(Commune commune);
    Map<String, Object> getCommunes(int pageNo, int pageSize);
    Map<String, Object> findCommuneById(Long id);
    boolean updateCommune(Long id, Commune commune);
    boolean deleteCommune(Long id);
}
