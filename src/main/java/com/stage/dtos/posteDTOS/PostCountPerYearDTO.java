package com.stage.dtos.posteDTOS;

import java.time.Year;

public record PostCountPerYearDTO(
        Year anOuverPoste,
        Long postCount
) {
    public PostCountPerYearDTO {
    }
}
