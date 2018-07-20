package com.tcutma.realstate.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * Investor - chu dau tu entity
 */
@ApiModel(description = "Investor - chu dau tu entity")
@Entity
@Table(name = "investor")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Investor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "investor_name", nullable = false)
    private String investorName;

    @Column(name = "investor_title")
    private String investorTitle;

    @Column(name = "investor_date")
    private ZonedDateTime investorDate;

    @Column(name = "investor_description")
    private String investorDescription;

    @Column(name = "investor_address")
    private String investorAddress;

    @Column(name = "investor_website")
    private String investorWebsite;

    @Column(name = "investor_phone")
    private String investorPhone;

    @Lob
    @Column(name = "investor_photo")
    private byte[] investorPhoto;

    @Column(name = "investor_photo_content_type")
    private String investorPhotoContentType;

    @ManyToMany(mappedBy = "investors")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Project> projects = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInvestorName() {
        return investorName;
    }

    public Investor investorName(String investorName) {
        this.investorName = investorName;
        return this;
    }

    public void setInvestorName(String investorName) {
        this.investorName = investorName;
    }

    public String getInvestorTitle() {
        return investorTitle;
    }

    public Investor investorTitle(String investorTitle) {
        this.investorTitle = investorTitle;
        return this;
    }

    public void setInvestorTitle(String investorTitle) {
        this.investorTitle = investorTitle;
    }

    public ZonedDateTime getInvestorDate() {
        return investorDate;
    }

    public Investor investorDate(ZonedDateTime investorDate) {
        this.investorDate = investorDate;
        return this;
    }

    public void setInvestorDate(ZonedDateTime investorDate) {
        this.investorDate = investorDate;
    }

    public String getInvestorDescription() {
        return investorDescription;
    }

    public Investor investorDescription(String investorDescription) {
        this.investorDescription = investorDescription;
        return this;
    }

    public void setInvestorDescription(String investorDescription) {
        this.investorDescription = investorDescription;
    }

    public String getInvestorAddress() {
        return investorAddress;
    }

    public Investor investorAddress(String investorAddress) {
        this.investorAddress = investorAddress;
        return this;
    }

    public void setInvestorAddress(String investorAddress) {
        this.investorAddress = investorAddress;
    }

    public String getInvestorWebsite() {
        return investorWebsite;
    }

    public Investor investorWebsite(String investorWebsite) {
        this.investorWebsite = investorWebsite;
        return this;
    }

    public void setInvestorWebsite(String investorWebsite) {
        this.investorWebsite = investorWebsite;
    }

    public String getInvestorPhone() {
        return investorPhone;
    }

    public Investor investorPhone(String investorPhone) {
        this.investorPhone = investorPhone;
        return this;
    }

    public void setInvestorPhone(String investorPhone) {
        this.investorPhone = investorPhone;
    }

    public byte[] getInvestorPhoto() {
        return investorPhoto;
    }

    public Investor investorPhoto(byte[] investorPhoto) {
        this.investorPhoto = investorPhoto;
        return this;
    }

    public void setInvestorPhoto(byte[] investorPhoto) {
        this.investorPhoto = investorPhoto;
    }

    public String getInvestorPhotoContentType() {
        return investorPhotoContentType;
    }

    public Investor investorPhotoContentType(String investorPhotoContentType) {
        this.investorPhotoContentType = investorPhotoContentType;
        return this;
    }

    public void setInvestorPhotoContentType(String investorPhotoContentType) {
        this.investorPhotoContentType = investorPhotoContentType;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public Investor projects(Set<Project> projects) {
        this.projects = projects;
        return this;
    }

    public Investor addProject(Project project) {
        this.projects.add(project);
        project.getInvestors().add(this);
        return this;
    }

    public Investor removeProject(Project project) {
        this.projects.remove(project);
        project.getInvestors().remove(this);
        return this;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
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
        Investor investor = (Investor) o;
        if (investor.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), investor.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Investor{" +
            "id=" + getId() +
            ", investorName='" + getInvestorName() + "'" +
            ", investorTitle='" + getInvestorTitle() + "'" +
            ", investorDate='" + getInvestorDate() + "'" +
            ", investorDescription='" + getInvestorDescription() + "'" +
            ", investorAddress='" + getInvestorAddress() + "'" +
            ", investorWebsite='" + getInvestorWebsite() + "'" +
            ", investorPhone='" + getInvestorPhone() + "'" +
            ", investorPhoto='" + getInvestorPhoto() + "'" +
            ", investorPhotoContentType='" + getInvestorPhotoContentType() + "'" +
            "}";
    }
}
