package com.tcutma.realstate.service.mapper;

import com.tcutma.realstate.domain.*;
import com.tcutma.realstate.service.dto.RequestDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Request and its DTO RequestDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, PropertyMapper.class, ProjectMapper.class})
public interface RequestMapper extends EntityMapper<RequestDTO, Request> {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.login", target = "userLogin")
    @Mapping(source = "property.id", target = "propertyId")
    @Mapping(source = "property.propertyName", target = "propertyPropertyName")
    @Mapping(source = "project.id", target = "projectId")
    @Mapping(source = "project.projectName", target = "projectProjectName")
    RequestDTO toDto(Request request);

    @Mapping(source = "userId", target = "user")
    @Mapping(source = "propertyId", target = "property")
    @Mapping(source = "projectId", target = "project")
    Request toEntity(RequestDTO requestDTO);

    default Request fromId(Long id) {
        if (id == null) {
            return null;
        }
        Request request = new Request();
        request.setId(id);
        return request;
    }
}
