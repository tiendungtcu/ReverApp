package com.tcutma.realstate.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A ProjectUser.
 */
@Entity
@Table(name = "project_user")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ProjectUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "liked")
    private Boolean liked;

    @Column(name = "rev_shared")
    private Boolean shared;

    @Column(name = "liked_date")
    private Instant likedDate;

    @Column(name = "shared_date")
    private Instant sharedDate;

    @ManyToOne
    @JsonIgnoreProperties("")
    private User user;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Project project;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isLiked() {
        return liked;
    }

    public ProjectUser liked(Boolean liked) {
        this.liked = liked;
        return this;
    }

    public void setLiked(Boolean liked) {
        this.liked = liked;
    }

    public Boolean isShared() {
        return shared;
    }

    public ProjectUser shared(Boolean shared) {
        this.shared = shared;
        return this;
    }

    public void setShared(Boolean shared) {
        this.shared = shared;
    }

    public Instant getLikedDate() {
        return likedDate;
    }

    public ProjectUser likedDate(Instant likedDate) {
        this.likedDate = likedDate;
        return this;
    }

    public void setLikedDate(Instant likedDate) {
        this.likedDate = likedDate;
    }

    public Instant getSharedDate() {
        return sharedDate;
    }

    public ProjectUser sharedDate(Instant sharedDate) {
        this.sharedDate = sharedDate;
        return this;
    }

    public void setSharedDate(Instant sharedDate) {
        this.sharedDate = sharedDate;
    }

    public User getUser() {
        return user;
    }

    public ProjectUser user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Project getProject() {
        return project;
    }

    public ProjectUser project(Project project) {
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
        ProjectUser projectUser = (ProjectUser) o;
        if (projectUser.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), projectUser.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProjectUser{" +
            "id=" + getId() +
            ", liked='" + isLiked() + "'" +
            ", shared='" + isShared() + "'" +
            ", likedDate='" + getLikedDate() + "'" +
            ", sharedDate='" + getSharedDate() + "'" +
            "}";
    }
}
