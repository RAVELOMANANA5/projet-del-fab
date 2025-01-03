package com.stage.repository.posteRepository;

import com.stage.models.poste.Commune;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommuneRepository extends JpaRepository<Commune, Long> {
}
