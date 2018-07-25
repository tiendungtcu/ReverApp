package com.tcutma.realstate.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the RecruitmentInfo entity.
 */
public class RecruitmentInfoDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 128)
    private String recruitmentTitle;

    private String recruitmentAvatarUrl;

    @Lob
    private String recruitmentContent;

    private String recruitmentNotes;

    private LocalDate recruitmentDate;

    private Long recruitmentSeenCount;

    private Boolean recruitmentStatus;

    private Long userId;

    private String userLogin;

    private Long jobtitleId;

    private String jobtitleTitleName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecruitmentTitle() {
        return recruitmentTitle;
    }

    public void setRecruitmentTitle(String recruitmentTitle) {
        this.recruitmentTitle = recruitmentTitle;
    }

    public String getRecruitmentAvatarUrl() {
        return recruitmentAvatarUrl;
    }

    public void setRecruitmentAvatarUrl(String recruitmentAvatarUrl) {
        this.recruitmentAvatarUrl = recruitmentAvatarUrl;
    }

    public String getRecruitmentContent() {
        return recruitmentContent;
    }

    public void setRecruitmentContent(String recruitmentContent) {
        this.recruitmentContent = recruitmentContent;
    }

    public String getRecruitmentNotes() {
        return recruitmentNotes;
    }

    public void setRecruitmentNotes(String recruitmentNotes) {
        this.recruitmentNotes = recruitmentNotes;
    }

    public LocalDate getRecruitmentDate() {
        return recruitmentDate;
    }

    public void setRecruitmentDate(LocalDate recruitmentDate) {
        this.recruitmentDate = recruitmentDate;
    }

    public Long getRecruitmentSeenCount() {
        return recruitmentSeenCount;
    }

    public void setRecruitmentSeenCount(Long recruitmentSeenCount) {
        this.recruitmentSeenCount = recruitmentSeenCount;
    }

    public Boolean isRecruitmentStatus() {
        return recruitmentStatus;
    }

    public void setRecruitmentStatus(Boolean recruitmentStatus) {
        this.recruitmentStatus = recruitmentStatus;
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

    public Long getJobtitleId() {
        return jobtitleId;
    }

    public void setJobtitleId(Long jobTitleId) {
        this.jobtitleId = jobTitleId;
    }

    public String getJobtitleTitleName() {
        return jobtitleTitleName;
    }

    public void setJobtitleTitleName(String jobTitleTitleName) {
        this.jobtitleTitleName = jobTitleTitleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RecruitmentInfoDTO recruitmentInfoDTO = (RecruitmentInfoDTO) o;
        if (recruitmentInfoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), recruitmentInfoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RecruitmentInfoDTO{" +
            "id=" + getId() +
            ", recruitmentTitle='" + getRecruitmentTitle() + "'" +
            ", recruitmentAvatarUrl='" + getRecruitmentAvatarUrl() + "'" +
            ", recruitmentContent='" + getRecruitmentContent() + "'" +
            ", recruitmentNotes='" + getRecruitmentNotes() + "'" +
            ", recruitmentDate='" + getRecruitmentDate() + "'" +
            ", recruitmentSeenCount=" + getRecruitmentSeenCount() +
            ", recruitmentStatus='" + isRecruitmentStatus() + "'" +
            ", user=" + getUserId() +
            ", user='" + getUserLogin() + "'" +
            ", jobtitle=" + getJobtitleId() +
            ", jobtitle='" + getJobtitleTitleName() + "'" +
            "}";
    }
}
