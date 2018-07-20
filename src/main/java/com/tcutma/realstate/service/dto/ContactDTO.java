package com.tcutma.realstate.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the Contact entity.
 */
public class ContactDTO implements Serializable {

    private Long id;

    @NotNull
    private String contactName;

    @NotNull
    private String contactPhone;

    private String contactAddress;

    @NotNull
    private String contactEmail;

    private String contactWebsite;

    @Lob
    private byte[] contactPhoto;
    private String contactPhotoContentType;

    private String contactFacebook;

    private String contactTwitter;

    private String contactInstagram;

    private String contactLinkedin;

    private String contactGooglePlus;

    private String contactYoutube;

    private Boolean contactStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactWebsite() {
        return contactWebsite;
    }

    public void setContactWebsite(String contactWebsite) {
        this.contactWebsite = contactWebsite;
    }

    public byte[] getContactPhoto() {
        return contactPhoto;
    }

    public void setContactPhoto(byte[] contactPhoto) {
        this.contactPhoto = contactPhoto;
    }

    public String getContactPhotoContentType() {
        return contactPhotoContentType;
    }

    public void setContactPhotoContentType(String contactPhotoContentType) {
        this.contactPhotoContentType = contactPhotoContentType;
    }

    public String getContactFacebook() {
        return contactFacebook;
    }

    public void setContactFacebook(String contactFacebook) {
        this.contactFacebook = contactFacebook;
    }

    public String getContactTwitter() {
        return contactTwitter;
    }

    public void setContactTwitter(String contactTwitter) {
        this.contactTwitter = contactTwitter;
    }

    public String getContactInstagram() {
        return contactInstagram;
    }

    public void setContactInstagram(String contactInstagram) {
        this.contactInstagram = contactInstagram;
    }

    public String getContactLinkedin() {
        return contactLinkedin;
    }

    public void setContactLinkedin(String contactLinkedin) {
        this.contactLinkedin = contactLinkedin;
    }

    public String getContactGooglePlus() {
        return contactGooglePlus;
    }

    public void setContactGooglePlus(String contactGooglePlus) {
        this.contactGooglePlus = contactGooglePlus;
    }

    public String getContactYoutube() {
        return contactYoutube;
    }

    public void setContactYoutube(String contactYoutube) {
        this.contactYoutube = contactYoutube;
    }

    public Boolean isContactStatus() {
        return contactStatus;
    }

    public void setContactStatus(Boolean contactStatus) {
        this.contactStatus = contactStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ContactDTO contactDTO = (ContactDTO) o;
        if (contactDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), contactDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ContactDTO{" +
            "id=" + getId() +
            ", contactName='" + getContactName() + "'" +
            ", contactPhone='" + getContactPhone() + "'" +
            ", contactAddress='" + getContactAddress() + "'" +
            ", contactEmail='" + getContactEmail() + "'" +
            ", contactWebsite='" + getContactWebsite() + "'" +
            ", contactPhoto='" + getContactPhoto() + "'" +
            ", contactFacebook='" + getContactFacebook() + "'" +
            ", contactTwitter='" + getContactTwitter() + "'" +
            ", contactInstagram='" + getContactInstagram() + "'" +
            ", contactLinkedin='" + getContactLinkedin() + "'" +
            ", contactGooglePlus='" + getContactGooglePlus() + "'" +
            ", contactYoutube='" + getContactYoutube() + "'" +
            ", contactStatus='" + isContactStatus() + "'" +
            "}";
    }
}
