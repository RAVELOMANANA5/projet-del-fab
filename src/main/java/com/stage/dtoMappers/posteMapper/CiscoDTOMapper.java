package com.stage.dtoMappers.posteMapper;

import com.stage.dtos.posteDTOS.CommuneOrCiscoDTO;
import com.stage.models.poste.Cisco;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CiscoDTOMapper implements Function<Cisco, CommuneOrCiscoDTO> {
    /**
     * Applies this function to the given argument.
     *
     * @param cisco the function argument
     * @return the function result
     */
    @Override
    public CommuneOrCiscoDTO apply(Cisco cisco) {
        return new CommuneOrCiscoDTO(
                cisco.getId(),
                cisco.getNom()
        );
    }
}
