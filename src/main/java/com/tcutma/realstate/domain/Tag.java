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

/**
 * Tag - the entity
 */
@ApiModel(description = "Tag - the entity")
@Entity
@Table(name = "tag")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Tag implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "tag_name", nullable = false)
    private String tagName;

    @ManyToMany(mappedBy = "tags")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Property> properties = new HashSet<>();

    @ManyToMany(mappedBy = "tags")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Project> projects = new HashSet<>();

    @ManyToMany(mappedBy = "tags")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ResidentialArea> residentialAreas = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public Tag tagName(String tagName) {
        this.tagName = tagName;
        return this;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Set<Property> getProperties() {
        return properties;
    }

    public Tag properties(Set<Property> properties) {
        this.properties = properties;
        return this;
    }

    public Tag addProperty(Property property) {
        this.properties.add(property);
        property.getTags().add(this);
        return this;
    }

    public Tag removeProperty(Property property) {
        this.properties.remove(property);
        property.getTags().remove(this);
        return this;
    }

    public void setProperties(Set<Property> properties) {
        this.properties = properties;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public Tag projects(Set<Project> projects) {
        this.projects = projects;
        return this;
    }

    public Tag addProject(Project project) {
        this.projects.add(project);
        project.getTags().add(this);
        return this;
    }

    public Tag removeProject(Project project) {
        this.projects.remove(project);
        project.getTags().remove(this);
        return this;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public Set<ResidentialArea> getResidentialAreas() {
        return residentialAreas;
    }

    public Tag residentialAreas(Set<ResidentialArea> residentialAreas) {
        this.residentialAreas = residentialAreas;
        return this;
    }

    public Tag addResidentialArea(ResidentialArea residentialArea) {
        this.residentialAreas.add(residentialArea);
        residentialArea.getTags().add(this);
        return this;
    }

    public Tag removeResidentialArea(ResidentialArea residentialArea) {
        this.residentialAreas.remove(residentialArea);
        residentialArea.getTags().remove(this);
        return this;
    }

    public void setResidentialAreas(Set<ResidentialArea> residentialAreas) {
        this.residentialAreas = residentialAreas;
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
        Tag tag = (Tag) o;
        if (tag.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tag.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Tag{" +
            "id=" + getId() +
            ", tagName='" + getTagName() + "'" +
            "}";
    }
}
