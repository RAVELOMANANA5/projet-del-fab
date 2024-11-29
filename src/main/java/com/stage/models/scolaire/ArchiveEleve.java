package com.stage.models.scolaire;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity @Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode @Table(name = "archive_eleve")
public class ArchiveEleve {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_fin", nullable = false)
    private LocalDate dateFin;
}
