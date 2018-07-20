package com.tcutma.realstate.domain;

import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * Department - Phong ban entity
 */
@ApiModel(description = "Department - Phong ban entity")
@Entity
@Table(name = "department")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "department_name", nullable = false)
    private String departmentName;

    @Lob
    @Column(name = "department_photo")
    private byte[] departmentPhoto;

    @Column(name = "department_photo_content_type")
    private String departmentPhotoContentType;

    @Column(name = "department_phone")
    private String departmentPhone;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public Department departmentName(String departmentName) {
        this.departmentName = departmentName;
        return this;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public byte[] getDepartmentPhoto() {
        return departmentPhoto;
    }

    public Department departmentPhoto(byte[] departmentPhoto) {
        this.departmentPhoto = departmentPhoto;
        return this;
    }

    public void setDepartmentPhoto(byte[] departmentPhoto) {
        this.departmentPhoto = departmentPhoto;
    }

    public String getDepartmentPhotoContentType() {
        return departmentPhotoContentType;
    }

    public Department departmentPhotoContentType(String departmentPhotoContentType) {
        this.departmentPhotoContentType = departmentPhotoContentType;
        return this;
    }

    public void setDepartmentPhotoContentType(String departmentPhotoContentType) {
        this.departmentPhotoContentType = departmentPhotoContentType;
    }

    public String getDepartmentPhone() {
        return departmentPhone;
    }

    public Department departmentPhone(String departmentPhone) {
        this.departmentPhone = departmentPhone;
        return this;
    }

    public void setDepartmentPhone(String departmentPhone) {
        this.departmentPhone = departmentPhone;
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
        Department department = (Department) o;
        if (department.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), department.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Department{" +
            "id=" + getId() +
            ", departmentName='" + getDepartmentName() + "'" +
            ", departmentPhoto='" + getDepartmentPhoto() + "'" +
            ", departmentPhotoContentType='" + getDepartmentPhotoContentType() + "'" +
            ", departmentPhone='" + getDepartmentPhone() + "'" +
            "}";
    }
}
