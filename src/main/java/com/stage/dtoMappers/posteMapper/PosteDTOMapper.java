package com.stage.dtoMappers.posteMapper;

import com.stage.constants.Constante;
import com.stage.dtos.posteDTOS.PosteDTO;
import com.stage.models.poste.Poste;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.function.Supplier;

@Service
public class PosteDTOMapper implements Function<Poste, PosteDTO> {
    /**
     * Applies this function to the given argument.
     *
     * @param poste the function argument
     * @return the function result
     */
    @Override
    public PosteDTO apply(Poste poste) {
        return new PosteDTO(
                poste.getId(),
                poste.getNumPoste(),
                poste.getNomPoste(),
                poste.getAnOuverPoste(),
                poste.getNomMateriel(),
                getOrDefault(() -> poste.getCisco().getNom(), Constante.INCONNU),
                getOrDefault(() -> poste.getCommune().getNom(), Constante.INCONNU),
                getOrDefault(() -> poste.getSecteur().getNomSecteur(), Constante.INCONNU),
                getOrDefault(() -> poste.getMoniteur().getNom(), Constante.INCONNU),
                getOrDefault(() -> poste.getMoniteur().getPrenom(), Constante.INCONNU),
                getOrDefault(() -> poste.getInspecteur().getNom(), Constante.INCONNU),
                getOrDefault(() -> poste.getInspecteur().getPrenom(), Constante.INCONNU)
        );
    }

    /**
     * @param supplier
     * @param defaultValue
     * @return
     * @param <T>
     */
    private <T> T getOrDefault(Supplier<T> supplier, T defaultValue) {
        try {
            return (supplier.get() != null) ? supplier.get() : defaultValue;
        }
        catch (NullPointerException e) {
            return defaultValue;
        }
    }
}
