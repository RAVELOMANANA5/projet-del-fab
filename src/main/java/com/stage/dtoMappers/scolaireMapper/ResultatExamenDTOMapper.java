package com.stage.dtoMappers.scolaireMapper;

import com.stage.dtos.scolaireDTOS.ResultatExamenDTO;
import com.stage.models.scolaire.ResultatExamen;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ResultatExamenDTOMapper implements Function<ResultatExamen, ResultatExamenDTO> {
    /**
     * Applies this function to the given argument.
     *
     * @param resultatExamen the function argument
     * @return the function result
     */
    @Override
    public ResultatExamenDTO apply(ResultatExamen resultatExamen) {
        return new ResultatExamenDTO(
                resultatExamen.getId(),
                resultatExamen.getEleve().getMatrEleve(),
                resultatExamen.getEleve().getNomEleve(),
                resultatExamen.getEleve().getPrenomEleve(),
                resultatExamen.getMatiere().getNomMatiere(),
                resultatExamen.getMatiere().getCoefficient(),
                resultatExamen.getExamen().getDateExamenDt(),
                resultatExamen.getExamen().getTrimestre(),
                resultatExamen.getNote()
        );
    }
}
