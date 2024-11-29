package com.stage.dtos.scolaireDTOS;

import java.time.LocalDate;
import java.time.Year;

public record EleveDTO(
        Long id,
        String matrEleve,
        String nomEleve,
        String prenomEleve,
        LocalDate dateNaissEleve,
        String lieuNaissEleve,
        String sexe,
        String pere,
        String mere,
        String professionPerent,
        String quitteEnCoursAn,
        LocalDate dateQuitte,
        String classe,
        String classeAnPasse,
        String situation,
        String annEntrer,
        Year annSort,
        String abr,
        String religion,
        String copieEtatCivil,
        long numCopie,
        long nmbFrereEtSoeur,
        String poste

) {
}
