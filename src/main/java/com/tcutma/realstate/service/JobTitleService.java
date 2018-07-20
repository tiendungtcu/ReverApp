package com.tcutma.realstate.service;

import com.tcutma.realstate.service.dto.JobTitleDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing JobTitle.
 */
public interface JobTitleService {

    /**
     * Save a jobTitle.
     *
     * @param jobTitleDTO the entity to save
     * @return the persisted entity
     */
    JobTitleDTO save(JobTitleDTO jobTitleDTO);

    /**
     * Get all the jobTitles.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<JobTitleDTO> findAll(Pageable pageable);


    /**
     * Get the "id" jobTitle.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<JobTitleDTO> findOne(Long id);

    /**
     * Delete the "id" jobTitle.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
