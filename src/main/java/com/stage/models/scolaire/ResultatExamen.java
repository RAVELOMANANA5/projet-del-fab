package com.stage.models.scolaire;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "eleve_matiere_examen")
public class ResultatExamen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "eleve_id")
    @NotNull(message = "Eleve ne peut pas être nulle")
    private Eleve eleve;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "matiere_id")
    @NotNull(message = "Matière ne peut pas être nulle")
    private Matiere matiere;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "examen_id")
    @NotNull(message = "Examen ne peut pas être nulle")
    private Examen examen;

    private Long note;
}
