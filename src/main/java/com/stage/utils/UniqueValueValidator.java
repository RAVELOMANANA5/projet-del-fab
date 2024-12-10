package com.stage.utils;

import com.stage.repository.scolaireRepository.AnnScolaireRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.time.Year;

@Component
public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {

    private final AnnScolaireRepository annScolaireRepository;

    public UniqueValueValidator(AnnScolaireRepository annScolaireRepository) {
        this.annScolaireRepository = annScolaireRepository;
    }

    /**
     * @param object
     * @param constraintValidatorContext
     * @return
     */
    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        if (!(object instanceof Year)) {
            return false; // Return false if the object is not a Year instance
        }

        Year year = (Year) object; // Safely cast the object to Year
        boolean existsAnnDebut = annScolaireRepository.existsByAnnDebut(year);
        boolean existsAnnFin = annScolaireRepository.existsByAnnFin(year);
        return !existsAnnDebut && !existsAnnFin;
    }
}
