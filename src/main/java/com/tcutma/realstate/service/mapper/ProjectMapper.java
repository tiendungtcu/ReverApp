package com.tcutma.realstate.service.mapper;

import com.tcutma.realstate.domain.*;
import com.tcutma.realstate.service.dto.ProjectDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Project and its DTO ProjectDTO.
 */
@Mapper(componentModel = "spring", uses = {DocumentMapper.class, LocationMapper.class, TagMapper.class, BuildingTypeMapper.class, InvestorMapper.class, ProjectBuilderMapper.class, PhotoMapper.class})
public interface ProjectMapper extends EntityMapper<ProjectDTO, Project> {

    @Mapping(source = "document.id", target = "documentId")
    @Mapping(source = "location.id", target = "locationId")
    ProjectDTO toDto(Project project);

    @Mapping(source = "documentId", target = "document")
    @Mapping(source = "locationId", target = "location")
    @Mapping(target = "posts", ignore = true)
    Project toEntity(ProjectDTO projectDTO);

    default Project fromId(Long id) {
        if (id == null) {
            return null;
        }
        Project project = new Project();
        project.setId(id);
        return project;
    }
}
