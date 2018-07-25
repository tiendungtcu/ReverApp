package com.tcutma.realstate.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.tcutma.realstate.domain.enumeration.SupportType;

/**
 * A DTO for the SupportCategory entity.
 */
public class SupportCategoryDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 128)
    private String categoryName;

    private String categoryDescription;

    private SupportType categorySupportType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public SupportType getCategorySupportType() {
        return categorySupportType;
    }

    public void setCategorySupportType(SupportType categorySupportType) {
        this.categorySupportType = categorySupportType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SupportCategoryDTO supportCategoryDTO = (SupportCategoryDTO) o;
        if (supportCategoryDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), supportCategoryDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SupportCategoryDTO{" +
            "id=" + getId() +
            ", categoryName='" + getCategoryName() + "'" +
            ", categoryDescription='" + getCategoryDescription() + "'" +
            ", categorySupportType='" + getCategorySupportType() + "'" +
            "}";
    }
}
