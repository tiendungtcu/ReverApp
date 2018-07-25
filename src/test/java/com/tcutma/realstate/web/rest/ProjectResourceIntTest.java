package com.tcutma.realstate.web.rest;

import com.tcutma.realstate.RiverApp;

import com.tcutma.realstate.domain.Project;
import com.tcutma.realstate.domain.Location;
import com.tcutma.realstate.domain.User;
import com.tcutma.realstate.domain.Tag;
import com.tcutma.realstate.domain.BuildingType;
import com.tcutma.realstate.domain.Investor;
import com.tcutma.realstate.domain.Contractor;
import com.tcutma.realstate.repository.ProjectRepository;
import com.tcutma.realstate.service.ProjectService;
import com.tcutma.realstate.service.dto.ProjectDTO;
import com.tcutma.realstate.service.mapper.ProjectMapper;
import com.tcutma.realstate.web.rest.errors.ExceptionTranslator;
import com.tcutma.realstate.service.dto.ProjectCriteria;
import com.tcutma.realstate.service.ProjectQueryService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;


import static com.tcutma.realstate.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.tcutma.realstate.domain.enumeration.TransactionStatus;
import com.tcutma.realstate.domain.enumeration.PriceUnit;
import com.tcutma.realstate.domain.enumeration.PriceUnit;
/**
 * Test class for the ProjectResource REST controller.
 *
 * @see ProjectResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RiverApp.class)
public class ProjectResourceIntTest {

    private static final String DEFAULT_PROJECT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PROJECT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PROJECT_ALIAS = "AAAAAAAAAA";
    private static final String UPDATED_PROJECT_ALIAS = "BBBBBBBBBB";

    private static final String DEFAULT_PROJECT_AVATAR_URL = "AAAAAAAAAA";
    private static final String UPDATED_PROJECT_AVATAR_URL = "BBBBBBBBBB";

    private static final String DEFAULT_PROJECT_DISTRICT = "AAAAAAAAAA";
    private static final String UPDATED_PROJECT_DISTRICT = "BBBBBBBBBB";

    private static final String DEFAULT_PROJECT_PROVINCE = "AAAAAAAAAA";
    private static final String UPDATED_PROJECT_PROVINCE = "BBBBBBBBBB";

    private static final Long DEFAULT_RESIDENTIAL_AREA_ID = 1L;
    private static final Long UPDATED_RESIDENTIAL_AREA_ID = 2L;

    private static final String DEFAULT_PROJECT_ROAD = "AAAAAAAAAA";
    private static final String UPDATED_PROJECT_ROAD = "BBBBBBBBBB";

    private static final String DEFAULT_PROJECT_WARD = "AAAAAAAAAA";
    private static final String UPDATED_PROJECT_WARD = "BBBBBBBBBB";

    private static final TransactionStatus DEFAULT_PROJECT_STATUS = TransactionStatus.SELLING;
    private static final TransactionStatus UPDATED_PROJECT_STATUS = TransactionStatus.PRESELL;

    private static final Integer DEFAULT_PROJECT_NO_BLOCKS = 1;
    private static final Integer UPDATED_PROJECT_NO_BLOCKS = 2;

    private static final Integer DEFAULT_PROJECT_NO_FLOORS = 1;
    private static final Integer UPDATED_PROJECT_NO_FLOORS = 2;

    private static final Integer DEFAULT_PROJECT_NO_APARTMENTS = 1;
    private static final Integer UPDATED_PROJECT_NO_APARTMENTS = 2;

    private static final Integer DEFAULT_PROJECT_NO_SHOPHOUSE = 1;
    private static final Integer UPDATED_PROJECT_NO_SHOPHOUSE = 2;

    private static final String DEFAULT_PROJECT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_PROJECT_DESCRIPTION = "BBBBBBBBBB";

    private static final Double DEFAULT_PROJECT_MIN_SELL_PRICE = 0D;
    private static final Double UPDATED_PROJECT_MIN_SELL_PRICE = 1D;

    private static final Double DEFAULT_PROJECT_MAX_SELL_PRICE = 1D;
    private static final Double UPDATED_PROJECT_MAX_SELL_PRICE = 2D;

    private static final PriceUnit DEFAULT_PROJECT_SELL_PRICE_UNIT = PriceUnit.THOUSAND;
    private static final PriceUnit UPDATED_PROJECT_SELL_PRICE_UNIT = PriceUnit.MILLION;

    private static final Double DEFAULT_PROJECT_MIN_RENT_PRICE = 0D;
    private static final Double UPDATED_PROJECT_MIN_RENT_PRICE = 1D;

    private static final Double DEFAULT_PROJECT_MAX_RENT_PRICE = 1D;
    private static final Double UPDATED_PROJECT_MAX_RENT_PRICE = 2D;

    private static final PriceUnit DEFAULT_PROJECT_RENT_PRICE_UNIT = PriceUnit.THOUSAND;
    private static final PriceUnit UPDATED_PROJECT_RENT_PRICE_UNIT = PriceUnit.MILLION;

    private static final LocalDate DEFAULT_PROJECT_STARTED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PROJECT_STARTED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_PROJECT_FINISHING_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PROJECT_FINISHING_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_PROJECT_MIN_APARTMENT_SQUARE = 1;
    private static final Integer UPDATED_PROJECT_MIN_APARTMENT_SQUARE = 2;

    private static final Integer DEFAULT_PROJECT_MAX_APARTMENT_SQUARE = 1;
    private static final Integer UPDATED_PROJECT_MAX_APARTMENT_SQUARE = 2;

    private static final Integer DEFAULT_PROJECT_GREEN_SPACE = 0;
    private static final Integer UPDATED_PROJECT_GREEN_SPACE = 1;

    private static final Integer DEFAULT_PROJECT_BUILDING_DENSITY = 0;
    private static final Integer UPDATED_PROJECT_BUILDING_DENSITY = 1;

    private static final String DEFAULT_PROJECT_DESIGN_COMPANY = "AAAAAAAAAA";
    private static final String UPDATED_PROJECT_DESIGN_COMPANY = "BBBBBBBBBB";

    private static final Boolean DEFAULT_PROJECT_CAR_PARK = false;
    private static final Boolean UPDATED_PROJECT_CAR_PARK = true;

    private static final Boolean DEFAULT_PROJECT_BBQ_COURT = false;
    private static final Boolean UPDATED_PROJECT_BBQ_COURT = true;

    private static final Boolean DEFAULT_PROJECT_ELEVATOR = false;
    private static final Boolean UPDATED_PROJECT_ELEVATOR = true;

    private static final Boolean DEFAULT_PROJECT_SHOPPING_CENTER = false;
    private static final Boolean UPDATED_PROJECT_SHOPPING_CENTER = true;

    private static final Boolean DEFAULT_PROJECT_SWIMMING_POOL = false;
    private static final Boolean UPDATED_PROJECT_SWIMMING_POOL = true;

    private static final Boolean DEFAULT_PROJECT_COMMUNITY_ROOM = false;
    private static final Boolean UPDATED_PROJECT_COMMUNITY_ROOM = true;

    private static final Boolean DEFAULT_PROJECT_GYM = false;
    private static final Boolean UPDATED_PROJECT_GYM = true;

    private static final Boolean DEFAULT_PROJECT_CITY_PARK = false;
    private static final Boolean UPDATED_PROJECT_CITY_PARK = true;

    private static final Boolean DEFAULT_PROJECT_GUARD = false;
    private static final Boolean UPDATED_PROJECT_GUARD = true;

    private static final Boolean DEFAULT_PROJECT_PLAY_GROUND = false;
    private static final Boolean UPDATED_PROJECT_PLAY_GROUND = true;

    private static final Double DEFAULT_LONGITUDE = 1D;
    private static final Double UPDATED_LONGITUDE = 2D;

    private static final Double DEFAULT_LATITUDE = 1D;
    private static final Double UPDATED_LATITUDE = 2D;

    private static final Long DEFAULT_PROJECT_SEEN_COUNT = 1L;
    private static final Long UPDATED_PROJECT_SEEN_COUNT = 2L;

    private static final Boolean DEFAULT_PROJECT_AVAILABLE = false;
    private static final Boolean UPDATED_PROJECT_AVAILABLE = true;

    @Autowired
    private ProjectRepository projectRepository;
    @Mock
    private ProjectRepository projectRepositoryMock;

    @Autowired
    private ProjectMapper projectMapper;
    
    @Mock
    private ProjectService projectServiceMock;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectQueryService projectQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restProjectMockMvc;

    private Project project;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProjectResource projectResource = new ProjectResource(projectService, projectQueryService);
        this.restProjectMockMvc = MockMvcBuilders.standaloneSetup(projectResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Project createEntity(EntityManager em) {
        Project project = new Project()
            .projectName(DEFAULT_PROJECT_NAME)
            .projectAlias(DEFAULT_PROJECT_ALIAS)
            .projectAvatarUrl(DEFAULT_PROJECT_AVATAR_URL)
            .projectDistrict(DEFAULT_PROJECT_DISTRICT)
            .projectProvince(DEFAULT_PROJECT_PROVINCE)
            .residentialAreaId(DEFAULT_RESIDENTIAL_AREA_ID)
            .projectRoad(DEFAULT_PROJECT_ROAD)
            .projectWard(DEFAULT_PROJECT_WARD)
            .projectStatus(DEFAULT_PROJECT_STATUS)
            .projectNoBlocks(DEFAULT_PROJECT_NO_BLOCKS)
            .projectNoFloors(DEFAULT_PROJECT_NO_FLOORS)
            .projectNoApartments(DEFAULT_PROJECT_NO_APARTMENTS)
            .projectNoShophouse(DEFAULT_PROJECT_NO_SHOPHOUSE)
            .projectDescription(DEFAULT_PROJECT_DESCRIPTION)
            .projectMinSellPrice(DEFAULT_PROJECT_MIN_SELL_PRICE)
            .projectMaxSellPrice(DEFAULT_PROJECT_MAX_SELL_PRICE)
            .projectSellPriceUnit(DEFAULT_PROJECT_SELL_PRICE_UNIT)
            .projectMinRentPrice(DEFAULT_PROJECT_MIN_RENT_PRICE)
            .projectMaxRentPrice(DEFAULT_PROJECT_MAX_RENT_PRICE)
            .projectRentPriceUnit(DEFAULT_PROJECT_RENT_PRICE_UNIT)
            .projectStartedDate(DEFAULT_PROJECT_STARTED_DATE)
            .projectFinishingDate(DEFAULT_PROJECT_FINISHING_DATE)
            .projectMinApartmentSquare(DEFAULT_PROJECT_MIN_APARTMENT_SQUARE)
            .projectMaxApartmentSquare(DEFAULT_PROJECT_MAX_APARTMENT_SQUARE)
            .projectGreenSpace(DEFAULT_PROJECT_GREEN_SPACE)
            .projectBuildingDensity(DEFAULT_PROJECT_BUILDING_DENSITY)
            .projectDesignCompany(DEFAULT_PROJECT_DESIGN_COMPANY)
            .projectCarPark(DEFAULT_PROJECT_CAR_PARK)
            .projectBbqCourt(DEFAULT_PROJECT_BBQ_COURT)
            .projectElevator(DEFAULT_PROJECT_ELEVATOR)
            .projectShoppingCenter(DEFAULT_PROJECT_SHOPPING_CENTER)
            .projectSwimmingPool(DEFAULT_PROJECT_SWIMMING_POOL)
            .projectCommunityRoom(DEFAULT_PROJECT_COMMUNITY_ROOM)
            .projectGym(DEFAULT_PROJECT_GYM)
            .projectCityPark(DEFAULT_PROJECT_CITY_PARK)
            .projectGuard(DEFAULT_PROJECT_GUARD)
            .projectPlayGround(DEFAULT_PROJECT_PLAY_GROUND)
            .longitude(DEFAULT_LONGITUDE)
            .latitude(DEFAULT_LATITUDE)
            .projectSeenCount(DEFAULT_PROJECT_SEEN_COUNT)
            .projectAvailable(DEFAULT_PROJECT_AVAILABLE);
        return project;
    }

    @Before
    public void initTest() {
        project = createEntity(em);
    }

    @Test
    @Transactional
    public void createProject() throws Exception {
        int databaseSizeBeforeCreate = projectRepository.findAll().size();

        // Create the Project
        ProjectDTO projectDTO = projectMapper.toDto(project);
        restProjectMockMvc.perform(post("/api/projects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectDTO)))
            .andExpect(status().isCreated());

        // Validate the Project in the database
        List<Project> projectList = projectRepository.findAll();
        assertThat(projectList).hasSize(databaseSizeBeforeCreate + 1);
        Project testProject = projectList.get(projectList.size() - 1);
        assertThat(testProject.getProjectName()).isEqualTo(DEFAULT_PROJECT_NAME);
        assertThat(testProject.getProjectAlias()).isEqualTo(DEFAULT_PROJECT_ALIAS);
        assertThat(testProject.getProjectAvatarUrl()).isEqualTo(DEFAULT_PROJECT_AVATAR_URL);
        assertThat(testProject.getProjectDistrict()).isEqualTo(DEFAULT_PROJECT_DISTRICT);
        assertThat(testProject.getProjectProvince()).isEqualTo(DEFAULT_PROJECT_PROVINCE);
        assertThat(testProject.getResidentialAreaId()).isEqualTo(DEFAULT_RESIDENTIAL_AREA_ID);
        assertThat(testProject.getProjectRoad()).isEqualTo(DEFAULT_PROJECT_ROAD);
        assertThat(testProject.getProjectWard()).isEqualTo(DEFAULT_PROJECT_WARD);
        assertThat(testProject.getProjectStatus()).isEqualTo(DEFAULT_PROJECT_STATUS);
        assertThat(testProject.getProjectNoBlocks()).isEqualTo(DEFAULT_PROJECT_NO_BLOCKS);
        assertThat(testProject.getProjectNoFloors()).isEqualTo(DEFAULT_PROJECT_NO_FLOORS);
        assertThat(testProject.getProjectNoApartments()).isEqualTo(DEFAULT_PROJECT_NO_APARTMENTS);
        assertThat(testProject.getProjectNoShophouse()).isEqualTo(DEFAULT_PROJECT_NO_SHOPHOUSE);
        assertThat(testProject.getProjectDescription()).isEqualTo(DEFAULT_PROJECT_DESCRIPTION);
        assertThat(testProject.getProjectMinSellPrice()).isEqualTo(DEFAULT_PROJECT_MIN_SELL_PRICE);
        assertThat(testProject.getProjectMaxSellPrice()).isEqualTo(DEFAULT_PROJECT_MAX_SELL_PRICE);
        assertThat(testProject.getProjectSellPriceUnit()).isEqualTo(DEFAULT_PROJECT_SELL_PRICE_UNIT);
        assertThat(testProject.getProjectMinRentPrice()).isEqualTo(DEFAULT_PROJECT_MIN_RENT_PRICE);
        assertThat(testProject.getProjectMaxRentPrice()).isEqualTo(DEFAULT_PROJECT_MAX_RENT_PRICE);
        assertThat(testProject.getProjectRentPriceUnit()).isEqualTo(DEFAULT_PROJECT_RENT_PRICE_UNIT);
        assertThat(testProject.getProjectStartedDate()).isEqualTo(DEFAULT_PROJECT_STARTED_DATE);
        assertThat(testProject.getProjectFinishingDate()).isEqualTo(DEFAULT_PROJECT_FINISHING_DATE);
        assertThat(testProject.getProjectMinApartmentSquare()).isEqualTo(DEFAULT_PROJECT_MIN_APARTMENT_SQUARE);
        assertThat(testProject.getProjectMaxApartmentSquare()).isEqualTo(DEFAULT_PROJECT_MAX_APARTMENT_SQUARE);
        assertThat(testProject.getProjectGreenSpace()).isEqualTo(DEFAULT_PROJECT_GREEN_SPACE);
        assertThat(testProject.getProjectBuildingDensity()).isEqualTo(DEFAULT_PROJECT_BUILDING_DENSITY);
        assertThat(testProject.getProjectDesignCompany()).isEqualTo(DEFAULT_PROJECT_DESIGN_COMPANY);
        assertThat(testProject.isProjectCarPark()).isEqualTo(DEFAULT_PROJECT_CAR_PARK);
        assertThat(testProject.isProjectBbqCourt()).isEqualTo(DEFAULT_PROJECT_BBQ_COURT);
        assertThat(testProject.isProjectElevator()).isEqualTo(DEFAULT_PROJECT_ELEVATOR);
        assertThat(testProject.isProjectShoppingCenter()).isEqualTo(DEFAULT_PROJECT_SHOPPING_CENTER);
        assertThat(testProject.isProjectSwimmingPool()).isEqualTo(DEFAULT_PROJECT_SWIMMING_POOL);
        assertThat(testProject.isProjectCommunityRoom()).isEqualTo(DEFAULT_PROJECT_COMMUNITY_ROOM);
        assertThat(testProject.isProjectGym()).isEqualTo(DEFAULT_PROJECT_GYM);
        assertThat(testProject.isProjectCityPark()).isEqualTo(DEFAULT_PROJECT_CITY_PARK);
        assertThat(testProject.isProjectGuard()).isEqualTo(DEFAULT_PROJECT_GUARD);
        assertThat(testProject.isProjectPlayGround()).isEqualTo(DEFAULT_PROJECT_PLAY_GROUND);
        assertThat(testProject.getLongitude()).isEqualTo(DEFAULT_LONGITUDE);
        assertThat(testProject.getLatitude()).isEqualTo(DEFAULT_LATITUDE);
        assertThat(testProject.getProjectSeenCount()).isEqualTo(DEFAULT_PROJECT_SEEN_COUNT);
        assertThat(testProject.isProjectAvailable()).isEqualTo(DEFAULT_PROJECT_AVAILABLE);
    }

    @Test
    @Transactional
    public void createProjectWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = projectRepository.findAll().size();

        // Create the Project with an existing ID
        project.setId(1L);
        ProjectDTO projectDTO = projectMapper.toDto(project);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProjectMockMvc.perform(post("/api/projects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Project in the database
        List<Project> projectList = projectRepository.findAll();
        assertThat(projectList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkProjectNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = projectRepository.findAll().size();
        // set the field null
        project.setProjectName(null);

        // Create the Project, which fails.
        ProjectDTO projectDTO = projectMapper.toDto(project);

        restProjectMockMvc.perform(post("/api/projects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectDTO)))
            .andExpect(status().isBadRequest());

        List<Project> projectList = projectRepository.findAll();
        assertThat(projectList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkProjectNoBlocksIsRequired() throws Exception {
        int databaseSizeBeforeTest = projectRepository.findAll().size();
        // set the field null
        project.setProjectNoBlocks(null);

        // Create the Project, which fails.
        ProjectDTO projectDTO = projectMapper.toDto(project);

        restProjectMockMvc.perform(post("/api/projects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectDTO)))
            .andExpect(status().isBadRequest());

        List<Project> projectList = projectRepository.findAll();
        assertThat(projectList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkProjectNoFloorsIsRequired() throws Exception {
        int databaseSizeBeforeTest = projectRepository.findAll().size();
        // set the field null
        project.setProjectNoFloors(null);

        // Create the Project, which fails.
        ProjectDTO projectDTO = projectMapper.toDto(project);

        restProjectMockMvc.perform(post("/api/projects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectDTO)))
            .andExpect(status().isBadRequest());

        List<Project> projectList = projectRepository.findAll();
        assertThat(projectList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkProjectNoApartmentsIsRequired() throws Exception {
        int databaseSizeBeforeTest = projectRepository.findAll().size();
        // set the field null
        project.setProjectNoApartments(null);

        // Create the Project, which fails.
        ProjectDTO projectDTO = projectMapper.toDto(project);

        restProjectMockMvc.perform(post("/api/projects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectDTO)))
            .andExpect(status().isBadRequest());

        List<Project> projectList = projectRepository.findAll();
        assertThat(projectList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllProjects() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList
        restProjectMockMvc.perform(get("/api/projects?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(project.getId().intValue())))
            .andExpect(jsonPath("$.[*].projectName").value(hasItem(DEFAULT_PROJECT_NAME.toString())))
            .andExpect(jsonPath("$.[*].projectAlias").value(hasItem(DEFAULT_PROJECT_ALIAS.toString())))
            .andExpect(jsonPath("$.[*].projectAvatarUrl").value(hasItem(DEFAULT_PROJECT_AVATAR_URL.toString())))
            .andExpect(jsonPath("$.[*].projectDistrict").value(hasItem(DEFAULT_PROJECT_DISTRICT.toString())))
            .andExpect(jsonPath("$.[*].projectProvince").value(hasItem(DEFAULT_PROJECT_PROVINCE.toString())))
            .andExpect(jsonPath("$.[*].residentialAreaId").value(hasItem(DEFAULT_RESIDENTIAL_AREA_ID.intValue())))
            .andExpect(jsonPath("$.[*].projectRoad").value(hasItem(DEFAULT_PROJECT_ROAD.toString())))
            .andExpect(jsonPath("$.[*].projectWard").value(hasItem(DEFAULT_PROJECT_WARD.toString())))
            .andExpect(jsonPath("$.[*].projectStatus").value(hasItem(DEFAULT_PROJECT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].projectNoBlocks").value(hasItem(DEFAULT_PROJECT_NO_BLOCKS)))
            .andExpect(jsonPath("$.[*].projectNoFloors").value(hasItem(DEFAULT_PROJECT_NO_FLOORS)))
            .andExpect(jsonPath("$.[*].projectNoApartments").value(hasItem(DEFAULT_PROJECT_NO_APARTMENTS)))
            .andExpect(jsonPath("$.[*].projectNoShophouse").value(hasItem(DEFAULT_PROJECT_NO_SHOPHOUSE)))
            .andExpect(jsonPath("$.[*].projectDescription").value(hasItem(DEFAULT_PROJECT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].projectMinSellPrice").value(hasItem(DEFAULT_PROJECT_MIN_SELL_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].projectMaxSellPrice").value(hasItem(DEFAULT_PROJECT_MAX_SELL_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].projectSellPriceUnit").value(hasItem(DEFAULT_PROJECT_SELL_PRICE_UNIT.toString())))
            .andExpect(jsonPath("$.[*].projectMinRentPrice").value(hasItem(DEFAULT_PROJECT_MIN_RENT_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].projectMaxRentPrice").value(hasItem(DEFAULT_PROJECT_MAX_RENT_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].projectRentPriceUnit").value(hasItem(DEFAULT_PROJECT_RENT_PRICE_UNIT.toString())))
            .andExpect(jsonPath("$.[*].projectStartedDate").value(hasItem(DEFAULT_PROJECT_STARTED_DATE.toString())))
            .andExpect(jsonPath("$.[*].projectFinishingDate").value(hasItem(DEFAULT_PROJECT_FINISHING_DATE.toString())))
            .andExpect(jsonPath("$.[*].projectMinApartmentSquare").value(hasItem(DEFAULT_PROJECT_MIN_APARTMENT_SQUARE)))
            .andExpect(jsonPath("$.[*].projectMaxApartmentSquare").value(hasItem(DEFAULT_PROJECT_MAX_APARTMENT_SQUARE)))
            .andExpect(jsonPath("$.[*].projectGreenSpace").value(hasItem(DEFAULT_PROJECT_GREEN_SPACE)))
            .andExpect(jsonPath("$.[*].projectBuildingDensity").value(hasItem(DEFAULT_PROJECT_BUILDING_DENSITY)))
            .andExpect(jsonPath("$.[*].projectDesignCompany").value(hasItem(DEFAULT_PROJECT_DESIGN_COMPANY.toString())))
            .andExpect(jsonPath("$.[*].projectCarPark").value(hasItem(DEFAULT_PROJECT_CAR_PARK.booleanValue())))
            .andExpect(jsonPath("$.[*].projectBbqCourt").value(hasItem(DEFAULT_PROJECT_BBQ_COURT.booleanValue())))
            .andExpect(jsonPath("$.[*].projectElevator").value(hasItem(DEFAULT_PROJECT_ELEVATOR.booleanValue())))
            .andExpect(jsonPath("$.[*].projectShoppingCenter").value(hasItem(DEFAULT_PROJECT_SHOPPING_CENTER.booleanValue())))
            .andExpect(jsonPath("$.[*].projectSwimmingPool").value(hasItem(DEFAULT_PROJECT_SWIMMING_POOL.booleanValue())))
            .andExpect(jsonPath("$.[*].projectCommunityRoom").value(hasItem(DEFAULT_PROJECT_COMMUNITY_ROOM.booleanValue())))
            .andExpect(jsonPath("$.[*].projectGym").value(hasItem(DEFAULT_PROJECT_GYM.booleanValue())))
            .andExpect(jsonPath("$.[*].projectCityPark").value(hasItem(DEFAULT_PROJECT_CITY_PARK.booleanValue())))
            .andExpect(jsonPath("$.[*].projectGuard").value(hasItem(DEFAULT_PROJECT_GUARD.booleanValue())))
            .andExpect(jsonPath("$.[*].projectPlayGround").value(hasItem(DEFAULT_PROJECT_PLAY_GROUND.booleanValue())))
            .andExpect(jsonPath("$.[*].longitude").value(hasItem(DEFAULT_LONGITUDE.doubleValue())))
            .andExpect(jsonPath("$.[*].latitude").value(hasItem(DEFAULT_LATITUDE.doubleValue())))
            .andExpect(jsonPath("$.[*].projectSeenCount").value(hasItem(DEFAULT_PROJECT_SEEN_COUNT.intValue())))
            .andExpect(jsonPath("$.[*].projectAvailable").value(hasItem(DEFAULT_PROJECT_AVAILABLE.booleanValue())));
    }
    
    public void getAllProjectsWithEagerRelationshipsIsEnabled() throws Exception {
        ProjectResource projectResource = new ProjectResource(projectServiceMock, projectQueryService);
        when(projectServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restProjectMockMvc = MockMvcBuilders.standaloneSetup(projectResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restProjectMockMvc.perform(get("/api/projects?eagerload=true"))
        .andExpect(status().isOk());

        verify(projectServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    public void getAllProjectsWithEagerRelationshipsIsNotEnabled() throws Exception {
        ProjectResource projectResource = new ProjectResource(projectServiceMock, projectQueryService);
            when(projectServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restProjectMockMvc = MockMvcBuilders.standaloneSetup(projectResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restProjectMockMvc.perform(get("/api/projects?eagerload=true"))
        .andExpect(status().isOk());

            verify(projectServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getProject() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get the project
        restProjectMockMvc.perform(get("/api/projects/{id}", project.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(project.getId().intValue()))
            .andExpect(jsonPath("$.projectName").value(DEFAULT_PROJECT_NAME.toString()))
            .andExpect(jsonPath("$.projectAlias").value(DEFAULT_PROJECT_ALIAS.toString()))
            .andExpect(jsonPath("$.projectAvatarUrl").value(DEFAULT_PROJECT_AVATAR_URL.toString()))
            .andExpect(jsonPath("$.projectDistrict").value(DEFAULT_PROJECT_DISTRICT.toString()))
            .andExpect(jsonPath("$.projectProvince").value(DEFAULT_PROJECT_PROVINCE.toString()))
            .andExpect(jsonPath("$.residentialAreaId").value(DEFAULT_RESIDENTIAL_AREA_ID.intValue()))
            .andExpect(jsonPath("$.projectRoad").value(DEFAULT_PROJECT_ROAD.toString()))
            .andExpect(jsonPath("$.projectWard").value(DEFAULT_PROJECT_WARD.toString()))
            .andExpect(jsonPath("$.projectStatus").value(DEFAULT_PROJECT_STATUS.toString()))
            .andExpect(jsonPath("$.projectNoBlocks").value(DEFAULT_PROJECT_NO_BLOCKS))
            .andExpect(jsonPath("$.projectNoFloors").value(DEFAULT_PROJECT_NO_FLOORS))
            .andExpect(jsonPath("$.projectNoApartments").value(DEFAULT_PROJECT_NO_APARTMENTS))
            .andExpect(jsonPath("$.projectNoShophouse").value(DEFAULT_PROJECT_NO_SHOPHOUSE))
            .andExpect(jsonPath("$.projectDescription").value(DEFAULT_PROJECT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.projectMinSellPrice").value(DEFAULT_PROJECT_MIN_SELL_PRICE.doubleValue()))
            .andExpect(jsonPath("$.projectMaxSellPrice").value(DEFAULT_PROJECT_MAX_SELL_PRICE.doubleValue()))
            .andExpect(jsonPath("$.projectSellPriceUnit").value(DEFAULT_PROJECT_SELL_PRICE_UNIT.toString()))
            .andExpect(jsonPath("$.projectMinRentPrice").value(DEFAULT_PROJECT_MIN_RENT_PRICE.doubleValue()))
            .andExpect(jsonPath("$.projectMaxRentPrice").value(DEFAULT_PROJECT_MAX_RENT_PRICE.doubleValue()))
            .andExpect(jsonPath("$.projectRentPriceUnit").value(DEFAULT_PROJECT_RENT_PRICE_UNIT.toString()))
            .andExpect(jsonPath("$.projectStartedDate").value(DEFAULT_PROJECT_STARTED_DATE.toString()))
            .andExpect(jsonPath("$.projectFinishingDate").value(DEFAULT_PROJECT_FINISHING_DATE.toString()))
            .andExpect(jsonPath("$.projectMinApartmentSquare").value(DEFAULT_PROJECT_MIN_APARTMENT_SQUARE))
            .andExpect(jsonPath("$.projectMaxApartmentSquare").value(DEFAULT_PROJECT_MAX_APARTMENT_SQUARE))
            .andExpect(jsonPath("$.projectGreenSpace").value(DEFAULT_PROJECT_GREEN_SPACE))
            .andExpect(jsonPath("$.projectBuildingDensity").value(DEFAULT_PROJECT_BUILDING_DENSITY))
            .andExpect(jsonPath("$.projectDesignCompany").value(DEFAULT_PROJECT_DESIGN_COMPANY.toString()))
            .andExpect(jsonPath("$.projectCarPark").value(DEFAULT_PROJECT_CAR_PARK.booleanValue()))
            .andExpect(jsonPath("$.projectBbqCourt").value(DEFAULT_PROJECT_BBQ_COURT.booleanValue()))
            .andExpect(jsonPath("$.projectElevator").value(DEFAULT_PROJECT_ELEVATOR.booleanValue()))
            .andExpect(jsonPath("$.projectShoppingCenter").value(DEFAULT_PROJECT_SHOPPING_CENTER.booleanValue()))
            .andExpect(jsonPath("$.projectSwimmingPool").value(DEFAULT_PROJECT_SWIMMING_POOL.booleanValue()))
            .andExpect(jsonPath("$.projectCommunityRoom").value(DEFAULT_PROJECT_COMMUNITY_ROOM.booleanValue()))
            .andExpect(jsonPath("$.projectGym").value(DEFAULT_PROJECT_GYM.booleanValue()))
            .andExpect(jsonPath("$.projectCityPark").value(DEFAULT_PROJECT_CITY_PARK.booleanValue()))
            .andExpect(jsonPath("$.projectGuard").value(DEFAULT_PROJECT_GUARD.booleanValue()))
            .andExpect(jsonPath("$.projectPlayGround").value(DEFAULT_PROJECT_PLAY_GROUND.booleanValue()))
            .andExpect(jsonPath("$.longitude").value(DEFAULT_LONGITUDE.doubleValue()))
            .andExpect(jsonPath("$.latitude").value(DEFAULT_LATITUDE.doubleValue()))
            .andExpect(jsonPath("$.projectSeenCount").value(DEFAULT_PROJECT_SEEN_COUNT.intValue()))
            .andExpect(jsonPath("$.projectAvailable").value(DEFAULT_PROJECT_AVAILABLE.booleanValue()));
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectNameIsEqualToSomething() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectName equals to DEFAULT_PROJECT_NAME
        defaultProjectShouldBeFound("projectName.equals=" + DEFAULT_PROJECT_NAME);

        // Get all the projectList where projectName equals to UPDATED_PROJECT_NAME
        defaultProjectShouldNotBeFound("projectName.equals=" + UPDATED_PROJECT_NAME);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectNameIsInShouldWork() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectName in DEFAULT_PROJECT_NAME or UPDATED_PROJECT_NAME
        defaultProjectShouldBeFound("projectName.in=" + DEFAULT_PROJECT_NAME + "," + UPDATED_PROJECT_NAME);

        // Get all the projectList where projectName equals to UPDATED_PROJECT_NAME
        defaultProjectShouldNotBeFound("projectName.in=" + UPDATED_PROJECT_NAME);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectName is not null
        defaultProjectShouldBeFound("projectName.specified=true");

        // Get all the projectList where projectName is null
        defaultProjectShouldNotBeFound("projectName.specified=false");
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectAliasIsEqualToSomething() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectAlias equals to DEFAULT_PROJECT_ALIAS
        defaultProjectShouldBeFound("projectAlias.equals=" + DEFAULT_PROJECT_ALIAS);

        // Get all the projectList where projectAlias equals to UPDATED_PROJECT_ALIAS
        defaultProjectShouldNotBeFound("projectAlias.equals=" + UPDATED_PROJECT_ALIAS);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectAliasIsInShouldWork() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectAlias in DEFAULT_PROJECT_ALIAS or UPDATED_PROJECT_ALIAS
        defaultProjectShouldBeFound("projectAlias.in=" + DEFAULT_PROJECT_ALIAS + "," + UPDATED_PROJECT_ALIAS);

        // Get all the projectList where projectAlias equals to UPDATED_PROJECT_ALIAS
        defaultProjectShouldNotBeFound("projectAlias.in=" + UPDATED_PROJECT_ALIAS);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectAliasIsNullOrNotNull() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectAlias is not null
        defaultProjectShouldBeFound("projectAlias.specified=true");

        // Get all the projectList where projectAlias is null
        defaultProjectShouldNotBeFound("projectAlias.specified=false");
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectAvatarUrlIsEqualToSomething() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectAvatarUrl equals to DEFAULT_PROJECT_AVATAR_URL
        defaultProjectShouldBeFound("projectAvatarUrl.equals=" + DEFAULT_PROJECT_AVATAR_URL);

        // Get all the projectList where projectAvatarUrl equals to UPDATED_PROJECT_AVATAR_URL
        defaultProjectShouldNotBeFound("projectAvatarUrl.equals=" + UPDATED_PROJECT_AVATAR_URL);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectAvatarUrlIsInShouldWork() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectAvatarUrl in DEFAULT_PROJECT_AVATAR_URL or UPDATED_PROJECT_AVATAR_URL
        defaultProjectShouldBeFound("projectAvatarUrl.in=" + DEFAULT_PROJECT_AVATAR_URL + "," + UPDATED_PROJECT_AVATAR_URL);

        // Get all the projectList where projectAvatarUrl equals to UPDATED_PROJECT_AVATAR_URL
        defaultProjectShouldNotBeFound("projectAvatarUrl.in=" + UPDATED_PROJECT_AVATAR_URL);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectAvatarUrlIsNullOrNotNull() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectAvatarUrl is not null
        defaultProjectShouldBeFound("projectAvatarUrl.specified=true");

        // Get all the projectList where projectAvatarUrl is null
        defaultProjectShouldNotBeFound("projectAvatarUrl.specified=false");
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectDistrictIsEqualToSomething() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectDistrict equals to DEFAULT_PROJECT_DISTRICT
        defaultProjectShouldBeFound("projectDistrict.equals=" + DEFAULT_PROJECT_DISTRICT);

        // Get all the projectList where projectDistrict equals to UPDATED_PROJECT_DISTRICT
        defaultProjectShouldNotBeFound("projectDistrict.equals=" + UPDATED_PROJECT_DISTRICT);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectDistrictIsInShouldWork() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectDistrict in DEFAULT_PROJECT_DISTRICT or UPDATED_PROJECT_DISTRICT
        defaultProjectShouldBeFound("projectDistrict.in=" + DEFAULT_PROJECT_DISTRICT + "," + UPDATED_PROJECT_DISTRICT);

        // Get all the projectList where projectDistrict equals to UPDATED_PROJECT_DISTRICT
        defaultProjectShouldNotBeFound("projectDistrict.in=" + UPDATED_PROJECT_DISTRICT);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectDistrictIsNullOrNotNull() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectDistrict is not null
        defaultProjectShouldBeFound("projectDistrict.specified=true");

        // Get all the projectList where projectDistrict is null
        defaultProjectShouldNotBeFound("projectDistrict.specified=false");
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectProvinceIsEqualToSomething() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectProvince equals to DEFAULT_PROJECT_PROVINCE
        defaultProjectShouldBeFound("projectProvince.equals=" + DEFAULT_PROJECT_PROVINCE);

        // Get all the projectList where projectProvince equals to UPDATED_PROJECT_PROVINCE
        defaultProjectShouldNotBeFound("projectProvince.equals=" + UPDATED_PROJECT_PROVINCE);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectProvinceIsInShouldWork() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectProvince in DEFAULT_PROJECT_PROVINCE or UPDATED_PROJECT_PROVINCE
        defaultProjectShouldBeFound("projectProvince.in=" + DEFAULT_PROJECT_PROVINCE + "," + UPDATED_PROJECT_PROVINCE);

        // Get all the projectList where projectProvince equals to UPDATED_PROJECT_PROVINCE
        defaultProjectShouldNotBeFound("projectProvince.in=" + UPDATED_PROJECT_PROVINCE);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectProvinceIsNullOrNotNull() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectProvince is not null
        defaultProjectShouldBeFound("projectProvince.specified=true");

        // Get all the projectList where projectProvince is null
        defaultProjectShouldNotBeFound("projectProvince.specified=false");
    }

    @Test
    @Transactional
    public void getAllProjectsByResidentialAreaIdIsEqualToSomething() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where residentialAreaId equals to DEFAULT_RESIDENTIAL_AREA_ID
        defaultProjectShouldBeFound("residentialAreaId.equals=" + DEFAULT_RESIDENTIAL_AREA_ID);

        // Get all the projectList where residentialAreaId equals to UPDATED_RESIDENTIAL_AREA_ID
        defaultProjectShouldNotBeFound("residentialAreaId.equals=" + UPDATED_RESIDENTIAL_AREA_ID);
    }

    @Test
    @Transactional
    public void getAllProjectsByResidentialAreaIdIsInShouldWork() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where residentialAreaId in DEFAULT_RESIDENTIAL_AREA_ID or UPDATED_RESIDENTIAL_AREA_ID
        defaultProjectShouldBeFound("residentialAreaId.in=" + DEFAULT_RESIDENTIAL_AREA_ID + "," + UPDATED_RESIDENTIAL_AREA_ID);

        // Get all the projectList where residentialAreaId equals to UPDATED_RESIDENTIAL_AREA_ID
        defaultProjectShouldNotBeFound("residentialAreaId.in=" + UPDATED_RESIDENTIAL_AREA_ID);
    }

    @Test
    @Transactional
    public void getAllProjectsByResidentialAreaIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where residentialAreaId is not null
        defaultProjectShouldBeFound("residentialAreaId.specified=true");

        // Get all the projectList where residentialAreaId is null
        defaultProjectShouldNotBeFound("residentialAreaId.specified=false");
    }

    @Test
    @Transactional
    public void getAllProjectsByResidentialAreaIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where residentialAreaId greater than or equals to DEFAULT_RESIDENTIAL_AREA_ID
        defaultProjectShouldBeFound("residentialAreaId.greaterOrEqualThan=" + DEFAULT_RESIDENTIAL_AREA_ID);

        // Get all the projectList where residentialAreaId greater than or equals to UPDATED_RESIDENTIAL_AREA_ID
        defaultProjectShouldNotBeFound("residentialAreaId.greaterOrEqualThan=" + UPDATED_RESIDENTIAL_AREA_ID);
    }

    @Test
    @Transactional
    public void getAllProjectsByResidentialAreaIdIsLessThanSomething() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where residentialAreaId less than or equals to DEFAULT_RESIDENTIAL_AREA_ID
        defaultProjectShouldNotBeFound("residentialAreaId.lessThan=" + DEFAULT_RESIDENTIAL_AREA_ID);

        // Get all the projectList where residentialAreaId less than or equals to UPDATED_RESIDENTIAL_AREA_ID
        defaultProjectShouldBeFound("residentialAreaId.lessThan=" + UPDATED_RESIDENTIAL_AREA_ID);
    }


    @Test
    @Transactional
    public void getAllProjectsByProjectRoadIsEqualToSomething() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectRoad equals to DEFAULT_PROJECT_ROAD
        defaultProjectShouldBeFound("projectRoad.equals=" + DEFAULT_PROJECT_ROAD);

        // Get all the projectList where projectRoad equals to UPDATED_PROJECT_ROAD
        defaultProjectShouldNotBeFound("projectRoad.equals=" + UPDATED_PROJECT_ROAD);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectRoadIsInShouldWork() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectRoad in DEFAULT_PROJECT_ROAD or UPDATED_PROJECT_ROAD
        defaultProjectShouldBeFound("projectRoad.in=" + DEFAULT_PROJECT_ROAD + "," + UPDATED_PROJECT_ROAD);

        // Get all the projectList where projectRoad equals to UPDATED_PROJECT_ROAD
        defaultProjectShouldNotBeFound("projectRoad.in=" + UPDATED_PROJECT_ROAD);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectRoadIsNullOrNotNull() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectRoad is not null
        defaultProjectShouldBeFound("projectRoad.specified=true");

        // Get all the projectList where projectRoad is null
        defaultProjectShouldNotBeFound("projectRoad.specified=false");
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectWardIsEqualToSomething() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectWard equals to DEFAULT_PROJECT_WARD
        defaultProjectShouldBeFound("projectWard.equals=" + DEFAULT_PROJECT_WARD);

        // Get all the projectList where projectWard equals to UPDATED_PROJECT_WARD
        defaultProjectShouldNotBeFound("projectWard.equals=" + UPDATED_PROJECT_WARD);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectWardIsInShouldWork() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectWard in DEFAULT_PROJECT_WARD or UPDATED_PROJECT_WARD
        defaultProjectShouldBeFound("projectWard.in=" + DEFAULT_PROJECT_WARD + "," + UPDATED_PROJECT_WARD);

        // Get all the projectList where projectWard equals to UPDATED_PROJECT_WARD
        defaultProjectShouldNotBeFound("projectWard.in=" + UPDATED_PROJECT_WARD);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectWardIsNullOrNotNull() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectWard is not null
        defaultProjectShouldBeFound("projectWard.specified=true");

        // Get all the projectList where projectWard is null
        defaultProjectShouldNotBeFound("projectWard.specified=false");
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectStatus equals to DEFAULT_PROJECT_STATUS
        defaultProjectShouldBeFound("projectStatus.equals=" + DEFAULT_PROJECT_STATUS);

        // Get all the projectList where projectStatus equals to UPDATED_PROJECT_STATUS
        defaultProjectShouldNotBeFound("projectStatus.equals=" + UPDATED_PROJECT_STATUS);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectStatusIsInShouldWork() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectStatus in DEFAULT_PROJECT_STATUS or UPDATED_PROJECT_STATUS
        defaultProjectShouldBeFound("projectStatus.in=" + DEFAULT_PROJECT_STATUS + "," + UPDATED_PROJECT_STATUS);

        // Get all the projectList where projectStatus equals to UPDATED_PROJECT_STATUS
        defaultProjectShouldNotBeFound("projectStatus.in=" + UPDATED_PROJECT_STATUS);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectStatus is not null
        defaultProjectShouldBeFound("projectStatus.specified=true");

        // Get all the projectList where projectStatus is null
        defaultProjectShouldNotBeFound("projectStatus.specified=false");
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectNoBlocksIsEqualToSomething() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectNoBlocks equals to DEFAULT_PROJECT_NO_BLOCKS
        defaultProjectShouldBeFound("projectNoBlocks.equals=" + DEFAULT_PROJECT_NO_BLOCKS);

        // Get all the projectList where projectNoBlocks equals to UPDATED_PROJECT_NO_BLOCKS
        defaultProjectShouldNotBeFound("projectNoBlocks.equals=" + UPDATED_PROJECT_NO_BLOCKS);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectNoBlocksIsInShouldWork() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectNoBlocks in DEFAULT_PROJECT_NO_BLOCKS or UPDATED_PROJECT_NO_BLOCKS
        defaultProjectShouldBeFound("projectNoBlocks.in=" + DEFAULT_PROJECT_NO_BLOCKS + "," + UPDATED_PROJECT_NO_BLOCKS);

        // Get all the projectList where projectNoBlocks equals to UPDATED_PROJECT_NO_BLOCKS
        defaultProjectShouldNotBeFound("projectNoBlocks.in=" + UPDATED_PROJECT_NO_BLOCKS);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectNoBlocksIsNullOrNotNull() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectNoBlocks is not null
        defaultProjectShouldBeFound("projectNoBlocks.specified=true");

        // Get all the projectList where projectNoBlocks is null
        defaultProjectShouldNotBeFound("projectNoBlocks.specified=false");
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectNoBlocksIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectNoBlocks greater than or equals to DEFAULT_PROJECT_NO_BLOCKS
        defaultProjectShouldBeFound("projectNoBlocks.greaterOrEqualThan=" + DEFAULT_PROJECT_NO_BLOCKS);

        // Get all the projectList where projectNoBlocks greater than or equals to UPDATED_PROJECT_NO_BLOCKS
        defaultProjectShouldNotBeFound("projectNoBlocks.greaterOrEqualThan=" + UPDATED_PROJECT_NO_BLOCKS);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectNoBlocksIsLessThanSomething() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectNoBlocks less than or equals to DEFAULT_PROJECT_NO_BLOCKS
        defaultProjectShouldNotBeFound("projectNoBlocks.lessThan=" + DEFAULT_PROJECT_NO_BLOCKS);

        // Get all the projectList where projectNoBlocks less than or equals to UPDATED_PROJECT_NO_BLOCKS
        defaultProjectShouldBeFound("projectNoBlocks.lessThan=" + UPDATED_PROJECT_NO_BLOCKS);
    }


    @Test
    @Transactional
    public void getAllProjectsByProjectNoFloorsIsEqualToSomething() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectNoFloors equals to DEFAULT_PROJECT_NO_FLOORS
        defaultProjectShouldBeFound("projectNoFloors.equals=" + DEFAULT_PROJECT_NO_FLOORS);

        // Get all the projectList where projectNoFloors equals to UPDATED_PROJECT_NO_FLOORS
        defaultProjectShouldNotBeFound("projectNoFloors.equals=" + UPDATED_PROJECT_NO_FLOORS);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectNoFloorsIsInShouldWork() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectNoFloors in DEFAULT_PROJECT_NO_FLOORS or UPDATED_PROJECT_NO_FLOORS
        defaultProjectShouldBeFound("projectNoFloors.in=" + DEFAULT_PROJECT_NO_FLOORS + "," + UPDATED_PROJECT_NO_FLOORS);

        // Get all the projectList where projectNoFloors equals to UPDATED_PROJECT_NO_FLOORS
        defaultProjectShouldNotBeFound("projectNoFloors.in=" + UPDATED_PROJECT_NO_FLOORS);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectNoFloorsIsNullOrNotNull() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectNoFloors is not null
        defaultProjectShouldBeFound("projectNoFloors.specified=true");

        // Get all the projectList where projectNoFloors is null
        defaultProjectShouldNotBeFound("projectNoFloors.specified=false");
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectNoFloorsIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectNoFloors greater than or equals to DEFAULT_PROJECT_NO_FLOORS
        defaultProjectShouldBeFound("projectNoFloors.greaterOrEqualThan=" + DEFAULT_PROJECT_NO_FLOORS);

        // Get all the projectList where projectNoFloors greater than or equals to UPDATED_PROJECT_NO_FLOORS
        defaultProjectShouldNotBeFound("projectNoFloors.greaterOrEqualThan=" + UPDATED_PROJECT_NO_FLOORS);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectNoFloorsIsLessThanSomething() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectNoFloors less than or equals to DEFAULT_PROJECT_NO_FLOORS
        defaultProjectShouldNotBeFound("projectNoFloors.lessThan=" + DEFAULT_PROJECT_NO_FLOORS);

        // Get all the projectList where projectNoFloors less than or equals to UPDATED_PROJECT_NO_FLOORS
        defaultProjectShouldBeFound("projectNoFloors.lessThan=" + UPDATED_PROJECT_NO_FLOORS);
    }


    @Test
    @Transactional
    public void getAllProjectsByProjectNoApartmentsIsEqualToSomething() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectNoApartments equals to DEFAULT_PROJECT_NO_APARTMENTS
        defaultProjectShouldBeFound("projectNoApartments.equals=" + DEFAULT_PROJECT_NO_APARTMENTS);

        // Get all the projectList where projectNoApartments equals to UPDATED_PROJECT_NO_APARTMENTS
        defaultProjectShouldNotBeFound("projectNoApartments.equals=" + UPDATED_PROJECT_NO_APARTMENTS);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectNoApartmentsIsInShouldWork() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectNoApartments in DEFAULT_PROJECT_NO_APARTMENTS or UPDATED_PROJECT_NO_APARTMENTS
        defaultProjectShouldBeFound("projectNoApartments.in=" + DEFAULT_PROJECT_NO_APARTMENTS + "," + UPDATED_PROJECT_NO_APARTMENTS);

        // Get all the projectList where projectNoApartments equals to UPDATED_PROJECT_NO_APARTMENTS
        defaultProjectShouldNotBeFound("projectNoApartments.in=" + UPDATED_PROJECT_NO_APARTMENTS);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectNoApartmentsIsNullOrNotNull() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectNoApartments is not null
        defaultProjectShouldBeFound("projectNoApartments.specified=true");

        // Get all the projectList where projectNoApartments is null
        defaultProjectShouldNotBeFound("projectNoApartments.specified=false");
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectNoApartmentsIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectNoApartments greater than or equals to DEFAULT_PROJECT_NO_APARTMENTS
        defaultProjectShouldBeFound("projectNoApartments.greaterOrEqualThan=" + DEFAULT_PROJECT_NO_APARTMENTS);

        // Get all the projectList where projectNoApartments greater than or equals to UPDATED_PROJECT_NO_APARTMENTS
        defaultProjectShouldNotBeFound("projectNoApartments.greaterOrEqualThan=" + UPDATED_PROJECT_NO_APARTMENTS);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectNoApartmentsIsLessThanSomething() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectNoApartments less than or equals to DEFAULT_PROJECT_NO_APARTMENTS
        defaultProjectShouldNotBeFound("projectNoApartments.lessThan=" + DEFAULT_PROJECT_NO_APARTMENTS);

        // Get all the projectList where projectNoApartments less than or equals to UPDATED_PROJECT_NO_APARTMENTS
        defaultProjectShouldBeFound("projectNoApartments.lessThan=" + UPDATED_PROJECT_NO_APARTMENTS);
    }


    @Test
    @Transactional
    public void getAllProjectsByProjectNoShophouseIsEqualToSomething() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectNoShophouse equals to DEFAULT_PROJECT_NO_SHOPHOUSE
        defaultProjectShouldBeFound("projectNoShophouse.equals=" + DEFAULT_PROJECT_NO_SHOPHOUSE);

        // Get all the projectList where projectNoShophouse equals to UPDATED_PROJECT_NO_SHOPHOUSE
        defaultProjectShouldNotBeFound("projectNoShophouse.equals=" + UPDATED_PROJECT_NO_SHOPHOUSE);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectNoShophouseIsInShouldWork() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectNoShophouse in DEFAULT_PROJECT_NO_SHOPHOUSE or UPDATED_PROJECT_NO_SHOPHOUSE
        defaultProjectShouldBeFound("projectNoShophouse.in=" + DEFAULT_PROJECT_NO_SHOPHOUSE + "," + UPDATED_PROJECT_NO_SHOPHOUSE);

        // Get all the projectList where projectNoShophouse equals to UPDATED_PROJECT_NO_SHOPHOUSE
        defaultProjectShouldNotBeFound("projectNoShophouse.in=" + UPDATED_PROJECT_NO_SHOPHOUSE);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectNoShophouseIsNullOrNotNull() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectNoShophouse is not null
        defaultProjectShouldBeFound("projectNoShophouse.specified=true");

        // Get all the projectList where projectNoShophouse is null
        defaultProjectShouldNotBeFound("projectNoShophouse.specified=false");
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectNoShophouseIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectNoShophouse greater than or equals to DEFAULT_PROJECT_NO_SHOPHOUSE
        defaultProjectShouldBeFound("projectNoShophouse.greaterOrEqualThan=" + DEFAULT_PROJECT_NO_SHOPHOUSE);

        // Get all the projectList where projectNoShophouse greater than or equals to UPDATED_PROJECT_NO_SHOPHOUSE
        defaultProjectShouldNotBeFound("projectNoShophouse.greaterOrEqualThan=" + UPDATED_PROJECT_NO_SHOPHOUSE);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectNoShophouseIsLessThanSomething() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectNoShophouse less than or equals to DEFAULT_PROJECT_NO_SHOPHOUSE
        defaultProjectShouldNotBeFound("projectNoShophouse.lessThan=" + DEFAULT_PROJECT_NO_SHOPHOUSE);

        // Get all the projectList where projectNoShophouse less than or equals to UPDATED_PROJECT_NO_SHOPHOUSE
        defaultProjectShouldBeFound("projectNoShophouse.lessThan=" + UPDATED_PROJECT_NO_SHOPHOUSE);
    }


    @Test
    @Transactional
    public void getAllProjectsByProjectMinSellPriceIsEqualToSomething() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectMinSellPrice equals to DEFAULT_PROJECT_MIN_SELL_PRICE
        defaultProjectShouldBeFound("projectMinSellPrice.equals=" + DEFAULT_PROJECT_MIN_SELL_PRICE);

        // Get all the projectList where projectMinSellPrice equals to UPDATED_PROJECT_MIN_SELL_PRICE
        defaultProjectShouldNotBeFound("projectMinSellPrice.equals=" + UPDATED_PROJECT_MIN_SELL_PRICE);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectMinSellPriceIsInShouldWork() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectMinSellPrice in DEFAULT_PROJECT_MIN_SELL_PRICE or UPDATED_PROJECT_MIN_SELL_PRICE
        defaultProjectShouldBeFound("projectMinSellPrice.in=" + DEFAULT_PROJECT_MIN_SELL_PRICE + "," + UPDATED_PROJECT_MIN_SELL_PRICE);

        // Get all the projectList where projectMinSellPrice equals to UPDATED_PROJECT_MIN_SELL_PRICE
        defaultProjectShouldNotBeFound("projectMinSellPrice.in=" + UPDATED_PROJECT_MIN_SELL_PRICE);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectMinSellPriceIsNullOrNotNull() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectMinSellPrice is not null
        defaultProjectShouldBeFound("projectMinSellPrice.specified=true");

        // Get all the projectList where projectMinSellPrice is null
        defaultProjectShouldNotBeFound("projectMinSellPrice.specified=false");
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectMaxSellPriceIsEqualToSomething() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectMaxSellPrice equals to DEFAULT_PROJECT_MAX_SELL_PRICE
        defaultProjectShouldBeFound("projectMaxSellPrice.equals=" + DEFAULT_PROJECT_MAX_SELL_PRICE);

        // Get all the projectList where projectMaxSellPrice equals to UPDATED_PROJECT_MAX_SELL_PRICE
        defaultProjectShouldNotBeFound("projectMaxSellPrice.equals=" + UPDATED_PROJECT_MAX_SELL_PRICE);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectMaxSellPriceIsInShouldWork() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectMaxSellPrice in DEFAULT_PROJECT_MAX_SELL_PRICE or UPDATED_PROJECT_MAX_SELL_PRICE
        defaultProjectShouldBeFound("projectMaxSellPrice.in=" + DEFAULT_PROJECT_MAX_SELL_PRICE + "," + UPDATED_PROJECT_MAX_SELL_PRICE);

        // Get all the projectList where projectMaxSellPrice equals to UPDATED_PROJECT_MAX_SELL_PRICE
        defaultProjectShouldNotBeFound("projectMaxSellPrice.in=" + UPDATED_PROJECT_MAX_SELL_PRICE);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectMaxSellPriceIsNullOrNotNull() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectMaxSellPrice is not null
        defaultProjectShouldBeFound("projectMaxSellPrice.specified=true");

        // Get all the projectList where projectMaxSellPrice is null
        defaultProjectShouldNotBeFound("projectMaxSellPrice.specified=false");
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectSellPriceUnitIsEqualToSomething() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectSellPriceUnit equals to DEFAULT_PROJECT_SELL_PRICE_UNIT
        defaultProjectShouldBeFound("projectSellPriceUnit.equals=" + DEFAULT_PROJECT_SELL_PRICE_UNIT);

        // Get all the projectList where projectSellPriceUnit equals to UPDATED_PROJECT_SELL_PRICE_UNIT
        defaultProjectShouldNotBeFound("projectSellPriceUnit.equals=" + UPDATED_PROJECT_SELL_PRICE_UNIT);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectSellPriceUnitIsInShouldWork() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectSellPriceUnit in DEFAULT_PROJECT_SELL_PRICE_UNIT or UPDATED_PROJECT_SELL_PRICE_UNIT
        defaultProjectShouldBeFound("projectSellPriceUnit.in=" + DEFAULT_PROJECT_SELL_PRICE_UNIT + "," + UPDATED_PROJECT_SELL_PRICE_UNIT);

        // Get all the projectList where projectSellPriceUnit equals to UPDATED_PROJECT_SELL_PRICE_UNIT
        defaultProjectShouldNotBeFound("projectSellPriceUnit.in=" + UPDATED_PROJECT_SELL_PRICE_UNIT);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectSellPriceUnitIsNullOrNotNull() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectSellPriceUnit is not null
        defaultProjectShouldBeFound("projectSellPriceUnit.specified=true");

        // Get all the projectList where projectSellPriceUnit is null
        defaultProjectShouldNotBeFound("projectSellPriceUnit.specified=false");
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectMinRentPriceIsEqualToSomething() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectMinRentPrice equals to DEFAULT_PROJECT_MIN_RENT_PRICE
        defaultProjectShouldBeFound("projectMinRentPrice.equals=" + DEFAULT_PROJECT_MIN_RENT_PRICE);

        // Get all the projectList where projectMinRentPrice equals to UPDATED_PROJECT_MIN_RENT_PRICE
        defaultProjectShouldNotBeFound("projectMinRentPrice.equals=" + UPDATED_PROJECT_MIN_RENT_PRICE);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectMinRentPriceIsInShouldWork() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectMinRentPrice in DEFAULT_PROJECT_MIN_RENT_PRICE or UPDATED_PROJECT_MIN_RENT_PRICE
        defaultProjectShouldBeFound("projectMinRentPrice.in=" + DEFAULT_PROJECT_MIN_RENT_PRICE + "," + UPDATED_PROJECT_MIN_RENT_PRICE);

        // Get all the projectList where projectMinRentPrice equals to UPDATED_PROJECT_MIN_RENT_PRICE
        defaultProjectShouldNotBeFound("projectMinRentPrice.in=" + UPDATED_PROJECT_MIN_RENT_PRICE);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectMinRentPriceIsNullOrNotNull() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectMinRentPrice is not null
        defaultProjectShouldBeFound("projectMinRentPrice.specified=true");

        // Get all the projectList where projectMinRentPrice is null
        defaultProjectShouldNotBeFound("projectMinRentPrice.specified=false");
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectMaxRentPriceIsEqualToSomething() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectMaxRentPrice equals to DEFAULT_PROJECT_MAX_RENT_PRICE
        defaultProjectShouldBeFound("projectMaxRentPrice.equals=" + DEFAULT_PROJECT_MAX_RENT_PRICE);

        // Get all the projectList where projectMaxRentPrice equals to UPDATED_PROJECT_MAX_RENT_PRICE
        defaultProjectShouldNotBeFound("projectMaxRentPrice.equals=" + UPDATED_PROJECT_MAX_RENT_PRICE);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectMaxRentPriceIsInShouldWork() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectMaxRentPrice in DEFAULT_PROJECT_MAX_RENT_PRICE or UPDATED_PROJECT_MAX_RENT_PRICE
        defaultProjectShouldBeFound("projectMaxRentPrice.in=" + DEFAULT_PROJECT_MAX_RENT_PRICE + "," + UPDATED_PROJECT_MAX_RENT_PRICE);

        // Get all the projectList where projectMaxRentPrice equals to UPDATED_PROJECT_MAX_RENT_PRICE
        defaultProjectShouldNotBeFound("projectMaxRentPrice.in=" + UPDATED_PROJECT_MAX_RENT_PRICE);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectMaxRentPriceIsNullOrNotNull() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectMaxRentPrice is not null
        defaultProjectShouldBeFound("projectMaxRentPrice.specified=true");

        // Get all the projectList where projectMaxRentPrice is null
        defaultProjectShouldNotBeFound("projectMaxRentPrice.specified=false");
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectRentPriceUnitIsEqualToSomething() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectRentPriceUnit equals to DEFAULT_PROJECT_RENT_PRICE_UNIT
        defaultProjectShouldBeFound("projectRentPriceUnit.equals=" + DEFAULT_PROJECT_RENT_PRICE_UNIT);

        // Get all the projectList where projectRentPriceUnit equals to UPDATED_PROJECT_RENT_PRICE_UNIT
        defaultProjectShouldNotBeFound("projectRentPriceUnit.equals=" + UPDATED_PROJECT_RENT_PRICE_UNIT);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectRentPriceUnitIsInShouldWork() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectRentPriceUnit in DEFAULT_PROJECT_RENT_PRICE_UNIT or UPDATED_PROJECT_RENT_PRICE_UNIT
        defaultProjectShouldBeFound("projectRentPriceUnit.in=" + DEFAULT_PROJECT_RENT_PRICE_UNIT + "," + UPDATED_PROJECT_RENT_PRICE_UNIT);

        // Get all the projectList where projectRentPriceUnit equals to UPDATED_PROJECT_RENT_PRICE_UNIT
        defaultProjectShouldNotBeFound("projectRentPriceUnit.in=" + UPDATED_PROJECT_RENT_PRICE_UNIT);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectRentPriceUnitIsNullOrNotNull() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectRentPriceUnit is not null
        defaultProjectShouldBeFound("projectRentPriceUnit.specified=true");

        // Get all the projectList where projectRentPriceUnit is null
        defaultProjectShouldNotBeFound("projectRentPriceUnit.specified=false");
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectStartedDateIsEqualToSomething() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectStartedDate equals to DEFAULT_PROJECT_STARTED_DATE
        defaultProjectShouldBeFound("projectStartedDate.equals=" + DEFAULT_PROJECT_STARTED_DATE);

        // Get all the projectList where projectStartedDate equals to UPDATED_PROJECT_STARTED_DATE
        defaultProjectShouldNotBeFound("projectStartedDate.equals=" + UPDATED_PROJECT_STARTED_DATE);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectStartedDateIsInShouldWork() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectStartedDate in DEFAULT_PROJECT_STARTED_DATE or UPDATED_PROJECT_STARTED_DATE
        defaultProjectShouldBeFound("projectStartedDate.in=" + DEFAULT_PROJECT_STARTED_DATE + "," + UPDATED_PROJECT_STARTED_DATE);

        // Get all the projectList where projectStartedDate equals to UPDATED_PROJECT_STARTED_DATE
        defaultProjectShouldNotBeFound("projectStartedDate.in=" + UPDATED_PROJECT_STARTED_DATE);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectStartedDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectStartedDate is not null
        defaultProjectShouldBeFound("projectStartedDate.specified=true");

        // Get all the projectList where projectStartedDate is null
        defaultProjectShouldNotBeFound("projectStartedDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectStartedDateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectStartedDate greater than or equals to DEFAULT_PROJECT_STARTED_DATE
        defaultProjectShouldBeFound("projectStartedDate.greaterOrEqualThan=" + DEFAULT_PROJECT_STARTED_DATE);

        // Get all the projectList where projectStartedDate greater than or equals to UPDATED_PROJECT_STARTED_DATE
        defaultProjectShouldNotBeFound("projectStartedDate.greaterOrEqualThan=" + UPDATED_PROJECT_STARTED_DATE);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectStartedDateIsLessThanSomething() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectStartedDate less than or equals to DEFAULT_PROJECT_STARTED_DATE
        defaultProjectShouldNotBeFound("projectStartedDate.lessThan=" + DEFAULT_PROJECT_STARTED_DATE);

        // Get all the projectList where projectStartedDate less than or equals to UPDATED_PROJECT_STARTED_DATE
        defaultProjectShouldBeFound("projectStartedDate.lessThan=" + UPDATED_PROJECT_STARTED_DATE);
    }


    @Test
    @Transactional
    public void getAllProjectsByProjectFinishingDateIsEqualToSomething() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectFinishingDate equals to DEFAULT_PROJECT_FINISHING_DATE
        defaultProjectShouldBeFound("projectFinishingDate.equals=" + DEFAULT_PROJECT_FINISHING_DATE);

        // Get all the projectList where projectFinishingDate equals to UPDATED_PROJECT_FINISHING_DATE
        defaultProjectShouldNotBeFound("projectFinishingDate.equals=" + UPDATED_PROJECT_FINISHING_DATE);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectFinishingDateIsInShouldWork() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectFinishingDate in DEFAULT_PROJECT_FINISHING_DATE or UPDATED_PROJECT_FINISHING_DATE
        defaultProjectShouldBeFound("projectFinishingDate.in=" + DEFAULT_PROJECT_FINISHING_DATE + "," + UPDATED_PROJECT_FINISHING_DATE);

        // Get all the projectList where projectFinishingDate equals to UPDATED_PROJECT_FINISHING_DATE
        defaultProjectShouldNotBeFound("projectFinishingDate.in=" + UPDATED_PROJECT_FINISHING_DATE);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectFinishingDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectFinishingDate is not null
        defaultProjectShouldBeFound("projectFinishingDate.specified=true");

        // Get all the projectList where projectFinishingDate is null
        defaultProjectShouldNotBeFound("projectFinishingDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectFinishingDateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectFinishingDate greater than or equals to DEFAULT_PROJECT_FINISHING_DATE
        defaultProjectShouldBeFound("projectFinishingDate.greaterOrEqualThan=" + DEFAULT_PROJECT_FINISHING_DATE);

        // Get all the projectList where projectFinishingDate greater than or equals to UPDATED_PROJECT_FINISHING_DATE
        defaultProjectShouldNotBeFound("projectFinishingDate.greaterOrEqualThan=" + UPDATED_PROJECT_FINISHING_DATE);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectFinishingDateIsLessThanSomething() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectFinishingDate less than or equals to DEFAULT_PROJECT_FINISHING_DATE
        defaultProjectShouldNotBeFound("projectFinishingDate.lessThan=" + DEFAULT_PROJECT_FINISHING_DATE);

        // Get all the projectList where projectFinishingDate less than or equals to UPDATED_PROJECT_FINISHING_DATE
        defaultProjectShouldBeFound("projectFinishingDate.lessThan=" + UPDATED_PROJECT_FINISHING_DATE);
    }


    @Test
    @Transactional
    public void getAllProjectsByProjectMinApartmentSquareIsEqualToSomething() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectMinApartmentSquare equals to DEFAULT_PROJECT_MIN_APARTMENT_SQUARE
        defaultProjectShouldBeFound("projectMinApartmentSquare.equals=" + DEFAULT_PROJECT_MIN_APARTMENT_SQUARE);

        // Get all the projectList where projectMinApartmentSquare equals to UPDATED_PROJECT_MIN_APARTMENT_SQUARE
        defaultProjectShouldNotBeFound("projectMinApartmentSquare.equals=" + UPDATED_PROJECT_MIN_APARTMENT_SQUARE);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectMinApartmentSquareIsInShouldWork() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectMinApartmentSquare in DEFAULT_PROJECT_MIN_APARTMENT_SQUARE or UPDATED_PROJECT_MIN_APARTMENT_SQUARE
        defaultProjectShouldBeFound("projectMinApartmentSquare.in=" + DEFAULT_PROJECT_MIN_APARTMENT_SQUARE + "," + UPDATED_PROJECT_MIN_APARTMENT_SQUARE);

        // Get all the projectList where projectMinApartmentSquare equals to UPDATED_PROJECT_MIN_APARTMENT_SQUARE
        defaultProjectShouldNotBeFound("projectMinApartmentSquare.in=" + UPDATED_PROJECT_MIN_APARTMENT_SQUARE);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectMinApartmentSquareIsNullOrNotNull() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectMinApartmentSquare is not null
        defaultProjectShouldBeFound("projectMinApartmentSquare.specified=true");

        // Get all the projectList where projectMinApartmentSquare is null
        defaultProjectShouldNotBeFound("projectMinApartmentSquare.specified=false");
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectMinApartmentSquareIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectMinApartmentSquare greater than or equals to DEFAULT_PROJECT_MIN_APARTMENT_SQUARE
        defaultProjectShouldBeFound("projectMinApartmentSquare.greaterOrEqualThan=" + DEFAULT_PROJECT_MIN_APARTMENT_SQUARE);

        // Get all the projectList where projectMinApartmentSquare greater than or equals to UPDATED_PROJECT_MIN_APARTMENT_SQUARE
        defaultProjectShouldNotBeFound("projectMinApartmentSquare.greaterOrEqualThan=" + UPDATED_PROJECT_MIN_APARTMENT_SQUARE);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectMinApartmentSquareIsLessThanSomething() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectMinApartmentSquare less than or equals to DEFAULT_PROJECT_MIN_APARTMENT_SQUARE
        defaultProjectShouldNotBeFound("projectMinApartmentSquare.lessThan=" + DEFAULT_PROJECT_MIN_APARTMENT_SQUARE);

        // Get all the projectList where projectMinApartmentSquare less than or equals to UPDATED_PROJECT_MIN_APARTMENT_SQUARE
        defaultProjectShouldBeFound("projectMinApartmentSquare.lessThan=" + UPDATED_PROJECT_MIN_APARTMENT_SQUARE);
    }


    @Test
    @Transactional
    public void getAllProjectsByProjectMaxApartmentSquareIsEqualToSomething() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectMaxApartmentSquare equals to DEFAULT_PROJECT_MAX_APARTMENT_SQUARE
        defaultProjectShouldBeFound("projectMaxApartmentSquare.equals=" + DEFAULT_PROJECT_MAX_APARTMENT_SQUARE);

        // Get all the projectList where projectMaxApartmentSquare equals to UPDATED_PROJECT_MAX_APARTMENT_SQUARE
        defaultProjectShouldNotBeFound("projectMaxApartmentSquare.equals=" + UPDATED_PROJECT_MAX_APARTMENT_SQUARE);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectMaxApartmentSquareIsInShouldWork() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectMaxApartmentSquare in DEFAULT_PROJECT_MAX_APARTMENT_SQUARE or UPDATED_PROJECT_MAX_APARTMENT_SQUARE
        defaultProjectShouldBeFound("projectMaxApartmentSquare.in=" + DEFAULT_PROJECT_MAX_APARTMENT_SQUARE + "," + UPDATED_PROJECT_MAX_APARTMENT_SQUARE);

        // Get all the projectList where projectMaxApartmentSquare equals to UPDATED_PROJECT_MAX_APARTMENT_SQUARE
        defaultProjectShouldNotBeFound("projectMaxApartmentSquare.in=" + UPDATED_PROJECT_MAX_APARTMENT_SQUARE);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectMaxApartmentSquareIsNullOrNotNull() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectMaxApartmentSquare is not null
        defaultProjectShouldBeFound("projectMaxApartmentSquare.specified=true");

        // Get all the projectList where projectMaxApartmentSquare is null
        defaultProjectShouldNotBeFound("projectMaxApartmentSquare.specified=false");
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectMaxApartmentSquareIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectMaxApartmentSquare greater than or equals to DEFAULT_PROJECT_MAX_APARTMENT_SQUARE
        defaultProjectShouldBeFound("projectMaxApartmentSquare.greaterOrEqualThan=" + DEFAULT_PROJECT_MAX_APARTMENT_SQUARE);

        // Get all the projectList where projectMaxApartmentSquare greater than or equals to UPDATED_PROJECT_MAX_APARTMENT_SQUARE
        defaultProjectShouldNotBeFound("projectMaxApartmentSquare.greaterOrEqualThan=" + UPDATED_PROJECT_MAX_APARTMENT_SQUARE);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectMaxApartmentSquareIsLessThanSomething() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectMaxApartmentSquare less than or equals to DEFAULT_PROJECT_MAX_APARTMENT_SQUARE
        defaultProjectShouldNotBeFound("projectMaxApartmentSquare.lessThan=" + DEFAULT_PROJECT_MAX_APARTMENT_SQUARE);

        // Get all the projectList where projectMaxApartmentSquare less than or equals to UPDATED_PROJECT_MAX_APARTMENT_SQUARE
        defaultProjectShouldBeFound("projectMaxApartmentSquare.lessThan=" + UPDATED_PROJECT_MAX_APARTMENT_SQUARE);
    }


    @Test
    @Transactional
    public void getAllProjectsByProjectGreenSpaceIsEqualToSomething() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectGreenSpace equals to DEFAULT_PROJECT_GREEN_SPACE
        defaultProjectShouldBeFound("projectGreenSpace.equals=" + DEFAULT_PROJECT_GREEN_SPACE);

        // Get all the projectList where projectGreenSpace equals to UPDATED_PROJECT_GREEN_SPACE
        defaultProjectShouldNotBeFound("projectGreenSpace.equals=" + UPDATED_PROJECT_GREEN_SPACE);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectGreenSpaceIsInShouldWork() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectGreenSpace in DEFAULT_PROJECT_GREEN_SPACE or UPDATED_PROJECT_GREEN_SPACE
        defaultProjectShouldBeFound("projectGreenSpace.in=" + DEFAULT_PROJECT_GREEN_SPACE + "," + UPDATED_PROJECT_GREEN_SPACE);

        // Get all the projectList where projectGreenSpace equals to UPDATED_PROJECT_GREEN_SPACE
        defaultProjectShouldNotBeFound("projectGreenSpace.in=" + UPDATED_PROJECT_GREEN_SPACE);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectGreenSpaceIsNullOrNotNull() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectGreenSpace is not null
        defaultProjectShouldBeFound("projectGreenSpace.specified=true");

        // Get all the projectList where projectGreenSpace is null
        defaultProjectShouldNotBeFound("projectGreenSpace.specified=false");
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectGreenSpaceIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectGreenSpace greater than or equals to DEFAULT_PROJECT_GREEN_SPACE
        defaultProjectShouldBeFound("projectGreenSpace.greaterOrEqualThan=" + DEFAULT_PROJECT_GREEN_SPACE);

        // Get all the projectList where projectGreenSpace greater than or equals to (DEFAULT_PROJECT_GREEN_SPACE + 1)
        defaultProjectShouldNotBeFound("projectGreenSpace.greaterOrEqualThan=" + (DEFAULT_PROJECT_GREEN_SPACE + 1));
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectGreenSpaceIsLessThanSomething() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectGreenSpace less than or equals to DEFAULT_PROJECT_GREEN_SPACE
        defaultProjectShouldNotBeFound("projectGreenSpace.lessThan=" + DEFAULT_PROJECT_GREEN_SPACE);

        // Get all the projectList where projectGreenSpace less than or equals to (DEFAULT_PROJECT_GREEN_SPACE + 1)
        defaultProjectShouldBeFound("projectGreenSpace.lessThan=" + (DEFAULT_PROJECT_GREEN_SPACE + 1));
    }


    @Test
    @Transactional
    public void getAllProjectsByProjectBuildingDensityIsEqualToSomething() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectBuildingDensity equals to DEFAULT_PROJECT_BUILDING_DENSITY
        defaultProjectShouldBeFound("projectBuildingDensity.equals=" + DEFAULT_PROJECT_BUILDING_DENSITY);

        // Get all the projectList where projectBuildingDensity equals to UPDATED_PROJECT_BUILDING_DENSITY
        defaultProjectShouldNotBeFound("projectBuildingDensity.equals=" + UPDATED_PROJECT_BUILDING_DENSITY);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectBuildingDensityIsInShouldWork() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectBuildingDensity in DEFAULT_PROJECT_BUILDING_DENSITY or UPDATED_PROJECT_BUILDING_DENSITY
        defaultProjectShouldBeFound("projectBuildingDensity.in=" + DEFAULT_PROJECT_BUILDING_DENSITY + "," + UPDATED_PROJECT_BUILDING_DENSITY);

        // Get all the projectList where projectBuildingDensity equals to UPDATED_PROJECT_BUILDING_DENSITY
        defaultProjectShouldNotBeFound("projectBuildingDensity.in=" + UPDATED_PROJECT_BUILDING_DENSITY);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectBuildingDensityIsNullOrNotNull() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectBuildingDensity is not null
        defaultProjectShouldBeFound("projectBuildingDensity.specified=true");

        // Get all the projectList where projectBuildingDensity is null
        defaultProjectShouldNotBeFound("projectBuildingDensity.specified=false");
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectBuildingDensityIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectBuildingDensity greater than or equals to DEFAULT_PROJECT_BUILDING_DENSITY
        defaultProjectShouldBeFound("projectBuildingDensity.greaterOrEqualThan=" + DEFAULT_PROJECT_BUILDING_DENSITY);

        // Get all the projectList where projectBuildingDensity greater than or equals to (DEFAULT_PROJECT_BUILDING_DENSITY + 1)
        defaultProjectShouldNotBeFound("projectBuildingDensity.greaterOrEqualThan=" + (DEFAULT_PROJECT_BUILDING_DENSITY + 1));
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectBuildingDensityIsLessThanSomething() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectBuildingDensity less than or equals to DEFAULT_PROJECT_BUILDING_DENSITY
        defaultProjectShouldNotBeFound("projectBuildingDensity.lessThan=" + DEFAULT_PROJECT_BUILDING_DENSITY);

        // Get all the projectList where projectBuildingDensity less than or equals to (DEFAULT_PROJECT_BUILDING_DENSITY + 1)
        defaultProjectShouldBeFound("projectBuildingDensity.lessThan=" + (DEFAULT_PROJECT_BUILDING_DENSITY + 1));
    }


    @Test
    @Transactional
    public void getAllProjectsByProjectDesignCompanyIsEqualToSomething() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectDesignCompany equals to DEFAULT_PROJECT_DESIGN_COMPANY
        defaultProjectShouldBeFound("projectDesignCompany.equals=" + DEFAULT_PROJECT_DESIGN_COMPANY);

        // Get all the projectList where projectDesignCompany equals to UPDATED_PROJECT_DESIGN_COMPANY
        defaultProjectShouldNotBeFound("projectDesignCompany.equals=" + UPDATED_PROJECT_DESIGN_COMPANY);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectDesignCompanyIsInShouldWork() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectDesignCompany in DEFAULT_PROJECT_DESIGN_COMPANY or UPDATED_PROJECT_DESIGN_COMPANY
        defaultProjectShouldBeFound("projectDesignCompany.in=" + DEFAULT_PROJECT_DESIGN_COMPANY + "," + UPDATED_PROJECT_DESIGN_COMPANY);

        // Get all the projectList where projectDesignCompany equals to UPDATED_PROJECT_DESIGN_COMPANY
        defaultProjectShouldNotBeFound("projectDesignCompany.in=" + UPDATED_PROJECT_DESIGN_COMPANY);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectDesignCompanyIsNullOrNotNull() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectDesignCompany is not null
        defaultProjectShouldBeFound("projectDesignCompany.specified=true");

        // Get all the projectList where projectDesignCompany is null
        defaultProjectShouldNotBeFound("projectDesignCompany.specified=false");
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectCarParkIsEqualToSomething() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectCarPark equals to DEFAULT_PROJECT_CAR_PARK
        defaultProjectShouldBeFound("projectCarPark.equals=" + DEFAULT_PROJECT_CAR_PARK);

        // Get all the projectList where projectCarPark equals to UPDATED_PROJECT_CAR_PARK
        defaultProjectShouldNotBeFound("projectCarPark.equals=" + UPDATED_PROJECT_CAR_PARK);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectCarParkIsInShouldWork() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectCarPark in DEFAULT_PROJECT_CAR_PARK or UPDATED_PROJECT_CAR_PARK
        defaultProjectShouldBeFound("projectCarPark.in=" + DEFAULT_PROJECT_CAR_PARK + "," + UPDATED_PROJECT_CAR_PARK);

        // Get all the projectList where projectCarPark equals to UPDATED_PROJECT_CAR_PARK
        defaultProjectShouldNotBeFound("projectCarPark.in=" + UPDATED_PROJECT_CAR_PARK);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectCarParkIsNullOrNotNull() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectCarPark is not null
        defaultProjectShouldBeFound("projectCarPark.specified=true");

        // Get all the projectList where projectCarPark is null
        defaultProjectShouldNotBeFound("projectCarPark.specified=false");
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectBbqCourtIsEqualToSomething() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectBbqCourt equals to DEFAULT_PROJECT_BBQ_COURT
        defaultProjectShouldBeFound("projectBbqCourt.equals=" + DEFAULT_PROJECT_BBQ_COURT);

        // Get all the projectList where projectBbqCourt equals to UPDATED_PROJECT_BBQ_COURT
        defaultProjectShouldNotBeFound("projectBbqCourt.equals=" + UPDATED_PROJECT_BBQ_COURT);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectBbqCourtIsInShouldWork() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectBbqCourt in DEFAULT_PROJECT_BBQ_COURT or UPDATED_PROJECT_BBQ_COURT
        defaultProjectShouldBeFound("projectBbqCourt.in=" + DEFAULT_PROJECT_BBQ_COURT + "," + UPDATED_PROJECT_BBQ_COURT);

        // Get all the projectList where projectBbqCourt equals to UPDATED_PROJECT_BBQ_COURT
        defaultProjectShouldNotBeFound("projectBbqCourt.in=" + UPDATED_PROJECT_BBQ_COURT);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectBbqCourtIsNullOrNotNull() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectBbqCourt is not null
        defaultProjectShouldBeFound("projectBbqCourt.specified=true");

        // Get all the projectList where projectBbqCourt is null
        defaultProjectShouldNotBeFound("projectBbqCourt.specified=false");
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectElevatorIsEqualToSomething() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectElevator equals to DEFAULT_PROJECT_ELEVATOR
        defaultProjectShouldBeFound("projectElevator.equals=" + DEFAULT_PROJECT_ELEVATOR);

        // Get all the projectList where projectElevator equals to UPDATED_PROJECT_ELEVATOR
        defaultProjectShouldNotBeFound("projectElevator.equals=" + UPDATED_PROJECT_ELEVATOR);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectElevatorIsInShouldWork() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectElevator in DEFAULT_PROJECT_ELEVATOR or UPDATED_PROJECT_ELEVATOR
        defaultProjectShouldBeFound("projectElevator.in=" + DEFAULT_PROJECT_ELEVATOR + "," + UPDATED_PROJECT_ELEVATOR);

        // Get all the projectList where projectElevator equals to UPDATED_PROJECT_ELEVATOR
        defaultProjectShouldNotBeFound("projectElevator.in=" + UPDATED_PROJECT_ELEVATOR);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectElevatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectElevator is not null
        defaultProjectShouldBeFound("projectElevator.specified=true");

        // Get all the projectList where projectElevator is null
        defaultProjectShouldNotBeFound("projectElevator.specified=false");
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectShoppingCenterIsEqualToSomething() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectShoppingCenter equals to DEFAULT_PROJECT_SHOPPING_CENTER
        defaultProjectShouldBeFound("projectShoppingCenter.equals=" + DEFAULT_PROJECT_SHOPPING_CENTER);

        // Get all the projectList where projectShoppingCenter equals to UPDATED_PROJECT_SHOPPING_CENTER
        defaultProjectShouldNotBeFound("projectShoppingCenter.equals=" + UPDATED_PROJECT_SHOPPING_CENTER);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectShoppingCenterIsInShouldWork() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectShoppingCenter in DEFAULT_PROJECT_SHOPPING_CENTER or UPDATED_PROJECT_SHOPPING_CENTER
        defaultProjectShouldBeFound("projectShoppingCenter.in=" + DEFAULT_PROJECT_SHOPPING_CENTER + "," + UPDATED_PROJECT_SHOPPING_CENTER);

        // Get all the projectList where projectShoppingCenter equals to UPDATED_PROJECT_SHOPPING_CENTER
        defaultProjectShouldNotBeFound("projectShoppingCenter.in=" + UPDATED_PROJECT_SHOPPING_CENTER);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectShoppingCenterIsNullOrNotNull() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectShoppingCenter is not null
        defaultProjectShouldBeFound("projectShoppingCenter.specified=true");

        // Get all the projectList where projectShoppingCenter is null
        defaultProjectShouldNotBeFound("projectShoppingCenter.specified=false");
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectSwimmingPoolIsEqualToSomething() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectSwimmingPool equals to DEFAULT_PROJECT_SWIMMING_POOL
        defaultProjectShouldBeFound("projectSwimmingPool.equals=" + DEFAULT_PROJECT_SWIMMING_POOL);

        // Get all the projectList where projectSwimmingPool equals to UPDATED_PROJECT_SWIMMING_POOL
        defaultProjectShouldNotBeFound("projectSwimmingPool.equals=" + UPDATED_PROJECT_SWIMMING_POOL);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectSwimmingPoolIsInShouldWork() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectSwimmingPool in DEFAULT_PROJECT_SWIMMING_POOL or UPDATED_PROJECT_SWIMMING_POOL
        defaultProjectShouldBeFound("projectSwimmingPool.in=" + DEFAULT_PROJECT_SWIMMING_POOL + "," + UPDATED_PROJECT_SWIMMING_POOL);

        // Get all the projectList where projectSwimmingPool equals to UPDATED_PROJECT_SWIMMING_POOL
        defaultProjectShouldNotBeFound("projectSwimmingPool.in=" + UPDATED_PROJECT_SWIMMING_POOL);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectSwimmingPoolIsNullOrNotNull() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectSwimmingPool is not null
        defaultProjectShouldBeFound("projectSwimmingPool.specified=true");

        // Get all the projectList where projectSwimmingPool is null
        defaultProjectShouldNotBeFound("projectSwimmingPool.specified=false");
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectCommunityRoomIsEqualToSomething() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectCommunityRoom equals to DEFAULT_PROJECT_COMMUNITY_ROOM
        defaultProjectShouldBeFound("projectCommunityRoom.equals=" + DEFAULT_PROJECT_COMMUNITY_ROOM);

        // Get all the projectList where projectCommunityRoom equals to UPDATED_PROJECT_COMMUNITY_ROOM
        defaultProjectShouldNotBeFound("projectCommunityRoom.equals=" + UPDATED_PROJECT_COMMUNITY_ROOM);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectCommunityRoomIsInShouldWork() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectCommunityRoom in DEFAULT_PROJECT_COMMUNITY_ROOM or UPDATED_PROJECT_COMMUNITY_ROOM
        defaultProjectShouldBeFound("projectCommunityRoom.in=" + DEFAULT_PROJECT_COMMUNITY_ROOM + "," + UPDATED_PROJECT_COMMUNITY_ROOM);

        // Get all the projectList where projectCommunityRoom equals to UPDATED_PROJECT_COMMUNITY_ROOM
        defaultProjectShouldNotBeFound("projectCommunityRoom.in=" + UPDATED_PROJECT_COMMUNITY_ROOM);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectCommunityRoomIsNullOrNotNull() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectCommunityRoom is not null
        defaultProjectShouldBeFound("projectCommunityRoom.specified=true");

        // Get all the projectList where projectCommunityRoom is null
        defaultProjectShouldNotBeFound("projectCommunityRoom.specified=false");
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectGymIsEqualToSomething() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectGym equals to DEFAULT_PROJECT_GYM
        defaultProjectShouldBeFound("projectGym.equals=" + DEFAULT_PROJECT_GYM);

        // Get all the projectList where projectGym equals to UPDATED_PROJECT_GYM
        defaultProjectShouldNotBeFound("projectGym.equals=" + UPDATED_PROJECT_GYM);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectGymIsInShouldWork() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectGym in DEFAULT_PROJECT_GYM or UPDATED_PROJECT_GYM
        defaultProjectShouldBeFound("projectGym.in=" + DEFAULT_PROJECT_GYM + "," + UPDATED_PROJECT_GYM);

        // Get all the projectList where projectGym equals to UPDATED_PROJECT_GYM
        defaultProjectShouldNotBeFound("projectGym.in=" + UPDATED_PROJECT_GYM);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectGymIsNullOrNotNull() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectGym is not null
        defaultProjectShouldBeFound("projectGym.specified=true");

        // Get all the projectList where projectGym is null
        defaultProjectShouldNotBeFound("projectGym.specified=false");
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectCityParkIsEqualToSomething() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectCityPark equals to DEFAULT_PROJECT_CITY_PARK
        defaultProjectShouldBeFound("projectCityPark.equals=" + DEFAULT_PROJECT_CITY_PARK);

        // Get all the projectList where projectCityPark equals to UPDATED_PROJECT_CITY_PARK
        defaultProjectShouldNotBeFound("projectCityPark.equals=" + UPDATED_PROJECT_CITY_PARK);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectCityParkIsInShouldWork() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectCityPark in DEFAULT_PROJECT_CITY_PARK or UPDATED_PROJECT_CITY_PARK
        defaultProjectShouldBeFound("projectCityPark.in=" + DEFAULT_PROJECT_CITY_PARK + "," + UPDATED_PROJECT_CITY_PARK);

        // Get all the projectList where projectCityPark equals to UPDATED_PROJECT_CITY_PARK
        defaultProjectShouldNotBeFound("projectCityPark.in=" + UPDATED_PROJECT_CITY_PARK);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectCityParkIsNullOrNotNull() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectCityPark is not null
        defaultProjectShouldBeFound("projectCityPark.specified=true");

        // Get all the projectList where projectCityPark is null
        defaultProjectShouldNotBeFound("projectCityPark.specified=false");
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectGuardIsEqualToSomething() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectGuard equals to DEFAULT_PROJECT_GUARD
        defaultProjectShouldBeFound("projectGuard.equals=" + DEFAULT_PROJECT_GUARD);

        // Get all the projectList where projectGuard equals to UPDATED_PROJECT_GUARD
        defaultProjectShouldNotBeFound("projectGuard.equals=" + UPDATED_PROJECT_GUARD);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectGuardIsInShouldWork() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectGuard in DEFAULT_PROJECT_GUARD or UPDATED_PROJECT_GUARD
        defaultProjectShouldBeFound("projectGuard.in=" + DEFAULT_PROJECT_GUARD + "," + UPDATED_PROJECT_GUARD);

        // Get all the projectList where projectGuard equals to UPDATED_PROJECT_GUARD
        defaultProjectShouldNotBeFound("projectGuard.in=" + UPDATED_PROJECT_GUARD);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectGuardIsNullOrNotNull() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectGuard is not null
        defaultProjectShouldBeFound("projectGuard.specified=true");

        // Get all the projectList where projectGuard is null
        defaultProjectShouldNotBeFound("projectGuard.specified=false");
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectPlayGroundIsEqualToSomething() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectPlayGround equals to DEFAULT_PROJECT_PLAY_GROUND
        defaultProjectShouldBeFound("projectPlayGround.equals=" + DEFAULT_PROJECT_PLAY_GROUND);

        // Get all the projectList where projectPlayGround equals to UPDATED_PROJECT_PLAY_GROUND
        defaultProjectShouldNotBeFound("projectPlayGround.equals=" + UPDATED_PROJECT_PLAY_GROUND);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectPlayGroundIsInShouldWork() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectPlayGround in DEFAULT_PROJECT_PLAY_GROUND or UPDATED_PROJECT_PLAY_GROUND
        defaultProjectShouldBeFound("projectPlayGround.in=" + DEFAULT_PROJECT_PLAY_GROUND + "," + UPDATED_PROJECT_PLAY_GROUND);

        // Get all the projectList where projectPlayGround equals to UPDATED_PROJECT_PLAY_GROUND
        defaultProjectShouldNotBeFound("projectPlayGround.in=" + UPDATED_PROJECT_PLAY_GROUND);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectPlayGroundIsNullOrNotNull() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectPlayGround is not null
        defaultProjectShouldBeFound("projectPlayGround.specified=true");

        // Get all the projectList where projectPlayGround is null
        defaultProjectShouldNotBeFound("projectPlayGround.specified=false");
    }

    @Test
    @Transactional
    public void getAllProjectsByLongitudeIsEqualToSomething() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where longitude equals to DEFAULT_LONGITUDE
        defaultProjectShouldBeFound("longitude.equals=" + DEFAULT_LONGITUDE);

        // Get all the projectList where longitude equals to UPDATED_LONGITUDE
        defaultProjectShouldNotBeFound("longitude.equals=" + UPDATED_LONGITUDE);
    }

    @Test
    @Transactional
    public void getAllProjectsByLongitudeIsInShouldWork() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where longitude in DEFAULT_LONGITUDE or UPDATED_LONGITUDE
        defaultProjectShouldBeFound("longitude.in=" + DEFAULT_LONGITUDE + "," + UPDATED_LONGITUDE);

        // Get all the projectList where longitude equals to UPDATED_LONGITUDE
        defaultProjectShouldNotBeFound("longitude.in=" + UPDATED_LONGITUDE);
    }

    @Test
    @Transactional
    public void getAllProjectsByLongitudeIsNullOrNotNull() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where longitude is not null
        defaultProjectShouldBeFound("longitude.specified=true");

        // Get all the projectList where longitude is null
        defaultProjectShouldNotBeFound("longitude.specified=false");
    }

    @Test
    @Transactional
    public void getAllProjectsByLatitudeIsEqualToSomething() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where latitude equals to DEFAULT_LATITUDE
        defaultProjectShouldBeFound("latitude.equals=" + DEFAULT_LATITUDE);

        // Get all the projectList where latitude equals to UPDATED_LATITUDE
        defaultProjectShouldNotBeFound("latitude.equals=" + UPDATED_LATITUDE);
    }

    @Test
    @Transactional
    public void getAllProjectsByLatitudeIsInShouldWork() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where latitude in DEFAULT_LATITUDE or UPDATED_LATITUDE
        defaultProjectShouldBeFound("latitude.in=" + DEFAULT_LATITUDE + "," + UPDATED_LATITUDE);

        // Get all the projectList where latitude equals to UPDATED_LATITUDE
        defaultProjectShouldNotBeFound("latitude.in=" + UPDATED_LATITUDE);
    }

    @Test
    @Transactional
    public void getAllProjectsByLatitudeIsNullOrNotNull() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where latitude is not null
        defaultProjectShouldBeFound("latitude.specified=true");

        // Get all the projectList where latitude is null
        defaultProjectShouldNotBeFound("latitude.specified=false");
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectSeenCountIsEqualToSomething() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectSeenCount equals to DEFAULT_PROJECT_SEEN_COUNT
        defaultProjectShouldBeFound("projectSeenCount.equals=" + DEFAULT_PROJECT_SEEN_COUNT);

        // Get all the projectList where projectSeenCount equals to UPDATED_PROJECT_SEEN_COUNT
        defaultProjectShouldNotBeFound("projectSeenCount.equals=" + UPDATED_PROJECT_SEEN_COUNT);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectSeenCountIsInShouldWork() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectSeenCount in DEFAULT_PROJECT_SEEN_COUNT or UPDATED_PROJECT_SEEN_COUNT
        defaultProjectShouldBeFound("projectSeenCount.in=" + DEFAULT_PROJECT_SEEN_COUNT + "," + UPDATED_PROJECT_SEEN_COUNT);

        // Get all the projectList where projectSeenCount equals to UPDATED_PROJECT_SEEN_COUNT
        defaultProjectShouldNotBeFound("projectSeenCount.in=" + UPDATED_PROJECT_SEEN_COUNT);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectSeenCountIsNullOrNotNull() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectSeenCount is not null
        defaultProjectShouldBeFound("projectSeenCount.specified=true");

        // Get all the projectList where projectSeenCount is null
        defaultProjectShouldNotBeFound("projectSeenCount.specified=false");
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectSeenCountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectSeenCount greater than or equals to DEFAULT_PROJECT_SEEN_COUNT
        defaultProjectShouldBeFound("projectSeenCount.greaterOrEqualThan=" + DEFAULT_PROJECT_SEEN_COUNT);

        // Get all the projectList where projectSeenCount greater than or equals to UPDATED_PROJECT_SEEN_COUNT
        defaultProjectShouldNotBeFound("projectSeenCount.greaterOrEqualThan=" + UPDATED_PROJECT_SEEN_COUNT);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectSeenCountIsLessThanSomething() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectSeenCount less than or equals to DEFAULT_PROJECT_SEEN_COUNT
        defaultProjectShouldNotBeFound("projectSeenCount.lessThan=" + DEFAULT_PROJECT_SEEN_COUNT);

        // Get all the projectList where projectSeenCount less than or equals to UPDATED_PROJECT_SEEN_COUNT
        defaultProjectShouldBeFound("projectSeenCount.lessThan=" + UPDATED_PROJECT_SEEN_COUNT);
    }


    @Test
    @Transactional
    public void getAllProjectsByProjectAvailableIsEqualToSomething() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectAvailable equals to DEFAULT_PROJECT_AVAILABLE
        defaultProjectShouldBeFound("projectAvailable.equals=" + DEFAULT_PROJECT_AVAILABLE);

        // Get all the projectList where projectAvailable equals to UPDATED_PROJECT_AVAILABLE
        defaultProjectShouldNotBeFound("projectAvailable.equals=" + UPDATED_PROJECT_AVAILABLE);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectAvailableIsInShouldWork() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectAvailable in DEFAULT_PROJECT_AVAILABLE or UPDATED_PROJECT_AVAILABLE
        defaultProjectShouldBeFound("projectAvailable.in=" + DEFAULT_PROJECT_AVAILABLE + "," + UPDATED_PROJECT_AVAILABLE);

        // Get all the projectList where projectAvailable equals to UPDATED_PROJECT_AVAILABLE
        defaultProjectShouldNotBeFound("projectAvailable.in=" + UPDATED_PROJECT_AVAILABLE);
    }

    @Test
    @Transactional
    public void getAllProjectsByProjectAvailableIsNullOrNotNull() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList where projectAvailable is not null
        defaultProjectShouldBeFound("projectAvailable.specified=true");

        // Get all the projectList where projectAvailable is null
        defaultProjectShouldNotBeFound("projectAvailable.specified=false");
    }

    @Test
    @Transactional
    public void getAllProjectsByLocationIsEqualToSomething() throws Exception {
        // Initialize the database
        Location location = LocationResourceIntTest.createEntity(em);
        em.persist(location);
        em.flush();
        project.setLocation(location);
        projectRepository.saveAndFlush(project);
        Long locationId = location.getId();

        // Get all the projectList where location equals to locationId
        defaultProjectShouldBeFound("locationId.equals=" + locationId);

        // Get all the projectList where location equals to locationId + 1
        defaultProjectShouldNotBeFound("locationId.equals=" + (locationId + 1));
    }


    @Test
    @Transactional
    public void getAllProjectsByConsultantIsEqualToSomething() throws Exception {
        // Initialize the database
        User consultant = UserResourceIntTest.createEntity(em);
        em.persist(consultant);
        em.flush();
        project.setConsultant(consultant);
        projectRepository.saveAndFlush(project);
        Long consultantId = consultant.getId();

        // Get all the projectList where consultant equals to consultantId
        defaultProjectShouldBeFound("consultantId.equals=" + consultantId);

        // Get all the projectList where consultant equals to consultantId + 1
        defaultProjectShouldNotBeFound("consultantId.equals=" + (consultantId + 1));
    }


    @Test
    @Transactional
    public void getAllProjectsByTagIsEqualToSomething() throws Exception {
        // Initialize the database
        Tag tag = TagResourceIntTest.createEntity(em);
        em.persist(tag);
        em.flush();
        project.addTag(tag);
        projectRepository.saveAndFlush(project);
        Long tagId = tag.getId();

        // Get all the projectList where tag equals to tagId
        defaultProjectShouldBeFound("tagId.equals=" + tagId);

        // Get all the projectList where tag equals to tagId + 1
        defaultProjectShouldNotBeFound("tagId.equals=" + (tagId + 1));
    }


    @Test
    @Transactional
    public void getAllProjectsByBuildingtypeIsEqualToSomething() throws Exception {
        // Initialize the database
        BuildingType buildingtype = BuildingTypeResourceIntTest.createEntity(em);
        em.persist(buildingtype);
        em.flush();
        project.addBuildingtype(buildingtype);
        projectRepository.saveAndFlush(project);
        Long buildingtypeId = buildingtype.getId();

        // Get all the projectList where buildingtype equals to buildingtypeId
        defaultProjectShouldBeFound("buildingtypeId.equals=" + buildingtypeId);

        // Get all the projectList where buildingtype equals to buildingtypeId + 1
        defaultProjectShouldNotBeFound("buildingtypeId.equals=" + (buildingtypeId + 1));
    }


    @Test
    @Transactional
    public void getAllProjectsByInverstorIsEqualToSomething() throws Exception {
        // Initialize the database
        Investor inverstor = InvestorResourceIntTest.createEntity(em);
        em.persist(inverstor);
        em.flush();
        project.addInverstor(inverstor);
        projectRepository.saveAndFlush(project);
        Long inverstorId = inverstor.getId();

        // Get all the projectList where inverstor equals to inverstorId
        defaultProjectShouldBeFound("inverstorId.equals=" + inverstorId);

        // Get all the projectList where inverstor equals to inverstorId + 1
        defaultProjectShouldNotBeFound("inverstorId.equals=" + (inverstorId + 1));
    }


    @Test
    @Transactional
    public void getAllProjectsByContractorIsEqualToSomething() throws Exception {
        // Initialize the database
        Contractor contractor = ContractorResourceIntTest.createEntity(em);
        em.persist(contractor);
        em.flush();
        project.addContractor(contractor);
        projectRepository.saveAndFlush(project);
        Long contractorId = contractor.getId();

        // Get all the projectList where contractor equals to contractorId
        defaultProjectShouldBeFound("contractorId.equals=" + contractorId);

        // Get all the projectList where contractor equals to contractorId + 1
        defaultProjectShouldNotBeFound("contractorId.equals=" + (contractorId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultProjectShouldBeFound(String filter) throws Exception {
        restProjectMockMvc.perform(get("/api/projects?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(project.getId().intValue())))
            .andExpect(jsonPath("$.[*].projectName").value(hasItem(DEFAULT_PROJECT_NAME.toString())))
            .andExpect(jsonPath("$.[*].projectAlias").value(hasItem(DEFAULT_PROJECT_ALIAS.toString())))
            .andExpect(jsonPath("$.[*].projectAvatarUrl").value(hasItem(DEFAULT_PROJECT_AVATAR_URL.toString())))
            .andExpect(jsonPath("$.[*].projectDistrict").value(hasItem(DEFAULT_PROJECT_DISTRICT.toString())))
            .andExpect(jsonPath("$.[*].projectProvince").value(hasItem(DEFAULT_PROJECT_PROVINCE.toString())))
            .andExpect(jsonPath("$.[*].residentialAreaId").value(hasItem(DEFAULT_RESIDENTIAL_AREA_ID.intValue())))
            .andExpect(jsonPath("$.[*].projectRoad").value(hasItem(DEFAULT_PROJECT_ROAD.toString())))
            .andExpect(jsonPath("$.[*].projectWard").value(hasItem(DEFAULT_PROJECT_WARD.toString())))
            .andExpect(jsonPath("$.[*].projectStatus").value(hasItem(DEFAULT_PROJECT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].projectNoBlocks").value(hasItem(DEFAULT_PROJECT_NO_BLOCKS)))
            .andExpect(jsonPath("$.[*].projectNoFloors").value(hasItem(DEFAULT_PROJECT_NO_FLOORS)))
            .andExpect(jsonPath("$.[*].projectNoApartments").value(hasItem(DEFAULT_PROJECT_NO_APARTMENTS)))
            .andExpect(jsonPath("$.[*].projectNoShophouse").value(hasItem(DEFAULT_PROJECT_NO_SHOPHOUSE)))
            .andExpect(jsonPath("$.[*].projectDescription").value(hasItem(DEFAULT_PROJECT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].projectMinSellPrice").value(hasItem(DEFAULT_PROJECT_MIN_SELL_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].projectMaxSellPrice").value(hasItem(DEFAULT_PROJECT_MAX_SELL_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].projectSellPriceUnit").value(hasItem(DEFAULT_PROJECT_SELL_PRICE_UNIT.toString())))
            .andExpect(jsonPath("$.[*].projectMinRentPrice").value(hasItem(DEFAULT_PROJECT_MIN_RENT_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].projectMaxRentPrice").value(hasItem(DEFAULT_PROJECT_MAX_RENT_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].projectRentPriceUnit").value(hasItem(DEFAULT_PROJECT_RENT_PRICE_UNIT.toString())))
            .andExpect(jsonPath("$.[*].projectStartedDate").value(hasItem(DEFAULT_PROJECT_STARTED_DATE.toString())))
            .andExpect(jsonPath("$.[*].projectFinishingDate").value(hasItem(DEFAULT_PROJECT_FINISHING_DATE.toString())))
            .andExpect(jsonPath("$.[*].projectMinApartmentSquare").value(hasItem(DEFAULT_PROJECT_MIN_APARTMENT_SQUARE)))
            .andExpect(jsonPath("$.[*].projectMaxApartmentSquare").value(hasItem(DEFAULT_PROJECT_MAX_APARTMENT_SQUARE)))
            .andExpect(jsonPath("$.[*].projectGreenSpace").value(hasItem(DEFAULT_PROJECT_GREEN_SPACE)))
            .andExpect(jsonPath("$.[*].projectBuildingDensity").value(hasItem(DEFAULT_PROJECT_BUILDING_DENSITY)))
            .andExpect(jsonPath("$.[*].projectDesignCompany").value(hasItem(DEFAULT_PROJECT_DESIGN_COMPANY.toString())))
            .andExpect(jsonPath("$.[*].projectCarPark").value(hasItem(DEFAULT_PROJECT_CAR_PARK.booleanValue())))
            .andExpect(jsonPath("$.[*].projectBbqCourt").value(hasItem(DEFAULT_PROJECT_BBQ_COURT.booleanValue())))
            .andExpect(jsonPath("$.[*].projectElevator").value(hasItem(DEFAULT_PROJECT_ELEVATOR.booleanValue())))
            .andExpect(jsonPath("$.[*].projectShoppingCenter").value(hasItem(DEFAULT_PROJECT_SHOPPING_CENTER.booleanValue())))
            .andExpect(jsonPath("$.[*].projectSwimmingPool").value(hasItem(DEFAULT_PROJECT_SWIMMING_POOL.booleanValue())))
            .andExpect(jsonPath("$.[*].projectCommunityRoom").value(hasItem(DEFAULT_PROJECT_COMMUNITY_ROOM.booleanValue())))
            .andExpect(jsonPath("$.[*].projectGym").value(hasItem(DEFAULT_PROJECT_GYM.booleanValue())))
            .andExpect(jsonPath("$.[*].projectCityPark").value(hasItem(DEFAULT_PROJECT_CITY_PARK.booleanValue())))
            .andExpect(jsonPath("$.[*].projectGuard").value(hasItem(DEFAULT_PROJECT_GUARD.booleanValue())))
            .andExpect(jsonPath("$.[*].projectPlayGround").value(hasItem(DEFAULT_PROJECT_PLAY_GROUND.booleanValue())))
            .andExpect(jsonPath("$.[*].longitude").value(hasItem(DEFAULT_LONGITUDE.doubleValue())))
            .andExpect(jsonPath("$.[*].latitude").value(hasItem(DEFAULT_LATITUDE.doubleValue())))
            .andExpect(jsonPath("$.[*].projectSeenCount").value(hasItem(DEFAULT_PROJECT_SEEN_COUNT.intValue())))
            .andExpect(jsonPath("$.[*].projectAvailable").value(hasItem(DEFAULT_PROJECT_AVAILABLE.booleanValue())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultProjectShouldNotBeFound(String filter) throws Exception {
        restProjectMockMvc.perform(get("/api/projects?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @Transactional
    public void getNonExistingProject() throws Exception {
        // Get the project
        restProjectMockMvc.perform(get("/api/projects/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProject() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        int databaseSizeBeforeUpdate = projectRepository.findAll().size();

        // Update the project
        Project updatedProject = projectRepository.findById(project.getId()).get();
        // Disconnect from session so that the updates on updatedProject are not directly saved in db
        em.detach(updatedProject);
        updatedProject
            .projectName(UPDATED_PROJECT_NAME)
            .projectAlias(UPDATED_PROJECT_ALIAS)
            .projectAvatarUrl(UPDATED_PROJECT_AVATAR_URL)
            .projectDistrict(UPDATED_PROJECT_DISTRICT)
            .projectProvince(UPDATED_PROJECT_PROVINCE)
            .residentialAreaId(UPDATED_RESIDENTIAL_AREA_ID)
            .projectRoad(UPDATED_PROJECT_ROAD)
            .projectWard(UPDATED_PROJECT_WARD)
            .projectStatus(UPDATED_PROJECT_STATUS)
            .projectNoBlocks(UPDATED_PROJECT_NO_BLOCKS)
            .projectNoFloors(UPDATED_PROJECT_NO_FLOORS)
            .projectNoApartments(UPDATED_PROJECT_NO_APARTMENTS)
            .projectNoShophouse(UPDATED_PROJECT_NO_SHOPHOUSE)
            .projectDescription(UPDATED_PROJECT_DESCRIPTION)
            .projectMinSellPrice(UPDATED_PROJECT_MIN_SELL_PRICE)
            .projectMaxSellPrice(UPDATED_PROJECT_MAX_SELL_PRICE)
            .projectSellPriceUnit(UPDATED_PROJECT_SELL_PRICE_UNIT)
            .projectMinRentPrice(UPDATED_PROJECT_MIN_RENT_PRICE)
            .projectMaxRentPrice(UPDATED_PROJECT_MAX_RENT_PRICE)
            .projectRentPriceUnit(UPDATED_PROJECT_RENT_PRICE_UNIT)
            .projectStartedDate(UPDATED_PROJECT_STARTED_DATE)
            .projectFinishingDate(UPDATED_PROJECT_FINISHING_DATE)
            .projectMinApartmentSquare(UPDATED_PROJECT_MIN_APARTMENT_SQUARE)
            .projectMaxApartmentSquare(UPDATED_PROJECT_MAX_APARTMENT_SQUARE)
            .projectGreenSpace(UPDATED_PROJECT_GREEN_SPACE)
            .projectBuildingDensity(UPDATED_PROJECT_BUILDING_DENSITY)
            .projectDesignCompany(UPDATED_PROJECT_DESIGN_COMPANY)
            .projectCarPark(UPDATED_PROJECT_CAR_PARK)
            .projectBbqCourt(UPDATED_PROJECT_BBQ_COURT)
            .projectElevator(UPDATED_PROJECT_ELEVATOR)
            .projectShoppingCenter(UPDATED_PROJECT_SHOPPING_CENTER)
            .projectSwimmingPool(UPDATED_PROJECT_SWIMMING_POOL)
            .projectCommunityRoom(UPDATED_PROJECT_COMMUNITY_ROOM)
            .projectGym(UPDATED_PROJECT_GYM)
            .projectCityPark(UPDATED_PROJECT_CITY_PARK)
            .projectGuard(UPDATED_PROJECT_GUARD)
            .projectPlayGround(UPDATED_PROJECT_PLAY_GROUND)
            .longitude(UPDATED_LONGITUDE)
            .latitude(UPDATED_LATITUDE)
            .projectSeenCount(UPDATED_PROJECT_SEEN_COUNT)
            .projectAvailable(UPDATED_PROJECT_AVAILABLE);
        ProjectDTO projectDTO = projectMapper.toDto(updatedProject);

        restProjectMockMvc.perform(put("/api/projects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectDTO)))
            .andExpect(status().isOk());

        // Validate the Project in the database
        List<Project> projectList = projectRepository.findAll();
        assertThat(projectList).hasSize(databaseSizeBeforeUpdate);
        Project testProject = projectList.get(projectList.size() - 1);
        assertThat(testProject.getProjectName()).isEqualTo(UPDATED_PROJECT_NAME);
        assertThat(testProject.getProjectAlias()).isEqualTo(UPDATED_PROJECT_ALIAS);
        assertThat(testProject.getProjectAvatarUrl()).isEqualTo(UPDATED_PROJECT_AVATAR_URL);
        assertThat(testProject.getProjectDistrict()).isEqualTo(UPDATED_PROJECT_DISTRICT);
        assertThat(testProject.getProjectProvince()).isEqualTo(UPDATED_PROJECT_PROVINCE);
        assertThat(testProject.getResidentialAreaId()).isEqualTo(UPDATED_RESIDENTIAL_AREA_ID);
        assertThat(testProject.getProjectRoad()).isEqualTo(UPDATED_PROJECT_ROAD);
        assertThat(testProject.getProjectWard()).isEqualTo(UPDATED_PROJECT_WARD);
        assertThat(testProject.getProjectStatus()).isEqualTo(UPDATED_PROJECT_STATUS);
        assertThat(testProject.getProjectNoBlocks()).isEqualTo(UPDATED_PROJECT_NO_BLOCKS);
        assertThat(testProject.getProjectNoFloors()).isEqualTo(UPDATED_PROJECT_NO_FLOORS);
        assertThat(testProject.getProjectNoApartments()).isEqualTo(UPDATED_PROJECT_NO_APARTMENTS);
        assertThat(testProject.getProjectNoShophouse()).isEqualTo(UPDATED_PROJECT_NO_SHOPHOUSE);
        assertThat(testProject.getProjectDescription()).isEqualTo(UPDATED_PROJECT_DESCRIPTION);
        assertThat(testProject.getProjectMinSellPrice()).isEqualTo(UPDATED_PROJECT_MIN_SELL_PRICE);
        assertThat(testProject.getProjectMaxSellPrice()).isEqualTo(UPDATED_PROJECT_MAX_SELL_PRICE);
        assertThat(testProject.getProjectSellPriceUnit()).isEqualTo(UPDATED_PROJECT_SELL_PRICE_UNIT);
        assertThat(testProject.getProjectMinRentPrice()).isEqualTo(UPDATED_PROJECT_MIN_RENT_PRICE);
        assertThat(testProject.getProjectMaxRentPrice()).isEqualTo(UPDATED_PROJECT_MAX_RENT_PRICE);
        assertThat(testProject.getProjectRentPriceUnit()).isEqualTo(UPDATED_PROJECT_RENT_PRICE_UNIT);
        assertThat(testProject.getProjectStartedDate()).isEqualTo(UPDATED_PROJECT_STARTED_DATE);
        assertThat(testProject.getProjectFinishingDate()).isEqualTo(UPDATED_PROJECT_FINISHING_DATE);
        assertThat(testProject.getProjectMinApartmentSquare()).isEqualTo(UPDATED_PROJECT_MIN_APARTMENT_SQUARE);
        assertThat(testProject.getProjectMaxApartmentSquare()).isEqualTo(UPDATED_PROJECT_MAX_APARTMENT_SQUARE);
        assertThat(testProject.getProjectGreenSpace()).isEqualTo(UPDATED_PROJECT_GREEN_SPACE);
        assertThat(testProject.getProjectBuildingDensity()).isEqualTo(UPDATED_PROJECT_BUILDING_DENSITY);
        assertThat(testProject.getProjectDesignCompany()).isEqualTo(UPDATED_PROJECT_DESIGN_COMPANY);
        assertThat(testProject.isProjectCarPark()).isEqualTo(UPDATED_PROJECT_CAR_PARK);
        assertThat(testProject.isProjectBbqCourt()).isEqualTo(UPDATED_PROJECT_BBQ_COURT);
        assertThat(testProject.isProjectElevator()).isEqualTo(UPDATED_PROJECT_ELEVATOR);
        assertThat(testProject.isProjectShoppingCenter()).isEqualTo(UPDATED_PROJECT_SHOPPING_CENTER);
        assertThat(testProject.isProjectSwimmingPool()).isEqualTo(UPDATED_PROJECT_SWIMMING_POOL);
        assertThat(testProject.isProjectCommunityRoom()).isEqualTo(UPDATED_PROJECT_COMMUNITY_ROOM);
        assertThat(testProject.isProjectGym()).isEqualTo(UPDATED_PROJECT_GYM);
        assertThat(testProject.isProjectCityPark()).isEqualTo(UPDATED_PROJECT_CITY_PARK);
        assertThat(testProject.isProjectGuard()).isEqualTo(UPDATED_PROJECT_GUARD);
        assertThat(testProject.isProjectPlayGround()).isEqualTo(UPDATED_PROJECT_PLAY_GROUND);
        assertThat(testProject.getLongitude()).isEqualTo(UPDATED_LONGITUDE);
        assertThat(testProject.getLatitude()).isEqualTo(UPDATED_LATITUDE);
        assertThat(testProject.getProjectSeenCount()).isEqualTo(UPDATED_PROJECT_SEEN_COUNT);
        assertThat(testProject.isProjectAvailable()).isEqualTo(UPDATED_PROJECT_AVAILABLE);
    }

    @Test
    @Transactional
    public void updateNonExistingProject() throws Exception {
        int databaseSizeBeforeUpdate = projectRepository.findAll().size();

        // Create the Project
        ProjectDTO projectDTO = projectMapper.toDto(project);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restProjectMockMvc.perform(put("/api/projects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Project in the database
        List<Project> projectList = projectRepository.findAll();
        assertThat(projectList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProject() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        int databaseSizeBeforeDelete = projectRepository.findAll().size();

        // Get the project
        restProjectMockMvc.perform(delete("/api/projects/{id}", project.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Project> projectList = projectRepository.findAll();
        assertThat(projectList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Project.class);
        Project project1 = new Project();
        project1.setId(1L);
        Project project2 = new Project();
        project2.setId(project1.getId());
        assertThat(project1).isEqualTo(project2);
        project2.setId(2L);
        assertThat(project1).isNotEqualTo(project2);
        project1.setId(null);
        assertThat(project1).isNotEqualTo(project2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProjectDTO.class);
        ProjectDTO projectDTO1 = new ProjectDTO();
        projectDTO1.setId(1L);
        ProjectDTO projectDTO2 = new ProjectDTO();
        assertThat(projectDTO1).isNotEqualTo(projectDTO2);
        projectDTO2.setId(projectDTO1.getId());
        assertThat(projectDTO1).isEqualTo(projectDTO2);
        projectDTO2.setId(2L);
        assertThat(projectDTO1).isNotEqualTo(projectDTO2);
        projectDTO1.setId(null);
        assertThat(projectDTO1).isNotEqualTo(projectDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(projectMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(projectMapper.fromId(null)).isNull();
    }
}
