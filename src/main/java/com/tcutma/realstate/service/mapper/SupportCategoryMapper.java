package com.tcutma.realstate.service.mapper;

import com.tcutma.realstate.domain.*;
import com.tcutma.realstate.service.dto.SupportCategoryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity SupportCategory and its DTO SupportCategoryDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SupportCategoryMapper extends EntityMapper<SupportCategoryDTO, SupportCategory> {



    default SupportCategory fromId(Long id) {
        if (id == null) {
            return null;
        }
        SupportCategory supportCategory = new SupportCategory();
        supportCategory.setId(id);
        return supportCategory;
    }
}
