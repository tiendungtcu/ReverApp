package com.tcutma.realstate.repository;

import com.tcutma.realstate.domain.RecruitmentInfo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the RecruitmentInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RecruitmentInfoRepository extends JpaRepository<RecruitmentInfo, Long> {

}
