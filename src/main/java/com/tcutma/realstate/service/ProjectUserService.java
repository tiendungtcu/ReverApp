package com.tcutma.realstate.service;

import com.tcutma.realstate.service.dto.ProjectUserDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing ProjectUser.
 */
public interface ProjectUserService {

    /**
     * Save a projectUser.
     *
     * @param projectUserDTO the entity to save
     * @return the persisted entity
     */
    ProjectUserDTO save(ProjectUserDTO projectUserDTO);

    /**
     * Get all the projectUsers.
     *
     * @return the list of entities
     */
    List<ProjectUserDTO> findAll();


    /**
     * Get the "id" projectUser.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ProjectUserDTO> findOne(Long id);

    /**
     * Delete the "id" projectUser.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
