package com.stage.models.poste;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Entity
@Data @EqualsAndHashCode @ToString
@Table(name = "region", uniqueConstraints = @UniqueConstraint(columnNames = "num_region"))
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "num_region",
            nullable = false,
            unique = true,
            updatable = false
    )
    @NotBlank(message = "Le num region ne peut pas être vide")
    @Size(min = 2, message = "Numéro region doit comporter au moins 2 caractères")
    private String numRegion;

    @Column(name = "nom_region")
    @NotBlank(message = "Le nom region ne peut pas être vide")
    @Size(min = 2, message = "Nom region doit comporter au moins 2 caractères")
    private String nomRegion;

    @OneToMany(mappedBy = "region", fetch = FetchType.EAGER)
    private List<Zone> zones;

}
