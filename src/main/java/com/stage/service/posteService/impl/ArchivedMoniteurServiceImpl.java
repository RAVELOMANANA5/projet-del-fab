package com.stage.service.posteService.impl;

import com.stage.exception.ResourceNotFoundException;
import com.stage.models.poste.ArchiveMoniteur;
import com.stage.models.poste.Moniteur;
import com.stage.repository.posteRepository.ArchivedMoniteurRepository;
import com.stage.repository.posteRepository.MoniteurRepository;
import com.stage.service.posteService.ArchivedMoniteurService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class ArchivedMoniteurServiceImpl implements ArchivedMoniteurService {

    private final ArchivedMoniteurRepository archivedMoniteurRepository;
    private final MoniteurRepository moniteurRepository;

    public ArchivedMoniteurServiceImpl(
            ArchivedMoniteurRepository archivedMoniteurRepository, MoniteurRepository moniteurRepository) {
        this.archivedMoniteurRepository = archivedMoniteurRepository;
        this.moniteurRepository = moniteurRepository;
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
}
