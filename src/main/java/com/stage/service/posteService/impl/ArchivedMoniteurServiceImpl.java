package com.stage.service.posteService.impl;

import com.stage.dtoMappers.posteMapper.ArchivedMoniteurDTOMapper;
import com.stage.dtoMappers.posteMapper.MoniteurDetailDTOMapper;
import com.stage.dtos.posteDTOS.ArchivedMoniteurDTO;
import com.stage.dtos.posteDTOS.MoniteurDTO;
import com.stage.dtos.posteDTOS.MoniteurDetailDTO;
import com.stage.models.poste.ArchiveMoniteur;
import com.stage.models.poste.Moniteur;
import com.stage.repository.posteRepository.ArchivedMoniteurRepository;
import com.stage.repository.posteRepository.MoniteurRepository;
import com.stage.service.posteService.ArchivedMoniteurService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ArchivedMoniteurServiceImpl implements ArchivedMoniteurService {

    private final ArchivedMoniteurRepository archivedMoniteurRepository;
    private final MoniteurRepository moniteurRepository;
    private final ArchivedMoniteurDTOMapper archivedMoniteurDTOMapper;

    public ArchivedMoniteurServiceImpl(
            ArchivedMoniteurRepository archivedMoniteurRepository,
            MoniteurRepository moniteurRepository,
            ArchivedMoniteurDTOMapper archivedMoniteurDTOMapper) {
        this.archivedMoniteurRepository = archivedMoniteurRepository;
        this.moniteurRepository = moniteurRepository;
        this.archivedMoniteurDTOMapper = archivedMoniteurDTOMapper;
    }

    /**
     * @param id
     */
    @Override
    public void archiverMoniteur(Long id) {
        Moniteur byId = moniteurRepository.findById(id).get();

        // Créer une archive pour moniteur
        ArchiveMoniteur archiveMoniteur = new ArchiveMoniteur();
        archiveMoniteur.setArchiveDateMon(LocalDate.now());
        archiveMoniteur.setMoniteur(byId);

        // Mettre à jour le statut de moniteur
        byId.setArchived(true);
        byId.setArchiveMoniteur(archiveMoniteur);

        archivedMoniteurRepository.save(archiveMoniteur);
        moniteurRepository.save(byId);
    }

    /**
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public Map<String, Object> getAllArchivedMoniteurs(int pageNo, int pageSize) {
        Page<ArchiveMoniteur> moniteurs = archivedMoniteurRepository.findAll(PageRequest.of(pageNo, pageSize));

        List<ArchivedMoniteurDTO> moniteurList = moniteurs.getContent()
                .stream()
                .sorted(Comparator.comparing(ArchiveMoniteur::getArchiveDateMon))
                .map(archivedMoniteurDTOMapper)
                .collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("archiveMoniteur", moniteurList);
        response.put("currentPage", moniteurs.getNumber());
        response.put("size", moniteurs.getSize());
        response.put("totalPages", moniteurs.getTotalPages());
        response.put("totalElements", moniteurs.getTotalElements());

        return response;
    }
}
