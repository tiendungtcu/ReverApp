package com.tcutma.realstate.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import com.tcutma.realstate.domain.enumeration.DocumentType;

/**
 * Document - Tai lieu du an entity
 */
@ApiModel(description = "Document - Tai lieu du an entity")
@Entity
@Table(name = "document")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Document implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "document_name", nullable = false)
    private String documentName;

    @Column(name = "document_url")
    private String documentUrl;

    @Column(name = "document_date")
    private Instant documentDate;

    @Lob
    @Column(name = "document_content")
    private String documentContent;

    @Lob
    @Column(name = "document_photo")
    private byte[] documentPhoto;

    @Column(name = "document_photo_content_type")
    private String documentPhotoContentType;

    @Enumerated(EnumType.STRING)
    @Column(name = "document_type")
    private DocumentType documentType;

    @OneToOne
    @JoinColumn(unique = true)
    private Photo photo;

    @OneToOne(mappedBy = "document")
    @JsonIgnore
    private Project project;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumentName() {
        return documentName;
    }

    public Document documentName(String documentName) {
        this.documentName = documentName;
        return this;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getDocumentUrl() {
        return documentUrl;
    }

    public Document documentUrl(String documentUrl) {
        this.documentUrl = documentUrl;
        return this;
    }

    public void setDocumentUrl(String documentUrl) {
        this.documentUrl = documentUrl;
    }

    public Instant getDocumentDate() {
        return documentDate;
    }

    public Document documentDate(Instant documentDate) {
        this.documentDate = documentDate;
        return this;
    }

    public void setDocumentDate(Instant documentDate) {
        this.documentDate = documentDate;
    }

    public String getDocumentContent() {
        return documentContent;
    }

    public Document documentContent(String documentContent) {
        this.documentContent = documentContent;
        return this;
    }

    public void setDocumentContent(String documentContent) {
        this.documentContent = documentContent;
    }

    public byte[] getDocumentPhoto() {
        return documentPhoto;
    }

    public Document documentPhoto(byte[] documentPhoto) {
        this.documentPhoto = documentPhoto;
        return this;
    }

    public void setDocumentPhoto(byte[] documentPhoto) {
        this.documentPhoto = documentPhoto;
    }

    public String getDocumentPhotoContentType() {
        return documentPhotoContentType;
    }

    public Document documentPhotoContentType(String documentPhotoContentType) {
        this.documentPhotoContentType = documentPhotoContentType;
        return this;
    }

    public void setDocumentPhotoContentType(String documentPhotoContentType) {
        this.documentPhotoContentType = documentPhotoContentType;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public Document documentType(DocumentType documentType) {
        this.documentType = documentType;
        return this;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public Photo getPhoto() {
        return photo;
    }

    public Document photo(Photo photo) {
        this.photo = photo;
        return this;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public Project getProject() {
        return project;
    }

    public Document project(Project project) {
        this.project = project;
        return this;
    }

    public void setProject(Project project) {
        this.project = project;
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
        Document document = (Document) o;
        if (document.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), document.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Document{" +
            "id=" + getId() +
            ", documentName='" + getDocumentName() + "'" +
            ", documentUrl='" + getDocumentUrl() + "'" +
            ", documentDate='" + getDocumentDate() + "'" +
            ", documentContent='" + getDocumentContent() + "'" +
            ", documentPhoto='" + getDocumentPhoto() + "'" +
            ", documentPhotoContentType='" + getDocumentPhotoContentType() + "'" +
            ", documentType='" + getDocumentType() + "'" +
            "}";
    }
}
