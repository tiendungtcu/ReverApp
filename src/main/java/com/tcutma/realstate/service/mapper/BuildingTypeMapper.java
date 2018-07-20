package com.tcutma.realstate.service.mapper;

import com.tcutma.realstate.domain.*;
import com.tcutma.realstate.service.dto.BuildingTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity BuildingType and its DTO BuildingTypeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BuildingTypeMapper extends EntityMapper<BuildingTypeDTO, BuildingType> {


    @Mapping(target = "projects", ignore = true)
    @Mapping(target = "properties", ignore = true)
    BuildingType toEntity(BuildingTypeDTO buildingTypeDTO);

    default BuildingType fromId(Long id) {
        if (id == null) {
            return null;
        }
        BuildingType buildingType = new BuildingType();
        buildingType.setId(id);
        return buildingType;
    }
}
