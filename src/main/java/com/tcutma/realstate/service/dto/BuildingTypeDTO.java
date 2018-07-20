package com.tcutma.realstate.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.tcutma.realstate.domain.enumeration.PropertyType;

/**
 * A DTO for the BuildingType entity.
 */
public class BuildingTypeDTO implements Serializable {

    private Long id;

    @NotNull
    private String typeName;

    private PropertyType typeSelect;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public PropertyType getTypeSelect() {
        return typeSelect;
    }

    public void setTypeSelect(PropertyType typeSelect) {
        this.typeSelect = typeSelect;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BuildingTypeDTO buildingTypeDTO = (BuildingTypeDTO) o;
        if (buildingTypeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), buildingTypeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BuildingTypeDTO{" +
            "id=" + getId() +
            ", typeName='" + getTypeName() + "'" +
            ", typeSelect='" + getTypeSelect() + "'" +
            "}";
    }
}
