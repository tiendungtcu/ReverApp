package com.tcutma.realstate.service.impl;

import com.tcutma.realstate.service.InvestorService;
import com.tcutma.realstate.domain.Investor;
import com.tcutma.realstate.repository.InvestorRepository;
import com.tcutma.realstate.service.dto.InvestorDTO;
import com.tcutma.realstate.service.mapper.InvestorMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing Investor.
 */
@Service
@Transactional
public class InvestorServiceImpl implements InvestorService {

    private final Logger log = LoggerFactory.getLogger(InvestorServiceImpl.class);

    private final InvestorRepository investorRepository;

    private final InvestorMapper investorMapper;

    public InvestorServiceImpl(InvestorRepository investorRepository, InvestorMapper investorMapper) {
        this.investorRepository = investorRepository;
        this.investorMapper = investorMapper;
    }

    /**
     * Save a investor.
     *
     * @param investorDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public InvestorDTO save(InvestorDTO investorDTO) {
        log.debug("Request to save Investor : {}", investorDTO);
        Investor investor = investorMapper.toEntity(investorDTO);
        investor = investorRepository.save(investor);
        return investorMapper.toDto(investor);
    }

    /**
     * Get all the investors.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<InvestorDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Investors");
        return investorRepository.findAll(pageable)
            .map(investorMapper::toDto);
    }


    /**
     * Get one investor by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<InvestorDTO> findOne(Long id) {
        log.debug("Request to get Investor : {}", id);
        return investorRepository.findById(id)
            .map(investorMapper::toDto);
    }

    /**
     * Delete the investor by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Investor : {}", id);
        investorRepository.deleteById(id);
    }
}
