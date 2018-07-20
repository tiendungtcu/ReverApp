package com.tcutma.realstate.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.tcutma.realstate.service.ResidentialAreaService;
import com.tcutma.realstate.web.rest.errors.BadRequestAlertException;
import com.tcutma.realstate.web.rest.util.HeaderUtil;
import com.tcutma.realstate.web.rest.util.PaginationUtil;
import com.tcutma.realstate.service.dto.ResidentialAreaDTO;
import com.tcutma.realstate.service.dto.ResidentialAreaCriteria;
import com.tcutma.realstate.service.ResidentialAreaQueryService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing ResidentialArea.
 */
@RestController
@RequestMapping("/api")
public class ResidentialAreaResource {

    private final Logger log = LoggerFactory.getLogger(ResidentialAreaResource.class);

    private static final String ENTITY_NAME = "residentialArea";

    private final ResidentialAreaService residentialAreaService;

    private final ResidentialAreaQueryService residentialAreaQueryService;

    public ResidentialAreaResource(ResidentialAreaService residentialAreaService, ResidentialAreaQueryService residentialAreaQueryService) {
        this.residentialAreaService = residentialAreaService;
        this.residentialAreaQueryService = residentialAreaQueryService;
    }

    /**
     * POST  /residential-areas : Create a new residentialArea.
     *
     * @param residentialAreaDTO the residentialAreaDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new residentialAreaDTO, or with status 400 (Bad Request) if the residentialArea has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/residential-areas")
    @Timed
    public ResponseEntity<ResidentialAreaDTO> createResidentialArea(@Valid @RequestBody ResidentialAreaDTO residentialAreaDTO) throws URISyntaxException {
        log.debug("REST request to save ResidentialArea : {}", residentialAreaDTO);
        if (residentialAreaDTO.getId() != null) {
            throw new BadRequestAlertException("A new residentialArea cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ResidentialAreaDTO result = residentialAreaService.save(residentialAreaDTO);
        return ResponseEntity.created(new URI("/api/residential-areas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /residential-areas : Updates an existing residentialArea.
     *
     * @param residentialAreaDTO the residentialAreaDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated residentialAreaDTO,
     * or with status 400 (Bad Request) if the residentialAreaDTO is not valid,
     * or with status 500 (Internal Server Error) if the residentialAreaDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/residential-areas")
    @Timed
    public ResponseEntity<ResidentialAreaDTO> updateResidentialArea(@Valid @RequestBody ResidentialAreaDTO residentialAreaDTO) throws URISyntaxException {
        log.debug("REST request to update ResidentialArea : {}", residentialAreaDTO);
        if (residentialAreaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ResidentialAreaDTO result = residentialAreaService.save(residentialAreaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, residentialAreaDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /residential-areas : get all the residentialAreas.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of residentialAreas in body
     */
    @GetMapping("/residential-areas")
    @Timed
    public ResponseEntity<List<ResidentialAreaDTO>> getAllResidentialAreas(ResidentialAreaCriteria criteria, Pageable pageable) {
        log.debug("REST request to get ResidentialAreas by criteria: {}", criteria);
        Page<ResidentialAreaDTO> page = residentialAreaQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/residential-areas");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /residential-areas/:id : get the "id" residentialArea.
     *
     * @param id the id of the residentialAreaDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the residentialAreaDTO, or with status 404 (Not Found)
     */
    @GetMapping("/residential-areas/{id}")
    @Timed
    public ResponseEntity<ResidentialAreaDTO> getResidentialArea(@PathVariable Long id) {
        log.debug("REST request to get ResidentialArea : {}", id);
        Optional<ResidentialAreaDTO> residentialAreaDTO = residentialAreaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(residentialAreaDTO);
    }

    /**
     * DELETE  /residential-areas/:id : delete the "id" residentialArea.
     *
     * @param id the id of the residentialAreaDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/residential-areas/{id}")
    @Timed
    public ResponseEntity<Void> deleteResidentialArea(@PathVariable Long id) {
        log.debug("REST request to delete ResidentialArea : {}", id);
        residentialAreaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
