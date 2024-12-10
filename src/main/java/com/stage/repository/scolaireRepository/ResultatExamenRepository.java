package com.stage.repository.scolaireRepository;

import com.stage.dtos.scolaireDTOS.ResultatParTrimestreDTO;
import com.stage.models.scolaire.ResultatExamen;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

    @Query(value = "" +
            "WITH moyennes_eleves AS (\n" +
            "  SELECT\n" +
            "    e.nom_eleve,\n" +
            "    e.prenom_eleve,\n" +
            "\te.classe,\n" +
            "    ex.trimestre,\n" +
            "\tpo.num_poste,\n" +
            "\tpo.nom_poste,\n" +
            "\tan.ann_debut,\n" +
            "\tan.ann_fin,\n" +
            "\tROUND(SUM(mee.note * m.coefficient) / SUM(m.coefficient), 2) AS moyenne\n" +
            "  FROM\n" +
            "    eleve e\n" +
            "  INNER JOIN\n" +
            "    eleve_matiere_examen mee ON e.id = mee.eleve_id\n" +
            "  INNER JOIN\n" +
            "    examen ex ON mee.examen_id = ex.id\n" +
            "  INNER JOIN\n" +
            "    matiere m ON mee.matiere_id = m.id\n" +
            "  INNER JOIN \n" +
            "    poste po ON e.poste_id = po.id\n" +
            "  INNER JOIN \n" +
            "    ann_scolaire an ON e.ann_scolaire_id = an.id\n" +
            "  GROUP BY\n" +
            "    e.nom_eleve,\n" +
            "    e.prenom_eleve,\n" +
            "\te.classe,\n" +
            "    ex.trimestre,\n" +
            "\tpo.num_poste,\n" +
            "\tpo.nom_poste,\n" +
            "\tan.ann_debut,\n" +
            "\tan.ann_fin\n" +
            ")\n" +
            "SELECT\n" +
            "  *,\n" +
            "  CASE\n" +
            "    WHEN moyenne >= 16 THEN 'Très Bien'\n" +
            "    WHEN moyenne >= 14 THEN 'Bien'\n" +
            "    WHEN moyenne >= 12 THEN 'Assez Bien'\n" +
            "    ELSE 'Passable'\n" +
            "  END AS mention,\n" +
            "  ROW_NUMBER() OVER (PARTITION BY trimestre, num_poste, classe ORDER BY moyenne DESC) AS rang\n" +
            "FROM\n" +
            "  moyennes_eleves\n" +
            "ORDER BY\n" +
            "  trimestre,\n" +
            "  classe,\n" +
            "  nom_poste,\n" +
            "  ann_debut,\n" +
            "  ann_fin,\n" +
            "  rang;", nativeQuery = true)
    Page<Object[]> findResultPerTrimeste(Pageable pageable);

    @Query(value = "" +
            "WITH moyennes_eleves AS (\n" +
            "  SELECT\n" +
            "    e.nom_eleve,\n" +
            "    e.prenom_eleve,\n" +
            "\te.classe,\n" +
            "    ex.trimestre,\n" +
            "\tpo.num_poste,\n" +
            "\tpo.nom_poste,\n" +
            "\tan.ann_debut,\n" +
            "\tan.ann_fin,\n" +
            "\tROUND(SUM(mee.note * m.coefficient) / SUM(m.coefficient), 2) AS moyenne\n" +
            "  FROM\n" +
            "    eleve e\n" +
            "  INNER JOIN\n" +
            "    eleve_matiere_examen mee ON e.id = mee.eleve_id\n" +
            "  INNER JOIN\n" +
            "    examen ex ON mee.examen_id = ex.id\n" +
            "  INNER JOIN\n" +
            "    matiere m ON mee.matiere_id = m.id\n" +
            "  INNER JOIN \n" +
            "    poste po ON e.poste_id = po.id\n" +
            "  INNER JOIN \n" +
            "    ann_scolaire an ON e.ann_scolaire_id = an.id\n" +
            "  GROUP BY\n" +
            "    e.nom_eleve,\n" +
            "    e.prenom_eleve,\n" +
            "\te.classe,\n" +
            "    ex.trimestre,\n" +
            "\tpo.num_poste,\n" +
            "\tpo.nom_poste,\n" +
            "\tan.ann_debut,\n" +
            "\tan.ann_fin\n" +
            ")\n" +
            "SELECT\n" +
            "  *,\n" +
            "  CASE\n" +
            "    WHEN moyenne >= 16 THEN 'Très Bien'\n" +
            "    WHEN moyenne >= 14 THEN 'Bien'\n" +
            "    WHEN moyenne >= 12 THEN 'Assez Bien'\n" +
            "    ELSE 'Passable'\n" +
            "  END AS mention,\n" +
            "  ROW_NUMBER() OVER (PARTITION BY trimestre, num_poste, classe ORDER BY moyenne DESC) AS rang\n" +
            "FROM\n" +
            "  moyennes_eleves\n" +
            "WHERE\n" +
            "(num_poste LIKE CONCAT('%', :numPoste, '%') OR nom_poste LIKE CONCAT('%', :nomPoste, '%'))\n" +
            "ORDER BY\n" +
            "  trimestre,\n" +
            "  classe,\n" +
            "  nom_poste,\n" +
            "  ann_debut,\n" +
            "  ann_fin,\n" +
            "  rang;", nativeQuery = true)
    Page<Object[]> findAllByNumPosteOrNomPoste(
            Pageable pageable,
            @Param("numPoste") String numPoste,
            @Param("nomPoste") String nomPoste
    );

}
