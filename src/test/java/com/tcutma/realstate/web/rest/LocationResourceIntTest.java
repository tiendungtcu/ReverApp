package com.tcutma.realstate.web.rest;

import com.tcutma.realstate.RiverApp;

import com.tcutma.realstate.domain.Location;
import com.tcutma.realstate.repository.LocationRepository;
import com.tcutma.realstate.service.LocationService;
import com.tcutma.realstate.service.dto.LocationDTO;
import com.tcutma.realstate.service.mapper.LocationMapper;
import com.tcutma.realstate.web.rest.errors.ExceptionTranslator;
import com.tcutma.realstate.service.dto.LocationCriteria;
import com.tcutma.realstate.service.LocationQueryService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


import static com.tcutma.realstate.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the LocationResource REST controller.
 *
 * @see LocationResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RiverApp.class)
public class LocationResourceIntTest {

    private static final String DEFAULT_LOCATION_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LOCATION_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LOCATION_FULL_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_LOCATION_FULL_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_LOCATION_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_LOCATION_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_LOCATION_ROAD = "AAAAAAAAAA";
    private static final String UPDATED_LOCATION_ROAD = "BBBBBBBBBB";

    private static final String DEFAULT_LOCATION_WARD = "AAAAAAAAAA";
    private static final String UPDATED_LOCATION_WARD = "BBBBBBBBBB";

    private static final String DEFAULT_LOCATION_DISTRICT = "AAAAAAAAAA";
    private static final String UPDATED_LOCATION_DISTRICT = "BBBBBBBBBB";

    private static final String DEFAULT_LOCATION_PROVINCE = "AAAAAAAAAA";
    private static final String UPDATED_LOCATION_PROVINCE = "BBBBBBBBBB";

    private static final String DEFAULT_LOCATION_GMAP_URL = "AAAAAAAAAA";
    private static final String UPDATED_LOCATION_GMAP_URL = "BBBBBBBBBB";

    private static final Double DEFAULT_LONGITUDE = 1D;
    private static final Double UPDATED_LONGITUDE = 2D;

    private static final Double DEFAULT_LATITUDE = 1D;
    private static final Double UPDATED_LATITUDE = 2D;

    private static final String DEFAULT_LOCATION_ZIP_CODE = "AAAAAAAAAA";
    private static final String UPDATED_LOCATION_ZIP_CODE = "BBBBBBBBBB";

    @Autowired
    private LocationRepository locationRepository;


    @Autowired
    private LocationMapper locationMapper;
    

    @Autowired
    private LocationService locationService;

    @Autowired
    private LocationQueryService locationQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restLocationMockMvc;

    private Location location;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LocationResource locationResource = new LocationResource(locationService, locationQueryService);
        this.restLocationMockMvc = MockMvcBuilders.standaloneSetup(locationResource)
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
    public static Location createEntity(EntityManager em) {
        Location location = new Location()
            .locationName(DEFAULT_LOCATION_NAME)
            .locationFullAddress(DEFAULT_LOCATION_FULL_ADDRESS)
            .locationNumber(DEFAULT_LOCATION_NUMBER)
            .locationRoad(DEFAULT_LOCATION_ROAD)
            .locationWard(DEFAULT_LOCATION_WARD)
            .locationDistrict(DEFAULT_LOCATION_DISTRICT)
            .locationProvince(DEFAULT_LOCATION_PROVINCE)
            .locationGmapUrl(DEFAULT_LOCATION_GMAP_URL)
            .longitude(DEFAULT_LONGITUDE)
            .latitude(DEFAULT_LATITUDE)
            .locationZipCode(DEFAULT_LOCATION_ZIP_CODE);
        return location;
    }

    @Before
    public void initTest() {
        location = createEntity(em);
    }

