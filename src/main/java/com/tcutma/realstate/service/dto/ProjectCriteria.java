package com.tcutma.realstate.service.dto;

import java.io.Serializable;
import com.tcutma.realstate.domain.enumeration.TransactionStatus;
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
 * Criteria class for the Project entity. This class is used in ProjectResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /projects?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ProjectCriteria implements Serializable {
    /**
     * Class for filtering TransactionStatus
     */
    public static class TransactionStatusFilter extends Filter<TransactionStatus> {
    }

    /**
     * Class for filtering PriceUnit
     */
    public static class PriceUnitFilter extends Filter<PriceUnit> {
    }

    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private StringFilter projectName;

    private StringFilter projectAlias;

    private StringFilter projectAvatarUrl;

    private StringFilter projectDistrict;

    private StringFilter projectProvince;

    private LongFilter residentialAreaId;

    private StringFilter projectRoad;

    private StringFilter projectWard;

    private TransactionStatusFilter projectStatus;

    private IntegerFilter projectNoBlocks;

    private IntegerFilter projectNoFloors;

    private IntegerFilter projectNoApartments;

    private IntegerFilter projectNoShophouse;

    private DoubleFilter projectMinSellPrice;

    private DoubleFilter projectMaxSellPrice;

    private PriceUnitFilter projectSellPriceUnit;

    private DoubleFilter projectMinRentPrice;

    private DoubleFilter projectMaxRentPrice;

    private PriceUnitFilter projectRentPriceUnit;

    private LocalDateFilter projectStartedDate;

    private LocalDateFilter projectFinishingDate;

    private IntegerFilter projectMinApartmentSquare;

    private IntegerFilter projectMaxApartmentSquare;

    private IntegerFilter projectGreenSpace;

    private IntegerFilter projectBuildingDensity;

    private StringFilter projectDesignCompany;

    private BooleanFilter projectCarPark;

    private BooleanFilter projectBbqCourt;

    private BooleanFilter projectElevator;

    private BooleanFilter projectShoppingCenter;

    private BooleanFilter projectSwimmingPool;

    private BooleanFilter projectCommunityRoom;

    private BooleanFilter projectGym;

    private BooleanFilter projectCityPark;

    private BooleanFilter projectGuard;

    private BooleanFilter projectPlayGround;

    private DoubleFilter longitude;

    private DoubleFilter latitude;

    private LongFilter projectSeenCount;

    private BooleanFilter projectAvailable;

    private LongFilter locationId;

    private LongFilter consultantId;

    private LongFilter tagId;

    private LongFilter buildingtypeId;

    private LongFilter inverstorId;

    private LongFilter contractorId;

    public ProjectCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getProjectName() {
        return projectName;
    }

    public void setProjectName(StringFilter projectName) {
        this.projectName = projectName;
    }

    public StringFilter getProjectAlias() {
        return projectAlias;
    }

    public void setProjectAlias(StringFilter projectAlias) {
        this.projectAlias = projectAlias;
    }

    public StringFilter getProjectAvatarUrl() {
        return projectAvatarUrl;
    }

    public void setProjectAvatarUrl(StringFilter projectAvatarUrl) {
        this.projectAvatarUrl = projectAvatarUrl;
    }

    public StringFilter getProjectDistrict() {
        return projectDistrict;
    }

    public void setProjectDistrict(StringFilter projectDistrict) {
        this.projectDistrict = projectDistrict;
    }

    public StringFilter getProjectProvince() {
        return projectProvince;
    }

    public void setProjectProvince(StringFilter projectProvince) {
        this.projectProvince = projectProvince;
    }

    public LongFilter getResidentialAreaId() {
        return residentialAreaId;
    }

    public void setResidentialAreaId(LongFilter residentialAreaId) {
        this.residentialAreaId = residentialAreaId;
    }

    public StringFilter getProjectRoad() {
        return projectRoad;
    }

    public void setProjectRoad(StringFilter projectRoad) {
        this.projectRoad = projectRoad;
    }

    public StringFilter getProjectWard() {
        return projectWard;
    }

    public void setProjectWard(StringFilter projectWard) {
        this.projectWard = projectWard;
    }

    public TransactionStatusFilter getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(TransactionStatusFilter projectStatus) {
        this.projectStatus = projectStatus;
    }

    public IntegerFilter getProjectNoBlocks() {
        return projectNoBlocks;
    }

    public void setProjectNoBlocks(IntegerFilter projectNoBlocks) {
        this.projectNoBlocks = projectNoBlocks;
    }

    public IntegerFilter getProjectNoFloors() {
        return projectNoFloors;
    }

    public void setProjectNoFloors(IntegerFilter projectNoFloors) {
        this.projectNoFloors = projectNoFloors;
    }

    public IntegerFilter getProjectNoApartments() {
        return projectNoApartments;
    }

    public void setProjectNoApartments(IntegerFilter projectNoApartments) {
        this.projectNoApartments = projectNoApartments;
    }

    public IntegerFilter getProjectNoShophouse() {
        return projectNoShophouse;
    }

    public void setProjectNoShophouse(IntegerFilter projectNoShophouse) {
        this.projectNoShophouse = projectNoShophouse;
    }

    public DoubleFilter getProjectMinSellPrice() {
        return projectMinSellPrice;
    }

    public void setProjectMinSellPrice(DoubleFilter projectMinSellPrice) {
        this.projectMinSellPrice = projectMinSellPrice;
    }

    public DoubleFilter getProjectMaxSellPrice() {
        return projectMaxSellPrice;
    }

    public void setProjectMaxSellPrice(DoubleFilter projectMaxSellPrice) {
        this.projectMaxSellPrice = projectMaxSellPrice;
    }

    public PriceUnitFilter getProjectSellPriceUnit() {
        return projectSellPriceUnit;
    }

    public void setProjectSellPriceUnit(PriceUnitFilter projectSellPriceUnit) {
        this.projectSellPriceUnit = projectSellPriceUnit;
    }

    public DoubleFilter getProjectMinRentPrice() {
        return projectMinRentPrice;
    }

    public void setProjectMinRentPrice(DoubleFilter projectMinRentPrice) {
        this.projectMinRentPrice = projectMinRentPrice;
    }

    public DoubleFilter getProjectMaxRentPrice() {
        return projectMaxRentPrice;
    }

    public void setProjectMaxRentPrice(DoubleFilter projectMaxRentPrice) {
        this.projectMaxRentPrice = projectMaxRentPrice;
    }

    public PriceUnitFilter getProjectRentPriceUnit() {
        return projectRentPriceUnit;
    }

    public void setProjectRentPriceUnit(PriceUnitFilter projectRentPriceUnit) {
        this.projectRentPriceUnit = projectRentPriceUnit;
    }

    public LocalDateFilter getProjectStartedDate() {
        return projectStartedDate;
    }

    public void setProjectStartedDate(LocalDateFilter projectStartedDate) {
        this.projectStartedDate = projectStartedDate;
    }

    public LocalDateFilter getProjectFinishingDate() {
        return projectFinishingDate;
    }

    public void setProjectFinishingDate(LocalDateFilter projectFinishingDate) {
        this.projectFinishingDate = projectFinishingDate;
    }

    public IntegerFilter getProjectMinApartmentSquare() {
        return projectMinApartmentSquare;
    }

    public void setProjectMinApartmentSquare(IntegerFilter projectMinApartmentSquare) {
        this.projectMinApartmentSquare = projectMinApartmentSquare;
    }

    public IntegerFilter getProjectMaxApartmentSquare() {
        return projectMaxApartmentSquare;
    }

    public void setProjectMaxApartmentSquare(IntegerFilter projectMaxApartmentSquare) {
        this.projectMaxApartmentSquare = projectMaxApartmentSquare;
    }

    public IntegerFilter getProjectGreenSpace() {
        return projectGreenSpace;
    }

    public void setProjectGreenSpace(IntegerFilter projectGreenSpace) {
        this.projectGreenSpace = projectGreenSpace;
    }

    public IntegerFilter getProjectBuildingDensity() {
        return projectBuildingDensity;
    }

    public void setProjectBuildingDensity(IntegerFilter projectBuildingDensity) {
        this.projectBuildingDensity = projectBuildingDensity;
    }

    public StringFilter getProjectDesignCompany() {
        return projectDesignCompany;
    }

    public void setProjectDesignCompany(StringFilter projectDesignCompany) {
        this.projectDesignCompany = projectDesignCompany;
    }

    public BooleanFilter getProjectCarPark() {
        return projectCarPark;
    }

    public void setProjectCarPark(BooleanFilter projectCarPark) {
        this.projectCarPark = projectCarPark;
    }

    public BooleanFilter getProjectBbqCourt() {
        return projectBbqCourt;
    }

    public void setProjectBbqCourt(BooleanFilter projectBbqCourt) {
        this.projectBbqCourt = projectBbqCourt;
    }

    public BooleanFilter getProjectElevator() {
        return projectElevator;
    }

    public void setProjectElevator(BooleanFilter projectElevator) {
        this.projectElevator = projectElevator;
    }

    public BooleanFilter getProjectShoppingCenter() {
        return projectShoppingCenter;
    }

    public void setProjectShoppingCenter(BooleanFilter projectShoppingCenter) {
        this.projectShoppingCenter = projectShoppingCenter;
    }

    public BooleanFilter getProjectSwimmingPool() {
        return projectSwimmingPool;
    }

    public void setProjectSwimmingPool(BooleanFilter projectSwimmingPool) {
        this.projectSwimmingPool = projectSwimmingPool;
    }

    public BooleanFilter getProjectCommunityRoom() {
        return projectCommunityRoom;
    }

    public void setProjectCommunityRoom(BooleanFilter projectCommunityRoom) {
        this.projectCommunityRoom = projectCommunityRoom;
    }

    public BooleanFilter getProjectGym() {
        return projectGym;
    }

    public void setProjectGym(BooleanFilter projectGym) {
        this.projectGym = projectGym;
    }

    public BooleanFilter getProjectCityPark() {
        return projectCityPark;
    }

    public void setProjectCityPark(BooleanFilter projectCityPark) {
        this.projectCityPark = projectCityPark;
    }

    public BooleanFilter getProjectGuard() {
        return projectGuard;
    }

    public void setProjectGuard(BooleanFilter projectGuard) {
        this.projectGuard = projectGuard;
    }

    public BooleanFilter getProjectPlayGround() {
        return projectPlayGround;
    }

    public void setProjectPlayGround(BooleanFilter projectPlayGround) {
        this.projectPlayGround = projectPlayGround;
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

    public LongFilter getProjectSeenCount() {
        return projectSeenCount;
    }

    public void setProjectSeenCount(LongFilter projectSeenCount) {
        this.projectSeenCount = projectSeenCount;
    }

    public BooleanFilter getProjectAvailable() {
        return projectAvailable;
    }

    public void setProjectAvailable(BooleanFilter projectAvailable) {
        this.projectAvailable = projectAvailable;
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

    public LongFilter getBuildingtypeId() {
        return buildingtypeId;
    }

    public void setBuildingtypeId(LongFilter buildingtypeId) {
        this.buildingtypeId = buildingtypeId;
    }

    public LongFilter getInverstorId() {
        return inverstorId;
    }

    public void setInverstorId(LongFilter inverstorId) {
        this.inverstorId = inverstorId;
    }

    public LongFilter getContractorId() {
        return contractorId;
    }

    public void setContractorId(LongFilter contractorId) {
        this.contractorId = contractorId;
    }

    @Override
    public String toString() {
        return "ProjectCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (projectName != null ? "projectName=" + projectName + ", " : "") +
                (projectAlias != null ? "projectAlias=" + projectAlias + ", " : "") +
                (projectAvatarUrl != null ? "projectAvatarUrl=" + projectAvatarUrl + ", " : "") +
                (projectDistrict != null ? "projectDistrict=" + projectDistrict + ", " : "") +
                (projectProvince != null ? "projectProvince=" + projectProvince + ", " : "") +
                (residentialAreaId != null ? "residentialAreaId=" + residentialAreaId + ", " : "") +
                (projectRoad != null ? "projectRoad=" + projectRoad + ", " : "") +
                (projectWard != null ? "projectWard=" + projectWard + ", " : "") +
                (projectStatus != null ? "projectStatus=" + projectStatus + ", " : "") +
                (projectNoBlocks != null ? "projectNoBlocks=" + projectNoBlocks + ", " : "") +
                (projectNoFloors != null ? "projectNoFloors=" + projectNoFloors + ", " : "") +
                (projectNoApartments != null ? "projectNoApartments=" + projectNoApartments + ", " : "") +
                (projectNoShophouse != null ? "projectNoShophouse=" + projectNoShophouse + ", " : "") +
                (projectMinSellPrice != null ? "projectMinSellPrice=" + projectMinSellPrice + ", " : "") +
                (projectMaxSellPrice != null ? "projectMaxSellPrice=" + projectMaxSellPrice + ", " : "") +
                (projectSellPriceUnit != null ? "projectSellPriceUnit=" + projectSellPriceUnit + ", " : "") +
                (projectMinRentPrice != null ? "projectMinRentPrice=" + projectMinRentPrice + ", " : "") +
                (projectMaxRentPrice != null ? "projectMaxRentPrice=" + projectMaxRentPrice + ", " : "") +
                (projectRentPriceUnit != null ? "projectRentPriceUnit=" + projectRentPriceUnit + ", " : "") +
                (projectStartedDate != null ? "projectStartedDate=" + projectStartedDate + ", " : "") +
                (projectFinishingDate != null ? "projectFinishingDate=" + projectFinishingDate + ", " : "") +
                (projectMinApartmentSquare != null ? "projectMinApartmentSquare=" + projectMinApartmentSquare + ", " : "") +
                (projectMaxApartmentSquare != null ? "projectMaxApartmentSquare=" + projectMaxApartmentSquare + ", " : "") +
                (projectGreenSpace != null ? "projectGreenSpace=" + projectGreenSpace + ", " : "") +
                (projectBuildingDensity != null ? "projectBuildingDensity=" + projectBuildingDensity + ", " : "") +
                (projectDesignCompany != null ? "projectDesignCompany=" + projectDesignCompany + ", " : "") +
                (projectCarPark != null ? "projectCarPark=" + projectCarPark + ", " : "") +
                (projectBbqCourt != null ? "projectBbqCourt=" + projectBbqCourt + ", " : "") +
                (projectElevator != null ? "projectElevator=" + projectElevator + ", " : "") +
                (projectShoppingCenter != null ? "projectShoppingCenter=" + projectShoppingCenter + ", " : "") +
                (projectSwimmingPool != null ? "projectSwimmingPool=" + projectSwimmingPool + ", " : "") +
                (projectCommunityRoom != null ? "projectCommunityRoom=" + projectCommunityRoom + ", " : "") +
                (projectGym != null ? "projectGym=" + projectGym + ", " : "") +
                (projectCityPark != null ? "projectCityPark=" + projectCityPark + ", " : "") +
                (projectGuard != null ? "projectGuard=" + projectGuard + ", " : "") +
                (projectPlayGround != null ? "projectPlayGround=" + projectPlayGround + ", " : "") +
                (longitude != null ? "longitude=" + longitude + ", " : "") +
                (latitude != null ? "latitude=" + latitude + ", " : "") +
                (projectSeenCount != null ? "projectSeenCount=" + projectSeenCount + ", " : "") +
                (projectAvailable != null ? "projectAvailable=" + projectAvailable + ", " : "") +
                (locationId != null ? "locationId=" + locationId + ", " : "") +
                (consultantId != null ? "consultantId=" + consultantId + ", " : "") +
                (tagId != null ? "tagId=" + tagId + ", " : "") +
                (buildingtypeId != null ? "buildingtypeId=" + buildingtypeId + ", " : "") +
                (inverstorId != null ? "inverstorId=" + inverstorId + ", " : "") +
                (contractorId != null ? "contractorId=" + contractorId + ", " : "") +
            "}";
    }

}
