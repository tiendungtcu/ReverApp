package com.tcutma.realstate.service.mapper;

import com.tcutma.realstate.domain.*;
import com.tcutma.realstate.service.dto.NotificationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Notification and its DTO NotificationDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface NotificationMapper extends EntityMapper<NotificationDTO, Notification> {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.login", target = "userLogin")
    NotificationDTO toDto(Notification notification);

    @Mapping(source = "userId", target = "user")
    Notification toEntity(NotificationDTO notificationDTO);

    default Notification fromId(Long id) {
        if (id == null) {
            return null;
        }
        Notification notification = new Notification();
        notification.setId(id);
        return notification;
    }
}
