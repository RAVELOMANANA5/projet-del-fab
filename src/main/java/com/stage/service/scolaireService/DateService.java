package com.stage.service.scolaireService;

import com.stage.models.scolaire.Date;

import java.util.Map;

public interface DateService {
    void addDate(Date date);
    Map<String, Object> getAllDates(int pageNo, int pageSize);
    Map<String, Object> findDateById(Long id);
    boolean updateDate(Long id, Date date);
    boolean deleteDate(Long id);
}
