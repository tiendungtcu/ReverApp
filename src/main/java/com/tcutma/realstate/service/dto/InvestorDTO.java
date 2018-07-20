package com.tcutma.realstate.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the Investor entity.
 */
public class InvestorDTO implements Serializable {

    private Long id;

    @NotNull
    private String investorName;

    private String investorTitle;

    private ZonedDateTime investorDate;

    private String investorDescription;

    private String investorAddress;

    private String investorWebsite;

    private String investorPhone;

    @Lob
    private byte[] investorPhoto;
    private String investorPhotoContentType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInvestorName() {
        return investorName;
    }

    public void setInvestorName(String investorName) {
        this.investorName = investorName;
    }

    public String getInvestorTitle() {
        return investorTitle;
    }

    public void setInvestorTitle(String investorTitle) {
        this.investorTitle = investorTitle;
    }

    public ZonedDateTime getInvestorDate() {
        return investorDate;
    }

    public void setInvestorDate(ZonedDateTime investorDate) {
        this.investorDate = investorDate;
    }

    public String getInvestorDescription() {
        return investorDescription;
    }

    public void setInvestorDescription(String investorDescription) {
        this.investorDescription = investorDescription;
    }

    public String getInvestorAddress() {
        return investorAddress;
    }

    public void setInvestorAddress(String investorAddress) {
        this.investorAddress = investorAddress;
    }

    public String getInvestorWebsite() {
        return investorWebsite;
    }

    public void setInvestorWebsite(String investorWebsite) {
        this.investorWebsite = investorWebsite;
    }

    public String getInvestorPhone() {
        return investorPhone;
    }

    public void setInvestorPhone(String investorPhone) {
        this.investorPhone = investorPhone;
    }

    public byte[] getInvestorPhoto() {
        return investorPhoto;
    }

    public void setInvestorPhoto(byte[] investorPhoto) {
        this.investorPhoto = investorPhoto;
    }

    public String getInvestorPhotoContentType() {
        return investorPhotoContentType;
    }

    public void setInvestorPhotoContentType(String investorPhotoContentType) {
        this.investorPhotoContentType = investorPhotoContentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        InvestorDTO investorDTO = (InvestorDTO) o;
        if (investorDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), investorDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "InvestorDTO{" +
            "id=" + getId() +
            ", investorName='" + getInvestorName() + "'" +
            ", investorTitle='" + getInvestorTitle() + "'" +
            ", investorDate='" + getInvestorDate() + "'" +
            ", investorDescription='" + getInvestorDescription() + "'" +
            ", investorAddress='" + getInvestorAddress() + "'" +
            ", investorWebsite='" + getInvestorWebsite() + "'" +
            ", investorPhone='" + getInvestorPhone() + "'" +
            ", investorPhoto='" + getInvestorPhoto() + "'" +
            "}";
    }
}
