package com.tcutma.realstate.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * Comment entity
 */
@ApiModel(description = "Comment entity")
@Entity
@Table(name = "comment")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "comment_title", nullable = false)
    private String commentTitle;

    @Lob
    @Column(name = "comment_content")
    private String commentContent;

    @Column(name = "comment_created_date")
    private Instant commentCreatedDate;

    @Column(name = "comment_update_date")
    private Instant commentUpdateDate;

    @ManyToOne
    @JsonIgnoreProperties("")
    private User user;

    @ManyToOne
    @JsonIgnoreProperties("comments")
    private BlogPost post;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommentTitle() {
        return commentTitle;
    }

    public Comment commentTitle(String commentTitle) {
        this.commentTitle = commentTitle;
        return this;
    }

    public void setCommentTitle(String commentTitle) {
        this.commentTitle = commentTitle;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public Comment commentContent(String commentContent) {
        this.commentContent = commentContent;
        return this;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Instant getCommentCreatedDate() {
        return commentCreatedDate;
    }

    public Comment commentCreatedDate(Instant commentCreatedDate) {
        this.commentCreatedDate = commentCreatedDate;
        return this;
    }

    public void setCommentCreatedDate(Instant commentCreatedDate) {
        this.commentCreatedDate = commentCreatedDate;
    }

    public Instant getCommentUpdateDate() {
        return commentUpdateDate;
    }

    public Comment commentUpdateDate(Instant commentUpdateDate) {
        this.commentUpdateDate = commentUpdateDate;
        return this;
    }

    public void setCommentUpdateDate(Instant commentUpdateDate) {
        this.commentUpdateDate = commentUpdateDate;
    }

    public User getUser() {
        return user;
    }

    public Comment user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BlogPost getPost() {
        return post;
    }

    public Comment post(BlogPost blogPost) {
        this.post = blogPost;
        return this;
    }

    public void setPost(BlogPost blogPost) {
        this.post = blogPost;
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
        Comment comment = (Comment) o;
        if (comment.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), comment.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Comment{" +
            "id=" + getId() +
            ", commentTitle='" + getCommentTitle() + "'" +
            ", commentContent='" + getCommentContent() + "'" +
            ", commentCreatedDate='" + getCommentCreatedDate() + "'" +
            ", commentUpdateDate='" + getCommentUpdateDate() + "'" +
            "}";
    }
}
