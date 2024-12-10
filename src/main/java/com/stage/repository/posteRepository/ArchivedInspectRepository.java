package com.stage.repository.posteRepository;

import com.stage.models.poste.ArchiveInspect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchivedInspectRepository extends JpaRepository<ArchiveInspect, Long> {
}
