package com.tcutma.realstate.domain;

import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

import com.tcutma.realstate.domain.enumeration.SupportType;

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
    @Size(max = 128)
    @Column(name = "category_name", length = 128, nullable = false)
    private String categoryName;

    @Column(name = "category_description")
    private String categoryDescription;

    @Enumerated(EnumType.STRING)
    @Column(name = "category_support_type")
    private SupportType categorySupportType;

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

    public SupportType getCategorySupportType() {
        return categorySupportType;
    }

    public SupportCategory categorySupportType(SupportType categorySupportType) {
        this.categorySupportType = categorySupportType;
        return this;
    }

    public void setCategorySupportType(SupportType categorySupportType) {
        this.categorySupportType = categorySupportType;
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
            ", categorySupportType='" + getCategorySupportType() + "'" +
            "}";
    }
}
