package com.stage.service.posteService;

import com.stage.models.poste.Inspecteur;

import java.util.Map;

public interface InspecteurService {
    void addInspecteur(Inspecteur inspecteur);
    Map<String, Object> getAllInspecteurs(int pageNo, int pageSize);
    Map<String, Object> getInspecteurById(Long id);
    Map<String, Object> getInspecteursWithDetails(int pageNo, int pageSize);
    boolean updateInspecteur(Long id, Inspecteur inspecteur);
    boolean deleteInspecteur(Long id);

}
