package com.stage.service.posteService.impl;

import com.stage.dtoMappers.posteMapper.MoniteurDTOMapper;
import com.stage.dtoMappers.posteMapper.MoniteurDetailDTOMapper;
import com.stage.dtos.posteDTOS.MoniteurDTO;
import com.stage.dtos.posteDTOS.MoniteurDetailDTO;
import com.stage.exception.ResourceAlreadyExistsException;
import com.stage.exception.ResourceNotFoundException;
import com.stage.models.poste.Moniteur;
import com.stage.repository.posteRepository.MoniteurRepository;
import com.stage.service.posteService.MoniteurService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MoniteurServiceImpl implements MoniteurService {

    private final MoniteurRepository moniteurRepository;
    private final MoniteurDTOMapper moniteurDTOMapper;
    private final MoniteurDetailDTOMapper moniteurDetailDTOMapper;


    public MoniteurServiceImpl(
            MoniteurRepository MoniteurRepository,
            MoniteurDTOMapper moniteurDTOMapper,
            MoniteurDetailDTOMapper moniteurDetailDTOMapper


    ) {
        this.moniteurRepository = MoniteurRepository;
        this.moniteurDTOMapper = moniteurDTOMapper;
        this.moniteurDetailDTOMapper = moniteurDetailDTOMapper;
    }

    /**
     * @param moniteur
     */
    @Override
    public void addMoniteur(Moniteur moniteur) {
        Optional<Moniteur> byMatrMoniteur = moniteurRepository.findByMatrMoniteur(moniteur.getMatrMoniteur());
        if (byMatrMoniteur.isPresent()) {
            throw new ResourceAlreadyExistsException("Matricule " + moniteur.getMatrMoniteur() + " est déjà existe");
        }
        moniteur.setArchived(false);
        moniteurRepository.save(moniteur);
    }

    /**
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public Map<String, Object> getAllMoniteurs(int pageNo, int pageSize) {
        Page<Moniteur> moniteurs = moniteurRepository.findAll(PageRequest.of(pageNo, pageSize));

        List<MoniteurDTO> moniteurList = moniteurs.getContent()
                .stream()
                .filter(moniteur -> moniteur.isArchived() == false)
                .sorted(Comparator.comparing(Moniteur::getNom))
                .map(moniteurDTOMapper)
                .collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("moniteurs", moniteurList);
        response.put("currentPage", moniteurs.getNumber());
        response.put("size", moniteurs.getSize());
        response.put("totalPages", moniteurs.getTotalPages());
        response.put("totalElements", moniteurs.getTotalElements());

        return response;
    }

    /**
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public Map<String, Object> findAllBySexe(Integer pageNo, Integer pageSize, String sexe) {
        Page<Moniteur> moniteurs = moniteurRepository.findAllBySexe(PageRequest.of(pageNo, pageSize), sexe);

        List<MoniteurDTO> moniteurList = moniteurs.getContent()
                .stream()
                .filter(moniteur -> moniteur.isArchived() == false)
                .sorted(Comparator.comparing(Moniteur::getNom))
                .map(moniteurDTOMapper)
                .collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("moniteurs", moniteurList);
        response.put("currentPage", moniteurs.getNumber());
        response.put("size", moniteurs.getSize());
        response.put("totalPages", moniteurs.getTotalPages());
        response.put("totalElements", moniteurs.getTotalElements());

        return response;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Map<String, Object> findMoniteurById(Long id) {
        MoniteurDTO moniteurDTO = moniteurRepository.findById(id)
                .stream()
                .filter(moniteur -> moniteur.isArchived() == false)
                .map(moniteurDTOMapper)
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Moniteur " + id + " n'existe pas"));

        Map<String, Object> response = new HashMap<>();
        response.put("moniteur", moniteurDTO);
        return response;
    }

    /**
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public Map<String, Object> getMoniteursWithDetails(int pageNo, int pageSize) {
          Pageable pageable = PageRequest.of(pageNo, pageSize);
          Page<Moniteur> all = moniteurRepository.findAll(pageable);

          List<MoniteurDetailDTO> content = all.getContent()
                  .stream()
                  .filter(moniteur -> moniteur.isArchived() == false)
                  .map(moniteurDetailDTOMapper)
                  .collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("moniteurs", content);
        response.put("currentPage", all.getNumber());
        response.put("size", all.getSize());
        response.put("totalPages", all.getTotalPages());
        response.put("totalElements", all.getTotalElements());

        return response;

    }

    /**
     * @param id
     * @param moniteur
     * @return
     */
    @Override
    public boolean updateMoniteur(Long id, Moniteur moniteur) {
        Moniteur byId = moniteurRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Moniteur " + id + " n'existe pas"));
        byId.setAnEntrerMon(moniteur.getAnEntrerMon());
        byId.setCodeSecteur(moniteur.getCodeSecteur());
        byId.setHeure(moniteur.getHeure());
        byId.setNom(moniteur.getNom());
        byId.setPrenom(moniteur.getPrenom());
        byId.setDateNaiss(moniteur.getDateNaiss());
        byId.setLieuDeNaiss(moniteur.getLieuDeNaiss());
        byId.setAdresse(moniteur.getAdresse());
        byId.setProfession(moniteur.getProfession());
        byId.setNbrEnfant(moniteur.getNbrEnfant());
        byId.setNbrEnfantCharger(moniteur.getNbrEnfantCharger());
        byId.setEtudePrim(moniteur.getEtudePrim());
        byId.setLieuEtudePrim(moniteur.getLieuEtudePrim());
        byId.setEtudeSecond(moniteur.getEtudeSecond());
        byId.setLieuEtudeSecond(moniteur.getLieuEtudeSecond());
        byId.setDernierDiplome(moniteur.getDernierDiplome());
        byId.setArchived(false);
        moniteurRepository.save(byId);
        return true;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public boolean deleteMoniteur(Long id) {
        moniteurRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Moniteur " + id + " n'existe pas"));
        moniteurRepository.deleteById(id);
        return true;
    }
}
