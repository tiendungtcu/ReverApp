package com.tcutma.realstate.domain;

import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.tcutma.realstate.domain.enumeration.TransactionStatus;

import com.tcutma.realstate.domain.enumeration.PriceUnit;

/**
 * Project entity
 */
@ApiModel(description = "Project entity")
@Entity
@Table(name = "project")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 128)
    @Column(name = "project_name", length = 128, nullable = false)
    private String projectName;

    @Size(max = 128)
    @Column(name = "project_alias", length = 128)
    private String projectAlias;

    @Column(name = "project_avatar_url")
    private String projectAvatarUrl;

    @Size(max = 64)
    @Column(name = "project_district", length = 64)
    private String projectDistrict;

    @Size(max = 64)
    @Column(name = "project_province", length = 64)
    private String projectProvince;

    @Column(name = "residential_area_id")
    private Long residentialAreaId;

    @Size(max = 128)
    @Column(name = "project_road", length = 128)
    private String projectRoad;

    @Size(max = 64)
    @Column(name = "project_ward", length = 64)
    private String projectWard;

    @Enumerated(EnumType.STRING)
    @Column(name = "project_status")
    private TransactionStatus projectStatus;

    @NotNull
    @Column(name = "project_no_blocks", nullable = false)
    private Integer projectNoBlocks;

    @NotNull
    @Column(name = "project_no_floors", nullable = false)
    private Integer projectNoFloors;

    @NotNull
    @Column(name = "project_no_apartments", nullable = false)
    private Integer projectNoApartments;

    @Column(name = "project_no_shophouse")
    private Integer projectNoShophouse;

    @Lob
    @Column(name = "project_description")
    private String projectDescription;

    @DecimalMin(value = "0")
    @Column(name = "project_min_sell_price")
    private Double projectMinSellPrice;

    @Column(name = "project_max_sell_price")
    private Double projectMaxSellPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "project_sell_price_unit")
    private PriceUnit projectSellPriceUnit;

    @DecimalMin(value = "0")
    @Column(name = "project_min_rent_price")
    private Double projectMinRentPrice;

    @Column(name = "project_max_rent_price")
    private Double projectMaxRentPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "project_rent_price_unit")
    private PriceUnit projectRentPriceUnit;

    @Column(name = "project_started_date")
    private LocalDate projectStartedDate;

    @Column(name = "project_finishing_date")
    private LocalDate projectFinishingDate;

    @Min(value = 1)
    @Column(name = "project_min_apartment_square")
    private Integer projectMinApartmentSquare;

    @Column(name = "project_max_apartment_square")
    private Integer projectMaxApartmentSquare;

    @Min(value = 0)
    @Max(value = 100)
    @Column(name = "project_green_space")
    private Integer projectGreenSpace;

    @Min(value = 0)
    @Max(value = 100)
    @Column(name = "project_building_density")
    private Integer projectBuildingDensity;

    @Column(name = "project_design_company")
    private String projectDesignCompany;

    @Column(name = "project_car_park")
    private Boolean projectCarPark;

    @Column(name = "project_bbq_court")
    private Boolean projectBbqCourt;

    @Column(name = "project_elevator")
    private Boolean projectElevator;

    @Column(name = "project_shopping_center")
    private Boolean projectShoppingCenter;

    @Column(name = "project_swimming_pool")
    private Boolean projectSwimmingPool;

    @Column(name = "project_community_room")
    private Boolean projectCommunityRoom;

    @Column(name = "project_gym")
    private Boolean projectGym;

    @Column(name = "project_city_park")
    private Boolean projectCityPark;

    @Column(name = "project_guard")
    private Boolean projectGuard;

    @Column(name = "project_play_ground")
    private Boolean projectPlayGround;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "project_seen_count")
    private Long projectSeenCount;

    @Column(name = "project_available")
    private Boolean projectAvailable;

    @OneToOne
    @JoinColumn(unique = true)
    private Location location;

    @OneToOne
    @JoinColumn(unique = true)
    private User consultant;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "project_tag",
               joinColumns = @JoinColumn(name = "projects_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "tags_id", referencedColumnName = "id"))
    private Set<Tag> tags = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "project_buildingtype",
               joinColumns = @JoinColumn(name = "projects_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "buildingtypes_id", referencedColumnName = "id"))
    private Set<BuildingType> buildingtypes = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "project_inverstor",
               joinColumns = @JoinColumn(name = "projects_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "inverstors_id", referencedColumnName = "id"))
    private Set<Investor> inverstors = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "project_contractor",
               joinColumns = @JoinColumn(name = "projects_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "contractors_id", referencedColumnName = "id"))
    private Set<Contractor> contractors = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public Project projectName(String projectName) {
        this.projectName = projectName;
        return this;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectAlias() {
        return projectAlias;
    }

    public Project projectAlias(String projectAlias) {
        this.projectAlias = projectAlias;
        return this;
    }

    public void setProjectAlias(String projectAlias) {
        this.projectAlias = projectAlias;
    }

    public String getProjectAvatarUrl() {
        return projectAvatarUrl;
    }

    public Project projectAvatarUrl(String projectAvatarUrl) {
        this.projectAvatarUrl = projectAvatarUrl;
        return this;
    }

    public void setProjectAvatarUrl(String projectAvatarUrl) {
        this.projectAvatarUrl = projectAvatarUrl;
    }

    public String getProjectDistrict() {
        return projectDistrict;
    }

    public Project projectDistrict(String projectDistrict) {
        this.projectDistrict = projectDistrict;
        return this;
    }

    public void setProjectDistrict(String projectDistrict) {
        this.projectDistrict = projectDistrict;
    }

    public String getProjectProvince() {
        return projectProvince;
    }

    public Project projectProvince(String projectProvince) {
        this.projectProvince = projectProvince;
        return this;
    }

    public void setProjectProvince(String projectProvince) {
        this.projectProvince = projectProvince;
    }

    public Long getResidentialAreaId() {
        return residentialAreaId;
    }

    public Project residentialAreaId(Long residentialAreaId) {
        this.residentialAreaId = residentialAreaId;
        return this;
    }

    public void setResidentialAreaId(Long residentialAreaId) {
        this.residentialAreaId = residentialAreaId;
    }

    public String getProjectRoad() {
        return projectRoad;
    }

    public Project projectRoad(String projectRoad) {
        this.projectRoad = projectRoad;
        return this;
    }

    public void setProjectRoad(String projectRoad) {
        this.projectRoad = projectRoad;
    }

    public String getProjectWard() {
        return projectWard;
    }

    public Project projectWard(String projectWard) {
        this.projectWard = projectWard;
        return this;
    }

    public void setProjectWard(String projectWard) {
        this.projectWard = projectWard;
    }

    public TransactionStatus getProjectStatus() {
        return projectStatus;
    }

    public Project projectStatus(TransactionStatus projectStatus) {
        this.projectStatus = projectStatus;
        return this;
    }

    public void setProjectStatus(TransactionStatus projectStatus) {
        this.projectStatus = projectStatus;
    }

    public Integer getProjectNoBlocks() {
        return projectNoBlocks;
    }

    public Project projectNoBlocks(Integer projectNoBlocks) {
        this.projectNoBlocks = projectNoBlocks;
        return this;
    }

    public void setProjectNoBlocks(Integer projectNoBlocks) {
        this.projectNoBlocks = projectNoBlocks;
    }

    public Integer getProjectNoFloors() {
        return projectNoFloors;
    }

    public Project projectNoFloors(Integer projectNoFloors) {
        this.projectNoFloors = projectNoFloors;
        return this;
    }

    public void setProjectNoFloors(Integer projectNoFloors) {
        this.projectNoFloors = projectNoFloors;
    }

    public Integer getProjectNoApartments() {
        return projectNoApartments;
    }

    public Project projectNoApartments(Integer projectNoApartments) {
        this.projectNoApartments = projectNoApartments;
        return this;
    }

    public void setProjectNoApartments(Integer projectNoApartments) {
        this.projectNoApartments = projectNoApartments;
    }

    public Integer getProjectNoShophouse() {
        return projectNoShophouse;
    }

    public Project projectNoShophouse(Integer projectNoShophouse) {
        this.projectNoShophouse = projectNoShophouse;
        return this;
    }

    public void setProjectNoShophouse(Integer projectNoShophouse) {
        this.projectNoShophouse = projectNoShophouse;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public Project projectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
        return this;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public Double getProjectMinSellPrice() {
        return projectMinSellPrice;
    }

    public Project projectMinSellPrice(Double projectMinSellPrice) {
        this.projectMinSellPrice = projectMinSellPrice;
        return this;
    }

    public void setProjectMinSellPrice(Double projectMinSellPrice) {
        this.projectMinSellPrice = projectMinSellPrice;
    }

    public Double getProjectMaxSellPrice() {
        return projectMaxSellPrice;
    }

    public Project projectMaxSellPrice(Double projectMaxSellPrice) {
        this.projectMaxSellPrice = projectMaxSellPrice;
        return this;
    }

    public void setProjectMaxSellPrice(Double projectMaxSellPrice) {
        this.projectMaxSellPrice = projectMaxSellPrice;
    }

    public PriceUnit getProjectSellPriceUnit() {
        return projectSellPriceUnit;
    }

    public Project projectSellPriceUnit(PriceUnit projectSellPriceUnit) {
        this.projectSellPriceUnit = projectSellPriceUnit;
        return this;
    }

    public void setProjectSellPriceUnit(PriceUnit projectSellPriceUnit) {
        this.projectSellPriceUnit = projectSellPriceUnit;
    }

    public Double getProjectMinRentPrice() {
        return projectMinRentPrice;
    }

    public Project projectMinRentPrice(Double projectMinRentPrice) {
        this.projectMinRentPrice = projectMinRentPrice;
        return this;
    }

    public void setProjectMinRentPrice(Double projectMinRentPrice) {
        this.projectMinRentPrice = projectMinRentPrice;
    }

    public Double getProjectMaxRentPrice() {
        return projectMaxRentPrice;
    }

    public Project projectMaxRentPrice(Double projectMaxRentPrice) {
        this.projectMaxRentPrice = projectMaxRentPrice;
        return this;
    }

    public void setProjectMaxRentPrice(Double projectMaxRentPrice) {
        this.projectMaxRentPrice = projectMaxRentPrice;
    }

    public PriceUnit getProjectRentPriceUnit() {
        return projectRentPriceUnit;
    }

    public Project projectRentPriceUnit(PriceUnit projectRentPriceUnit) {
        this.projectRentPriceUnit = projectRentPriceUnit;
        return this;
    }

    public void setProjectRentPriceUnit(PriceUnit projectRentPriceUnit) {
        this.projectRentPriceUnit = projectRentPriceUnit;
    }

    public LocalDate getProjectStartedDate() {
        return projectStartedDate;
    }

    public Project projectStartedDate(LocalDate projectStartedDate) {
        this.projectStartedDate = projectStartedDate;
        return this;
    }

    public void setProjectStartedDate(LocalDate projectStartedDate) {
        this.projectStartedDate = projectStartedDate;
    }

    public LocalDate getProjectFinishingDate() {
        return projectFinishingDate;
    }

    public Project projectFinishingDate(LocalDate projectFinishingDate) {
        this.projectFinishingDate = projectFinishingDate;
        return this;
    }

    public void setProjectFinishingDate(LocalDate projectFinishingDate) {
        this.projectFinishingDate = projectFinishingDate;
    }

    public Integer getProjectMinApartmentSquare() {
        return projectMinApartmentSquare;
    }

    public Project projectMinApartmentSquare(Integer projectMinApartmentSquare) {
        this.projectMinApartmentSquare = projectMinApartmentSquare;
        return this;
    }

    public void setProjectMinApartmentSquare(Integer projectMinApartmentSquare) {
        this.projectMinApartmentSquare = projectMinApartmentSquare;
    }

    public Integer getProjectMaxApartmentSquare() {
        return projectMaxApartmentSquare;
    }

    public Project projectMaxApartmentSquare(Integer projectMaxApartmentSquare) {
        this.projectMaxApartmentSquare = projectMaxApartmentSquare;
        return this;
    }

    public void setProjectMaxApartmentSquare(Integer projectMaxApartmentSquare) {
        this.projectMaxApartmentSquare = projectMaxApartmentSquare;
    }

    public Integer getProjectGreenSpace() {
        return projectGreenSpace;
    }

    public Project projectGreenSpace(Integer projectGreenSpace) {
        this.projectGreenSpace = projectGreenSpace;
        return this;
    }

    public void setProjectGreenSpace(Integer projectGreenSpace) {
        this.projectGreenSpace = projectGreenSpace;
    }

    public Integer getProjectBuildingDensity() {
        return projectBuildingDensity;
    }

    public Project projectBuildingDensity(Integer projectBuildingDensity) {
        this.projectBuildingDensity = projectBuildingDensity;
        return this;
    }

    public void setProjectBuildingDensity(Integer projectBuildingDensity) {
        this.projectBuildingDensity = projectBuildingDensity;
    }

    public String getProjectDesignCompany() {
        return projectDesignCompany;
    }

    public Project projectDesignCompany(String projectDesignCompany) {
        this.projectDesignCompany = projectDesignCompany;
        return this;
    }

    public void setProjectDesignCompany(String projectDesignCompany) {
        this.projectDesignCompany = projectDesignCompany;
    }

    public Boolean isProjectCarPark() {
        return projectCarPark;
    }

    public Project projectCarPark(Boolean projectCarPark) {
        this.projectCarPark = projectCarPark;
        return this;
    }

    public void setProjectCarPark(Boolean projectCarPark) {
        this.projectCarPark = projectCarPark;
    }

    public Boolean isProjectBbqCourt() {
        return projectBbqCourt;
    }

    public Project projectBbqCourt(Boolean projectBbqCourt) {
        this.projectBbqCourt = projectBbqCourt;
        return this;
    }

    public void setProjectBbqCourt(Boolean projectBbqCourt) {
        this.projectBbqCourt = projectBbqCourt;
    }

    public Boolean isProjectElevator() {
        return projectElevator;
    }

    public Project projectElevator(Boolean projectElevator) {
        this.projectElevator = projectElevator;
        return this;
    }

    public void setProjectElevator(Boolean projectElevator) {
        this.projectElevator = projectElevator;
    }

    public Boolean isProjectShoppingCenter() {
        return projectShoppingCenter;
    }

    public Project projectShoppingCenter(Boolean projectShoppingCenter) {
        this.projectShoppingCenter = projectShoppingCenter;
        return this;
    }

    public void setProjectShoppingCenter(Boolean projectShoppingCenter) {
        this.projectShoppingCenter = projectShoppingCenter;
    }

    public Boolean isProjectSwimmingPool() {
        return projectSwimmingPool;
    }

    public Project projectSwimmingPool(Boolean projectSwimmingPool) {
        this.projectSwimmingPool = projectSwimmingPool;
        return this;
    }

    public void setProjectSwimmingPool(Boolean projectSwimmingPool) {
        this.projectSwimmingPool = projectSwimmingPool;
    }

    public Boolean isProjectCommunityRoom() {
        return projectCommunityRoom;
    }

    public Project projectCommunityRoom(Boolean projectCommunityRoom) {
        this.projectCommunityRoom = projectCommunityRoom;
        return this;
    }

    public void setProjectCommunityRoom(Boolean projectCommunityRoom) {
        this.projectCommunityRoom = projectCommunityRoom;
    }

    public Boolean isProjectGym() {
        return projectGym;
    }

    public Project projectGym(Boolean projectGym) {
        this.projectGym = projectGym;
        return this;
    }

    public void setProjectGym(Boolean projectGym) {
        this.projectGym = projectGym;
    }

    public Boolean isProjectCityPark() {
        return projectCityPark;
    }

    public Project projectCityPark(Boolean projectCityPark) {
        this.projectCityPark = projectCityPark;
        return this;
    }

    public void setProjectCityPark(Boolean projectCityPark) {
        this.projectCityPark = projectCityPark;
    }

    public Boolean isProjectGuard() {
        return projectGuard;
    }

    public Project projectGuard(Boolean projectGuard) {
        this.projectGuard = projectGuard;
        return this;
    }

    public void setProjectGuard(Boolean projectGuard) {
        this.projectGuard = projectGuard;
    }

    public Boolean isProjectPlayGround() {
        return projectPlayGround;
    }

    public Project projectPlayGround(Boolean projectPlayGround) {
        this.projectPlayGround = projectPlayGround;
        return this;
    }

    public void setProjectPlayGround(Boolean projectPlayGround) {
        this.projectPlayGround = projectPlayGround;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Project longitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Project latitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Long getProjectSeenCount() {
        return projectSeenCount;
    }

    public Project projectSeenCount(Long projectSeenCount) {
        this.projectSeenCount = projectSeenCount;
        return this;
    }

    public void setProjectSeenCount(Long projectSeenCount) {
        this.projectSeenCount = projectSeenCount;
    }

    public Boolean isProjectAvailable() {
        return projectAvailable;
    }

    public Project projectAvailable(Boolean projectAvailable) {
        this.projectAvailable = projectAvailable;
        return this;
    }

    public void setProjectAvailable(Boolean projectAvailable) {
        this.projectAvailable = projectAvailable;
    }

    public Location getLocation() {
        return location;
    }

    public Project location(Location location) {
        this.location = location;
        return this;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public User getConsultant() {
        return consultant;
    }

    public Project consultant(User user) {
        this.consultant = user;
        return this;
    }

    public void setConsultant(User user) {
        this.consultant = user;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public Project tags(Set<Tag> tags) {
        this.tags = tags;
        return this;
    }

    public Project addTag(Tag tag) {
        this.tags.add(tag);
        return this;
    }

    public Project removeTag(Tag tag) {
        this.tags.remove(tag);
        return this;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public Set<BuildingType> getBuildingtypes() {
        return buildingtypes;
    }

    public Project buildingtypes(Set<BuildingType> buildingTypes) {
        this.buildingtypes = buildingTypes;
        return this;
    }

    public Project addBuildingtype(BuildingType buildingType) {
        this.buildingtypes.add(buildingType);
        return this;
    }

    public Project removeBuildingtype(BuildingType buildingType) {
        this.buildingtypes.remove(buildingType);
        return this;
    }

    public void setBuildingtypes(Set<BuildingType> buildingTypes) {
        this.buildingtypes = buildingTypes;
    }

    public Set<Investor> getInverstors() {
        return inverstors;
    }

    public Project inverstors(Set<Investor> investors) {
        this.inverstors = investors;
        return this;
    }

    public Project addInverstor(Investor investor) {
        this.inverstors.add(investor);
        return this;
    }

    public Project removeInverstor(Investor investor) {
        this.inverstors.remove(investor);
        return this;
    }

    public void setInverstors(Set<Investor> investors) {
        this.inverstors = investors;
    }

    public Set<Contractor> getContractors() {
        return contractors;
    }

    public Project contractors(Set<Contractor> contractors) {
        this.contractors = contractors;
        return this;
    }

    public Project addContractor(Contractor contractor) {
        this.contractors.add(contractor);
        return this;
    }

    public Project removeContractor(Contractor contractor) {
        this.contractors.remove(contractor);
        return this;
    }

    public void setContractors(Set<Contractor> contractors) {
        this.contractors = contractors;
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
        Project project = (Project) o;
        if (project.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), project.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Project{" +
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
            "}";
    }
}
