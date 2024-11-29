package com.stage.repository.posteRepository;

import com.stage.models.poste.Secteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SecteurRepository extends JpaRepository<Secteur, Long> {
    Optional<Secteur> findByNumSecteur(String numSecteur);
}
