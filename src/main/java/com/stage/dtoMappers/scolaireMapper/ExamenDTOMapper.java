package com.stage.dtoMappers.scolaireMapper;

import com.stage.dtos.scolaireDTOS.ExamenDTO;
import com.stage.models.scolaire.Examen;
import com.stage.models.scolaire.Matiere;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ExamenDTOMapper implements Function<Examen, ExamenDTO>  {
    /**
     * Applies this function to the given argument.
     *
     * @param examen the function argument
     * @return the function result
     */
    @Override
    public ExamenDTO apply(Examen examen) {
        return new ExamenDTO(
                examen.getId(),
                examen.getDateExamenDt(),
                examen.getTrimestre(),
                examen.getEleves().stream()
                        .map(eleve -> eleve.getNomEleve() + " " + eleve.getPrenomEleve())
                        .collect(Collectors.joining(", ")),
                examen.getMatieres()
                        .stream()
                        .map(Matiere::getNomMatiere)
                        .collect(Collectors.joining(", "))
        );
    }
}
