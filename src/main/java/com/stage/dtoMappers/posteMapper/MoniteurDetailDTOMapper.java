package com.stage.dtoMappers.posteMapper;

import com.stage.constants.Constante;
import com.stage.dtos.posteDTOS.MoniteurDetailDTO;
import com.stage.models.poste.Moniteur;
import com.stage.models.poste.Poste;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
public class MoniteurDetailDTOMapper implements Function<Moniteur, MoniteurDetailDTO> {

    /**
     * Applies this function to the given argument.
     *
     * @param moniteur the function argument
     * @return the function result
     */
    @Override
    public MoniteurDetailDTO apply(Moniteur moniteur) {
        return new MoniteurDetailDTO(
                moniteur.getMatrMoniteur(),
                moniteur.getNom(),
                moniteur.getPrenom(),
                collectPosteAttributes(moniteur, Poste::getNumPoste),
                collectPosteAttributes(moniteur, Poste::getNomPoste),
                collectPosteAttributes(moniteur, p -> getOrDefault(() ->
                        p.getSecteur().getNomSecteur(), Constante.INCONNU)),
                collectPosteAttributes(moniteur, p -> getOrDefault(() ->
                        p.getSecteur().getNomSecteur(), Constante.INCONNU)),
                collectPosteAttributes(moniteur, p -> getOrDefault(() ->
                        p.getInspecteur().getMatrInspect(), Constante.INCONNU)),
                collectPosteAttributes(moniteur, p -> getOrDefault(() ->
                        p.getInspecteur().getNom(), Constante.INCONNU)),
                collectPosteAttributes(moniteur, p -> getOrDefault(() ->
                        p.getInspecteur().getPrenom(), Constante.INCONNU)),
                collectPosteAttributes(moniteur, p -> getOrDefault(() ->
                        p.getSecteur().getZone().getNomZone(), Constante.INCONNU)),
                collectPosteAttributes(moniteur, p -> getOrDefault(() ->
                        p.getSecteur().getZone().getRegion().getNomRegion(), Constante.INCONNU))
        );
    }

    /**
     * @param moniteur
     * @param mapper
     * @return
     * @param <T>
     */
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
