package com.tcutma.realstate.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.tcutma.realstate.service.RecruitmentInfoService;
import com.tcutma.realstate.web.rest.errors.BadRequestAlertException;
import com.tcutma.realstate.web.rest.util.HeaderUtil;
import com.tcutma.realstate.web.rest.util.PaginationUtil;
import com.tcutma.realstate.service.dto.RecruitmentInfoDTO;
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
 * REST controller for managing RecruitmentInfo.
 */
@RestController
@RequestMapping("/api")
public class RecruitmentInfoResource {

    private final Logger log = LoggerFactory.getLogger(RecruitmentInfoResource.class);

    private static final String ENTITY_NAME = "recruitmentInfo";

    private final RecruitmentInfoService recruitmentInfoService;

    public RecruitmentInfoResource(RecruitmentInfoService recruitmentInfoService) {
        this.recruitmentInfoService = recruitmentInfoService;
    }

    /**
     * POST  /recruitment-infos : Create a new recruitmentInfo.
     *
     * @param recruitmentInfoDTO the recruitmentInfoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new recruitmentInfoDTO, or with status 400 (Bad Request) if the recruitmentInfo has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/recruitment-infos")
    @Timed
    public ResponseEntity<RecruitmentInfoDTO> createRecruitmentInfo(@Valid @RequestBody RecruitmentInfoDTO recruitmentInfoDTO) throws URISyntaxException {
        log.debug("REST request to save RecruitmentInfo : {}", recruitmentInfoDTO);
        if (recruitmentInfoDTO.getId() != null) {
            throw new BadRequestAlertException("A new recruitmentInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RecruitmentInfoDTO result = recruitmentInfoService.save(recruitmentInfoDTO);
        return ResponseEntity.created(new URI("/api/recruitment-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /recruitment-infos : Updates an existing recruitmentInfo.
     *
     * @param recruitmentInfoDTO the recruitmentInfoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated recruitmentInfoDTO,
     * or with status 400 (Bad Request) if the recruitmentInfoDTO is not valid,
     * or with status 500 (Internal Server Error) if the recruitmentInfoDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/recruitment-infos")
    @Timed
    public ResponseEntity<RecruitmentInfoDTO> updateRecruitmentInfo(@Valid @RequestBody RecruitmentInfoDTO recruitmentInfoDTO) throws URISyntaxException {
        log.debug("REST request to update RecruitmentInfo : {}", recruitmentInfoDTO);
        if (recruitmentInfoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RecruitmentInfoDTO result = recruitmentInfoService.save(recruitmentInfoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, recruitmentInfoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /recruitment-infos : get all the recruitmentInfos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of recruitmentInfos in body
     */
    @GetMapping("/recruitment-infos")
    @Timed
    public ResponseEntity<List<RecruitmentInfoDTO>> getAllRecruitmentInfos(Pageable pageable) {
        log.debug("REST request to get a page of RecruitmentInfos");
        Page<RecruitmentInfoDTO> page = recruitmentInfoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/recruitment-infos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /recruitment-infos/:id : get the "id" recruitmentInfo.
     *
     * @param id the id of the recruitmentInfoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the recruitmentInfoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/recruitment-infos/{id}")
    @Timed
    public ResponseEntity<RecruitmentInfoDTO> getRecruitmentInfo(@PathVariable Long id) {
        log.debug("REST request to get RecruitmentInfo : {}", id);
        Optional<RecruitmentInfoDTO> recruitmentInfoDTO = recruitmentInfoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(recruitmentInfoDTO);
    }

    /**
     * DELETE  /recruitment-infos/:id : delete the "id" recruitmentInfo.
     *
     * @param id the id of the recruitmentInfoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/recruitment-infos/{id}")
    @Timed
    public ResponseEntity<Void> deleteRecruitmentInfo(@PathVariable Long id) {
        log.debug("REST request to delete RecruitmentInfo : {}", id);
        recruitmentInfoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
