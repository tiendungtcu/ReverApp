package com.tcutma.realstate.service.mapper;

import com.tcutma.realstate.domain.*;
import com.tcutma.realstate.service.dto.PropertyDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Property and its DTO PropertyDTO.
 */
@Mapper(componentModel = "spring", uses = {LocationMapper.class, UserMapper.class, TagMapper.class})
public interface PropertyMapper extends EntityMapper<PropertyDTO, Property> {

    @Mapping(source = "location.id", target = "locationId")
    @Mapping(source = "consultant.id", target = "consultantId")
    @Mapping(source = "consultant.login", target = "consultantLogin")
    PropertyDTO toDto(Property property);

    @Mapping(source = "locationId", target = "location")
    @Mapping(source = "consultantId", target = "consultant")
    Property toEntity(PropertyDTO propertyDTO);

    default Property fromId(Long id) {
        if (id == null) {
            return null;
        }
        Property property = new Property();
        property.setId(id);
        return property;
    }
}
