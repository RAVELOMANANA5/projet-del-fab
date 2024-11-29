package com.stage.models.scolaire;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode
@Entity @Table(name = "matiere")
public class Matiere {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom_matiere", nullable = false)
    @NotBlank(message = "Nom ne peut pas être vide")
    @Size(min = 2, message = "Nom doit comporter au moins 2 caractères")
    private String nomMatiere;

    @Column(nullable = false)
    @NotNull(message = "Coefficient ne peut pas être nulle")
    private int coefficient;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "eleve_matiere_examen",
            joinColumns = @JoinColumn(name = "matiere_id"),
            inverseJoinColumns = @JoinColumn(name = "examen_id")
    )
    private List<Examen> examens = new ArrayList<>();
}
