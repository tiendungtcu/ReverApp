package com.tcutma.realstate.service;

import com.tcutma.realstate.service.dto.ResidentialAreaDTO;

import com.tcutma.realstate.service.dto.TagDTO;
import com.tcutma.realstate.web.rest.vm.UploadFileResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing ResidentialArea.
 */
public interface ResidentialAreaService {

    /**
     * Save a residentialArea.
     *
     * @param residentialAreaDTO the entity to save
     * @return the persisted entity
     */
    ResidentialAreaDTO save(ResidentialAreaDTO residentialAreaDTO);

    /**
     * Get all the residentialAreas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ResidentialAreaDTO> findAll(Pageable pageable);

    /**
     * Get all the ResidentialArea with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<ResidentialAreaDTO> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" residentialArea.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ResidentialAreaDTO> findOne(Long id);

    /**
     * Delete the "id" residentialArea.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    Optional<TagDTO> addTag(Long raId, TagDTO tagDTO);

    void removeTag(Long raId, Long id);

    List<TagDTO> findAllTags(Long raId);

    UploadFileResponse addAvatar(Long raId, MultipartFile multipartFile);
}
