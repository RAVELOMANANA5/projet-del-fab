package com.stage.dtos.posteDTOS;

public record MoniteurDetailDTO(
        String MatrMoniteur,
        String NomMoniteur,
        String PrenomMoniteur,
        String MatrPoste,
        String NomPoste,
        String numSecteur,
        String nomSecteur,
        String MatrInspecteur,
        String NomInspecteur,
        String PrenomInspecteur,
        String nomZone,
        String nomRegion
) {
    public MoniteurDetailDTO {
    }
}
