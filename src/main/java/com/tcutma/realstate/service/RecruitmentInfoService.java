package com.tcutma.realstate.service;

import com.tcutma.realstate.service.dto.RecruitmentInfoDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing RecruitmentInfo.
 */
public interface RecruitmentInfoService {

    /**
     * Save a recruitmentInfo.
     *
     * @param recruitmentInfoDTO the entity to save
     * @return the persisted entity
     */
    RecruitmentInfoDTO save(RecruitmentInfoDTO recruitmentInfoDTO);

    /**
     * Get all the recruitmentInfos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RecruitmentInfoDTO> findAll(Pageable pageable);


    /**
     * Get the "id" recruitmentInfo.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<RecruitmentInfoDTO> findOne(Long id);

    /**
     * Delete the "id" recruitmentInfo.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
