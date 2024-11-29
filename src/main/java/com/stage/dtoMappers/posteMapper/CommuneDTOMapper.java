package com.stage.dtoMappers.posteMapper;

import com.stage.dtos.posteDTOS.CommuneOrCiscoDTO;
import com.stage.models.poste.Commune;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CommuneDTOMapper implements Function<Commune, CommuneOrCiscoDTO> {
    /**
     * Applies this function to the given argument.
     *
     * @param commune the function argument
     * @return the function result
     */
    @Override
    public CommuneOrCiscoDTO apply(Commune commune) {
        return new CommuneOrCiscoDTO(
                commune.getId(),
                commune.getNom()
        );
    }
}
