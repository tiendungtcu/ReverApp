package com.tcutma.realstate.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.tcutma.realstate.domain.ResidentialArea;
import com.tcutma.realstate.domain.*; // for static metamodels
import com.tcutma.realstate.repository.ResidentialAreaRepository;
import com.tcutma.realstate.service.dto.ResidentialAreaCriteria;

import com.tcutma.realstate.service.dto.ResidentialAreaDTO;
import com.tcutma.realstate.service.mapper.ResidentialAreaMapper;

/**
 * Service for executing complex queries for ResidentialArea entities in the database.
 * The main input is a {@link ResidentialAreaCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ResidentialAreaDTO} or a {@link Page} of {@link ResidentialAreaDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ResidentialAreaQueryService extends QueryService<ResidentialArea> {

    private final Logger log = LoggerFactory.getLogger(ResidentialAreaQueryService.class);

    private final ResidentialAreaRepository residentialAreaRepository;

    private final ResidentialAreaMapper residentialAreaMapper;

    public ResidentialAreaQueryService(ResidentialAreaRepository residentialAreaRepository, ResidentialAreaMapper residentialAreaMapper) {
        this.residentialAreaRepository = residentialAreaRepository;
        this.residentialAreaMapper = residentialAreaMapper;
    }

    /**
     * Return a {@link List} of {@link ResidentialAreaDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ResidentialAreaDTO> findByCriteria(ResidentialAreaCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ResidentialArea> specification = createSpecification(criteria);
        return residentialAreaMapper.toDto(residentialAreaRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ResidentialAreaDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ResidentialAreaDTO> findByCriteria(ResidentialAreaCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ResidentialArea> specification = createSpecification(criteria);
        return residentialAreaRepository.findAll(specification, page)
            .map(residentialAreaMapper::toDto);
    }

    /**
     * Function to convert ResidentialAreaCriteria to a {@link Specification}
     */
    private Specification<ResidentialArea> createSpecification(ResidentialAreaCriteria criteria) {
        Specification<ResidentialArea> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ResidentialArea_.id));
            }
            if (criteria.getResidentialName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getResidentialName(), ResidentialArea_.residentialName));
            }
            if (criteria.getResidentialAlias() != null) {
                specification = specification.and(buildStringSpecification(criteria.getResidentialAlias(), ResidentialArea_.residentialAlias));
            }
            if (criteria.getResidentialProvince() != null) {
                specification = specification.and(buildStringSpecification(criteria.getResidentialProvince(), ResidentialArea_.residentialProvince));
            }
            if (criteria.getResidentialDistrict() != null) {
                specification = specification.and(buildStringSpecification(criteria.getResidentialDistrict(), ResidentialArea_.residentialDistrict));
            }
            if (criteria.getResidentialAvatar() != null) {
                specification = specification.and(buildStringSpecification(criteria.getResidentialAvatar(), ResidentialArea_.residentialAvatar));
            }
            if (criteria.getPhotoId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getPhotoId(), ResidentialArea_.photo, Photo_.id));
            }
            if (criteria.getTagId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getTagId(), ResidentialArea_.tags, Tag_.id));
            }
        }
        return specification;
    }

}
