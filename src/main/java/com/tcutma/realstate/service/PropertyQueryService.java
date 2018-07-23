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

import com.tcutma.realstate.domain.Property;
import com.tcutma.realstate.domain.*; // for static metamodels
import com.tcutma.realstate.repository.PropertyRepository;
import com.tcutma.realstate.service.dto.PropertyCriteria;

import com.tcutma.realstate.service.dto.PropertyDTO;
import com.tcutma.realstate.service.mapper.PropertyMapper;

/**
 * Service for executing complex queries for Property entities in the database.
 * The main input is a {@link PropertyCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PropertyDTO} or a {@link Page} of {@link PropertyDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PropertyQueryService extends QueryService<Property> {

    private final Logger log = LoggerFactory.getLogger(PropertyQueryService.class);

    private final PropertyRepository propertyRepository;

    private final PropertyMapper propertyMapper;

    public PropertyQueryService(PropertyRepository propertyRepository, PropertyMapper propertyMapper) {
        this.propertyRepository = propertyRepository;
        this.propertyMapper = propertyMapper;
    }

    /**
     * Return a {@link List} of {@link PropertyDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PropertyDTO> findByCriteria(PropertyCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Property> specification = createSpecification(criteria);
        return propertyMapper.toDto(propertyRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link PropertyDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PropertyDTO> findByCriteria(PropertyCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Property> specification = createSpecification(criteria);
        return propertyRepository.findAll(specification, page)
            .map(propertyMapper::toDto);
    }

    /**
     * Function to convert PropertyCriteria to a {@link Specification}
     */
    private Specification<Property> createSpecification(PropertyCriteria criteria) {
        Specification<Property> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Property_.id));
            }
            if (criteria.getPropertyCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPropertyCode(), Property_.propertyCode));
            }
            if (criteria.getPropertyName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPropertyName(), Property_.propertyName));
            }
            if (criteria.getPropertyAlias() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPropertyAlias(), Property_.propertyAlias));
            }
            if (criteria.getPropertyTransaction() != null) {
                specification = specification.and(buildSpecification(criteria.getPropertyTransaction(), Property_.propertyTransaction));
            }
            if (criteria.getPropertyNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPropertyNumber(), Property_.propertyNumber));
            }
            if (criteria.getPropertyRoad() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPropertyRoad(), Property_.propertyRoad));
            }
            if (criteria.getPropertyWard() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPropertyWard(), Property_.propertyWard));
            }
            if (criteria.getPropertyDistrict() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPropertyDistrict(), Property_.propertyDistrict));
            }
            if (criteria.getPropertyProvince() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPropertyProvince(), Property_.propertyProvince));
            }
            if (criteria.getPropertyBedRooms() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPropertyBedRooms(), Property_.propertyBedRooms));
            }
            if (criteria.getPropertyBathRooms() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPropertyBathRooms(), Property_.propertyBathRooms));
            }
            if (criteria.getPropertySquare() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPropertySquare(), Property_.propertySquare));
            }
            if (criteria.getPropertyUsePurpose() != null) {
                specification = specification.and(buildSpecification(criteria.getPropertyUsePurpose(), Property_.propertyUsePurpose));
            }
            if (criteria.getPropertyOwnerType() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPropertyOwnerType(), Property_.propertyOwnerType));
            }
            if (criteria.getPropertyTower() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPropertyTower(), Property_.propertyTower));
            }
            if (criteria.getPropertyRentPrice() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPropertyRentPrice(), Property_.propertyRentPrice));
            }
            if (criteria.getPropertyRentUnit() != null) {
                specification = specification.and(buildSpecification(criteria.getPropertyRentUnit(), Property_.propertyRentUnit));
            }
            if (criteria.getPropertyRentStartedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPropertyRentStartedDate(), Property_.propertyRentStartedDate));
            }
            if (criteria.getPropertySellPrice() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPropertySellPrice(), Property_.propertySellPrice));
            }
            if (criteria.getPropertySellUnit() != null) {
                specification = specification.and(buildSpecification(criteria.getPropertySellUnit(), Property_.propertySellUnit));
            }
            if (criteria.getPropertySellStartedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPropertySellStartedDate(), Property_.propertySellStartedDate));
            }
            if (criteria.getPropertySofa() != null) {
                specification = specification.and(buildSpecification(criteria.getPropertySofa(), Property_.propertySofa));
            }
            if (criteria.getPropertyDiningTable() != null) {
                specification = specification.and(buildSpecification(criteria.getPropertyDiningTable(), Property_.propertyDiningTable));
            }
            if (criteria.getPropertyKitchen() != null) {
                specification = specification.and(buildSpecification(criteria.getPropertyKitchen(), Property_.propertyKitchen));
            }
            if (criteria.getPropertyCabinetKitchen() != null) {
                specification = specification.and(buildSpecification(criteria.getPropertyCabinetKitchen(), Property_.propertyCabinetKitchen));
            }
            if (criteria.getPropertyKitchenEquipment() != null) {
                specification = specification.and(buildSpecification(criteria.getPropertyKitchenEquipment(), Property_.propertyKitchenEquipment));
            }
            if (criteria.getPropertyWardrobe() != null) {
                specification = specification.and(buildSpecification(criteria.getPropertyWardrobe(), Property_.propertyWardrobe));
            }
            if (criteria.getPropertyMakeupTable() != null) {
                specification = specification.and(buildSpecification(criteria.getPropertyMakeupTable(), Property_.propertyMakeupTable));
            }
            if (criteria.getPropertyDesk() != null) {
                specification = specification.and(buildSpecification(criteria.getPropertyDesk(), Property_.propertyDesk));
            }
            if (criteria.getPropertyTivi() != null) {
                specification = specification.and(buildSpecification(criteria.getPropertyTivi(), Property_.propertyTivi));
            }
            if (criteria.getPropertyWashingMachine() != null) {
                specification = specification.and(buildSpecification(criteria.getPropertyWashingMachine(), Property_.propertyWashingMachine));
            }
            if (criteria.getPropertyMicrowave() != null) {
                specification = specification.and(buildSpecification(criteria.getPropertyMicrowave(), Property_.propertyMicrowave));
            }
            if (criteria.getPropertyWaterHeater() != null) {
                specification = specification.and(buildSpecification(criteria.getPropertyWaterHeater(), Property_.propertyWaterHeater));
            }
            if (criteria.getPropertyBed() != null) {
                specification = specification.and(buildSpecification(criteria.getPropertyBed(), Property_.propertyBed));
            }
            if (criteria.getPropertyHeater() != null) {
                specification = specification.and(buildSpecification(criteria.getPropertyHeater(), Property_.propertyHeater));
            }
            if (criteria.getPropertyAudioEquipment() != null) {
                specification = specification.and(buildSpecification(criteria.getPropertyAudioEquipment(), Property_.propertyAudioEquipment));
            }
            if (criteria.getPropertyInternet() != null) {
                specification = specification.and(buildSpecification(criteria.getPropertyInternet(), Property_.propertyInternet));
            }
            if (criteria.getPropertyCableTivi() != null) {
                specification = specification.and(buildSpecification(criteria.getPropertyCableTivi(), Property_.propertyCableTivi));
            }
            if (criteria.getPropertyPetPermission() != null) {
                specification = specification.and(buildSpecification(criteria.getPropertyPetPermission(), Property_.propertyPetPermission));
            }
            if (criteria.getPropertyElevator() != null) {
                specification = specification.and(buildSpecification(criteria.getPropertyElevator(), Property_.propertyElevator));
            }
            if (criteria.getPropertySwimmingPool() != null) {
                specification = specification.and(buildSpecification(criteria.getPropertySwimmingPool(), Property_.propertySwimmingPool));
            }
            if (criteria.getPropertyGym() != null) {
                specification = specification.and(buildSpecification(criteria.getPropertyGym(), Property_.propertyGym));
            }
            if (criteria.getPropertyFunctionalArea() != null) {
                specification = specification.and(buildSpecification(criteria.getPropertyFunctionalArea(), Property_.propertyFunctionalArea));
            }
            if (criteria.getPropertyOpen24h() != null) {
                specification = specification.and(buildSpecification(criteria.getPropertyOpen24h(), Property_.propertyOpen24h));
            }
            if (criteria.getPropertyCarPark() != null) {
                specification = specification.and(buildSpecification(criteria.getPropertyCarPark(), Property_.propertyCarPark));
            }
            if (criteria.getPropertyBalcony() != null) {
                specification = specification.and(buildSpecification(criteria.getPropertyBalcony(), Property_.propertyBalcony));
            }
            if (criteria.getPropertySauna() != null) {
                specification = specification.and(buildSpecification(criteria.getPropertySauna(), Property_.propertySauna));
            }
            if (criteria.getPropertySteamSauna() != null) {
                specification = specification.and(buildSpecification(criteria.getPropertySteamSauna(), Property_.propertySteamSauna));
            }
            if (criteria.getPropertyAttraction() != null) {
                specification = specification.and(buildSpecification(criteria.getPropertyAttraction(), Property_.propertyAttraction));
            }
            if (criteria.getPropertySpecialFeature() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPropertySpecialFeature(), Property_.propertySpecialFeature));
            }
            if (criteria.getPropertyFurnitureOverview() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPropertyFurnitureOverview(), Property_.propertyFurnitureOverview));
            }
            if (criteria.getPropertyLocationOverview() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPropertyLocationOverview(), Property_.propertyLocationOverview));
            }
            if (criteria.getPropertyResidentialCommunity() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPropertyResidentialCommunity(), Property_.propertyResidentialCommunity));
            }
            if (criteria.getPropertyEducationalAspect() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPropertyEducationalAspect(), Property_.propertyEducationalAspect));
            }
            if (criteria.getPropertyExtraInfo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPropertyExtraInfo(), Property_.propertyExtraInfo));
            }
            if (criteria.getLongitude() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLongitude(), Property_.longitude));
            }
            if (criteria.getLatitude() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLatitude(), Property_.latitude));
            }
            if (criteria.getPropertyGoodPrice() != null) {
                specification = specification.and(buildSpecification(criteria.getPropertyGoodPrice(), Property_.propertyGoodPrice));
            }
            if (criteria.getPropertySeenCount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPropertySeenCount(), Property_.propertySeenCount));
            }
            if (criteria.getPropertyIsSold() != null) {
                specification = specification.and(buildSpecification(criteria.getPropertyIsSold(), Property_.propertyIsSold));
            }
            if (criteria.getPropertyIsRent() != null) {
                specification = specification.and(buildSpecification(criteria.getPropertyIsRent(), Property_.propertyIsRent));
            }
            if (criteria.getPropertyAvailable() != null) {
                specification = specification.and(buildSpecification(criteria.getPropertyAvailable(), Property_.propertyAvailable));
            }
            if (criteria.getPropertyRefrigerator() != null) {
                specification = specification.and(buildSpecification(criteria.getPropertyRefrigerator(), Property_.propertyRefrigerator));
            }
            if (criteria.getPropertyAirconditioner() != null) {
                specification = specification.and(buildSpecification(criteria.getPropertyAirconditioner(), Property_.propertyAirconditioner));
            }
            if (criteria.getLocationId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getLocationId(), Property_.location, Location_.id));
            }
            if (criteria.getResidentialAreaId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getResidentialAreaId(), Property_.residentialArea, ResidentialArea_.id));
            }
            if (criteria.getTagId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getTagId(), Property_.tags, Tag_.id));
            }
            if (criteria.getBuildingtypeId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getBuildingtypeId(), Property_.buildingtypes, BuildingType_.id));
            }
            if (criteria.getPhotoId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getPhotoId(), Property_.photos, Photo_.id));
            }
        }
        return specification;
    }

}
