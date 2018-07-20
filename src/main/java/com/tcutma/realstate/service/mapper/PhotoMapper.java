package com.tcutma.realstate.service.mapper;

import com.tcutma.realstate.domain.*;
import com.tcutma.realstate.service.dto.PhotoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Photo and its DTO PhotoDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PhotoMapper extends EntityMapper<PhotoDTO, Photo> {


    @Mapping(target = "properties", ignore = true)
    @Mapping(target = "projects", ignore = true)
    Photo toEntity(PhotoDTO photoDTO);

    default Photo fromId(Long id) {
        if (id == null) {
            return null;
        }
        Photo photo = new Photo();
        photo.setId(id);
        return photo;
    }
}
