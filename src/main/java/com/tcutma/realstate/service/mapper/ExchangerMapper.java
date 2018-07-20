package com.tcutma.realstate.service.mapper;

import com.tcutma.realstate.domain.*;
import com.tcutma.realstate.service.dto.ExchangerDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Exchanger and its DTO ExchangerDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ExchangerMapper extends EntityMapper<ExchangerDTO, Exchanger> {



    default Exchanger fromId(Long id) {
        if (id == null) {
            return null;
        }
        Exchanger exchanger = new Exchanger();
        exchanger.setId(id);
        return exchanger;
    }
}
