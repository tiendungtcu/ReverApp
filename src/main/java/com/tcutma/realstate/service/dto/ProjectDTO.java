package com.tcutma.realstate.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import javax.persistence.Lob;
import com.tcutma.realstate.domain.enumeration.TransactionStatus;
import com.tcutma.realstate.domain.enumeration.PriceUnit;
import com.tcutma.realstate.domain.enumeration.PriceUnit;

/**
 * A DTO for the Project entity.
 */
public class ProjectDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 128)
    private String projectName;

    @Size(max = 128)
    private String projectAlias;

    private String projectAvatarUrl;

    @Size(max = 64)
    private String projectDistrict;

    @Size(max = 64)
    private String projectProvince;

    private Long residentialAreaId;

    @Size(max = 128)
    private String projectRoad;

    @Size(max = 64)
    private String projectWard;

    private TransactionStatus projectStatus;

    @NotNull
    private Integer projectNoBlocks;

    @NotNull
    private Integer projectNoFloors;

    @NotNull
    private Integer projectNoApartments;

    private Integer projectNoShophouse;

    @Lob
    private String projectDescription;

    @DecimalMin(value = "0")
    private Double projectMinSellPrice;

    private Double projectMaxSellPrice;

    private PriceUnit projectSellPriceUnit;

    @DecimalMin(value = "0")
    private Double projectMinRentPrice;

    private Double projectMaxRentPrice;

    private PriceUnit projectRentPriceUnit;

    private LocalDate projectStartedDate;

    private LocalDate projectFinishingDate;

    @Min(value = 1)
    private Integer projectMinApartmentSquare;

    private Integer projectMaxApartmentSquare;

    @Min(value = 0)
    @Max(value = 100)
    private Integer projectGreenSpace;

    @Min(value = 0)
    @Max(value = 100)
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

    private Long locationId;

    private Long consultantId;

    private String consultantLogin;

    private Set<TagDTO> tags = new HashSet<>();

    private Set<BuildingTypeDTO> buildingtypes = new HashSet<>();

    private Set<InvestorDTO> inverstors = new HashSet<>();

    private Set<ContractorDTO> contractors = new HashSet<>();

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

    public Long getResidentialAreaId() {
        return residentialAreaId;
    }

    public void setResidentialAreaId(Long residentialAreaId) {
        this.residentialAreaId = residentialAreaId;
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

    public PriceUnit getProjectRentPriceUnit() {
        return projectRentPriceUnit;
    }

    public void setProjectRentPriceUnit(PriceUnit projectRentPriceUnit) {
        this.projectRentPriceUnit = projectRentPriceUnit;
    }

    public LocalDate getProjectStartedDate() {
        return projectStartedDate;
    }

    public void setProjectStartedDate(LocalDate projectStartedDate) {
        this.projectStartedDate = projectStartedDate;
    }

    public LocalDate getProjectFinishingDate() {
        return projectFinishingDate;
    }

    public void setProjectFinishingDate(LocalDate projectFinishingDate) {
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

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public Long getConsultantId() {
        return consultantId;
    }

    public void setConsultantId(Long userId) {
        this.consultantId = userId;
    }

    public String getConsultantLogin() {
        return consultantLogin;
    }

    public void setConsultantLogin(String userLogin) {
        this.consultantLogin = userLogin;
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

    public Set<InvestorDTO> getInverstors() {
        return inverstors;
    }

    public void setInverstors(Set<InvestorDTO> investors) {
        this.inverstors = investors;
    }

    public Set<ContractorDTO> getContractors() {
        return contractors;
    }

    public void setContractors(Set<ContractorDTO> contractors) {
        this.contractors = contractors;
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
            ", projectAvatarUrl='" + getProjectAvatarUrl() + "'" +
            ", projectDistrict='" + getProjectDistrict() + "'" +
            ", projectProvince='" + getProjectProvince() + "'" +
            ", residentialAreaId=" + getResidentialAreaId() +
            ", projectRoad='" + getProjectRoad() + "'" +
            ", projectWard='" + getProjectWard() + "'" +
            ", projectStatus='" + getProjectStatus() + "'" +
            ", projectNoBlocks=" + getProjectNoBlocks() +
            ", projectNoFloors=" + getProjectNoFloors() +
            ", projectNoApartments=" + getProjectNoApartments() +
            ", projectNoShophouse=" + getProjectNoShophouse() +
            ", projectDescription='" + getProjectDescription() + "'" +
            ", projectMinSellPrice=" + getProjectMinSellPrice() +
            ", projectMaxSellPrice=" + getProjectMaxSellPrice() +
            ", projectSellPriceUnit='" + getProjectSellPriceUnit() + "'" +
            ", projectMinRentPrice=" + getProjectMinRentPrice() +
            ", projectMaxRentPrice=" + getProjectMaxRentPrice() +
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
            ", location=" + getLocationId() +
            ", consultant=" + getConsultantId() +
            ", consultant='" + getConsultantLogin() + "'" +
            "}";
    }
}
