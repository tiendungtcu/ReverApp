package com.tcutma.realstate.service.mapper;

import com.tcutma.realstate.domain.*;
import com.tcutma.realstate.service.dto.ResidentialAreaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ResidentialArea and its DTO ResidentialAreaDTO.
 */
@Mapper(componentModel = "spring", uses = {TagMapper.class})
public interface ResidentialAreaMapper extends EntityMapper<ResidentialAreaDTO, ResidentialArea> {



    default ResidentialArea fromId(Long id) {
        if (id == null) {
            return null;
        }
        ResidentialArea residentialArea = new ResidentialArea();
        residentialArea.setId(id);
        return residentialArea;
    }
}
