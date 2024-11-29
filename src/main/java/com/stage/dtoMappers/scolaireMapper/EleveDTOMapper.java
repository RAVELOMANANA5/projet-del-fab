package com.stage.dtoMappers.scolaireMapper;

import com.stage.dtos.scolaireDTOS.EleveDTO;
import com.stage.models.scolaire.Eleve;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class EleveDTOMapper implements Function<Eleve, EleveDTO>  {
    /**
     * Applies this function to the given argument.
     *
     * @param eleve the function argument
     * @return the function result
     */
    @Override
    public EleveDTO apply(Eleve eleve) {
        return new EleveDTO(
                eleve.getId(),
                eleve.getMatrEleve(),
                eleve.getNomEleve(),
                eleve.getPrenomEleve(),
                eleve.getDateNaissEleve(),
                eleve.getLieuNaissEleve(),
                eleve.getSexe(),
                eleve.getPere(),
                eleve.getMere(),
                eleve.getProfessionPerent(),
                eleve.getQuitteEnCoursAn(),
                eleve.getDateQuitte(),
                eleve.getClasse(),
                eleve.getClasseAnPasse(),
                eleve.getSituation(),
                eleve.getAnnEntrer(),
                eleve.getAnnSort(),
                eleve.getAbr(),
                eleve.getReligion(),
                eleve.getCopieEtatCivil(),
                eleve.getNumCopie(),
                eleve.getNmbFrereEtSoeur(),
                eleve.getPoste().getNomPoste()
        );
    }
}
