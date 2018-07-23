package com.tcutma.realstate.service.mapper;

import com.tcutma.realstate.domain.*;
import com.tcutma.realstate.service.dto.PropertyDTO;

import com.tcutma.realstate.web.rest.vm.PropertyVM;
import org.mapstruct.*;

/**
 * Mapper for the entity Property and its DTO PropertyDTO.
 */
@Mapper(componentModel = "spring", uses = {LocationMapper.class, ResidentialAreaMapper.class, TagMapper.class, BuildingTypeMapper.class, PhotoMapper.class})
public interface PropertyMapper extends EntityMapper<PropertyDTO, Property> {

    @Mapping(source = "location.id", target = "locationId")
    @Mapping(source = "residentialArea.id", target = "residentialAreaId")
    @Mapping(source = "residentialArea.residentialName", target = "residentialAreaResidentialName")
    PropertyDTO toDto(Property property);

    @Mapping(source = "locationId", target = "location")
    @Mapping(source = "residentialAreaId", target = "residentialArea")
    Property toEntity(PropertyDTO propertyDTO);

    default Property fromId(Long id) {
        if (id == null) {
            return null;
        }
        Property property = new Property();
        property.setId(id);
        return property;
    }

    @Mapping(source = "propertySofa", target = "furniture.propertySofa")
    @Mapping(source = "propertyDiningTable", target = "furniture.propertyDiningTable")
    @Mapping(source = "propertyKitchen", target = "furniture.propertyKitchen")
    @Mapping(source = "propertyCabinetKitchen", target = "furniture.propertyCabinetKitchen")
    @Mapping(source = "propertyKitchenEquipment", target = "furniture.propertyKitchenEquipment")
    @Mapping(source = "propertyWardrobe", target = "furniture.propertyWardrobe")
    @Mapping(source = "propertyMakeupTable", target = "furniture.propertyMakeupTable")
    @Mapping(source = "propertyDesk", target = "furniture.propertyDesk")
    @Mapping(source = "propertyTivi", target = "furniture.propertyTivi")
    @Mapping(source = "propertyWashingMachine", target = "furniture.propertyWashingMachine")
    @Mapping(source = "propertyRefrigerator", target = "furniture.propertyRefrigerator")
    @Mapping(source = "propertyAirconditioner", target = "furniture.propertyAirconditioner")
    @Mapping(source = "propertyMicrowave", target = "furniture.propertyMicrowave")
    @Mapping(source = "propertyWaterHeater", target = "furniture.propertyWaterHeater")
    @Mapping(source = "propertyBed", target = "furniture.propertyBed")
    @Mapping(source = "propertyHeater", target = "furniture.propertyHeater")
    @Mapping(source = "propertyAudioEquipment", target = "furniture.propertyAudioEquipment")

    @Mapping(source = "propertyInternet", target = "facility.propertyInternet")
    @Mapping(source = "propertyCableTivi", target = "facility.propertyCableTivi")
    @Mapping(source = "propertyPetPermission", target = "facility.propertyPetPermission")
    @Mapping(source = "propertyElevator", target = "facility.propertyElevator")
    @Mapping(source = "propertySwimmingPool", target = "facility.propertySwimmingPool")
    @Mapping(source = "propertyGym", target = "facility.propertyGym")
    @Mapping(source = "propertyFunctionalArea", target = "facility.propertyFunctionalArea")
    @Mapping(source = "propertyOpen24h", target = "facility.propertyOpen24h")
    @Mapping(source = "propertyCarPark", target = "facility.propertyCarPark")
    @Mapping(source = "propertyBalcony", target = "facility.propertyBalcony")
    @Mapping(source = "propertySauna", target = "facility.propertySauna")
    @Mapping(source = "propertySteamSauna", target = "facility.propertySteamSauna")
    @Mapping(source = "propertyAttraction", target = "facility.propertyAttraction")

    @Mapping(source = "location.id", target = "locationId")
    @Mapping(source = "residentialArea.id", target = "residentialAreaId")
    @Mapping(source = "residentialArea.residentialName", target = "residentialName")
    PropertyVM toVM(Property property);
}
