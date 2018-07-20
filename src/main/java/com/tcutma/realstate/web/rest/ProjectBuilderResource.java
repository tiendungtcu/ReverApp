package com.tcutma.realstate.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.tcutma.realstate.service.ProjectBuilderService;
import com.tcutma.realstate.web.rest.errors.BadRequestAlertException;
import com.tcutma.realstate.web.rest.util.HeaderUtil;
import com.tcutma.realstate.web.rest.util.PaginationUtil;
import com.tcutma.realstate.service.dto.ProjectBuilderDTO;
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
 * REST controller for managing ProjectBuilder.
 */
@RestController
@RequestMapping("/api")
public class ProjectBuilderResource {

    private final Logger log = LoggerFactory.getLogger(ProjectBuilderResource.class);

    private static final String ENTITY_NAME = "projectBuilder";

    private final ProjectBuilderService projectBuilderService;

    public ProjectBuilderResource(ProjectBuilderService projectBuilderService) {
        this.projectBuilderService = projectBuilderService;
    }

    /**
     * POST  /project-builders : Create a new projectBuilder.
     *
     * @param projectBuilderDTO the projectBuilderDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new projectBuilderDTO, or with status 400 (Bad Request) if the projectBuilder has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/project-builders")
    @Timed
    public ResponseEntity<ProjectBuilderDTO> createProjectBuilder(@Valid @RequestBody ProjectBuilderDTO projectBuilderDTO) throws URISyntaxException {
        log.debug("REST request to save ProjectBuilder : {}", projectBuilderDTO);
        if (projectBuilderDTO.getId() != null) {
            throw new BadRequestAlertException("A new projectBuilder cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProjectBuilderDTO result = projectBuilderService.save(projectBuilderDTO);
        return ResponseEntity.created(new URI("/api/project-builders/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /project-builders : Updates an existing projectBuilder.
     *
     * @param projectBuilderDTO the projectBuilderDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated projectBuilderDTO,
     * or with status 400 (Bad Request) if the projectBuilderDTO is not valid,
     * or with status 500 (Internal Server Error) if the projectBuilderDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/project-builders")
    @Timed
    public ResponseEntity<ProjectBuilderDTO> updateProjectBuilder(@Valid @RequestBody ProjectBuilderDTO projectBuilderDTO) throws URISyntaxException {
        log.debug("REST request to update ProjectBuilder : {}", projectBuilderDTO);
        if (projectBuilderDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProjectBuilderDTO result = projectBuilderService.save(projectBuilderDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, projectBuilderDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /project-builders : get all the projectBuilders.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of projectBuilders in body
     */
    @GetMapping("/project-builders")
    @Timed
    public ResponseEntity<List<ProjectBuilderDTO>> getAllProjectBuilders(Pageable pageable) {
        log.debug("REST request to get a page of ProjectBuilders");
        Page<ProjectBuilderDTO> page = projectBuilderService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/project-builders");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /project-builders/:id : get the "id" projectBuilder.
     *
     * @param id the id of the projectBuilderDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the projectBuilderDTO, or with status 404 (Not Found)
     */
    @GetMapping("/project-builders/{id}")
    @Timed
    public ResponseEntity<ProjectBuilderDTO> getProjectBuilder(@PathVariable Long id) {
        log.debug("REST request to get ProjectBuilder : {}", id);
        Optional<ProjectBuilderDTO> projectBuilderDTO = projectBuilderService.findOne(id);
        return ResponseUtil.wrapOrNotFound(projectBuilderDTO);
    }

    /**
     * DELETE  /project-builders/:id : delete the "id" projectBuilder.
     *
     * @param id the id of the projectBuilderDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/project-builders/{id}")
    @Timed
    public ResponseEntity<Void> deleteProjectBuilder(@PathVariable Long id) {
        log.debug("REST request to delete ProjectBuilder : {}", id);
        projectBuilderService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
