package com.stage.service.scolaireService.impl;

import com.stage.dtoMappers.scolaireMapper.ResultatExamenDTOMapper;
import com.stage.dtos.scolaireDTOS.ResultatExamenDTO;
import com.stage.exception.ResourceNotFoundException;
import com.stage.models.scolaire.ResultatExamen;
import com.stage.repository.scolaireRepository.ResultatExamenRepository;
import com.stage.service.scolaireService.ResultatExamenService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ResultatExamenServiceImpl implements ResultatExamenService {

    private final ResultatExamenRepository resultatExamenRepository;
    private final ResultatExamenDTOMapper resultatExamenDTOMapper;

    public ResultatExamenServiceImpl(
            ResultatExamenRepository resultatExamenRepository,
            ResultatExamenDTOMapper resultatExamenDTOMapper
    ) {
        this.resultatExamenRepository = resultatExamenRepository;
        this.resultatExamenDTOMapper = resultatExamenDTOMapper;
    }

    /**
     * @param resultatExamen
     */
    @Override
    public void addResultatExamen(ResultatExamen resultatExamen) {
        resultatExamenRepository.save(resultatExamen);
    }

    /**
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public Map<String, Object> getAllResultatExamens(Integer pageNo, Integer pageSize) {
        Page<ResultatExamen> resultatExamen = resultatExamenRepository.findAll(PageRequest.of(pageNo, pageSize));

        List<ResultatExamenDTO> resultatExamenDTOS = resultatExamen.getContent()
                .stream()
                .map(resultatExamenDTOMapper)
                .collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("resultat", resultatExamenDTOS);
        response.put("currentPage", resultatExamen.getNumber());
        response.put("size", resultatExamen.getSize());
        response.put("totalPages", resultatExamen.getTotalPages());
        response.put("totalElements", resultatExamen.getTotalElements());

        return response;
    }

    /**
     * @param id
     * @param resultatExamen
     * @return
     */
    @Override
    public boolean updateResultatExamen(Long id, ResultatExamen resultatExamen) {
        ResultatExamen updateResultatExamen = resultatExamenRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resultat examen n'existe pas avec id: " + id));

        updateResultatExamen.setEleve(resultatExamen.getEleve());
        updateResultatExamen.setMatiere(resultatExamen.getMatiere());
        updateResultatExamen.setExamen(resultatExamen.getExamen());
        updateResultatExamen.setNote(resultatExamen.getNote());
        resultatExamenRepository.save(updateResultatExamen);
        return true;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public boolean deleteResultatExamen(Long id) {
        ResultatExamen resultatExamen = resultatExamenRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resultat examen n'existe pas avec id: " + id));
        resultatExamenRepository.delete(resultatExamen);
        return true;
    }

    public Map<String, Object> findElevesWithMoyenneAndRang() {
        List<ResultatExamen> elevesWithMoyenneAndRang = resultatExamenRepository.findElevesWithMoyenneAndRang();

        Map<String, Object> response = new HashMap<>();
        response.put("resultat", elevesWithMoyenneAndRang);

        return response;
    }
}
