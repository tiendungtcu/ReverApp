package com.tcutma.realstate.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import javax.persistence.Lob;
import com.tcutma.realstate.domain.enumeration.TransactionType;
import com.tcutma.realstate.domain.enumeration.UseEstablishment;
import com.tcutma.realstate.domain.enumeration.PriceUnit;
import com.tcutma.realstate.domain.enumeration.PriceUnit;

/**
 * A DTO for the Property entity.
 */
public class PropertyDTO implements Serializable {

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

    private Boolean propertySofa;

    private Boolean propertyDiningTable;

    private Boolean propertyKitchen;

    private Boolean propertyCabinetKitchen;

    private Boolean propertyKitchenEquipment;

    private Boolean propertyWardrobe;

    private Boolean propertyMakeupTable;

    private Boolean propertyDesk;

    private Boolean propertyTivi;

    private Boolean propertyWashingMachine;

    private Boolean propertyRefrigerator;

    private Boolean propertyAircondition;

    private Boolean propertyMicrowave;

    private Boolean propertyWaterHeater;

    private Boolean propertyBed;

    private Boolean propertyHeater;

    private Boolean propertyAudioEquipment;

    private Boolean propertyInternet;

    private Boolean propertyCableTivi;

    private Boolean propertyPetPermission;

    private Boolean propertyElevator;

    private Boolean propertySwimmingPool;

    private Boolean propertyGym;

    private Boolean propertyFunctionalArea;

    private Boolean propertyOpen24h;

    private Boolean propertyCarPark;

    private Boolean propertyBalcony;

    private Boolean propertySauna;

    private Boolean propertySteamSauna;

    private Boolean propertyAttraction;

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

    private String residentialAreaResidentialName;

    private Set<TagDTO> tags = new HashSet<>();

    private Set<BuildingTypeDTO> buildingtypes = new HashSet<>();

    private Set<PhotoDTO> photos = new HashSet<>();

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

    public Boolean isPropertySofa() {
        return propertySofa;
    }

    public void setPropertySofa(Boolean propertySofa) {
        this.propertySofa = propertySofa;
    }

    public Boolean isPropertyDiningTable() {
        return propertyDiningTable;
    }

    public void setPropertyDiningTable(Boolean propertyDiningTable) {
        this.propertyDiningTable = propertyDiningTable;
    }

    public Boolean isPropertyKitchen() {
        return propertyKitchen;
    }

    public void setPropertyKitchen(Boolean propertyKitchen) {
        this.propertyKitchen = propertyKitchen;
    }

    public Boolean isPropertyCabinetKitchen() {
        return propertyCabinetKitchen;
    }

    public void setPropertyCabinetKitchen(Boolean propertyCabinetKitchen) {
        this.propertyCabinetKitchen = propertyCabinetKitchen;
    }

    public Boolean isPropertyKitchenEquipment() {
        return propertyKitchenEquipment;
    }

    public void setPropertyKitchenEquipment(Boolean propertyKitchenEquipment) {
        this.propertyKitchenEquipment = propertyKitchenEquipment;
    }

    public Boolean isPropertyWardrobe() {
        return propertyWardrobe;
    }

    public void setPropertyWardrobe(Boolean propertyWardrobe) {
        this.propertyWardrobe = propertyWardrobe;
    }

    public Boolean isPropertyMakeupTable() {
        return propertyMakeupTable;
    }

    public void setPropertyMakeupTable(Boolean propertyMakeupTable) {
        this.propertyMakeupTable = propertyMakeupTable;
    }

    public Boolean isPropertyDesk() {
        return propertyDesk;
    }

    public void setPropertyDesk(Boolean propertyDesk) {
        this.propertyDesk = propertyDesk;
    }

    public Boolean isPropertyTivi() {
        return propertyTivi;
    }

    public void setPropertyTivi(Boolean propertyTivi) {
        this.propertyTivi = propertyTivi;
    }

    public Boolean isPropertyWashingMachine() {
        return propertyWashingMachine;
    }

    public void setPropertyWashingMachine(Boolean propertyWashingMachine) {
        this.propertyWashingMachine = propertyWashingMachine;
    }

    public Boolean isPropertyRefrigerator() {
        return propertyRefrigerator;
    }

    public void setPropertyRefrigerator(Boolean propertyRefrigerator) {
        this.propertyRefrigerator = propertyRefrigerator;
    }

    public Boolean isPropertyAircondition() {
        return propertyAircondition;
    }

    public void setPropertyAircondition(Boolean propertyAircondition) {
        this.propertyAircondition = propertyAircondition;
    }

    public Boolean isPropertyMicrowave() {
        return propertyMicrowave;
    }

    public void setPropertyMicrowave(Boolean propertyMicrowave) {
        this.propertyMicrowave = propertyMicrowave;
    }

    public Boolean isPropertyWaterHeater() {
        return propertyWaterHeater;
    }

