package com.stage.models.poste;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Year;
import java.util.List;

@Entity
@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
@Table(name = "inspecteur", uniqueConstraints = @UniqueConstraint(columnNames = "matr_inspect"))
public class Inspecteur extends Personnel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "matr_inspect",
            nullable = false,
            unique = true,
            updatable = false
    )
    @NotBlank(message = "Le matricule inspecteur ne peut pas être vide")
    @Size(min = 4, message = "Matricule inspecteur doit comporter au moins 4 caractères")
    private String matrInspect;

    @Column(name = "etat_civil")
    @NotBlank(message = "L'etat civil ne peut pas être vide")
    @Size(min = 2, message = "Etat civil doit comporter au moins 2 caractères")
    private String etatCivil;

    @Column(name = "date_entrer_vozama")
    private Year dateEntrerVozama;

    @Column(name = "date_prise_fonction")
    private LocalDate datePriseFonction;

    @Column(name = "temps_transport_visit_poste")
    private LocalTime tempsTransportVisitPoste;

    @Column(name = "temps_transport_formation")
    private LocalTime tempsTransportFormation;

    @OneToMany(mappedBy = "inspecteur", fetch = FetchType.EAGER)
    private List<Poste> postes;
}
