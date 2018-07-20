package com.tcutma.realstate.service.mapper;

import com.tcutma.realstate.domain.*;
import com.tcutma.realstate.service.dto.JobTitleDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity JobTitle and its DTO JobTitleDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface JobTitleMapper extends EntityMapper<JobTitleDTO, JobTitle> {



    default JobTitle fromId(Long id) {
        if (id == null) {
            return null;
        }
        JobTitle jobTitle = new JobTitle();
        jobTitle.setId(id);
        return jobTitle;
    }
}
