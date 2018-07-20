package com.tcutma.realstate.repository;

import com.tcutma.realstate.domain.SupportCategory;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SupportCategory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SupportCategoryRepository extends JpaRepository<SupportCategory, Long> {

}
