package com.stage.service.scolaireService;

import com.stage.models.scolaire.AnnScolaire;

import java.util.Map;

public interface AnnScolaireService {
    void addAnnScolaire(AnnScolaire annScolaire);
    Map<String, Object> getAllAnnScolaire(int pageNo, int pageSize);
    Map<String, Object> findAnnScolaireById(Long id);
}
