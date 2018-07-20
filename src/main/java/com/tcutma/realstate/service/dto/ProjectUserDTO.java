package com.tcutma.realstate.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the ProjectUser entity.
 */
public class ProjectUserDTO implements Serializable {

    private Long id;

    private Boolean liked;

    private Boolean shared;

    private Instant likedDate;

    private Instant sharedDate;

    private Long userId;

    private String userLogin;

    private Long projectId;

    private String projectProjectName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isLiked() {
        return liked;
    }

    public void setLiked(Boolean liked) {
        this.liked = liked;
    }

    public Boolean isShared() {
        return shared;
    }

    public void setShared(Boolean shared) {
        this.shared = shared;
    }

    public Instant getLikedDate() {
        return likedDate;
    }

    public void setLikedDate(Instant likedDate) {
        this.likedDate = likedDate;
    }

    public Instant getSharedDate() {
        return sharedDate;
    }

    public void setSharedDate(Instant sharedDate) {
        this.sharedDate = sharedDate;
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

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getProjectProjectName() {
        return projectProjectName;
    }

    public void setProjectProjectName(String projectProjectName) {
        this.projectProjectName = projectProjectName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProjectUserDTO projectUserDTO = (ProjectUserDTO) o;
        if (projectUserDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), projectUserDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProjectUserDTO{" +
            "id=" + getId() +
            ", liked='" + isLiked() + "'" +
            ", shared='" + isShared() + "'" +
            ", likedDate='" + getLikedDate() + "'" +
            ", sharedDate='" + getSharedDate() + "'" +
            ", user=" + getUserId() +
            ", user='" + getUserLogin() + "'" +
            ", project=" + getProjectId() +
            ", project='" + getProjectProjectName() + "'" +
            "}";
    }
}
