package com.stage.repository.posteRepository;

import com.stage.models.poste.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {

    Optional<Region> findByNumRegion(String numRegion);

}
