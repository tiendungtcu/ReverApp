package com.tcutma.realstate.repository;

import com.tcutma.realstate.domain.ProjectBuilder;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ProjectBuilder entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProjectBuilderRepository extends JpaRepository<ProjectBuilder, Long> {

}
