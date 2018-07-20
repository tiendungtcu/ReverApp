package com.tcutma.realstate.repository;

import com.tcutma.realstate.domain.ProjectUser;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the ProjectUser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProjectUserRepository extends JpaRepository<ProjectUser, Long> {

    @Query("select project_user from ProjectUser project_user where project_user.user.login = ?#{principal.username}")
    List<ProjectUser> findByUserIsCurrentUser();

}
