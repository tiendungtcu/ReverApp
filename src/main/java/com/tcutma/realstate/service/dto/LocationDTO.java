package com.tcutma.realstate.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Location entity.
 */
public class LocationDTO implements Serializable {

    private Long id;

    private String locationName;

    @Size(max = 256)
    private String locationFullAddress;

    private String locationNumber;

    private String locationRoad;

    private String locationWard;

    private String locationDistrict;

    private String locationProvince;

    private String locationGmapUrl;

    @NotNull
    private Double longitude;

    @NotNull
    private Double latitude;

    @Size(max = 16)
    private String locationZipCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationFullAddress() {
        return locationFullAddress;
    }

    public void setLocationFullAddress(String locationFullAddress) {
        this.locationFullAddress = locationFullAddress;
    }

    public String getLocationNumber() {
        return locationNumber;
    }

    public void setLocationNumber(String locationNumber) {
        this.locationNumber = locationNumber;
    }

    public String getLocationRoad() {
        return locationRoad;
    }

    public void setLocationRoad(String locationRoad) {
        this.locationRoad = locationRoad;
    }

    public String getLocationWard() {
        return locationWard;
    }

    public void setLocationWard(String locationWard) {
        this.locationWard = locationWard;
    }

    public String getLocationDistrict() {
        return locationDistrict;
    }

    public void setLocationDistrict(String locationDistrict) {
        this.locationDistrict = locationDistrict;
    }

    public String getLocationProvince() {
        return locationProvince;
    }

    public void setLocationProvince(String locationProvince) {
        this.locationProvince = locationProvince;
    }

    public String getLocationGmapUrl() {
        return locationGmapUrl;
    }

    public void setLocationGmapUrl(String locationGmapUrl) {
        this.locationGmapUrl = locationGmapUrl;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getLocationZipCode() {
        return locationZipCode;
    }

    public void setLocationZipCode(String locationZipCode) {
        this.locationZipCode = locationZipCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LocationDTO locationDTO = (LocationDTO) o;
        if (locationDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), locationDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LocationDTO{" +
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
