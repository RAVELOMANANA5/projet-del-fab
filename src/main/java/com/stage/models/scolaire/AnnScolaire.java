package com.stage.models.scolaire;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "ann_scolaire")
public class AnnScolaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_debut_dt", nullable = false)
    private LocalDate dateDebutDt;

    @Column(name = "date_fin_dt", nullable = false)
    private LocalDate dateFinDt;
}
