package com.stage.service.scolaireService;

import com.stage.models.scolaire.Matiere;

import java.util.Map;

public interface MatiereService {
    void addMatiere(Matiere matiere);
    Map<String, Object> getAllMatieres(int pageNo, int pageSize);
    Map<String, Object> findMatiereById(Long id);
    boolean updateMatiere(Long id, Matiere matiere);
    boolean deleteMatiere(Long id);
}
