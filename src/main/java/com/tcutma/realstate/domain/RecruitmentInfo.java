package com.tcutma.realstate.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * RecruitmentInfo entity
 */
@ApiModel(description = "RecruitmentInfo entity")
@Entity
@Table(name = "recruitment_info")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RecruitmentInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 128)
    @Column(name = "recruitment_title", length = 128, nullable = false)
    private String recruitmentTitle;

    @Column(name = "recruitment_avatar_url")
    private String recruitmentAvatarUrl;

    @Lob
    @Column(name = "recruitment_content")
    private String recruitmentContent;

    @Column(name = "recruitment_notes")
    private String recruitmentNotes;

    @Column(name = "recruitment_date")
    private LocalDate recruitmentDate;

    @Column(name = "recruitment_seen_count")
    private Long recruitmentSeenCount;

    @Column(name = "recruitment_status")
    private Boolean recruitmentStatus;

    @ManyToOne
    @JsonIgnoreProperties("")
    private User user;

    @ManyToOne
    @JsonIgnoreProperties("")
    private JobTitle jobtitle;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecruitmentTitle() {
        return recruitmentTitle;
    }

    public RecruitmentInfo recruitmentTitle(String recruitmentTitle) {
        this.recruitmentTitle = recruitmentTitle;
        return this;
    }

    public void setRecruitmentTitle(String recruitmentTitle) {
        this.recruitmentTitle = recruitmentTitle;
    }

    public String getRecruitmentAvatarUrl() {
        return recruitmentAvatarUrl;
    }

    public RecruitmentInfo recruitmentAvatarUrl(String recruitmentAvatarUrl) {
        this.recruitmentAvatarUrl = recruitmentAvatarUrl;
        return this;
    }

    public void setRecruitmentAvatarUrl(String recruitmentAvatarUrl) {
        this.recruitmentAvatarUrl = recruitmentAvatarUrl;
    }

    public String getRecruitmentContent() {
        return recruitmentContent;
    }

    public RecruitmentInfo recruitmentContent(String recruitmentContent) {
        this.recruitmentContent = recruitmentContent;
        return this;
    }

    public void setRecruitmentContent(String recruitmentContent) {
        this.recruitmentContent = recruitmentContent;
    }

    public String getRecruitmentNotes() {
        return recruitmentNotes;
    }

    public RecruitmentInfo recruitmentNotes(String recruitmentNotes) {
        this.recruitmentNotes = recruitmentNotes;
        return this;
    }

    public void setRecruitmentNotes(String recruitmentNotes) {
        this.recruitmentNotes = recruitmentNotes;
    }

    public LocalDate getRecruitmentDate() {
        return recruitmentDate;
    }

    public RecruitmentInfo recruitmentDate(LocalDate recruitmentDate) {
        this.recruitmentDate = recruitmentDate;
        return this;
    }

    public void setRecruitmentDate(LocalDate recruitmentDate) {
        this.recruitmentDate = recruitmentDate;
    }

    public Long getRecruitmentSeenCount() {
        return recruitmentSeenCount;
    }

    public RecruitmentInfo recruitmentSeenCount(Long recruitmentSeenCount) {
        this.recruitmentSeenCount = recruitmentSeenCount;
        return this;
    }

    public void setRecruitmentSeenCount(Long recruitmentSeenCount) {
        this.recruitmentSeenCount = recruitmentSeenCount;
    }

    public Boolean isRecruitmentStatus() {
        return recruitmentStatus;
    }

    public RecruitmentInfo recruitmentStatus(Boolean recruitmentStatus) {
        this.recruitmentStatus = recruitmentStatus;
        return this;
    }

    public void setRecruitmentStatus(Boolean recruitmentStatus) {
        this.recruitmentStatus = recruitmentStatus;
    }

    public User getUser() {
        return user;
    }

    public RecruitmentInfo user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public JobTitle getJobtitle() {
        return jobtitle;
    }

    public RecruitmentInfo jobtitle(JobTitle jobTitle) {
        this.jobtitle = jobTitle;
        return this;
    }

    public void setJobtitle(JobTitle jobTitle) {
        this.jobtitle = jobTitle;
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
        RecruitmentInfo recruitmentInfo = (RecruitmentInfo) o;
        if (recruitmentInfo.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), recruitmentInfo.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RecruitmentInfo{" +
            "id=" + getId() +
            ", recruitmentTitle='" + getRecruitmentTitle() + "'" +
            ", recruitmentAvatarUrl='" + getRecruitmentAvatarUrl() + "'" +
            ", recruitmentContent='" + getRecruitmentContent() + "'" +
            ", recruitmentNotes='" + getRecruitmentNotes() + "'" +
            ", recruitmentDate='" + getRecruitmentDate() + "'" +
            ", recruitmentSeenCount=" + getRecruitmentSeenCount() +
            ", recruitmentStatus='" + isRecruitmentStatus() + "'" +
            "}";
    }
}
