package com.tcutma.realstate.service.impl;

import com.tcutma.realstate.domain.Photo;
import com.tcutma.realstate.domain.Tag;
import com.tcutma.realstate.domain.enumeration.UploadType;
import com.tcutma.realstate.repository.PhotoRepository;
import com.tcutma.realstate.repository.TagRepository;
import com.tcutma.realstate.service.FileStorageService;
import com.tcutma.realstate.service.ResidentialAreaService;
import com.tcutma.realstate.domain.ResidentialArea;
import com.tcutma.realstate.repository.ResidentialAreaRepository;
import com.tcutma.realstate.service.dto.ResidentialAreaDTO;
import com.tcutma.realstate.service.dto.TagDTO;
import com.tcutma.realstate.service.mapper.PhotoMapper;
import com.tcutma.realstate.service.mapper.ResidentialAreaMapper;
import com.tcutma.realstate.service.mapper.TagMapper;
import com.tcutma.realstate.web.rest.vm.UploadFileResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import java.util.*;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing ResidentialArea.
 */
@Service
@Transactional
public class ResidentialAreaServiceImpl implements ResidentialAreaService {

    private final Logger log = LoggerFactory.getLogger(ResidentialAreaServiceImpl.class);

    private final ResidentialAreaRepository residentialAreaRepository;

    private final TagRepository tagRepository; // my added code

    private final FileStorageService fileStorageService ; // my added code

    private final ResidentialAreaMapper residentialAreaMapper;

    private final TagMapper tagMapper;


    public ResidentialAreaServiceImpl(ResidentialAreaRepository residentialAreaRepository, TagRepository tagRepository,FileStorageService fileStorageService, ResidentialAreaMapper residentialAreaMapper, TagMapper tagMapper) {
        this.residentialAreaRepository = residentialAreaRepository;
        this.tagRepository = tagRepository; // my added code
        this.fileStorageService = fileStorageService; // my added code
        this.residentialAreaMapper = residentialAreaMapper;
        this.tagMapper = tagMapper; // my added code
    }

    /**
     * Save a residentialArea.
     *
     * @param residentialAreaDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ResidentialAreaDTO save(ResidentialAreaDTO residentialAreaDTO) {
        log.debug("Request to save ResidentialArea : {}", residentialAreaDTO);
        ResidentialArea residentialArea = residentialAreaMapper.toEntity(residentialAreaDTO);
        residentialArea = residentialAreaRepository.save(residentialArea);
        return residentialAreaMapper.toDto(residentialArea);
    }

    /**
     * Get all the residentialAreas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ResidentialAreaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ResidentialAreas");
        return residentialAreaRepository.findAll(pageable)
            .map(residentialAreaMapper::toDto);
    }

    /**
     * Get all the ResidentialArea with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<ResidentialAreaDTO> findAllWithEagerRelationships(Pageable pageable) {
        return residentialAreaRepository.findAllWithEagerRelationships(pageable).map(residentialAreaMapper::toDto);
    }


    /**
     * Get one residentialArea by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ResidentialAreaDTO> findOne(Long id) {
        log.debug("Request to get ResidentialArea : {}", id);
        return residentialAreaRepository.findOneWithEagerRelationships(id)
            .map(residentialAreaMapper::toDto);
    }

    /**
     * Delete the residentialArea by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ResidentialArea : {}", id);
        residentialAreaRepository.deleteById(id);
    }

    @Override
    public Optional<TagDTO> addTag(Long raId, TagDTO tagDTO) {

        log.debug("Request to add Tag : {} to residential area {}" , tagDTO,raId);

        // Find the RA
        Optional<ResidentialArea> raOption = residentialAreaRepository.findOneWithEagerRelationships(raId);

        if (raOption==null) {return null;}

        // get the RA
        ResidentialArea residentialArea = raOption.get();
        log.debug("Get one and only one RA with id : {}", residentialArea);

        // Convert tagDTO to tag
        Tag tag = tagMapper.toEntity(tagDTO);

        // Save a tag
        tag = tagRepository.save(tag);

        // Add tag to RA
        residentialArea = residentialArea.addTag(tag);

        // Save to database
        residentialArea = residentialAreaRepository.save(residentialArea);

        return Optional.ofNullable(tagMapper.toDto(tag));
    }

    @Override
    public void removeTag(Long raId, Long id) {
        log.debug("Request to remove tag {} from residentialArea {}", id,raId);

        // Find the RA
        Optional<ResidentialArea> raOption = residentialAreaRepository.findOneWithEagerRelationships(raId);

        if (raOption==null) {return ;}

        // get the RA
        ResidentialArea residentialArea = raOption.get();
        log.debug("Get one and only one RA with id : {}", residentialArea);

        // Find the tag
        Optional<Tag> tagOp = tagRepository.findById(id);

        if(tagOp==null) {return;}

        // get the tag
        Tag tag = tagOp.get();
        // Add tag to RA
        residentialArea = residentialArea.removeTag(tag);

        // Save to database
        residentialArea = residentialAreaRepository.save(residentialArea);
    }

    @Override
    public List<TagDTO> findAllTags(Long raId) {

        // Find the RA
        Optional<ResidentialArea> raOption = residentialAreaRepository.findOneWithEagerRelationships(raId);

        if (raOption==null) {return null;}

        // get the RA
        ResidentialArea residentialArea = raOption.get();
        log.debug("Get one and only one RA with id : {}", residentialArea);

        return residentialArea.getTags().stream().map(tagMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));

        //log.debug("Request to get all Tags");
    }

    @Override
    public UploadFileResponse addAvatar(Long raId, MultipartFile multipartFile) {
        // Find the RA
        Optional<ResidentialArea> raOption = residentialAreaRepository.findOneWithEagerRelationships(raId);

        if (raOption==null) {return null;}

        // get the RA
        ResidentialArea residentialArea = raOption.get();
        log.debug("Get one and only one RA with id : {}", residentialArea);

        // Save photo to file system and return URL
        UploadFileResponse ufr = fileStorageService.storeFile(UploadType.PHOTO,multipartFile);

        // Save url of avatar to residentialAvatar
        residentialArea.setResidentialAvatar(ufr.getFileDownloadUri());

        return ufr;

    }
}
