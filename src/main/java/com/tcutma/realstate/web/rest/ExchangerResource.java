package com.tcutma.realstate.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.tcutma.realstate.service.ExchangerService;
import com.tcutma.realstate.web.rest.errors.BadRequestAlertException;
import com.tcutma.realstate.web.rest.util.HeaderUtil;
import com.tcutma.realstate.service.dto.ExchangerDTO;
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
 * REST controller for managing Exchanger.
 */
@RestController
@RequestMapping("/api")
public class ExchangerResource {

    private final Logger log = LoggerFactory.getLogger(ExchangerResource.class);

    private static final String ENTITY_NAME = "exchanger";

    private final ExchangerService exchangerService;

    public ExchangerResource(ExchangerService exchangerService) {
        this.exchangerService = exchangerService;
    }

    /**
     * POST  /exchangers : Create a new exchanger.
     *
     * @param exchangerDTO the exchangerDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new exchangerDTO, or with status 400 (Bad Request) if the exchanger has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/exchangers")
    @Timed
    public ResponseEntity<ExchangerDTO> createExchanger(@Valid @RequestBody ExchangerDTO exchangerDTO) throws URISyntaxException {
        log.debug("REST request to save Exchanger : {}", exchangerDTO);
        if (exchangerDTO.getId() != null) {
            throw new BadRequestAlertException("A new exchanger cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ExchangerDTO result = exchangerService.save(exchangerDTO);
        return ResponseEntity.created(new URI("/api/exchangers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /exchangers : Updates an existing exchanger.
     *
     * @param exchangerDTO the exchangerDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated exchangerDTO,
     * or with status 400 (Bad Request) if the exchangerDTO is not valid,
     * or with status 500 (Internal Server Error) if the exchangerDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/exchangers")
    @Timed
    public ResponseEntity<ExchangerDTO> updateExchanger(@Valid @RequestBody ExchangerDTO exchangerDTO) throws URISyntaxException {
        log.debug("REST request to update Exchanger : {}", exchangerDTO);
        if (exchangerDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ExchangerDTO result = exchangerService.save(exchangerDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, exchangerDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /exchangers : get all the exchangers.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of exchangers in body
     */
    @GetMapping("/exchangers")
    @Timed
    public List<ExchangerDTO> getAllExchangers() {
        log.debug("REST request to get all Exchangers");
        return exchangerService.findAll();
    }

    /**
     * GET  /exchangers/:id : get the "id" exchanger.
     *
     * @param id the id of the exchangerDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the exchangerDTO, or with status 404 (Not Found)
     */
    @GetMapping("/exchangers/{id}")
    @Timed
    public ResponseEntity<ExchangerDTO> getExchanger(@PathVariable Long id) {
        log.debug("REST request to get Exchanger : {}", id);
        Optional<ExchangerDTO> exchangerDTO = exchangerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(exchangerDTO);
    }

    /**
     * DELETE  /exchangers/:id : delete the "id" exchanger.
     *
     * @param id the id of the exchangerDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/exchangers/{id}")
    @Timed
    public ResponseEntity<Void> deleteExchanger(@PathVariable Long id) {
        log.debug("REST request to delete Exchanger : {}", id);
        exchangerService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
