package com.tcutma.realstate.domain;

import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * Exchanger - san giao dich entity
 */
@ApiModel(description = "Exchanger - san giao dich entity")
@Entity
@Table(name = "exchanger")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Exchanger implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "exchanger_name", nullable = false)
    private String exchangerName;

    @Column(name = "exchanger_address")
    private String exchangerAddress;

    @NotNull
    @Column(name = "exchanger_phone", nullable = false)
    private String exchangerPhone;

    @Lob
    @Column(name = "exchanger_photo")
    private byte[] exchangerPhoto;

    @Column(name = "exchanger_photo_content_type")
    private String exchangerPhotoContentType;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExchangerName() {
        return exchangerName;
    }

    public Exchanger exchangerName(String exchangerName) {
        this.exchangerName = exchangerName;
        return this;
    }

    public void setExchangerName(String exchangerName) {
        this.exchangerName = exchangerName;
    }

    public String getExchangerAddress() {
        return exchangerAddress;
    }

    public Exchanger exchangerAddress(String exchangerAddress) {
        this.exchangerAddress = exchangerAddress;
        return this;
    }

    public void setExchangerAddress(String exchangerAddress) {
        this.exchangerAddress = exchangerAddress;
    }

    public String getExchangerPhone() {
        return exchangerPhone;
    }

    public Exchanger exchangerPhone(String exchangerPhone) {
        this.exchangerPhone = exchangerPhone;
        return this;
    }

    public void setExchangerPhone(String exchangerPhone) {
        this.exchangerPhone = exchangerPhone;
    }

    public byte[] getExchangerPhoto() {
        return exchangerPhoto;
    }

    public Exchanger exchangerPhoto(byte[] exchangerPhoto) {
        this.exchangerPhoto = exchangerPhoto;
        return this;
    }

    public void setExchangerPhoto(byte[] exchangerPhoto) {
        this.exchangerPhoto = exchangerPhoto;
    }

    public String getExchangerPhotoContentType() {
        return exchangerPhotoContentType;
    }

    public Exchanger exchangerPhotoContentType(String exchangerPhotoContentType) {
        this.exchangerPhotoContentType = exchangerPhotoContentType;
        return this;
    }

    public void setExchangerPhotoContentType(String exchangerPhotoContentType) {
        this.exchangerPhotoContentType = exchangerPhotoContentType;
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
        Exchanger exchanger = (Exchanger) o;
        if (exchanger.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), exchanger.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Exchanger{" +
            "id=" + getId() +
            ", exchangerName='" + getExchangerName() + "'" +
            ", exchangerAddress='" + getExchangerAddress() + "'" +
            ", exchangerPhone='" + getExchangerPhone() + "'" +
            ", exchangerPhoto='" + getExchangerPhoto() + "'" +
            ", exchangerPhotoContentType='" + getExchangerPhotoContentType() + "'" +
            "}";
    }
}
