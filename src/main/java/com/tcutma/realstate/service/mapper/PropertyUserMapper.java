package com.tcutma.realstate.service.mapper;

import com.tcutma.realstate.domain.*;
import com.tcutma.realstate.service.dto.PropertyUserDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity PropertyUser and its DTO PropertyUserDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, PropertyMapper.class})
public interface PropertyUserMapper extends EntityMapper<PropertyUserDTO, PropertyUser> {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.login", target = "userLogin")
    @Mapping(source = "property.id", target = "propertyId")
    @Mapping(source = "property.propertyName", target = "propertyPropertyName")
    PropertyUserDTO toDto(PropertyUser propertyUser);

    @Mapping(source = "userId", target = "user")
    @Mapping(source = "propertyId", target = "property")
    PropertyUser toEntity(PropertyUserDTO propertyUserDTO);

    default PropertyUser fromId(Long id) {
        if (id == null) {
            return null;
        }
        PropertyUser propertyUser = new PropertyUser();
        propertyUser.setId(id);
        return propertyUser;
    }
}
