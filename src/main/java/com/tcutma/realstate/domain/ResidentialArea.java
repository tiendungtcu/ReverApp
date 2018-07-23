package com.tcutma.realstate.domain;

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
 * ResidentialArea entity
 */
@ApiModel(description = "ResidentialArea entity")
@Entity
@Table(name = "residential_area")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ResidentialArea implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "residential_name", nullable = false)
    private String residentialName;

    @NotNull
    @Column(name = "residential_alias", nullable = false)
    private String residentialAlias;

    @Lob
    @Column(name = "residential_description")
    private String residentialDescription;

    @Lob
    @Column(name = "residential_detail")
    private String residentialDetail;

    @Column(name = "residential_province")
    private String residentialProvince;

    @Column(name = "residential_district")
    private String residentialDistrict;

    @Lob
    @Column(name = "residential_boundary")
    private String residentialBoundary;

    @Column(name = "residential_avatar")
    private String residentialAvatar;

    @OneToOne
    @JoinColumn(unique = true)
    private Photo photo;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "residential_area_tag",
               joinColumns = @JoinColumn(name = "residential_areas_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "tags_id", referencedColumnName = "id"))
    private Set<Tag> tags = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResidentialName() {
        return residentialName;
    }

    public ResidentialArea residentialName(String residentialName) {
        this.residentialName = residentialName;
        return this;
    }

    public void setResidentialName(String residentialName) {
        this.residentialName = residentialName;
    }

    public String getResidentialAlias() {
        return residentialAlias;
    }

    public ResidentialArea residentialAlias(String residentialAlias) {
        this.residentialAlias = residentialAlias;
        return this;
    }

    public void setResidentialAlias(String residentialAlias) {
        this.residentialAlias = residentialAlias;
    }

    public String getResidentialDescription() {
        return residentialDescription;
    }

    public ResidentialArea residentialDescription(String residentialDescription) {
        this.residentialDescription = residentialDescription;
        return this;
    }

    public void setResidentialDescription(String residentialDescription) {
        this.residentialDescription = residentialDescription;
    }

    public String getResidentialDetail() {
        return residentialDetail;
    }

    public ResidentialArea residentialDetail(String residentialDetail) {
        this.residentialDetail = residentialDetail;
        return this;
    }

    public void setResidentialDetail(String residentialDetail) {
        this.residentialDetail = residentialDetail;
    }

    public String getResidentialProvince() {
        return residentialProvince;
    }

    public ResidentialArea residentialProvince(String residentialProvince) {
        this.residentialProvince = residentialProvince;
        return this;
    }

    public void setResidentialProvince(String residentialProvince) {
        this.residentialProvince = residentialProvince;
    }

    public String getResidentialDistrict() {
        return residentialDistrict;
    }

    public ResidentialArea residentialDistrict(String residentialDistrict) {
        this.residentialDistrict = residentialDistrict;
        return this;
    }

    public void setResidentialDistrict(String residentialDistrict) {
        this.residentialDistrict = residentialDistrict;
    }

    public String getResidentialBoundary() {
        return residentialBoundary;
    }

    public ResidentialArea residentialBoundary(String residentialBoundary) {
        this.residentialBoundary = residentialBoundary;
        return this;
    }

    public void setResidentialBoundary(String residentialBoundary) {
        this.residentialBoundary = residentialBoundary;
    }

    public String getResidentialAvatar() {
        return residentialAvatar;
    }

    public ResidentialArea residentialAvatar(String residentialAvatar) {
        this.residentialAvatar = residentialAvatar;
        return this;
    }

    public void setResidentialAvatar(String residentialAvatar) {
        this.residentialAvatar = residentialAvatar;
    }

    public Photo getPhoto() {
        return photo;
    }

    public ResidentialArea photo(Photo photo) {
        this.photo = photo;
        return this;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public ResidentialArea tags(Set<Tag> tags) {
        this.tags = tags;
        return this;
    }

    public ResidentialArea addTag(Tag tag) {
        this.tags.add(tag);
        tag.getResidentialAreas().add(this);
        return this;
    }

    public ResidentialArea removeTag(Tag tag) {
        this.tags.remove(tag);
        tag.getResidentialAreas().remove(this);
        return this;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
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
        ResidentialArea residentialArea = (ResidentialArea) o;
        if (residentialArea.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), residentialArea.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ResidentialArea{" +
            "id=" + getId() +
            ", residentialName='" + getResidentialName() + "'" +
            ", residentialAlias='" + getResidentialAlias() + "'" +
            ", residentialDescription='" + getResidentialDescription() + "'" +
            ", residentialDetail='" + getResidentialDetail() + "'" +
            ", residentialProvince='" + getResidentialProvince() + "'" +
            ", residentialDistrict='" + getResidentialDistrict() + "'" +
            ", residentialBoundary='" + getResidentialBoundary() + "'" +
            ", residentialAvatar='" + getResidentialAvatar() + "'" +
            "}";
    }
}
