package com.tcutma.realstate.service;

import com.tcutma.realstate.service.dto.ExchangerDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Exchanger.
 */
public interface ExchangerService {

    /**
     * Save a exchanger.
     *
     * @param exchangerDTO the entity to save
     * @return the persisted entity
     */
    ExchangerDTO save(ExchangerDTO exchangerDTO);

    /**
     * Get all the exchangers.
     *
     * @return the list of entities
     */
    List<ExchangerDTO> findAll();


    /**
     * Get the "id" exchanger.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ExchangerDTO> findOne(Long id);

    /**
     * Delete the "id" exchanger.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
