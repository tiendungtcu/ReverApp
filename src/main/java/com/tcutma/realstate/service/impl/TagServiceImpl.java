package com.tcutma.realstate.service.impl;

import com.tcutma.realstate.exception.FileStorageException;
import com.tcutma.realstate.service.TagService;
import com.tcutma.realstate.domain.Tag;
import com.tcutma.realstate.repository.TagRepository;
import com.tcutma.realstate.service.dto.TagDTO;
import com.tcutma.realstate.service.mapper.TagMapper;
import com.tcutma.realstate.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing Tag.
 */
@Service
@Transactional
public class TagServiceImpl implements TagService {

    private final Logger log = LoggerFactory.getLogger(TagServiceImpl.class);

    private final TagRepository tagRepository;

    private final TagMapper tagMapper;

    public TagServiceImpl(TagRepository tagRepository, TagMapper tagMapper) {
        this.tagRepository = tagRepository;
        this.tagMapper = tagMapper;
    }

    /**
     * Save a tag.
     *
     * @param tagDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public TagDTO save(TagDTO tagDTO) {
        log.debug("Request to save Tag : {}", tagDTO);
/*
        int found = this.findTagsByName(tagDTO.getTagName());
        if(found>0) {
            throw new BadRequestAlertException("has already a same tag name", "Tag", "nameexist");
        }
        log.info("found {} same tag name",found);
        */
        Tag tag = tagMapper.toEntity(tagDTO);
        tag = tagRepository.save(tag);
        return tagMapper.toDto(tag);
    }

    /**
     * Get all the tags.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<TagDTO> findAll() {
        log.debug("Request to get all Tags");
        return tagRepository.findAll().stream()
            .map(tagMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one tag by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TagDTO> findOne(Long id) {
        log.debug("Request to get Tag : {}", id);
        return tagRepository.findById(id)
            .map(tagMapper::toDto);
    }

    public int findTagsByName(String tagName){

        return tagRepository.findTagsByName(tagName);

    }

    /**
     * Delete the tag by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Tag : {}", id);
        tagRepository.deleteById(id);
    }
}
