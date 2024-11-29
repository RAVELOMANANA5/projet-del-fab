package com.stage.service.scolaireService.impl;

import com.stage.dtoMappers.scolaireMapper.ExamenDTOMapper;
import com.stage.dtos.scolaireDTOS.ExamenDTO;
import com.stage.exception.ResourceNotFoundException;
import com.stage.models.scolaire.Examen;
import com.stage.repository.scolaireRepository.ExamenRepository;
import com.stage.service.scolaireService.ExamenService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ExamenServiceImpl implements ExamenService {

    private final ExamenRepository examenRepository;
    private final ExamenDTOMapper examenDTOMapper;

    public ExamenServiceImpl(ExamenRepository examenRepository, ExamenDTOMapper examenDTOMapper) {
        this.examenRepository = examenRepository;
        this.examenDTOMapper = examenDTOMapper;
    }

    /**
     * @param examen
     */
    @Override
    public void addExamen(Examen examen) {
        examenRepository.save(examen);
    }

    /**
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public Map<String, Object> getAllExamens(int pageNo, int pageSize) {
        Page<Examen> examens = examenRepository.findAll(PageRequest.of(pageNo, pageSize));

        List<ExamenDTO> examenDTOS = examens.getContent()
                .stream()
                .map(examenDTOMapper)
                .collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("eleves", examenDTOS);
        response.put("currentPage", examens.getNumber());
        response.put("size", examens.getSize());
        response.put("totalPages", examens.getTotalPages());
        response.put("totalElements", examens.getTotalElements());

        return response;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Map<String, Object> findExamenById(Long id) {
        ExamenDTO examenDTO = examenRepository.findById(id)
                .stream()
                .map(examenDTOMapper)
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Examen n'existe pas avec id: " + id));

        Map<String, Object> response = new HashMap<>();
        response.put("examen", examenDTO);
        return response;
    }

    /**
     * @param id
     * @param examen
     * @return
     */
    @Override
    public boolean updateExamen(Long id, Examen examen) {
        Examen updateExamen = examenRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Examen n'existe pas avec id: " + id));
        updateExamen.setDateExamenDt(examen.getDateExamenDt());
        updateExamen.setTrimestre(examen.getTrimestre());
        examenRepository.save(updateExamen);
        return true;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public boolean deleteExamen(Long id) {
        Examen examen = examenRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Examen n'existe pas avec id: " + id));
        examenRepository.delete(examen);
        return true;
    }
}
