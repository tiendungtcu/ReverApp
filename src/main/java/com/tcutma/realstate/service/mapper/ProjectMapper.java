package com.tcutma.realstate.service.mapper;

import com.tcutma.realstate.domain.*;
import com.tcutma.realstate.service.dto.ProjectDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Project and its DTO ProjectDTO.
 */
@Mapper(componentModel = "spring", uses = {LocationMapper.class, UserMapper.class, TagMapper.class, BuildingTypeMapper.class, InvestorMapper.class, ContractorMapper.class})
public interface ProjectMapper extends EntityMapper<ProjectDTO, Project> {

    @Mapping(source = "location.id", target = "locationId")
    @Mapping(source = "consultant.id", target = "consultantId")
    @Mapping(source = "consultant.login", target = "consultantLogin")
    ProjectDTO toDto(Project project);

    @Mapping(source = "locationId", target = "location")
    @Mapping(source = "consultantId", target = "consultant")
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
