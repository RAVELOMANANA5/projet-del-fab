package com.stage.service.posteService;

import com.stage.models.poste.Moniteur;

import java.util.Map;

public interface MoniteurService {
    void addMoniteur(Moniteur moniteur);
    Map<String, Object> getAllMoniteurs(int pageNo, int pageSize);
    Map<String, Object> findAllBySexe(Integer pageNo, Integer pageSize, String sexe);
    Map<String, Object> findMoniteurById(Long id);
    Map<String, Object> getMoniteursWithDetails(int pageNo, int pageSize);
    boolean updateMoniteur(Long id, Moniteur moniteur);
    boolean deleteMoniteur(Long id);
}
