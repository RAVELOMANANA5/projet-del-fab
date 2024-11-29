package com.stage.dtos.posteDTOS;

public record InspecteurDetailDTO(
        String matrInspecteur,
        String nomInsoecteur,
        String prenomInspecteur,
        String numPoste,
        String nomPoste,
        Integer nbrPoste,
        String nomSecteur,
        String nomZone,
        String nomRegion
) {
    public InspecteurDetailDTO {
    }
}
