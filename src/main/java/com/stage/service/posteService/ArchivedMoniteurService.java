package com.stage.service.posteService;

import java.util.Map;

public interface ArchivedMoniteurService {
    void archiverMoniteur(Long id);
    Map<String, Object> getAllArchivedMoniteurs(int pageNo, int pageSize);
}
