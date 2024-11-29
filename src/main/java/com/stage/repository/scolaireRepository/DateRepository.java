package com.stage.repository.scolaireRepository;

import com.stage.models.scolaire.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DateRepository extends JpaRepository<Date,Long> {
}
