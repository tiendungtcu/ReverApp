package com.tcutma.realstate.repository;

import com.tcutma.realstate.domain.Request;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Request entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

    @Query("select request from Request request where request.user.login = ?#{principal.username}")
    List<Request> findByUserIsCurrentUser();

}
