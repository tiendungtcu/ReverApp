package com.tcutma.realstate.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;
import com.tcutma.realstate.domain.enumeration.NotificationType;

/**
 * A DTO for the Notification entity.
 */
public class NotificationDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 128)
    private String notificationTitle;

    @Lob
    private String notificationContent;

    private Boolean notificationSeen;

    private Instant notificationDate;

    private NotificationType notificationType;

    private Long notificationSender;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNotificationTitle() {
        return notificationTitle;
    }

    public void setNotificationTitle(String notificationTitle) {
        this.notificationTitle = notificationTitle;
    }

    public String getNotificationContent() {
        return notificationContent;
    }

    public void setNotificationContent(String notificationContent) {
        this.notificationContent = notificationContent;
    }

    public Boolean isNotificationSeen() {
        return notificationSeen;
    }

    public void setNotificationSeen(Boolean notificationSeen) {
        this.notificationSeen = notificationSeen;
    }

    public Instant getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(Instant notificationDate) {
        this.notificationDate = notificationDate;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public Long getNotificationSender() {
        return notificationSender;
    }

    public void setNotificationSender(Long notificationSender) {
        this.notificationSender = notificationSender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        NotificationDTO notificationDTO = (NotificationDTO) o;
        if (notificationDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), notificationDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NotificationDTO{" +
            "id=" + getId() +
            ", notificationTitle='" + getNotificationTitle() + "'" +
            ", notificationContent='" + getNotificationContent() + "'" +
            ", notificationSeen='" + isNotificationSeen() + "'" +
            ", notificationDate='" + getNotificationDate() + "'" +
            ", notificationType='" + getNotificationType() + "'" +
            ", notificationSender=" + getNotificationSender() +
            "}";
    }
}