    public void setPropertyWaterHeater(Boolean propertyWaterHeater) {
        this.propertyWaterHeater = propertyWaterHeater;
    }

    public Boolean isPropertyBed() {
        return propertyBed;
    }

    public void setPropertyBed(Boolean propertyBed) {
        this.propertyBed = propertyBed;
    }

    public Boolean isPropertyHeater() {
        return propertyHeater;
    }

    public void setPropertyHeater(Boolean propertyHeater) {
        this.propertyHeater = propertyHeater;
    }

    public Boolean isPropertyAudioEquipment() {
        return propertyAudioEquipment;
    }

    public void setPropertyAudioEquipment(Boolean propertyAudioEquipment) {
        this.propertyAudioEquipment = propertyAudioEquipment;
    }

    public Boolean isPropertyInternet() {
        return propertyInternet;
    }

    public void setPropertyInternet(Boolean propertyInternet) {
        this.propertyInternet = propertyInternet;
    }

    public Boolean isPropertyCableTivi() {
        return propertyCableTivi;
    }

    public void setPropertyCableTivi(Boolean propertyCableTivi) {
        this.propertyCableTivi = propertyCableTivi;
    }

    public Boolean isPropertyPetPermission() {
        return propertyPetPermission;
    }

    public void setPropertyPetPermission(Boolean propertyPetPermission) {
        this.propertyPetPermission = propertyPetPermission;
    }

    public Boolean isPropertyElevator() {
        return propertyElevator;
    }

    public void setPropertyElevator(Boolean propertyElevator) {
        this.propertyElevator = propertyElevator;
    }

    public Boolean isPropertySwimmingPool() {
        return propertySwimmingPool;
    }

    public void setPropertySwimmingPool(Boolean propertySwimmingPool) {
        this.propertySwimmingPool = propertySwimmingPool;
    }

    public Boolean isPropertyGym() {
        return propertyGym;
    }

    public void setPropertyGym(Boolean propertyGym) {
        this.propertyGym = propertyGym;
    }

    public Boolean isPropertyFunctionalArea() {
        return propertyFunctionalArea;
    }

    public void setPropertyFunctionalArea(Boolean propertyFunctionalArea) {
        this.propertyFunctionalArea = propertyFunctionalArea;
    }

    public Boolean isPropertyOpen24h() {
        return propertyOpen24h;
    }

    public void setPropertyOpen24h(Boolean propertyOpen24h) {
        this.propertyOpen24h = propertyOpen24h;
    }

    public Boolean isPropertyCarPark() {
        return propertyCarPark;
    }

    public void setPropertyCarPark(Boolean propertyCarPark) {
        this.propertyCarPark = propertyCarPark;
    }

    public Boolean isPropertyBalcony() {
        return propertyBalcony;
    }

    public void setPropertyBalcony(Boolean propertyBalcony) {
        this.propertyBalcony = propertyBalcony;
    }

    public Boolean isPropertySauna() {
        return propertySauna;
    }

    public void setPropertySauna(Boolean propertySauna) {
        this.propertySauna = propertySauna;
    }

    public Boolean isPropertySteamSauna() {
        return propertySteamSauna;
    }

    public void setPropertySteamSauna(Boolean propertySteamSauna) {
        this.propertySteamSauna = propertySteamSauna;
    }

    public Boolean isPropertyAttraction() {
        return propertyAttraction;
    }

