package com.stage.dtoMappers.posteMapper;

import com.stage.constants.Constante;
import com.stage.dtos.posteDTOS.RegionDTO;
import com.stage.dtos.posteDTOS.ZoneDTO;
import com.stage.models.poste.Region;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class RegionDTOMapper implements Function<Region, RegionDTO> {
    /**
     * Applies this function to the given argument.
     *
     * @param region the function argument
     * @return the function result
     */
    @Override
    public RegionDTO apply(Region region) {

        return new RegionDTO(
                region.getId(),
                region.getNumRegion(),
                region.getNomRegion(),
                region.getZones()
                        .stream()
                        .map(zone -> new ZoneDTO(
                                zone.getId(),
                                zone.getNumZone(),
                                zone.getNomZone(),
                                Optional.ofNullable(zone.getRegion())
                                        .map(Region::getNomRegion)
                                        .orElse(Constante.INCONNU)
                        ))
                        .collect(Collectors.toList())
        );
    }

}
