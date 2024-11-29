package com.stage.dtos.posteDTOS;

import java.time.LocalTime;
import java.time.Year;
import java.util.Date;

public record MoniteurDTO(
        Long id,
        String matrMoniteur,
        Year anEntrerMon,
        String codeSecteur,
        LocalTime heure,
        String postes,
        String nom,
        String prenom,
        Date dateNaiss,
        String lieuDeNaiss,
        String adresse,
        String profession,
        Integer nbrEnfant,
        Integer nbrEnfantCharger,
        String etudePrim,
        String lieuEtudePrim,
        String etudeSecond,
        String lieuEtudeSecond,
        String dernierDiplome
) {
    public MoniteurDTO {
    }
}
