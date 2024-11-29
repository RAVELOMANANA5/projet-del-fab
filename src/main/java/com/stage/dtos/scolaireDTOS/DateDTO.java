package com.stage.dtos.scolaireDTOS;

import java.time.LocalDate;

public record DateDTO(
        Long id,
        LocalDate dateAbsenceDt,
        String motif
) {
}
