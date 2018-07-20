package com.tcutma.realstate.service;

import com.tcutma.realstate.service.dto.SupportCategoryDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing SupportCategory.
 */
public interface SupportCategoryService {

    /**
     * Save a supportCategory.
     *
     * @param supportCategoryDTO the entity to save
     * @return the persisted entity
     */
    SupportCategoryDTO save(SupportCategoryDTO supportCategoryDTO);

    /**
     * Get all the supportCategories.
     *
     * @return the list of entities
     */
    List<SupportCategoryDTO> findAll();


    /**
     * Get the "id" supportCategory.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SupportCategoryDTO> findOne(Long id);

    /**
     * Delete the "id" supportCategory.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
