package com.tcutma.realstate.domain;

import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import com.tcutma.realstate.domain.enumeration.ResourceType;

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
    @Size(max = 256)
    @Column(name = "photo_name", length = 256, nullable = false)
    private String photoName;

    @Column(name = "photo_date")
    private Instant photoDate;

    @Column(name = "photo_url")
    private String photoUrl;

    @Column(name = "photo_mime_type")
    private String photoMimeType;

    @Column(name = "resource_id")
    private Long resourceId;

    @Enumerated(EnumType.STRING)
    @Column(name = "resource_type")
    private ResourceType resourceType;

    @Column(name = "photo_size")
    private Integer photoSize;

    @Column(name = "photo_alt_text")
    private String photoAltText;

    @Column(name = "photo_thumbnail_url")
    private String photoThumbnailUrl;

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

    public Instant getPhotoDate() {
        return photoDate;
    }

    public Photo photoDate(Instant photoDate) {
        this.photoDate = photoDate;
        return this;
    }

    public void setPhotoDate(Instant photoDate) {
        this.photoDate = photoDate;
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

    public String getPhotoMimeType() {
        return photoMimeType;
    }

    public Photo photoMimeType(String photoMimeType) {
        this.photoMimeType = photoMimeType;
        return this;
    }

    public void setPhotoMimeType(String photoMimeType) {
        this.photoMimeType = photoMimeType;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public Photo resourceId(Long resourceId) {
        this.resourceId = resourceId;
        return this;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public Photo resourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
        return this;
    }

    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }

    public Integer getPhotoSize() {
        return photoSize;
    }

    public Photo photoSize(Integer photoSize) {
        this.photoSize = photoSize;
        return this;
    }

    public void setPhotoSize(Integer photoSize) {
        this.photoSize = photoSize;
    }

    public String getPhotoAltText() {
        return photoAltText;
    }

    public Photo photoAltText(String photoAltText) {
        this.photoAltText = photoAltText;
        return this;
    }

    public void setPhotoAltText(String photoAltText) {
        this.photoAltText = photoAltText;
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
            ", photoDate='" + getPhotoDate() + "'" +
            ", photoUrl='" + getPhotoUrl() + "'" +
            ", photoMimeType='" + getPhotoMimeType() + "'" +
            ", resourceId=" + getResourceId() +
            ", resourceType='" + getResourceType() + "'" +
            ", photoSize=" + getPhotoSize() +
            ", photoAltText='" + getPhotoAltText() + "'" +
            ", photoThumbnailUrl='" + getPhotoThumbnailUrl() + "'" +
            "}";
    }
}
