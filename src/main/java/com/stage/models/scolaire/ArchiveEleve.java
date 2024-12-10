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

    @Column(name = "archive_date", nullable = false)
    private LocalDate archiveDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "eleve_id", nullable = false)
    private Eleve eleve;
}
