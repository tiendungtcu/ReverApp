package com.tcutma.realstate.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

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

    @Column(name = "comment_content")
    private String commentContent;

    @Column(name = "comment_time_stamp")
    private Instant commentTimeStamp;

    @ManyToOne
    @JsonIgnoreProperties("")
    private User user;

    @ManyToOne
    @JsonIgnoreProperties("")
    private BlogPost post;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Instant getCommentTimeStamp() {
        return commentTimeStamp;
    }

    public Comment commentTimeStamp(Instant commentTimeStamp) {
        this.commentTimeStamp = commentTimeStamp;
        return this;
    }

    public void setCommentTimeStamp(Instant commentTimeStamp) {
        this.commentTimeStamp = commentTimeStamp;
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
            ", commentContent='" + getCommentContent() + "'" +
            ", commentTimeStamp='" + getCommentTimeStamp() + "'" +
            "}";
    }
}
