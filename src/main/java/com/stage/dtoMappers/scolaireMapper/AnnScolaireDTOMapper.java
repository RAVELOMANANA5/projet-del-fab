package com.stage.dtoMappers.scolaireMapper;

import com.stage.dtos.scolaireDTOS.AnnScolaireDTO;
import com.stage.models.scolaire.AnnScolaire;
import com.stage.repository.scolaireRepository.AnnScolaireRepository;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class AnnScolaireDTOMapper implements Function<AnnScolaire, AnnScolaireDTO> {

    /**
     * Applies this function to the given argument.
     *
     * @param annScolaire the function argument
     * @return the function result
     */
    @Override
    public AnnScolaireDTO apply(AnnScolaire annScolaire) {
        return new AnnScolaireDTO(
                annScolaire.getId(),
                annScolaire.getAnnDebut(),
                annScolaire.getAnnFin()
        );
    }
}
