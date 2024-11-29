package com.stage.dtoMappers.posteMapper;

import com.stage.dtos.posteDTOS.MoniteurDTO;
import com.stage.models.poste.Moniteur;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class MoniteurDTOMapper implements Function<Moniteur, MoniteurDTO> {
    /**
     * Applies this function to the given argument.
     *
     * @param moniteur the function argument
     * @return the function result
     */
    @Override
    public MoniteurDTO apply(Moniteur moniteur) {
        return new MoniteurDTO(
                moniteur.getId(),
                moniteur.getMatrMoniteur(),
                moniteur.getAnEntrerMon(),
                moniteur.getCodeSecteur(),
                moniteur.getHeure(),
                moniteur.getPostes()
                        .stream()
                        .map(poste -> poste.getNomPoste())
                        .collect(Collectors.joining(", ")),
                moniteur.getNom(),
                moniteur.getPrenom(),
                moniteur.getDateNaiss(),
                moniteur.getLieuDeNaiss(),
                moniteur.getAdresse(),
                moniteur.getProfession(),
                moniteur.getNbrEnfant(),
                moniteur.getNbrEnfantCharger(),
                moniteur.getEtudePrim(),
                moniteur.getLieuEtudePrim(),
                moniteur.getEtudeSecond(),
                moniteur.getLieuEtudeSecond(),
                moniteur.getDernierDiplome()
        );
    }
}
