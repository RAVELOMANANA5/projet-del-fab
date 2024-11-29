package com.stage.service.posteService.impl;

import com.stage.dtoMappers.posteMapper.CommuneDTOMapper;
import com.stage.dtos.posteDTOS.CommuneOrCiscoDTO;
import com.stage.exception.ResourceNotFoundException;
import com.stage.models.poste.Commune;
import com.stage.repository.posteRepository.CommuneRepository;
import com.stage.service.posteService.CommuneService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CommuneServiceImpl implements CommuneService {
    private final CommuneRepository communeRepository;
    private final CommuneDTOMapper communeDTOMapper;

    public CommuneServiceImpl(CommuneRepository communeRepository, CommuneDTOMapper communeDTOMapper) {
        this.communeRepository = communeRepository;
        this.communeDTOMapper = communeDTOMapper;
    }

    /**
     * @param commune
     */
    @Override
    public void addCommune(Commune commune) {
            communeRepository.save(commune);
    }

    /**
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public Map<String, Object> getCommunes(int pageNo, int pageSize) {
        Page<Commune> communes = communeRepository.findAll(PageRequest.of(pageNo, pageSize));
        List<CommuneOrCiscoDTO> content = communes.getContent()
                .stream()
                .map(communeDTOMapper)
                .collect(Collectors.toList());

        Map<String, Object> map = new HashMap<>();
        map.put("communes", content);
        map.put("currentPage", pageNo);
        map.put("pageSize", pageSize);
        map.put("totalPages", communes.getTotalPages());
        return map;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Map<String, Object> findCommuneById(Long id) {
        CommuneOrCiscoDTO communeOrCiscoDTO = communeRepository.findById(id)
                .stream()
                .map(communeDTOMapper)
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Commune n'existe pas"));

        Map<String, Object> map = new HashMap<>();
        map.put("commune", communeOrCiscoDTO);
        return map;
    }

    /**
     * @param id
     * @param commune
     * @return
     */
    @Override
    public boolean updateCommune(Long id, Commune commune) {
        Commune byId = communeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Commune " + id + " n'existe pas"));
        byId.setNom(commune.getNom());
        byId.setPostes(commune.getPostes());
        communeRepository.save(byId);
        return true;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public boolean deleteCommune(Long id) {
        communeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Commune " + id + " n'existe pas"));
        communeRepository.deleteById(id);
        return true;
    }
}
