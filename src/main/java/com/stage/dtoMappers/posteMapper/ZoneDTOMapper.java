package com.stage.dtoMappers.posteMapper;

import com.stage.constants.Constante;
import com.stage.dtos.posteDTOS.ZoneDTO;
import com.stage.models.poste.Region;
import com.stage.models.poste.Zone;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
public class ZoneDTOMapper implements Function<Zone, ZoneDTO> {
    /**
     * Applies this function to the given argument.
     *
     * @param zone the function argument
     * @return the function result
     */
    @Override
    public ZoneDTO apply(Zone zone) {
        
        return new ZoneDTO(
                zone.getId(),
                zone.getNumZone(),
                zone.getNomZone(),
                Optional.ofNullable(zone.getRegion())
                        .map(Region::getNomRegion)
                        .orElse(Constante.INCONNU)
        );
    }
}
