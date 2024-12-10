package com.stage.dtos.scolaireDTOS;

import java.math.BigDecimal;
import java.time.Year;

public record ResultatParTrimestreDTO(
        String nomEleve,
        String prenomEleve,
        String classe,
        String trimestre,
        String numPoste,
        String nomPoste,
        Integer annDebut,
        Integer annFin,
        BigDecimal moyenne,
        String mention,
        Long rang
) {
}
