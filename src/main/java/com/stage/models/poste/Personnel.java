package com.stage.models.poste;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter @ToString
public class Personnel {

    @NotBlank(message = "Le nom ne peut pas être vide")
    @Size(min = 2, message = "Nom doit comporter au moins 2 caractères")
    private String nom;

    private String prenom;

    @NotBlank(message = "Le sexe ne peut pas être vide")
    @Size(min = 2, message = "Sexe doit comporter au moins 2 caractères")
    private String sexe;

    @Column(name = "date_naiss")
    private Date dateNaiss;

    @Column(name = "lieu_de_naiss")
    @NotBlank(message = "Lieu de naissance ne peut pas être vide")
    @Size(min = 2, message = "Lieu de naissance doit comporter au moins 2 caractères")
    private String lieuDeNaiss;

    @NotBlank(message = "L'adresse ne peut pas être vide")
    @Size(min = 2, message = "Adresse doit comporter au moins 2 caractères")
    private String adresse;

    @NotBlank(message = "Le profession ne peut pas être vide")
    @Size(min = 2, message = "Profession doit comporter au moins 2 caractères")
    private String profession;

    @Column(name = "nbr_enfant")
    private Integer nbrEnfant;

    @Column(name = "nbr_enfant_charger")
    private Integer nbrEnfantCharger;

    @Column(name = "etude_prim")
    @NotBlank(message = "Etude primaire ne peut pas être vide")
    @Size(min = 2, message = "Etude primaire doit comporter au moins 2 caractères")
    private String etudePrim;

    @Column(name = "lieu_etude_prim")
    @NotBlank(message = "Lieu d'etude primaire ne peut pas être vide")
    @Size(min = 2, message = "Lieu d'etude primaire doit comporter au moins 2 caractères")
    private String lieuEtudePrim;

    @Column(name = "etude_second")
    @NotBlank(message = "Etude secondaire ne peut pas être vide")
    @Size(min = 2, message = "Etude secondaire doit comporter au moins 2 caractères")
    private String etudeSecond;

    @Column(name = "lieu_etude_second")
    @NotBlank(message = "Lieu d'etude secondaire ne peut pas être vide")
    @Size(min = 2, message = "Lieu d'etude secondaire doit comporter au moins 2 caractères")
    private String lieuEtudeSecond;

    @Column(name = "dernier_diplome")
    @NotBlank(message = "Derniere diplôme ne peut pas être vide")
    @Size(min = 2, message = "Derniere diplôme doit comporter au moins 2 caractères")
    private String dernierDiplome;
}
