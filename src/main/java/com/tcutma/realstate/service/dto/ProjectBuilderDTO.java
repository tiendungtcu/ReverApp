package com.tcutma.realstate.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the ProjectBuilder entity.
 */
public class ProjectBuilderDTO implements Serializable {

    private Long id;

    @NotNull
    private String builderName;

    private String builderTitle;

    private ZonedDateTime builderDate;

    private String builderDescription;

    private String builderAddress;

    private String builderWebsite;

    private String builderPhone;

    @Lob
    private byte[] builderPhoto;
    private String builderPhotoContentType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBuilderName() {
        return builderName;
    }

    public void setBuilderName(String builderName) {
        this.builderName = builderName;
    }

    public String getBuilderTitle() {
        return builderTitle;
    }

    public void setBuilderTitle(String builderTitle) {
        this.builderTitle = builderTitle;
    }

    public ZonedDateTime getBuilderDate() {
        return builderDate;
    }

    public void setBuilderDate(ZonedDateTime builderDate) {
        this.builderDate = builderDate;
    }

    public String getBuilderDescription() {
        return builderDescription;
    }

    public void setBuilderDescription(String builderDescription) {
        this.builderDescription = builderDescription;
    }

    public String getBuilderAddress() {
        return builderAddress;
    }

    public void setBuilderAddress(String builderAddress) {
        this.builderAddress = builderAddress;
    }

    public String getBuilderWebsite() {
        return builderWebsite;
    }

    public void setBuilderWebsite(String builderWebsite) {
        this.builderWebsite = builderWebsite;
    }

    public String getBuilderPhone() {
        return builderPhone;
    }

    public void setBuilderPhone(String builderPhone) {
        this.builderPhone = builderPhone;
    }

    public byte[] getBuilderPhoto() {
        return builderPhoto;
    }

    public void setBuilderPhoto(byte[] builderPhoto) {
        this.builderPhoto = builderPhoto;
    }

    public String getBuilderPhotoContentType() {
        return builderPhotoContentType;
    }

    public void setBuilderPhotoContentType(String builderPhotoContentType) {
        this.builderPhotoContentType = builderPhotoContentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProjectBuilderDTO projectBuilderDTO = (ProjectBuilderDTO) o;
        if (projectBuilderDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), projectBuilderDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProjectBuilderDTO{" +
            "id=" + getId() +
            ", builderName='" + getBuilderName() + "'" +
            ", builderTitle='" + getBuilderTitle() + "'" +
            ", builderDate='" + getBuilderDate() + "'" +
            ", builderDescription='" + getBuilderDescription() + "'" +
            ", builderAddress='" + getBuilderAddress() + "'" +
            ", builderWebsite='" + getBuilderWebsite() + "'" +
            ", builderPhone='" + getBuilderPhone() + "'" +
            ", builderPhoto='" + getBuilderPhoto() + "'" +
            "}";
    }
}
