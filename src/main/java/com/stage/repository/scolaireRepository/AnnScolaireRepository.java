package com.stage.repository.scolaireRepository;

import com.stage.models.scolaire.AnnScolaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Year;

@Repository
public interface AnnScolaireRepository extends JpaRepository<AnnScolaire, Long> {
    boolean existsByAnnDebut(Year annDebut);
    boolean existsByAnnFin(Year annFin);
}
