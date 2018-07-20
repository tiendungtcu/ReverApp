package com.tcutma.realstate.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the ResidentialArea entity.
 */
public class ResidentialAreaDTO implements Serializable {

    private Long id;

    @NotNull
    private String residentialName;

    @NotNull
    private String residentialAlias;

    @Lob
    private String residentialDescription;

    @Lob
    private String residentialDetail;

    private String residentialProvince;

    private String residentialDistrict;

    @Lob
    private String residentialBoundary;

    @Lob
    private byte[] residentialImage;
    private String residentialImageContentType;

    private Long photoId;

    private Set<TagDTO> tags = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResidentialName() {
        return residentialName;
    }

    public void setResidentialName(String residentialName) {
        this.residentialName = residentialName;
    }

    public String getResidentialAlias() {
        return residentialAlias;
    }

    public void setResidentialAlias(String residentialAlias) {
        this.residentialAlias = residentialAlias;
    }

    public String getResidentialDescription() {
        return residentialDescription;
    }

    public void setResidentialDescription(String residentialDescription) {
        this.residentialDescription = residentialDescription;
    }

    public String getResidentialDetail() {
        return residentialDetail;
    }

    public void setResidentialDetail(String residentialDetail) {
        this.residentialDetail = residentialDetail;
    }

    public String getResidentialProvince() {
        return residentialProvince;
    }

    public void setResidentialProvince(String residentialProvince) {
        this.residentialProvince = residentialProvince;
    }

    public String getResidentialDistrict() {
        return residentialDistrict;
    }

    public void setResidentialDistrict(String residentialDistrict) {
        this.residentialDistrict = residentialDistrict;
    }

    public String getResidentialBoundary() {
        return residentialBoundary;
    }

    public void setResidentialBoundary(String residentialBoundary) {
        this.residentialBoundary = residentialBoundary;
    }

    public byte[] getResidentialImage() {
        return residentialImage;
    }

    public void setResidentialImage(byte[] residentialImage) {
        this.residentialImage = residentialImage;
    }

    public String getResidentialImageContentType() {
        return residentialImageContentType;
    }

    public void setResidentialImageContentType(String residentialImageContentType) {
        this.residentialImageContentType = residentialImageContentType;
    }

    public Long getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Long photoId) {
        this.photoId = photoId;
    }

    public Set<TagDTO> getTags() {
        return tags;
    }

    public void setTags(Set<TagDTO> tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ResidentialAreaDTO residentialAreaDTO = (ResidentialAreaDTO) o;
        if (residentialAreaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), residentialAreaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ResidentialAreaDTO{" +
            "id=" + getId() +
            ", residentialName='" + getResidentialName() + "'" +
            ", residentialAlias='" + getResidentialAlias() + "'" +
            ", residentialDescription='" + getResidentialDescription() + "'" +
            ", residentialDetail='" + getResidentialDetail() + "'" +
            ", residentialProvince='" + getResidentialProvince() + "'" +
            ", residentialDistrict='" + getResidentialDistrict() + "'" +
            ", residentialBoundary='" + getResidentialBoundary() + "'" +
            ", residentialImage='" + getResidentialImage() + "'" +
            ", photo=" + getPhotoId() +
            "}";
    }
}
