package com.tcutma.realstate.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the Department entity.
 */
public class DepartmentDTO implements Serializable {

    private Long id;

    @NotNull
    private String departmentName;

    @Lob
    private byte[] departmentPhoto;
    private String departmentPhotoContentType;

    private String departmentPhone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public byte[] getDepartmentPhoto() {
        return departmentPhoto;
    }

    public void setDepartmentPhoto(byte[] departmentPhoto) {
        this.departmentPhoto = departmentPhoto;
    }

    public String getDepartmentPhotoContentType() {
        return departmentPhotoContentType;
    }

    public void setDepartmentPhotoContentType(String departmentPhotoContentType) {
        this.departmentPhotoContentType = departmentPhotoContentType;
    }

    public String getDepartmentPhone() {
        return departmentPhone;
    }

    public void setDepartmentPhone(String departmentPhone) {
        this.departmentPhone = departmentPhone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DepartmentDTO departmentDTO = (DepartmentDTO) o;
        if (departmentDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), departmentDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DepartmentDTO{" +
            "id=" + getId() +
            ", departmentName='" + getDepartmentName() + "'" +
            ", departmentPhoto='" + getDepartmentPhoto() + "'" +
            ", departmentPhone='" + getDepartmentPhone() + "'" +
            "}";
    }
}
