package com.tcutma.realstate.domain;

import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import com.tcutma.realstate.domain.enumeration.NotificationType;

/**
 * Notification - entity
 */
@ApiModel(description = "Notification - entity")
@Entity
@Table(name = "notification")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Notification implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 128)
    @Column(name = "notification_title", length = 128, nullable = false)
    private String notificationTitle;

    @Lob
    @Column(name = "notification_content")
    private String notificationContent;

    @Column(name = "notification_seen")
    private Boolean notificationSeen;

    @Column(name = "notification_date")
    private Instant notificationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "notification_type")
    private NotificationType notificationType;

    @Column(name = "notification_sender")
    private Long notificationSender;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNotificationTitle() {
        return notificationTitle;
    }

    public Notification notificationTitle(String notificationTitle) {
        this.notificationTitle = notificationTitle;
        return this;
    }

    public void setNotificationTitle(String notificationTitle) {
        this.notificationTitle = notificationTitle;
    }

    public String getNotificationContent() {
        return notificationContent;
    }

    public Notification notificationContent(String notificationContent) {
        this.notificationContent = notificationContent;
        return this;
    }

    public void setNotificationContent(String notificationContent) {
        this.notificationContent = notificationContent;
    }

    public Boolean isNotificationSeen() {
        return notificationSeen;
    }

    public Notification notificationSeen(Boolean notificationSeen) {
        this.notificationSeen = notificationSeen;
        return this;
    }

    public void setNotificationSeen(Boolean notificationSeen) {
        this.notificationSeen = notificationSeen;
    }

    public Instant getNotificationDate() {
        return notificationDate;
    }

    public Notification notificationDate(Instant notificationDate) {
        this.notificationDate = notificationDate;
        return this;
    }

    public void setNotificationDate(Instant notificationDate) {
        this.notificationDate = notificationDate;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public Notification notificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
        return this;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public Long getNotificationSender() {
        return notificationSender;
    }

    public Notification notificationSender(Long notificationSender) {
        this.notificationSender = notificationSender;
        return this;
    }

    public void setNotificationSender(Long notificationSender) {
        this.notificationSender = notificationSender;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Notification notification = (Notification) o;
        if (notification.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), notification.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Notification{" +
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
