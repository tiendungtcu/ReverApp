package com.tcutma.realstate.domain;

import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
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
    @Size(max = 128)
    @Column(name = "investor_name", length = 128, nullable = false)
    private String investorName;

    @Size(max = 256)
    @Column(name = "investor_title", length = 256)
    private String investorTitle;

    @Column(name = "investor_date")
    private LocalDate investorDate;

    @Size(max = 256)
    @Column(name = "investor_description", length = 256)
    private String investorDescription;

    @Size(max = 256)
    @Column(name = "investor_address", length = 256)
    private String investorAddress;

    @Column(name = "investor_website")
    private String investorWebsite;

    @Size(max = 16)
    @Column(name = "investor_phone", length = 16)
    private String investorPhone;

    @Column(name = "investor_avatar_url")
    private String investorAvatarUrl;

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

    public LocalDate getInvestorDate() {
        return investorDate;
    }

    public Investor investorDate(LocalDate investorDate) {
        this.investorDate = investorDate;
        return this;
    }

    public void setInvestorDate(LocalDate investorDate) {
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

    public String getInvestorAvatarUrl() {
        return investorAvatarUrl;
    }

    public Investor investorAvatarUrl(String investorAvatarUrl) {
        this.investorAvatarUrl = investorAvatarUrl;
        return this;
    }

    public void setInvestorAvatarUrl(String investorAvatarUrl) {
        this.investorAvatarUrl = investorAvatarUrl;
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
            ", investorAvatarUrl='" + getInvestorAvatarUrl() + "'" +
            "}";
    }
}
