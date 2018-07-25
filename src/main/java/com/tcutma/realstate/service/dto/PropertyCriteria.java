package com.tcutma.realstate.service.dto;

import java.io.Serializable;
import com.tcutma.realstate.domain.enumeration.TransactionType;
import com.tcutma.realstate.domain.enumeration.UseEstablishment;
import com.tcutma.realstate.domain.enumeration.PriceUnit;
import com.tcutma.realstate.domain.enumeration.PriceUnit;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;


import io.github.jhipster.service.filter.LocalDateFilter;



/**
 * Criteria class for the Property entity. This class is used in PropertyResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /properties?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class PropertyCriteria implements Serializable {
    /**
     * Class for filtering TransactionType
     */
    public static class TransactionTypeFilter extends Filter<TransactionType> {
    }

    /**
     * Class for filtering UseEstablishment
     */
    public static class UseEstablishmentFilter extends Filter<UseEstablishment> {
    }

    /**
     * Class for filtering PriceUnit
     */
    public static class PriceUnitFilter extends Filter<PriceUnit> {
    }

    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private StringFilter propertyCode;

    private StringFilter propertyName;

    private StringFilter propertyAlias;

    private TransactionTypeFilter propertyTransaction;

    private StringFilter propertyNumber;

    private StringFilter propertyRoad;

    private StringFilter propertyWard;

    private StringFilter propertyDistrict;

    private StringFilter propertyProvince;

    private IntegerFilter propertyBedRooms;

    private IntegerFilter propertyBathRooms;

    private DoubleFilter propertySquare;

    private UseEstablishmentFilter propertyUsePurpose;

    private StringFilter propertyOwnerType;

    private StringFilter propertyTower;

    private DoubleFilter propertyRentPrice;

    private PriceUnitFilter propertyRentUnit;

    private LocalDateFilter propertyRentStartedDate;

    private DoubleFilter propertySellPrice;

    private PriceUnitFilter propertySellUnit;

    private LocalDateFilter propertySellStartedDate;

    private BooleanFilter propertySofa;

    private BooleanFilter propertyDiningTable;

    private BooleanFilter propertyKitchen;

    private BooleanFilter propertyCabinetKitchen;

    private BooleanFilter propertyKitchenEquipment;

    private BooleanFilter propertyWardrobe;

    private BooleanFilter propertyMakeupTable;

    private BooleanFilter propertyDesk;

    private BooleanFilter propertyTivi;

    private BooleanFilter propertyWashingMachine;

    private BooleanFilter propertyMicrowave;

    private BooleanFilter propertyWaterHeater;

    private BooleanFilter propertyBed;

    private BooleanFilter propertyHeater;

    private BooleanFilter propertyAudioEquipment;

    private BooleanFilter propertyInternet;

    private BooleanFilter propertyCableTivi;

    private BooleanFilter propertyPetPermission;

    private BooleanFilter propertyElevator;

    private BooleanFilter propertySwimmingPool;

    private BooleanFilter propertyGym;

    private BooleanFilter propertyFunctionalArea;

    private BooleanFilter propertyOpen24h;

    private BooleanFilter propertyCarPark;

    private BooleanFilter propertyBalcony;

    private BooleanFilter propertySauna;

    private BooleanFilter propertySteamSauna;

    private BooleanFilter propertyAttraction;

    private StringFilter propertySpecialFeature;

    private StringFilter propertyFurnitureOverview;

    private StringFilter propertyLocationOverview;

    private StringFilter propertyResidentialCommunity;

    private StringFilter propertyEducationalAspect;

    private StringFilter propertyExtraInfo;

    private StringFilter propertyDraftUrl;

    private DoubleFilter longitude;

    private DoubleFilter latitude;

    private BooleanFilter propertyGoodPrice;

    private LongFilter propertySeenCount;

    private BooleanFilter propertyIsSold;

    private BooleanFilter propertyIsRent;

    private BooleanFilter propertyAvailable;

    private BooleanFilter propertyRefrigerator;

    private BooleanFilter propertyAirconditioner;

    private LongFilter locationId;

    private LongFilter consultantId;

    private LongFilter tagId;

    public PropertyCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getPropertyCode() {
        return propertyCode;
    }

    public void setPropertyCode(StringFilter propertyCode) {
        this.propertyCode = propertyCode;
    }

    public StringFilter getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(StringFilter propertyName) {
        this.propertyName = propertyName;
    }

    public StringFilter getPropertyAlias() {
        return propertyAlias;
    }

    public void setPropertyAlias(StringFilter propertyAlias) {
        this.propertyAlias = propertyAlias;
    }

    public TransactionTypeFilter getPropertyTransaction() {
        return propertyTransaction;
    }

    public void setPropertyTransaction(TransactionTypeFilter propertyTransaction) {
        this.propertyTransaction = propertyTransaction;
    }

    public StringFilter getPropertyNumber() {
        return propertyNumber;
    }

    public void setPropertyNumber(StringFilter propertyNumber) {
        this.propertyNumber = propertyNumber;
    }

    public StringFilter getPropertyRoad() {
        return propertyRoad;
    }

    public void setPropertyRoad(StringFilter propertyRoad) {
        this.propertyRoad = propertyRoad;
    }

    public StringFilter getPropertyWard() {
        return propertyWard;
    }

    public void setPropertyWard(StringFilter propertyWard) {
        this.propertyWard = propertyWard;
    }

    public StringFilter getPropertyDistrict() {
        return propertyDistrict;
    }

    public void setPropertyDistrict(StringFilter propertyDistrict) {
        this.propertyDistrict = propertyDistrict;
    }

    public StringFilter getPropertyProvince() {
        return propertyProvince;
    }

    public void setPropertyProvince(StringFilter propertyProvince) {
        this.propertyProvince = propertyProvince;
    }

    public IntegerFilter getPropertyBedRooms() {
        return propertyBedRooms;
    }

    public void setPropertyBedRooms(IntegerFilter propertyBedRooms) {
        this.propertyBedRooms = propertyBedRooms;
    }

    public IntegerFilter getPropertyBathRooms() {
        return propertyBathRooms;
    }

    public void setPropertyBathRooms(IntegerFilter propertyBathRooms) {
        this.propertyBathRooms = propertyBathRooms;
    }

    public DoubleFilter getPropertySquare() {
        return propertySquare;
    }

    public void setPropertySquare(DoubleFilter propertySquare) {
        this.propertySquare = propertySquare;
    }

    public UseEstablishmentFilter getPropertyUsePurpose() {
        return propertyUsePurpose;
    }

    public void setPropertyUsePurpose(UseEstablishmentFilter propertyUsePurpose) {
        this.propertyUsePurpose = propertyUsePurpose;
    }

    public StringFilter getPropertyOwnerType() {
        return propertyOwnerType;
    }

    public void setPropertyOwnerType(StringFilter propertyOwnerType) {
        this.propertyOwnerType = propertyOwnerType;
    }

    public StringFilter getPropertyTower() {
        return propertyTower;
    }

    public void setPropertyTower(StringFilter propertyTower) {
        this.propertyTower = propertyTower;
    }

    public DoubleFilter getPropertyRentPrice() {
        return propertyRentPrice;
    }

    public void setPropertyRentPrice(DoubleFilter propertyRentPrice) {
        this.propertyRentPrice = propertyRentPrice;
    }

    public PriceUnitFilter getPropertyRentUnit() {
        return propertyRentUnit;
    }

    public void setPropertyRentUnit(PriceUnitFilter propertyRentUnit) {
        this.propertyRentUnit = propertyRentUnit;
    }

    public LocalDateFilter getPropertyRentStartedDate() {
        return propertyRentStartedDate;
    }

    public void setPropertyRentStartedDate(LocalDateFilter propertyRentStartedDate) {
        this.propertyRentStartedDate = propertyRentStartedDate;
    }

    public DoubleFilter getPropertySellPrice() {
        return propertySellPrice;
    }

    public void setPropertySellPrice(DoubleFilter propertySellPrice) {
        this.propertySellPrice = propertySellPrice;
    }

    public PriceUnitFilter getPropertySellUnit() {
        return propertySellUnit;
    }

    public void setPropertySellUnit(PriceUnitFilter propertySellUnit) {
        this.propertySellUnit = propertySellUnit;
    }

    public LocalDateFilter getPropertySellStartedDate() {
        return propertySellStartedDate;
    }

    public void setPropertySellStartedDate(LocalDateFilter propertySellStartedDate) {
        this.propertySellStartedDate = propertySellStartedDate;
    }

    public BooleanFilter getPropertySofa() {
        return propertySofa;
    }

    public void setPropertySofa(BooleanFilter propertySofa) {
        this.propertySofa = propertySofa;
    }

    public BooleanFilter getPropertyDiningTable() {
        return propertyDiningTable;
    }

    public void setPropertyDiningTable(BooleanFilter propertyDiningTable) {
        this.propertyDiningTable = propertyDiningTable;
    }

    public BooleanFilter getPropertyKitchen() {
        return propertyKitchen;
    }

    public void setPropertyKitchen(BooleanFilter propertyKitchen) {
        this.propertyKitchen = propertyKitchen;
    }

    public BooleanFilter getPropertyCabinetKitchen() {
        return propertyCabinetKitchen;
    }

    public void setPropertyCabinetKitchen(BooleanFilter propertyCabinetKitchen) {
        this.propertyCabinetKitchen = propertyCabinetKitchen;
    }

    public BooleanFilter getPropertyKitchenEquipment() {
        return propertyKitchenEquipment;
    }

    public void setPropertyKitchenEquipment(BooleanFilter propertyKitchenEquipment) {
        this.propertyKitchenEquipment = propertyKitchenEquipment;
    }

    public BooleanFilter getPropertyWardrobe() {
        return propertyWardrobe;
    }

    public void setPropertyWardrobe(BooleanFilter propertyWardrobe) {
        this.propertyWardrobe = propertyWardrobe;
    }

    public BooleanFilter getPropertyMakeupTable() {
        return propertyMakeupTable;
    }

    public void setPropertyMakeupTable(BooleanFilter propertyMakeupTable) {
        this.propertyMakeupTable = propertyMakeupTable;
    }

    public BooleanFilter getPropertyDesk() {
        return propertyDesk;
    }

    public void setPropertyDesk(BooleanFilter propertyDesk) {
        this.propertyDesk = propertyDesk;
    }

    public BooleanFilter getPropertyTivi() {
        return propertyTivi;
    }

    public void setPropertyTivi(BooleanFilter propertyTivi) {
        this.propertyTivi = propertyTivi;
    }

    public BooleanFilter getPropertyWashingMachine() {
        return propertyWashingMachine;
    }

    public void setPropertyWashingMachine(BooleanFilter propertyWashingMachine) {
        this.propertyWashingMachine = propertyWashingMachine;
    }

    public BooleanFilter getPropertyMicrowave() {
        return propertyMicrowave;
    }

    public void setPropertyMicrowave(BooleanFilter propertyMicrowave) {
        this.propertyMicrowave = propertyMicrowave;
    }

    public BooleanFilter getPropertyWaterHeater() {
        return propertyWaterHeater;
    }

    public void setPropertyWaterHeater(BooleanFilter propertyWaterHeater) {
        this.propertyWaterHeater = propertyWaterHeater;
    }

    public BooleanFilter getPropertyBed() {
        return propertyBed;
    }

    public void setPropertyBed(BooleanFilter propertyBed) {
        this.propertyBed = propertyBed;
    }

    public BooleanFilter getPropertyHeater() {
        return propertyHeater;
    }

    public void setPropertyHeater(BooleanFilter propertyHeater) {
        this.propertyHeater = propertyHeater;
    }

    public BooleanFilter getPropertyAudioEquipment() {
        return propertyAudioEquipment;
    }

    public void setPropertyAudioEquipment(BooleanFilter propertyAudioEquipment) {
        this.propertyAudioEquipment = propertyAudioEquipment;
    }

    public BooleanFilter getPropertyInternet() {
        return propertyInternet;
    }

    public void setPropertyInternet(BooleanFilter propertyInternet) {
        this.propertyInternet = propertyInternet;
    }

    public BooleanFilter getPropertyCableTivi() {
        return propertyCableTivi;
    }

    public void setPropertyCableTivi(BooleanFilter propertyCableTivi) {
        this.propertyCableTivi = propertyCableTivi;
    }

    public BooleanFilter getPropertyPetPermission() {
        return propertyPetPermission;
    }

    public void setPropertyPetPermission(BooleanFilter propertyPetPermission) {
        this.propertyPetPermission = propertyPetPermission;
    }

    public BooleanFilter getPropertyElevator() {
        return propertyElevator;
    }

    public void setPropertyElevator(BooleanFilter propertyElevator) {
        this.propertyElevator = propertyElevator;
    }

    public BooleanFilter getPropertySwimmingPool() {
        return propertySwimmingPool;
    }

    public void setPropertySwimmingPool(BooleanFilter propertySwimmingPool) {
        this.propertySwimmingPool = propertySwimmingPool;
    }

    public BooleanFilter getPropertyGym() {
        return propertyGym;
    }

    public void setPropertyGym(BooleanFilter propertyGym) {
        this.propertyGym = propertyGym;
    }

    public BooleanFilter getPropertyFunctionalArea() {
        return propertyFunctionalArea;
    }

    public void setPropertyFunctionalArea(BooleanFilter propertyFunctionalArea) {
        this.propertyFunctionalArea = propertyFunctionalArea;
    }

    public BooleanFilter getPropertyOpen24h() {
        return propertyOpen24h;
    }

    public void setPropertyOpen24h(BooleanFilter propertyOpen24h) {
        this.propertyOpen24h = propertyOpen24h;
    }

    public BooleanFilter getPropertyCarPark() {
        return propertyCarPark;
    }

    public void setPropertyCarPark(BooleanFilter propertyCarPark) {
        this.propertyCarPark = propertyCarPark;
    }

    public BooleanFilter getPropertyBalcony() {
        return propertyBalcony;
    }

    public void setPropertyBalcony(BooleanFilter propertyBalcony) {
        this.propertyBalcony = propertyBalcony;
    }

    public BooleanFilter getPropertySauna() {
        return propertySauna;
    }

    public void setPropertySauna(BooleanFilter propertySauna) {
        this.propertySauna = propertySauna;
    }

    public BooleanFilter getPropertySteamSauna() {
        return propertySteamSauna;
    }

    public void setPropertySteamSauna(BooleanFilter propertySteamSauna) {
        this.propertySteamSauna = propertySteamSauna;
    }

    public BooleanFilter getPropertyAttraction() {
        return propertyAttraction;
    }

    public void setPropertyAttraction(BooleanFilter propertyAttraction) {
        this.propertyAttraction = propertyAttraction;
    }

    public StringFilter getPropertySpecialFeature() {
        return propertySpecialFeature;
    }

    public void setPropertySpecialFeature(StringFilter propertySpecialFeature) {
        this.propertySpecialFeature = propertySpecialFeature;
    }

    public StringFilter getPropertyFurnitureOverview() {
        return propertyFurnitureOverview;
    }

    public void setPropertyFurnitureOverview(StringFilter propertyFurnitureOverview) {
        this.propertyFurnitureOverview = propertyFurnitureOverview;
    }

    public StringFilter getPropertyLocationOverview() {
        return propertyLocationOverview;
    }

    public void setPropertyLocationOverview(StringFilter propertyLocationOverview) {
        this.propertyLocationOverview = propertyLocationOverview;
    }

    public StringFilter getPropertyResidentialCommunity() {
        return propertyResidentialCommunity;
    }

    public void setPropertyResidentialCommunity(StringFilter propertyResidentialCommunity) {
        this.propertyResidentialCommunity = propertyResidentialCommunity;
    }

    public StringFilter getPropertyEducationalAspect() {
        return propertyEducationalAspect;
    }

    public void setPropertyEducationalAspect(StringFilter propertyEducationalAspect) {
        this.propertyEducationalAspect = propertyEducationalAspect;
    }

    public StringFilter getPropertyExtraInfo() {
        return propertyExtraInfo;
    }

    public void setPropertyExtraInfo(StringFilter propertyExtraInfo) {
        this.propertyExtraInfo = propertyExtraInfo;
    }

    public StringFilter getPropertyDraftUrl() {
        return propertyDraftUrl;
    }

    public void setPropertyDraftUrl(StringFilter propertyDraftUrl) {
        this.propertyDraftUrl = propertyDraftUrl;
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

    public BooleanFilter getPropertyGoodPrice() {
        return propertyGoodPrice;
    }

    public void setPropertyGoodPrice(BooleanFilter propertyGoodPrice) {
        this.propertyGoodPrice = propertyGoodPrice;
    }

    public LongFilter getPropertySeenCount() {
        return propertySeenCount;
    }

    public void setPropertySeenCount(LongFilter propertySeenCount) {
        this.propertySeenCount = propertySeenCount;
    }

    public BooleanFilter getPropertyIsSold() {
        return propertyIsSold;
    }

    public void setPropertyIsSold(BooleanFilter propertyIsSold) {
        this.propertyIsSold = propertyIsSold;
    }

    public BooleanFilter getPropertyIsRent() {
        return propertyIsRent;
    }

    public void setPropertyIsRent(BooleanFilter propertyIsRent) {
        this.propertyIsRent = propertyIsRent;
    }

    public BooleanFilter getPropertyAvailable() {
        return propertyAvailable;
    }

    public void setPropertyAvailable(BooleanFilter propertyAvailable) {
        this.propertyAvailable = propertyAvailable;
    }

    public BooleanFilter getPropertyRefrigerator() {
        return propertyRefrigerator;
    }

    public void setPropertyRefrigerator(BooleanFilter propertyRefrigerator) {
        this.propertyRefrigerator = propertyRefrigerator;
    }

    public BooleanFilter getPropertyAirconditioner() {
        return propertyAirconditioner;
    }

    public void setPropertyAirconditioner(BooleanFilter propertyAirconditioner) {
        this.propertyAirconditioner = propertyAirconditioner;
    }

    public LongFilter getLocationId() {
        return locationId;
    }

    public void setLocationId(LongFilter locationId) {
        this.locationId = locationId;
    }

    public LongFilter getConsultantId() {
        return consultantId;
    }

    public void setConsultantId(LongFilter consultantId) {
        this.consultantId = consultantId;
    }

    public LongFilter getTagId() {
        return tagId;
    }

    public void setTagId(LongFilter tagId) {
        this.tagId = tagId;
    }

    @Override
    public String toString() {
        return "PropertyCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (propertyCode != null ? "propertyCode=" + propertyCode + ", " : "") +
                (propertyName != null ? "propertyName=" + propertyName + ", " : "") +
                (propertyAlias != null ? "propertyAlias=" + propertyAlias + ", " : "") +
                (propertyTransaction != null ? "propertyTransaction=" + propertyTransaction + ", " : "") +
                (propertyNumber != null ? "propertyNumber=" + propertyNumber + ", " : "") +
                (propertyRoad != null ? "propertyRoad=" + propertyRoad + ", " : "") +
                (propertyWard != null ? "propertyWard=" + propertyWard + ", " : "") +
                (propertyDistrict != null ? "propertyDistrict=" + propertyDistrict + ", " : "") +
                (propertyProvince != null ? "propertyProvince=" + propertyProvince + ", " : "") +
                (propertyBedRooms != null ? "propertyBedRooms=" + propertyBedRooms + ", " : "") +
                (propertyBathRooms != null ? "propertyBathRooms=" + propertyBathRooms + ", " : "") +
                (propertySquare != null ? "propertySquare=" + propertySquare + ", " : "") +
                (propertyUsePurpose != null ? "propertyUsePurpose=" + propertyUsePurpose + ", " : "") +
                (propertyOwnerType != null ? "propertyOwnerType=" + propertyOwnerType + ", " : "") +
                (propertyTower != null ? "propertyTower=" + propertyTower + ", " : "") +
                (propertyRentPrice != null ? "propertyRentPrice=" + propertyRentPrice + ", " : "") +
                (propertyRentUnit != null ? "propertyRentUnit=" + propertyRentUnit + ", " : "") +
                (propertyRentStartedDate != null ? "propertyRentStartedDate=" + propertyRentStartedDate + ", " : "") +
                (propertySellPrice != null ? "propertySellPrice=" + propertySellPrice + ", " : "") +
                (propertySellUnit != null ? "propertySellUnit=" + propertySellUnit + ", " : "") +
                (propertySellStartedDate != null ? "propertySellStartedDate=" + propertySellStartedDate + ", " : "") +
                (propertySofa != null ? "propertySofa=" + propertySofa + ", " : "") +
                (propertyDiningTable != null ? "propertyDiningTable=" + propertyDiningTable + ", " : "") +
                (propertyKitchen != null ? "propertyKitchen=" + propertyKitchen + ", " : "") +
                (propertyCabinetKitchen != null ? "propertyCabinetKitchen=" + propertyCabinetKitchen + ", " : "") +
                (propertyKitchenEquipment != null ? "propertyKitchenEquipment=" + propertyKitchenEquipment + ", " : "") +
                (propertyWardrobe != null ? "propertyWardrobe=" + propertyWardrobe + ", " : "") +
                (propertyMakeupTable != null ? "propertyMakeupTable=" + propertyMakeupTable + ", " : "") +
                (propertyDesk != null ? "propertyDesk=" + propertyDesk + ", " : "") +
                (propertyTivi != null ? "propertyTivi=" + propertyTivi + ", " : "") +
                (propertyWashingMachine != null ? "propertyWashingMachine=" + propertyWashingMachine + ", " : "") +
                (propertyMicrowave != null ? "propertyMicrowave=" + propertyMicrowave + ", " : "") +
                (propertyWaterHeater != null ? "propertyWaterHeater=" + propertyWaterHeater + ", " : "") +
                (propertyBed != null ? "propertyBed=" + propertyBed + ", " : "") +
                (propertyHeater != null ? "propertyHeater=" + propertyHeater + ", " : "") +
                (propertyAudioEquipment != null ? "propertyAudioEquipment=" + propertyAudioEquipment + ", " : "") +
                (propertyInternet != null ? "propertyInternet=" + propertyInternet + ", " : "") +
                (propertyCableTivi != null ? "propertyCableTivi=" + propertyCableTivi + ", " : "") +
                (propertyPetPermission != null ? "propertyPetPermission=" + propertyPetPermission + ", " : "") +
                (propertyElevator != null ? "propertyElevator=" + propertyElevator + ", " : "") +
                (propertySwimmingPool != null ? "propertySwimmingPool=" + propertySwimmingPool + ", " : "") +
                (propertyGym != null ? "propertyGym=" + propertyGym + ", " : "") +
                (propertyFunctionalArea != null ? "propertyFunctionalArea=" + propertyFunctionalArea + ", " : "") +
                (propertyOpen24h != null ? "propertyOpen24h=" + propertyOpen24h + ", " : "") +
                (propertyCarPark != null ? "propertyCarPark=" + propertyCarPark + ", " : "") +
                (propertyBalcony != null ? "propertyBalcony=" + propertyBalcony + ", " : "") +
                (propertySauna != null ? "propertySauna=" + propertySauna + ", " : "") +
                (propertySteamSauna != null ? "propertySteamSauna=" + propertySteamSauna + ", " : "") +
                (propertyAttraction != null ? "propertyAttraction=" + propertyAttraction + ", " : "") +
                (propertySpecialFeature != null ? "propertySpecialFeature=" + propertySpecialFeature + ", " : "") +
                (propertyFurnitureOverview != null ? "propertyFurnitureOverview=" + propertyFurnitureOverview + ", " : "") +
                (propertyLocationOverview != null ? "propertyLocationOverview=" + propertyLocationOverview + ", " : "") +
                (propertyResidentialCommunity != null ? "propertyResidentialCommunity=" + propertyResidentialCommunity + ", " : "") +
                (propertyEducationalAspect != null ? "propertyEducationalAspect=" + propertyEducationalAspect + ", " : "") +
                (propertyExtraInfo != null ? "propertyExtraInfo=" + propertyExtraInfo + ", " : "") +
                (propertyDraftUrl != null ? "propertyDraftUrl=" + propertyDraftUrl + ", " : "") +
                (longitude != null ? "longitude=" + longitude + ", " : "") +
                (latitude != null ? "latitude=" + latitude + ", " : "") +
                (propertyGoodPrice != null ? "propertyGoodPrice=" + propertyGoodPrice + ", " : "") +
                (propertySeenCount != null ? "propertySeenCount=" + propertySeenCount + ", " : "") +
                (propertyIsSold != null ? "propertyIsSold=" + propertyIsSold + ", " : "") +
                (propertyIsRent != null ? "propertyIsRent=" + propertyIsRent + ", " : "") +
                (propertyAvailable != null ? "propertyAvailable=" + propertyAvailable + ", " : "") +
                (propertyRefrigerator != null ? "propertyRefrigerator=" + propertyRefrigerator + ", " : "") +
                (propertyAirconditioner != null ? "propertyAirconditioner=" + propertyAirconditioner + ", " : "") +
                (locationId != null ? "locationId=" + locationId + ", " : "") +
                (consultantId != null ? "consultantId=" + consultantId + ", " : "") +
                (tagId != null ? "tagId=" + tagId + ", " : "") +
            "}";
    }

}