    @Test
    @Transactional
    public void createLocation() throws Exception {
        int databaseSizeBeforeCreate = locationRepository.findAll().size();

        // Create the Location
        LocationDTO locationDTO = locationMapper.toDto(location);
        restLocationMockMvc.perform(post("/api/locations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(locationDTO)))
            .andExpect(status().isCreated());

        // Validate the Location in the database
        List<Location> locationList = locationRepository.findAll();
        assertThat(locationList).hasSize(databaseSizeBeforeCreate + 1);
        Location testLocation = locationList.get(locationList.size() - 1);
        assertThat(testLocation.getLocationName()).isEqualTo(DEFAULT_LOCATION_NAME);
        assertThat(testLocation.getLocationFullAddress()).isEqualTo(DEFAULT_LOCATION_FULL_ADDRESS);
        assertThat(testLocation.getLocationNumber()).isEqualTo(DEFAULT_LOCATION_NUMBER);
        assertThat(testLocation.getLocationRoad()).isEqualTo(DEFAULT_LOCATION_ROAD);
        assertThat(testLocation.getLocationWard()).isEqualTo(DEFAULT_LOCATION_WARD);
        assertThat(testLocation.getLocationDistrict()).isEqualTo(DEFAULT_LOCATION_DISTRICT);
        assertThat(testLocation.getLocationProvince()).isEqualTo(DEFAULT_LOCATION_PROVINCE);
        assertThat(testLocation.getLocationGmapUrl()).isEqualTo(DEFAULT_LOCATION_GMAP_URL);
        assertThat(testLocation.getLongitude()).isEqualTo(DEFAULT_LONGITUDE);
        assertThat(testLocation.getLatitude()).isEqualTo(DEFAULT_LATITUDE);
        assertThat(testLocation.getLocationZipCode()).isEqualTo(DEFAULT_LOCATION_ZIP_CODE);
    }

    @Test
    @Transactional
    public void createLocationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = locationRepository.findAll().size();

        // Create the Location with an existing ID
        location.setId(1L);
        LocationDTO locationDTO = locationMapper.toDto(location);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLocationMockMvc.perform(post("/api/locations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(locationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Location in the database
        List<Location> locationList = locationRepository.findAll();
        assertThat(locationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkLongitudeIsRequired() throws Exception {
        int databaseSizeBeforeTest = locationRepository.findAll().size();
        // set the field null
        location.setLongitude(null);

        // Create the Location, which fails.
        LocationDTO locationDTO = locationMapper.toDto(location);

        restLocationMockMvc.perform(post("/api/locations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(locationDTO)))
            .andExpect(status().isBadRequest());

        List<Location> locationList = locationRepository.findAll();
        assertThat(locationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLatitudeIsRequired() throws Exception {
        int databaseSizeBeforeTest = locationRepository.findAll().size();
        // set the field null
        location.setLatitude(null);

        // Create the Location, which fails.
        LocationDTO locationDTO = locationMapper.toDto(location);

        restLocationMockMvc.perform(post("/api/locations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(locationDTO)))
            .andExpect(status().isBadRequest());

        List<Location> locationList = locationRepository.findAll();
        assertThat(locationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllLocations() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList
        restLocationMockMvc.perform(get("/api/locations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(location.getId().intValue())))
            .andExpect(jsonPath("$.[*].locationName").value(hasItem(DEFAULT_LOCATION_NAME.toString())))
            .andExpect(jsonPath("$.[*].locationFullAddress").value(hasItem(DEFAULT_LOCATION_FULL_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].locationNumber").value(hasItem(DEFAULT_LOCATION_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].locationRoad").value(hasItem(DEFAULT_LOCATION_ROAD.toString())))
            .andExpect(jsonPath("$.[*].locationWard").value(hasItem(DEFAULT_LOCATION_WARD.toString())))
            .andExpect(jsonPath("$.[*].locationDistrict").value(hasItem(DEFAULT_LOCATION_DISTRICT.toString())))
            .andExpect(jsonPath("$.[*].locationProvince").value(hasItem(DEFAULT_LOCATION_PROVINCE.toString())))
            .andExpect(jsonPath("$.[*].locationGmapUrl").value(hasItem(DEFAULT_LOCATION_GMAP_URL.toString())))
            .andExpect(jsonPath("$.[*].longitude").value(hasItem(DEFAULT_LONGITUDE.doubleValue())))
            .andExpect(jsonPath("$.[*].latitude").value(hasItem(DEFAULT_LATITUDE.doubleValue())))
            .andExpect(jsonPath("$.[*].locationZipCode").value(hasItem(DEFAULT_LOCATION_ZIP_CODE.toString())));
    }
    

    @Test
    @Transactional
    public void getLocation() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get the location
        restLocationMockMvc.perform(get("/api/locations/{id}", location.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(location.getId().intValue()))
            .andExpect(jsonPath("$.locationName").value(DEFAULT_LOCATION_NAME.toString()))
            .andExpect(jsonPath("$.locationFullAddress").value(DEFAULT_LOCATION_FULL_ADDRESS.toString()))
            .andExpect(jsonPath("$.locationNumber").value(DEFAULT_LOCATION_NUMBER.toString()))
            .andExpect(jsonPath("$.locationRoad").value(DEFAULT_LOCATION_ROAD.toString()))
            .andExpect(jsonPath("$.locationWard").value(DEFAULT_LOCATION_WARD.toString()))
            .andExpect(jsonPath("$.locationDistrict").value(DEFAULT_LOCATION_DISTRICT.toString()))
            .andExpect(jsonPath("$.locationProvince").value(DEFAULT_LOCATION_PROVINCE.toString()))
            .andExpect(jsonPath("$.locationGmapUrl").value(DEFAULT_LOCATION_GMAP_URL.toString()))
            .andExpect(jsonPath("$.longitude").value(DEFAULT_LONGITUDE.doubleValue()))
            .andExpect(jsonPath("$.latitude").value(DEFAULT_LATITUDE.doubleValue()))
            .andExpect(jsonPath("$.locationZipCode").value(DEFAULT_LOCATION_ZIP_CODE.toString()));
    }

    @Test
    @Transactional
    public void getAllLocationsByLocationNameIsEqualToSomething() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where locationName equals to DEFAULT_LOCATION_NAME
        defaultLocationShouldBeFound("locationName.equals=" + DEFAULT_LOCATION_NAME);

        // Get all the locationList where locationName equals to UPDATED_LOCATION_NAME
        defaultLocationShouldNotBeFound("locationName.equals=" + UPDATED_LOCATION_NAME);
    }

    @Test
    @Transactional
    public void getAllLocationsByLocationNameIsInShouldWork() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where locationName in DEFAULT_LOCATION_NAME or UPDATED_LOCATION_NAME
        defaultLocationShouldBeFound("locationName.in=" + DEFAULT_LOCATION_NAME + "," + UPDATED_LOCATION_NAME);

        // Get all the locationList where locationName equals to UPDATED_LOCATION_NAME
        defaultLocationShouldNotBeFound("locationName.in=" + UPDATED_LOCATION_NAME);
    }

    @Test
    @Transactional
    public void getAllLocationsByLocationNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where locationName is not null
        defaultLocationShouldBeFound("locationName.specified=true");

        // Get all the locationList where locationName is null
        defaultLocationShouldNotBeFound("locationName.specified=false");
    }

    @Test
    @Transactional
    public void getAllLocationsByLocationFullAddressIsEqualToSomething() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where locationFullAddress equals to DEFAULT_LOCATION_FULL_ADDRESS
        defaultLocationShouldBeFound("locationFullAddress.equals=" + DEFAULT_LOCATION_FULL_ADDRESS);

        // Get all the locationList where locationFullAddress equals to UPDATED_LOCATION_FULL_ADDRESS
        defaultLocationShouldNotBeFound("locationFullAddress.equals=" + UPDATED_LOCATION_FULL_ADDRESS);
    }

    @Test
    @Transactional
    public void getAllLocationsByLocationFullAddressIsInShouldWork() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where locationFullAddress in DEFAULT_LOCATION_FULL_ADDRESS or UPDATED_LOCATION_FULL_ADDRESS
        defaultLocationShouldBeFound("locationFullAddress.in=" + DEFAULT_LOCATION_FULL_ADDRESS + "," + UPDATED_LOCATION_FULL_ADDRESS);

        // Get all the locationList where locationFullAddress equals to UPDATED_LOCATION_FULL_ADDRESS
        defaultLocationShouldNotBeFound("locationFullAddress.in=" + UPDATED_LOCATION_FULL_ADDRESS);
    }

    @Test
    @Transactional
    public void getAllLocationsByLocationFullAddressIsNullOrNotNull() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where locationFullAddress is not null
        defaultLocationShouldBeFound("locationFullAddress.specified=true");

        // Get all the locationList where locationFullAddress is null
        defaultLocationShouldNotBeFound("locationFullAddress.specified=false");
    }

    @Test
    @Transactional
    public void getAllLocationsByLocationNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where locationNumber equals to DEFAULT_LOCATION_NUMBER
        defaultLocationShouldBeFound("locationNumber.equals=" + DEFAULT_LOCATION_NUMBER);

        // Get all the locationList where locationNumber equals to UPDATED_LOCATION_NUMBER
        defaultLocationShouldNotBeFound("locationNumber.equals=" + UPDATED_LOCATION_NUMBER);
    }

    @Test
    @Transactional
    public void getAllLocationsByLocationNumberIsInShouldWork() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where locationNumber in DEFAULT_LOCATION_NUMBER or UPDATED_LOCATION_NUMBER
        defaultLocationShouldBeFound("locationNumber.in=" + DEFAULT_LOCATION_NUMBER + "," + UPDATED_LOCATION_NUMBER);

        // Get all the locationList where locationNumber equals to UPDATED_LOCATION_NUMBER
        defaultLocationShouldNotBeFound("locationNumber.in=" + UPDATED_LOCATION_NUMBER);
    }

    @Test
    @Transactional
    public void getAllLocationsByLocationNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where locationNumber is not null
        defaultLocationShouldBeFound("locationNumber.specified=true");

        // Get all the locationList where locationNumber is null
        defaultLocationShouldNotBeFound("locationNumber.specified=false");
    }

    @Test
    @Transactional
    public void getAllLocationsByLocationRoadIsEqualToSomething() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where locationRoad equals to DEFAULT_LOCATION_ROAD
        defaultLocationShouldBeFound("locationRoad.equals=" + DEFAULT_LOCATION_ROAD);

        // Get all the locationList where locationRoad equals to UPDATED_LOCATION_ROAD
        defaultLocationShouldNotBeFound("locationRoad.equals=" + UPDATED_LOCATION_ROAD);
    }

    @Test
    @Transactional
    public void getAllLocationsByLocationRoadIsInShouldWork() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where locationRoad in DEFAULT_LOCATION_ROAD or UPDATED_LOCATION_ROAD
        defaultLocationShouldBeFound("locationRoad.in=" + DEFAULT_LOCATION_ROAD + "," + UPDATED_LOCATION_ROAD);

        // Get all the locationList where locationRoad equals to UPDATED_LOCATION_ROAD
        defaultLocationShouldNotBeFound("locationRoad.in=" + UPDATED_LOCATION_ROAD);
    }

    @Test
    @Transactional
    public void getAllLocationsByLocationRoadIsNullOrNotNull() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where locationRoad is not null
        defaultLocationShouldBeFound("locationRoad.specified=true");

        // Get all the locationList where locationRoad is null
        defaultLocationShouldNotBeFound("locationRoad.specified=false");
    }

    @Test
    @Transactional
    public void getAllLocationsByLocationWardIsEqualToSomething() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where locationWard equals to DEFAULT_LOCATION_WARD
        defaultLocationShouldBeFound("locationWard.equals=" + DEFAULT_LOCATION_WARD);

        // Get all the locationList where locationWard equals to UPDATED_LOCATION_WARD
        defaultLocationShouldNotBeFound("locationWard.equals=" + UPDATED_LOCATION_WARD);
    }

    @Test
    @Transactional
    public void getAllLocationsByLocationWardIsInShouldWork() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where locationWard in DEFAULT_LOCATION_WARD or UPDATED_LOCATION_WARD
        defaultLocationShouldBeFound("locationWard.in=" + DEFAULT_LOCATION_WARD + "," + UPDATED_LOCATION_WARD);

        // Get all the locationList where locationWard equals to UPDATED_LOCATION_WARD
        defaultLocationShouldNotBeFound("locationWard.in=" + UPDATED_LOCATION_WARD);
    }

