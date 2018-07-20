package com.tcutma.realstate.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.tcutma.realstate.service.SupportCategoryService;
import com.tcutma.realstate.web.rest.errors.BadRequestAlertException;
import com.tcutma.realstate.web.rest.util.HeaderUtil;
import com.tcutma.realstate.service.dto.SupportCategoryDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing SupportCategory.
 */
@RestController
@RequestMapping("/api")
public class SupportCategoryResource {

    private final Logger log = LoggerFactory.getLogger(SupportCategoryResource.class);

    private static final String ENTITY_NAME = "supportCategory";

    private final SupportCategoryService supportCategoryService;

    public SupportCategoryResource(SupportCategoryService supportCategoryService) {
        this.supportCategoryService = supportCategoryService;
    }

    /**
     * POST  /support-categories : Create a new supportCategory.
     *
     * @param supportCategoryDTO the supportCategoryDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new supportCategoryDTO, or with status 400 (Bad Request) if the supportCategory has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/support-categories")
    @Timed
    public ResponseEntity<SupportCategoryDTO> createSupportCategory(@Valid @RequestBody SupportCategoryDTO supportCategoryDTO) throws URISyntaxException {
        log.debug("REST request to save SupportCategory : {}", supportCategoryDTO);
        if (supportCategoryDTO.getId() != null) {
            throw new BadRequestAlertException("A new supportCategory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SupportCategoryDTO result = supportCategoryService.save(supportCategoryDTO);
        return ResponseEntity.created(new URI("/api/support-categories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /support-categories : Updates an existing supportCategory.
     *
     * @param supportCategoryDTO the supportCategoryDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated supportCategoryDTO,
     * or with status 400 (Bad Request) if the supportCategoryDTO is not valid,
     * or with status 500 (Internal Server Error) if the supportCategoryDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/support-categories")
    @Timed
    public ResponseEntity<SupportCategoryDTO> updateSupportCategory(@Valid @RequestBody SupportCategoryDTO supportCategoryDTO) throws URISyntaxException {
        log.debug("REST request to update SupportCategory : {}", supportCategoryDTO);
        if (supportCategoryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SupportCategoryDTO result = supportCategoryService.save(supportCategoryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, supportCategoryDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /support-categories : get all the supportCategories.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of supportCategories in body
     */
    @GetMapping("/support-categories")
    @Timed
    public List<SupportCategoryDTO> getAllSupportCategories() {
        log.debug("REST request to get all SupportCategories");
        return supportCategoryService.findAll();
    }

    /**
     * GET  /support-categories/:id : get the "id" supportCategory.
     *
     * @param id the id of the supportCategoryDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the supportCategoryDTO, or with status 404 (Not Found)
     */
    @GetMapping("/support-categories/{id}")
    @Timed
    public ResponseEntity<SupportCategoryDTO> getSupportCategory(@PathVariable Long id) {
        log.debug("REST request to get SupportCategory : {}", id);
        Optional<SupportCategoryDTO> supportCategoryDTO = supportCategoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(supportCategoryDTO);
    }

    /**
     * DELETE  /support-categories/:id : delete the "id" supportCategory.
     *
     * @param id the id of the supportCategoryDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/support-categories/{id}")
    @Timed
    public ResponseEntity<Void> deleteSupportCategory(@PathVariable Long id) {
        log.debug("REST request to delete SupportCategory : {}", id);
        supportCategoryService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
