package com.tcutma.realstate.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.tcutma.realstate.service.PropertyUserService;
import com.tcutma.realstate.web.rest.errors.BadRequestAlertException;
import com.tcutma.realstate.web.rest.util.HeaderUtil;
import com.tcutma.realstate.service.dto.PropertyUserDTO;
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
 * REST controller for managing PropertyUser.
 */
@RestController
@RequestMapping("/api")
public class PropertyUserResource {

    private final Logger log = LoggerFactory.getLogger(PropertyUserResource.class);

    private static final String ENTITY_NAME = "propertyUser";

    private final PropertyUserService propertyUserService;

    public PropertyUserResource(PropertyUserService propertyUserService) {
        this.propertyUserService = propertyUserService;
    }

    /**
     * POST  /property-users : Create a new propertyUser.
     *
     * @param propertyUserDTO the propertyUserDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new propertyUserDTO, or with status 400 (Bad Request) if the propertyUser has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/property-users")
    @Timed
    public ResponseEntity<PropertyUserDTO> createPropertyUser(@RequestBody PropertyUserDTO propertyUserDTO) throws URISyntaxException {
        log.debug("REST request to save PropertyUser : {}", propertyUserDTO);
        if (propertyUserDTO.getId() != null) {
            throw new BadRequestAlertException("A new propertyUser cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PropertyUserDTO result = propertyUserService.save(propertyUserDTO);
        return ResponseEntity.created(new URI("/api/property-users/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /property-users : Updates an existing propertyUser.
     *
     * @param propertyUserDTO the propertyUserDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated propertyUserDTO,
     * or with status 400 (Bad Request) if the propertyUserDTO is not valid,
     * or with status 500 (Internal Server Error) if the propertyUserDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/property-users")
    @Timed
    public ResponseEntity<PropertyUserDTO> updatePropertyUser(@RequestBody PropertyUserDTO propertyUserDTO) throws URISyntaxException {
        log.debug("REST request to update PropertyUser : {}", propertyUserDTO);
        if (propertyUserDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PropertyUserDTO result = propertyUserService.save(propertyUserDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, propertyUserDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /property-users : get all the propertyUsers.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of propertyUsers in body
     */
    @GetMapping("/property-users")
    @Timed
    public List<PropertyUserDTO> getAllPropertyUsers() {
        log.debug("REST request to get all PropertyUsers");
        return propertyUserService.findAll();
    }

    /**
     * GET  /property-users/:id : get the "id" propertyUser.
     *
     * @param id the id of the propertyUserDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the propertyUserDTO, or with status 404 (Not Found)
     */
    @GetMapping("/property-users/{id}")
    @Timed
    public ResponseEntity<PropertyUserDTO> getPropertyUser(@PathVariable Long id) {
        log.debug("REST request to get PropertyUser : {}", id);
        Optional<PropertyUserDTO> propertyUserDTO = propertyUserService.findOne(id);
        return ResponseUtil.wrapOrNotFound(propertyUserDTO);
    }

    /**
     * DELETE  /property-users/:id : delete the "id" propertyUser.
     *
     * @param id the id of the propertyUserDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/property-users/{id}")
    @Timed
    public ResponseEntity<Void> deletePropertyUser(@PathVariable Long id) {
        log.debug("REST request to delete PropertyUser : {}", id);
        propertyUserService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
