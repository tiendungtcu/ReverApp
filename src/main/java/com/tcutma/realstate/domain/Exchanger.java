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
    @Size(max = 128)
    @Column(name = "exchanger_name", length = 128, nullable = false)
    private String exchangerName;

    @Size(max = 256)
    @Column(name = "exchanger_address", length = 256)
    private String exchangerAddress;

    @NotNull
    @Size(max = 16)
    @Column(name = "exchanger_phone", length = 16, nullable = false)
    private String exchangerPhone;

    @Column(name = "exchanger_avatar_url")
    private String exchangerAvatarUrl;

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

    public String getExchangerAvatarUrl() {
        return exchangerAvatarUrl;
    }

    public Exchanger exchangerAvatarUrl(String exchangerAvatarUrl) {
        this.exchangerAvatarUrl = exchangerAvatarUrl;
        return this;
    }

    public void setExchangerAvatarUrl(String exchangerAvatarUrl) {
        this.exchangerAvatarUrl = exchangerAvatarUrl;
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
            ", exchangerAvatarUrl='" + getExchangerAvatarUrl() + "'" +
            "}";
    }
}
