package com.stage.service.posteService.impl;

import com.stage.dtoMappers.posteMapper.PosteDTOMapper;
import com.stage.dtos.posteDTOS.PostCountPerClassDTO;
import com.stage.dtos.posteDTOS.PostCountPerYearDTO;
import com.stage.dtos.posteDTOS.PosteDTO;
import com.stage.exception.ResourceAlreadyExistsException;
import com.stage.exception.ResourceNotFoundException;
import com.stage.models.poste.Poste;
import com.stage.repository.posteRepository.PosteRepository;
import com.stage.service.posteService.PosteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.Year;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PosteServiceImpl implements PosteService {

    private final PosteRepository posteRepository;
    private final PosteDTOMapper posteDTOMapper;
    public PosteServiceImpl(PosteRepository posteRepository, PosteDTOMapper posteDTOMapper) {
        this.posteRepository = posteRepository;
        this.posteDTOMapper = posteDTOMapper;
    }

    /**
     * @param poste
     */
    @Override
    public void addPoste(Poste poste) {
        Optional<Poste> byNumPoste = posteRepository.findByNumPoste(poste.getNumPoste());
        if (byNumPoste.isPresent()) {
            throw new ResourceAlreadyExistsException("Numéro Poste " + poste.getNomPoste() + " est déjà existe");
        }
        posteRepository.save(poste);
    }

    /**
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public Map<String, Object> getAllPostes(int pageNo, int pageSize) {
        Pageable pageRequest = PageRequest.of(pageNo, pageSize);
        Page<Poste> all = posteRepository.findAll(pageRequest);
        List<PosteDTO> collect = all.getContent()
                .stream()
                .map(posteDTOMapper)
                .collect(Collectors.toList());

        Map<String, Object> map = new HashMap<>();
        map.put("postes", collect);
        map.put("currentPage", all.getNumber());
        map.put("totalPages", all.getTotalPages());
        map.put("totalElement", all.getTotalElements());
        return map;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Map<String, Object> findPosteById(Long id) {
        PosteDTO byId = posteRepository.findById(id)
                .stream()
                .map(posteDTOMapper)
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Poste " + id + " not found"));

            Map<String, Object> map = new HashMap<>();
            map.put("poste", byId);
            return map;
    }

    /**
     * @return
     */
    @Override
    public Map<String, Object> findPaginatedPostsByOpeningYear (int pageNo, int pageSize) {
        Page<Object[]> postsPerYear = posteRepository.findPostsPerYear(PageRequest.of(pageNo, pageSize));

        List<PostCountPerYearDTO> postCounts = postsPerYear.getContent()
                .stream()
                .map(result -> new PostCountPerYearDTO((Year) result[0], (Long) result[1]))
                .collect(Collectors.toList());

        Map<String, Object> map = new HashMap<>();
        map.put("postsPerYear", postCounts);
        map.put("currentPage", postsPerYear.getNumber());
        map.put("pageSize", postsPerYear.getTotalPages());
        map.put("totalPages", postsPerYear.getTotalPages());
        return map;
    }

    /**
     * @return
     */
    @Override
    public Map<String, Object> getCountElevesByClass(Integer pageNo, Integer pageSize) {
        Page<Object[]> countElevesByClass = posteRepository.eleveCountByClass(PageRequest.of(pageNo, pageSize));

        List<PostCountPerClassDTO> postCounts = countElevesByClass.getContent()
                .stream()
                .map(result -> new PostCountPerClassDTO((String) result[0], (Long) result[1]))
                .collect(Collectors.toList());

        Map<String, Object> map = new HashMap<>();
        map.put("countElevesByClass", postCounts);
        map.put("currentPage", countElevesByClass.getNumber());
        map.put("pageSize", countElevesByClass.getTotalPages());
        map.put("totalPages", countElevesByClass.getTotalPages());
        return map;
    }

    /**
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public Map<String, Object> getCountPostesByClass(Integer pageNo, Integer pageSize) {
        Page<Object[]> countPostesByClass = posteRepository.countPostesByClass(PageRequest.of(pageNo, pageSize));

        List<PostCountPerClassDTO> postCounts = countPostesByClass.getContent()
                .stream()
                .map(result -> new PostCountPerClassDTO((String) result[0], (Long) result[1]))
                .collect(Collectors.toList());

        Map<String, Object> map = new HashMap<>();
        map.put("countPostesByClass", postCounts);
        map.put("currentPage", countPostesByClass.getNumber());
        map.put("pageSize", countPostesByClass.getTotalPages());
        map.put("totalPages", countPostesByClass.getTotalPages());
        return map;
    }

    /**
     * @return
     */
    @Override
    public Map<String, Object> getMorningAndAfternoonPosts() {
        LocalTime morningStart = LocalTime.of(8, 0);
        LocalTime morningEnd = LocalTime.of(12, 0);
        LocalTime afternoonStart = LocalTime.of(13, 0);
        LocalTime afternoonEnd = LocalTime.of(17, 0);

        List<Object[]> results = posteRepository.findMorningAndAfternoonPosts(morningStart, morningEnd,
                afternoonStart, afternoonEnd);

        Map<String, Object> response = new HashMap<>();
        response.put("morningPosts", results.get(0));
        response.put("afternoonPosts", results.get(1));

        return response;
    }

    /**
     * @param id
     * @param poste
     * @return
     */
    @Override
    public boolean updatePoste(Long id, Poste poste) {
        Poste byId = posteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Poste " + id + " n'existe pas"));
            byId.setNomPoste(poste.getNomPoste());
            byId.setAnOuverPoste(poste.getAnOuverPoste());
            byId.setNomMateriel(poste.getNomMateriel());
            posteRepository.save(byId);
        return true;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public boolean deletePoste(Long id) {
        posteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Poste " + id + " n'existe pas"));
        posteRepository.deleteById(id);
            return true;
    }
}
