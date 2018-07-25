package com.tcutma.realstate.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.tcutma.realstate.domain.enumeration.ResourceType;

/**
 * A DTO for the Photo entity.
 */
public class PhotoDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 256)
    private String photoName;

    private Instant photoDate;

    private String photoUrl;

    private String photoMimeType;

    private Long resourceId;

    private ResourceType resourceType;

    private Integer photoSize;

    private String photoAltText;

    private String photoThumbnailUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public Instant getPhotoDate() {
        return photoDate;
    }

    public void setPhotoDate(Instant photoDate) {
        this.photoDate = photoDate;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getPhotoMimeType() {
        return photoMimeType;
    }

    public void setPhotoMimeType(String photoMimeType) {
        this.photoMimeType = photoMimeType;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }

    public Integer getPhotoSize() {
        return photoSize;
    }

    public void setPhotoSize(Integer photoSize) {
        this.photoSize = photoSize;
    }

    public String getPhotoAltText() {
        return photoAltText;
    }

    public void setPhotoAltText(String photoAltText) {
        this.photoAltText = photoAltText;
    }

    public String getPhotoThumbnailUrl() {
        return photoThumbnailUrl;
    }

    public void setPhotoThumbnailUrl(String photoThumbnailUrl) {
        this.photoThumbnailUrl = photoThumbnailUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PhotoDTO photoDTO = (PhotoDTO) o;
        if (photoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), photoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PhotoDTO{" +
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
