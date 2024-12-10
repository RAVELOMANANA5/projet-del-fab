package com.stage.dtoMappers.posteMapper;

import com.stage.constants.Constante;
import com.stage.dtos.posteDTOS.ArchivedMoniteurDTO;
import com.stage.models.poste.ArchiveMoniteur;
import com.stage.models.poste.Moniteur;
import com.stage.models.poste.Poste;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
public class ArchivedMoniteurDTOMapper implements Function<ArchiveMoniteur, ArchivedMoniteurDTO> {
    /**
     * Applies this function to the given argument.
     *
     * @param am the function argument
     * @return the function result
     */
    @Override
    public ArchivedMoniteurDTO apply(ArchiveMoniteur am) {/*
        return new ArchivedMoniteurDTO(
                getOrDefault(() -> am.getMoniteur().getMatrMoniteur(), Constante.INCONNU),
                getOrDefault(() -> am.getMoniteur().getNom(), Constante.INCONNU),
                getOrDefault(() -> am.getMoniteur().getPrenom(), Constante.INCONNU),
                getOrDefault(() -> am.getMoniteur().getPostes().)
        );
*/
        return null;
    }

    private <T> String collectPosteAttributes(Moniteur moniteur, Function<Poste, T> mapper) {
        return moniteur.getPostes()
                .stream().map(mapper)
                .map(Objects::toString)
                .collect(Collectors.joining(", "));
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
