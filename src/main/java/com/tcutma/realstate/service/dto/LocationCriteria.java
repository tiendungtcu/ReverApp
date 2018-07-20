package com.tcutma.realstate.service.dto;

import java.io.Serializable;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;






/**
 * Criteria class for the Location entity. This class is used in LocationResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /locations?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class LocationCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private StringFilter locationName;

    private StringFilter locationFullAddress;

    private StringFilter locationNumber;

    private StringFilter locationRoad;

    private StringFilter locationWard;

    private StringFilter locationDistrict;

    private StringFilter locationProvince;

    private StringFilter locationCountry;

    private StringFilter locationGmapUrl;

    private DoubleFilter longitude;

    private DoubleFilter latitude;

    private BooleanFilter locationHide;

    public LocationCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getLocationName() {
        return locationName;
    }

    public void setLocationName(StringFilter locationName) {
        this.locationName = locationName;
    }

    public StringFilter getLocationFullAddress() {
        return locationFullAddress;
    }

    public void setLocationFullAddress(StringFilter locationFullAddress) {
        this.locationFullAddress = locationFullAddress;
    }

    public StringFilter getLocationNumber() {
        return locationNumber;
    }

    public void setLocationNumber(StringFilter locationNumber) {
        this.locationNumber = locationNumber;
    }

    public StringFilter getLocationRoad() {
        return locationRoad;
    }

    public void setLocationRoad(StringFilter locationRoad) {
        this.locationRoad = locationRoad;
    }

    public StringFilter getLocationWard() {
        return locationWard;
    }

    public void setLocationWard(StringFilter locationWard) {
        this.locationWard = locationWard;
    }

    public StringFilter getLocationDistrict() {
        return locationDistrict;
    }

    public void setLocationDistrict(StringFilter locationDistrict) {
        this.locationDistrict = locationDistrict;
    }

    public StringFilter getLocationProvince() {
        return locationProvince;
    }

    public void setLocationProvince(StringFilter locationProvince) {
        this.locationProvince = locationProvince;
    }

    public StringFilter getLocationCountry() {
        return locationCountry;
    }

    public void setLocationCountry(StringFilter locationCountry) {
        this.locationCountry = locationCountry;
    }

    public StringFilter getLocationGmapUrl() {
        return locationGmapUrl;
    }

    public void setLocationGmapUrl(StringFilter locationGmapUrl) {
        this.locationGmapUrl = locationGmapUrl;
    }

    public DoubleFilter getLongitude() {
        return longitude;
    }

    public void setLongitude(DoubleFilter longitude) {
        this.longitude = longitude;
    }

    public DoubleFilter getLatitude() {
        return latitude;
    }

    public void setLatitude(DoubleFilter latitude) {
        this.latitude = latitude;
    }

    public BooleanFilter getLocationHide() {
        return locationHide;
    }

    public void setLocationHide(BooleanFilter locationHide) {
        this.locationHide = locationHide;
    }

    @Override
    public String toString() {
        return "LocationCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (locationName != null ? "locationName=" + locationName + ", " : "") +
                (locationFullAddress != null ? "locationFullAddress=" + locationFullAddress + ", " : "") +
                (locationNumber != null ? "locationNumber=" + locationNumber + ", " : "") +
                (locationRoad != null ? "locationRoad=" + locationRoad + ", " : "") +
                (locationWard != null ? "locationWard=" + locationWard + ", " : "") +
                (locationDistrict != null ? "locationDistrict=" + locationDistrict + ", " : "") +
                (locationProvince != null ? "locationProvince=" + locationProvince + ", " : "") +
                (locationCountry != null ? "locationCountry=" + locationCountry + ", " : "") +
                (locationGmapUrl != null ? "locationGmapUrl=" + locationGmapUrl + ", " : "") +
                (longitude != null ? "longitude=" + longitude + ", " : "") +
                (latitude != null ? "latitude=" + latitude + ", " : "") +
                (locationHide != null ? "locationHide=" + locationHide + ", " : "") +
            "}";
    }

}
