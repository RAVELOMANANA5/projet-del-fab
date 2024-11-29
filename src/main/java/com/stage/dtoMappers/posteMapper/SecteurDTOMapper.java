package com.stage.dtoMappers.posteMapper;

import com.stage.constants.Constante;
import com.stage.dtos.posteDTOS.SecteurDTO;
import com.stage.models.poste.Secteur;
import com.stage.models.poste.Zone;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
public class SecteurDTOMapper implements Function<Secteur, SecteurDTO> {
    /**
     * Applies this function to the given argument.
     *
     * @param secteur the function argument
     * @return the function result
     */
    @Override
    public SecteurDTO apply(Secteur secteur) {
        return new SecteurDTO(
                secteur.getId(),
                secteur.getNumSecteur(),
                secteur.getNomSecteur(),
                Optional.ofNullable(secteur.getZone())
                        .map(Zone::getNomZone)
                        .orElse(Constante.INCONNU)
        );
    }
}
