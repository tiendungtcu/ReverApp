package com.tcutma.realstate.service.impl;

import com.tcutma.realstate.service.PropertyUserService;
import com.tcutma.realstate.domain.PropertyUser;
import com.tcutma.realstate.repository.PropertyUserRepository;
import com.tcutma.realstate.service.dto.PropertyUserDTO;
import com.tcutma.realstate.service.mapper.PropertyUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing PropertyUser.
 */
@Service
@Transactional
public class PropertyUserServiceImpl implements PropertyUserService {

    private final Logger log = LoggerFactory.getLogger(PropertyUserServiceImpl.class);

    private final PropertyUserRepository propertyUserRepository;

    private final PropertyUserMapper propertyUserMapper;

    public PropertyUserServiceImpl(PropertyUserRepository propertyUserRepository, PropertyUserMapper propertyUserMapper) {
        this.propertyUserRepository = propertyUserRepository;
        this.propertyUserMapper = propertyUserMapper;
    }

    /**
     * Save a propertyUser.
     *
     * @param propertyUserDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PropertyUserDTO save(PropertyUserDTO propertyUserDTO) {
        log.debug("Request to save PropertyUser : {}", propertyUserDTO);
        PropertyUser propertyUser = propertyUserMapper.toEntity(propertyUserDTO);
        propertyUser = propertyUserRepository.save(propertyUser);
        return propertyUserMapper.toDto(propertyUser);
    }

    /**
     * Get all the propertyUsers.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<PropertyUserDTO> findAll() {
        log.debug("Request to get all PropertyUsers");
        return propertyUserRepository.findAll().stream()
            .map(propertyUserMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one propertyUser by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PropertyUserDTO> findOne(Long id) {
        log.debug("Request to get PropertyUser : {}", id);
        return propertyUserRepository.findById(id)
            .map(propertyUserMapper::toDto);
    }

    /**
     * Delete the propertyUser by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PropertyUser : {}", id);
        propertyUserRepository.deleteById(id);
    }
}
