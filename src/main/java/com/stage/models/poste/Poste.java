package com.stage.models.poste;

import com.stage.models.scolaire.Eleve;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.Year;
import java.util.List;

@Entity
@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode @Builder
@Table(name = "poste", uniqueConstraints = @UniqueConstraint(columnNames = "num_poste"))
public class Poste {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(
            name = "num_poste",
            nullable = false,
            unique = true,
            updatable = false
    )
    @NotBlank(message = "Le numéro poste ne peut pas être vide")
    @Size(min = 2, message = "Numéro poste doit comporter au moins 2 caractères")
    private String numPoste;

    @Column(name = "nom_poste")
    @NotBlank(message = "Le nom poste ne peut pas être vide")
    @Size(min = 2, message = "Nom poste doit comporter au moins 2 caractères")
    private String nomPoste;

    @Column(name = "ann_ouver_poste")
    private Year anOuverPoste;

    @Column(name = "nom_materiel")
    @NotBlank(message = "Le nom matériel ne peut pas être vide")
    @Size(min = 2, message = "Nom matériel doit comporter au moins 2 caractères")
    private String nomMateriel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cisco_id")
    @NotNull(message = "La cisco ne peut pas être nulle")
    private Cisco cisco;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commune_id")
    @NotNull(message = "La commune ne peut pas être nulle")
    private Commune commune;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "secteur_id")
    @NotNull(message = "La secteur ne peut pas être nulle")
    private Secteur secteur;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "moniteur_id")
    @NotNull(message = "La moniteur ne peut pas être nulle")
    private Moniteur moniteur;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inspecteur_id")
    @NotNull(message = "La inspecteur ne peut pas être nulle")
    private Inspecteur inspecteur;

    @OneToMany(mappedBy = "poste", fetch = FetchType.EAGER)
    private List<Eleve> eleves;
}
