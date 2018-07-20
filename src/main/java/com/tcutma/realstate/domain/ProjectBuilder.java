package com.tcutma.realstate.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * ProjectBuilder - nha thau entity
 */
@ApiModel(description = "ProjectBuilder - nha thau entity")
@Entity
@Table(name = "project_builder")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ProjectBuilder implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "builder_name", nullable = false)
    private String builderName;

    @Column(name = "builder_title")
    private String builderTitle;

    @Column(name = "builder_date")
    private ZonedDateTime builderDate;

    @Column(name = "builder_description")
    private String builderDescription;

    @Column(name = "builder_address")
    private String builderAddress;

    @Column(name = "builder_website")
    private String builderWebsite;

    @Column(name = "builder_phone")
    private String builderPhone;

    @Lob
    @Column(name = "builder_photo")
    private byte[] builderPhoto;

    @Column(name = "builder_photo_content_type")
    private String builderPhotoContentType;

    @ManyToMany(mappedBy = "projectbuilders")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Project> projects = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBuilderName() {
        return builderName;
    }

    public ProjectBuilder builderName(String builderName) {
        this.builderName = builderName;
        return this;
    }

    public void setBuilderName(String builderName) {
        this.builderName = builderName;
    }

    public String getBuilderTitle() {
        return builderTitle;
    }

    public ProjectBuilder builderTitle(String builderTitle) {
        this.builderTitle = builderTitle;
        return this;
    }

    public void setBuilderTitle(String builderTitle) {
        this.builderTitle = builderTitle;
    }

    public ZonedDateTime getBuilderDate() {
        return builderDate;
    }

    public ProjectBuilder builderDate(ZonedDateTime builderDate) {
        this.builderDate = builderDate;
        return this;
    }

    public void setBuilderDate(ZonedDateTime builderDate) {
        this.builderDate = builderDate;
    }

    public String getBuilderDescription() {
        return builderDescription;
    }

    public ProjectBuilder builderDescription(String builderDescription) {
        this.builderDescription = builderDescription;
        return this;
    }

    public void setBuilderDescription(String builderDescription) {
        this.builderDescription = builderDescription;
    }

    public String getBuilderAddress() {
        return builderAddress;
    }

    public ProjectBuilder builderAddress(String builderAddress) {
        this.builderAddress = builderAddress;
        return this;
    }

    public void setBuilderAddress(String builderAddress) {
        this.builderAddress = builderAddress;
    }

    public String getBuilderWebsite() {
        return builderWebsite;
    }

    public ProjectBuilder builderWebsite(String builderWebsite) {
        this.builderWebsite = builderWebsite;
        return this;
    }

    public void setBuilderWebsite(String builderWebsite) {
        this.builderWebsite = builderWebsite;
    }

    public String getBuilderPhone() {
        return builderPhone;
    }

    public ProjectBuilder builderPhone(String builderPhone) {
        this.builderPhone = builderPhone;
        return this;
    }

    public void setBuilderPhone(String builderPhone) {
        this.builderPhone = builderPhone;
    }

    public byte[] getBuilderPhoto() {
        return builderPhoto;
    }

    public ProjectBuilder builderPhoto(byte[] builderPhoto) {
        this.builderPhoto = builderPhoto;
        return this;
    }

    public void setBuilderPhoto(byte[] builderPhoto) {
        this.builderPhoto = builderPhoto;
    }

    public String getBuilderPhotoContentType() {
        return builderPhotoContentType;
    }

    public ProjectBuilder builderPhotoContentType(String builderPhotoContentType) {
        this.builderPhotoContentType = builderPhotoContentType;
        return this;
    }

    public void setBuilderPhotoContentType(String builderPhotoContentType) {
        this.builderPhotoContentType = builderPhotoContentType;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public ProjectBuilder projects(Set<Project> projects) {
        this.projects = projects;
        return this;
    }

    public ProjectBuilder addProject(Project project) {
        this.projects.add(project);
        project.getProjectbuilders().add(this);
        return this;
    }

    public ProjectBuilder removeProject(Project project) {
        this.projects.remove(project);
        project.getProjectbuilders().remove(this);
        return this;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
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
        ProjectBuilder projectBuilder = (ProjectBuilder) o;
        if (projectBuilder.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), projectBuilder.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProjectBuilder{" +
            "id=" + getId() +
            ", builderName='" + getBuilderName() + "'" +
            ", builderTitle='" + getBuilderTitle() + "'" +
            ", builderDate='" + getBuilderDate() + "'" +
            ", builderDescription='" + getBuilderDescription() + "'" +
            ", builderAddress='" + getBuilderAddress() + "'" +
            ", builderWebsite='" + getBuilderWebsite() + "'" +
            ", builderPhone='" + getBuilderPhone() + "'" +
            ", builderPhoto='" + getBuilderPhoto() + "'" +
            ", builderPhotoContentType='" + getBuilderPhotoContentType() + "'" +
            "}";
    }
}
