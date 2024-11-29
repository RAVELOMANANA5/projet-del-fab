package com.stage.service.scolaireService;

import com.stage.models.scolaire.Examen;

import java.util.Map;

public interface ExamenService {
    void addExamen(Examen examen);
    Map<String, Object> getAllExamens(int pageNo, int pageSize);
    Map<String, Object> findExamenById(Long id);
    boolean updateExamen(Long id, Examen examen);
    boolean deleteExamen(Long id);
}
