package com.tcutma.realstate.domain;

import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * JobTitle - chuc vu entity
 */
@ApiModel(description = "JobTitle - chuc vu entity")
@Entity
@Table(name = "job_title")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class JobTitle implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 128)
    @Column(name = "title_name", length = 128, nullable = false)
    private String titleName;

    @DecimalMin(value = "1")
    @Column(name = "salary_factor")
    private Double salaryFactor;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitleName() {
        return titleName;
    }

    public JobTitle titleName(String titleName) {
        this.titleName = titleName;
        return this;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public Double getSalaryFactor() {
        return salaryFactor;
    }

    public JobTitle salaryFactor(Double salaryFactor) {
        this.salaryFactor = salaryFactor;
        return this;
    }

    public void setSalaryFactor(Double salaryFactor) {
        this.salaryFactor = salaryFactor;
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
        JobTitle jobTitle = (JobTitle) o;
        if (jobTitle.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), jobTitle.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "JobTitle{" +
            "id=" + getId() +
            ", titleName='" + getTitleName() + "'" +
            ", salaryFactor=" + getSalaryFactor() +
            "}";
    }
}
