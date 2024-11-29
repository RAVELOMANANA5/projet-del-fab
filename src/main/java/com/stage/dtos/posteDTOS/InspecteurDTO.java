package com.stage.dtos.posteDTOS;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Year;
import java.util.Date;

public record InspecteurDTO (
        Long id,
        String matrInspect,
        String etatCivil,
        Year dateEntrerVozama,
        LocalDate datePriseFonction,
        LocalTime tempsTransportVisitPoste,
        LocalTime tempsTransportFormation,
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
){
    public InspecteurDTO {
    }
}
