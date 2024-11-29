package com.stage.service.posteService.impl;

import com.stage.dtoMappers.posteMapper.RegionDTOMapper;
import com.stage.dtos.posteDTOS.RegionDTO;
import com.stage.exception.ResourceAlreadyExistsException;
import com.stage.exception.ResourceNotFoundException;
import com.stage.models.poste.Region;
import com.stage.repository.posteRepository.RegionRepository;
import com.stage.service.posteService.RegionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegionServiceImpl implements RegionService {

    private final RegionRepository regionRepository;
    private final RegionDTOMapper regionDTOMapper;

    public RegionServiceImpl(RegionRepository regionRepository, RegionDTOMapper regionDTOMapper) {
        this.regionRepository = regionRepository;
        this.regionDTOMapper = regionDTOMapper;
    }

    /**
     * @param region
     */
    public void addRegion(Region region) {
        Optional<Region> existingRegion = regionRepository.findByNumRegion(region.getNumRegion());
        if (existingRegion.isPresent()) {
            throw new ResourceAlreadyExistsException("NumRegion est déjà existe avec numéro : " + region.getNumRegion());
        }
        regionRepository.save(region);
    }

    /**
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Map<String, Object> getRegions(int pageNo, int pageSize) {

        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Region> regionPage = regionRepository.findAll(pageable);

        List<RegionDTO> regionsContent = regionPage.getContent()
                .stream()
                .map(regionDTOMapper)
                .collect(Collectors.toList());

        Map<String, Object> result = new HashMap<>();
        result.put("regions", regionsContent);
        result.put("pageNo", pageNo);
        result.put("pageSize", pageSize);
        result.put("currentPage", regionPage.getNumber());
        result.put("totalPages", regionPage.getTotalPages());
        result.put("totalElements", regionPage.getTotalElements());

        return result;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Map<String, Object> findRegionById(Long id) {
        RegionDTO regionDTO = regionRepository.findById(id)
                .stream()
                .map(regionDTOMapper)
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Region " + id + " n'existe pas"));

        Map<String, Object> response = new HashMap<>();
        response.put("region", regionDTO);
        return response;
    }

    /**
     * @param id
     * @param region
     * @return
     */
    @Override
    public boolean updateRegion(Long id, Region region) {
        Region byId = regionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Region " + id + " n'existe pas"));
        byId.setNomRegion(region.getNomRegion());
        byId.setZones(region.getZones());
        regionRepository.save(byId);
        return true;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public boolean deleteRegion(Long id) {
        regionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Region " + id + " n'existe pas"));
        regionRepository.deleteById(id);
        return true;
    }

}
