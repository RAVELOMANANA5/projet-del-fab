package com.stage.dtos.scolaireDTOS;

import java.time.Year;

public record AnnScolaireDTO(
        Long id,
        Year annDebut,
        Year annFin
) {
}
