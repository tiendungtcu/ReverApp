package com.tcutma.realstate.service.dto;

import java.time.ZonedDateTime;
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
    private String notificationTitle;

    @Lob
    private String notificationContent;

    private Boolean notificationSeen;

    private ZonedDateTime notificationDate;

    private NotificationType notificationType;

    private String notificationReference;

    private Long userId;

    private String userLogin;

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

    public ZonedDateTime getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(ZonedDateTime notificationDate) {
        this.notificationDate = notificationDate;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public String getNotificationReference() {
        return notificationReference;
    }

    public void setNotificationReference(String notificationReference) {
        this.notificationReference = notificationReference;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
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
            ", notificationReference='" + getNotificationReference() + "'" +
            ", user=" + getUserId() +
            ", user='" + getUserLogin() + "'" +
            "}";
    }
}
