package com.stage.repository.scolaireRepository;

import com.stage.models.scolaire.ArchiveEleve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchiveEleveRepository extends JpaRepository<ArchiveEleve, Long> {
}
