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
    public ArchivedMoniteurDTO apply(ArchiveMoniteur am) {
        return new ArchivedMoniteurDTO(
                getOrDefault(() -> am.getMoniteur().getMatrMoniteur(), Constante.INCONNU),
                getOrDefault(() -> am.getMoniteur().getNom(), Constante.INCONNU),
                getOrDefault(() -> am.getMoniteur().getPrenom(), Constante.INCONNU),
                collectPosteAttributes(am, Poste::getNumPoste),
                collectPosteAttributes(am, Poste::getNomPoste),
                collectPosteAttributes(am, p -> getOrDefault(() ->
                        p.getSecteur().getNomSecteur(), Constante.INCONNU)),
                collectPosteAttributes(am, p -> getOrDefault(() ->
                        p.getInspecteur().getMatrInspect(), Constante.INCONNU)),
                collectPosteAttributes(am, p -> getOrDefault(() ->
                        p.getInspecteur().getMatrInspect(), Constante.INCONNU)),
                collectPosteAttributes(am, p -> getOrDefault(() ->
                        p.getInspecteur().getNom(), Constante.INCONNU)),
                collectPosteAttributes(am, p -> getOrDefault(() ->
                        p.getInspecteur().getPrenom(), Constante.INCONNU)),
                collectPosteAttributes(am, p -> getOrDefault(() ->
                        p.getSecteur().getZone().getNomZone(), Constante.INCONNU)),
                collectPosteAttributes(am, p -> getOrDefault(() ->
                        p.getSecteur().getZone().getRegion().getNomRegion(), Constante.INCONNU)),
                am.getArchiveDateMon() + ""
        );
    }

    private <T> String collectPosteAttributes(ArchiveMoniteur archiveMoniteur, Function<Poste, T> mapper) {
        return archiveMoniteur.getMoniteur().getPostes()
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
