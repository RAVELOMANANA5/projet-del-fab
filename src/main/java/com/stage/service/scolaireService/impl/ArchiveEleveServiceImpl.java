package com.stage.service.scolaireService.impl;

import com.stage.exception.ResourceAlreadyExistsException;
import com.stage.exception.ResourceNotFoundException;
import com.stage.models.scolaire.ArchiveEleve;
import com.stage.models.scolaire.Eleve;
import com.stage.repository.scolaireRepository.ArchiveEleveRepository;
import com.stage.repository.scolaireRepository.EleveRepository;
import com.stage.service.scolaireService.ArchiveEleveService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ArchiveEleveServiceImpl implements ArchiveEleveService {
    private final ArchiveEleveRepository archiveEleveRepository;
    private final EleveRepository eleveRepository;

    public ArchiveEleveServiceImpl(ArchiveEleveRepository archiveEleveRepository, EleveRepository eleveRepository) {
        this.archiveEleveRepository = archiveEleveRepository;
        this.eleveRepository = eleveRepository;
    }

    /**
     * @param eleveId
     */
    @Override
    public void addEleve(Long eleveId) {
        Eleve eleve = eleveRepository.findById(eleveId)
                .orElseThrow(() -> new ResourceNotFoundException("Élève non trouvé"));

        if (eleve.isArchived()) {
            throw new ResourceAlreadyExistsException("L'élève est déjà archivé.");
        }

        ArchiveEleve archive = new ArchiveEleve();
        archive.setArchiveDate(LocalDate.now());
        archive.setEleve(eleve);

        // Mettre à jour le statut de l'élève
        eleve.setArchived(true);
        eleve.setArchive(archive);

        archiveEleveRepository.save(archive);
        eleveRepository.save(eleve);

    }
}
