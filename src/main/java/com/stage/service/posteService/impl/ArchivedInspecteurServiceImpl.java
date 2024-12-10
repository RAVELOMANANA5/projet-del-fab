package com.stage.service.posteService.impl;

import com.stage.models.poste.ArchiveInspect;
import com.stage.models.poste.ArchiveMoniteur;
import com.stage.models.poste.Inspecteur;
import com.stage.repository.posteRepository.ArchivedInspectRepository;
import com.stage.repository.posteRepository.InspecteurRepository;
import com.stage.service.posteService.ArchivedInspectService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ArchivedInspecteurServiceImpl implements ArchivedInspectService {

    private final ArchivedInspectRepository archivedInspectRepository;
    private final InspecteurRepository inspecteurRepository;

    public ArchivedInspecteurServiceImpl(
            ArchivedInspectRepository archivedInspectRepository, InspecteurRepository inspecteurRepository) {
        this.archivedInspectRepository = archivedInspectRepository;
        this.inspecteurRepository = inspecteurRepository;
    }

    /**
     * @param id
     */
    @Override
    public void archivedInspecteur(Long id) {
        Inspecteur inspecteur = inspecteurRepository.findById(id).get();

        // Créer une archive pour moniteur
        ArchiveInspect archiveInspect = new ArchiveInspect();
        archiveInspect.setArchiveDateInspect(LocalDate.now());
        archiveInspect.setInspecteur(inspecteur);

        // Mettre à jour le statut de inspecteur
        inspecteur.setArchived(true);
        inspecteur.setArchiveinspect(archiveInspect);

        archivedInspectRepository.save(archiveInspect);
        inspecteurRepository.save(inspecteur);
    }
}
