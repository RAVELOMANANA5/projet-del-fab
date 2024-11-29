package com.stage.service.scolaireService.impl;

import com.stage.dtoMappers.scolaireMapper.MatiereDTOMapper;
import com.stage.dtos.scolaireDTOS.MatiereDTO;
import com.stage.exception.ResourceNotFoundException;
import com.stage.models.scolaire.Matiere;
import com.stage.repository.scolaireRepository.MatiereRepository;
import com.stage.service.scolaireService.MatiereService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MatiereServiceImpl implements MatiereService {

    private final MatiereRepository matiereRepository;
    private final MatiereDTOMapper matiereDTOMapper;

    public MatiereServiceImpl(MatiereRepository matiereRepository, MatiereDTOMapper matiereDTOMapper) {
        this.matiereRepository = matiereRepository;
        this.matiereDTOMapper = matiereDTOMapper;
    }

    /**
     * @param matiere
     */
    @Override
    public void addMatiere(Matiere matiere) {
        matiereRepository.save(matiere);
    }

    /**
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public Map<String, Object> getAllMatieres(int pageNo, int pageSize) {
        Page<Matiere> matieres = matiereRepository.findAll(PageRequest.of(pageNo, pageSize));

        List<MatiereDTO> matiereDTOS = matieres.getContent()
                .stream()
                .map(matiereDTOMapper)
                .collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("eleves", matiereDTOS);
        response.put("currentPage", matieres.getNumber());
        response.put("size", matieres.getSize());
        response.put("totalPages", matieres.getTotalPages());
        response.put("totalElements", matieres.getTotalElements());

        return response;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Map<String, Object> findMatiereById(Long id) {
        MatiereDTO matiereDTO = matiereRepository.findById(id)
                .stream()
                .map(matiereDTOMapper)
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Matiere n'existe pas avec id: " + id));

        Map<String, Object> response = new HashMap<>();
        response.put("eleve", matiereDTO);
        return response;
    }

    /**
     * @param id
     * @param matiere
     * @return
     */
    @Override
    public boolean updateMatiere(Long id, Matiere matiere) {
        Matiere upadateMatiere = matiereRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Matiere n'existe pas avec id: " + id));

        upadateMatiere.setNomMatiere(matiere.getNomMatiere());
        upadateMatiere.setCoefficient(matiere.getCoefficient());
        upadateMatiere.setExamens(matiere.getExamens());
        matiereRepository.save(upadateMatiere);
        return true;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public boolean deleteMatiere(Long id) {
        Matiere matiere = matiereRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Matiere n'existe pas avec id: " + id));
        matiereRepository.delete(matiere);
        return true;
    }
}
