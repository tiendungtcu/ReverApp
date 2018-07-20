package com.tcutma.realstate.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.tcutma.realstate.domain.BuildingType;
import com.tcutma.realstate.repository.BuildingTypeRepository;
import com.tcutma.realstate.web.rest.errors.BadRequestAlertException;
import com.tcutma.realstate.web.rest.util.HeaderUtil;
import com.tcutma.realstate.service.dto.BuildingTypeDTO;
import com.tcutma.realstate.service.mapper.BuildingTypeMapper;
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
 * REST controller for managing BuildingType.
 */
@RestController
@RequestMapping("/api")
public class BuildingTypeResource {

    private final Logger log = LoggerFactory.getLogger(BuildingTypeResource.class);

    private static final String ENTITY_NAME = "buildingType";

    private final BuildingTypeRepository buildingTypeRepository;

    private final BuildingTypeMapper buildingTypeMapper;

    public BuildingTypeResource(BuildingTypeRepository buildingTypeRepository, BuildingTypeMapper buildingTypeMapper) {
        this.buildingTypeRepository = buildingTypeRepository;
        this.buildingTypeMapper = buildingTypeMapper;
    }

    /**
     * POST  /building-types : Create a new buildingType.
     *
     * @param buildingTypeDTO the buildingTypeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new buildingTypeDTO, or with status 400 (Bad Request) if the buildingType has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/building-types")
    @Timed
    public ResponseEntity<BuildingTypeDTO> createBuildingType(@Valid @RequestBody BuildingTypeDTO buildingTypeDTO) throws URISyntaxException {
        log.debug("REST request to save BuildingType : {}", buildingTypeDTO);
        if (buildingTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new buildingType cannot already have an ID", ENTITY_NAME, "idexists");
        }

        BuildingType buildingType = buildingTypeMapper.toEntity(buildingTypeDTO);
        buildingType = buildingTypeRepository.save(buildingType);
        BuildingTypeDTO result = buildingTypeMapper.toDto(buildingType);
        return ResponseEntity.created(new URI("/api/building-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /building-types : Updates an existing buildingType.
     *
     * @param buildingTypeDTO the buildingTypeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated buildingTypeDTO,
     * or with status 400 (Bad Request) if the buildingTypeDTO is not valid,
     * or with status 500 (Internal Server Error) if the buildingTypeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/building-types")
    @Timed
    public ResponseEntity<BuildingTypeDTO> updateBuildingType(@Valid @RequestBody BuildingTypeDTO buildingTypeDTO) throws URISyntaxException {
        log.debug("REST request to update BuildingType : {}", buildingTypeDTO);
        if (buildingTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        BuildingType buildingType = buildingTypeMapper.toEntity(buildingTypeDTO);
        buildingType = buildingTypeRepository.save(buildingType);
        BuildingTypeDTO result = buildingTypeMapper.toDto(buildingType);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, buildingTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /building-types : get all the buildingTypes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of buildingTypes in body
     */
    @GetMapping("/building-types")
    @Timed
    public List<BuildingTypeDTO> getAllBuildingTypes() {
        log.debug("REST request to get all BuildingTypes");
        List<BuildingType> buildingTypes = buildingTypeRepository.findAll();
        return buildingTypeMapper.toDto(buildingTypes);
    }

    /**
     * GET  /building-types/:id : get the "id" buildingType.
     *
     * @param id the id of the buildingTypeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the buildingTypeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/building-types/{id}")
    @Timed
    public ResponseEntity<BuildingTypeDTO> getBuildingType(@PathVariable Long id) {
        log.debug("REST request to get BuildingType : {}", id);
        Optional<BuildingTypeDTO> buildingTypeDTO = buildingTypeRepository.findById(id)
            .map(buildingTypeMapper::toDto);
        return ResponseUtil.wrapOrNotFound(buildingTypeDTO);
    }

    /**
     * DELETE  /building-types/:id : delete the "id" buildingType.
     *
     * @param id the id of the buildingTypeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/building-types/{id}")
    @Timed
    public ResponseEntity<Void> deleteBuildingType(@PathVariable Long id) {
        log.debug("REST request to delete BuildingType : {}", id);

        buildingTypeRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
