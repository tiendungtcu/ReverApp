package com.tcutma.realstate.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Exchanger entity.
 */
public class ExchangerDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 128)
    private String exchangerName;

    @Size(max = 256)
    private String exchangerAddress;

    @NotNull
    @Size(max = 16)
    private String exchangerPhone;

    private String exchangerAvatarUrl;

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

    public String getExchangerAvatarUrl() {
        return exchangerAvatarUrl;
    }

    public void setExchangerAvatarUrl(String exchangerAvatarUrl) {
        this.exchangerAvatarUrl = exchangerAvatarUrl;
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
            ", exchangerAvatarUrl='" + getExchangerAvatarUrl() + "'" +
            "}";
    }
}
