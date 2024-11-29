package com.stage.repository.posteRepository;

import com.stage.models.poste.Moniteur;
import com.stage.models.poste.Poste;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MoniteurRepository extends JpaRepository<Moniteur, Long> {

    Optional<Moniteur> findByMatrMoniteur(String matrMoniteur);

    @Query("SELECT m.sexe, COUNT(DISTINCT m.id) AS postCount FROM Moniteur m GROUP BY m.sexe")
    Page<Object[]> countMoniteursBySexe(Pageable pageable);
}
