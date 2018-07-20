package com.tcutma.realstate.service.mapper;

import com.tcutma.realstate.domain.*;
import com.tcutma.realstate.service.dto.ProjectBuilderDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ProjectBuilder and its DTO ProjectBuilderDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProjectBuilderMapper extends EntityMapper<ProjectBuilderDTO, ProjectBuilder> {


    @Mapping(target = "projects", ignore = true)
    ProjectBuilder toEntity(ProjectBuilderDTO projectBuilderDTO);

    default ProjectBuilder fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProjectBuilder projectBuilder = new ProjectBuilder();
        projectBuilder.setId(id);
        return projectBuilder;
    }
}
