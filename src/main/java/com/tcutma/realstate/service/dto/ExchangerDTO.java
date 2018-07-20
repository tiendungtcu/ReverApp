package com.tcutma.realstate.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the Exchanger entity.
 */
public class ExchangerDTO implements Serializable {

    private Long id;

    @NotNull
    private String exchangerName;

    private String exchangerAddress;

    @NotNull
    private String exchangerPhone;

    @Lob
    private byte[] exchangerPhoto;
    private String exchangerPhotoContentType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExchangerName() {
        return exchangerName;
    }

    public void setExchangerName(String exchangerName) {
        this.exchangerName = exchangerName;
    }

    public String getExchangerAddress() {
        return exchangerAddress;
    }

    public void setExchangerAddress(String exchangerAddress) {
        this.exchangerAddress = exchangerAddress;
    }

    public String getExchangerPhone() {
        return exchangerPhone;
    }

    public void setExchangerPhone(String exchangerPhone) {
        this.exchangerPhone = exchangerPhone;
    }

    public byte[] getExchangerPhoto() {
        return exchangerPhoto;
    }

    public void setExchangerPhoto(byte[] exchangerPhoto) {
        this.exchangerPhoto = exchangerPhoto;
    }

    public String getExchangerPhotoContentType() {
        return exchangerPhotoContentType;
    }

    public void setExchangerPhotoContentType(String exchangerPhotoContentType) {
        this.exchangerPhotoContentType = exchangerPhotoContentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ExchangerDTO exchangerDTO = (ExchangerDTO) o;
        if (exchangerDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), exchangerDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ExchangerDTO{" +
            "id=" + getId() +
            ", exchangerName='" + getExchangerName() + "'" +
            ", exchangerAddress='" + getExchangerAddress() + "'" +
            ", exchangerPhone='" + getExchangerPhone() + "'" +
            ", exchangerPhoto='" + getExchangerPhoto() + "'" +
            "}";
    }
}
