package com.tcutma.realstate.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.tcutma.realstate.domain.enumeration.PropertyType;

/**
 * BuildingType - Loai hinh du an entity
 */
@ApiModel(description = "BuildingType - Loai hinh du an entity")
@Entity
@Table(name = "building_type")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class BuildingType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "type_name", nullable = false)
    private String typeName;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_select")
    private PropertyType typeSelect;

    @ManyToMany(mappedBy = "buildingtypes")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Project> projects = new HashSet<>();

    @ManyToMany(mappedBy = "buildingtypes")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Property> properties = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public BuildingType typeName(String typeName) {
        this.typeName = typeName;
        return this;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public PropertyType getTypeSelect() {
        return typeSelect;
    }

    public BuildingType typeSelect(PropertyType typeSelect) {
        this.typeSelect = typeSelect;
        return this;
    }

    public void setTypeSelect(PropertyType typeSelect) {
        this.typeSelect = typeSelect;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public BuildingType projects(Set<Project> projects) {
        this.projects = projects;
        return this;
    }

    public BuildingType addProject(Project project) {
        this.projects.add(project);
        project.getBuildingtypes().add(this);
        return this;
    }

    public BuildingType removeProject(Project project) {
        this.projects.remove(project);
        project.getBuildingtypes().remove(this);
        return this;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public Set<Property> getProperties() {
        return properties;
    }

    public BuildingType properties(Set<Property> properties) {
        this.properties = properties;
        return this;
    }

    public BuildingType addProperty(Property property) {
        this.properties.add(property);
        property.getBuildingtypes().add(this);
        return this;
    }

    public BuildingType removeProperty(Property property) {
        this.properties.remove(property);
        property.getBuildingtypes().remove(this);
        return this;
    }

    public void setProperties(Set<Property> properties) {
        this.properties = properties;
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
        BuildingType buildingType = (BuildingType) o;
        if (buildingType.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), buildingType.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BuildingType{" +
            "id=" + getId() +
            ", typeName='" + getTypeName() + "'" +
            ", typeSelect='" + getTypeSelect() + "'" +
            "}";
    }
}
