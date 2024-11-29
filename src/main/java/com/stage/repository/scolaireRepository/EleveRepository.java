package com.stage.repository.scolaireRepository;

import com.stage.models.scolaire.Eleve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EleveRepository extends JpaRepository<Eleve, Long> {
}
