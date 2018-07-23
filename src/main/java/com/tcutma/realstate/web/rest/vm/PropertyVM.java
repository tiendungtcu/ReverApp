package com.tcutma.realstate.web.rest.vm;

import com.tcutma.realstate.domain.enumeration.PriceUnit;
import com.tcutma.realstate.domain.enumeration.TransactionType;
import com.tcutma.realstate.domain.enumeration.UseEstablishment;
import com.tcutma.realstate.service.dto.BuildingTypeDTO;
import com.tcutma.realstate.service.dto.PhotoDTO;
import com.tcutma.realstate.service.dto.TagDTO;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class PropertyVM implements Serializable {


    private Long id;

    @NotNull
    private String propertyCode;

    @NotNull
    private String propertyName;

    private String propertyAlias;

    private TransactionType propertyTransaction;

    private String propertyNumber;

    private String propertyRoad;

    private String propertyWard;

    private String propertyDistrict;

    private String propertyProvince;

    @Lob
    private String propertyDescription;

    private Integer propertyBedRooms;

    private Integer propertyBathRooms;

    private Double propertySquare;

    private UseEstablishment propertyUsePurpose;

    private String propertyOwnerType;

    private String propertyTower;

    private Double propertyRentPrice;

    private PriceUnit propertyRentUnit;

    private ZonedDateTime propertyRentStartedDate;

    private Double propertySellPrice;

    private PriceUnit propertySellUnit;

    private ZonedDateTime propertySellStartedDate;


    private String propertySpecialFeature;

    private String propertyFurnitureOverview;

    private String propertyLocationOverview;

    private String propertyResidentialCommunity;

    private String propertyEducationalAspect;

    private String propertyExtraInfo;

    @Lob
    private byte[] propertyDraft;
    private String propertyDraftContentType;

    private Double longitude;

    private Double latitude;

    private Boolean propertyGoodPrice;

    private Long propertySeenCount;

    private Boolean propertyIsSold;

    private Boolean propertyIsRent;

    private Boolean propertyAvailable;

    private Long locationId;

    private Long residentialAreaId;

    private String residentialName;

    private Set<TagDTO> tags = new HashSet<>();

    private Set<BuildingTypeDTO> buildingtypes = new HashSet<>();

    private Set<PhotoDTO> photos = new HashSet<>();

    private FacilityVM facility;

    private FurnitureVM furniture;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPropertyCode() {
        return propertyCode;
    }

    public void setPropertyCode(String propertyCode) {
        this.propertyCode = propertyCode;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyAlias() {
        return propertyAlias;
    }

    public void setPropertyAlias(String propertyAlias) {
        this.propertyAlias = propertyAlias;
    }

    public TransactionType getPropertyTransaction() {
        return propertyTransaction;
    }

    public void setPropertyTransaction(TransactionType propertyTransaction) {
        this.propertyTransaction = propertyTransaction;
    }

    public String getPropertyNumber() {
        return propertyNumber;
    }

    public void setPropertyNumber(String propertyNumber) {
        this.propertyNumber = propertyNumber;
    }

    public String getPropertyRoad() {
        return propertyRoad;
    }

    public void setPropertyRoad(String propertyRoad) {
        this.propertyRoad = propertyRoad;
    }

    public String getPropertyWard() {
        return propertyWard;
    }

    public void setPropertyWard(String propertyWard) {
        this.propertyWard = propertyWard;
    }

    public String getPropertyDistrict() {
        return propertyDistrict;
    }

    public void setPropertyDistrict(String propertyDistrict) {
        this.propertyDistrict = propertyDistrict;
    }

    public String getPropertyProvince() {
        return propertyProvince;
    }

    public void setPropertyProvince(String propertyProvince) {
        this.propertyProvince = propertyProvince;
    }

    public String getPropertyDescription() {
        return propertyDescription;
    }

    public void setPropertyDescription(String propertyDescription) {
        this.propertyDescription = propertyDescription;
    }

    public Integer getPropertyBedRooms() {
        return propertyBedRooms;
    }

    public void setPropertyBedRooms(Integer propertyBedRooms) {
        this.propertyBedRooms = propertyBedRooms;
    }

    public Integer getPropertyBathRooms() {
        return propertyBathRooms;
    }

    public void setPropertyBathRooms(Integer propertyBathRooms) {
        this.propertyBathRooms = propertyBathRooms;
    }

    public Double getPropertySquare() {
        return propertySquare;
    }

    public void setPropertySquare(Double propertySquare) {
        this.propertySquare = propertySquare;
    }

    public UseEstablishment getPropertyUsePurpose() {
        return propertyUsePurpose;
    }

    public void setPropertyUsePurpose(UseEstablishment propertyUsePurpose) {
        this.propertyUsePurpose = propertyUsePurpose;
    }

    public String getPropertyOwnerType() {
        return propertyOwnerType;
    }

    public void setPropertyOwnerType(String propertyOwnerType) {
        this.propertyOwnerType = propertyOwnerType;
    }

    public String getPropertyTower() {
        return propertyTower;
    }

    public void setPropertyTower(String propertyTower) {
        this.propertyTower = propertyTower;
    }

    public Double getPropertyRentPrice() {
        return propertyRentPrice;
    }

    public void setPropertyRentPrice(Double propertyRentPrice) {
        this.propertyRentPrice = propertyRentPrice;
    }

    public PriceUnit getPropertyRentUnit() {
        return propertyRentUnit;
    }

    public void setPropertyRentUnit(PriceUnit propertyRentUnit) {
        this.propertyRentUnit = propertyRentUnit;
    }

    public ZonedDateTime getPropertyRentStartedDate() {
        return propertyRentStartedDate;
    }

    public void setPropertyRentStartedDate(ZonedDateTime propertyRentStartedDate) {
        this.propertyRentStartedDate = propertyRentStartedDate;
    }

    public Double getPropertySellPrice() {
        return propertySellPrice;
    }

    public void setPropertySellPrice(Double propertySellPrice) {
        this.propertySellPrice = propertySellPrice;
    }

    public PriceUnit getPropertySellUnit() {
        return propertySellUnit;
    }

    public void setPropertySellUnit(PriceUnit propertySellUnit) {
        this.propertySellUnit = propertySellUnit;
    }

    public ZonedDateTime getPropertySellStartedDate() {
        return propertySellStartedDate;
    }

    public void setPropertySellStartedDate(ZonedDateTime propertySellStartedDate) {
        this.propertySellStartedDate = propertySellStartedDate;
    }

    public String getPropertySpecialFeature() {
        return propertySpecialFeature;
    }

    public void setPropertySpecialFeature(String propertySpecialFeature) {
        this.propertySpecialFeature = propertySpecialFeature;
    }

    public String getPropertyFurnitureOverview() {
        return propertyFurnitureOverview;
    }

    public void setPropertyFurnitureOverview(String propertyFurnitureOverview) {
        this.propertyFurnitureOverview = propertyFurnitureOverview;
    }

    public String getPropertyLocationOverview() {
        return propertyLocationOverview;
    }

    public void setPropertyLocationOverview(String propertyLocationOverview) {
        this.propertyLocationOverview = propertyLocationOverview;
    }

    public String getPropertyResidentialCommunity() {
        return propertyResidentialCommunity;
    }

    public void setPropertyResidentialCommunity(String propertyResidentialCommunity) {
        this.propertyResidentialCommunity = propertyResidentialCommunity;
    }

    public String getPropertyEducationalAspect() {
        return propertyEducationalAspect;
    }

    public void setPropertyEducationalAspect(String propertyEducationalAspect) {
        this.propertyEducationalAspect = propertyEducationalAspect;
    }

    public String getPropertyExtraInfo() {
        return propertyExtraInfo;
    }

    public void setPropertyExtraInfo(String propertyExtraInfo) {
        this.propertyExtraInfo = propertyExtraInfo;
    }

    public byte[] getPropertyDraft() {
        return propertyDraft;
    }

    public void setPropertyDraft(byte[] propertyDraft) {
        this.propertyDraft = propertyDraft;
    }

    public String getPropertyDraftContentType() {
        return propertyDraftContentType;
    }

    public void setPropertyDraftContentType(String propertyDraftContentType) {
        this.propertyDraftContentType = propertyDraftContentType;
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

    public Boolean getPropertyGoodPrice() {
        return propertyGoodPrice;
    }

    public void setPropertyGoodPrice(Boolean propertyGoodPrice) {
        this.propertyGoodPrice = propertyGoodPrice;
    }

    public Long getPropertySeenCount() {
        return propertySeenCount;
    }

    public void setPropertySeenCount(Long propertySeenCount) {
        this.propertySeenCount = propertySeenCount;
    }

    public Boolean getPropertyIsSold() {
        return propertyIsSold;
    }

    public void setPropertyIsSold(Boolean propertyIsSold) {
        this.propertyIsSold = propertyIsSold;
    }

    public Boolean getPropertyIsRent() {
        return propertyIsRent;
    }

    public void setPropertyIsRent(Boolean propertyIsRent) {
        this.propertyIsRent = propertyIsRent;
    }

    public Boolean getPropertyAvailable() {
        return propertyAvailable;
    }

    public void setPropertyAvailable(Boolean propertyAvailable) {
        this.propertyAvailable = propertyAvailable;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public Long getResidentialAreaId() {
        return residentialAreaId;
    }

    public void setResidentialAreaId(Long residentialAreaId) {
        this.residentialAreaId = residentialAreaId;
    }

    public String getResidentialName() {
        return residentialName;
    }

    public void setResidentialName(String residentialName) {
        this.residentialName = residentialName;
    }

    public Set<TagDTO> getTags() {
        return tags;
    }

    public void setTags(Set<TagDTO> tags) {
        this.tags = tags;
    }

    public Set<BuildingTypeDTO> getBuildingtypes() {
        return buildingtypes;
    }

    public void setBuildingtypes(Set<BuildingTypeDTO> buildingtypes) {
        this.buildingtypes = buildingtypes;
    }

    public Set<PhotoDTO> getPhotos() {
        return photos;
    }

    public void setPhotos(Set<PhotoDTO> photos) {
        this.photos = photos;
    }

    public FacilityVM getFacility() {
        return facility;
    }

    public void setFacility(FacilityVM facility) {
        this.facility = facility;
    }

    public FurnitureVM getFurniture() {
        return furniture;
    }

    public void setFurniture(FurnitureVM furniture) {
        this.furniture = furniture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PropertyVM propertyVM = (PropertyVM) o;
        if (propertyVM.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), propertyVM.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
