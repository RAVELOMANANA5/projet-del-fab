package com.stage.service.posteService.impl;

import com.stage.dtoMappers.posteMapper.SecteurDTOMapper;
import com.stage.dtos.posteDTOS.SecteurDTO;
import com.stage.exception.ResourceAlreadyExistsException;
import com.stage.exception.ResourceNotFoundException;
import com.stage.models.poste.Secteur;
import com.stage.repository.posteRepository.SecteurRepository;
import com.stage.service.posteService.SecteurService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SecteurServiceImpl implements SecteurService {

    private final SecteurRepository secteurRepository;
    private final SecteurDTOMapper secteurDTOMapper;

    public SecteurServiceImpl(SecteurRepository secteurRepository, SecteurDTOMapper secteurDTOMapper) {
        this.secteurRepository = secteurRepository;
        this.secteurDTOMapper = secteurDTOMapper;
    }

    /**
     * @param secteur
     * @return
     */
    @Override
    public void addSecteur(Secteur secteur) {
        Optional<Secteur> byNumSecteur = secteurRepository.findByNumSecteur(secteur.getNumSecteur());
        if (byNumSecteur.isPresent()) {
            throw new ResourceAlreadyExistsException("Numéro Secteur " + secteur.getNumSecteur() + " est déjà existe");
        }
        secteurRepository.save(secteur);
    }

    /**
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public Map<String, Object> getSecteurs(int pageNo, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
        Page<Secteur> page = secteurRepository.findAll(pageRequest);

        List<SecteurDTO> list = page.getContent()
                .stream()
                .map(secteurDTOMapper)
                .collect(Collectors.toList());

        Map<String, Object> result = new HashMap<>();
        result.put("secteurs", list);
        result.put("currentPage", page.getNumber());
        result.put("pageSize", page.getSize());
        result.put("totalPages", page.getTotalPages());
        result.put("totalElements", page.getTotalElements());
        return result;
    }

    /**
     * @param nomZone
     * @return
     */
    @Override
    public Map<String, Object> findSecteurByNomZone(int pageNo, int pageSize, String nomZone) {
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
        Page<Secteur> page = secteurRepository.findAll(pageRequest);

        List<SecteurDTO> list = page.getContent()
                .stream()
                .filter(secteur -> secteur.getZone().getNomZone().contains(nomZone))
                .map(secteurDTOMapper)
                .collect(Collectors.toList());

        long count = list.stream().count();

        Map<String, Object> result = new HashMap<>();
        result.put("secteurs", list);
        result.put("currentPage", page.getNumber());
        result.put("pageSize", page.getSize());
        result.put("totalPages", page.getTotalPages());
        result.put("count", count);
        result.put("totalElements", page.getTotalElements());
        return result;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Map<String, Object> findSecteurById(Long id) {
        SecteurDTO secteurDTO = secteurRepository.findById(id)
                .stream()
                .map(secteurDTOMapper)
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Secteur n'existe pas avec id : " + id));

        Map<String, Object> map = new HashMap<>();
        map.put("secteur", secteurDTO);

        return map;
    }

    /**
     * @param id
     * @param secteur
     * @return
     */
    @Override
    public boolean updateSecteur(Long id, Secteur secteur) {
        Secteur byId = secteurRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Secteur " + id + " n'existe pas"));
        byId.setNomSecteur(secteur.getNomSecteur());
        byId.setZone(secteur.getZone());
        byId.setPostes(secteur.getPostes());
        secteurRepository.save(byId);
        return true;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public boolean deleteSecteur(Long id) {
        secteurRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Secteur " + id + " n'existe pas"));
        secteurRepository.deleteById(id);
        return true;
    }

}
