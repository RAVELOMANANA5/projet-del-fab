package com.stage.dtos.posteDTOS;

import java.time.Year;

public record PosteDTO (
        Long id,
        String numPoste,
        String nomPoste,
        Year anOuverPoste,
        String nomMateriel,
        String cisco,
        String commune,
        String secteur,
        String NomMoniteur,
        String PrenomMoniteur,
        String NomInspecteur,
        String PrenomInspecteur
){
    public PosteDTO {
    }
}
