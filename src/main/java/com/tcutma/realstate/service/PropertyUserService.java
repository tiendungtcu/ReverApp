package com.tcutma.realstate.service;

import com.tcutma.realstate.service.dto.PropertyUserDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing PropertyUser.
 */
public interface PropertyUserService {

    /**
     * Save a propertyUser.
     *
     * @param propertyUserDTO the entity to save
     * @return the persisted entity
     */
    PropertyUserDTO save(PropertyUserDTO propertyUserDTO);

    /**
     * Get all the propertyUsers.
     *
     * @return the list of entities
     */
    List<PropertyUserDTO> findAll();


    /**
     * Get the "id" propertyUser.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<PropertyUserDTO> findOne(Long id);

    /**
     * Delete the "id" propertyUser.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
