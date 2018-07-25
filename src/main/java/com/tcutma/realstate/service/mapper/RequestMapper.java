package com.tcutma.realstate.service.mapper;

import com.tcutma.realstate.domain.*;
import com.tcutma.realstate.service.dto.RequestDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Request and its DTO RequestDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface RequestMapper extends EntityMapper<RequestDTO, Request> {

    @Mapping(source = "sender.id", target = "senderId")
    @Mapping(source = "sender.login", target = "senderLogin")
    @Mapping(source = "receiver.id", target = "receiverId")
    @Mapping(source = "receiver.login", target = "receiverLogin")
    RequestDTO toDto(Request request);

    @Mapping(source = "senderId", target = "sender")
    @Mapping(source = "receiverId", target = "receiver")
    Request toEntity(RequestDTO requestDTO);

    default Request fromId(Long id) {
        if (id == null) {
            return null;
        }
        Request request = new Request();
        request.setId(id);
        return request;
    }
}
