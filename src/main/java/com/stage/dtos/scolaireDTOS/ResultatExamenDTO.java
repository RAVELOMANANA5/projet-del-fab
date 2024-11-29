package com.stage.dtos.scolaireDTOS;

import java.time.LocalDate;

public record ResultatExamenDTO(
        Long id,
        String matrEleve,
        String nomEleve,
        String prenomEleve,
        String nomMatiere,
        Integer coefficient,
        LocalDate examen,
        String trimestre,
        Long note
) {
    public ResultatExamenDTO {
    }
}
