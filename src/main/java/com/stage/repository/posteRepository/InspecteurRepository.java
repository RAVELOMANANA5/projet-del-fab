package com.stage.repository.posteRepository;

import com.stage.models.poste.Inspecteur;
import com.stage.models.poste.Moniteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InspecteurRepository extends JpaRepository<Inspecteur, Long> {
    Optional<Inspecteur> findByMatrInspect(String matrInspect);
}
