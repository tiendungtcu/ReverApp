package com.tcutma.realstate.service.impl;

import com.tcutma.realstate.service.PropertyService;
import com.tcutma.realstate.domain.Property;
import com.tcutma.realstate.repository.PropertyRepository;
import com.tcutma.realstate.service.dto.PropertyDTO;
import com.tcutma.realstate.service.mapper.PropertyMapper;
import com.tcutma.realstate.web.rest.vm.PropertyVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing Property.
 */
@Service
@Transactional
public class PropertyServiceImpl implements PropertyService {

    private final Logger log = LoggerFactory.getLogger(PropertyServiceImpl.class);

    private final PropertyRepository propertyRepository;

    private final PropertyMapper propertyMapper;

    public PropertyServiceImpl(PropertyRepository propertyRepository, PropertyMapper propertyMapper) {
        this.propertyRepository = propertyRepository;
        this.propertyMapper = propertyMapper;
    }

    /**
     * Save a property.
     *
     * @param propertyDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PropertyDTO save(PropertyDTO propertyDTO) {
        log.debug("Request to save Property : {}", propertyDTO);
        Property property = propertyMapper.toEntity(propertyDTO);
        property = propertyRepository.save(property);
        return propertyMapper.toDto(property);
    }

    /**
     * Get all the properties.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PropertyDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Properties");
        return propertyRepository.findAll(pageable)
            .map(propertyMapper::toDto);
    }

    /**
     * Get all the Property with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<PropertyDTO> findAllWithEagerRelationships(Pageable pageable) {
        return propertyRepository.findAllWithEagerRelationships(pageable).map(propertyMapper::toDto);
    }


    /**
     * Get one property by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PropertyDTO> findOne(Long id) {
        log.debug("Request to get Property : {}", id);
        return propertyRepository.findOneWithEagerRelationships(id)
            .map(propertyMapper::toDto);
    }

    /**
     * Delete the property by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Property : {}", id);
        propertyRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PropertyVM> findMyOne(Long id) {
        log.debug("Request to get Property : {}", id);
        return propertyRepository.findOneWithEagerRelationships(id)
            .map(propertyMapper::toVM);
    }
}
