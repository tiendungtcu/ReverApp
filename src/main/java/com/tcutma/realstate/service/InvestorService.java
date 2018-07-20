package com.tcutma.realstate.service;

import com.tcutma.realstate.service.dto.InvestorDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Investor.
 */
public interface InvestorService {

    /**
     * Save a investor.
     *
     * @param investorDTO the entity to save
     * @return the persisted entity
     */
    InvestorDTO save(InvestorDTO investorDTO);

    /**
     * Get all the investors.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<InvestorDTO> findAll(Pageable pageable);


    /**
     * Get the "id" investor.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<InvestorDTO> findOne(Long id);

    /**
     * Delete the "id" investor.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
