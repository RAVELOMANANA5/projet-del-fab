package com.stage.dtoMappers.posteMapper;

import com.stage.dtos.posteDTOS.InspecteurDTO;
import com.stage.models.poste.Inspecteur;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class InspecteurDTOMapper implements Function<Inspecteur, InspecteurDTO> {
    /**
     * Applies this function to the given argument.
     *
     * @param inspecteur the function argument
     * @return the function result
     */
    @Override
    public InspecteurDTO apply(Inspecteur inspecteur) {
        return new InspecteurDTO(
                inspecteur.getId(),
                inspecteur.getMatrInspect(),
                inspecteur.getEtatCivil(),
                inspecteur.getDateEntrerVozama(),
                inspecteur.getDatePriseFonction(),
                inspecteur.getTempsTransportVisitPoste(),
                inspecteur.getTempsTransportFormation(),
                inspecteur.getPostes()
                        .stream()
                        .map(poste -> poste.getNomPoste())
                        .collect(Collectors.joining(", ")),
                inspecteur.getNom(),
                inspecteur.getPrenom(),
                inspecteur.getDateNaiss(),
                inspecteur.getLieuDeNaiss(),
                inspecteur.getAdresse(),
                inspecteur.getProfession(),
                inspecteur.getNbrEnfant(),
                inspecteur.getNbrEnfantCharger(),
                inspecteur.getEtudePrim(),
                inspecteur.getLieuEtudePrim(),
                inspecteur.getEtudeSecond(),
                inspecteur.getLieuEtudeSecond(),
                inspecteur.getDernierDiplome()
        );
    }
}
