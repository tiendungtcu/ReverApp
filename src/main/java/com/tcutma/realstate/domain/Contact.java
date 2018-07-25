package com.tcutma.realstate.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * Contact - chi tiet lien he entity
 */
@ApiModel(description = "Contact - chi tiet lien he entity")
@Entity
@Table(name = "contact")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Contact implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 128)
    @Column(name = "contact_name", length = 128, nullable = false)
    private String contactName;

    @NotNull
    @Size(max = 16)
    @Column(name = "contact_phone", length = 16, nullable = false)
    private String contactPhone;

    @Column(name = "contact_address")
    private String contactAddress;

    @Column(name = "contact_website")
    private String contactWebsite;

    @Column(name = "contact_avatar_url")
    private String contactAvatarUrl;

    @Column(name = "contact_facebook")
    private String contactFacebook;

    @Column(name = "contact_twitter")
    private String contactTwitter;

    @Column(name = "contact_instagram")
    private String contactInstagram;

    @Column(name = "contact_linkedin")
    private String contactLinkedin;

    @Column(name = "contact_google_plus")
    private String contactGooglePlus;

    @Column(name = "contact_youtube")
    private String contactYoutube;

    @Column(name = "contact_status")
    private Boolean contactStatus;

    @ManyToOne
    @JsonIgnoreProperties("")
    private User user;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContactName() {
        return contactName;
    }

    public Contact contactName(String contactName) {
        this.contactName = contactName;
        return this;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public Contact contactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
        return this;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public Contact contactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
        return this;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    public String getContactWebsite() {
        return contactWebsite;
    }

    public Contact contactWebsite(String contactWebsite) {
        this.contactWebsite = contactWebsite;
        return this;
    }

    public void setContactWebsite(String contactWebsite) {
        this.contactWebsite = contactWebsite;
    }

    public String getContactAvatarUrl() {
        return contactAvatarUrl;
    }

    public Contact contactAvatarUrl(String contactAvatarUrl) {
        this.contactAvatarUrl = contactAvatarUrl;
        return this;
    }

    public void setContactAvatarUrl(String contactAvatarUrl) {
        this.contactAvatarUrl = contactAvatarUrl;
    }

    public String getContactFacebook() {
        return contactFacebook;
    }

    public Contact contactFacebook(String contactFacebook) {
        this.contactFacebook = contactFacebook;
        return this;
    }

    public void setContactFacebook(String contactFacebook) {
        this.contactFacebook = contactFacebook;
    }

    public String getContactTwitter() {
        return contactTwitter;
    }

    public Contact contactTwitter(String contactTwitter) {
        this.contactTwitter = contactTwitter;
        return this;
    }

    public void setContactTwitter(String contactTwitter) {
        this.contactTwitter = contactTwitter;
    }

    public String getContactInstagram() {
        return contactInstagram;
    }

    public Contact contactInstagram(String contactInstagram) {
        this.contactInstagram = contactInstagram;
        return this;
    }

    public void setContactInstagram(String contactInstagram) {
        this.contactInstagram = contactInstagram;
    }

    public String getContactLinkedin() {
        return contactLinkedin;
    }

    public Contact contactLinkedin(String contactLinkedin) {
        this.contactLinkedin = contactLinkedin;
        return this;
    }

    public void setContactLinkedin(String contactLinkedin) {
        this.contactLinkedin = contactLinkedin;
    }

    public String getContactGooglePlus() {
        return contactGooglePlus;
    }

    public Contact contactGooglePlus(String contactGooglePlus) {
        this.contactGooglePlus = contactGooglePlus;
        return this;
    }

    public void setContactGooglePlus(String contactGooglePlus) {
        this.contactGooglePlus = contactGooglePlus;
    }

    public String getContactYoutube() {
        return contactYoutube;
    }

    public Contact contactYoutube(String contactYoutube) {
        this.contactYoutube = contactYoutube;
        return this;
    }

    public void setContactYoutube(String contactYoutube) {
        this.contactYoutube = contactYoutube;
    }

    public Boolean isContactStatus() {
        return contactStatus;
    }

    public Contact contactStatus(Boolean contactStatus) {
        this.contactStatus = contactStatus;
        return this;
    }

    public void setContactStatus(Boolean contactStatus) {
        this.contactStatus = contactStatus;
    }

    public User getUser() {
        return user;
    }

    public Contact user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
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
        Contact contact = (Contact) o;
        if (contact.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), contact.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Contact{" +
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
            "}";
    }
}
