package com.stage.models.scolaire;

import com.stage.utils.UniqueValue;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Year;
import java.util.List;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "ann_scolaire", uniqueConstraints = @UniqueConstraint(columnNames = {"ann_debut", "ann_fin"}))
public class AnnScolaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ann_debut", nullable = false, unique = true)
    @NotNull
    private Year annDebut;

    @Column(name = "ann_fin", nullable = false, unique = true)
    @NotNull
    private Year annFin;

    @OneToMany(mappedBy = "annScolaire", fetch = FetchType.EAGER)
    private List<Eleve> eleves;

    @AssertTrue(message = "AnnDebut doit être supérieur ou égal à 1900")
    public boolean isAnnDebutValid() {
        return annDebut != null && annDebut.getValue() >= 1900;
    }

    @AssertTrue(message = "AnnFin doit être supérieur ou égal à 1900")
    public boolean isAnnFinValid() {
        return annFin != null && annFin.getValue() >= 1901;
    }

    @AssertTrue(message = "AnnDebut doit être inférieur à AnnFin")
    public boolean isYearConsistent() {
        return (annDebut != null && annFin != null && annDebut.getValue() < annFin.getValue());
    }

    @AssertTrue(message = "La durée de l’année académique doit être exactement d’un an")
    public boolean isDurationValid() {
        return annDebut != null && annFin != null && annFin.getValue() - annDebut.getValue() == 1;
    }

    @AssertTrue(message = "AnnFin doit être au maximum l'année en cours ou l'année suivante")
    public boolean isAnnFinWithinMaxLimit() {
        int currentYear = Year.now().getValue();
        return annFin != null && annFin.getValue() <= currentYear + 1;
    }

}
