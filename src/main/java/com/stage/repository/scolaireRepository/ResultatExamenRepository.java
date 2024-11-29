package com.stage.repository.scolaireRepository;

import com.stage.models.scolaire.ResultatExamen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultatExamenRepository extends JpaRepository<ResultatExamen, Long> {
    @Query(value = """
            WITH Moyennes AS (
                SELECT 
                    e.id AS eleve_id,
                    e.nom_eleve,
                    SUM(re.note * m.coefficient) / SUM(m.coefficient) AS moyenne
                FROM 
                    eleve e
                JOIN  
                    eleve_matiere_examen re ON e.id = re.eleve_id
                JOIN 
                    matiere m ON re.matiere_id = m.id
                GROUP BY 
                    e.id, e.nom_eleve
            ), Classement AS (
                SELECT 
                    eleve_id,
                    nom_eleve,
                    moyenne,
                    RANK() OVER (ORDER BY moyenne DESC) AS rang,
                    CASE 
                        WHEN moyenne >= 10 THEN 'Admis'
                        ELSE 'Redouble'
                    END AS statut
                FROM 
                    Moyennes
            )
            SELECT 
                eleve_id,
                nom_eleve,
                moyenne,
                rang,
                statut
            FROM 
                Classement
            ORDER BY 
                rang
            """, nativeQuery = true)
    List<ResultatExamen> findElevesWithMoyenneAndRang();
}
