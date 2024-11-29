package com.stage.service.scolaireService;

import com.stage.models.scolaire.Eleve;

import java.util.Map;

public interface EleveService {
    void addEleve(Eleve eleve);
    Map<String, Object> getAllEleves(int pageNo, int pageSize);
    Map<String, Object> findEleveById(Long id);
    boolean updateEleve(Long id, Eleve eleve);
    boolean deleteEleve(Long id);
}
