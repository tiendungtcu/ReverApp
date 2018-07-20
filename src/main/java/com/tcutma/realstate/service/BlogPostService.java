package com.tcutma.realstate.service;

import com.tcutma.realstate.service.dto.BlogPostDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing BlogPost.
 */
public interface BlogPostService {

    /**
     * Save a blogPost.
     *
     * @param blogPostDTO the entity to save
     * @return the persisted entity
     */
    BlogPostDTO save(BlogPostDTO blogPostDTO);

    /**
     * Get all the blogPosts.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<BlogPostDTO> findAll(Pageable pageable);


    /**
     * Get the "id" blogPost.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<BlogPostDTO> findOne(Long id);

    /**
     * Delete the "id" blogPost.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
