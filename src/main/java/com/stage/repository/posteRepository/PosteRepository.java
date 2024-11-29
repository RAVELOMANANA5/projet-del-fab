package com.stage.repository.posteRepository;

import com.stage.models.poste.Poste;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PosteRepository extends JpaRepository<Poste, Long> {

    Optional<Poste> findByNumPoste(String numPoste);

    @Query(value = "SELECT p.anOuverPoste AS year, COUNT(p) AS postCount FROM Poste p GROUP BY p.anOuverPoste")
    Page<Object[]> findPostsPerYear(Pageable pageable);

    @Query("SELECT e.classe, COUNT(p) AS postCount FROM Poste p INNER JOIN p.eleves e GROUP BY e.classe")
    Page<Object[]> eleveCountByClass(Pageable pageable);

    @Query("SELECT e.classe, COUNT(DISTINCT p.id) AS postCount FROM Poste p INNER JOIN p.eleves e GROUP BY e.classe")
    Page<Object[]> countPostesByClass(Pageable pageable);
/*
    @Query("SELECT COUNT(p), GROUP_CONCAT(p.nomPoste) " +
            "FROM Poste p " +
            "WHERE p.heureOuverture " +
            "BETWEEN :morningStart " +
            "AND :morningEnd " +
            "AND p.heureFermeture > :morningEnd " +
            "UNION ALL SELECT COUNT(p), GROUP_CONCAT(p.nomPoste) " +
            "FROM Poste p " +
            "WHERE p.heureOuverture " +
            "BETWEEN :afternoonStart " +
            "AND :afternoonEnd " +
            "AND p.heureFermeture > :afternoonEnd"
            )'08:00:00'
 */
    @Query("SELECT COUNT(p), GROUP_CONCAT(p.nomPoste) " +
            "FROM Poste p WHERE '08:00:00' " +
            "BETWEEN :morningStart AND :morningEnd AND '12:00:00' > :morningEnd " +
            "UNION ALL SELECT COUNT(p), GROUP_CONCAT(p.nomPoste) FROM Poste p WHERE '13:00:00' " +
            "BETWEEN :afternoonStart AND :afternoonEnd AND '17:00:00' > :afternoonEnd"
    )
    List<Object[]> findMorningAndAfternoonPosts(
            LocalTime morningStart, LocalTime morningEnd,
            LocalTime afternoonStart, LocalTime afternoonEnd
    );
}
