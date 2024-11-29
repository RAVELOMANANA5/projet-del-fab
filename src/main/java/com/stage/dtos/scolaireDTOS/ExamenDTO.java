package com.stage.dtos.scolaireDTOS;

import java.time.LocalDate;

public record ExamenDTO(
        Long id,
        LocalDate dateExamenDt,
        String trimestre,
        String eleves,
        String matieres
) {
}
