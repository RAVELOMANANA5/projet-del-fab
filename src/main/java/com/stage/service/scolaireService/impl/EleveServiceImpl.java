package com.stage.service.scolaireService.impl;

import com.stage.dtoMappers.scolaireMapper.EleveDTOMapper;
import com.stage.dtos.scolaireDTOS.EleveDTO;
import com.stage.exception.ResourceNotFoundException;
import com.stage.models.scolaire.Eleve;
import com.stage.repository.scolaireRepository.EleveRepository;
import com.stage.service.scolaireService.EleveService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EleveServiceImpl implements EleveService {

    private final EleveRepository eleveRepository;
    private final EleveDTOMapper eleveDTOMapper;

    public EleveServiceImpl(EleveRepository eleveRepository, EleveDTOMapper eleveDTOMapper) {
        this.eleveRepository = eleveRepository;
        this.eleveDTOMapper = eleveDTOMapper;
    }

    /**
     * @param eleve
     */
    @Override
    public void addEleve(Eleve eleve) {
        eleveRepository.save(eleve);
    }

    /**
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public Map<String, Object> getAllEleves(int pageNo, int pageSize) {
        Page<Eleve> eleves = eleveRepository.findAllActiveEleves(PageRequest.of(pageNo, pageSize));

        List<EleveDTO> eleveDTOS = eleves.getContent()
                .stream()
                .map(eleveDTOMapper)
                .collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("eleves", eleveDTOS);
        response.put("currentPage", eleves.getNumber());
        response.put("size", eleves.getSize());
        response.put("totalPages", eleves.getTotalPages());
        response.put("totalElements", eleves.getTotalElements());

        return response;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Map<String, Object> findEleveById(Long id) {
        EleveDTO eleveDTO = eleveRepository.findById(id)
                .stream()
                .map(eleveDTOMapper)
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Eleve n'existe pas avec id : " + id));

        Map<String, Object> response = new HashMap<>();
        response.put("eleve", eleveDTO);
        return response;
    }

    /**
     * @param id
     * @param eleve
     * @return
     */
    @Override
    public boolean updateEleve(Long id, Eleve eleve) {
        Eleve upadateEleve = eleveRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Eleve n'existe pas avec id: " + id));
        upadateEleve.setNomEleve(eleve.getNomEleve());
        upadateEleve.setPrenomEleve(eleve.getPrenomEleve());
        upadateEleve.setDateNaissEleve(eleve.getDateNaissEleve());
        upadateEleve.setLieuNaissEleve(eleve.getLieuNaissEleve());
        upadateEleve.setSexe(eleve.getSexe());
        upadateEleve.setPere(eleve.getPere());
        upadateEleve.setMere(eleve.getMere());
        upadateEleve.setProfessionPerent(eleve.getProfessionPerent());
        upadateEleve.setQuitteEnCoursAn(eleve.getQuitteEnCoursAn());
        upadateEleve.setDateQuitte(eleve.getDateQuitte());
        upadateEleve.setClasse(eleve.getClasse());
        upadateEleve.setClasseAnPasse(eleve.getClasseAnPasse());
        upadateEleve.setSituation(eleve.getSituation());
        upadateEleve.setAnnEntrer(eleve.getAnnEntrer());
        upadateEleve.setAnnSort(eleve.getAnnSort());
        upadateEleve.setAbr(eleve.getAbr());
        upadateEleve.setReligion(eleve.getReligion());
        upadateEleve.setNumCopie(eleve.getNumCopie());
        upadateEleve.setNmbFrereEtSoeur(eleve.getNmbFrereEtSoeur());
        upadateEleve.setPoste(eleve.getPoste());
        upadateEleve.setAnnScolaire(eleve.getAnnScolaire());
        eleveRepository.save(upadateEleve);
        return true;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public boolean deleteEleve(Long id) {
        Eleve eleve = eleveRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Eleve n'existe pas avec id: " + id));
            eleveRepository.delete(eleve);
        return true;
    }
}
