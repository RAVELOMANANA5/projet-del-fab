package com.stage.models.scolaire;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode
@Entity @Table(name = "examen")
public class Examen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_examen", nullable = false)
    private LocalDate dateExamenDt;

    @NotBlank(message = "Trimestre ne peut pas être vide")
    @Size(min = 2, message = "Trimestre doit comporter au moins 2 caractères")
    private String trimestre;

    @ManyToMany(mappedBy = "examens", fetch = FetchType.EAGER)
    private List<Eleve> eleves = new ArrayList<>();

    @ManyToMany(mappedBy = "examens", fetch = FetchType.EAGER)
    private List<Matiere> matieres = new ArrayList<>();
}
