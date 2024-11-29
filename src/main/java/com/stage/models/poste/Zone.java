package com.stage.models.poste;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Entity
@Data @EqualsAndHashCode
@Table(name = "zone", uniqueConstraints = @UniqueConstraint(columnNames = "num_zone"))
public class Zone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "num_zone",
            nullable = false,
            unique = true,
            updatable = false
    )
    @NotBlank(message = "Numéro zone ne peut pas être vide")
    @Size(min = 2, message = "Numéro zone doit comporter au moins 2 caractères")
    private String numZone;

    @Column(name = "nom_zone")
    @NotBlank(message = "Le nom zone ne peut pas être vide")
    @Size(min = 2, message = "Nom zone doit comporter au moins 2 caractères")
    private String nomZone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    @NotNull(message = "La région ne peut pas être nulle")
    private Region region;

    @OneToMany(mappedBy = "zone", fetch = FetchType.EAGER)
    private List<Secteur> secteurs;
}
