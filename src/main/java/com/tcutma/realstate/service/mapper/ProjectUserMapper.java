package com.tcutma.realstate.service.mapper;

import com.tcutma.realstate.domain.*;
import com.tcutma.realstate.service.dto.ProjectUserDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ProjectUser and its DTO ProjectUserDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, ProjectMapper.class})
public interface ProjectUserMapper extends EntityMapper<ProjectUserDTO, ProjectUser> {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.login", target = "userLogin")
    @Mapping(source = "project.id", target = "projectId")
    @Mapping(source = "project.projectName", target = "projectProjectName")
    ProjectUserDTO toDto(ProjectUser projectUser);

    @Mapping(source = "userId", target = "user")
    @Mapping(source = "projectId", target = "project")
    ProjectUser toEntity(ProjectUserDTO projectUserDTO);

    default ProjectUser fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProjectUser projectUser = new ProjectUser();
        projectUser.setId(id);
        return projectUser;
    }
}
