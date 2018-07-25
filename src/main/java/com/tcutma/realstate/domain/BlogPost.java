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
    @Size(max = 256)
    @Column(name = "post_title", length = 256, nullable = false)
    private String postTitle;

    @Enumerated(EnumType.STRING)
    @Column(name = "post_status")
    private BlogStatus postStatus;

    @Column(name = "post_created_date")
    private Instant postCreatedDate;

    @Column(name = "post_seen_count")
    private Long postSeenCount;

    @Lob
    @Column(name = "post_content")
    private String postContent;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Category category;

    @ManyToOne
    @JsonIgnoreProperties("")
    private User user;

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
            ", postSeenCount=" + getPostSeenCount() +
            ", postContent='" + getPostContent() + "'" +
            "}";
    }
}
