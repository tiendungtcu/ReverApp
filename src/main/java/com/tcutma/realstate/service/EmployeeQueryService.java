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

import com.tcutma.realstate.domain.Employee;
import com.tcutma.realstate.domain.*; // for static metamodels
import com.tcutma.realstate.repository.EmployeeRepository;
import com.tcutma.realstate.service.dto.EmployeeCriteria;

import com.tcutma.realstate.service.dto.EmployeeDTO;
import com.tcutma.realstate.service.mapper.EmployeeMapper;

/**
 * Service for executing complex queries for Employee entities in the database.
 * The main input is a {@link EmployeeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link EmployeeDTO} or a {@link Page} of {@link EmployeeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class EmployeeQueryService extends QueryService<Employee> {

    private final Logger log = LoggerFactory.getLogger(EmployeeQueryService.class);

    private final EmployeeRepository employeeRepository;

    private final EmployeeMapper employeeMapper;

    public EmployeeQueryService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    /**
     * Return a {@link List} of {@link EmployeeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<EmployeeDTO> findByCriteria(EmployeeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Employee> specification = createSpecification(criteria);
        return employeeMapper.toDto(employeeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link EmployeeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<EmployeeDTO> findByCriteria(EmployeeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Employee> specification = createSpecification(criteria);
        return employeeRepository.findAll(specification, page)
            .map(employeeMapper::toDto);
    }

    /**
     * Function to convert EmployeeCriteria to a {@link Specification}
     */
    private Specification<Employee> createSpecification(EmployeeCriteria criteria) {
        Specification<Employee> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Employee_.id));
            }
            if (criteria.getEmployeeName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmployeeName(), Employee_.employeeName));
            }
            if (criteria.getEmployeeFirstName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmployeeFirstName(), Employee_.employeeFirstName));
            }
            if (criteria.getEmployeeLastName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmployeeLastName(), Employee_.employeeLastName));
            }
            if (criteria.getEmployeeDob() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEmployeeDob(), Employee_.employeeDob));
            }
            if (criteria.getEmployeeSex() != null) {
                specification = specification.and(buildSpecification(criteria.getEmployeeSex(), Employee_.employeeSex));
            }
            if (criteria.getEmployeeIdentityCard() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmployeeIdentityCard(), Employee_.employeeIdentityCard));
            }
            if (criteria.getEmployeePhone() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmployeePhone(), Employee_.employeePhone));
            }
            if (criteria.getEmployeeEmail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmployeeEmail(), Employee_.employeeEmail));
            }
            if (criteria.getEmployeeFacebook() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmployeeFacebook(), Employee_.employeeFacebook));
            }
            if (criteria.getEmployeeLinkedin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmployeeLinkedin(), Employee_.employeeLinkedin));
            }
            if (criteria.getEmployeeInstagram() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmployeeInstagram(), Employee_.employeeInstagram));
            }
            if (criteria.getEmployeeGooglePlus() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmployeeGooglePlus(), Employee_.employeeGooglePlus));
            }
            if (criteria.getEmployeeZalo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmployeeZalo(), Employee_.employeeZalo));
            }
            if (criteria.getEmployeeTwitter() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmployeeTwitter(), Employee_.employeeTwitter));
            }
            if (criteria.getEmployeeYoutube() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmployeeYoutube(), Employee_.employeeYoutube));
            }
            if (criteria.getContactId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getContactId(), Employee_.contact, Contact_.id));
            }
            if (criteria.getPhotoId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getPhotoId(), Employee_.photo, Photo_.id));
            }
            if (criteria.getJobtitleId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getJobtitleId(), Employee_.jobtitle, JobTitle_.id));
            }
            if (criteria.getDepartmentId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getDepartmentId(), Employee_.department, Department_.id));
            }
            if (criteria.getManagerId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getManagerId(), Employee_.manager, Employee_.id));
            }
        }
        return specification;
    }

}
