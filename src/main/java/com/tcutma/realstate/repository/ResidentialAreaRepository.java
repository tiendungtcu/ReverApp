package com.tcutma.realstate.repository;

import com.tcutma.realstate.domain.ResidentialArea;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the ResidentialArea entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ResidentialAreaRepository extends JpaRepository<ResidentialArea, Long>, JpaSpecificationExecutor<ResidentialArea> {

    @Query(value = "select distinct residential_area from ResidentialArea residential_area left join fetch residential_area.tags",
        countQuery = "select count(distinct residential_area) from ResidentialArea residential_area")
    Page<ResidentialArea> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct residential_area from ResidentialArea residential_area left join fetch residential_area.tags")
    List<ResidentialArea> findAllWithEagerRelationships();

    @Query("select residential_area from ResidentialArea residential_area left join fetch residential_area.tags where residential_area.id =:id")
    Optional<ResidentialArea> findOneWithEagerRelationships(@Param("id") Long id);

}
