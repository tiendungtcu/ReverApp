package com.tcutma.realstate.service.mapper;

import com.tcutma.realstate.domain.*;
import com.tcutma.realstate.service.dto.InvestorDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Investor and its DTO InvestorDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface InvestorMapper extends EntityMapper<InvestorDTO, Investor> {



    default Investor fromId(Long id) {
        if (id == null) {
            return null;
        }
        Investor investor = new Investor();
        investor.setId(id);
        return investor;
    }
}
