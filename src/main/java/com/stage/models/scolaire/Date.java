package com.stage.models.scolaire;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "date")
public class Date {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_absence")
    private LocalDate dateAbsence;
    private String motif;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Eleve> eleves;
}
