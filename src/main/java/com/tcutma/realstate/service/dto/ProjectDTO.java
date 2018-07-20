package com.tcutma.realstate.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import javax.persistence.Lob;
import com.tcutma.realstate.domain.enumeration.TransactionStatus;
import com.tcutma.realstate.domain.enumeration.AreaUnit;
import com.tcutma.realstate.domain.enumeration.PriceUnit;
import com.tcutma.realstate.domain.enumeration.AreaUnit;
import com.tcutma.realstate.domain.enumeration.PriceUnit;

/**
 * A DTO for the Project entity.
 */
public class ProjectDTO implements Serializable {

    private Long id;

    @NotNull
    private String projectName;

    @NotNull
    private String projectAlias;

    @Lob
    private byte[] projectAvatar;
    private String projectAvatarContentType;

    private Integer projectAvatarId;

    private String projectAvatarUrl;

    private String projectDistrict;

    private String projectProvince;

    private String projectResidentialArea;

    private String projectRoad;

    private String projectWard;

    private TransactionStatus projectStatus;

    private String projectType;

    @NotNull
    private Integer projectNoBlocks;

    @NotNull
    private Integer projectNoFloors;

    @NotNull
    private Integer projectNoApartments;

    private Integer projectNoShophouse;

    @Lob
    private String projectDescription;

    private Double projectMinSellPrice;

    private Double projectMaxSellPrice;

    private AreaUnit projectSellAreaUnit;

    private PriceUnit projectSellPriceUnit;

    private Double projectMinRentPrice;

    private Double projectMaxRentPrice;

    private AreaUnit projectRentAreaUnit;

    private PriceUnit projectRentPriceUnit;

    private Instant projectStartedDate;

    private Instant projectFinishingDate;

    private Integer projectMinApartmentSquare;

    private Integer projectMaxApartmentSquare;

    private Integer projectGreenSpace;

    private Integer projectBuildingDensity;

    private String projectDesignCompany;

    private Boolean projectCarPark;

    private Boolean projectBbqCourt;

    private Boolean projectElevator;

    private Boolean projectShoppingCenter;

    private Boolean projectSwimmingPool;

    private Boolean projectCommunityRoom;

    private Boolean projectGym;

    private Boolean projectCityPark;

    private Boolean projectGuard;

    private Boolean projectPlayGround;

    private Double longitude;

    private Double latitude;

    private Long projectSeenCount;

    private Boolean projectAvailable;

    private Long documentId;

    private Long locationId;

    private Set<TagDTO> tags = new HashSet<>();

    private Set<BuildingTypeDTO> buildingtypes = new HashSet<>();

    private Set<InvestorDTO> investors = new HashSet<>();

    private Set<ProjectBuilderDTO> projectbuilders = new HashSet<>();

    private Set<PhotoDTO> photos = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectAlias() {
        return projectAlias;
    }

    public void setProjectAlias(String projectAlias) {
        this.projectAlias = projectAlias;
    }

    public byte[] getProjectAvatar() {
        return projectAvatar;
    }

    public void setProjectAvatar(byte[] projectAvatar) {
        this.projectAvatar = projectAvatar;
    }

    public String getProjectAvatarContentType() {
        return projectAvatarContentType;
    }

    public void setProjectAvatarContentType(String projectAvatarContentType) {
        this.projectAvatarContentType = projectAvatarContentType;
    }

    public Integer getProjectAvatarId() {
        return projectAvatarId;
    }

    public void setProjectAvatarId(Integer projectAvatarId) {
        this.projectAvatarId = projectAvatarId;
    }

    public String getProjectAvatarUrl() {
        return projectAvatarUrl;
    }

    public void setProjectAvatarUrl(String projectAvatarUrl) {
        this.projectAvatarUrl = projectAvatarUrl;
    }

    public String getProjectDistrict() {
        return projectDistrict;
    }

