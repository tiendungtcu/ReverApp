package com.tcutma.realstate.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;
import com.tcutma.realstate.domain.enumeration.BlogStatus;

/**
 * A DTO for the BlogPost entity.
 */
public class BlogPostDTO implements Serializable {

    private Long id;

    @NotNull
    private String postTitle;

    private BlogStatus postStatus;

    private Instant postCreatedDate;

    private Instant postPublishDate;

    private Instant postUpdateDate;

    private Long postSeenCount;

    @Lob
    private String postContent;

    private Long categoryId;

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

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public BlogStatus getPostStatus() {
        return postStatus;
    }

    public void setPostStatus(BlogStatus postStatus) {
        this.postStatus = postStatus;
    }

    public Instant getPostCreatedDate() {
        return postCreatedDate;
    }

    public void setPostCreatedDate(Instant postCreatedDate) {
        this.postCreatedDate = postCreatedDate;
    }

    public Instant getPostPublishDate() {
        return postPublishDate;
    }

    public void setPostPublishDate(Instant postPublishDate) {
        this.postPublishDate = postPublishDate;
    }

    public Instant getPostUpdateDate() {
        return postUpdateDate;
    }

    public void setPostUpdateDate(Instant postUpdateDate) {
        this.postUpdateDate = postUpdateDate;
    }

    public Long getPostSeenCount() {
        return postSeenCount;
    }

    public void setPostSeenCount(Long postSeenCount) {
        this.postSeenCount = postSeenCount;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
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

        BlogPostDTO blogPostDTO = (BlogPostDTO) o;
        if (blogPostDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), blogPostDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BlogPostDTO{" +
            "id=" + getId() +
            ", postTitle='" + getPostTitle() + "'" +
            ", postStatus='" + getPostStatus() + "'" +
            ", postCreatedDate='" + getPostCreatedDate() + "'" +
            ", postPublishDate='" + getPostPublishDate() + "'" +
            ", postUpdateDate='" + getPostUpdateDate() + "'" +
            ", postSeenCount=" + getPostSeenCount() +
            ", postContent='" + getPostContent() + "'" +
            ", category=" + getCategoryId() +
            ", user=" + getUserId() +
            ", user='" + getUserLogin() + "'" +
            ", project=" + getProjectId() +
            ", project='" + getProjectProjectName() + "'" +
            "}";
    }
}
