package com.tcutma.realstate.service.impl;

import com.tcutma.realstate.service.PhotoService;
import com.tcutma.realstate.domain.Photo;
import com.tcutma.realstate.repository.PhotoRepository;
import com.tcutma.realstate.service.dto.PhotoDTO;
import com.tcutma.realstate.service.mapper.PhotoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing Photo.
 */
@Service
@Transactional
public class PhotoServiceImpl implements PhotoService {

    private final Logger log = LoggerFactory.getLogger(PhotoServiceImpl.class);

    private final PhotoRepository photoRepository;

    private final PhotoMapper photoMapper;

    public PhotoServiceImpl(PhotoRepository photoRepository, PhotoMapper photoMapper) {
        this.photoRepository = photoRepository;
        this.photoMapper = photoMapper;
    }

    /**
     * Save a photo.
     *
     * @param photoDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PhotoDTO save(PhotoDTO photoDTO) {
        log.debug("Request to save Photo : {}", photoDTO);
        Photo photo = photoMapper.toEntity(photoDTO);
        photo = photoRepository.save(photo);
        return photoMapper.toDto(photo);
    }

    /**
     * Get all the photos.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<PhotoDTO> findAll() {
        log.debug("Request to get all Photos");
        return photoRepository.findAll().stream()
            .map(photoMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one photo by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PhotoDTO> findOne(Long id) {
        log.debug("Request to get Photo : {}", id);
        return photoRepository.findById(id)
            .map(photoMapper::toDto);
    }

    /**
     * Delete the photo by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Photo : {}", id);
        photoRepository.deleteById(id);
    }
}
