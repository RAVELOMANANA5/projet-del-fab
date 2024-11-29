package com.stage.service.posteService.impl;

import com.stage.dtoMappers.posteMapper.ZoneDTOMapper;
import com.stage.dtos.posteDTOS.ZoneDTO;
import com.stage.exception.ResourceAlreadyExistsException;
import com.stage.exception.ResourceNotFoundException;
import com.stage.models.poste.Zone;
import com.stage.repository.posteRepository.ZoneRepository;
import com.stage.service.posteService.ZoneService;
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
public class ZoneServiceImpl implements ZoneService {

    private final ZoneRepository zoneRepository;
    private final ZoneDTOMapper zoneDTOMapper;

    public ZoneServiceImpl(ZoneRepository zoneRepository, ZoneDTOMapper zoneDTOMapper) {
        this.zoneRepository = zoneRepository;
        this.zoneDTOMapper = zoneDTOMapper;
    }

    /**
     * @param zone
     */
    public void addZone(Zone zone) {
        Optional<Zone> byNumZone = zoneRepository.findByNumZone(zone.getNumZone());
        if (byNumZone.isPresent()) {
            throw new ResourceAlreadyExistsException("Numéro poste est déjà existente avec numéro : " + zone.getNumZone());
        }
        zoneRepository.save(zone);
    }

    /**
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Map<String, Object> getZones(int pageNo, int pageSize) {

        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Zone> zonePage = zoneRepository.findAll(pageable);

        List<ZoneDTO> zoneContents = zonePage.getContent()
                .stream()
                .map(zoneDTOMapper)
                .collect(Collectors.toList());

        Map<String, Object> map = new HashMap<>();
        map.put("zones", zoneContents);
        map.put("currentPage", zonePage.getNumber());
        map.put("pageSize", pageSize);
        map.put("totalPages", zonePage.getTotalPages());
        map.put("totalElements", zonePage.getTotalElements());
        return map;
    }

    /**
     * @param pageNo
     * @param pageSize
     * @param nomRegion
     * @return
     */
    public Map<String, Object> findZonesByNomRegion(int pageNo, int pageSize, String nomRegion) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Zone> zonePage = zoneRepository.findAll(pageable);

        List<ZoneDTO> zoneContents = zonePage.getContent()
                .stream()
                .filter(zone -> zone.getRegion().getNomRegion().contains(nomRegion))
                .map(zoneDTOMapper)
                .collect(Collectors.toList());

        long count = zoneContents.stream().count();

        Map<String, Object> map = new HashMap<>();
        map.put("zones", zoneContents);
        map.put("currentPage", pageNo);
        map.put("pageSize", pageSize);
        map.put("count", count);
        map.put("totalPages", zonePage.getTotalPages());

        return map;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public ZoneDTO findZoneByID(Long id) {
        return zoneRepository.findById(id)
                .stream()
                .map(zoneDTOMapper)
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Zone n'existe pas avec id : " + id));
    }

    /**
     * @param id
     * @param zone
     * @return
     */
    @Override
    public boolean updateZone(Long id, Zone zone) {
        Zone byId = zoneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Zone " + id + " n'existe pas"));
        byId.setNomZone(zone.getNomZone());
        byId.setRegion(zone.getRegion());
        byId.setSecteurs(zone.getSecteurs());
        zoneRepository.save(byId);
        return true;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public boolean deleteZone(Long id) {
        zoneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Zone " + id + " n'existe pas"));
        zoneRepository.deleteById(id);
        return true;
    }

}
