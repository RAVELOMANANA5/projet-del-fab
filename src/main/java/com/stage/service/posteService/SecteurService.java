package com.stage.service.posteService;

import com.stage.models.poste.Secteur;

import java.util.Map;

public interface SecteurService {
    void addSecteur(Secteur secteur);
    Map<String, Object> getSecteurs(int pageNo, int pageSize);
    Map<String, Object> findSecteurByNomZone(int pageNo, int pageSize, String nomZone);
    Map<String, Object> findSecteurById(Long id);
    boolean updateSecteur(Long id, Secteur secteur);
    boolean deleteSecteur(Long id);
}
