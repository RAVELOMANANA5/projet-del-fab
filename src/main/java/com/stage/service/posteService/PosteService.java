package com.stage.service.posteService;

import com.stage.models.poste.Poste;

import java.util.Map;

public interface PosteService {
    void addPoste(Poste poste);
    Map<String, Object> getAllPostes(int pageNo, int pageSize);
    Map<String, Object> findPosteById(Long id);
    Map<String, Object> findPaginatedPostsByOpeningYear (int pageNo, int pageSize);
    Map<String, Object> getCountElevesByClass(Integer pageNo, Integer pageSize);
    Map<String, Object> getCountPostesByClass(Integer pageNo, Integer pageSize);
    Map<String, Object> getMorningAndAfternoonPosts();
    boolean updatePoste(Long id, Poste poste);
    boolean deletePoste(Long id);
}

