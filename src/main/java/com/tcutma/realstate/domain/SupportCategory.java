package com.tcutma.realstate.domain;

import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * SupportCategory entity
 */
@ApiModel(description = "SupportCategory entity")
@Entity
@Table(name = "support_category")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SupportCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "category_name", nullable = false)
    private String categoryName;

    @Column(name = "category_description")
    private String categoryDescription;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public SupportCategory categoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public SupportCategory categoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
        return this;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
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
        SupportCategory supportCategory = (SupportCategory) o;
        if (supportCategory.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), supportCategory.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SupportCategory{" +
            "id=" + getId() +
            ", categoryName='" + getCategoryName() + "'" +
            ", categoryDescription='" + getCategoryDescription() + "'" +
            "}";
    }
}
