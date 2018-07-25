package com.tcutma.realstate.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.tcutma.realstate.domain.enumeration.UploadType;
import com.tcutma.realstate.service.FileStorageService;
import com.tcutma.realstate.service.ResidentialAreaService;
import com.tcutma.realstate.service.dto.TagDTO;
import com.tcutma.realstate.web.rest.errors.BadRequestAlertException;
import com.tcutma.realstate.web.rest.util.HeaderUtil;
import com.tcutma.realstate.web.rest.util.PaginationUtil;
import com.tcutma.realstate.service.dto.ResidentialAreaDTO;
import com.tcutma.realstate.service.dto.ResidentialAreaCriteria;
import com.tcutma.realstate.service.ResidentialAreaQueryService;
import com.tcutma.realstate.web.rest.vm.UploadFileResponse;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing ResidentialArea.
 */
@RestController
@RequestMapping("/api/v1")
public class ResidentialAreaResource {

    private final Logger log = LoggerFactory.getLogger(ResidentialAreaResource.class);

    private static final String ENTITY_NAME = "residentialArea";

    private final ResidentialAreaService residentialAreaService;

    private final ResidentialAreaQueryService residentialAreaQueryService;

    private final FileStorageService fileStorageService;

    public ResidentialAreaResource(ResidentialAreaService residentialAreaService, ResidentialAreaQueryService residentialAreaQueryService, FileStorageService fileStorageService) {
        this.residentialAreaService = residentialAreaService;
        this.residentialAreaQueryService = residentialAreaQueryService;
        this.fileStorageService = fileStorageService;
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
     * @param resid the id of the residentialAreaDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the residentialAreaDTO, or with status 404 (Not Found)
     */
    @GetMapping("/residential-areas/{resid}")
    @Timed
    public ResponseEntity<ResidentialAreaDTO> getResidentialArea(@PathVariable Long resid) {
        log.debug("REST request to get ResidentialArea : {}", resid);
        Optional<ResidentialAreaDTO> residentialAreaDTO = residentialAreaService.findOne(resid);
        return ResponseUtil.wrapOrNotFound(residentialAreaDTO);
    }

    /**
     * DELETE  /residential-areas/:resid : delete the "id" residentialArea.
     *
     * @param resid the id of the residentialAreaDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/residential-areas/{resid}")
    @Timed
    public ResponseEntity<Void> deleteResidentialArea(@PathVariable Long resid) {
        log.debug("REST request to delete ResidentialArea : {}", resid);
        residentialAreaService.delete(resid);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, resid.toString())).build();
    }

    /**
     * POST  /residential-areas/:raIdd/tags : Add a tag to residential area .
     *
     * @param raId the id of the residentialAreaDTO to add tag to
     * @return  the ResponseEntity with status 201 (Created) and with body the new TagDTO, or with status 400 (Bad Request) if the Tag has already an ID
     */
    @PostMapping("/residential-areas/{resid}/tags")
    @Timed
    public ResponseEntity<TagDTO> addNewTag(@PathVariable(value = "resid") Long raId, @Valid @RequestBody TagDTO tagDTO) throws URISyntaxException {
        log.debug("REST request to Add Tag : {}", tagDTO);
        TagDTO result = residentialAreaService.addTag(raId,tagDTO).get();
        return ResponseEntity.created(new URI("/api/residential-areas/" + result.getId() + "/tags"))
            .headers(HeaderUtil.createEntityCreationAlert("tag" +
                "", result.getId().toString()))
            .body(result);
    }

    /**
     * DELETE  /residential-areas/:resid/tags/:tagid : remove "id" tag from the "raId" residentialArea.
     *
     * @param resid the id of the residentialAreaDTO to remove the tag
     * @param  tagid the id of Tag to remove
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/residential-areas/{resid}/tags/{tagid}")
    @Timed
    public ResponseEntity<Void> removeOneTag(@PathVariable (value = "resid") Long resid, @PathVariable(value="tagid") Long tagid) {
        log.debug("REST request to remove tag {id} from ResidentialArea : {}", tagid, resid);
        residentialAreaService.removeTag(resid,tagid);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("tag", tagid.toString())).build();
    }

    /**
     * GET  /residential-areas/{raId}/tags : get all the tags.
     *
     *
     * @param  resid the Id of residential area
     * @return the ResponseEntity with status 200 (OK) and the list of tags in body
     */
    @GetMapping("/residential-areas/{resid}/tags")
    @Timed
    public List<TagDTO> getAllRaTags(@PathVariable(value = "resid") Long resid) {
        log.debug("REST request to get a page of Tags belong to residential area {}",resid);
        return residentialAreaService.findAllTags(resid);
        //HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/v1/residential-areas/"+raId +"/tags");
        //return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * POST  /residential-areas/{raId}/avatar: Upload avatar for residential area.
     *
     * @param resid the residential area id to add avatar
     * @param  file Image file to upload
     * @return the ResponseEntity with status 201 (Created) and with body the new residentialAreaDTO, or with status 400 (Bad Request) if the residentialArea has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/residential-areas/{resid}/avatar")
    @Timed
    public UploadFileResponse AddResidentialAvatar(@PathVariable(value = "resid") Long resid, MultipartFile file) throws URISyntaxException {
        log.debug("REST request to save avatar : {}", file);
        UploadFileResponse avatar = residentialAreaService.addAvatar(resid,file);
        //UploadFileResponse uploadFileResponse = fileStorageService.storeFile(UploadType.PHOTO,multipartFile);
        return avatar;
    }
}
