package com.stage.models.poste;

import jakarta.persistence.*;

import java.time.LocalTime;
import java.time.Year;
import java.util.List;

public class ArchiveMoniteur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "matr_moniteur",
            nullable = false,
            unique = true,
            updatable = false
    )
    private String matrMoniteur;

    @Column(name = "ann_entrer_mon")
    private Year anEntrerMon;

    @Column(name = "code_secteur")
    private String codeSecteur;

    private LocalTime heure;

    @OneToMany(mappedBy = "moniteur", fetch = FetchType.EAGER)
    private List<Poste> postes;
}
