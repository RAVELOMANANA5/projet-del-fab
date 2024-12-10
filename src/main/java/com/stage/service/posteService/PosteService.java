package com.stage.service.posteService;

import com.itextpdf.text.DocumentException;
import com.stage.models.poste.Poste;

import java.io.IOException;
import java.util.Map;

public interface PosteService {
    void addPoste(Poste poste);
    Map<String, Object> getAllPostes(int pageNo, int pageSize);
    Map<String, Object> findPosteById(Long id);
    byte[] generatePostePdf(Long id) throws DocumentException, IOException;
    Map<String, Object> findAllByAnOuverPoste(int pageNo, int pageSize);
    Map<String, Object> getCountElevesByClass(Integer pageNo, Integer pageSize);
    Map<String, Object> getCountPostesByClass(Integer pageNo, Integer pageSize);
    Map<String, Object> getMorningAndAfternoonPosts();
    boolean updatePoste(Long id, Poste poste);
    boolean deletePoste(Long id);
}

