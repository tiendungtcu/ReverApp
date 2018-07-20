package com.tcutma.realstate.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * Photo - Hinh anh entity
 */
@ApiModel(description = "Photo - Hinh anh entity")
@Entity
@Table(name = "photo")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Photo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "photo_name", nullable = false)
    private String photoName;

    @Lob
    @Column(name = "photo_image")
    private byte[] photoImage;

    @Column(name = "photo_image_content_type")
    private String photoImageContentType;

    @Column(name = "photo_extension")
    private String photoExtension;

    @Column(name = "photo_url")
    private String photoUrl;

    @Column(name = "photo_thumbnail_url")
    private String photoThumbnailUrl;

    @ManyToMany(mappedBy = "photos")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Property> properties = new HashSet<>();

    @ManyToMany(mappedBy = "photos")
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

    public String getPhotoName() {
        return photoName;
    }

    public Photo photoName(String photoName) {
        this.photoName = photoName;
        return this;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public byte[] getPhotoImage() {
        return photoImage;
    }

    public Photo photoImage(byte[] photoImage) {
        this.photoImage = photoImage;
        return this;
    }

    public void setPhotoImage(byte[] photoImage) {
        this.photoImage = photoImage;
    }

    public String getPhotoImageContentType() {
        return photoImageContentType;
    }

    public Photo photoImageContentType(String photoImageContentType) {
        this.photoImageContentType = photoImageContentType;
        return this;
    }

    public void setPhotoImageContentType(String photoImageContentType) {
        this.photoImageContentType = photoImageContentType;
    }

    public String getPhotoExtension() {
        return photoExtension;
    }

    public Photo photoExtension(String photoExtension) {
        this.photoExtension = photoExtension;
        return this;
    }

    public void setPhotoExtension(String photoExtension) {
        this.photoExtension = photoExtension;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public Photo photoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
        return this;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getPhotoThumbnailUrl() {
        return photoThumbnailUrl;
    }

    public Photo photoThumbnailUrl(String photoThumbnailUrl) {
        this.photoThumbnailUrl = photoThumbnailUrl;
        return this;
    }

    public void setPhotoThumbnailUrl(String photoThumbnailUrl) {
        this.photoThumbnailUrl = photoThumbnailUrl;
    }

    public Set<Property> getProperties() {
        return properties;
    }

    public Photo properties(Set<Property> properties) {
        this.properties = properties;
        return this;
    }

    public Photo addProperty(Property property) {
        this.properties.add(property);
        property.getPhotos().add(this);
        return this;
    }

    public Photo removeProperty(Property property) {
        this.properties.remove(property);
        property.getPhotos().remove(this);
        return this;
    }

    public void setProperties(Set<Property> properties) {
        this.properties = properties;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public Photo projects(Set<Project> projects) {
        this.projects = projects;
        return this;
    }

    public Photo addProject(Project project) {
        this.projects.add(project);
        project.getPhotos().add(this);
        return this;
    }

    public Photo removeProject(Project project) {
        this.projects.remove(project);
        project.getPhotos().remove(this);
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
        Photo photo = (Photo) o;
        if (photo.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), photo.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Photo{" +
            "id=" + getId() +
            ", photoName='" + getPhotoName() + "'" +
            ", photoImage='" + getPhotoImage() + "'" +
            ", photoImageContentType='" + getPhotoImageContentType() + "'" +
            ", photoExtension='" + getPhotoExtension() + "'" +
            ", photoUrl='" + getPhotoUrl() + "'" +
            ", photoThumbnailUrl='" + getPhotoThumbnailUrl() + "'" +
            "}";
    }
}
