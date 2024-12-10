package com.stage.repository.scolaireRepository;

import com.stage.models.scolaire.Eleve;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EleveRepository extends JpaRepository<Eleve, Long> {
    Eleve findById(long id);

    @Query(value = "SELECT e FROM Eleve e WHERE e.archived = false")
    Page<Eleve> findAllActiveEleves(Pageable pageable);
}
