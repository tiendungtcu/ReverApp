package com.tcutma.realstate.service.dto;

import java.time.ZonedDateTime;
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
    private String recruitmentTitle;

    @Lob
    private byte[] recruitmentImage;
    private String recruitmentImageContentType;

    @Lob
    private String recruitmentContent;

    private String recruitmentNotes;

    private ZonedDateTime recruitmentDate;

    private Long recruitmentSeenCount;

    private Boolean recruitmentStatus;

    private Long photoId;

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

    public byte[] getRecruitmentImage() {
        return recruitmentImage;
    }

    public void setRecruitmentImage(byte[] recruitmentImage) {
        this.recruitmentImage = recruitmentImage;
    }

    public String getRecruitmentImageContentType() {
        return recruitmentImageContentType;
    }

    public void setRecruitmentImageContentType(String recruitmentImageContentType) {
        this.recruitmentImageContentType = recruitmentImageContentType;
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

    public ZonedDateTime getRecruitmentDate() {
        return recruitmentDate;
    }

    public void setRecruitmentDate(ZonedDateTime recruitmentDate) {
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

    public Long getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Long photoId) {
        this.photoId = photoId;
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
            ", recruitmentImage='" + getRecruitmentImage() + "'" +
            ", recruitmentContent='" + getRecruitmentContent() + "'" +
            ", recruitmentNotes='" + getRecruitmentNotes() + "'" +
            ", recruitmentDate='" + getRecruitmentDate() + "'" +
            ", recruitmentSeenCount=" + getRecruitmentSeenCount() +
            ", recruitmentStatus='" + isRecruitmentStatus() + "'" +
            ", photo=" + getPhotoId() +
            ", jobtitle=" + getJobtitleId() +
            ", jobtitle='" + getJobtitleTitleName() + "'" +
            "}";
    }
}
