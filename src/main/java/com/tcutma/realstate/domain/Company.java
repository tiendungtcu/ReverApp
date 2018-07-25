package com.tcutma.realstate.domain;

import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * Company-thong tin cong ty entity
 */
@ApiModel(description = "Company-thong tin cong ty entity")
@Entity
@Table(name = "company")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Company implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 128)
    @Column(name = "company_name", length = 128, nullable = false)
    private String companyName;

    @Size(max = 16)
    @Column(name = "company_phone", length = 16)
    private String companyPhone;

    @Column(name = "company_address")
    private String companyAddress;

    @Lob
    @Column(name = "company_logo")
    private byte[] companyLogo;

    @Column(name = "company_logo_content_type")
    private String companyLogoContentType;

    @Column(name = "company_website")
    private String companyWebsite;

    @Column(name = "company_facebook")
    private String companyFacebook;

    @Column(name = "company_twitter")
    private String companyTwitter;

    @Column(name = "company_instagram")
    private String companyInstagram;

    @Column(name = "company_linkedin")
    private String companyLinkedin;

    @Column(name = "company_google_plus")
    private String companyGooglePlus;

    @Column(name = "company_youtube")
    private String companyYoutube;

    @Lob
    @Column(name = "company_description")
    private String companyDescription;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public Company companyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public Company companyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
        return this;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public Company companyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
        return this;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public byte[] getCompanyLogo() {
        return companyLogo;
    }

    public Company companyLogo(byte[] companyLogo) {
        this.companyLogo = companyLogo;
        return this;
    }

    public void setCompanyLogo(byte[] companyLogo) {
        this.companyLogo = companyLogo;
    }

    public String getCompanyLogoContentType() {
        return companyLogoContentType;
    }

    public Company companyLogoContentType(String companyLogoContentType) {
        this.companyLogoContentType = companyLogoContentType;
        return this;
    }

    public void setCompanyLogoContentType(String companyLogoContentType) {
        this.companyLogoContentType = companyLogoContentType;
    }

    public String getCompanyWebsite() {
        return companyWebsite;
    }

    public Company companyWebsite(String companyWebsite) {
        this.companyWebsite = companyWebsite;
        return this;
    }

    public void setCompanyWebsite(String companyWebsite) {
        this.companyWebsite = companyWebsite;
    }

    public String getCompanyFacebook() {
        return companyFacebook;
    }

    public Company companyFacebook(String companyFacebook) {
        this.companyFacebook = companyFacebook;
        return this;
    }

    public void setCompanyFacebook(String companyFacebook) {
        this.companyFacebook = companyFacebook;
    }

    public String getCompanyTwitter() {
        return companyTwitter;
    }

    public Company companyTwitter(String companyTwitter) {
        this.companyTwitter = companyTwitter;
        return this;
    }

    public void setCompanyTwitter(String companyTwitter) {
        this.companyTwitter = companyTwitter;
    }

    public String getCompanyInstagram() {
        return companyInstagram;
    }

    public Company companyInstagram(String companyInstagram) {
        this.companyInstagram = companyInstagram;
        return this;
    }

    public void setCompanyInstagram(String companyInstagram) {
        this.companyInstagram = companyInstagram;
    }

    public String getCompanyLinkedin() {
        return companyLinkedin;
    }

    public Company companyLinkedin(String companyLinkedin) {
        this.companyLinkedin = companyLinkedin;
        return this;
    }

    public void setCompanyLinkedin(String companyLinkedin) {
        this.companyLinkedin = companyLinkedin;
    }

    public String getCompanyGooglePlus() {
        return companyGooglePlus;
    }

    public Company companyGooglePlus(String companyGooglePlus) {
        this.companyGooglePlus = companyGooglePlus;
        return this;
    }

    public void setCompanyGooglePlus(String companyGooglePlus) {
        this.companyGooglePlus = companyGooglePlus;
    }

    public String getCompanyYoutube() {
        return companyYoutube;
    }

    public Company companyYoutube(String companyYoutube) {
        this.companyYoutube = companyYoutube;
        return this;
    }

    public void setCompanyYoutube(String companyYoutube) {
        this.companyYoutube = companyYoutube;
    }

    public String getCompanyDescription() {
        return companyDescription;
    }

    public Company companyDescription(String companyDescription) {
        this.companyDescription = companyDescription;
        return this;
    }

    public void setCompanyDescription(String companyDescription) {
        this.companyDescription = companyDescription;
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
        Company company = (Company) o;
        if (company.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), company.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Company{" +
            "id=" + getId() +
            ", companyName='" + getCompanyName() + "'" +
            ", companyPhone='" + getCompanyPhone() + "'" +
            ", companyAddress='" + getCompanyAddress() + "'" +
            ", companyLogo='" + getCompanyLogo() + "'" +
            ", companyLogoContentType='" + getCompanyLogoContentType() + "'" +
            ", companyWebsite='" + getCompanyWebsite() + "'" +
            ", companyFacebook='" + getCompanyFacebook() + "'" +
            ", companyTwitter='" + getCompanyTwitter() + "'" +
            ", companyInstagram='" + getCompanyInstagram() + "'" +
            ", companyLinkedin='" + getCompanyLinkedin() + "'" +
            ", companyGooglePlus='" + getCompanyGooglePlus() + "'" +
            ", companyYoutube='" + getCompanyYoutube() + "'" +
            ", companyDescription='" + getCompanyDescription() + "'" +
            "}";
    }
}
