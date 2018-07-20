package com.tcutma.realstate.service.dto;

import java.io.Serializable;
import com.tcutma.realstate.domain.enumeration.TransactionStatus;
import com.tcutma.realstate.domain.enumeration.AreaUnit;
import com.tcutma.realstate.domain.enumeration.PriceUnit;
import com.tcutma.realstate.domain.enumeration.AreaUnit;
import com.tcutma.realstate.domain.enumeration.PriceUnit;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

import io.github.jhipster.service.filter.InstantFilter;




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
     * Class for filtering AreaUnit
     */
    public static class AreaUnitFilter extends Filter<AreaUnit> {
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

    private IntegerFilter projectAvatarId;

    private StringFilter projectAvatarUrl;

    private StringFilter projectDistrict;

    private StringFilter projectProvince;

    private StringFilter projectResidentialArea;

    private StringFilter projectRoad;

    private StringFilter projectWard;

    private TransactionStatusFilter projectStatus;

    private StringFilter projectType;

    private IntegerFilter projectNoBlocks;

    private IntegerFilter projectNoFloors;

    private IntegerFilter projectNoApartments;

    private IntegerFilter projectNoShophouse;

    private DoubleFilter projectMinSellPrice;

    private DoubleFilter projectMaxSellPrice;

    private AreaUnitFilter projectSellAreaUnit;

    private PriceUnitFilter projectSellPriceUnit;

    private DoubleFilter projectMinRentPrice;

    private DoubleFilter projectMaxRentPrice;

    private AreaUnitFilter projectRentAreaUnit;

    private PriceUnitFilter projectRentPriceUnit;

    private InstantFilter projectStartedDate;

    private InstantFilter projectFinishingDate;

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

    private LongFilter documentId;

    private LongFilter locationId;

    private LongFilter postId;

    private LongFilter tagId;

    private LongFilter buildingtypeId;

    private LongFilter investorId;

    private LongFilter projectbuilderId;

    private LongFilter photoId;

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

    public IntegerFilter getProjectAvatarId() {
        return projectAvatarId;
    }

    public void setProjectAvatarId(IntegerFilter projectAvatarId) {
        this.projectAvatarId = projectAvatarId;
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

    public StringFilter getProjectResidentialArea() {
        return projectResidentialArea;
    }

    public void setProjectResidentialArea(StringFilter projectResidentialArea) {
        this.projectResidentialArea = projectResidentialArea;
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

    public StringFilter getProjectType() {
        return projectType;
    }

    public void setProjectType(StringFilter projectType) {
        this.projectType = projectType;
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

    public AreaUnitFilter getProjectSellAreaUnit() {
        return projectSellAreaUnit;
    }

    public void setProjectSellAreaUnit(AreaUnitFilter projectSellAreaUnit) {
        this.projectSellAreaUnit = projectSellAreaUnit;
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

    public AreaUnitFilter getProjectRentAreaUnit() {
        return projectRentAreaUnit;
    }

    public void setProjectRentAreaUnit(AreaUnitFilter projectRentAreaUnit) {
        this.projectRentAreaUnit = projectRentAreaUnit;
    }

    public PriceUnitFilter getProjectRentPriceUnit() {
        return projectRentPriceUnit;
    }

    public void setProjectRentPriceUnit(PriceUnitFilter projectRentPriceUnit) {
        this.projectRentPriceUnit = projectRentPriceUnit;
    }

    public InstantFilter getProjectStartedDate() {
        return projectStartedDate;
    }

    public void setProjectStartedDate(InstantFilter projectStartedDate) {
        this.projectStartedDate = projectStartedDate;
    }

    public InstantFilter getProjectFinishingDate() {
        return projectFinishingDate;
    }

    public void setProjectFinishingDate(InstantFilter projectFinishingDate) {
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

    public LongFilter getDocumentId() {
        return documentId;
    }

    public void setDocumentId(LongFilter documentId) {
        this.documentId = documentId;
    }

    public LongFilter getLocationId() {
        return locationId;
    }

    public void setLocationId(LongFilter locationId) {
        this.locationId = locationId;
    }

    public LongFilter getPostId() {
        return postId;
    }

    public void setPostId(LongFilter postId) {
        this.postId = postId;
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

    public LongFilter getInvestorId() {
        return investorId;
    }

    public void setInvestorId(LongFilter investorId) {
        this.investorId = investorId;
    }

    public LongFilter getProjectbuilderId() {
        return projectbuilderId;
    }

    public void setProjectbuilderId(LongFilter projectbuilderId) {
        this.projectbuilderId = projectbuilderId;
    }

    public LongFilter getPhotoId() {
        return photoId;
    }

    public void setPhotoId(LongFilter photoId) {
        this.photoId = photoId;
    }

    @Override
    public String toString() {
        return "ProjectCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (projectName != null ? "projectName=" + projectName + ", " : "") +
                (projectAlias != null ? "projectAlias=" + projectAlias + ", " : "") +
                (projectAvatarId != null ? "projectAvatarId=" + projectAvatarId + ", " : "") +
                (projectAvatarUrl != null ? "projectAvatarUrl=" + projectAvatarUrl + ", " : "") +
                (projectDistrict != null ? "projectDistrict=" + projectDistrict + ", " : "") +
                (projectProvince != null ? "projectProvince=" + projectProvince + ", " : "") +
                (projectResidentialArea != null ? "projectResidentialArea=" + projectResidentialArea + ", " : "") +
                (projectRoad != null ? "projectRoad=" + projectRoad + ", " : "") +
                (projectWard != null ? "projectWard=" + projectWard + ", " : "") +
                (projectStatus != null ? "projectStatus=" + projectStatus + ", " : "") +
                (projectType != null ? "projectType=" + projectType + ", " : "") +
                (projectNoBlocks != null ? "projectNoBlocks=" + projectNoBlocks + ", " : "") +
                (projectNoFloors != null ? "projectNoFloors=" + projectNoFloors + ", " : "") +
                (projectNoApartments != null ? "projectNoApartments=" + projectNoApartments + ", " : "") +
                (projectNoShophouse != null ? "projectNoShophouse=" + projectNoShophouse + ", " : "") +
                (projectMinSellPrice != null ? "projectMinSellPrice=" + projectMinSellPrice + ", " : "") +
                (projectMaxSellPrice != null ? "projectMaxSellPrice=" + projectMaxSellPrice + ", " : "") +
                (projectSellAreaUnit != null ? "projectSellAreaUnit=" + projectSellAreaUnit + ", " : "") +
                (projectSellPriceUnit != null ? "projectSellPriceUnit=" + projectSellPriceUnit + ", " : "") +
                (projectMinRentPrice != null ? "projectMinRentPrice=" + projectMinRentPrice + ", " : "") +
                (projectMaxRentPrice != null ? "projectMaxRentPrice=" + projectMaxRentPrice + ", " : "") +
                (projectRentAreaUnit != null ? "projectRentAreaUnit=" + projectRentAreaUnit + ", " : "") +
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
                (documentId != null ? "documentId=" + documentId + ", " : "") +
                (locationId != null ? "locationId=" + locationId + ", " : "") +
                (postId != null ? "postId=" + postId + ", " : "") +
                (tagId != null ? "tagId=" + tagId + ", " : "") +
                (buildingtypeId != null ? "buildingtypeId=" + buildingtypeId + ", " : "") +
                (investorId != null ? "investorId=" + investorId + ", " : "") +
                (projectbuilderId != null ? "projectbuilderId=" + projectbuilderId + ", " : "") +
                (photoId != null ? "photoId=" + photoId + ", " : "") +
            "}";
    }

}
