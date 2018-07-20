package com.tcutma.realstate.service.impl;

import com.tcutma.realstate.service.ExchangerService;
import com.tcutma.realstate.domain.Exchanger;
import com.tcutma.realstate.repository.ExchangerRepository;
import com.tcutma.realstate.service.dto.ExchangerDTO;
import com.tcutma.realstate.service.mapper.ExchangerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing Exchanger.
 */
@Service
@Transactional
public class ExchangerServiceImpl implements ExchangerService {

    private final Logger log = LoggerFactory.getLogger(ExchangerServiceImpl.class);

    private final ExchangerRepository exchangerRepository;

    private final ExchangerMapper exchangerMapper;

    public ExchangerServiceImpl(ExchangerRepository exchangerRepository, ExchangerMapper exchangerMapper) {
        this.exchangerRepository = exchangerRepository;
        this.exchangerMapper = exchangerMapper;
    }

    /**
     * Save a exchanger.
     *
     * @param exchangerDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ExchangerDTO save(ExchangerDTO exchangerDTO) {
        log.debug("Request to save Exchanger : {}", exchangerDTO);
        Exchanger exchanger = exchangerMapper.toEntity(exchangerDTO);
        exchanger = exchangerRepository.save(exchanger);
        return exchangerMapper.toDto(exchanger);
    }

    /**
     * Get all the exchangers.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ExchangerDTO> findAll() {
        log.debug("Request to get all Exchangers");
        return exchangerRepository.findAll().stream()
            .map(exchangerMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one exchanger by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ExchangerDTO> findOne(Long id) {
        log.debug("Request to get Exchanger : {}", id);
        return exchangerRepository.findById(id)
            .map(exchangerMapper::toDto);
    }

    /**
     * Delete the exchanger by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Exchanger : {}", id);
        exchangerRepository.deleteById(id);
    }
}
