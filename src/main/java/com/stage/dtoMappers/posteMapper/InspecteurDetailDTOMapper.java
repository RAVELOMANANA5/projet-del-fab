package com.stage.dtoMappers.posteMapper;

import com.stage.constants.Constante;
import com.stage.dtos.posteDTOS.InspecteurDetailDTO;
import com.stage.models.poste.Inspecteur;
import com.stage.models.poste.Poste;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
public class InspecteurDetailDTOMapper implements Function<Inspecteur, InspecteurDetailDTO> {
    /**
     * Applies this function to the given argument.
     *
     * @param inspecteur the function argument
     * @return the function result
     */
    @Override
    public InspecteurDetailDTO apply(Inspecteur inspecteur) {
        return new InspecteurDetailDTO(
                inspecteur.getMatrInspect(),
                getOrDefault(() -> inspecteur.getNom(), Constante.INCONNU),
                getOrDefault(() -> inspecteur.getPrenom(), Constante.INCONNU),
                collectPosteAttributes(inspecteur, p -> getOrDefault(() ->
                        p.getNumPoste(), Constante.INCONNU)),
                collectPosteAttributes(inspecteur, p -> getOrDefault(() ->
                        p.getNomPoste(), Constante.INCONNU)),
                inspecteur.getPostes().size(),
                collectPosteAttributes(inspecteur, p -> getOrDefault(() ->
                        p.getSecteur().getNomSecteur(), Constante.INCONNU)),
                collectPosteAttributes(inspecteur, p -> getOrDefault(() ->
                        p.getSecteur().getZone().getNomZone(), Constante.INCONNU)),
                collectPosteAttributes(inspecteur, p -> getOrDefault(() ->
                        p.getSecteur().getZone().getRegion().getNomRegion(), Constante.INCONNU))
        );
    }

    /**
     * @param inspecteur
     * @param mapper
     * @return
     * @param <T>
     */
    private <T> String collectPosteAttributes(Inspecteur inspecteur, Function<Poste, T> mapper) {
        return inspecteur.getPostes()
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
