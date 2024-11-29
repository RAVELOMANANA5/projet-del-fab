package com.stage.models.poste;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "commune")
public class Commune {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Le nom ne peut pas être vide")
    @Size(min = 2, message = "Nom doit comporter au moins 2 caractères")
    private String nom;

    @OneToMany(mappedBy = "commune", fetch = FetchType.EAGER)
    private List<Poste> postes;
}
