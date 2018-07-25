package com.tcutma.realstate.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Contact entity.
 */
public class ContactDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 128)
    private String contactName;

    @NotNull
    @Size(max = 16)
    private String contactPhone;

    private String contactAddress;

    private String contactWebsite;

    private String contactAvatarUrl;

    private String contactFacebook;

    private String contactTwitter;

    private String contactInstagram;

    private String contactLinkedin;

    private String contactGooglePlus;

    private String contactYoutube;

    private Boolean contactStatus;

    private Long userId;

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

    public String getContactWebsite() {
        return contactWebsite;
    }

    public void setContactWebsite(String contactWebsite) {
        this.contactWebsite = contactWebsite;
    }

    public String getContactAvatarUrl() {
        return contactAvatarUrl;
    }

    public void setContactAvatarUrl(String contactAvatarUrl) {
        this.contactAvatarUrl = contactAvatarUrl;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
            ", contactWebsite='" + getContactWebsite() + "'" +
            ", contactAvatarUrl='" + getContactAvatarUrl() + "'" +
            ", contactFacebook='" + getContactFacebook() + "'" +
            ", contactTwitter='" + getContactTwitter() + "'" +
            ", contactInstagram='" + getContactInstagram() + "'" +
            ", contactLinkedin='" + getContactLinkedin() + "'" +
            ", contactGooglePlus='" + getContactGooglePlus() + "'" +
            ", contactYoutube='" + getContactYoutube() + "'" +
            ", contactStatus='" + isContactStatus() + "'" +
            ", user=" + getUserId() +
            "}";
    }
}
