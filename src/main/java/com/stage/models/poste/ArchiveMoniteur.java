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
@Table(name = "archive_moniteur")
public class ArchiveMoniteur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "archive_date_mon", nullable = false)
    private LocalDate archiveDateMon;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "moniteur_id", nullable = false)
    private Moniteur moniteur;
}
