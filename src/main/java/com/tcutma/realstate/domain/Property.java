package com.tcutma.realstate.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.tcutma.realstate.domain.enumeration.TransactionType;

import com.tcutma.realstate.domain.enumeration.UseEstablishment;

import com.tcutma.realstate.domain.enumeration.PriceUnit;

/**
 * Property entity
 */
@ApiModel(description = "Property entity")
@Entity
@Table(name = "property")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Property implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "property_code", nullable = false)
    private String propertyCode;

    @NotNull
    @Column(name = "property_name", nullable = false)
    private String propertyName;

    @Column(name = "property_alias")
    private String propertyAlias;

    @Enumerated(EnumType.STRING)
    @Column(name = "property_transaction")
    private TransactionType propertyTransaction;

    @Column(name = "property_number")
    private String propertyNumber;

    @Column(name = "property_road")
    private String propertyRoad;

    @Column(name = "property_ward")
    private String propertyWard;

    @Column(name = "property_district")
    private String propertyDistrict;

    @Column(name = "property_province")
    private String propertyProvince;

    @Lob
    @Column(name = "property_description")
    private String propertyDescription;

    @Column(name = "property_bed_rooms")
    private Integer propertyBedRooms;

    @Column(name = "property_bath_rooms")
    private Integer propertyBathRooms;

    @Column(name = "property_square")
    private Double propertySquare;

    @Enumerated(EnumType.STRING)
    @Column(name = "property_use_purpose")
    private UseEstablishment propertyUsePurpose;

    @Column(name = "property_owner_type")
    private String propertyOwnerType;

    @Column(name = "property_tower")
    private String propertyTower;

    @Column(name = "property_rent_price")
    private Double propertyRentPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "property_rent_unit")
    private PriceUnit propertyRentUnit;

    @Column(name = "property_rent_started_date")
    private ZonedDateTime propertyRentStartedDate;

    @Column(name = "property_sell_price")
    private Double propertySellPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "property_sell_unit")
    private PriceUnit propertySellUnit;

    @Column(name = "property_sell_started_date")
    private ZonedDateTime propertySellStartedDate;

    @Column(name = "property_sofa")
    private Boolean propertySofa;

    @Column(name = "property_dining_table")
    private Boolean propertyDiningTable;

    @Column(name = "property_kitchen")
    private Boolean propertyKitchen;

    @Column(name = "property_cabinet_kitchen")
    private Boolean propertyCabinetKitchen;

    @Column(name = "property_kitchen_equipment")
    private Boolean propertyKitchenEquipment;

    @Column(name = "property_wardrobe")
    private Boolean propertyWardrobe;

    @Column(name = "property_makeup_table")
    private Boolean propertyMakeupTable;

    @Column(name = "property_desk")
    private Boolean propertyDesk;

    @Column(name = "property_tivi")
    private Boolean propertyTivi;

    @Column(name = "property_washing_machine")
    private Boolean propertyWashingMachine;

    @Column(name = "property_refrigerator")
    private Boolean propertyRefrigerator;

    @Column(name = "property_aircondition")
    private Boolean propertyAircondition;

    @Column(name = "property_microwave")
    private Boolean propertyMicrowave;

    @Column(name = "property_water_heater")
    private Boolean propertyWaterHeater;

    @Column(name = "property_bed")
    private Boolean propertyBed;

    @Column(name = "property_heater")
    private Boolean propertyHeater;

    @Column(name = "property_audio_equipment")
    private Boolean propertyAudioEquipment;

    @Column(name = "property_internet")
    private Boolean propertyInternet;

    @Column(name = "property_cable_tivi")
    private Boolean propertyCableTivi;

    @Column(name = "property_pet_permission")
    private Boolean propertyPetPermission;

    @Column(name = "property_elevator")
    private Boolean propertyElevator;

    @Column(name = "property_swimming_pool")
    private Boolean propertySwimmingPool;

    @Column(name = "property_gym")
    private Boolean propertyGym;

    @Column(name = "property_functional_area")
    private Boolean propertyFunctionalArea;

    @Column(name = "property_open_24_h")
    private Boolean propertyOpen24h;

    @Column(name = "property_car_park")
    private Boolean propertyCarPark;

    @Column(name = "property_balcony")
    private Boolean propertyBalcony;

    @Column(name = "property_sauna")
    private Boolean propertySauna;

    @Column(name = "property_steam_sauna")
    private Boolean propertySteamSauna;

    @Column(name = "property_attraction")
    private Boolean propertyAttraction;

    @Column(name = "property_special_feature")
    private String propertySpecialFeature;

    @Column(name = "property_furniture_overview")
    private String propertyFurnitureOverview;

    @Column(name = "property_location_overview")
    private String propertyLocationOverview;

    @Column(name = "property_residential_community")
    private String propertyResidentialCommunity;

    @Column(name = "property_educational_aspect")
    private String propertyEducationalAspect;

    @Column(name = "property_extra_info")
    private String propertyExtraInfo;

    @Lob
    @Column(name = "property_draft")
    private byte[] propertyDraft;

    @Column(name = "property_draft_content_type")
    private String propertyDraftContentType;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "property_good_price")
    private Boolean propertyGoodPrice;

    @Column(name = "property_seen_count")
    private Long propertySeenCount;

    @Column(name = "property_is_sold")
    private Boolean propertyIsSold;

    @Column(name = "property_is_rent")
    private Boolean propertyIsRent;

    @Column(name = "property_available")
    private Boolean propertyAvailable;

    @OneToOne
    @JoinColumn(unique = true)
    private Location location;

    @ManyToOne
    @JsonIgnoreProperties("")
    private ResidentialArea residentialArea;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "property_tag",
               joinColumns = @JoinColumn(name = "properties_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "tags_id", referencedColumnName = "id"))
    private Set<Tag> tags = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "property_buildingtype",
               joinColumns = @JoinColumn(name = "properties_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "buildingtypes_id", referencedColumnName = "id"))
    private Set<BuildingType> buildingtypes = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "property_photo",
               joinColumns = @JoinColumn(name = "properties_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "photos_id", referencedColumnName = "id"))
    private Set<Photo> photos = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPropertyCode() {
        return propertyCode;
    }

    public Property propertyCode(String propertyCode) {
        this.propertyCode = propertyCode;
        return this;
    }

    public void setPropertyCode(String propertyCode) {
        this.propertyCode = propertyCode;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public Property propertyName(String propertyName) {
        this.propertyName = propertyName;
        return this;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyAlias() {
        return propertyAlias;
    }

    public Property propertyAlias(String propertyAlias) {
        this.propertyAlias = propertyAlias;
        return this;
    }

    public void setPropertyAlias(String propertyAlias) {
        this.propertyAlias = propertyAlias;
    }

    public TransactionType getPropertyTransaction() {
        return propertyTransaction;
    }

    public Property propertyTransaction(TransactionType propertyTransaction) {
        this.propertyTransaction = propertyTransaction;
        return this;
    }

    public void setPropertyTransaction(TransactionType propertyTransaction) {
        this.propertyTransaction = propertyTransaction;
    }

    public String getPropertyNumber() {
        return propertyNumber;
    }

    public Property propertyNumber(String propertyNumber) {
        this.propertyNumber = propertyNumber;
        return this;
    }

    public void setPropertyNumber(String propertyNumber) {
        this.propertyNumber = propertyNumber;
    }

    public String getPropertyRoad() {
        return propertyRoad;
    }

    public Property propertyRoad(String propertyRoad) {
        this.propertyRoad = propertyRoad;
        return this;
    }

    public void setPropertyRoad(String propertyRoad) {
        this.propertyRoad = propertyRoad;
    }

    public String getPropertyWard() {
        return propertyWard;
    }

    public Property propertyWard(String propertyWard) {
        this.propertyWard = propertyWard;
        return this;
    }

    public void setPropertyWard(String propertyWard) {
        this.propertyWard = propertyWard;
    }

    public String getPropertyDistrict() {
        return propertyDistrict;
    }

    public Property propertyDistrict(String propertyDistrict) {
        this.propertyDistrict = propertyDistrict;
        return this;
    }

    public void setPropertyDistrict(String propertyDistrict) {
        this.propertyDistrict = propertyDistrict;
    }

    public String getPropertyProvince() {
        return propertyProvince;
    }

    public Property propertyProvince(String propertyProvince) {
        this.propertyProvince = propertyProvince;
        return this;
    }

    public void setPropertyProvince(String propertyProvince) {
        this.propertyProvince = propertyProvince;
    }

    public String getPropertyDescription() {
        return propertyDescription;
    }

    public Property propertyDescription(String propertyDescription) {
        this.propertyDescription = propertyDescription;
        return this;
    }

    public void setPropertyDescription(String propertyDescription) {
        this.propertyDescription = propertyDescription;
    }

    public Integer getPropertyBedRooms() {
        return propertyBedRooms;
    }

    public Property propertyBedRooms(Integer propertyBedRooms) {
        this.propertyBedRooms = propertyBedRooms;
        return this;
    }

    public void setPropertyBedRooms(Integer propertyBedRooms) {
        this.propertyBedRooms = propertyBedRooms;
    }

    public Integer getPropertyBathRooms() {
        return propertyBathRooms;
    }

    public Property propertyBathRooms(Integer propertyBathRooms) {
        this.propertyBathRooms = propertyBathRooms;
        return this;
    }

    public void setPropertyBathRooms(Integer propertyBathRooms) {
        this.propertyBathRooms = propertyBathRooms;
    }

    public Double getPropertySquare() {
        return propertySquare;
    }

    public Property propertySquare(Double propertySquare) {
        this.propertySquare = propertySquare;
        return this;
    }

    public void setPropertySquare(Double propertySquare) {
        this.propertySquare = propertySquare;
    }

    public UseEstablishment getPropertyUsePurpose() {
        return propertyUsePurpose;
    }

    public Property propertyUsePurpose(UseEstablishment propertyUsePurpose) {
        this.propertyUsePurpose = propertyUsePurpose;
        return this;
    }

    public void setPropertyUsePurpose(UseEstablishment propertyUsePurpose) {
        this.propertyUsePurpose = propertyUsePurpose;
    }

    public String getPropertyOwnerType() {
        return propertyOwnerType;
    }

    public Property propertyOwnerType(String propertyOwnerType) {
        this.propertyOwnerType = propertyOwnerType;
        return this;
    }

    public void setPropertyOwnerType(String propertyOwnerType) {
        this.propertyOwnerType = propertyOwnerType;
    }

    public String getPropertyTower() {
        return propertyTower;
    }

    public Property propertyTower(String propertyTower) {
        this.propertyTower = propertyTower;
        return this;
    }

    public void setPropertyTower(String propertyTower) {
        this.propertyTower = propertyTower;
    }

    public Double getPropertyRentPrice() {
        return propertyRentPrice;
    }

    public Property propertyRentPrice(Double propertyRentPrice) {
        this.propertyRentPrice = propertyRentPrice;
        return this;
    }

    public void setPropertyRentPrice(Double propertyRentPrice) {
        this.propertyRentPrice = propertyRentPrice;
    }

    public PriceUnit getPropertyRentUnit() {
        return propertyRentUnit;
    }

    public Property propertyRentUnit(PriceUnit propertyRentUnit) {
        this.propertyRentUnit = propertyRentUnit;
        return this;
    }

    public void setPropertyRentUnit(PriceUnit propertyRentUnit) {
        this.propertyRentUnit = propertyRentUnit;
    }

    public ZonedDateTime getPropertyRentStartedDate() {
        return propertyRentStartedDate;
    }

    public Property propertyRentStartedDate(ZonedDateTime propertyRentStartedDate) {
        this.propertyRentStartedDate = propertyRentStartedDate;
        return this;
    }

    public void setPropertyRentStartedDate(ZonedDateTime propertyRentStartedDate) {
        this.propertyRentStartedDate = propertyRentStartedDate;
    }

    public Double getPropertySellPrice() {
        return propertySellPrice;
    }

    public Property propertySellPrice(Double propertySellPrice) {
        this.propertySellPrice = propertySellPrice;
        return this;
    }

    public void setPropertySellPrice(Double propertySellPrice) {
        this.propertySellPrice = propertySellPrice;
    }

    public PriceUnit getPropertySellUnit() {
        return propertySellUnit;
    }

    public Property propertySellUnit(PriceUnit propertySellUnit) {
        this.propertySellUnit = propertySellUnit;
        return this;
    }

    public void setPropertySellUnit(PriceUnit propertySellUnit) {
        this.propertySellUnit = propertySellUnit;
    }

    public ZonedDateTime getPropertySellStartedDate() {
        return propertySellStartedDate;
    }

    public Property propertySellStartedDate(ZonedDateTime propertySellStartedDate) {
        this.propertySellStartedDate = propertySellStartedDate;
        return this;
    }

    public void setPropertySellStartedDate(ZonedDateTime propertySellStartedDate) {
        this.propertySellStartedDate = propertySellStartedDate;
    }

    public Boolean isPropertySofa() {
        return propertySofa;
    }

    public Property propertySofa(Boolean propertySofa) {
        this.propertySofa = propertySofa;
        return this;
    }

    public void setPropertySofa(Boolean propertySofa) {
        this.propertySofa = propertySofa;
    }

    public Boolean isPropertyDiningTable() {
        return propertyDiningTable;
    }

    public Property propertyDiningTable(Boolean propertyDiningTable) {
        this.propertyDiningTable = propertyDiningTable;
        return this;
    }

    public void setPropertyDiningTable(Boolean propertyDiningTable) {
        this.propertyDiningTable = propertyDiningTable;
    }

    public Boolean isPropertyKitchen() {
        return propertyKitchen;
    }

    public Property propertyKitchen(Boolean propertyKitchen) {
        this.propertyKitchen = propertyKitchen;
        return this;
    }

    public void setPropertyKitchen(Boolean propertyKitchen) {
        this.propertyKitchen = propertyKitchen;
    }

    public Boolean isPropertyCabinetKitchen() {
        return propertyCabinetKitchen;
    }

    public Property propertyCabinetKitchen(Boolean propertyCabinetKitchen) {
        this.propertyCabinetKitchen = propertyCabinetKitchen;
        return this;
    }

    public void setPropertyCabinetKitchen(Boolean propertyCabinetKitchen) {
        this.propertyCabinetKitchen = propertyCabinetKitchen;
    }

    public Boolean isPropertyKitchenEquipment() {
        return propertyKitchenEquipment;
    }

    public Property propertyKitchenEquipment(Boolean propertyKitchenEquipment) {
        this.propertyKitchenEquipment = propertyKitchenEquipment;
        return this;
    }

    public void setPropertyKitchenEquipment(Boolean propertyKitchenEquipment) {
        this.propertyKitchenEquipment = propertyKitchenEquipment;
    }

    public Boolean isPropertyWardrobe() {
        return propertyWardrobe;
    }

    public Property propertyWardrobe(Boolean propertyWardrobe) {
        this.propertyWardrobe = propertyWardrobe;
        return this;
    }

    public void setPropertyWardrobe(Boolean propertyWardrobe) {
        this.propertyWardrobe = propertyWardrobe;
    }

    public Boolean isPropertyMakeupTable() {
        return propertyMakeupTable;
    }

    public Property propertyMakeupTable(Boolean propertyMakeupTable) {
        this.propertyMakeupTable = propertyMakeupTable;
        return this;
    }

    public void setPropertyMakeupTable(Boolean propertyMakeupTable) {
        this.propertyMakeupTable = propertyMakeupTable;
    }

    public Boolean isPropertyDesk() {
        return propertyDesk;
    }

    public Property propertyDesk(Boolean propertyDesk) {
        this.propertyDesk = propertyDesk;
        return this;
    }

    public void setPropertyDesk(Boolean propertyDesk) {
        this.propertyDesk = propertyDesk;
    }

    public Boolean isPropertyTivi() {
        return propertyTivi;
    }

    public Property propertyTivi(Boolean propertyTivi) {
        this.propertyTivi = propertyTivi;
        return this;
    }

    public void setPropertyTivi(Boolean propertyTivi) {
        this.propertyTivi = propertyTivi;
    }

    public Boolean isPropertyWashingMachine() {
        return propertyWashingMachine;
    }

    public Property propertyWashingMachine(Boolean propertyWashingMachine) {
        this.propertyWashingMachine = propertyWashingMachine;
        return this;
    }

    public void setPropertyWashingMachine(Boolean propertyWashingMachine) {
        this.propertyWashingMachine = propertyWashingMachine;
    }

    public Boolean isPropertyRefrigerator() {
        return propertyRefrigerator;
    }

    public Property propertyRefrigerator(Boolean propertyRefrigerator) {
        this.propertyRefrigerator = propertyRefrigerator;
        return this;
    }

    public void setPropertyRefrigerator(Boolean propertyRefrigerator) {
        this.propertyRefrigerator = propertyRefrigerator;
    }

    public Boolean isPropertyAircondition() {
        return propertyAircondition;
    }

    public Property propertyAircondition(Boolean propertyAircondition) {
        this.propertyAircondition = propertyAircondition;
        return this;
    }

    public void setPropertyAircondition(Boolean propertyAircondition) {
        this.propertyAircondition = propertyAircondition;
    }

    public Boolean isPropertyMicrowave() {
        return propertyMicrowave;
    }

    public Property propertyMicrowave(Boolean propertyMicrowave) {
        this.propertyMicrowave = propertyMicrowave;
        return this;
    }

    public void setPropertyMicrowave(Boolean propertyMicrowave) {
        this.propertyMicrowave = propertyMicrowave;
    }

    public Boolean isPropertyWaterHeater() {
        return propertyWaterHeater;
    }

    public Property propertyWaterHeater(Boolean propertyWaterHeater) {
        this.propertyWaterHeater = propertyWaterHeater;
        return this;
    }

    public void setPropertyWaterHeater(Boolean propertyWaterHeater) {
        this.propertyWaterHeater = propertyWaterHeater;
    }

    public Boolean isPropertyBed() {
        return propertyBed;
    }

    public Property propertyBed(Boolean propertyBed) {
        this.propertyBed = propertyBed;
        return this;
    }

    public void setPropertyBed(Boolean propertyBed) {
        this.propertyBed = propertyBed;
    }

    public Boolean isPropertyHeater() {
        return propertyHeater;
    }

    public Property propertyHeater(Boolean propertyHeater) {
        this.propertyHeater = propertyHeater;
        return this;
    }

    public void setPropertyHeater(Boolean propertyHeater) {
        this.propertyHeater = propertyHeater;
    }

    public Boolean isPropertyAudioEquipment() {
        return propertyAudioEquipment;
    }

    public Property propertyAudioEquipment(Boolean propertyAudioEquipment) {
        this.propertyAudioEquipment = propertyAudioEquipment;
        return this;
    }

    public void setPropertyAudioEquipment(Boolean propertyAudioEquipment) {
        this.propertyAudioEquipment = propertyAudioEquipment;
    }

    public Boolean isPropertyInternet() {
        return propertyInternet;
    }

    public Property propertyInternet(Boolean propertyInternet) {
        this.propertyInternet = propertyInternet;
        return this;
    }

    public void setPropertyInternet(Boolean propertyInternet) {
        this.propertyInternet = propertyInternet;
    }

    public Boolean isPropertyCableTivi() {
        return propertyCableTivi;
    }

    public Property propertyCableTivi(Boolean propertyCableTivi) {
        this.propertyCableTivi = propertyCableTivi;
        return this;
    }

    public void setPropertyCableTivi(Boolean propertyCableTivi) {
        this.propertyCableTivi = propertyCableTivi;
    }

    public Boolean isPropertyPetPermission() {
        return propertyPetPermission;
    }

    public Property propertyPetPermission(Boolean propertyPetPermission) {
        this.propertyPetPermission = propertyPetPermission;
        return this;
    }

    public void setPropertyPetPermission(Boolean propertyPetPermission) {
        this.propertyPetPermission = propertyPetPermission;
    }

    public Boolean isPropertyElevator() {
        return propertyElevator;
    }

    public Property propertyElevator(Boolean propertyElevator) {
        this.propertyElevator = propertyElevator;
        return this;
    }

    public void setPropertyElevator(Boolean propertyElevator) {
        this.propertyElevator = propertyElevator;
    }

    public Boolean isPropertySwimmingPool() {
        return propertySwimmingPool;
    }

    public Property propertySwimmingPool(Boolean propertySwimmingPool) {
        this.propertySwimmingPool = propertySwimmingPool;
        return this;
    }

    public void setPropertySwimmingPool(Boolean propertySwimmingPool) {
        this.propertySwimmingPool = propertySwimmingPool;
    }

    public Boolean isPropertyGym() {
        return propertyGym;
    }

    public Property propertyGym(Boolean propertyGym) {
        this.propertyGym = propertyGym;
        return this;
    }

    public void setPropertyGym(Boolean propertyGym) {
        this.propertyGym = propertyGym;
    }

    public Boolean isPropertyFunctionalArea() {
        return propertyFunctionalArea;
    }

    public Property propertyFunctionalArea(Boolean propertyFunctionalArea) {
        this.propertyFunctionalArea = propertyFunctionalArea;
        return this;
    }

    public void setPropertyFunctionalArea(Boolean propertyFunctionalArea) {
        this.propertyFunctionalArea = propertyFunctionalArea;
    }

    public Boolean isPropertyOpen24h() {
        return propertyOpen24h;
    }

    public Property propertyOpen24h(Boolean propertyOpen24h) {
        this.propertyOpen24h = propertyOpen24h;
        return this;
    }

    public void setPropertyOpen24h(Boolean propertyOpen24h) {
        this.propertyOpen24h = propertyOpen24h;
    }

    public Boolean isPropertyCarPark() {
        return propertyCarPark;
    }

    public Property propertyCarPark(Boolean propertyCarPark) {
        this.propertyCarPark = propertyCarPark;
        return this;
    }

    public void setPropertyCarPark(Boolean propertyCarPark) {
        this.propertyCarPark = propertyCarPark;
    }

    public Boolean isPropertyBalcony() {
        return propertyBalcony;
    }

    public Property propertyBalcony(Boolean propertyBalcony) {
        this.propertyBalcony = propertyBalcony;
        return this;
    }

    public void setPropertyBalcony(Boolean propertyBalcony) {
        this.propertyBalcony = propertyBalcony;
    }

    public Boolean isPropertySauna() {
        return propertySauna;
    }

    public Property propertySauna(Boolean propertySauna) {
        this.propertySauna = propertySauna;
        return this;
    }

    public void setPropertySauna(Boolean propertySauna) {
        this.propertySauna = propertySauna;
    }

    public Boolean isPropertySteamSauna() {
        return propertySteamSauna;
    }

    public Property propertySteamSauna(Boolean propertySteamSauna) {
        this.propertySteamSauna = propertySteamSauna;
        return this;
    }

    public void setPropertySteamSauna(Boolean propertySteamSauna) {
        this.propertySteamSauna = propertySteamSauna;
    }

    public Boolean isPropertyAttraction() {
        return propertyAttraction;
    }

    public Property propertyAttraction(Boolean propertyAttraction) {
        this.propertyAttraction = propertyAttraction;
        return this;
    }

    public void setPropertyAttraction(Boolean propertyAttraction) {
        this.propertyAttraction = propertyAttraction;
    }

    public String getPropertySpecialFeature() {
        return propertySpecialFeature;
    }

    public Property propertySpecialFeature(String propertySpecialFeature) {
        this.propertySpecialFeature = propertySpecialFeature;
        return this;
    }

    public void setPropertySpecialFeature(String propertySpecialFeature) {
        this.propertySpecialFeature = propertySpecialFeature;
    }

    public String getPropertyFurnitureOverview() {
        return propertyFurnitureOverview;
    }

    public Property propertyFurnitureOverview(String propertyFurnitureOverview) {
        this.propertyFurnitureOverview = propertyFurnitureOverview;
        return this;
    }

    public void setPropertyFurnitureOverview(String propertyFurnitureOverview) {
        this.propertyFurnitureOverview = propertyFurnitureOverview;
    }

    public String getPropertyLocationOverview() {
        return propertyLocationOverview;
    }

    public Property propertyLocationOverview(String propertyLocationOverview) {
        this.propertyLocationOverview = propertyLocationOverview;
        return this;
    }

    public void setPropertyLocationOverview(String propertyLocationOverview) {
        this.propertyLocationOverview = propertyLocationOverview;
    }

    public String getPropertyResidentialCommunity() {
        return propertyResidentialCommunity;
    }

    public Property propertyResidentialCommunity(String propertyResidentialCommunity) {
        this.propertyResidentialCommunity = propertyResidentialCommunity;
        return this;
    }

    public void setPropertyResidentialCommunity(String propertyResidentialCommunity) {
        this.propertyResidentialCommunity = propertyResidentialCommunity;
    }

    public String getPropertyEducationalAspect() {
        return propertyEducationalAspect;
    }

    public Property propertyEducationalAspect(String propertyEducationalAspect) {
        this.propertyEducationalAspect = propertyEducationalAspect;
        return this;
    }

    public void setPropertyEducationalAspect(String propertyEducationalAspect) {
        this.propertyEducationalAspect = propertyEducationalAspect;
    }

    public String getPropertyExtraInfo() {
        return propertyExtraInfo;
    }

    public Property propertyExtraInfo(String propertyExtraInfo) {
        this.propertyExtraInfo = propertyExtraInfo;
        return this;
    }

    public void setPropertyExtraInfo(String propertyExtraInfo) {
        this.propertyExtraInfo = propertyExtraInfo;
    }

    public byte[] getPropertyDraft() {
        return propertyDraft;
    }

    public Property propertyDraft(byte[] propertyDraft) {
        this.propertyDraft = propertyDraft;
        return this;
    }

    public void setPropertyDraft(byte[] propertyDraft) {
        this.propertyDraft = propertyDraft;
    }

    public String getPropertyDraftContentType() {
        return propertyDraftContentType;
    }

    public Property propertyDraftContentType(String propertyDraftContentType) {
        this.propertyDraftContentType = propertyDraftContentType;
        return this;
    }

    public void setPropertyDraftContentType(String propertyDraftContentType) {
        this.propertyDraftContentType = propertyDraftContentType;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Property longitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Property latitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Boolean isPropertyGoodPrice() {
        return propertyGoodPrice;
    }

    public Property propertyGoodPrice(Boolean propertyGoodPrice) {
        this.propertyGoodPrice = propertyGoodPrice;
        return this;
    }

    public void setPropertyGoodPrice(Boolean propertyGoodPrice) {
        this.propertyGoodPrice = propertyGoodPrice;
    }

    public Long getPropertySeenCount() {
        return propertySeenCount;
    }

    public Property propertySeenCount(Long propertySeenCount) {
        this.propertySeenCount = propertySeenCount;
        return this;
    }

    public void setPropertySeenCount(Long propertySeenCount) {
        this.propertySeenCount = propertySeenCount;
    }

    public Boolean isPropertyIsSold() {
        return propertyIsSold;
    }

    public Property propertyIsSold(Boolean propertyIsSold) {
        this.propertyIsSold = propertyIsSold;
        return this;
    }

    public void setPropertyIsSold(Boolean propertyIsSold) {
        this.propertyIsSold = propertyIsSold;
    }

    public Boolean isPropertyIsRent() {
        return propertyIsRent;
    }

    public Property propertyIsRent(Boolean propertyIsRent) {
        this.propertyIsRent = propertyIsRent;
        return this;
    }

    public void setPropertyIsRent(Boolean propertyIsRent) {
        this.propertyIsRent = propertyIsRent;
    }

    public Boolean isPropertyAvailable() {
        return propertyAvailable;
    }

    public Property propertyAvailable(Boolean propertyAvailable) {
        this.propertyAvailable = propertyAvailable;
        return this;
    }

    public void setPropertyAvailable(Boolean propertyAvailable) {
        this.propertyAvailable = propertyAvailable;
    }

    public Location getLocation() {
        return location;
    }

    public Property location(Location location) {
        this.location = location;
        return this;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public ResidentialArea getResidentialArea() {
        return residentialArea;
    }

    public Property residentialArea(ResidentialArea residentialArea) {
        this.residentialArea = residentialArea;
        return this;
    }

    public void setResidentialArea(ResidentialArea residentialArea) {
        this.residentialArea = residentialArea;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public Property tags(Set<Tag> tags) {
        this.tags = tags;
        return this;
    }

    public Property addTag(Tag tag) {
        this.tags.add(tag);
        tag.getProperties().add(this);
        return this;
    }

    public Property removeTag(Tag tag) {
        this.tags.remove(tag);
        tag.getProperties().remove(this);
        return this;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public Set<BuildingType> getBuildingtypes() {
        return buildingtypes;
    }

    public Property buildingtypes(Set<BuildingType> buildingTypes) {
        this.buildingtypes = buildingTypes;
        return this;
    }

    public Property addBuildingtype(BuildingType buildingType) {
        this.buildingtypes.add(buildingType);
        buildingType.getProperties().add(this);
        return this;
    }

    public Property removeBuildingtype(BuildingType buildingType) {
        this.buildingtypes.remove(buildingType);
        buildingType.getProperties().remove(this);
        return this;
    }

    public void setBuildingtypes(Set<BuildingType> buildingTypes) {
        this.buildingtypes = buildingTypes;
    }

    public Set<Photo> getPhotos() {
        return photos;
    }

    public Property photos(Set<Photo> photos) {
        this.photos = photos;
        return this;
    }

    public Property addPhoto(Photo photo) {
        this.photos.add(photo);
        photo.getProperties().add(this);
        return this;
    }

    public Property removePhoto(Photo photo) {
        this.photos.remove(photo);
        photo.getProperties().remove(this);
        return this;
    }

    public void setPhotos(Set<Photo> photos) {
        this.photos = photos;
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
        Property property = (Property) o;
        if (property.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), property.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Property{" +
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
            ", propertyDraftContentType='" + getPropertyDraftContentType() + "'" +
            ", longitude=" + getLongitude() +
            ", latitude=" + getLatitude() +
            ", propertyGoodPrice='" + isPropertyGoodPrice() + "'" +
            ", propertySeenCount=" + getPropertySeenCount() +
            ", propertyIsSold='" + isPropertyIsSold() + "'" +
            ", propertyIsRent='" + isPropertyIsRent() + "'" +
            ", propertyAvailable='" + isPropertyAvailable() + "'" +
            "}";
    }
}
