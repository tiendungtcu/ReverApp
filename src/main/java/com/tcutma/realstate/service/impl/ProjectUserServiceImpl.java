package com.tcutma.realstate.service.impl;

import com.tcutma.realstate.service.ProjectUserService;
import com.tcutma.realstate.domain.ProjectUser;
import com.tcutma.realstate.repository.ProjectUserRepository;
import com.tcutma.realstate.service.dto.ProjectUserDTO;
import com.tcutma.realstate.service.mapper.ProjectUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing ProjectUser.
 */
@Service
@Transactional
public class ProjectUserServiceImpl implements ProjectUserService {

    private final Logger log = LoggerFactory.getLogger(ProjectUserServiceImpl.class);

    private final ProjectUserRepository projectUserRepository;

    private final ProjectUserMapper projectUserMapper;

    public ProjectUserServiceImpl(ProjectUserRepository projectUserRepository, ProjectUserMapper projectUserMapper) {
        this.projectUserRepository = projectUserRepository;
        this.projectUserMapper = projectUserMapper;
    }

    /**
     * Save a projectUser.
     *
     * @param projectUserDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ProjectUserDTO save(ProjectUserDTO projectUserDTO) {
        log.debug("Request to save ProjectUser : {}", projectUserDTO);
        ProjectUser projectUser = projectUserMapper.toEntity(projectUserDTO);
        projectUser = projectUserRepository.save(projectUser);
        return projectUserMapper.toDto(projectUser);
    }

    /**
     * Get all the projectUsers.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ProjectUserDTO> findAll() {
        log.debug("Request to get all ProjectUsers");
        return projectUserRepository.findAll().stream()
            .map(projectUserMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one projectUser by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ProjectUserDTO> findOne(Long id) {
        log.debug("Request to get ProjectUser : {}", id);
        return projectUserRepository.findById(id)
            .map(projectUserMapper::toDto);
    }

    /**
     * Delete the projectUser by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProjectUser : {}", id);
        projectUserRepository.deleteById(id);
    }
}
