package com.tcutma.realstate.service;

import com.tcutma.realstate.service.dto.ProjectBuilderDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing ProjectBuilder.
 */
public interface ProjectBuilderService {

    /**
     * Save a projectBuilder.
     *
     * @param projectBuilderDTO the entity to save
     * @return the persisted entity
     */
    ProjectBuilderDTO save(ProjectBuilderDTO projectBuilderDTO);

    /**
     * Get all the projectBuilders.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ProjectBuilderDTO> findAll(Pageable pageable);


    /**
     * Get the "id" projectBuilder.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ProjectBuilderDTO> findOne(Long id);

    /**
     * Delete the "id" projectBuilder.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
