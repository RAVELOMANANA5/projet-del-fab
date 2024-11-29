package com.stage.repository.posteRepository;

import com.stage.models.poste.Cisco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CiscoRepository extends JpaRepository<Cisco, Long> {
}
