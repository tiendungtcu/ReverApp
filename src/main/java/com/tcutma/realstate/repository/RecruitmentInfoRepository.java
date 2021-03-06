package com.tcutma.realstate.repository;

import com.tcutma.realstate.domain.RecruitmentInfo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the RecruitmentInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RecruitmentInfoRepository extends JpaRepository<RecruitmentInfo, Long> {

    @Query("select recruitment_info from RecruitmentInfo recruitment_info where recruitment_info.user.login = ?#{principal.username}")
    List<RecruitmentInfo> findByUserIsCurrentUser();

}
