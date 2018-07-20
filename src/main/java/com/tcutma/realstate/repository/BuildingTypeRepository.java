package com.tcutma.realstate.repository;

import com.tcutma.realstate.domain.BuildingType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the BuildingType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BuildingTypeRepository extends JpaRepository<BuildingType, Long> {

}
