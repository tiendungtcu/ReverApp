package com.tcutma.realstate.repository;

import com.tcutma.realstate.domain.Property;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Property entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PropertyRepository extends JpaRepository<Property, Long>, JpaSpecificationExecutor<Property> {

    @Query(value = "select distinct property from Property property left join fetch property.tags",
        countQuery = "select count(distinct property) from Property property")
    Page<Property> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct property from Property property left join fetch property.tags")
    List<Property> findAllWithEagerRelationships();

    @Query("select property from Property property left join fetch property.tags where property.id =:id")
    Optional<Property> findOneWithEagerRelationships(@Param("id") Long id);

}
