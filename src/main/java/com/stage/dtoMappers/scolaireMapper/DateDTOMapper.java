package com.stage.dtoMappers.scolaireMapper;

import com.stage.dtos.scolaireDTOS.DateDTO;
import com.stage.models.scolaire.Date;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class DateDTOMapper implements Function<Date, DateDTO> {
    /**
     * Applies this function to the given argument.
     *
     * @param date the function argument
     * @return the function result
     */
    @Override
    public DateDTO apply(Date date) {
        return new DateDTO(
                date.getId(),
                date.getDateAbsence(),
                date.getMotif()
        );
    }
}
