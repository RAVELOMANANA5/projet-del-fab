package com.stage.models.poste;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "archive_inspecteur")
public class ArchiveInspect {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "archive_date_inspecteur", nullable = false)
    private LocalDate archiveDateInspect;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inspecteur_id", nullable = false)
    private Inspecteur inspecteur;
}
