package com.tcutma.realstate.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;
import com.tcutma.realstate.domain.enumeration.DocumentType;

/**
 * A DTO for the Document entity.
 */
public class DocumentDTO implements Serializable {

    private Long id;

    @NotNull
    private String documentName;

    private String documentUrl;

    private Instant documentDate;

    @Lob
    private String documentContent;

    @Lob
    private byte[] documentPhoto;
    private String documentPhotoContentType;

    private DocumentType documentType;

    private Long photoId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getDocumentUrl() {
        return documentUrl;
    }

    public void setDocumentUrl(String documentUrl) {
        this.documentUrl = documentUrl;
    }

    public Instant getDocumentDate() {
        return documentDate;
    }

    public void setDocumentDate(Instant documentDate) {
        this.documentDate = documentDate;
    }

    public String getDocumentContent() {
        return documentContent;
    }

    public void setDocumentContent(String documentContent) {
        this.documentContent = documentContent;
    }

    public byte[] getDocumentPhoto() {
        return documentPhoto;
    }

    public void setDocumentPhoto(byte[] documentPhoto) {
        this.documentPhoto = documentPhoto;
    }

    public String getDocumentPhotoContentType() {
        return documentPhotoContentType;
    }

    public void setDocumentPhotoContentType(String documentPhotoContentType) {
        this.documentPhotoContentType = documentPhotoContentType;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public Long getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Long photoId) {
        this.photoId = photoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DocumentDTO documentDTO = (DocumentDTO) o;
        if (documentDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), documentDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DocumentDTO{" +
            "id=" + getId() +
            ", documentName='" + getDocumentName() + "'" +
            ", documentUrl='" + getDocumentUrl() + "'" +
            ", documentDate='" + getDocumentDate() + "'" +
            ", documentContent='" + getDocumentContent() + "'" +
            ", documentPhoto='" + getDocumentPhoto() + "'" +
            ", documentType='" + getDocumentType() + "'" +
            ", photo=" + getPhotoId() +
            "}";
    }
}
