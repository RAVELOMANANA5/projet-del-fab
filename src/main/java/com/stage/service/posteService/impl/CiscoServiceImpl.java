package com.stage.service.posteService.impl;

import com.stage.dtoMappers.posteMapper.CiscoDTOMapper;
import com.stage.dtos.posteDTOS.CommuneOrCiscoDTO;
import com.stage.exception.ResourceNotFoundException;
import com.stage.models.poste.Cisco;
import com.stage.repository.posteRepository.CiscoRepository;
import com.stage.service.posteService.CiscoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CiscoServiceImpl implements CiscoService {

    private final CiscoRepository ciscoRepository;
    private final CiscoDTOMapper ciscoDTOMapper;

    public CiscoServiceImpl(CiscoRepository ciscoRepository, CiscoDTOMapper ciscoDTOMapper) {
        this.ciscoRepository = ciscoRepository;
        this.ciscoDTOMapper = ciscoDTOMapper;
    }

    /**
     * @param cisco
     */
    @Override
    public void addCisco(Cisco cisco) {
            ciscoRepository.save(cisco);
    }

    /**
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public Map<String, Object> getAllCisco(int pageNo, int pageSize) {
        Page<Cisco> inspecteurList = ciscoRepository.findAll(PageRequest.of(pageNo, pageSize));

        List<CommuneOrCiscoDTO> ciscoList = inspecteurList.getContent()
                .stream()
                .map(ciscoDTOMapper)
                .collect(Collectors.toList());

        Map<String, Object> result = new HashMap<>();
        result.put("ciscoList", ciscoList);
        result.put("currentPage", inspecteurList.getNumber());
        result.put("pageSize", inspecteurList.getSize());
        result.put("totalPages", inspecteurList.getTotalPages());
        return result;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Map<String, Object> findCiscoById(Long id) {

        CommuneOrCiscoDTO communeOrCiscoDTO = ciscoRepository.findById(id)
                .stream()
                .map(ciscoDTOMapper)
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Commune n'existe pas"));

        Map<String, Object> result = new HashMap<>();
        result.put("communeOrCiscoDTO", communeOrCiscoDTO);
        return result;
    }

    /**
     * @param id
     * @param cisco
     * @return
     */
    @Override
    public boolean updateCisco(Long id, Cisco cisco) {
        Cisco byId = ciscoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cisco " + id + " n'existe pas"));
        byId.setNom(cisco.getNom());
        byId.setPostes(cisco.getPostes());
        ciscoRepository.save(byId);
        return true;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public boolean deleteCisco(Long id) {
        ciscoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cisco " + id + " n'existe pas"));
        ciscoRepository.deleteById(id);
        return true;
    }

}
