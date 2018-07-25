package com.tcutma.realstate.service.mapper;

import com.tcutma.realstate.domain.*;
import com.tcutma.realstate.service.dto.RecruitmentInfoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity RecruitmentInfo and its DTO RecruitmentInfoDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, JobTitleMapper.class})
public interface RecruitmentInfoMapper extends EntityMapper<RecruitmentInfoDTO, RecruitmentInfo> {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.login", target = "userLogin")
    @Mapping(source = "jobtitle.id", target = "jobtitleId")
    @Mapping(source = "jobtitle.titleName", target = "jobtitleTitleName")
    RecruitmentInfoDTO toDto(RecruitmentInfo recruitmentInfo);

    @Mapping(source = "userId", target = "user")
    @Mapping(source = "jobtitleId", target = "jobtitle")
    RecruitmentInfo toEntity(RecruitmentInfoDTO recruitmentInfoDTO);

    default RecruitmentInfo fromId(Long id) {
        if (id == null) {
            return null;
        }
        RecruitmentInfo recruitmentInfo = new RecruitmentInfo();
        recruitmentInfo.setId(id);
        return recruitmentInfo;
    }
}
