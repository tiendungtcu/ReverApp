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

import com.tcutma.realstate.domain.Location;
import com.tcutma.realstate.domain.*; // for static metamodels
import com.tcutma.realstate.repository.LocationRepository;
import com.tcutma.realstate.service.dto.LocationCriteria;

import com.tcutma.realstate.service.dto.LocationDTO;
import com.tcutma.realstate.service.mapper.LocationMapper;

/**
 * Service for executing complex queries for Location entities in the database.
 * The main input is a {@link LocationCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link LocationDTO} or a {@link Page} of {@link LocationDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class LocationQueryService extends QueryService<Location> {

    private final Logger log = LoggerFactory.getLogger(LocationQueryService.class);

    private final LocationRepository locationRepository;

    private final LocationMapper locationMapper;

    public LocationQueryService(LocationRepository locationRepository, LocationMapper locationMapper) {
        this.locationRepository = locationRepository;
        this.locationMapper = locationMapper;
    }

    /**
     * Return a {@link List} of {@link LocationDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<LocationDTO> findByCriteria(LocationCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Location> specification = createSpecification(criteria);
        return locationMapper.toDto(locationRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link LocationDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<LocationDTO> findByCriteria(LocationCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Location> specification = createSpecification(criteria);
        return locationRepository.findAll(specification, page)
            .map(locationMapper::toDto);
    }

    /**
     * Function to convert LocationCriteria to a {@link Specification}
     */
    private Specification<Location> createSpecification(LocationCriteria criteria) {
        Specification<Location> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Location_.id));
            }
            if (criteria.getLocationName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLocationName(), Location_.locationName));
            }
            if (criteria.getLocationFullAddress() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLocationFullAddress(), Location_.locationFullAddress));
            }
            if (criteria.getLocationNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLocationNumber(), Location_.locationNumber));
            }
            if (criteria.getLocationRoad() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLocationRoad(), Location_.locationRoad));
            }
            if (criteria.getLocationWard() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLocationWard(), Location_.locationWard));
            }
            if (criteria.getLocationDistrict() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLocationDistrict(), Location_.locationDistrict));
            }
            if (criteria.getLocationProvince() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLocationProvince(), Location_.locationProvince));
            }
            if (criteria.getLocationGmapUrl() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLocationGmapUrl(), Location_.locationGmapUrl));
            }
            if (criteria.getLongitude() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLongitude(), Location_.longitude));
            }
            if (criteria.getLatitude() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLatitude(), Location_.latitude));
            }
            if (criteria.getLocationZipCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLocationZipCode(), Location_.locationZipCode));
            }
        }
        return specification;
    }

}
