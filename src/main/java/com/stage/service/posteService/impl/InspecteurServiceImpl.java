package com.stage.service.posteService.impl;

import com.stage.dtoMappers.posteMapper.InspecteurDTOMapper;
import com.stage.dtoMappers.posteMapper.InspecteurDetailDTOMapper;
import com.stage.dtos.posteDTOS.InspecteurDTO;
import com.stage.dtos.posteDTOS.InspecteurDetailDTO;
import com.stage.exception.ResourceAlreadyExistsException;
import com.stage.exception.ResourceNotFoundException;
import com.stage.models.poste.Inspecteur;
import com.stage.repository.posteRepository.InspecteurRepository;
import com.stage.service.posteService.InspecteurService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InspecteurServiceImpl implements InspecteurService {

    private final InspecteurRepository inspecteurRepository;
    private final InspecteurDTOMapper inspecteurDTOMapper;
    private final InspecteurDetailDTOMapper inspecteurDetailDTOMapper;

    public InspecteurServiceImpl(InspecteurRepository inspecteurRepository,
                                 InspecteurDTOMapper inspecteurDTOMapper,
                                 InspecteurDetailDTOMapper inspecteurDetailDTOMapper
    ) {
        this.inspecteurRepository = inspecteurRepository;
        this.inspecteurDTOMapper = inspecteurDTOMapper;
        this.inspecteurDetailDTOMapper = inspecteurDetailDTOMapper;
    }

    /**
     * @param inspecteur
     */
    @Override
    public void addInspecteur(Inspecteur inspecteur) {
        Optional<Inspecteur> byMatrInspect = inspecteurRepository.findByMatrInspect(inspecteur.getMatrInspect());
        if (byMatrInspect.isPresent()) {
            throw new ResourceAlreadyExistsException("Matricule inspecteur " + inspecteur.getMatrInspect() + " est déjà existe");
        }
        inspecteurRepository.save(inspecteur);
    }

    /**
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public Map<String, Object> getAllInspecteurs(int pageNo, int pageSize) {
        Page<Inspecteur> inspecteurs = inspecteurRepository.findAll(PageRequest.of(pageNo, pageSize));

        List<InspecteurDTO> inspecteurDTOList = inspecteurs.getContent()
                .stream()
                .map(inspecteurDTOMapper)
                .collect(Collectors.toList());

        Map<String, Object> result = new HashMap<>();
        result.put("inspecteurs", inspecteurDTOList);
        result.put("currentPage", inspecteurs.getNumber());
        result.put("totalPages", inspecteurs.getTotalPages());

        return result;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Map<String, Object> getInspecteurById(Long id) {

        InspecteurDTO inspecteurDTO = inspecteurRepository.findById(id)
                .stream()
                .map(inspecteurDTOMapper)
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Inspecteur " + id + " n'existe pas"));

        Map<String, Object> result = new HashMap<>();
        result.put("inspecteur", inspecteurDTO);
        return result;
    }

    /**
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public Map<String, Object> getInspecteursWithDetails(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Inspecteur> inspecteurs = inspecteurRepository.findAll(pageable);

        List<InspecteurDetailDTO> inspecteurDetailDTOS = inspecteurs.getContent()
                .stream()
                .map(inspecteurDetailDTOMapper)
                .collect(Collectors.toList());

        Map<String, Object> result = new HashMap<>();
        result.put("inspecteurs", inspecteurDetailDTOS);
        result.put("currentPage", inspecteurs.getNumber());
        result.put("totalPages", inspecteurs.getTotalPages());
        result.put("totalElements", inspecteurs.getTotalElements());
        return result;
    }

    /**
     * @param id
     * @param inspecteur
     * @return
     */
    @Override
    public boolean updateInspecteur(Long id, Inspecteur inspecteur) {
        Inspecteur byID = inspecteurRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inspecteur " + id + " n'existe pas"));
        byID.setEtatCivil(inspecteur.getEtatCivil());
        byID.setDateEntrerVozama(inspecteur.getDateEntrerVozama());
        byID.setTempsTransportVisitPoste(inspecteur.getTempsTransportVisitPoste());
        byID.setTempsTransportFormation(inspecteur.getTempsTransportFormation());
        byID.setNom(inspecteur.getNom());
        byID.setPrenom(inspecteur.getPrenom());
        byID.setDateNaiss(inspecteur.getDateNaiss());
        byID.setLieuDeNaiss(inspecteur.getLieuDeNaiss());
        byID.setAdresse(inspecteur.getAdresse());
        byID.setProfession(inspecteur.getProfession());
        byID.setNbrEnfant(inspecteur.getNbrEnfant());
        byID.setNbrEnfantCharger(inspecteur.getNbrEnfantCharger());
        byID.setEtudePrim(inspecteur.getEtudePrim());
        byID.setLieuEtudePrim(inspecteur.getLieuEtudePrim());
        byID.setEtudeSecond(inspecteur.getEtudeSecond());
        byID.setLieuEtudeSecond(inspecteur.getLieuEtudeSecond());
        byID.setDernierDiplome(inspecteur.getDernierDiplome());
        inspecteurRepository.save(byID);
        return true;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public boolean deleteInspecteur(Long id) {
        Inspecteur deleteById = inspecteurRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inspecteur " + id + " n'existe pas"));
        inspecteurRepository.deleteById(deleteById.getId());
        return true;
    }
}
