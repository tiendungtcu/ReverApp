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

import com.tcutma.realstate.domain.Project;
import com.tcutma.realstate.domain.*; // for static metamodels
import com.tcutma.realstate.repository.ProjectRepository;
import com.tcutma.realstate.service.dto.ProjectCriteria;

import com.tcutma.realstate.service.dto.ProjectDTO;
import com.tcutma.realstate.service.mapper.ProjectMapper;

/**
 * Service for executing complex queries for Project entities in the database.
 * The main input is a {@link ProjectCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ProjectDTO} or a {@link Page} of {@link ProjectDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ProjectQueryService extends QueryService<Project> {

    private final Logger log = LoggerFactory.getLogger(ProjectQueryService.class);

    private final ProjectRepository projectRepository;

    private final ProjectMapper projectMapper;

    public ProjectQueryService(ProjectRepository projectRepository, ProjectMapper projectMapper) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
    }

    /**
     * Return a {@link List} of {@link ProjectDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ProjectDTO> findByCriteria(ProjectCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Project> specification = createSpecification(criteria);
        return projectMapper.toDto(projectRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ProjectDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ProjectDTO> findByCriteria(ProjectCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Project> specification = createSpecification(criteria);
        return projectRepository.findAll(specification, page)
            .map(projectMapper::toDto);
    }

    /**
     * Function to convert ProjectCriteria to a {@link Specification}
     */
    private Specification<Project> createSpecification(ProjectCriteria criteria) {
        Specification<Project> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Project_.id));
            }
            if (criteria.getProjectName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getProjectName(), Project_.projectName));
            }
            if (criteria.getProjectAlias() != null) {
                specification = specification.and(buildStringSpecification(criteria.getProjectAlias(), Project_.projectAlias));
            }
            if (criteria.getProjectAvatarUrl() != null) {
                specification = specification.and(buildStringSpecification(criteria.getProjectAvatarUrl(), Project_.projectAvatarUrl));
            }
            if (criteria.getProjectDistrict() != null) {
                specification = specification.and(buildStringSpecification(criteria.getProjectDistrict(), Project_.projectDistrict));
            }
            if (criteria.getProjectProvince() != null) {
                specification = specification.and(buildStringSpecification(criteria.getProjectProvince(), Project_.projectProvince));
            }
            if (criteria.getResidentialAreaId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getResidentialAreaId(), Project_.residentialAreaId));
            }
            if (criteria.getProjectRoad() != null) {
                specification = specification.and(buildStringSpecification(criteria.getProjectRoad(), Project_.projectRoad));
            }
            if (criteria.getProjectWard() != null) {
                specification = specification.and(buildStringSpecification(criteria.getProjectWard(), Project_.projectWard));
            }
            if (criteria.getProjectStatus() != null) {
                specification = specification.and(buildSpecification(criteria.getProjectStatus(), Project_.projectStatus));
            }
            if (criteria.getProjectNoBlocks() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getProjectNoBlocks(), Project_.projectNoBlocks));
            }
            if (criteria.getProjectNoFloors() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getProjectNoFloors(), Project_.projectNoFloors));
            }
            if (criteria.getProjectNoApartments() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getProjectNoApartments(), Project_.projectNoApartments));
            }
            if (criteria.getProjectNoShophouse() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getProjectNoShophouse(), Project_.projectNoShophouse));
            }
            if (criteria.getProjectMinSellPrice() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getProjectMinSellPrice(), Project_.projectMinSellPrice));
            }
            if (criteria.getProjectMaxSellPrice() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getProjectMaxSellPrice(), Project_.projectMaxSellPrice));
            }
            if (criteria.getProjectSellPriceUnit() != null) {
                specification = specification.and(buildSpecification(criteria.getProjectSellPriceUnit(), Project_.projectSellPriceUnit));
            }
            if (criteria.getProjectMinRentPrice() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getProjectMinRentPrice(), Project_.projectMinRentPrice));
            }
            if (criteria.getProjectMaxRentPrice() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getProjectMaxRentPrice(), Project_.projectMaxRentPrice));
            }
            if (criteria.getProjectRentPriceUnit() != null) {
                specification = specification.and(buildSpecification(criteria.getProjectRentPriceUnit(), Project_.projectRentPriceUnit));
            }
            if (criteria.getProjectStartedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getProjectStartedDate(), Project_.projectStartedDate));
            }
            if (criteria.getProjectFinishingDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getProjectFinishingDate(), Project_.projectFinishingDate));
            }
            if (criteria.getProjectMinApartmentSquare() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getProjectMinApartmentSquare(), Project_.projectMinApartmentSquare));
            }
            if (criteria.getProjectMaxApartmentSquare() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getProjectMaxApartmentSquare(), Project_.projectMaxApartmentSquare));
            }
            if (criteria.getProjectGreenSpace() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getProjectGreenSpace(), Project_.projectGreenSpace));
            }
            if (criteria.getProjectBuildingDensity() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getProjectBuildingDensity(), Project_.projectBuildingDensity));
            }
            if (criteria.getProjectDesignCompany() != null) {
                specification = specification.and(buildStringSpecification(criteria.getProjectDesignCompany(), Project_.projectDesignCompany));
            }
            if (criteria.getProjectCarPark() != null) {
                specification = specification.and(buildSpecification(criteria.getProjectCarPark(), Project_.projectCarPark));
            }
            if (criteria.getProjectBbqCourt() != null) {
                specification = specification.and(buildSpecification(criteria.getProjectBbqCourt(), Project_.projectBbqCourt));
            }
            if (criteria.getProjectElevator() != null) {
                specification = specification.and(buildSpecification(criteria.getProjectElevator(), Project_.projectElevator));
            }
            if (criteria.getProjectShoppingCenter() != null) {
                specification = specification.and(buildSpecification(criteria.getProjectShoppingCenter(), Project_.projectShoppingCenter));
            }
            if (criteria.getProjectSwimmingPool() != null) {
                specification = specification.and(buildSpecification(criteria.getProjectSwimmingPool(), Project_.projectSwimmingPool));
            }
            if (criteria.getProjectCommunityRoom() != null) {
                specification = specification.and(buildSpecification(criteria.getProjectCommunityRoom(), Project_.projectCommunityRoom));
            }
            if (criteria.getProjectGym() != null) {
                specification = specification.and(buildSpecification(criteria.getProjectGym(), Project_.projectGym));
            }
            if (criteria.getProjectCityPark() != null) {
                specification = specification.and(buildSpecification(criteria.getProjectCityPark(), Project_.projectCityPark));
            }
            if (criteria.getProjectGuard() != null) {
                specification = specification.and(buildSpecification(criteria.getProjectGuard(), Project_.projectGuard));
            }
            if (criteria.getProjectPlayGround() != null) {
                specification = specification.and(buildSpecification(criteria.getProjectPlayGround(), Project_.projectPlayGround));
            }
            if (criteria.getLongitude() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLongitude(), Project_.longitude));
            }
            if (criteria.getLatitude() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLatitude(), Project_.latitude));
            }
            if (criteria.getProjectSeenCount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getProjectSeenCount(), Project_.projectSeenCount));
            }
            if (criteria.getProjectAvailable() != null) {
                specification = specification.and(buildSpecification(criteria.getProjectAvailable(), Project_.projectAvailable));
            }
            if (criteria.getLocationId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getLocationId(), Project_.location, Location_.id));
            }
            if (criteria.getConsultantId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getConsultantId(), Project_.consultant, User_.id));
            }
            if (criteria.getTagId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getTagId(), Project_.tags, Tag_.id));
            }
            if (criteria.getBuildingtypeId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getBuildingtypeId(), Project_.buildingtypes, BuildingType_.id));
            }
            if (criteria.getInverstorId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getInverstorId(), Project_.inverstors, Investor_.id));
            }
            if (criteria.getContractorId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getContractorId(), Project_.contractors, Contractor_.id));
            }
        }
        return specification;
    }

}
