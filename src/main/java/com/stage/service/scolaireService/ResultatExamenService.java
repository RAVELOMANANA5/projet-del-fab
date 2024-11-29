package com.stage.service.scolaireService;

import com.stage.models.scolaire.ResultatExamen;

import java.util.Map;

public interface ResultatExamenService {
    void addResultatExamen(ResultatExamen resultatExamen);
    Map<String, Object> getAllResultatExamens(Integer pageNo, Integer pageSize);
    boolean updateResultatExamen(Long id, ResultatExamen resultatExamen);
    boolean deleteResultatExamen(Long id);
}
