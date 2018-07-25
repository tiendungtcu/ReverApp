package com.tcutma.realstate.domain;

import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import com.tcutma.realstate.domain.enumeration.ResourceType;

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
    @Size(max = 256)
    @Column(name = "document_name", length = 256, nullable = false)
    private String documentName;

    @NotNull
    @Column(name = "document_url", nullable = false)
    private String documentUrl;

    @Column(name = "document_date")
    private Instant documentDate;

    @Column(name = "document_mime_type")
    private String documentMimeType;

    @Column(name = "document_size")
    private String documentSize;

    @Enumerated(EnumType.STRING)
    @Column(name = "resource_type")
    private ResourceType resourceType;

    @Column(name = "resource_id")
    private Long resourceId;

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

    public String getDocumentMimeType() {
        return documentMimeType;
    }

    public Document documentMimeType(String documentMimeType) {
        this.documentMimeType = documentMimeType;
        return this;
    }

    public void setDocumentMimeType(String documentMimeType) {
        this.documentMimeType = documentMimeType;
    }

    public String getDocumentSize() {
        return documentSize;
    }

    public Document documentSize(String documentSize) {
        this.documentSize = documentSize;
        return this;
    }

    public void setDocumentSize(String documentSize) {
        this.documentSize = documentSize;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public Document resourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
        return this;
    }

    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public Document resourceId(Long resourceId) {
        this.resourceId = resourceId;
        return this;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
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
            ", documentMimeType='" + getDocumentMimeType() + "'" +
            ", documentSize='" + getDocumentSize() + "'" +
            ", resourceType='" + getResourceType() + "'" +
            ", resourceId=" + getResourceId() +
            "}";
    }
}
