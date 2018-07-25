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
    @Size(max = 128)
    @Column(name = "department_name", length = 128, nullable = false)
    private String departmentName;

    @Column(name = "department_avatar_url")
    private String departmentAvatarUrl;

    @Size(max = 16)
    @Column(name = "department_phone", length = 16)
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

    public String getDepartmentAvatarUrl() {
        return departmentAvatarUrl;
    }

    public Department departmentAvatarUrl(String departmentAvatarUrl) {
        this.departmentAvatarUrl = departmentAvatarUrl;
        return this;
    }

    public void setDepartmentAvatarUrl(String departmentAvatarUrl) {
        this.departmentAvatarUrl = departmentAvatarUrl;
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
            ", departmentAvatarUrl='" + getDepartmentAvatarUrl() + "'" +
            ", departmentPhone='" + getDepartmentPhone() + "'" +
            "}";
    }
}
