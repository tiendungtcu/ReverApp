package com.tcutma.realstate.service.impl;

import com.tcutma.realstate.service.SupportCategoryService;
import com.tcutma.realstate.domain.SupportCategory;
import com.tcutma.realstate.repository.SupportCategoryRepository;
import com.tcutma.realstate.service.dto.SupportCategoryDTO;
import com.tcutma.realstate.service.mapper.SupportCategoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing SupportCategory.
 */
@Service
@Transactional
public class SupportCategoryServiceImpl implements SupportCategoryService {

    private final Logger log = LoggerFactory.getLogger(SupportCategoryServiceImpl.class);

    private final SupportCategoryRepository supportCategoryRepository;

    private final SupportCategoryMapper supportCategoryMapper;

    public SupportCategoryServiceImpl(SupportCategoryRepository supportCategoryRepository, SupportCategoryMapper supportCategoryMapper) {
        this.supportCategoryRepository = supportCategoryRepository;
        this.supportCategoryMapper = supportCategoryMapper;
    }

    /**
     * Save a supportCategory.
     *
     * @param supportCategoryDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public SupportCategoryDTO save(SupportCategoryDTO supportCategoryDTO) {
        log.debug("Request to save SupportCategory : {}", supportCategoryDTO);
        SupportCategory supportCategory = supportCategoryMapper.toEntity(supportCategoryDTO);
        supportCategory = supportCategoryRepository.save(supportCategory);
        return supportCategoryMapper.toDto(supportCategory);
    }

    /**
     * Get all the supportCategories.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<SupportCategoryDTO> findAll() {
        log.debug("Request to get all SupportCategories");
        return supportCategoryRepository.findAll().stream()
            .map(supportCategoryMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one supportCategory by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SupportCategoryDTO> findOne(Long id) {
        log.debug("Request to get SupportCategory : {}", id);
        return supportCategoryRepository.findById(id)
            .map(supportCategoryMapper::toDto);
    }

    /**
     * Delete the supportCategory by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SupportCategory : {}", id);
        supportCategoryRepository.deleteById(id);
    }
}