    public void setPropertyAttraction(Boolean propertyAttraction) {
        this.propertyAttraction = propertyAttraction;
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

    public Boolean isPropertyGoodPrice() {
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

    public Boolean isPropertyIsSold() {
        return propertyIsSold;
    }

    public void setPropertyIsSold(Boolean propertyIsSold) {
        this.propertyIsSold = propertyIsSold;
    }

    public Boolean isPropertyIsRent() {
        return propertyIsRent;
    }

    public void setPropertyIsRent(Boolean propertyIsRent) {
        this.propertyIsRent = propertyIsRent;
    }

    public Boolean isPropertyAvailable() {
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

    public String getResidentialAreaResidentialName() {
        return residentialAreaResidentialName;
    }

    public void setResidentialAreaResidentialName(String residentialAreaResidentialName) {
        this.residentialAreaResidentialName = residentialAreaResidentialName;
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

    public void setBuildingtypes(Set<BuildingTypeDTO> buildingTypes) {
        this.buildingtypes = buildingTypes;
    }

    public Set<PhotoDTO> getPhotos() {
        return photos;
    }

    public void setPhotos(Set<PhotoDTO> photos) {
        this.photos = photos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PropertyDTO propertyDTO = (PropertyDTO) o;
        if (propertyDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), propertyDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PropertyDTO{" +
            "id=" + getId() +
            ", propertyCode='" + getPropertyCode() + "'" +
            ", propertyName='" + getPropertyName() + "'" +
            ", propertyAlias='" + getPropertyAlias() + "'" +
            ", propertyTransaction='" + getPropertyTransaction() + "'" +
            ", propertyNumber='" + getPropertyNumber() + "'" +
            ", propertyRoad='" + getPropertyRoad() + "'" +
            ", propertyWard='" + getPropertyWard() + "'" +
            ", propertyDistrict='" + getPropertyDistrict() + "'" +
            ", propertyProvince='" + getPropertyProvince() + "'" +
            ", propertyDescription='" + getPropertyDescription() + "'" +
            ", propertyBedRooms=" + getPropertyBedRooms() +
            ", propertyBathRooms=" + getPropertyBathRooms() +
            ", propertySquare=" + getPropertySquare() +
            ", propertyUsePurpose='" + getPropertyUsePurpose() + "'" +
            ", propertyOwnerType='" + getPropertyOwnerType() + "'" +
            ", propertyTower='" + getPropertyTower() + "'" +
            ", propertyRentPrice=" + getPropertyRentPrice() +
            ", propertyRentUnit='" + getPropertyRentUnit() + "'" +
            ", propertyRentStartedDate='" + getPropertyRentStartedDate() + "'" +
            ", propertySellPrice=" + getPropertySellPrice() +
            ", propertySellUnit='" + getPropertySellUnit() + "'" +
            ", propertySellStartedDate='" + getPropertySellStartedDate() + "'" +
            ", propertySofa='" + isPropertySofa() + "'" +
            ", propertyDiningTable='" + isPropertyDiningTable() + "'" +
            ", propertyKitchen='" + isPropertyKitchen() + "'" +
            ", propertyCabinetKitchen='" + isPropertyCabinetKitchen() + "'" +
            ", propertyKitchenEquipment='" + isPropertyKitchenEquipment() + "'" +
            ", propertyWardrobe='" + isPropertyWardrobe() + "'" +
            ", propertyMakeupTable='" + isPropertyMakeupTable() + "'" +
            ", propertyDesk='" + isPropertyDesk() + "'" +
            ", propertyTivi='" + isPropertyTivi() + "'" +
            ", propertyWashingMachine='" + isPropertyWashingMachine() + "'" +
            ", propertyRefrigerator='" + isPropertyRefrigerator() + "'" +
            ", propertyAircondition='" + isPropertyAircondition() + "'" +
            ", propertyMicrowave='" + isPropertyMicrowave() + "'" +
            ", propertyWaterHeater='" + isPropertyWaterHeater() + "'" +
            ", propertyBed='" + isPropertyBed() + "'" +
            ", propertyHeater='" + isPropertyHeater() + "'" +
            ", propertyAudioEquipment='" + isPropertyAudioEquipment() + "'" +
            ", propertyInternet='" + isPropertyInternet() + "'" +
            ", propertyCableTivi='" + isPropertyCableTivi() + "'" +
            ", propertyPetPermission='" + isPropertyPetPermission() + "'" +
            ", propertyElevator='" + isPropertyElevator() + "'" +
            ", propertySwimmingPool='" + isPropertySwimmingPool() + "'" +
            ", propertyGym='" + isPropertyGym() + "'" +
            ", propertyFunctionalArea='" + isPropertyFunctionalArea() + "'" +
            ", propertyOpen24h='" + isPropertyOpen24h() + "'" +
            ", propertyCarPark='" + isPropertyCarPark() + "'" +
            ", propertyBalcony='" + isPropertyBalcony() + "'" +
            ", propertySauna='" + isPropertySauna() + "'" +
            ", propertySteamSauna='" + isPropertySteamSauna() + "'" +
            ", propertyAttraction='" + isPropertyAttraction() + "'" +
            ", propertySpecialFeature='" + getPropertySpecialFeature() + "'" +
            ", propertyFurnitureOverview='" + getPropertyFurnitureOverview() + "'" +
            ", propertyLocationOverview='" + getPropertyLocationOverview() + "'" +
            ", propertyResidentialCommunity='" + getPropertyResidentialCommunity() + "'" +
            ", propertyEducationalAspect='" + getPropertyEducationalAspect() + "'" +
            ", propertyExtraInfo='" + getPropertyExtraInfo() + "'" +
            ", propertyDraft='" + getPropertyDraft() + "'" +
            ", longitude=" + getLongitude() +
            ", latitude=" + getLatitude() +
            ", propertyGoodPrice='" + isPropertyGoodPrice() + "'" +
            ", propertySeenCount=" + getPropertySeenCount() +
            ", propertyIsSold='" + isPropertyIsSold() + "'" +
            ", propertyIsRent='" + isPropertyIsRent() + "'" +
            ", propertyAvailable='" + isPropertyAvailable() + "'" +
            ", location=" + getLocationId() +
            ", residentialArea=" + getResidentialAreaId() +
            ", residentialArea='" + getResidentialAreaResidentialName() + "'" +
            "}";
    }
}
