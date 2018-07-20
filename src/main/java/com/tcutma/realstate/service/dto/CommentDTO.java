package com.tcutma.realstate.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the Comment entity.
 */
public class CommentDTO implements Serializable {

    private Long id;

    @NotNull
    private String commentTitle;

    @Lob
    private String commentContent;

    private Instant commentCreatedDate;

    private Instant commentUpdateDate;

    private Long userId;

    private String userLogin;

    private Long postId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommentTitle() {
        return commentTitle;
    }

    public void setCommentTitle(String commentTitle) {
        this.commentTitle = commentTitle;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Instant getCommentCreatedDate() {
        return commentCreatedDate;
    }

    public void setCommentCreatedDate(Instant commentCreatedDate) {
        this.commentCreatedDate = commentCreatedDate;
    }

    public Instant getCommentUpdateDate() {
        return commentUpdateDate;
    }

    public void setCommentUpdateDate(Instant commentUpdateDate) {
        this.commentUpdateDate = commentUpdateDate;
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

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long blogPostId) {
        this.postId = blogPostId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CommentDTO commentDTO = (CommentDTO) o;
        if (commentDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), commentDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CommentDTO{" +
            "id=" + getId() +
            ", commentTitle='" + getCommentTitle() + "'" +
            ", commentContent='" + getCommentContent() + "'" +
            ", commentCreatedDate='" + getCommentCreatedDate() + "'" +
            ", commentUpdateDate='" + getCommentUpdateDate() + "'" +
            ", user=" + getUserId() +
            ", user='" + getUserLogin() + "'" +
            ", post=" + getPostId() +
            "}";
    }
}
