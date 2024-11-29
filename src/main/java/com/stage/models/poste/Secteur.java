package com.stage.models.poste;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Entity
@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode @Builder
@Table(name = "secteur", uniqueConstraints = @UniqueConstraint(columnNames = "num_secteur"))
public class Secteur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(
            name = "num_secteur",
            nullable = false,
            unique = true,
            updatable = false
    )
    @NotBlank(message = "Le nom secteur ne peut pas être vide")
    @Size(min = 2, message = "Nom secteur doit comporter au moins 2 caractères")
    private String numSecteur;

    @Column(name = "nom_secteur")
    @NotBlank(message = "Le nom secteur ne peut pas être vide")
    @Size(min = 2, message = "Nom secteur doit comporter au moins 2 caractères")
    private String nomSecteur;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "zone_id")
    @NotNull(message = "La zone ne peut pas être nulle")
    private Zone zone;

    @OneToMany(mappedBy = "secteur", fetch = FetchType.EAGER)
    private List<Poste> postes;
}
