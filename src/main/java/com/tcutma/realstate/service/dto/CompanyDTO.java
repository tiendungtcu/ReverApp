package com.tcutma.realstate.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the Company entity.
 */
public class CompanyDTO implements Serializable {

    private Long id;

    @NotNull
    private String companyName;

    private String companyPhone;

    private String companyAddress;

    @Lob
    private byte[] companyLogo;
    private String companyLogoContentType;

    private String companyWebsite;

    private String companyFacebook;

    private String companyTwitter;

    private String companyInstagram;

    private String companyLinkedin;

    private String companyGooglePlus;

    private String companyYoutube;

    @Lob
    private String companyDescription;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public byte[] getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(byte[] companyLogo) {
        this.companyLogo = companyLogo;
    }

    public String getCompanyLogoContentType() {
        return companyLogoContentType;
    }

    public void setCompanyLogoContentType(String companyLogoContentType) {
        this.companyLogoContentType = companyLogoContentType;
    }

    public String getCompanyWebsite() {
        return companyWebsite;
    }

    public void setCompanyWebsite(String companyWebsite) {
        this.companyWebsite = companyWebsite;
    }

    public String getCompanyFacebook() {
        return companyFacebook;
    }

    public void setCompanyFacebook(String companyFacebook) {
        this.companyFacebook = companyFacebook;
    }

    public String getCompanyTwitter() {
        return companyTwitter;
    }

    public void setCompanyTwitter(String companyTwitter) {
        this.companyTwitter = companyTwitter;
    }

    public String getCompanyInstagram() {
        return companyInstagram;
    }

    public void setCompanyInstagram(String companyInstagram) {
        this.companyInstagram = companyInstagram;
    }

    public String getCompanyLinkedin() {
        return companyLinkedin;
    }

    public void setCompanyLinkedin(String companyLinkedin) {
        this.companyLinkedin = companyLinkedin;
    }

    public String getCompanyGooglePlus() {
        return companyGooglePlus;
    }

    public void setCompanyGooglePlus(String companyGooglePlus) {
        this.companyGooglePlus = companyGooglePlus;
    }

    public String getCompanyYoutube() {
        return companyYoutube;
    }

    public void setCompanyYoutube(String companyYoutube) {
        this.companyYoutube = companyYoutube;
    }

    public String getCompanyDescription() {
        return companyDescription;
    }

    public void setCompanyDescription(String companyDescription) {
        this.companyDescription = companyDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CompanyDTO companyDTO = (CompanyDTO) o;
        if (companyDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), companyDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CompanyDTO{" +
            "id=" + getId() +
            ", companyName='" + getCompanyName() + "'" +
            ", companyPhone='" + getCompanyPhone() + "'" +
            ", companyAddress='" + getCompanyAddress() + "'" +
            ", companyLogo='" + getCompanyLogo() + "'" +
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
