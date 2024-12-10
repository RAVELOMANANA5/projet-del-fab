package com.stage.repository.posteRepository;

import com.stage.models.poste.ArchiveMoniteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchivedMoniteurRepository extends JpaRepository<ArchiveMoniteur, Long> {
}
