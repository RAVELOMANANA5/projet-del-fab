package com.stage.models.poste;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalTime;
import java.time.Year;
import java.util.List;

@Entity
@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode
@Table(name = "moniteur", uniqueConstraints = @UniqueConstraint(columnNames = "matr_moniteur"))
public class Moniteur extends Personnel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "matr_moniteur",
            nullable = false,
            unique = true,
            updatable = false
    )
    @NotBlank(message = "Le matricule moniteur ne peut pas être vide")
    @Size(min = 4, message = "Matricule moniteur doit comporter au moins 4 caractères")
    private String matrMoniteur;

    @Column(name = "ann_entrer")
    private Year anEntrerMon;

    @Column(name = "code_secteur")
    //@NotBlank(message = "Le code secteur ne peut pas être vide")
    //@Size(min = 2, message = "Code secteur doit comporter au moins 2 caractères")
    private String codeSecteur;

    private LocalTime heure;

    @OneToMany(mappedBy = "moniteur", fetch = FetchType.EAGER)
    private List<Poste> postes;

    @OneToOne(mappedBy = "moniteur", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ArchiveMoniteur archiveMoniteur;
}
