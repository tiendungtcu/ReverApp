package com.tcutma.realstate.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Comment entity.
 */
public class CommentDTO implements Serializable {

    private Long id;

    private String commentContent;

    private Instant commentTimeStamp;

    private Long userId;

    private String userLogin;

    private Long postId;

    private String postPostTitle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Instant getCommentTimeStamp() {
        return commentTimeStamp;
    }

    public void setCommentTimeStamp(Instant commentTimeStamp) {
        this.commentTimeStamp = commentTimeStamp;
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

    public String getPostPostTitle() {
        return postPostTitle;
    }

    public void setPostPostTitle(String blogPostPostTitle) {
        this.postPostTitle = blogPostPostTitle;
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
            ", commentContent='" + getCommentContent() + "'" +
            ", commentTimeStamp='" + getCommentTimeStamp() + "'" +
            ", user=" + getUserId() +
            ", user='" + getUserLogin() + "'" +
            ", post=" + getPostId() +
            ", post='" + getPostPostTitle() + "'" +
            "}";
    }
}
