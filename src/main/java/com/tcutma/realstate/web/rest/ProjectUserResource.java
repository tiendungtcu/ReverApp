package com.tcutma.realstate.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.tcutma.realstate.service.ProjectUserService;
import com.tcutma.realstate.web.rest.errors.BadRequestAlertException;
import com.tcutma.realstate.web.rest.util.HeaderUtil;
import com.tcutma.realstate.service.dto.ProjectUserDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing ProjectUser.
 */
@RestController
@RequestMapping("/api")
public class ProjectUserResource {

    private final Logger log = LoggerFactory.getLogger(ProjectUserResource.class);

    private static final String ENTITY_NAME = "projectUser";

    private final ProjectUserService projectUserService;

    public ProjectUserResource(ProjectUserService projectUserService) {
        this.projectUserService = projectUserService;
    }

    /**
     * POST  /project-users : Create a new projectUser.
     *
     * @param projectUserDTO the projectUserDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new projectUserDTO, or with status 400 (Bad Request) if the projectUser has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/project-users")
    @Timed
    public ResponseEntity<ProjectUserDTO> createProjectUser(@RequestBody ProjectUserDTO projectUserDTO) throws URISyntaxException {
        log.debug("REST request to save ProjectUser : {}", projectUserDTO);
        if (projectUserDTO.getId() != null) {
            throw new BadRequestAlertException("A new projectUser cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProjectUserDTO result = projectUserService.save(projectUserDTO);
        return ResponseEntity.created(new URI("/api/project-users/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /project-users : Updates an existing projectUser.
     *
     * @param projectUserDTO the projectUserDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated projectUserDTO,
     * or with status 400 (Bad Request) if the projectUserDTO is not valid,
     * or with status 500 (Internal Server Error) if the projectUserDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/project-users")
    @Timed
    public ResponseEntity<ProjectUserDTO> updateProjectUser(@RequestBody ProjectUserDTO projectUserDTO) throws URISyntaxException {
        log.debug("REST request to update ProjectUser : {}", projectUserDTO);
        if (projectUserDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProjectUserDTO result = projectUserService.save(projectUserDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, projectUserDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /project-users : get all the projectUsers.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of projectUsers in body
     */
    @GetMapping("/project-users")
    @Timed
    public List<ProjectUserDTO> getAllProjectUsers() {
        log.debug("REST request to get all ProjectUsers");
        return projectUserService.findAll();
    }

    /**
     * GET  /project-users/:id : get the "id" projectUser.
     *
     * @param id the id of the projectUserDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the projectUserDTO, or with status 404 (Not Found)
     */
    @GetMapping("/project-users/{id}")
    @Timed
    public ResponseEntity<ProjectUserDTO> getProjectUser(@PathVariable Long id) {
        log.debug("REST request to get ProjectUser : {}", id);
        Optional<ProjectUserDTO> projectUserDTO = projectUserService.findOne(id);
        return ResponseUtil.wrapOrNotFound(projectUserDTO);
    }

    /**
     * DELETE  /project-users/:id : delete the "id" projectUser.
     *
     * @param id the id of the projectUserDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/project-users/{id}")
    @Timed
    public ResponseEntity<Void> deleteProjectUser(@PathVariable Long id) {
        log.debug("REST request to delete ProjectUser : {}", id);
        projectUserService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
