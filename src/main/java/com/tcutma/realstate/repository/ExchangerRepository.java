package com.tcutma.realstate.repository;

import com.tcutma.realstate.domain.Exchanger;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Exchanger entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ExchangerRepository extends JpaRepository<Exchanger, Long> {

}
