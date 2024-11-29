package com.stage.dtoMappers.scolaireMapper;

import com.stage.dtos.scolaireDTOS.MatiereDTO;
import com.stage.models.scolaire.Matiere;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class MatiereDTOMapper implements Function<Matiere, MatiereDTO>  {
    /**
     * Applies this function to the given argument.
     *
     * @param matiere the function argument
     * @return the function result
     */
    @Override
    public MatiereDTO apply(Matiere matiere) {
        return new MatiereDTO(
                matiere.getId(),
                matiere.getNomMatiere(),
                matiere.getCoefficient()
        );
    }
}
