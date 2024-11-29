package com.stage.dtos.posteDTOS;

import java.util.List;

public record RegionDTO(
        long id,
        String numRegion,
        String nomRegion,
        List<ZoneDTO> zones
) {
}