    @Test
    @Transactional
    public void getAllLocationsByLocationWardIsNullOrNotNull() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where locationWard is not null
        defaultLocationShouldBeFound("locationWard.specified=true");

        // Get all the locationList where locationWard is null
        defaultLocationShouldNotBeFound("locationWard.specified=false");
    }

    @Test
    @Transactional
    public void getAllLocationsByLocationDistrictIsEqualToSomething() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where locationDistrict equals to DEFAULT_LOCATION_DISTRICT
        defaultLocationShouldBeFound("locationDistrict.equals=" + DEFAULT_LOCATION_DISTRICT);

        // Get all the locationList where locationDistrict equals to UPDATED_LOCATION_DISTRICT
        defaultLocationShouldNotBeFound("locationDistrict.equals=" + UPDATED_LOCATION_DISTRICT);
    }

    @Test
    @Transactional
    public void getAllLocationsByLocationDistrictIsInShouldWork() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where locationDistrict in DEFAULT_LOCATION_DISTRICT or UPDATED_LOCATION_DISTRICT
        defaultLocationShouldBeFound("locationDistrict.in=" + DEFAULT_LOCATION_DISTRICT + "," + UPDATED_LOCATION_DISTRICT);

        // Get all the locationList where locationDistrict equals to UPDATED_LOCATION_DISTRICT
        defaultLocationShouldNotBeFound("locationDistrict.in=" + UPDATED_LOCATION_DISTRICT);
    }

    @Test
    @Transactional
    public void getAllLocationsByLocationDistrictIsNullOrNotNull() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where locationDistrict is not null
        defaultLocationShouldBeFound("locationDistrict.specified=true");

        // Get all the locationList where locationDistrict is null
        defaultLocationShouldNotBeFound("locationDistrict.specified=false");
    }

    @Test
    @Transactional
    public void getAllLocationsByLocationProvinceIsEqualToSomething() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where locationProvince equals to DEFAULT_LOCATION_PROVINCE
        defaultLocationShouldBeFound("locationProvince.equals=" + DEFAULT_LOCATION_PROVINCE);

        // Get all the locationList where locationProvince equals to UPDATED_LOCATION_PROVINCE
        defaultLocationShouldNotBeFound("locationProvince.equals=" + UPDATED_LOCATION_PROVINCE);
    }

    @Test
    @Transactional
    public void getAllLocationsByLocationProvinceIsInShouldWork() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where locationProvince in DEFAULT_LOCATION_PROVINCE or UPDATED_LOCATION_PROVINCE
        defaultLocationShouldBeFound("locationProvince.in=" + DEFAULT_LOCATION_PROVINCE + "," + UPDATED_LOCATION_PROVINCE);

        // Get all the locationList where locationProvince equals to UPDATED_LOCATION_PROVINCE
        defaultLocationShouldNotBeFound("locationProvince.in=" + UPDATED_LOCATION_PROVINCE);
    }

    @Test
    @Transactional
    public void getAllLocationsByLocationProvinceIsNullOrNotNull() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where locationProvince is not null
        defaultLocationShouldBeFound("locationProvince.specified=true");

        // Get all the locationList where locationProvince is null
        defaultLocationShouldNotBeFound("locationProvince.specified=false");
    }

    @Test
    @Transactional
    public void getAllLocationsByLocationGmapUrlIsEqualToSomething() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where locationGmapUrl equals to DEFAULT_LOCATION_GMAP_URL
        defaultLocationShouldBeFound("locationGmapUrl.equals=" + DEFAULT_LOCATION_GMAP_URL);

        // Get all the locationList where locationGmapUrl equals to UPDATED_LOCATION_GMAP_URL
        defaultLocationShouldNotBeFound("locationGmapUrl.equals=" + UPDATED_LOCATION_GMAP_URL);
    }

    @Test
    @Transactional
    public void getAllLocationsByLocationGmapUrlIsInShouldWork() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where locationGmapUrl in DEFAULT_LOCATION_GMAP_URL or UPDATED_LOCATION_GMAP_URL
        defaultLocationShouldBeFound("locationGmapUrl.in=" + DEFAULT_LOCATION_GMAP_URL + "," + UPDATED_LOCATION_GMAP_URL);

        // Get all the locationList where locationGmapUrl equals to UPDATED_LOCATION_GMAP_URL
        defaultLocationShouldNotBeFound("locationGmapUrl.in=" + UPDATED_LOCATION_GMAP_URL);
    }

    @Test
    @Transactional
    public void getAllLocationsByLocationGmapUrlIsNullOrNotNull() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where locationGmapUrl is not null
        defaultLocationShouldBeFound("locationGmapUrl.specified=true");

        // Get all the locationList where locationGmapUrl is null
        defaultLocationShouldNotBeFound("locationGmapUrl.specified=false");
    }

    @Test
    @Transactional
    public void getAllLocationsByLongitudeIsEqualToSomething() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where longitude equals to DEFAULT_LONGITUDE
        defaultLocationShouldBeFound("longitude.equals=" + DEFAULT_LONGITUDE);

        // Get all the locationList where longitude equals to UPDATED_LONGITUDE
        defaultLocationShouldNotBeFound("longitude.equals=" + UPDATED_LONGITUDE);
    }

    @Test
    @Transactional
    public void getAllLocationsByLongitudeIsInShouldWork() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where longitude in DEFAULT_LONGITUDE or UPDATED_LONGITUDE
        defaultLocationShouldBeFound("longitude.in=" + DEFAULT_LONGITUDE + "," + UPDATED_LONGITUDE);

        // Get all the locationList where longitude equals to UPDATED_LONGITUDE
        defaultLocationShouldNotBeFound("longitude.in=" + UPDATED_LONGITUDE);
    }

    @Test
    @Transactional
    public void getAllLocationsByLongitudeIsNullOrNotNull() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where longitude is not null
        defaultLocationShouldBeFound("longitude.specified=true");

        // Get all the locationList where longitude is null
        defaultLocationShouldNotBeFound("longitude.specified=false");
    }

    @Test
    @Transactional
    public void getAllLocationsByLatitudeIsEqualToSomething() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where latitude equals to DEFAULT_LATITUDE
        defaultLocationShouldBeFound("latitude.equals=" + DEFAULT_LATITUDE);

        // Get all the locationList where latitude equals to UPDATED_LATITUDE
        defaultLocationShouldNotBeFound("latitude.equals=" + UPDATED_LATITUDE);
    }

    @Test
    @Transactional
    public void getAllLocationsByLatitudeIsInShouldWork() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where latitude in DEFAULT_LATITUDE or UPDATED_LATITUDE
        defaultLocationShouldBeFound("latitude.in=" + DEFAULT_LATITUDE + "," + UPDATED_LATITUDE);

        // Get all the locationList where latitude equals to UPDATED_LATITUDE
        defaultLocationShouldNotBeFound("latitude.in=" + UPDATED_LATITUDE);
    }

    @Test
    @Transactional
    public void getAllLocationsByLatitudeIsNullOrNotNull() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where latitude is not null
        defaultLocationShouldBeFound("latitude.specified=true");

        // Get all the locationList where latitude is null
        defaultLocationShouldNotBeFound("latitude.specified=false");
    }

    @Test
    @Transactional
    public void getAllLocationsByLocationZipCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where locationZipCode equals to DEFAULT_LOCATION_ZIP_CODE
        defaultLocationShouldBeFound("locationZipCode.equals=" + DEFAULT_LOCATION_ZIP_CODE);

        // Get all the locationList where locationZipCode equals to UPDATED_LOCATION_ZIP_CODE
        defaultLocationShouldNotBeFound("locationZipCode.equals=" + UPDATED_LOCATION_ZIP_CODE);
    }

    @Test
    @Transactional
    public void getAllLocationsByLocationZipCodeIsInShouldWork() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where locationZipCode in DEFAULT_LOCATION_ZIP_CODE or UPDATED_LOCATION_ZIP_CODE
        defaultLocationShouldBeFound("locationZipCode.in=" + DEFAULT_LOCATION_ZIP_CODE + "," + UPDATED_LOCATION_ZIP_CODE);

        // Get all the locationList where locationZipCode equals to UPDATED_LOCATION_ZIP_CODE
        defaultLocationShouldNotBeFound("locationZipCode.in=" + UPDATED_LOCATION_ZIP_CODE);
    }

    @Test
    @Transactional
    public void getAllLocationsByLocationZipCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where locationZipCode is not null
        defaultLocationShouldBeFound("locationZipCode.specified=true");

        // Get all the locationList where locationZipCode is null
        defaultLocationShouldNotBeFound("locationZipCode.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultLocationShouldBeFound(String filter) throws Exception {
        restLocationMockMvc.perform(get("/api/locations?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(location.getId().intValue())))
            .andExpect(jsonPath("$.[*].locationName").value(hasItem(DEFAULT_LOCATION_NAME.toString())))
            .andExpect(jsonPath("$.[*].locationFullAddress").value(hasItem(DEFAULT_LOCATION_FULL_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].locationNumber").value(hasItem(DEFAULT_LOCATION_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].locationRoad").value(hasItem(DEFAULT_LOCATION_ROAD.toString())))
            .andExpect(jsonPath("$.[*].locationWard").value(hasItem(DEFAULT_LOCATION_WARD.toString())))
            .andExpect(jsonPath("$.[*].locationDistrict").value(hasItem(DEFAULT_LOCATION_DISTRICT.toString())))
            .andExpect(jsonPath("$.[*].locationProvince").value(hasItem(DEFAULT_LOCATION_PROVINCE.toString())))
            .andExpect(jsonPath("$.[*].locationGmapUrl").value(hasItem(DEFAULT_LOCATION_GMAP_URL.toString())))
            .andExpect(jsonPath("$.[*].longitude").value(hasItem(DEFAULT_LONGITUDE.doubleValue())))
            .andExpect(jsonPath("$.[*].latitude").value(hasItem(DEFAULT_LATITUDE.doubleValue())))
            .andExpect(jsonPath("$.[*].locationZipCode").value(hasItem(DEFAULT_LOCATION_ZIP_CODE.toString())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultLocationShouldNotBeFound(String filter) throws Exception {
        restLocationMockMvc.perform(get("/api/locations?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @Transactional
    public void getNonExistingLocation() throws Exception {
        // Get the location
        restLocationMockMvc.perform(get("/api/locations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLocation() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        int databaseSizeBeforeUpdate = locationRepository.findAll().size();

        // Update the location
        Location updatedLocation = locationRepository.findById(location.getId()).get();
        // Disconnect from session so that the updates on updatedLocation are not directly saved in db
        em.detach(updatedLocation);
        updatedLocation
            .locationName(UPDATED_LOCATION_NAME)
            .locationFullAddress(UPDATED_LOCATION_FULL_ADDRESS)
            .locationNumber(UPDATED_LOCATION_NUMBER)
            .locationRoad(UPDATED_LOCATION_ROAD)
            .locationWard(UPDATED_LOCATION_WARD)
            .locationDistrict(UPDATED_LOCATION_DISTRICT)
            .locationProvince(UPDATED_LOCATION_PROVINCE)
            .locationGmapUrl(UPDATED_LOCATION_GMAP_URL)
            .longitude(UPDATED_LONGITUDE)
            .latitude(UPDATED_LATITUDE)
            .locationZipCode(UPDATED_LOCATION_ZIP_CODE);
        LocationDTO locationDTO = locationMapper.toDto(updatedLocation);

        restLocationMockMvc.perform(put("/api/locations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(locationDTO)))
            .andExpect(status().isOk());

        // Validate the Location in the database
        List<Location> locationList = locationRepository.findAll();
        assertThat(locationList).hasSize(databaseSizeBeforeUpdate);
        Location testLocation = locationList.get(locationList.size() - 1);
        assertThat(testLocation.getLocationName()).isEqualTo(UPDATED_LOCATION_NAME);
        assertThat(testLocation.getLocationFullAddress()).isEqualTo(UPDATED_LOCATION_FULL_ADDRESS);
        assertThat(testLocation.getLocationNumber()).isEqualTo(UPDATED_LOCATION_NUMBER);
        assertThat(testLocation.getLocationRoad()).isEqualTo(UPDATED_LOCATION_ROAD);
        assertThat(testLocation.getLocationWard()).isEqualTo(UPDATED_LOCATION_WARD);
        assertThat(testLocation.getLocationDistrict()).isEqualTo(UPDATED_LOCATION_DISTRICT);
        assertThat(testLocation.getLocationProvince()).isEqualTo(UPDATED_LOCATION_PROVINCE);
        assertThat(testLocation.getLocationGmapUrl()).isEqualTo(UPDATED_LOCATION_GMAP_URL);
        assertThat(testLocation.getLongitude()).isEqualTo(UPDATED_LONGITUDE);
        assertThat(testLocation.getLatitude()).isEqualTo(UPDATED_LATITUDE);
        assertThat(testLocation.getLocationZipCode()).isEqualTo(UPDATED_LOCATION_ZIP_CODE);
    }

    @Test
    @Transactional
    public void updateNonExistingLocation() throws Exception {
        int databaseSizeBeforeUpdate = locationRepository.findAll().size();

        // Create the Location
        LocationDTO locationDTO = locationMapper.toDto(location);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restLocationMockMvc.perform(put("/api/locations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(locationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Location in the database
        List<Location> locationList = locationRepository.findAll();
        assertThat(locationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLocation() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        int databaseSizeBeforeDelete = locationRepository.findAll().size();

        // Get the location
        restLocationMockMvc.perform(delete("/api/locations/{id}", location.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Location> locationList = locationRepository.findAll();
        assertThat(locationList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Location.class);
        Location location1 = new Location();
        location1.setId(1L);
        Location location2 = new Location();
        location2.setId(location1.getId());
        assertThat(location1).isEqualTo(location2);
        location2.setId(2L);
        assertThat(location1).isNotEqualTo(location2);
        location1.setId(null);
        assertThat(location1).isNotEqualTo(location2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LocationDTO.class);
        LocationDTO locationDTO1 = new LocationDTO();
        locationDTO1.setId(1L);
        LocationDTO locationDTO2 = new LocationDTO();
        assertThat(locationDTO1).isNotEqualTo(locationDTO2);
        locationDTO2.setId(locationDTO1.getId());
        assertThat(locationDTO1).isEqualTo(locationDTO2);
        locationDTO2.setId(2L);
        assertThat(locationDTO1).isNotEqualTo(locationDTO2);
        locationDTO1.setId(null);
        assertThat(locationDTO1).isNotEqualTo(locationDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(locationMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(locationMapper.fromId(null)).isNull();
    }
}
