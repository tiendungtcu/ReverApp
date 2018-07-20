package com.tcutma.realstate.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.tcutma.realstate.domain.enumeration.BlogStatus;

/**
 * BlogPost entity
 */
@ApiModel(description = "BlogPost entity")
@Entity
@Table(name = "blog_post")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class BlogPost implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "post_title", nullable = false)
    private String postTitle;

    @Enumerated(EnumType.STRING)
    @Column(name = "post_status")
    private BlogStatus postStatus;

    @Column(name = "post_created_date")
    private Instant postCreatedDate;

    @Column(name = "post_publish_date")
    private Instant postPublishDate;

    @Column(name = "post_update_date")
    private Instant postUpdateDate;

    @Column(name = "post_seen_count")
    private Long postSeenCount;

    @Lob
    @Column(name = "post_content")
    private String postContent;

    @OneToMany(mappedBy = "post")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Comment> comments = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("")
    private Category category;

    @ManyToOne
    @JsonIgnoreProperties("")
    private User user;

    @ManyToOne
    @JsonIgnoreProperties("posts")
    private Project project;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public BlogPost postTitle(String postTitle) {
        this.postTitle = postTitle;
        return this;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public BlogStatus getPostStatus() {
        return postStatus;
    }

    public BlogPost postStatus(BlogStatus postStatus) {
        this.postStatus = postStatus;
        return this;
    }

    public void setPostStatus(BlogStatus postStatus) {
        this.postStatus = postStatus;
    }

    public Instant getPostCreatedDate() {
        return postCreatedDate;
    }

    public BlogPost postCreatedDate(Instant postCreatedDate) {
        this.postCreatedDate = postCreatedDate;
        return this;
    }

    public void setPostCreatedDate(Instant postCreatedDate) {
        this.postCreatedDate = postCreatedDate;
    }

    public Instant getPostPublishDate() {
        return postPublishDate;
    }

    public BlogPost postPublishDate(Instant postPublishDate) {
        this.postPublishDate = postPublishDate;
        return this;
    }

    public void setPostPublishDate(Instant postPublishDate) {
        this.postPublishDate = postPublishDate;
    }

    public Instant getPostUpdateDate() {
        return postUpdateDate;
    }

    public BlogPost postUpdateDate(Instant postUpdateDate) {
        this.postUpdateDate = postUpdateDate;
        return this;
    }

    public void setPostUpdateDate(Instant postUpdateDate) {
        this.postUpdateDate = postUpdateDate;
    }

    public Long getPostSeenCount() {
        return postSeenCount;
    }

    public BlogPost postSeenCount(Long postSeenCount) {
        this.postSeenCount = postSeenCount;
        return this;
    }

    public void setPostSeenCount(Long postSeenCount) {
        this.postSeenCount = postSeenCount;
    }

    public String getPostContent() {
        return postContent;
    }

    public BlogPost postContent(String postContent) {
        this.postContent = postContent;
        return this;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public BlogPost comments(Set<Comment> comments) {
        this.comments = comments;
        return this;
    }

    public BlogPost addComment(Comment comment) {
        this.comments.add(comment);
        comment.setPost(this);
        return this;
    }

    public BlogPost removeComment(Comment comment) {
        this.comments.remove(comment);
        comment.setPost(null);
        return this;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Category getCategory() {
        return category;
    }

    public BlogPost category(Category category) {
        this.category = category;
        return this;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public BlogPost user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Project getProject() {
        return project;
    }

    public BlogPost project(Project project) {
        this.project = project;
        return this;
    }

    public void setProject(Project project) {
        this.project = project;
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
        BlogPost blogPost = (BlogPost) o;
        if (blogPost.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), blogPost.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BlogPost{" +
            "id=" + getId() +
            ", postTitle='" + getPostTitle() + "'" +
            ", postStatus='" + getPostStatus() + "'" +
            ", postCreatedDate='" + getPostCreatedDate() + "'" +
            ", postPublishDate='" + getPostPublishDate() + "'" +
            ", postUpdateDate='" + getPostUpdateDate() + "'" +
            ", postSeenCount=" + getPostSeenCount() +
            ", postContent='" + getPostContent() + "'" +
            "}";
    }
}
