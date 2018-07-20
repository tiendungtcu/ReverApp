package com.tcutma.realstate.repository;

import com.tcutma.realstate.domain.PropertyUser;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the PropertyUser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PropertyUserRepository extends JpaRepository<PropertyUser, Long> {

    @Query("select property_user from PropertyUser property_user where property_user.user.login = ?#{principal.username}")
    List<PropertyUser> findByUserIsCurrentUser();

}
