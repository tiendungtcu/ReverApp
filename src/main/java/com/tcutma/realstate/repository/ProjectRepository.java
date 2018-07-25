package com.tcutma.realstate.repository;

import com.tcutma.realstate.domain.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Project entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>, JpaSpecificationExecutor<Project> {

    @Query(value = "select distinct project from Project project left join fetch project.tags left join fetch project.buildingtypes left join fetch project.inverstors left join fetch project.contractors",
        countQuery = "select count(distinct project) from Project project")
    Page<Project> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct project from Project project left join fetch project.tags left join fetch project.buildingtypes left join fetch project.inverstors left join fetch project.contractors")
    List<Project> findAllWithEagerRelationships();

    @Query("select project from Project project left join fetch project.tags left join fetch project.buildingtypes left join fetch project.inverstors left join fetch project.contractors where project.id =:id")
    Optional<Project> findOneWithEagerRelationships(@Param("id") Long id);

}
