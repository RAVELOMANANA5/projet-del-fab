package com.stage.service.scolaireService.impl;

import com.stage.dtoMappers.scolaireMapper.DateDTOMapper;
import com.stage.dtos.scolaireDTOS.DateDTO;
import com.stage.exception.ResourceNotFoundException;
import com.stage.models.scolaire.Date;
import com.stage.repository.scolaireRepository.DateRepository;
import com.stage.service.scolaireService.DateService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DateServiceImpl implements DateService {

    private final DateRepository dateRepository;
    private final DateDTOMapper dateDTOMapper;

    public DateServiceImpl(DateRepository dateRepository, DateDTOMapper dateDTOMapper) {
        this.dateRepository = dateRepository;
        this.dateDTOMapper = dateDTOMapper;
    }

    /**
     * @param date
     */
    @Override
    public void addDate(Date date) {
        dateRepository.save(date);
    }

    /**
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public Map<String, Object> getAllDates(int pageNo, int pageSize) {
        Page<Date> dates = dateRepository.findAll(PageRequest.of(pageNo, pageSize));

        List<DateDTO> dateDTOS = dates.getContent()
                .stream()
                .map(dateDTOMapper)
                .collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("eleves", dateDTOS);
        response.put("currentPage", dates.getNumber());
        response.put("size", dates.getSize());
        response.put("totalPages", dates.getTotalPages());
        response.put("totalElements", dates.getTotalElements());

        return response;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Map<String, Object> findDateById(Long id) {
        DateDTO dateDTO = dateRepository.findById(id)
                .stream()
                .map(dateDTOMapper)
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Date n'existe pas avec id : " + id));

        Map<String, Object> response = new HashMap<>();
        response.put("date", dateDTO);
        return response;
    }

    /**
     * @param id
     * @param date
     * @return
     */
    @Override
    public boolean updateDate(Long id, Date date) {
        Date upadateDate = dateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Date n'existe pas avec id : " + id));
        upadateDate.setDateAbsence(date.getDateAbsence());
        upadateDate.setEleves(date.getEleves());
        upadateDate.setMotif(date.getMotif());
        dateRepository.save(upadateDate);
        return true;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public boolean deleteDate(Long id) {
        Date date = dateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Date n'existe pas avec id : " + id));
        dateRepository.delete(date);
        return true;
    }
}
