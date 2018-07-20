package com.tcutma.realstate.service.dto;

import java.io.Serializable;
import com.tcutma.realstate.domain.enumeration.NotificationType;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;



import io.github.jhipster.service.filter.ZonedDateTimeFilter;


/**
 * Criteria class for the Notification entity. This class is used in NotificationResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /notifications?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class NotificationCriteria implements Serializable {
    /**
     * Class for filtering NotificationType
     */
    public static class NotificationTypeFilter extends Filter<NotificationType> {
    }

    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private StringFilter notificationTitle;

    private BooleanFilter notificationSeen;

    private ZonedDateTimeFilter notificationDate;

    private NotificationTypeFilter notificationType;

    private StringFilter notificationReference;

    private LongFilter userId;

    public NotificationCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getNotificationTitle() {
        return notificationTitle;
    }

    public void setNotificationTitle(StringFilter notificationTitle) {
        this.notificationTitle = notificationTitle;
    }

    public BooleanFilter getNotificationSeen() {
        return notificationSeen;
    }

    public void setNotificationSeen(BooleanFilter notificationSeen) {
        this.notificationSeen = notificationSeen;
    }

    public ZonedDateTimeFilter getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(ZonedDateTimeFilter notificationDate) {
        this.notificationDate = notificationDate;
    }

    public NotificationTypeFilter getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationTypeFilter notificationType) {
        this.notificationType = notificationType;
    }

    public StringFilter getNotificationReference() {
        return notificationReference;
    }

    public void setNotificationReference(StringFilter notificationReference) {
        this.notificationReference = notificationReference;
    }

    public LongFilter getUserId() {
        return userId;
    }

    public void setUserId(LongFilter userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "NotificationCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (notificationTitle != null ? "notificationTitle=" + notificationTitle + ", " : "") +
                (notificationSeen != null ? "notificationSeen=" + notificationSeen + ", " : "") +
                (notificationDate != null ? "notificationDate=" + notificationDate + ", " : "") +
                (notificationType != null ? "notificationType=" + notificationType + ", " : "") +
                (notificationReference != null ? "notificationReference=" + notificationReference + ", " : "") +
                (userId != null ? "userId=" + userId + ", " : "") +
            "}";
    }

}
