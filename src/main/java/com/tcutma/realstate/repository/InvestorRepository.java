package com.tcutma.realstate.repository;

import com.tcutma.realstate.domain.Investor;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Investor entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InvestorRepository extends JpaRepository<Investor, Long> {

}
