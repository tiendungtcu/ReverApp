package com.tcutma.realstate.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.tcutma.realstate.domain.enumeration.TransactionStatus;

import com.tcutma.realstate.domain.enumeration.AreaUnit;

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
    @Column(name = "project_name", nullable = false)
    private String projectName;

    @NotNull
    @Column(name = "project_alias", nullable = false)
    private String projectAlias;

    @Lob
    @Column(name = "project_avatar")
    private byte[] projectAvatar;

    @Column(name = "project_avatar_content_type")
    private String projectAvatarContentType;

    @Column(name = "project_avatar_id")
    private Integer projectAvatarId;

    @Column(name = "project_avatar_url")
    private String projectAvatarUrl;

    @Column(name = "project_district")
    private String projectDistrict;

    @Column(name = "project_province")
    private String projectProvince;

    @Column(name = "project_residential_area")
    private String projectResidentialArea;

    @Column(name = "project_road")
    private String projectRoad;

    @Column(name = "project_ward")
    private String projectWard;

    @Enumerated(EnumType.STRING)
    @Column(name = "project_status")
    private TransactionStatus projectStatus;

    @Column(name = "project_type")
    private String projectType;

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

    @Column(name = "project_min_sell_price")
    private Double projectMinSellPrice;

    @Column(name = "project_max_sell_price")
    private Double projectMaxSellPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "project_sell_area_unit")
    private AreaUnit projectSellAreaUnit;

    @Enumerated(EnumType.STRING)
    @Column(name = "project_sell_price_unit")
    private PriceUnit projectSellPriceUnit;

    @Column(name = "project_min_rent_price")
    private Double projectMinRentPrice;

    @Column(name = "project_max_rent_price")
    private Double projectMaxRentPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "project_rent_area_unit")
    private AreaUnit projectRentAreaUnit;

    @Enumerated(EnumType.STRING)
    @Column(name = "project_rent_price_unit")
    private PriceUnit projectRentPriceUnit;

    @Column(name = "project_started_date")
    private Instant projectStartedDate;

    @Column(name = "project_finishing_date")
    private Instant projectFinishingDate;

    @Column(name = "project_min_apartment_square")
    private Integer projectMinApartmentSquare;

    @Column(name = "project_max_apartment_square")
    private Integer projectMaxApartmentSquare;

    @Column(name = "project_green_space")
    private Integer projectGreenSpace;

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
    private Document document;

    @OneToOne
    @JoinColumn(unique = true)
    private Location location;

    @OneToMany(mappedBy = "project")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<BlogPost> posts = new HashSet<>();

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
    @JoinTable(name = "project_investor",
               joinColumns = @JoinColumn(name = "projects_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "investors_id", referencedColumnName = "id"))
    private Set<Investor> investors = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "project_projectbuilder",
               joinColumns = @JoinColumn(name = "projects_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "projectbuilders_id", referencedColumnName = "id"))
    private Set<ProjectBuilder> projectbuilders = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "project_photo",
               joinColumns = @JoinColumn(name = "projects_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "photos_id", referencedColumnName = "id"))
    private Set<Photo> photos = new HashSet<>();

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

    public byte[] getProjectAvatar() {
        return projectAvatar;
    }

    public Project projectAvatar(byte[] projectAvatar) {
        this.projectAvatar = projectAvatar;
        return this;
    }

    public void setProjectAvatar(byte[] projectAvatar) {
        this.projectAvatar = projectAvatar;
    }

    public String getProjectAvatarContentType() {
        return projectAvatarContentType;
    }

    public Project projectAvatarContentType(String projectAvatarContentType) {
        this.projectAvatarContentType = projectAvatarContentType;
        return this;
    }

    public void setProjectAvatarContentType(String projectAvatarContentType) {
        this.projectAvatarContentType = projectAvatarContentType;
    }

    public Integer getProjectAvatarId() {
        return projectAvatarId;
    }

    public Project projectAvatarId(Integer projectAvatarId) {
        this.projectAvatarId = projectAvatarId;
        return this;
    }

    public void setProjectAvatarId(Integer projectAvatarId) {
        this.projectAvatarId = projectAvatarId;
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

    public String getProjectResidentialArea() {
        return projectResidentialArea;
    }

    public Project projectResidentialArea(String projectResidentialArea) {
        this.projectResidentialArea = projectResidentialArea;
        return this;
    }

    public void setProjectResidentialArea(String projectResidentialArea) {
        this.projectResidentialArea = projectResidentialArea;
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

    public String getProjectType() {
        return projectType;
    }

    public Project projectType(String projectType) {
        this.projectType = projectType;
        return this;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
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

    public AreaUnit getProjectSellAreaUnit() {
        return projectSellAreaUnit;
    }

    public Project projectSellAreaUnit(AreaUnit projectSellAreaUnit) {
        this.projectSellAreaUnit = projectSellAreaUnit;
        return this;
    }

    public void setProjectSellAreaUnit(AreaUnit projectSellAreaUnit) {
        this.projectSellAreaUnit = projectSellAreaUnit;
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

    public AreaUnit getProjectRentAreaUnit() {
        return projectRentAreaUnit;
    }

    public Project projectRentAreaUnit(AreaUnit projectRentAreaUnit) {
        this.projectRentAreaUnit = projectRentAreaUnit;
        return this;
    }

    public void setProjectRentAreaUnit(AreaUnit projectRentAreaUnit) {
        this.projectRentAreaUnit = projectRentAreaUnit;
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

    public Instant getProjectStartedDate() {
        return projectStartedDate;
    }

    public Project projectStartedDate(Instant projectStartedDate) {
        this.projectStartedDate = projectStartedDate;
        return this;
    }

    public void setProjectStartedDate(Instant projectStartedDate) {
        this.projectStartedDate = projectStartedDate;
    }

    public Instant getProjectFinishingDate() {
        return projectFinishingDate;
    }

    public Project projectFinishingDate(Instant projectFinishingDate) {
        this.projectFinishingDate = projectFinishingDate;
        return this;
    }

    public void setProjectFinishingDate(Instant projectFinishingDate) {
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

    public Document getDocument() {
        return document;
    }

    public Project document(Document document) {
        this.document = document;
        return this;
    }

    public void setDocument(Document document) {
        this.document = document;
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

    public Set<BlogPost> getPosts() {
        return posts;
    }

    public Project posts(Set<BlogPost> blogPosts) {
        this.posts = blogPosts;
        return this;
    }

    public Project addPost(BlogPost blogPost) {
        this.posts.add(blogPost);
        blogPost.setProject(this);
        return this;
    }

    public Project removePost(BlogPost blogPost) {
        this.posts.remove(blogPost);
        blogPost.setProject(null);
        return this;
    }

    public void setPosts(Set<BlogPost> blogPosts) {
        this.posts = blogPosts;
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
        tag.getProjects().add(this);
        return this;
    }

    public Project removeTag(Tag tag) {
        this.tags.remove(tag);
        tag.getProjects().remove(this);
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
        buildingType.getProjects().add(this);
        return this;
    }

    public Project removeBuildingtype(BuildingType buildingType) {
        this.buildingtypes.remove(buildingType);
        buildingType.getProjects().remove(this);
        return this;
    }

    public void setBuildingtypes(Set<BuildingType> buildingTypes) {
        this.buildingtypes = buildingTypes;
    }

    public Set<Investor> getInvestors() {
        return investors;
    }

    public Project investors(Set<Investor> investors) {
        this.investors = investors;
        return this;
    }

    public Project addInvestor(Investor investor) {
        this.investors.add(investor);
        investor.getProjects().add(this);
        return this;
    }

    public Project removeInvestor(Investor investor) {
        this.investors.remove(investor);
        investor.getProjects().remove(this);
        return this;
    }

    public void setInvestors(Set<Investor> investors) {
        this.investors = investors;
    }

    public Set<ProjectBuilder> getProjectbuilders() {
        return projectbuilders;
    }

    public Project projectbuilders(Set<ProjectBuilder> projectBuilders) {
        this.projectbuilders = projectBuilders;
        return this;
    }

    public Project addProjectbuilder(ProjectBuilder projectBuilder) {
        this.projectbuilders.add(projectBuilder);
        projectBuilder.getProjects().add(this);
        return this;
    }

    public Project removeProjectbuilder(ProjectBuilder projectBuilder) {
        this.projectbuilders.remove(projectBuilder);
        projectBuilder.getProjects().remove(this);
        return this;
    }

    public void setProjectbuilders(Set<ProjectBuilder> projectBuilders) {
        this.projectbuilders = projectBuilders;
    }

    public Set<Photo> getPhotos() {
        return photos;
    }

    public Project photos(Set<Photo> photos) {
        this.photos = photos;
        return this;
    }

    public Project addPhoto(Photo photo) {
        this.photos.add(photo);
        photo.getProjects().add(this);
        return this;
    }

    public Project removePhoto(Photo photo) {
        this.photos.remove(photo);
        photo.getProjects().remove(this);
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
            ", projectAvatar='" + getProjectAvatar() + "'" +
            ", projectAvatarContentType='" + getProjectAvatarContentType() + "'" +
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
            "}";
    }
}
