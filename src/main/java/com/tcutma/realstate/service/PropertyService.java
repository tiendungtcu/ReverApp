package com.tcutma.realstate.service;

import com.tcutma.realstate.service.dto.PropertyDTO;

import com.tcutma.realstate.web.rest.vm.PropertyVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Property.
 */
public interface PropertyService {

    /**
     * Save a property.
     *
     * @param propertyDTO the entity to save
     * @return the persisted entity
     */
    PropertyDTO save(PropertyDTO propertyDTO);

    /**
     * Get all the properties.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PropertyDTO> findAll(Pageable pageable);

    /**
     * Get all the Property with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<PropertyDTO> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" property.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<PropertyDTO> findOne(Long id);

    /**
     * Delete the "id" property.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Get the "id" of my property.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<PropertyVM> findMyOne(Long id);
}
