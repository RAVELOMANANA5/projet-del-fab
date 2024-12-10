package com.stage.service.scolaireService.impl;

import com.stage.dtoMappers.scolaireMapper.AnnScolaireDTOMapper;
import com.stage.dtos.scolaireDTOS.AnnScolaireDTO;
import com.stage.dtos.scolaireDTOS.MatiereDTO;
import com.stage.exception.ResourceAlreadyExistsException;
import com.stage.exception.ResourceNotFoundException;
import com.stage.models.poste.Moniteur;
import com.stage.models.scolaire.AnnScolaire;
import com.stage.models.scolaire.Matiere;
import com.stage.repository.scolaireRepository.AnnScolaireRepository;
import com.stage.service.scolaireService.AnnScolaireService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnnScolaireServiceImpl implements AnnScolaireService {

    private final AnnScolaireRepository annScolaireRepository;
    private final AnnScolaireDTOMapper annScolaireDTOMapper;

    public AnnScolaireServiceImpl(
            AnnScolaireRepository annScolaireRepository, AnnScolaireDTOMapper annScolaireDTOMapper) {
        this.annScolaireRepository = annScolaireRepository;
        this.annScolaireDTOMapper = annScolaireDTOMapper;
    }

    /**
     * @param annScolaire
     */
    @Override
    public void addAnnScolaire(AnnScolaire annScolaire) {
//        Optional<AnnScolaire> byAnnDebut = annScolaireRepository.existsByAnnDebut(annScolaire.getAnnDebut());
//        Optional<AnnScolaire> byAnnFin = annScolaireRepository.existsByAnnFin(annScolaire.getAnnFin());
//        if (byAnnDebut.isPresent() && byAnnFin.isPresent()) {
//            throw new ResourceAlreadyExistsException("AnnScolaire est déjà existe");
//        }
        annScolaireRepository.save(annScolaire);
    }

    /**
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public Map<String, Object> getAllAnnScolaire(int pageNo, int pageSize) {
        Page<AnnScolaire> annScolaires = annScolaireRepository.findAll(PageRequest.of(pageNo, pageSize));

        List<AnnScolaireDTO> annScolaireDTOS = annScolaires.getContent()
                .stream()
                .map(annScolaireDTOMapper)
                .collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("annScolaire", annScolaireDTOS);
        response.put("currentPage", annScolaires.getNumber());
        response.put("size", annScolaires.getSize());
        response.put("totalPages", annScolaires.getTotalPages());
        return response;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Map<String, Object> findAnnScolaireById(Long id) {
        AnnScolaireDTO annScolaireDTO = annScolaireRepository.findById(id)
                .stream()
                .map(annScolaireDTOMapper)
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Année scolaire non trouvé"));

        Map<String, Object> response = new HashMap<>();
        response.put("annScolaire", annScolaireDTO);
        return response;
    }
}
