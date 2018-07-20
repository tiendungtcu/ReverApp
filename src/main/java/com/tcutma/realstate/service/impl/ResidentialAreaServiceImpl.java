package com.tcutma.realstate.service.impl;

import com.tcutma.realstate.service.ResidentialAreaService;
import com.tcutma.realstate.domain.ResidentialArea;
import com.tcutma.realstate.repository.ResidentialAreaRepository;
import com.tcutma.realstate.service.dto.ResidentialAreaDTO;
import com.tcutma.realstate.service.mapper.ResidentialAreaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing ResidentialArea.
 */
@Service
@Transactional
public class ResidentialAreaServiceImpl implements ResidentialAreaService {

    private final Logger log = LoggerFactory.getLogger(ResidentialAreaServiceImpl.class);

    private final ResidentialAreaRepository residentialAreaRepository;

    private final ResidentialAreaMapper residentialAreaMapper;

    public ResidentialAreaServiceImpl(ResidentialAreaRepository residentialAreaRepository, ResidentialAreaMapper residentialAreaMapper) {
        this.residentialAreaRepository = residentialAreaRepository;
        this.residentialAreaMapper = residentialAreaMapper;
    }

    /**
     * Save a residentialArea.
     *
     * @param residentialAreaDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ResidentialAreaDTO save(ResidentialAreaDTO residentialAreaDTO) {
        log.debug("Request to save ResidentialArea : {}", residentialAreaDTO);
        ResidentialArea residentialArea = residentialAreaMapper.toEntity(residentialAreaDTO);
        residentialArea = residentialAreaRepository.save(residentialArea);
        return residentialAreaMapper.toDto(residentialArea);
    }

    /**
     * Get all the residentialAreas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ResidentialAreaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ResidentialAreas");
        return residentialAreaRepository.findAll(pageable)
            .map(residentialAreaMapper::toDto);
    }

    /**
     * Get all the ResidentialArea with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<ResidentialAreaDTO> findAllWithEagerRelationships(Pageable pageable) {
        return residentialAreaRepository.findAllWithEagerRelationships(pageable).map(residentialAreaMapper::toDto);
    }
    

    /**
     * Get one residentialArea by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ResidentialAreaDTO> findOne(Long id) {
        log.debug("Request to get ResidentialArea : {}", id);
        return residentialAreaRepository.findOneWithEagerRelationships(id)
            .map(residentialAreaMapper::toDto);
    }

    /**
     * Delete the residentialArea by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ResidentialArea : {}", id);
        residentialAreaRepository.deleteById(id);
    }
}
