package com.tcutma.realstate.service.impl;

import com.tcutma.realstate.service.JobTitleService;
import com.tcutma.realstate.domain.JobTitle;
import com.tcutma.realstate.repository.JobTitleRepository;
import com.tcutma.realstate.service.dto.JobTitleDTO;
import com.tcutma.realstate.service.mapper.JobTitleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing JobTitle.
 */
@Service
@Transactional
public class JobTitleServiceImpl implements JobTitleService {

    private final Logger log = LoggerFactory.getLogger(JobTitleServiceImpl.class);

    private final JobTitleRepository jobTitleRepository;

    private final JobTitleMapper jobTitleMapper;

    public JobTitleServiceImpl(JobTitleRepository jobTitleRepository, JobTitleMapper jobTitleMapper) {
        this.jobTitleRepository = jobTitleRepository;
        this.jobTitleMapper = jobTitleMapper;
    }

    /**
     * Save a jobTitle.
     *
     * @param jobTitleDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public JobTitleDTO save(JobTitleDTO jobTitleDTO) {
        log.debug("Request to save JobTitle : {}", jobTitleDTO);
        JobTitle jobTitle = jobTitleMapper.toEntity(jobTitleDTO);
        jobTitle = jobTitleRepository.save(jobTitle);
        return jobTitleMapper.toDto(jobTitle);
    }

    /**
     * Get all the jobTitles.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<JobTitleDTO> findAll(Pageable pageable) {
        log.debug("Request to get all JobTitles");
        return jobTitleRepository.findAll(pageable)
            .map(jobTitleMapper::toDto);
    }


    /**
     * Get one jobTitle by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<JobTitleDTO> findOne(Long id) {
        log.debug("Request to get JobTitle : {}", id);
        return jobTitleRepository.findById(id)
            .map(jobTitleMapper::toDto);
    }

    /**
     * Delete the jobTitle by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete JobTitle : {}", id);
        jobTitleRepository.deleteById(id);
    }
}