    public void setProjectDistrict(String projectDistrict) {
        this.projectDistrict = projectDistrict;
    }

    public String getProjectProvince() {
        return projectProvince;
    }

    public void setProjectProvince(String projectProvince) {
        this.projectProvince = projectProvince;
    }

    public String getProjectResidentialArea() {
        return projectResidentialArea;
    }

    public void setProjectResidentialArea(String projectResidentialArea) {
        this.projectResidentialArea = projectResidentialArea;
    }

    public String getProjectRoad() {
        return projectRoad;
    }

    public void setProjectRoad(String projectRoad) {
        this.projectRoad = projectRoad;
    }

    public String getProjectWard() {
        return projectWard;
    }

    public void setProjectWard(String projectWard) {
        this.projectWard = projectWard;
    }

    public TransactionStatus getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(TransactionStatus projectStatus) {
        this.projectStatus = projectStatus;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public Integer getProjectNoBlocks() {
        return projectNoBlocks;
    }

    public void setProjectNoBlocks(Integer projectNoBlocks) {
        this.projectNoBlocks = projectNoBlocks;
    }

    public Integer getProjectNoFloors() {
        return projectNoFloors;
    }

    public void setProjectNoFloors(Integer projectNoFloors) {
        this.projectNoFloors = projectNoFloors;
    }

    public Integer getProjectNoApartments() {
        return projectNoApartments;
    }

    public void setProjectNoApartments(Integer projectNoApartments) {
        this.projectNoApartments = projectNoApartments;
    }

    public Integer getProjectNoShophouse() {
        return projectNoShophouse;
    }

    public void setProjectNoShophouse(Integer projectNoShophouse) {
        this.projectNoShophouse = projectNoShophouse;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public Double getProjectMinSellPrice() {
        return projectMinSellPrice;
    }

    public void setProjectMinSellPrice(Double projectMinSellPrice) {
        this.projectMinSellPrice = projectMinSellPrice;
    }

    public Double getProjectMaxSellPrice() {
        return projectMaxSellPrice;
    }

    public void setProjectMaxSellPrice(Double projectMaxSellPrice) {
        this.projectMaxSellPrice = projectMaxSellPrice;
    }

    public AreaUnit getProjectSellAreaUnit() {
        return projectSellAreaUnit;
    }

    public void setProjectSellAreaUnit(AreaUnit projectSellAreaUnit) {
        this.projectSellAreaUnit = projectSellAreaUnit;
    }

    public PriceUnit getProjectSellPriceUnit() {
        return projectSellPriceUnit;
    }

    public void setProjectSellPriceUnit(PriceUnit projectSellPriceUnit) {
        this.projectSellPriceUnit = projectSellPriceUnit;
    }

    public Double getProjectMinRentPrice() {
        return projectMinRentPrice;
    }

    public void setProjectMinRentPrice(Double projectMinRentPrice) {
        this.projectMinRentPrice = projectMinRentPrice;
    }

    public Double getProjectMaxRentPrice() {
        return projectMaxRentPrice;
    }

    public void setProjectMaxRentPrice(Double projectMaxRentPrice) {
        this.projectMaxRentPrice = projectMaxRentPrice;
    }

    public AreaUnit getProjectRentAreaUnit() {
        return projectRentAreaUnit;
    }

    public void setProjectRentAreaUnit(AreaUnit projectRentAreaUnit) {
        this.projectRentAreaUnit = projectRentAreaUnit;
    }

    public PriceUnit getProjectRentPriceUnit() {
        return projectRentPriceUnit;
    }

    public void setProjectRentPriceUnit(PriceUnit projectRentPriceUnit) {
        this.projectRentPriceUnit = projectRentPriceUnit;
    }

    public Instant getProjectStartedDate() {
        return projectStartedDate;
    }

    public void setProjectStartedDate(Instant projectStartedDate) {
        this.projectStartedDate = projectStartedDate;
    }

    public Instant getProjectFinishingDate() {
        return projectFinishingDate;
    }

    public void setProjectFinishingDate(Instant projectFinishingDate) {
        this.projectFinishingDate = projectFinishingDate;
    }

    public Integer getProjectMinApartmentSquare() {
        return projectMinApartmentSquare;
    }

    public void setProjectMinApartmentSquare(Integer projectMinApartmentSquare) {
        this.projectMinApartmentSquare = projectMinApartmentSquare;
    }

    public Integer getProjectMaxApartmentSquare() {
        return projectMaxApartmentSquare;
    }

    public void setProjectMaxApartmentSquare(Integer projectMaxApartmentSquare) {
        this.projectMaxApartmentSquare = projectMaxApartmentSquare;
    }

    public Integer getProjectGreenSpace() {
        return projectGreenSpace;
    }

    public void setProjectGreenSpace(Integer projectGreenSpace) {
        this.projectGreenSpace = projectGreenSpace;
    }

    public Integer getProjectBuildingDensity() {
        return projectBuildingDensity;
    }

    public void setProjectBuildingDensity(Integer projectBuildingDensity) {
        this.projectBuildingDensity = projectBuildingDensity;
    }

    public String getProjectDesignCompany() {
        return projectDesignCompany;
    }

    public void setProjectDesignCompany(String projectDesignCompany) {
        this.projectDesignCompany = projectDesignCompany;
    }

    public Boolean isProjectCarPark() {
        return projectCarPark;
    }

    public void setProjectCarPark(Boolean projectCarPark) {
        this.projectCarPark = projectCarPark;
    }

    public Boolean isProjectBbqCourt() {
        return projectBbqCourt;
    }

    public void setProjectBbqCourt(Boolean projectBbqCourt) {
        this.projectBbqCourt = projectBbqCourt;
    }

    public Boolean isProjectElevator() {
        return projectElevator;
    }

    public void setProjectElevator(Boolean projectElevator) {
        this.projectElevator = projectElevator;
    }

    public Boolean isProjectShoppingCenter() {
        return projectShoppingCenter;
    }

    public void setProjectShoppingCenter(Boolean projectShoppingCenter) {
        this.projectShoppingCenter = projectShoppingCenter;
    }

    public Boolean isProjectSwimmingPool() {
        return projectSwimmingPool;
    }

    public void setProjectSwimmingPool(Boolean projectSwimmingPool) {
        this.projectSwimmingPool = projectSwimmingPool;
    }

    public Boolean isProjectCommunityRoom() {
        return projectCommunityRoom;
    }

    public void setProjectCommunityRoom(Boolean projectCommunityRoom) {
        this.projectCommunityRoom = projectCommunityRoom;
    }

    public Boolean isProjectGym() {
        return projectGym;
    }

    public void setProjectGym(Boolean projectGym) {
        this.projectGym = projectGym;
    }

    public Boolean isProjectCityPark() {
        return projectCityPark;
    }

    public void setProjectCityPark(Boolean projectCityPark) {
        this.projectCityPark = projectCityPark;
    }

    public Boolean isProjectGuard() {
        return projectGuard;
    }

    public void setProjectGuard(Boolean projectGuard) {
        this.projectGuard = projectGuard;
    }

    public Boolean isProjectPlayGround() {
        return projectPlayGround;
    }

    public void setProjectPlayGround(Boolean projectPlayGround) {
        this.projectPlayGround = projectPlayGround;
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

    public Long getProjectSeenCount() {
        return projectSeenCount;
    }

    public void setProjectSeenCount(Long projectSeenCount) {
        this.projectSeenCount = projectSeenCount;
    }

    public Boolean isProjectAvailable() {
        return projectAvailable;
    }

    public void setProjectAvailable(Boolean projectAvailable) {
        this.projectAvailable = projectAvailable;
    }

    public Long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
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

    public Set<InvestorDTO> getInvestors() {
        return investors;
    }

    public void setInvestors(Set<InvestorDTO> investors) {
        this.investors = investors;
    }

    public Set<ProjectBuilderDTO> getProjectbuilders() {
        return projectbuilders;
    }

    public void setProjectbuilders(Set<ProjectBuilderDTO> projectBuilders) {
        this.projectbuilders = projectBuilders;
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

        ProjectDTO projectDTO = (ProjectDTO) o;
        if (projectDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), projectDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProjectDTO{" +
            "id=" + getId() +
            ", projectName='" + getProjectName() + "'" +
            ", projectAlias='" + getProjectAlias() + "'" +
            ", projectAvatar='" + getProjectAvatar() + "'" +
            ", projectAvatarId=" + getProjectAvatarId() +
            ", projectAvatarUrl='" + getProjectAvatarUrl() + "'" +
            ", projectDistrict='" + getProjectDistrict() + "'" +
            ", projectProvince='" + getProjectProvince() + "'" +
            ", projectResidentialArea='" + getProjectResidentialArea() + "'" +
            ", projectRoad='" + getProjectRoad() + "'" +
            ", projectWard='" + getProjectWard() + "'" +
            ", projectStatus='" + getProjectStatus() + "'" +
            ", projectType='" + getProjectType() + "'" +
            ", projectNoBlocks=" + getProjectNoBlocks() +
            ", projectNoFloors=" + getProjectNoFloors() +
            ", projectNoApartments=" + getProjectNoApartments() +
            ", projectNoShophouse=" + getProjectNoShophouse() +
            ", projectDescription='" + getProjectDescription() + "'" +
            ", projectMinSellPrice=" + getProjectMinSellPrice() +
            ", projectMaxSellPrice=" + getProjectMaxSellPrice() +
            ", projectSellAreaUnit='" + getProjectSellAreaUnit() + "'" +
            ", projectSellPriceUnit='" + getProjectSellPriceUnit() + "'" +
            ", projectMinRentPrice=" + getProjectMinRentPrice() +
            ", projectMaxRentPrice=" + getProjectMaxRentPrice() +
            ", projectRentAreaUnit='" + getProjectRentAreaUnit() + "'" +
            ", projectRentPriceUnit='" + getProjectRentPriceUnit() + "'" +
            ", projectStartedDate='" + getProjectStartedDate() + "'" +
            ", projectFinishingDate='" + getProjectFinishingDate() + "'" +
            ", projectMinApartmentSquare=" + getProjectMinApartmentSquare() +
            ", projectMaxApartmentSquare=" + getProjectMaxApartmentSquare() +
            ", projectGreenSpace=" + getProjectGreenSpace() +
            ", projectBuildingDensity=" + getProjectBuildingDensity() +
            ", projectDesignCompany='" + getProjectDesignCompany() + "'" +
            ", projectCarPark='" + isProjectCarPark() + "'" +
            ", projectBbqCourt='" + isProjectBbqCourt() + "'" +
            ", projectElevator='" + isProjectElevator() + "'" +
            ", projectShoppingCenter='" + isProjectShoppingCenter() + "'" +
            ", projectSwimmingPool='" + isProjectSwimmingPool() + "'" +
            ", projectCommunityRoom='" + isProjectCommunityRoom() + "'" +
            ", projectGym='" + isProjectGym() + "'" +
            ", projectCityPark='" + isProjectCityPark() + "'" +
            ", projectGuard='" + isProjectGuard() + "'" +
            ", projectPlayGround='" + isProjectPlayGround() + "'" +
            ", longitude=" + getLongitude() +
            ", latitude=" + getLatitude() +
            ", projectSeenCount=" + getProjectSeenCount() +
            ", projectAvailable='" + isProjectAvailable() + "'" +
            ", document=" + getDocumentId() +
            ", location=" + getLocationId() +
            "}";
    }
}
