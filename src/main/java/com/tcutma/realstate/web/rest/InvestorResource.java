package com.tcutma.realstate.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.tcutma.realstate.service.InvestorService;
import com.tcutma.realstate.web.rest.errors.BadRequestAlertException;
import com.tcutma.realstate.web.rest.util.HeaderUtil;
import com.tcutma.realstate.web.rest.util.PaginationUtil;
import com.tcutma.realstate.service.dto.InvestorDTO;
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
 * REST controller for managing Investor.
 */
@RestController
@RequestMapping("/api")
public class InvestorResource {

    private final Logger log = LoggerFactory.getLogger(InvestorResource.class);

    private static final String ENTITY_NAME = "investor";

    private final InvestorService investorService;

    public InvestorResource(InvestorService investorService) {
        this.investorService = investorService;
    }

    /**
     * POST  /investors : Create a new investor.
     *
     * @param investorDTO the investorDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new investorDTO, or with status 400 (Bad Request) if the investor has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/investors")
    @Timed
    public ResponseEntity<InvestorDTO> createInvestor(@Valid @RequestBody InvestorDTO investorDTO) throws URISyntaxException {
        log.debug("REST request to save Investor : {}", investorDTO);
        if (investorDTO.getId() != null) {
            throw new BadRequestAlertException("A new investor cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InvestorDTO result = investorService.save(investorDTO);
        return ResponseEntity.created(new URI("/api/investors/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /investors : Updates an existing investor.
     *
     * @param investorDTO the investorDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated investorDTO,
     * or with status 400 (Bad Request) if the investorDTO is not valid,
     * or with status 500 (Internal Server Error) if the investorDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/investors")
    @Timed
    public ResponseEntity<InvestorDTO> updateInvestor(@Valid @RequestBody InvestorDTO investorDTO) throws URISyntaxException {
        log.debug("REST request to update Investor : {}", investorDTO);
        if (investorDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InvestorDTO result = investorService.save(investorDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, investorDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /investors : get all the investors.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of investors in body
     */
    @GetMapping("/investors")
    @Timed
    public ResponseEntity<List<InvestorDTO>> getAllInvestors(Pageable pageable) {
        log.debug("REST request to get a page of Investors");
        Page<InvestorDTO> page = investorService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/investors");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /investors/:id : get the "id" investor.
     *
     * @param id the id of the investorDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the investorDTO, or with status 404 (Not Found)
     */
    @GetMapping("/investors/{id}")
    @Timed
    public ResponseEntity<InvestorDTO> getInvestor(@PathVariable Long id) {
        log.debug("REST request to get Investor : {}", id);
        Optional<InvestorDTO> investorDTO = investorService.findOne(id);
        return ResponseUtil.wrapOrNotFound(investorDTO);
    }

    /**
     * DELETE  /investors/:id : delete the "id" investor.
     *
     * @param id the id of the investorDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/investors/{id}")
    @Timed
    public ResponseEntity<Void> deleteInvestor(@PathVariable Long id) {
        log.debug("REST request to delete Investor : {}", id);
        investorService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
