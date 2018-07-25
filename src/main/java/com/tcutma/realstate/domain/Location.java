package com.tcutma.realstate.domain;

import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * Location - vi tri entity
 */
@ApiModel(description = "Location - vi tri entity")
@Entity
@Table(name = "location")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Location implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "location_name")
    private String locationName;

    @Size(max = 256)
    @Column(name = "location_full_address", length = 256)
    private String locationFullAddress;

    @Column(name = "location_number")
    private String locationNumber;

    @Column(name = "location_road")
    private String locationRoad;

    @Column(name = "location_ward")
    private String locationWard;

    @Column(name = "location_district")
    private String locationDistrict;

    @Column(name = "location_province")
    private String locationProvince;

    @Column(name = "location_gmap_url")
    private String locationGmapUrl;

    @NotNull
    @Column(name = "longitude", nullable = false)
    private Double longitude;

    @NotNull
    @Column(name = "latitude", nullable = false)
    private Double latitude;

    @Size(max = 16)
    @Column(name = "location_zip_code", length = 16)
    private String locationZipCode;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocationName() {
        return locationName;
    }

    public Location locationName(String locationName) {
        this.locationName = locationName;
        return this;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationFullAddress() {
        return locationFullAddress;
    }

    public Location locationFullAddress(String locationFullAddress) {
        this.locationFullAddress = locationFullAddress;
        return this;
    }

    public void setLocationFullAddress(String locationFullAddress) {
        this.locationFullAddress = locationFullAddress;
    }

    public String getLocationNumber() {
        return locationNumber;
    }

    public Location locationNumber(String locationNumber) {
        this.locationNumber = locationNumber;
        return this;
    }

    public void setLocationNumber(String locationNumber) {
        this.locationNumber = locationNumber;
    }

    public String getLocationRoad() {
        return locationRoad;
    }

    public Location locationRoad(String locationRoad) {
        this.locationRoad = locationRoad;
        return this;
    }

    public void setLocationRoad(String locationRoad) {
        this.locationRoad = locationRoad;
    }

    public String getLocationWard() {
        return locationWard;
    }

    public Location locationWard(String locationWard) {
        this.locationWard = locationWard;
        return this;
    }

    public void setLocationWard(String locationWard) {
        this.locationWard = locationWard;
    }

    public String getLocationDistrict() {
        return locationDistrict;
    }

    public Location locationDistrict(String locationDistrict) {
        this.locationDistrict = locationDistrict;
        return this;
    }

    public void setLocationDistrict(String locationDistrict) {
        this.locationDistrict = locationDistrict;
    }

    public String getLocationProvince() {
        return locationProvince;
    }

    public Location locationProvince(String locationProvince) {
        this.locationProvince = locationProvince;
        return this;
    }

    public void setLocationProvince(String locationProvince) {
        this.locationProvince = locationProvince;
    }

    public String getLocationGmapUrl() {
        return locationGmapUrl;
    }

    public Location locationGmapUrl(String locationGmapUrl) {
        this.locationGmapUrl = locationGmapUrl;
        return this;
    }

    public void setLocationGmapUrl(String locationGmapUrl) {
        this.locationGmapUrl = locationGmapUrl;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Location longitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Location latitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getLocationZipCode() {
        return locationZipCode;
    }

    public Location locationZipCode(String locationZipCode) {
        this.locationZipCode = locationZipCode;
        return this;
    }

    public void setLocationZipCode(String locationZipCode) {
        this.locationZipCode = locationZipCode;
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
        Location location = (Location) o;
        if (location.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), location.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Location{" +
            "id=" + getId() +
            ", locationName='" + getLocationName() + "'" +
            ", locationFullAddress='" + getLocationFullAddress() + "'" +
            ", locationNumber='" + getLocationNumber() + "'" +
            ", locationRoad='" + getLocationRoad() + "'" +
            ", locationWard='" + getLocationWard() + "'" +
            ", locationDistrict='" + getLocationDistrict() + "'" +
            ", locationProvince='" + getLocationProvince() + "'" +
            ", locationGmapUrl='" + getLocationGmapUrl() + "'" +
            ", longitude=" + getLongitude() +
            ", latitude=" + getLatitude() +
            ", locationZipCode='" + getLocationZipCode() + "'" +
            "}";
    }
}
