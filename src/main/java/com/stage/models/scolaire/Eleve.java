package com.stage.models.scolaire;

import com.stage.models.poste.Poste;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode
@Entity @Table(name = "eleve")
public class Eleve {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "matr_eleve", nullable = false, unique = true, updatable = false)
    @NotBlank(message = "Matricule eleve ne peut pas être vide")
    @Size(min = 2, message = "Matricule eleve doit comporter au moins 2 caractères")
    private String matrEleve;

    @Column(name = "nom_eleve", nullable = false)
    @NotBlank(message = "Nom ne peut pas être vide")
    @Size(min = 2, message = "Nom doit comporter au moins 2 caractères")
    private String nomEleve;

    @Column(name = "prenom_eleve")
    @NotBlank(message = "Prenom ne peut pas être vide")
    @Size(min = 2, message = "Prenom doit comporter au moins 2 caractères")
    private String prenomEleve;

    private LocalDate dateNaissEleve;

    @Column(name = "lieu_naissance")
    @NotBlank(message = "Lieu de naissance ne peut pas être vide")
    @Size(min = 2, message = "Lieu de naissance doit comporter au moins 2 caractères")
    private String lieuNaissEleve;

    @NotBlank(message = "Sexe ne peut pas être vide")
    @Size(min = 2, message = "Sexe doit comporter au moins 2 caractères")
    private String sexe;

    private String pere;
    private String mere;

    @Column(name = "profession_parent")
    @NotBlank(message = "Profession de parent ne peut pas être vide")
    @Size(min = 2, message = "Profession de parent doit comporter au moins 2 caractères")
    private String professionPerent;

    @Column(name = "quitte_en_cours_ann")
    private String quitteEnCoursAn;

    @Column(name = "date_quitte")
    private LocalDate dateQuitte;

    @NotBlank(message = "Classe ne peut pas être vide")
    @Size(min = 2, message = "Classe doit comporter au moins 2 caractères")
    private String classe;

    @Column(name = "classe_an_passe")
    @NotBlank(message = "Le champ `classeAnPasse` ne peut pas être vide")
    @Size(min = 2, message = "Le champ `classeAnPasse` doit comporter au moins 2 caractères")
    private String classeAnPasse;

    @Column(nullable = false)
    @NotBlank(message = "Situation ne peut pas être vide")
    @Size(min = 2, message = "Situation doit comporter au moins 2 caractères")
    private String situation;

    @Column(name = "ann_entrer")
    private String annEntrer;

    @Column(name = "ann_sort")
    private Year annSort;
    private String abr;
    @NotBlank(message = "Réligion ne peut pas être vide")
    @Size(min = 2, message = "Réligion doit comporter au moins 2 caractères")
    private String religion;

    @Column(name = "copie_etat_civil")
    private String copieEtatCivil;

    @Column(name = "num_copie")
    private long numCopie;

    @Column(name = "nbr_frere_et_soeur")
    private long nmbFrereEtSoeur;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "poste_id")
    @NotNull(message = "La poste ne peut pas être nulle")
    private Poste poste;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "eleve_matiere_examen",
            joinColumns = @JoinColumn(name = "eleve_id"),
            inverseJoinColumns = @JoinColumn(name = "examen_id")
    )
    private List<Examen> examens = new ArrayList<>();
/*
    @ManyToOne(fetch = FetchType.LAZY)
    private Date date;
 */
}
