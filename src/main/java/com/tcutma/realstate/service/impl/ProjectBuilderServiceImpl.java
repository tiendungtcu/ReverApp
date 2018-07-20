package com.tcutma.realstate.service.impl;

import com.tcutma.realstate.service.ProjectBuilderService;
import com.tcutma.realstate.domain.ProjectBuilder;
import com.tcutma.realstate.repository.ProjectBuilderRepository;
import com.tcutma.realstate.service.dto.ProjectBuilderDTO;
import com.tcutma.realstate.service.mapper.ProjectBuilderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing ProjectBuilder.
 */
@Service
@Transactional
public class ProjectBuilderServiceImpl implements ProjectBuilderService {

    private final Logger log = LoggerFactory.getLogger(ProjectBuilderServiceImpl.class);

    private final ProjectBuilderRepository projectBuilderRepository;

    private final ProjectBuilderMapper projectBuilderMapper;

    public ProjectBuilderServiceImpl(ProjectBuilderRepository projectBuilderRepository, ProjectBuilderMapper projectBuilderMapper) {
        this.projectBuilderRepository = projectBuilderRepository;
        this.projectBuilderMapper = projectBuilderMapper;
    }

    /**
     * Save a projectBuilder.
     *
     * @param projectBuilderDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ProjectBuilderDTO save(ProjectBuilderDTO projectBuilderDTO) {
        log.debug("Request to save ProjectBuilder : {}", projectBuilderDTO);
        ProjectBuilder projectBuilder = projectBuilderMapper.toEntity(projectBuilderDTO);
        projectBuilder = projectBuilderRepository.save(projectBuilder);
        return projectBuilderMapper.toDto(projectBuilder);
    }

    /**
     * Get all the projectBuilders.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ProjectBuilderDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ProjectBuilders");
        return projectBuilderRepository.findAll(pageable)
            .map(projectBuilderMapper::toDto);
    }


    /**
     * Get one projectBuilder by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ProjectBuilderDTO> findOne(Long id) {
        log.debug("Request to get ProjectBuilder : {}", id);
        return projectBuilderRepository.findById(id)
            .map(projectBuilderMapper::toDto);
    }

    /**
     * Delete the projectBuilder by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProjectBuilder : {}", id);
        projectBuilderRepository.deleteById(id);
    }
}
