package com.tcutma.realstate.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the Photo entity.
 */
public class PhotoDTO implements Serializable {

    private Long id;

    @NotNull
    private String photoName;

    @Lob
    private byte[] photoImage;
    private String photoImageContentType;

    private String photoExtension;

    private String photoUrl;

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

    public byte[] getPhotoImage() {
        return photoImage;
    }

    public void setPhotoImage(byte[] photoImage) {
        this.photoImage = photoImage;
    }

    public String getPhotoImageContentType() {
        return photoImageContentType;
    }

    public void setPhotoImageContentType(String photoImageContentType) {
        this.photoImageContentType = photoImageContentType;
    }

    public String getPhotoExtension() {
        return photoExtension;
    }

    public void setPhotoExtension(String photoExtension) {
        this.photoExtension = photoExtension;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
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
            ", photoImage='" + getPhotoImage() + "'" +
            ", photoExtension='" + getPhotoExtension() + "'" +
            ", photoUrl='" + getPhotoUrl() + "'" +
            ", photoThumbnailUrl='" + getPhotoThumbnailUrl() + "'" +
            "}";
    }
}
