package com.tcutma.realstate.web.rest;

import com.tcutma.realstate.RiverApp;

import com.tcutma.realstate.domain.Property;
import com.tcutma.realstate.domain.Location;
import com.tcutma.realstate.domain.ResidentialArea;
import com.tcutma.realstate.domain.Tag;
import com.tcutma.realstate.domain.BuildingType;
import com.tcutma.realstate.domain.Photo;
import com.tcutma.realstate.repository.PropertyRepository;
import com.tcutma.realstate.service.PropertyService;
import com.tcutma.realstate.service.dto.PropertyDTO;
import com.tcutma.realstate.service.mapper.PropertyMapper;
import com.tcutma.realstate.web.rest.errors.ExceptionTranslator;
import com.tcutma.realstate.service.dto.PropertyCriteria;
import com.tcutma.realstate.service.PropertyQueryService;

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
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;


import static com.tcutma.realstate.web.rest.TestUtil.sameInstant;
import static com.tcutma.realstate.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.tcutma.realstate.domain.enumeration.TransactionType;
import com.tcutma.realstate.domain.enumeration.UseEstablishment;
import com.tcutma.realstate.domain.enumeration.PriceUnit;
import com.tcutma.realstate.domain.enumeration.PriceUnit;
/**
 * Test class for the PropertyResource REST controller.
 *
 * @see PropertyResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RiverApp.class)
public class PropertyResourceIntTest {

    private static final String DEFAULT_PROPERTY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PROPERTY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_PROPERTY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PROPERTY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PROPERTY_ALIAS = "AAAAAAAAAA";
    private static final String UPDATED_PROPERTY_ALIAS = "BBBBBBBBBB";

    private static final TransactionType DEFAULT_PROPERTY_TRANSACTION = TransactionType.SELL;
    private static final TransactionType UPDATED_PROPERTY_TRANSACTION = TransactionType.BUY;

    private static final String DEFAULT_PROPERTY_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PROPERTY_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_PROPERTY_ROAD = "AAAAAAAAAA";
    private static final String UPDATED_PROPERTY_ROAD = "BBBBBBBBBB";

    private static final String DEFAULT_PROPERTY_WARD = "AAAAAAAAAA";
    private static final String UPDATED_PROPERTY_WARD = "BBBBBBBBBB";

    private static final String DEFAULT_PROPERTY_DISTRICT = "AAAAAAAAAA";
    private static final String UPDATED_PROPERTY_DISTRICT = "BBBBBBBBBB";

    private static final String DEFAULT_PROPERTY_PROVINCE = "AAAAAAAAAA";
    private static final String UPDATED_PROPERTY_PROVINCE = "BBBBBBBBBB";

    private static final String DEFAULT_PROPERTY_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_PROPERTY_DESCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_PROPERTY_BED_ROOMS = 1;
    private static final Integer UPDATED_PROPERTY_BED_ROOMS = 2;

    private static final Integer DEFAULT_PROPERTY_BATH_ROOMS = 1;
    private static final Integer UPDATED_PROPERTY_BATH_ROOMS = 2;

    private static final Double DEFAULT_PROPERTY_SQUARE = 1D;
    private static final Double UPDATED_PROPERTY_SQUARE = 2D;

    private static final UseEstablishment DEFAULT_PROPERTY_USE_PURPOSE = UseEstablishment.LODGE;
    private static final UseEstablishment UPDATED_PROPERTY_USE_PURPOSE = UseEstablishment.PUB;

    private static final String DEFAULT_PROPERTY_OWNER_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_PROPERTY_OWNER_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_PROPERTY_TOWER = "AAAAAAAAAA";
    private static final String UPDATED_PROPERTY_TOWER = "BBBBBBBBBB";

    private static final Double DEFAULT_PROPERTY_RENT_PRICE = 1D;
    private static final Double UPDATED_PROPERTY_RENT_PRICE = 2D;

    private static final PriceUnit DEFAULT_PROPERTY_RENT_UNIT = PriceUnit.THOUSAND;
    private static final PriceUnit UPDATED_PROPERTY_RENT_UNIT = PriceUnit.MILLION;

    private static final ZonedDateTime DEFAULT_PROPERTY_RENT_STARTED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_PROPERTY_RENT_STARTED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Double DEFAULT_PROPERTY_SELL_PRICE = 1D;
    private static final Double UPDATED_PROPERTY_SELL_PRICE = 2D;

    private static final PriceUnit DEFAULT_PROPERTY_SELL_UNIT = PriceUnit.THOUSAND;
    private static final PriceUnit UPDATED_PROPERTY_SELL_UNIT = PriceUnit.MILLION;

    private static final ZonedDateTime DEFAULT_PROPERTY_SELL_STARTED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_PROPERTY_SELL_STARTED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Boolean DEFAULT_PROPERTY_SOFA = false;
    private static final Boolean UPDATED_PROPERTY_SOFA = true;

    private static final Boolean DEFAULT_PROPERTY_DINING_TABLE = false;
    private static final Boolean UPDATED_PROPERTY_DINING_TABLE = true;

    private static final Boolean DEFAULT_PROPERTY_KITCHEN = false;
    private static final Boolean UPDATED_PROPERTY_KITCHEN = true;

    private static final Boolean DEFAULT_PROPERTY_CABINET_KITCHEN = false;
    private static final Boolean UPDATED_PROPERTY_CABINET_KITCHEN = true;

    private static final Boolean DEFAULT_PROPERTY_KITCHEN_EQUIPMENT = false;
    private static final Boolean UPDATED_PROPERTY_KITCHEN_EQUIPMENT = true;

    private static final Boolean DEFAULT_PROPERTY_WARDROBE = false;
    private static final Boolean UPDATED_PROPERTY_WARDROBE = true;

    private static final Boolean DEFAULT_PROPERTY_MAKEUP_TABLE = false;
    private static final Boolean UPDATED_PROPERTY_MAKEUP_TABLE = true;

    private static final Boolean DEFAULT_PROPERTY_DESK = false;
    private static final Boolean UPDATED_PROPERTY_DESK = true;

    private static final Boolean DEFAULT_PROPERTY_TIVI = false;
    private static final Boolean UPDATED_PROPERTY_TIVI = true;

    private static final Boolean DEFAULT_PROPERTY_WASHING_MACHINE = false;
    private static final Boolean UPDATED_PROPERTY_WASHING_MACHINE = true;

    private static final Boolean DEFAULT_PROPERTY_REFRIGERATOR = false;
    private static final Boolean UPDATED_PROPERTY_REFRIGERATOR = true;

    private static final Boolean DEFAULT_PROPERTY_AIRCONDITION = false;
    private static final Boolean UPDATED_PROPERTY_AIRCONDITION = true;

    private static final Boolean DEFAULT_PROPERTY_MICROWAVE = false;
    private static final Boolean UPDATED_PROPERTY_MICROWAVE = true;

    private static final Boolean DEFAULT_PROPERTY_WATER_HEATER = false;
    private static final Boolean UPDATED_PROPERTY_WATER_HEATER = true;

    private static final Boolean DEFAULT_PROPERTY_BED = false;
    private static final Boolean UPDATED_PROPERTY_BED = true;

    private static final Boolean DEFAULT_PROPERTY_HEATER = false;
    private static final Boolean UPDATED_PROPERTY_HEATER = true;

    private static final Boolean DEFAULT_PROPERTY_AUDIO_EQUIPMENT = false;
    private static final Boolean UPDATED_PROPERTY_AUDIO_EQUIPMENT = true;

    private static final Boolean DEFAULT_PROPERTY_INTERNET = false;
    private static final Boolean UPDATED_PROPERTY_INTERNET = true;

    private static final Boolean DEFAULT_PROPERTY_CABLE_TIVI = false;
    private static final Boolean UPDATED_PROPERTY_CABLE_TIVI = true;

    private static final Boolean DEFAULT_PROPERTY_PET_PERMISSION = false;
    private static final Boolean UPDATED_PROPERTY_PET_PERMISSION = true;

    private static final Boolean DEFAULT_PROPERTY_ELEVATOR = false;
    private static final Boolean UPDATED_PROPERTY_ELEVATOR = true;

    private static final Boolean DEFAULT_PROPERTY_SWIMMING_POOL = false;
    private static final Boolean UPDATED_PROPERTY_SWIMMING_POOL = true;

    private static final Boolean DEFAULT_PROPERTY_GYM = false;
    private static final Boolean UPDATED_PROPERTY_GYM = true;

    private static final Boolean DEFAULT_PROPERTY_FUNCTIONAL_AREA = false;
    private static final Boolean UPDATED_PROPERTY_FUNCTIONAL_AREA = true;

    private static final Boolean DEFAULT_PROPERTY_OPEN_24_H = false;
    private static final Boolean UPDATED_PROPERTY_OPEN_24_H = true;

    private static final Boolean DEFAULT_PROPERTY_CAR_PARK = false;
    private static final Boolean UPDATED_PROPERTY_CAR_PARK = true;

    private static final Boolean DEFAULT_PROPERTY_BALCONY = false;
    private static final Boolean UPDATED_PROPERTY_BALCONY = true;

    private static final Boolean DEFAULT_PROPERTY_SAUNA = false;
    private static final Boolean UPDATED_PROPERTY_SAUNA = true;

    private static final Boolean DEFAULT_PROPERTY_STEAM_SAUNA = false;
    private static final Boolean UPDATED_PROPERTY_STEAM_SAUNA = true;

    private static final Boolean DEFAULT_PROPERTY_ATTRACTION = false;
    private static final Boolean UPDATED_PROPERTY_ATTRACTION = true;

    private static final String DEFAULT_PROPERTY_SPECIAL_FEATURE = "AAAAAAAAAA";
    private static final String UPDATED_PROPERTY_SPECIAL_FEATURE = "BBBBBBBBBB";

    private static final String DEFAULT_PROPERTY_FURNITURE_OVERVIEW = "AAAAAAAAAA";
    private static final String UPDATED_PROPERTY_FURNITURE_OVERVIEW = "BBBBBBBBBB";

    private static final String DEFAULT_PROPERTY_LOCATION_OVERVIEW = "AAAAAAAAAA";
    private static final String UPDATED_PROPERTY_LOCATION_OVERVIEW = "BBBBBBBBBB";

    private static final String DEFAULT_PROPERTY_RESIDENTIAL_COMMUNITY = "AAAAAAAAAA";
    private static final String UPDATED_PROPERTY_RESIDENTIAL_COMMUNITY = "BBBBBBBBBB";

    private static final String DEFAULT_PROPERTY_EDUCATIONAL_ASPECT = "AAAAAAAAAA";
    private static final String UPDATED_PROPERTY_EDUCATIONAL_ASPECT = "BBBBBBBBBB";

    private static final String DEFAULT_PROPERTY_EXTRA_INFO = "AAAAAAAAAA";
    private static final String UPDATED_PROPERTY_EXTRA_INFO = "BBBBBBBBBB";

    private static final byte[] DEFAULT_PROPERTY_DRAFT = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_PROPERTY_DRAFT = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_PROPERTY_DRAFT_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_PROPERTY_DRAFT_CONTENT_TYPE = "image/png";

    private static final Double DEFAULT_LONGITUDE = 1D;
    private static final Double UPDATED_LONGITUDE = 2D;

    private static final Double DEFAULT_LATITUDE = 1D;
    private static final Double UPDATED_LATITUDE = 2D;

    private static final Boolean DEFAULT_PROPERTY_GOOD_PRICE = false;
    private static final Boolean UPDATED_PROPERTY_GOOD_PRICE = true;

    private static final Long DEFAULT_PROPERTY_SEEN_COUNT = 1L;
    private static final Long UPDATED_PROPERTY_SEEN_COUNT = 2L;

    private static final Boolean DEFAULT_PROPERTY_IS_SOLD = false;
    private static final Boolean UPDATED_PROPERTY_IS_SOLD = true;

    private static final Boolean DEFAULT_PROPERTY_IS_RENT = false;
    private static final Boolean UPDATED_PROPERTY_IS_RENT = true;

    private static final Boolean DEFAULT_PROPERTY_AVAILABLE = false;
    private static final Boolean UPDATED_PROPERTY_AVAILABLE = true;

    @Autowired
    private PropertyRepository propertyRepository;
    @Mock
    private PropertyRepository propertyRepositoryMock;

    @Autowired
    private PropertyMapper propertyMapper;
    
    @Mock
    private PropertyService propertyServiceMock;

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private PropertyQueryService propertyQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPropertyMockMvc;

    private Property property;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PropertyResource propertyResource = new PropertyResource(propertyService, propertyQueryService);
        this.restPropertyMockMvc = MockMvcBuilders.standaloneSetup(propertyResource)
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
    public static Property createEntity(EntityManager em) {
        Property property = new Property()
            .propertyCode(DEFAULT_PROPERTY_CODE)
            .propertyName(DEFAULT_PROPERTY_NAME)
            .propertyAlias(DEFAULT_PROPERTY_ALIAS)
            .propertyTransaction(DEFAULT_PROPERTY_TRANSACTION)
            .propertyNumber(DEFAULT_PROPERTY_NUMBER)
            .propertyRoad(DEFAULT_PROPERTY_ROAD)
            .propertyWard(DEFAULT_PROPERTY_WARD)
            .propertyDistrict(DEFAULT_PROPERTY_DISTRICT)
            .propertyProvince(DEFAULT_PROPERTY_PROVINCE)
            .propertyDescription(DEFAULT_PROPERTY_DESCRIPTION)
            .propertyBedRooms(DEFAULT_PROPERTY_BED_ROOMS)
            .propertyBathRooms(DEFAULT_PROPERTY_BATH_ROOMS)
            .propertySquare(DEFAULT_PROPERTY_SQUARE)
            .propertyUsePurpose(DEFAULT_PROPERTY_USE_PURPOSE)
            .propertyOwnerType(DEFAULT_PROPERTY_OWNER_TYPE)
            .propertyTower(DEFAULT_PROPERTY_TOWER)
            .propertyRentPrice(DEFAULT_PROPERTY_RENT_PRICE)
            .propertyRentUnit(DEFAULT_PROPERTY_RENT_UNIT)
            .propertyRentStartedDate(DEFAULT_PROPERTY_RENT_STARTED_DATE)
            .propertySellPrice(DEFAULT_PROPERTY_SELL_PRICE)
            .propertySellUnit(DEFAULT_PROPERTY_SELL_UNIT)
            .propertySellStartedDate(DEFAULT_PROPERTY_SELL_STARTED_DATE)
            .propertySofa(DEFAULT_PROPERTY_SOFA)
            .propertyDiningTable(DEFAULT_PROPERTY_DINING_TABLE)
            .propertyKitchen(DEFAULT_PROPERTY_KITCHEN)
            .propertyCabinetKitchen(DEFAULT_PROPERTY_CABINET_KITCHEN)
            .propertyKitchenEquipment(DEFAULT_PROPERTY_KITCHEN_EQUIPMENT)
            .propertyWardrobe(DEFAULT_PROPERTY_WARDROBE)
            .propertyMakeupTable(DEFAULT_PROPERTY_MAKEUP_TABLE)
            .propertyDesk(DEFAULT_PROPERTY_DESK)
            .propertyTivi(DEFAULT_PROPERTY_TIVI)
            .propertyWashingMachine(DEFAULT_PROPERTY_WASHING_MACHINE)
            .propertyRefrigerator(DEFAULT_PROPERTY_REFRIGERATOR)
            .propertyAircondition(DEFAULT_PROPERTY_AIRCONDITION)
            .propertyMicrowave(DEFAULT_PROPERTY_MICROWAVE)
            .propertyWaterHeater(DEFAULT_PROPERTY_WATER_HEATER)
            .propertyBed(DEFAULT_PROPERTY_BED)
            .propertyHeater(DEFAULT_PROPERTY_HEATER)
            .propertyAudioEquipment(DEFAULT_PROPERTY_AUDIO_EQUIPMENT)
            .propertyInternet(DEFAULT_PROPERTY_INTERNET)
            .propertyCableTivi(DEFAULT_PROPERTY_CABLE_TIVI)
            .propertyPetPermission(DEFAULT_PROPERTY_PET_PERMISSION)
            .propertyElevator(DEFAULT_PROPERTY_ELEVATOR)
            .propertySwimmingPool(DEFAULT_PROPERTY_SWIMMING_POOL)
            .propertyGym(DEFAULT_PROPERTY_GYM)
            .propertyFunctionalArea(DEFAULT_PROPERTY_FUNCTIONAL_AREA)
            .propertyOpen24h(DEFAULT_PROPERTY_OPEN_24_H)
            .propertyCarPark(DEFAULT_PROPERTY_CAR_PARK)
            .propertyBalcony(DEFAULT_PROPERTY_BALCONY)
            .propertySauna(DEFAULT_PROPERTY_SAUNA)
            .propertySteamSauna(DEFAULT_PROPERTY_STEAM_SAUNA)
            .propertyAttraction(DEFAULT_PROPERTY_ATTRACTION)
            .propertySpecialFeature(DEFAULT_PROPERTY_SPECIAL_FEATURE)
            .propertyFurnitureOverview(DEFAULT_PROPERTY_FURNITURE_OVERVIEW)
            .propertyLocationOverview(DEFAULT_PROPERTY_LOCATION_OVERVIEW)
            .propertyResidentialCommunity(DEFAULT_PROPERTY_RESIDENTIAL_COMMUNITY)
            .propertyEducationalAspect(DEFAULT_PROPERTY_EDUCATIONAL_ASPECT)
            .propertyExtraInfo(DEFAULT_PROPERTY_EXTRA_INFO)
            .propertyDraft(DEFAULT_PROPERTY_DRAFT)
            .propertyDraftContentType(DEFAULT_PROPERTY_DRAFT_CONTENT_TYPE)
            .longitude(DEFAULT_LONGITUDE)
            .latitude(DEFAULT_LATITUDE)
            .propertyGoodPrice(DEFAULT_PROPERTY_GOOD_PRICE)
            .propertySeenCount(DEFAULT_PROPERTY_SEEN_COUNT)
            .propertyIsSold(DEFAULT_PROPERTY_IS_SOLD)
            .propertyIsRent(DEFAULT_PROPERTY_IS_RENT)
            .propertyAvailable(DEFAULT_PROPERTY_AVAILABLE);
        return property;
    }

    @Before
    public void initTest() {
        property = createEntity(em);
    }

    @Test
    @Transactional
    public void createProperty() throws Exception {
        int databaseSizeBeforeCreate = propertyRepository.findAll().size();

        // Create the Property
        PropertyDTO propertyDTO = propertyMapper.toDto(property);
        restPropertyMockMvc.perform(post("/api/properties")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(propertyDTO)))
            .andExpect(status().isCreated());

        // Validate the Property in the database
        List<Property> propertyList = propertyRepository.findAll();
        assertThat(propertyList).hasSize(databaseSizeBeforeCreate + 1);
        Property testProperty = propertyList.get(propertyList.size() - 1);
        assertThat(testProperty.getPropertyCode()).isEqualTo(DEFAULT_PROPERTY_CODE);
        assertThat(testProperty.getPropertyName()).isEqualTo(DEFAULT_PROPERTY_NAME);
        assertThat(testProperty.getPropertyAlias()).isEqualTo(DEFAULT_PROPERTY_ALIAS);
        assertThat(testProperty.getPropertyTransaction()).isEqualTo(DEFAULT_PROPERTY_TRANSACTION);
        assertThat(testProperty.getPropertyNumber()).isEqualTo(DEFAULT_PROPERTY_NUMBER);
        assertThat(testProperty.getPropertyRoad()).isEqualTo(DEFAULT_PROPERTY_ROAD);
        assertThat(testProperty.getPropertyWard()).isEqualTo(DEFAULT_PROPERTY_WARD);
        assertThat(testProperty.getPropertyDistrict()).isEqualTo(DEFAULT_PROPERTY_DISTRICT);
        assertThat(testProperty.getPropertyProvince()).isEqualTo(DEFAULT_PROPERTY_PROVINCE);
        assertThat(testProperty.getPropertyDescription()).isEqualTo(DEFAULT_PROPERTY_DESCRIPTION);
        assertThat(testProperty.getPropertyBedRooms()).isEqualTo(DEFAULT_PROPERTY_BED_ROOMS);
        assertThat(testProperty.getPropertyBathRooms()).isEqualTo(DEFAULT_PROPERTY_BATH_ROOMS);
        assertThat(testProperty.getPropertySquare()).isEqualTo(DEFAULT_PROPERTY_SQUARE);
        assertThat(testProperty.getPropertyUsePurpose()).isEqualTo(DEFAULT_PROPERTY_USE_PURPOSE);
        assertThat(testProperty.getPropertyOwnerType()).isEqualTo(DEFAULT_PROPERTY_OWNER_TYPE);
        assertThat(testProperty.getPropertyTower()).isEqualTo(DEFAULT_PROPERTY_TOWER);
        assertThat(testProperty.getPropertyRentPrice()).isEqualTo(DEFAULT_PROPERTY_RENT_PRICE);
        assertThat(testProperty.getPropertyRentUnit()).isEqualTo(DEFAULT_PROPERTY_RENT_UNIT);
        assertThat(testProperty.getPropertyRentStartedDate()).isEqualTo(DEFAULT_PROPERTY_RENT_STARTED_DATE);
        assertThat(testProperty.getPropertySellPrice()).isEqualTo(DEFAULT_PROPERTY_SELL_PRICE);
        assertThat(testProperty.getPropertySellUnit()).isEqualTo(DEFAULT_PROPERTY_SELL_UNIT);
        assertThat(testProperty.getPropertySellStartedDate()).isEqualTo(DEFAULT_PROPERTY_SELL_STARTED_DATE);
        assertThat(testProperty.isPropertySofa()).isEqualTo(DEFAULT_PROPERTY_SOFA);
        assertThat(testProperty.isPropertyDiningTable()).isEqualTo(DEFAULT_PROPERTY_DINING_TABLE);
        assertThat(testProperty.isPropertyKitchen()).isEqualTo(DEFAULT_PROPERTY_KITCHEN);
        assertThat(testProperty.isPropertyCabinetKitchen()).isEqualTo(DEFAULT_PROPERTY_CABINET_KITCHEN);
        assertThat(testProperty.isPropertyKitchenEquipment()).isEqualTo(DEFAULT_PROPERTY_KITCHEN_EQUIPMENT);
        assertThat(testProperty.isPropertyWardrobe()).isEqualTo(DEFAULT_PROPERTY_WARDROBE);
        assertThat(testProperty.isPropertyMakeupTable()).isEqualTo(DEFAULT_PROPERTY_MAKEUP_TABLE);
        assertThat(testProperty.isPropertyDesk()).isEqualTo(DEFAULT_PROPERTY_DESK);
        assertThat(testProperty.isPropertyTivi()).isEqualTo(DEFAULT_PROPERTY_TIVI);
        assertThat(testProperty.isPropertyWashingMachine()).isEqualTo(DEFAULT_PROPERTY_WASHING_MACHINE);
        assertThat(testProperty.isPropertyRefrigerator()).isEqualTo(DEFAULT_PROPERTY_REFRIGERATOR);
        assertThat(testProperty.isPropertyAircondition()).isEqualTo(DEFAULT_PROPERTY_AIRCONDITION);
        assertThat(testProperty.isPropertyMicrowave()).isEqualTo(DEFAULT_PROPERTY_MICROWAVE);
        assertThat(testProperty.isPropertyWaterHeater()).isEqualTo(DEFAULT_PROPERTY_WATER_HEATER);
        assertThat(testProperty.isPropertyBed()).isEqualTo(DEFAULT_PROPERTY_BED);
        assertThat(testProperty.isPropertyHeater()).isEqualTo(DEFAULT_PROPERTY_HEATER);
        assertThat(testProperty.isPropertyAudioEquipment()).isEqualTo(DEFAULT_PROPERTY_AUDIO_EQUIPMENT);
        assertThat(testProperty.isPropertyInternet()).isEqualTo(DEFAULT_PROPERTY_INTERNET);
        assertThat(testProperty.isPropertyCableTivi()).isEqualTo(DEFAULT_PROPERTY_CABLE_TIVI);
        assertThat(testProperty.isPropertyPetPermission()).isEqualTo(DEFAULT_PROPERTY_PET_PERMISSION);
        assertThat(testProperty.isPropertyElevator()).isEqualTo(DEFAULT_PROPERTY_ELEVATOR);
        assertThat(testProperty.isPropertySwimmingPool()).isEqualTo(DEFAULT_PROPERTY_SWIMMING_POOL);
        assertThat(testProperty.isPropertyGym()).isEqualTo(DEFAULT_PROPERTY_GYM);
        assertThat(testProperty.isPropertyFunctionalArea()).isEqualTo(DEFAULT_PROPERTY_FUNCTIONAL_AREA);
        assertThat(testProperty.isPropertyOpen24h()).isEqualTo(DEFAULT_PROPERTY_OPEN_24_H);
        assertThat(testProperty.isPropertyCarPark()).isEqualTo(DEFAULT_PROPERTY_CAR_PARK);
        assertThat(testProperty.isPropertyBalcony()).isEqualTo(DEFAULT_PROPERTY_BALCONY);
        assertThat(testProperty.isPropertySauna()).isEqualTo(DEFAULT_PROPERTY_SAUNA);
        assertThat(testProperty.isPropertySteamSauna()).isEqualTo(DEFAULT_PROPERTY_STEAM_SAUNA);
        assertThat(testProperty.isPropertyAttraction()).isEqualTo(DEFAULT_PROPERTY_ATTRACTION);
        assertThat(testProperty.getPropertySpecialFeature()).isEqualTo(DEFAULT_PROPERTY_SPECIAL_FEATURE);
        assertThat(testProperty.getPropertyFurnitureOverview()).isEqualTo(DEFAULT_PROPERTY_FURNITURE_OVERVIEW);
        assertThat(testProperty.getPropertyLocationOverview()).isEqualTo(DEFAULT_PROPERTY_LOCATION_OVERVIEW);
        assertThat(testProperty.getPropertyResidentialCommunity()).isEqualTo(DEFAULT_PROPERTY_RESIDENTIAL_COMMUNITY);
        assertThat(testProperty.getPropertyEducationalAspect()).isEqualTo(DEFAULT_PROPERTY_EDUCATIONAL_ASPECT);
        assertThat(testProperty.getPropertyExtraInfo()).isEqualTo(DEFAULT_PROPERTY_EXTRA_INFO);
        assertThat(testProperty.getPropertyDraft()).isEqualTo(DEFAULT_PROPERTY_DRAFT);
        assertThat(testProperty.getPropertyDraftContentType()).isEqualTo(DEFAULT_PROPERTY_DRAFT_CONTENT_TYPE);
        assertThat(testProperty.getLongitude()).isEqualTo(DEFAULT_LONGITUDE);
        assertThat(testProperty.getLatitude()).isEqualTo(DEFAULT_LATITUDE);
        assertThat(testProperty.isPropertyGoodPrice()).isEqualTo(DEFAULT_PROPERTY_GOOD_PRICE);
        assertThat(testProperty.getPropertySeenCount()).isEqualTo(DEFAULT_PROPERTY_SEEN_COUNT);
        assertThat(testProperty.isPropertyIsSold()).isEqualTo(DEFAULT_PROPERTY_IS_SOLD);
        assertThat(testProperty.isPropertyIsRent()).isEqualTo(DEFAULT_PROPERTY_IS_RENT);
        assertThat(testProperty.isPropertyAvailable()).isEqualTo(DEFAULT_PROPERTY_AVAILABLE);
    }

    @Test
    @Transactional
    public void createPropertyWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = propertyRepository.findAll().size();

        // Create the Property with an existing ID
        property.setId(1L);
        PropertyDTO propertyDTO = propertyMapper.toDto(property);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPropertyMockMvc.perform(post("/api/properties")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(propertyDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Property in the database
        List<Property> propertyList = propertyRepository.findAll();
        assertThat(propertyList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkPropertyCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = propertyRepository.findAll().size();
        // set the field null
        property.setPropertyCode(null);

        // Create the Property, which fails.
        PropertyDTO propertyDTO = propertyMapper.toDto(property);

        restPropertyMockMvc.perform(post("/api/properties")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(propertyDTO)))
            .andExpect(status().isBadRequest());

        List<Property> propertyList = propertyRepository.findAll();
        assertThat(propertyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPropertyNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = propertyRepository.findAll().size();
        // set the field null
        property.setPropertyName(null);

        // Create the Property, which fails.
        PropertyDTO propertyDTO = propertyMapper.toDto(property);

        restPropertyMockMvc.perform(post("/api/properties")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(propertyDTO)))
            .andExpect(status().isBadRequest());

        List<Property> propertyList = propertyRepository.findAll();
        assertThat(propertyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllProperties() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList
        restPropertyMockMvc.perform(get("/api/properties?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(property.getId().intValue())))
            .andExpect(jsonPath("$.[*].propertyCode").value(hasItem(DEFAULT_PROPERTY_CODE.toString())))
            .andExpect(jsonPath("$.[*].propertyName").value(hasItem(DEFAULT_PROPERTY_NAME.toString())))
            .andExpect(jsonPath("$.[*].propertyAlias").value(hasItem(DEFAULT_PROPERTY_ALIAS.toString())))
            .andExpect(jsonPath("$.[*].propertyTransaction").value(hasItem(DEFAULT_PROPERTY_TRANSACTION.toString())))
            .andExpect(jsonPath("$.[*].propertyNumber").value(hasItem(DEFAULT_PROPERTY_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].propertyRoad").value(hasItem(DEFAULT_PROPERTY_ROAD.toString())))
            .andExpect(jsonPath("$.[*].propertyWard").value(hasItem(DEFAULT_PROPERTY_WARD.toString())))
            .andExpect(jsonPath("$.[*].propertyDistrict").value(hasItem(DEFAULT_PROPERTY_DISTRICT.toString())))
            .andExpect(jsonPath("$.[*].propertyProvince").value(hasItem(DEFAULT_PROPERTY_PROVINCE.toString())))
            .andExpect(jsonPath("$.[*].propertyDescription").value(hasItem(DEFAULT_PROPERTY_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].propertyBedRooms").value(hasItem(DEFAULT_PROPERTY_BED_ROOMS)))
            .andExpect(jsonPath("$.[*].propertyBathRooms").value(hasItem(DEFAULT_PROPERTY_BATH_ROOMS)))
            .andExpect(jsonPath("$.[*].propertySquare").value(hasItem(DEFAULT_PROPERTY_SQUARE.doubleValue())))
            .andExpect(jsonPath("$.[*].propertyUsePurpose").value(hasItem(DEFAULT_PROPERTY_USE_PURPOSE.toString())))
            .andExpect(jsonPath("$.[*].propertyOwnerType").value(hasItem(DEFAULT_PROPERTY_OWNER_TYPE.toString())))
            .andExpect(jsonPath("$.[*].propertyTower").value(hasItem(DEFAULT_PROPERTY_TOWER.toString())))
            .andExpect(jsonPath("$.[*].propertyRentPrice").value(hasItem(DEFAULT_PROPERTY_RENT_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].propertyRentUnit").value(hasItem(DEFAULT_PROPERTY_RENT_UNIT.toString())))
            .andExpect(jsonPath("$.[*].propertyRentStartedDate").value(hasItem(sameInstant(DEFAULT_PROPERTY_RENT_STARTED_DATE))))
            .andExpect(jsonPath("$.[*].propertySellPrice").value(hasItem(DEFAULT_PROPERTY_SELL_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].propertySellUnit").value(hasItem(DEFAULT_PROPERTY_SELL_UNIT.toString())))
            .andExpect(jsonPath("$.[*].propertySellStartedDate").value(hasItem(sameInstant(DEFAULT_PROPERTY_SELL_STARTED_DATE))))
            .andExpect(jsonPath("$.[*].propertySofa").value(hasItem(DEFAULT_PROPERTY_SOFA.booleanValue())))
            .andExpect(jsonPath("$.[*].propertyDiningTable").value(hasItem(DEFAULT_PROPERTY_DINING_TABLE.booleanValue())))
            .andExpect(jsonPath("$.[*].propertyKitchen").value(hasItem(DEFAULT_PROPERTY_KITCHEN.booleanValue())))
            .andExpect(jsonPath("$.[*].propertyCabinetKitchen").value(hasItem(DEFAULT_PROPERTY_CABINET_KITCHEN.booleanValue())))
            .andExpect(jsonPath("$.[*].propertyKitchenEquipment").value(hasItem(DEFAULT_PROPERTY_KITCHEN_EQUIPMENT.booleanValue())))
            .andExpect(jsonPath("$.[*].propertyWardrobe").value(hasItem(DEFAULT_PROPERTY_WARDROBE.booleanValue())))
            .andExpect(jsonPath("$.[*].propertyMakeupTable").value(hasItem(DEFAULT_PROPERTY_MAKEUP_TABLE.booleanValue())))
            .andExpect(jsonPath("$.[*].propertyDesk").value(hasItem(DEFAULT_PROPERTY_DESK.booleanValue())))
            .andExpect(jsonPath("$.[*].propertyTivi").value(hasItem(DEFAULT_PROPERTY_TIVI.booleanValue())))
            .andExpect(jsonPath("$.[*].propertyWashingMachine").value(hasItem(DEFAULT_PROPERTY_WASHING_MACHINE.booleanValue())))
            .andExpect(jsonPath("$.[*].propertyRefrigerator").value(hasItem(DEFAULT_PROPERTY_REFRIGERATOR.booleanValue())))
            .andExpect(jsonPath("$.[*].propertyAircondition").value(hasItem(DEFAULT_PROPERTY_AIRCONDITION.booleanValue())))
            .andExpect(jsonPath("$.[*].propertyMicrowave").value(hasItem(DEFAULT_PROPERTY_MICROWAVE.booleanValue())))
            .andExpect(jsonPath("$.[*].propertyWaterHeater").value(hasItem(DEFAULT_PROPERTY_WATER_HEATER.booleanValue())))
            .andExpect(jsonPath("$.[*].propertyBed").value(hasItem(DEFAULT_PROPERTY_BED.booleanValue())))
            .andExpect(jsonPath("$.[*].propertyHeater").value(hasItem(DEFAULT_PROPERTY_HEATER.booleanValue())))
            .andExpect(jsonPath("$.[*].propertyAudioEquipment").value(hasItem(DEFAULT_PROPERTY_AUDIO_EQUIPMENT.booleanValue())))
            .andExpect(jsonPath("$.[*].propertyInternet").value(hasItem(DEFAULT_PROPERTY_INTERNET.booleanValue())))
            .andExpect(jsonPath("$.[*].propertyCableTivi").value(hasItem(DEFAULT_PROPERTY_CABLE_TIVI.booleanValue())))
            .andExpect(jsonPath("$.[*].propertyPetPermission").value(hasItem(DEFAULT_PROPERTY_PET_PERMISSION.booleanValue())))
            .andExpect(jsonPath("$.[*].propertyElevator").value(hasItem(DEFAULT_PROPERTY_ELEVATOR.booleanValue())))
            .andExpect(jsonPath("$.[*].propertySwimmingPool").value(hasItem(DEFAULT_PROPERTY_SWIMMING_POOL.booleanValue())))
            .andExpect(jsonPath("$.[*].propertyGym").value(hasItem(DEFAULT_PROPERTY_GYM.booleanValue())))
            .andExpect(jsonPath("$.[*].propertyFunctionalArea").value(hasItem(DEFAULT_PROPERTY_FUNCTIONAL_AREA.booleanValue())))
            .andExpect(jsonPath("$.[*].propertyOpen24h").value(hasItem(DEFAULT_PROPERTY_OPEN_24_H.booleanValue())))
            .andExpect(jsonPath("$.[*].propertyCarPark").value(hasItem(DEFAULT_PROPERTY_CAR_PARK.booleanValue())))
            .andExpect(jsonPath("$.[*].propertyBalcony").value(hasItem(DEFAULT_PROPERTY_BALCONY.booleanValue())))
            .andExpect(jsonPath("$.[*].propertySauna").value(hasItem(DEFAULT_PROPERTY_SAUNA.booleanValue())))
            .andExpect(jsonPath("$.[*].propertySteamSauna").value(hasItem(DEFAULT_PROPERTY_STEAM_SAUNA.booleanValue())))
            .andExpect(jsonPath("$.[*].propertyAttraction").value(hasItem(DEFAULT_PROPERTY_ATTRACTION.booleanValue())))
            .andExpect(jsonPath("$.[*].propertySpecialFeature").value(hasItem(DEFAULT_PROPERTY_SPECIAL_FEATURE.toString())))
            .andExpect(jsonPath("$.[*].propertyFurnitureOverview").value(hasItem(DEFAULT_PROPERTY_FURNITURE_OVERVIEW.toString())))
            .andExpect(jsonPath("$.[*].propertyLocationOverview").value(hasItem(DEFAULT_PROPERTY_LOCATION_OVERVIEW.toString())))
            .andExpect(jsonPath("$.[*].propertyResidentialCommunity").value(hasItem(DEFAULT_PROPERTY_RESIDENTIAL_COMMUNITY.toString())))
            .andExpect(jsonPath("$.[*].propertyEducationalAspect").value(hasItem(DEFAULT_PROPERTY_EDUCATIONAL_ASPECT.toString())))
            .andExpect(jsonPath("$.[*].propertyExtraInfo").value(hasItem(DEFAULT_PROPERTY_EXTRA_INFO.toString())))
            .andExpect(jsonPath("$.[*].propertyDraftContentType").value(hasItem(DEFAULT_PROPERTY_DRAFT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].propertyDraft").value(hasItem(Base64Utils.encodeToString(DEFAULT_PROPERTY_DRAFT))))
            .andExpect(jsonPath("$.[*].longitude").value(hasItem(DEFAULT_LONGITUDE.doubleValue())))
            .andExpect(jsonPath("$.[*].latitude").value(hasItem(DEFAULT_LATITUDE.doubleValue())))
            .andExpect(jsonPath("$.[*].propertyGoodPrice").value(hasItem(DEFAULT_PROPERTY_GOOD_PRICE.booleanValue())))
            .andExpect(jsonPath("$.[*].propertySeenCount").value(hasItem(DEFAULT_PROPERTY_SEEN_COUNT.intValue())))
            .andExpect(jsonPath("$.[*].propertyIsSold").value(hasItem(DEFAULT_PROPERTY_IS_SOLD.booleanValue())))
            .andExpect(jsonPath("$.[*].propertyIsRent").value(hasItem(DEFAULT_PROPERTY_IS_RENT.booleanValue())))
            .andExpect(jsonPath("$.[*].propertyAvailable").value(hasItem(DEFAULT_PROPERTY_AVAILABLE.booleanValue())));
    }
    
    public void getAllPropertiesWithEagerRelationshipsIsEnabled() throws Exception {
        PropertyResource propertyResource = new PropertyResource(propertyServiceMock, propertyQueryService);
        when(propertyServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restPropertyMockMvc = MockMvcBuilders.standaloneSetup(propertyResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restPropertyMockMvc.perform(get("/api/properties?eagerload=true"))
        .andExpect(status().isOk());

        verify(propertyServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    public void getAllPropertiesWithEagerRelationshipsIsNotEnabled() throws Exception {
        PropertyResource propertyResource = new PropertyResource(propertyServiceMock, propertyQueryService);
            when(propertyServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restPropertyMockMvc = MockMvcBuilders.standaloneSetup(propertyResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restPropertyMockMvc.perform(get("/api/properties?eagerload=true"))
        .andExpect(status().isOk());

            verify(propertyServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getProperty() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get the property
        restPropertyMockMvc.perform(get("/api/properties/{id}", property.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(property.getId().intValue()))
            .andExpect(jsonPath("$.propertyCode").value(DEFAULT_PROPERTY_CODE.toString()))
            .andExpect(jsonPath("$.propertyName").value(DEFAULT_PROPERTY_NAME.toString()))
            .andExpect(jsonPath("$.propertyAlias").value(DEFAULT_PROPERTY_ALIAS.toString()))
            .andExpect(jsonPath("$.propertyTransaction").value(DEFAULT_PROPERTY_TRANSACTION.toString()))
            .andExpect(jsonPath("$.propertyNumber").value(DEFAULT_PROPERTY_NUMBER.toString()))
            .andExpect(jsonPath("$.propertyRoad").value(DEFAULT_PROPERTY_ROAD.toString()))
            .andExpect(jsonPath("$.propertyWard").value(DEFAULT_PROPERTY_WARD.toString()))
            .andExpect(jsonPath("$.propertyDistrict").value(DEFAULT_PROPERTY_DISTRICT.toString()))
            .andExpect(jsonPath("$.propertyProvince").value(DEFAULT_PROPERTY_PROVINCE.toString()))
            .andExpect(jsonPath("$.propertyDescription").value(DEFAULT_PROPERTY_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.propertyBedRooms").value(DEFAULT_PROPERTY_BED_ROOMS))
            .andExpect(jsonPath("$.propertyBathRooms").value(DEFAULT_PROPERTY_BATH_ROOMS))
            .andExpect(jsonPath("$.propertySquare").value(DEFAULT_PROPERTY_SQUARE.doubleValue()))
            .andExpect(jsonPath("$.propertyUsePurpose").value(DEFAULT_PROPERTY_USE_PURPOSE.toString()))
            .andExpect(jsonPath("$.propertyOwnerType").value(DEFAULT_PROPERTY_OWNER_TYPE.toString()))
            .andExpect(jsonPath("$.propertyTower").value(DEFAULT_PROPERTY_TOWER.toString()))
            .andExpect(jsonPath("$.propertyRentPrice").value(DEFAULT_PROPERTY_RENT_PRICE.doubleValue()))
            .andExpect(jsonPath("$.propertyRentUnit").value(DEFAULT_PROPERTY_RENT_UNIT.toString()))
            .andExpect(jsonPath("$.propertyRentStartedDate").value(sameInstant(DEFAULT_PROPERTY_RENT_STARTED_DATE)))
            .andExpect(jsonPath("$.propertySellPrice").value(DEFAULT_PROPERTY_SELL_PRICE.doubleValue()))
            .andExpect(jsonPath("$.propertySellUnit").value(DEFAULT_PROPERTY_SELL_UNIT.toString()))
            .andExpect(jsonPath("$.propertySellStartedDate").value(sameInstant(DEFAULT_PROPERTY_SELL_STARTED_DATE)))
            .andExpect(jsonPath("$.propertySofa").value(DEFAULT_PROPERTY_SOFA.booleanValue()))
            .andExpect(jsonPath("$.propertyDiningTable").value(DEFAULT_PROPERTY_DINING_TABLE.booleanValue()))
            .andExpect(jsonPath("$.propertyKitchen").value(DEFAULT_PROPERTY_KITCHEN.booleanValue()))
            .andExpect(jsonPath("$.propertyCabinetKitchen").value(DEFAULT_PROPERTY_CABINET_KITCHEN.booleanValue()))
            .andExpect(jsonPath("$.propertyKitchenEquipment").value(DEFAULT_PROPERTY_KITCHEN_EQUIPMENT.booleanValue()))
            .andExpect(jsonPath("$.propertyWardrobe").value(DEFAULT_PROPERTY_WARDROBE.booleanValue()))
            .andExpect(jsonPath("$.propertyMakeupTable").value(DEFAULT_PROPERTY_MAKEUP_TABLE.booleanValue()))
            .andExpect(jsonPath("$.propertyDesk").value(DEFAULT_PROPERTY_DESK.booleanValue()))
            .andExpect(jsonPath("$.propertyTivi").value(DEFAULT_PROPERTY_TIVI.booleanValue()))
            .andExpect(jsonPath("$.propertyWashingMachine").value(DEFAULT_PROPERTY_WASHING_MACHINE.booleanValue()))
            .andExpect(jsonPath("$.propertyRefrigerator").value(DEFAULT_PROPERTY_REFRIGERATOR.booleanValue()))
            .andExpect(jsonPath("$.propertyAircondition").value(DEFAULT_PROPERTY_AIRCONDITION.booleanValue()))
            .andExpect(jsonPath("$.propertyMicrowave").value(DEFAULT_PROPERTY_MICROWAVE.booleanValue()))
            .andExpect(jsonPath("$.propertyWaterHeater").value(DEFAULT_PROPERTY_WATER_HEATER.booleanValue()))
            .andExpect(jsonPath("$.propertyBed").value(DEFAULT_PROPERTY_BED.booleanValue()))
            .andExpect(jsonPath("$.propertyHeater").value(DEFAULT_PROPERTY_HEATER.booleanValue()))
            .andExpect(jsonPath("$.propertyAudioEquipment").value(DEFAULT_PROPERTY_AUDIO_EQUIPMENT.booleanValue()))
            .andExpect(jsonPath("$.propertyInternet").value(DEFAULT_PROPERTY_INTERNET.booleanValue()))
            .andExpect(jsonPath("$.propertyCableTivi").value(DEFAULT_PROPERTY_CABLE_TIVI.booleanValue()))
            .andExpect(jsonPath("$.propertyPetPermission").value(DEFAULT_PROPERTY_PET_PERMISSION.booleanValue()))
            .andExpect(jsonPath("$.propertyElevator").value(DEFAULT_PROPERTY_ELEVATOR.booleanValue()))
            .andExpect(jsonPath("$.propertySwimmingPool").value(DEFAULT_PROPERTY_SWIMMING_POOL.booleanValue()))
            .andExpect(jsonPath("$.propertyGym").value(DEFAULT_PROPERTY_GYM.booleanValue()))
            .andExpect(jsonPath("$.propertyFunctionalArea").value(DEFAULT_PROPERTY_FUNCTIONAL_AREA.booleanValue()))
            .andExpect(jsonPath("$.propertyOpen24h").value(DEFAULT_PROPERTY_OPEN_24_H.booleanValue()))
            .andExpect(jsonPath("$.propertyCarPark").value(DEFAULT_PROPERTY_CAR_PARK.booleanValue()))
            .andExpect(jsonPath("$.propertyBalcony").value(DEFAULT_PROPERTY_BALCONY.booleanValue()))
            .andExpect(jsonPath("$.propertySauna").value(DEFAULT_PROPERTY_SAUNA.booleanValue()))
            .andExpect(jsonPath("$.propertySteamSauna").value(DEFAULT_PROPERTY_STEAM_SAUNA.booleanValue()))
            .andExpect(jsonPath("$.propertyAttraction").value(DEFAULT_PROPERTY_ATTRACTION.booleanValue()))
            .andExpect(jsonPath("$.propertySpecialFeature").value(DEFAULT_PROPERTY_SPECIAL_FEATURE.toString()))
            .andExpect(jsonPath("$.propertyFurnitureOverview").value(DEFAULT_PROPERTY_FURNITURE_OVERVIEW.toString()))
            .andExpect(jsonPath("$.propertyLocationOverview").value(DEFAULT_PROPERTY_LOCATION_OVERVIEW.toString()))
            .andExpect(jsonPath("$.propertyResidentialCommunity").value(DEFAULT_PROPERTY_RESIDENTIAL_COMMUNITY.toString()))
            .andExpect(jsonPath("$.propertyEducationalAspect").value(DEFAULT_PROPERTY_EDUCATIONAL_ASPECT.toString()))
            .andExpect(jsonPath("$.propertyExtraInfo").value(DEFAULT_PROPERTY_EXTRA_INFO.toString()))
            .andExpect(jsonPath("$.propertyDraftContentType").value(DEFAULT_PROPERTY_DRAFT_CONTENT_TYPE))
            .andExpect(jsonPath("$.propertyDraft").value(Base64Utils.encodeToString(DEFAULT_PROPERTY_DRAFT)))
            .andExpect(jsonPath("$.longitude").value(DEFAULT_LONGITUDE.doubleValue()))
            .andExpect(jsonPath("$.latitude").value(DEFAULT_LATITUDE.doubleValue()))
            .andExpect(jsonPath("$.propertyGoodPrice").value(DEFAULT_PROPERTY_GOOD_PRICE.booleanValue()))
            .andExpect(jsonPath("$.propertySeenCount").value(DEFAULT_PROPERTY_SEEN_COUNT.intValue()))
            .andExpect(jsonPath("$.propertyIsSold").value(DEFAULT_PROPERTY_IS_SOLD.booleanValue()))
            .andExpect(jsonPath("$.propertyIsRent").value(DEFAULT_PROPERTY_IS_RENT.booleanValue()))
            .andExpect(jsonPath("$.propertyAvailable").value(DEFAULT_PROPERTY_AVAILABLE.booleanValue()));
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyCode equals to DEFAULT_PROPERTY_CODE
        defaultPropertyShouldBeFound("propertyCode.equals=" + DEFAULT_PROPERTY_CODE);

        // Get all the propertyList where propertyCode equals to UPDATED_PROPERTY_CODE
        defaultPropertyShouldNotBeFound("propertyCode.equals=" + UPDATED_PROPERTY_CODE);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyCodeIsInShouldWork() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyCode in DEFAULT_PROPERTY_CODE or UPDATED_PROPERTY_CODE
        defaultPropertyShouldBeFound("propertyCode.in=" + DEFAULT_PROPERTY_CODE + "," + UPDATED_PROPERTY_CODE);

        // Get all the propertyList where propertyCode equals to UPDATED_PROPERTY_CODE
        defaultPropertyShouldNotBeFound("propertyCode.in=" + UPDATED_PROPERTY_CODE);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyCode is not null
        defaultPropertyShouldBeFound("propertyCode.specified=true");

        // Get all the propertyList where propertyCode is null
        defaultPropertyShouldNotBeFound("propertyCode.specified=false");
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyNameIsEqualToSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyName equals to DEFAULT_PROPERTY_NAME
        defaultPropertyShouldBeFound("propertyName.equals=" + DEFAULT_PROPERTY_NAME);

        // Get all the propertyList where propertyName equals to UPDATED_PROPERTY_NAME
        defaultPropertyShouldNotBeFound("propertyName.equals=" + UPDATED_PROPERTY_NAME);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyNameIsInShouldWork() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyName in DEFAULT_PROPERTY_NAME or UPDATED_PROPERTY_NAME
        defaultPropertyShouldBeFound("propertyName.in=" + DEFAULT_PROPERTY_NAME + "," + UPDATED_PROPERTY_NAME);

        // Get all the propertyList where propertyName equals to UPDATED_PROPERTY_NAME
        defaultPropertyShouldNotBeFound("propertyName.in=" + UPDATED_PROPERTY_NAME);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyName is not null
        defaultPropertyShouldBeFound("propertyName.specified=true");

        // Get all the propertyList where propertyName is null
        defaultPropertyShouldNotBeFound("propertyName.specified=false");
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyAliasIsEqualToSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyAlias equals to DEFAULT_PROPERTY_ALIAS
        defaultPropertyShouldBeFound("propertyAlias.equals=" + DEFAULT_PROPERTY_ALIAS);

        // Get all the propertyList where propertyAlias equals to UPDATED_PROPERTY_ALIAS
        defaultPropertyShouldNotBeFound("propertyAlias.equals=" + UPDATED_PROPERTY_ALIAS);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyAliasIsInShouldWork() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyAlias in DEFAULT_PROPERTY_ALIAS or UPDATED_PROPERTY_ALIAS
        defaultPropertyShouldBeFound("propertyAlias.in=" + DEFAULT_PROPERTY_ALIAS + "," + UPDATED_PROPERTY_ALIAS);

        // Get all the propertyList where propertyAlias equals to UPDATED_PROPERTY_ALIAS
        defaultPropertyShouldNotBeFound("propertyAlias.in=" + UPDATED_PROPERTY_ALIAS);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyAliasIsNullOrNotNull() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyAlias is not null
        defaultPropertyShouldBeFound("propertyAlias.specified=true");

        // Get all the propertyList where propertyAlias is null
        defaultPropertyShouldNotBeFound("propertyAlias.specified=false");
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyTransactionIsEqualToSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyTransaction equals to DEFAULT_PROPERTY_TRANSACTION
        defaultPropertyShouldBeFound("propertyTransaction.equals=" + DEFAULT_PROPERTY_TRANSACTION);

        // Get all the propertyList where propertyTransaction equals to UPDATED_PROPERTY_TRANSACTION
        defaultPropertyShouldNotBeFound("propertyTransaction.equals=" + UPDATED_PROPERTY_TRANSACTION);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyTransactionIsInShouldWork() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyTransaction in DEFAULT_PROPERTY_TRANSACTION or UPDATED_PROPERTY_TRANSACTION
        defaultPropertyShouldBeFound("propertyTransaction.in=" + DEFAULT_PROPERTY_TRANSACTION + "," + UPDATED_PROPERTY_TRANSACTION);

        // Get all the propertyList where propertyTransaction equals to UPDATED_PROPERTY_TRANSACTION
        defaultPropertyShouldNotBeFound("propertyTransaction.in=" + UPDATED_PROPERTY_TRANSACTION);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyTransactionIsNullOrNotNull() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyTransaction is not null
        defaultPropertyShouldBeFound("propertyTransaction.specified=true");

        // Get all the propertyList where propertyTransaction is null
        defaultPropertyShouldNotBeFound("propertyTransaction.specified=false");
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyNumber equals to DEFAULT_PROPERTY_NUMBER
        defaultPropertyShouldBeFound("propertyNumber.equals=" + DEFAULT_PROPERTY_NUMBER);

        // Get all the propertyList where propertyNumber equals to UPDATED_PROPERTY_NUMBER
        defaultPropertyShouldNotBeFound("propertyNumber.equals=" + UPDATED_PROPERTY_NUMBER);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyNumberIsInShouldWork() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyNumber in DEFAULT_PROPERTY_NUMBER or UPDATED_PROPERTY_NUMBER
        defaultPropertyShouldBeFound("propertyNumber.in=" + DEFAULT_PROPERTY_NUMBER + "," + UPDATED_PROPERTY_NUMBER);

        // Get all the propertyList where propertyNumber equals to UPDATED_PROPERTY_NUMBER
        defaultPropertyShouldNotBeFound("propertyNumber.in=" + UPDATED_PROPERTY_NUMBER);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyNumber is not null
        defaultPropertyShouldBeFound("propertyNumber.specified=true");

        // Get all the propertyList where propertyNumber is null
        defaultPropertyShouldNotBeFound("propertyNumber.specified=false");
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyRoadIsEqualToSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyRoad equals to DEFAULT_PROPERTY_ROAD
        defaultPropertyShouldBeFound("propertyRoad.equals=" + DEFAULT_PROPERTY_ROAD);

        // Get all the propertyList where propertyRoad equals to UPDATED_PROPERTY_ROAD
        defaultPropertyShouldNotBeFound("propertyRoad.equals=" + UPDATED_PROPERTY_ROAD);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyRoadIsInShouldWork() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyRoad in DEFAULT_PROPERTY_ROAD or UPDATED_PROPERTY_ROAD
        defaultPropertyShouldBeFound("propertyRoad.in=" + DEFAULT_PROPERTY_ROAD + "," + UPDATED_PROPERTY_ROAD);

        // Get all the propertyList where propertyRoad equals to UPDATED_PROPERTY_ROAD
        defaultPropertyShouldNotBeFound("propertyRoad.in=" + UPDATED_PROPERTY_ROAD);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyRoadIsNullOrNotNull() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyRoad is not null
        defaultPropertyShouldBeFound("propertyRoad.specified=true");

        // Get all the propertyList where propertyRoad is null
        defaultPropertyShouldNotBeFound("propertyRoad.specified=false");
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyWardIsEqualToSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyWard equals to DEFAULT_PROPERTY_WARD
        defaultPropertyShouldBeFound("propertyWard.equals=" + DEFAULT_PROPERTY_WARD);

        // Get all the propertyList where propertyWard equals to UPDATED_PROPERTY_WARD
        defaultPropertyShouldNotBeFound("propertyWard.equals=" + UPDATED_PROPERTY_WARD);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyWardIsInShouldWork() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyWard in DEFAULT_PROPERTY_WARD or UPDATED_PROPERTY_WARD
        defaultPropertyShouldBeFound("propertyWard.in=" + DEFAULT_PROPERTY_WARD + "," + UPDATED_PROPERTY_WARD);

        // Get all the propertyList where propertyWard equals to UPDATED_PROPERTY_WARD
        defaultPropertyShouldNotBeFound("propertyWard.in=" + UPDATED_PROPERTY_WARD);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyWardIsNullOrNotNull() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyWard is not null
        defaultPropertyShouldBeFound("propertyWard.specified=true");

        // Get all the propertyList where propertyWard is null
        defaultPropertyShouldNotBeFound("propertyWard.specified=false");
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyDistrictIsEqualToSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyDistrict equals to DEFAULT_PROPERTY_DISTRICT
        defaultPropertyShouldBeFound("propertyDistrict.equals=" + DEFAULT_PROPERTY_DISTRICT);

        // Get all the propertyList where propertyDistrict equals to UPDATED_PROPERTY_DISTRICT
        defaultPropertyShouldNotBeFound("propertyDistrict.equals=" + UPDATED_PROPERTY_DISTRICT);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyDistrictIsInShouldWork() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyDistrict in DEFAULT_PROPERTY_DISTRICT or UPDATED_PROPERTY_DISTRICT
        defaultPropertyShouldBeFound("propertyDistrict.in=" + DEFAULT_PROPERTY_DISTRICT + "," + UPDATED_PROPERTY_DISTRICT);

        // Get all the propertyList where propertyDistrict equals to UPDATED_PROPERTY_DISTRICT
        defaultPropertyShouldNotBeFound("propertyDistrict.in=" + UPDATED_PROPERTY_DISTRICT);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyDistrictIsNullOrNotNull() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyDistrict is not null
        defaultPropertyShouldBeFound("propertyDistrict.specified=true");

        // Get all the propertyList where propertyDistrict is null
        defaultPropertyShouldNotBeFound("propertyDistrict.specified=false");
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyProvinceIsEqualToSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyProvince equals to DEFAULT_PROPERTY_PROVINCE
        defaultPropertyShouldBeFound("propertyProvince.equals=" + DEFAULT_PROPERTY_PROVINCE);

        // Get all the propertyList where propertyProvince equals to UPDATED_PROPERTY_PROVINCE
        defaultPropertyShouldNotBeFound("propertyProvince.equals=" + UPDATED_PROPERTY_PROVINCE);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyProvinceIsInShouldWork() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyProvince in DEFAULT_PROPERTY_PROVINCE or UPDATED_PROPERTY_PROVINCE
        defaultPropertyShouldBeFound("propertyProvince.in=" + DEFAULT_PROPERTY_PROVINCE + "," + UPDATED_PROPERTY_PROVINCE);

        // Get all the propertyList where propertyProvince equals to UPDATED_PROPERTY_PROVINCE
        defaultPropertyShouldNotBeFound("propertyProvince.in=" + UPDATED_PROPERTY_PROVINCE);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyProvinceIsNullOrNotNull() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyProvince is not null
        defaultPropertyShouldBeFound("propertyProvince.specified=true");

        // Get all the propertyList where propertyProvince is null
        defaultPropertyShouldNotBeFound("propertyProvince.specified=false");
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyBedRoomsIsEqualToSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyBedRooms equals to DEFAULT_PROPERTY_BED_ROOMS
        defaultPropertyShouldBeFound("propertyBedRooms.equals=" + DEFAULT_PROPERTY_BED_ROOMS);

        // Get all the propertyList where propertyBedRooms equals to UPDATED_PROPERTY_BED_ROOMS
        defaultPropertyShouldNotBeFound("propertyBedRooms.equals=" + UPDATED_PROPERTY_BED_ROOMS);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyBedRoomsIsInShouldWork() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyBedRooms in DEFAULT_PROPERTY_BED_ROOMS or UPDATED_PROPERTY_BED_ROOMS
        defaultPropertyShouldBeFound("propertyBedRooms.in=" + DEFAULT_PROPERTY_BED_ROOMS + "," + UPDATED_PROPERTY_BED_ROOMS);

        // Get all the propertyList where propertyBedRooms equals to UPDATED_PROPERTY_BED_ROOMS
        defaultPropertyShouldNotBeFound("propertyBedRooms.in=" + UPDATED_PROPERTY_BED_ROOMS);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyBedRoomsIsNullOrNotNull() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyBedRooms is not null
        defaultPropertyShouldBeFound("propertyBedRooms.specified=true");

        // Get all the propertyList where propertyBedRooms is null
        defaultPropertyShouldNotBeFound("propertyBedRooms.specified=false");
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyBedRoomsIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyBedRooms greater than or equals to DEFAULT_PROPERTY_BED_ROOMS
        defaultPropertyShouldBeFound("propertyBedRooms.greaterOrEqualThan=" + DEFAULT_PROPERTY_BED_ROOMS);

        // Get all the propertyList where propertyBedRooms greater than or equals to UPDATED_PROPERTY_BED_ROOMS
        defaultPropertyShouldNotBeFound("propertyBedRooms.greaterOrEqualThan=" + UPDATED_PROPERTY_BED_ROOMS);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyBedRoomsIsLessThanSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyBedRooms less than or equals to DEFAULT_PROPERTY_BED_ROOMS
        defaultPropertyShouldNotBeFound("propertyBedRooms.lessThan=" + DEFAULT_PROPERTY_BED_ROOMS);

        // Get all the propertyList where propertyBedRooms less than or equals to UPDATED_PROPERTY_BED_ROOMS
        defaultPropertyShouldBeFound("propertyBedRooms.lessThan=" + UPDATED_PROPERTY_BED_ROOMS);
    }


    @Test
    @Transactional
    public void getAllPropertiesByPropertyBathRoomsIsEqualToSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyBathRooms equals to DEFAULT_PROPERTY_BATH_ROOMS
        defaultPropertyShouldBeFound("propertyBathRooms.equals=" + DEFAULT_PROPERTY_BATH_ROOMS);

        // Get all the propertyList where propertyBathRooms equals to UPDATED_PROPERTY_BATH_ROOMS
        defaultPropertyShouldNotBeFound("propertyBathRooms.equals=" + UPDATED_PROPERTY_BATH_ROOMS);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyBathRoomsIsInShouldWork() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyBathRooms in DEFAULT_PROPERTY_BATH_ROOMS or UPDATED_PROPERTY_BATH_ROOMS
        defaultPropertyShouldBeFound("propertyBathRooms.in=" + DEFAULT_PROPERTY_BATH_ROOMS + "," + UPDATED_PROPERTY_BATH_ROOMS);

        // Get all the propertyList where propertyBathRooms equals to UPDATED_PROPERTY_BATH_ROOMS
        defaultPropertyShouldNotBeFound("propertyBathRooms.in=" + UPDATED_PROPERTY_BATH_ROOMS);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyBathRoomsIsNullOrNotNull() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyBathRooms is not null
        defaultPropertyShouldBeFound("propertyBathRooms.specified=true");

        // Get all the propertyList where propertyBathRooms is null
        defaultPropertyShouldNotBeFound("propertyBathRooms.specified=false");
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyBathRoomsIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyBathRooms greater than or equals to DEFAULT_PROPERTY_BATH_ROOMS
        defaultPropertyShouldBeFound("propertyBathRooms.greaterOrEqualThan=" + DEFAULT_PROPERTY_BATH_ROOMS);

        // Get all the propertyList where propertyBathRooms greater than or equals to UPDATED_PROPERTY_BATH_ROOMS
        defaultPropertyShouldNotBeFound("propertyBathRooms.greaterOrEqualThan=" + UPDATED_PROPERTY_BATH_ROOMS);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyBathRoomsIsLessThanSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyBathRooms less than or equals to DEFAULT_PROPERTY_BATH_ROOMS
        defaultPropertyShouldNotBeFound("propertyBathRooms.lessThan=" + DEFAULT_PROPERTY_BATH_ROOMS);

        // Get all the propertyList where propertyBathRooms less than or equals to UPDATED_PROPERTY_BATH_ROOMS
        defaultPropertyShouldBeFound("propertyBathRooms.lessThan=" + UPDATED_PROPERTY_BATH_ROOMS);
    }


    @Test
    @Transactional
    public void getAllPropertiesByPropertySquareIsEqualToSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertySquare equals to DEFAULT_PROPERTY_SQUARE
        defaultPropertyShouldBeFound("propertySquare.equals=" + DEFAULT_PROPERTY_SQUARE);

        // Get all the propertyList where propertySquare equals to UPDATED_PROPERTY_SQUARE
        defaultPropertyShouldNotBeFound("propertySquare.equals=" + UPDATED_PROPERTY_SQUARE);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertySquareIsInShouldWork() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertySquare in DEFAULT_PROPERTY_SQUARE or UPDATED_PROPERTY_SQUARE
        defaultPropertyShouldBeFound("propertySquare.in=" + DEFAULT_PROPERTY_SQUARE + "," + UPDATED_PROPERTY_SQUARE);

        // Get all the propertyList where propertySquare equals to UPDATED_PROPERTY_SQUARE
        defaultPropertyShouldNotBeFound("propertySquare.in=" + UPDATED_PROPERTY_SQUARE);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertySquareIsNullOrNotNull() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertySquare is not null
        defaultPropertyShouldBeFound("propertySquare.specified=true");

        // Get all the propertyList where propertySquare is null
        defaultPropertyShouldNotBeFound("propertySquare.specified=false");
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyUsePurposeIsEqualToSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyUsePurpose equals to DEFAULT_PROPERTY_USE_PURPOSE
        defaultPropertyShouldBeFound("propertyUsePurpose.equals=" + DEFAULT_PROPERTY_USE_PURPOSE);

        // Get all the propertyList where propertyUsePurpose equals to UPDATED_PROPERTY_USE_PURPOSE
        defaultPropertyShouldNotBeFound("propertyUsePurpose.equals=" + UPDATED_PROPERTY_USE_PURPOSE);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyUsePurposeIsInShouldWork() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyUsePurpose in DEFAULT_PROPERTY_USE_PURPOSE or UPDATED_PROPERTY_USE_PURPOSE
        defaultPropertyShouldBeFound("propertyUsePurpose.in=" + DEFAULT_PROPERTY_USE_PURPOSE + "," + UPDATED_PROPERTY_USE_PURPOSE);

        // Get all the propertyList where propertyUsePurpose equals to UPDATED_PROPERTY_USE_PURPOSE
        defaultPropertyShouldNotBeFound("propertyUsePurpose.in=" + UPDATED_PROPERTY_USE_PURPOSE);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyUsePurposeIsNullOrNotNull() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyUsePurpose is not null
        defaultPropertyShouldBeFound("propertyUsePurpose.specified=true");

        // Get all the propertyList where propertyUsePurpose is null
        defaultPropertyShouldNotBeFound("propertyUsePurpose.specified=false");
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyOwnerTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyOwnerType equals to DEFAULT_PROPERTY_OWNER_TYPE
        defaultPropertyShouldBeFound("propertyOwnerType.equals=" + DEFAULT_PROPERTY_OWNER_TYPE);

        // Get all the propertyList where propertyOwnerType equals to UPDATED_PROPERTY_OWNER_TYPE
        defaultPropertyShouldNotBeFound("propertyOwnerType.equals=" + UPDATED_PROPERTY_OWNER_TYPE);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyOwnerTypeIsInShouldWork() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyOwnerType in DEFAULT_PROPERTY_OWNER_TYPE or UPDATED_PROPERTY_OWNER_TYPE
        defaultPropertyShouldBeFound("propertyOwnerType.in=" + DEFAULT_PROPERTY_OWNER_TYPE + "," + UPDATED_PROPERTY_OWNER_TYPE);

        // Get all the propertyList where propertyOwnerType equals to UPDATED_PROPERTY_OWNER_TYPE
        defaultPropertyShouldNotBeFound("propertyOwnerType.in=" + UPDATED_PROPERTY_OWNER_TYPE);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyOwnerTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyOwnerType is not null
        defaultPropertyShouldBeFound("propertyOwnerType.specified=true");

        // Get all the propertyList where propertyOwnerType is null
        defaultPropertyShouldNotBeFound("propertyOwnerType.specified=false");
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyTowerIsEqualToSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyTower equals to DEFAULT_PROPERTY_TOWER
        defaultPropertyShouldBeFound("propertyTower.equals=" + DEFAULT_PROPERTY_TOWER);

        // Get all the propertyList where propertyTower equals to UPDATED_PROPERTY_TOWER
        defaultPropertyShouldNotBeFound("propertyTower.equals=" + UPDATED_PROPERTY_TOWER);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyTowerIsInShouldWork() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyTower in DEFAULT_PROPERTY_TOWER or UPDATED_PROPERTY_TOWER
        defaultPropertyShouldBeFound("propertyTower.in=" + DEFAULT_PROPERTY_TOWER + "," + UPDATED_PROPERTY_TOWER);

        // Get all the propertyList where propertyTower equals to UPDATED_PROPERTY_TOWER
        defaultPropertyShouldNotBeFound("propertyTower.in=" + UPDATED_PROPERTY_TOWER);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyTowerIsNullOrNotNull() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyTower is not null
        defaultPropertyShouldBeFound("propertyTower.specified=true");

        // Get all the propertyList where propertyTower is null
        defaultPropertyShouldNotBeFound("propertyTower.specified=false");
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyRentPriceIsEqualToSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyRentPrice equals to DEFAULT_PROPERTY_RENT_PRICE
        defaultPropertyShouldBeFound("propertyRentPrice.equals=" + DEFAULT_PROPERTY_RENT_PRICE);

        // Get all the propertyList where propertyRentPrice equals to UPDATED_PROPERTY_RENT_PRICE
        defaultPropertyShouldNotBeFound("propertyRentPrice.equals=" + UPDATED_PROPERTY_RENT_PRICE);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyRentPriceIsInShouldWork() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyRentPrice in DEFAULT_PROPERTY_RENT_PRICE or UPDATED_PROPERTY_RENT_PRICE
        defaultPropertyShouldBeFound("propertyRentPrice.in=" + DEFAULT_PROPERTY_RENT_PRICE + "," + UPDATED_PROPERTY_RENT_PRICE);

        // Get all the propertyList where propertyRentPrice equals to UPDATED_PROPERTY_RENT_PRICE
        defaultPropertyShouldNotBeFound("propertyRentPrice.in=" + UPDATED_PROPERTY_RENT_PRICE);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyRentPriceIsNullOrNotNull() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyRentPrice is not null
        defaultPropertyShouldBeFound("propertyRentPrice.specified=true");

        // Get all the propertyList where propertyRentPrice is null
        defaultPropertyShouldNotBeFound("propertyRentPrice.specified=false");
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyRentUnitIsEqualToSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyRentUnit equals to DEFAULT_PROPERTY_RENT_UNIT
        defaultPropertyShouldBeFound("propertyRentUnit.equals=" + DEFAULT_PROPERTY_RENT_UNIT);

        // Get all the propertyList where propertyRentUnit equals to UPDATED_PROPERTY_RENT_UNIT
        defaultPropertyShouldNotBeFound("propertyRentUnit.equals=" + UPDATED_PROPERTY_RENT_UNIT);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyRentUnitIsInShouldWork() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyRentUnit in DEFAULT_PROPERTY_RENT_UNIT or UPDATED_PROPERTY_RENT_UNIT
        defaultPropertyShouldBeFound("propertyRentUnit.in=" + DEFAULT_PROPERTY_RENT_UNIT + "," + UPDATED_PROPERTY_RENT_UNIT);

        // Get all the propertyList where propertyRentUnit equals to UPDATED_PROPERTY_RENT_UNIT
        defaultPropertyShouldNotBeFound("propertyRentUnit.in=" + UPDATED_PROPERTY_RENT_UNIT);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyRentUnitIsNullOrNotNull() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyRentUnit is not null
        defaultPropertyShouldBeFound("propertyRentUnit.specified=true");

        // Get all the propertyList where propertyRentUnit is null
        defaultPropertyShouldNotBeFound("propertyRentUnit.specified=false");
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyRentStartedDateIsEqualToSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyRentStartedDate equals to DEFAULT_PROPERTY_RENT_STARTED_DATE
        defaultPropertyShouldBeFound("propertyRentStartedDate.equals=" + DEFAULT_PROPERTY_RENT_STARTED_DATE);

        // Get all the propertyList where propertyRentStartedDate equals to UPDATED_PROPERTY_RENT_STARTED_DATE
        defaultPropertyShouldNotBeFound("propertyRentStartedDate.equals=" + UPDATED_PROPERTY_RENT_STARTED_DATE);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyRentStartedDateIsInShouldWork() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyRentStartedDate in DEFAULT_PROPERTY_RENT_STARTED_DATE or UPDATED_PROPERTY_RENT_STARTED_DATE
        defaultPropertyShouldBeFound("propertyRentStartedDate.in=" + DEFAULT_PROPERTY_RENT_STARTED_DATE + "," + UPDATED_PROPERTY_RENT_STARTED_DATE);

        // Get all the propertyList where propertyRentStartedDate equals to UPDATED_PROPERTY_RENT_STARTED_DATE
        defaultPropertyShouldNotBeFound("propertyRentStartedDate.in=" + UPDATED_PROPERTY_RENT_STARTED_DATE);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyRentStartedDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyRentStartedDate is not null
        defaultPropertyShouldBeFound("propertyRentStartedDate.specified=true");

        // Get all the propertyList where propertyRentStartedDate is null
        defaultPropertyShouldNotBeFound("propertyRentStartedDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyRentStartedDateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyRentStartedDate greater than or equals to DEFAULT_PROPERTY_RENT_STARTED_DATE
        defaultPropertyShouldBeFound("propertyRentStartedDate.greaterOrEqualThan=" + DEFAULT_PROPERTY_RENT_STARTED_DATE);

        // Get all the propertyList where propertyRentStartedDate greater than or equals to UPDATED_PROPERTY_RENT_STARTED_DATE
        defaultPropertyShouldNotBeFound("propertyRentStartedDate.greaterOrEqualThan=" + UPDATED_PROPERTY_RENT_STARTED_DATE);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyRentStartedDateIsLessThanSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyRentStartedDate less than or equals to DEFAULT_PROPERTY_RENT_STARTED_DATE
        defaultPropertyShouldNotBeFound("propertyRentStartedDate.lessThan=" + DEFAULT_PROPERTY_RENT_STARTED_DATE);

        // Get all the propertyList where propertyRentStartedDate less than or equals to UPDATED_PROPERTY_RENT_STARTED_DATE
        defaultPropertyShouldBeFound("propertyRentStartedDate.lessThan=" + UPDATED_PROPERTY_RENT_STARTED_DATE);
    }


    @Test
    @Transactional
    public void getAllPropertiesByPropertySellPriceIsEqualToSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertySellPrice equals to DEFAULT_PROPERTY_SELL_PRICE
        defaultPropertyShouldBeFound("propertySellPrice.equals=" + DEFAULT_PROPERTY_SELL_PRICE);

        // Get all the propertyList where propertySellPrice equals to UPDATED_PROPERTY_SELL_PRICE
        defaultPropertyShouldNotBeFound("propertySellPrice.equals=" + UPDATED_PROPERTY_SELL_PRICE);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertySellPriceIsInShouldWork() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertySellPrice in DEFAULT_PROPERTY_SELL_PRICE or UPDATED_PROPERTY_SELL_PRICE
        defaultPropertyShouldBeFound("propertySellPrice.in=" + DEFAULT_PROPERTY_SELL_PRICE + "," + UPDATED_PROPERTY_SELL_PRICE);

        // Get all the propertyList where propertySellPrice equals to UPDATED_PROPERTY_SELL_PRICE
        defaultPropertyShouldNotBeFound("propertySellPrice.in=" + UPDATED_PROPERTY_SELL_PRICE);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertySellPriceIsNullOrNotNull() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertySellPrice is not null
        defaultPropertyShouldBeFound("propertySellPrice.specified=true");

        // Get all the propertyList where propertySellPrice is null
        defaultPropertyShouldNotBeFound("propertySellPrice.specified=false");
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertySellUnitIsEqualToSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertySellUnit equals to DEFAULT_PROPERTY_SELL_UNIT
        defaultPropertyShouldBeFound("propertySellUnit.equals=" + DEFAULT_PROPERTY_SELL_UNIT);

        // Get all the propertyList where propertySellUnit equals to UPDATED_PROPERTY_SELL_UNIT
        defaultPropertyShouldNotBeFound("propertySellUnit.equals=" + UPDATED_PROPERTY_SELL_UNIT);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertySellUnitIsInShouldWork() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertySellUnit in DEFAULT_PROPERTY_SELL_UNIT or UPDATED_PROPERTY_SELL_UNIT
        defaultPropertyShouldBeFound("propertySellUnit.in=" + DEFAULT_PROPERTY_SELL_UNIT + "," + UPDATED_PROPERTY_SELL_UNIT);

        // Get all the propertyList where propertySellUnit equals to UPDATED_PROPERTY_SELL_UNIT
        defaultPropertyShouldNotBeFound("propertySellUnit.in=" + UPDATED_PROPERTY_SELL_UNIT);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertySellUnitIsNullOrNotNull() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertySellUnit is not null
        defaultPropertyShouldBeFound("propertySellUnit.specified=true");

        // Get all the propertyList where propertySellUnit is null
        defaultPropertyShouldNotBeFound("propertySellUnit.specified=false");
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertySellStartedDateIsEqualToSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertySellStartedDate equals to DEFAULT_PROPERTY_SELL_STARTED_DATE
        defaultPropertyShouldBeFound("propertySellStartedDate.equals=" + DEFAULT_PROPERTY_SELL_STARTED_DATE);

        // Get all the propertyList where propertySellStartedDate equals to UPDATED_PROPERTY_SELL_STARTED_DATE
        defaultPropertyShouldNotBeFound("propertySellStartedDate.equals=" + UPDATED_PROPERTY_SELL_STARTED_DATE);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertySellStartedDateIsInShouldWork() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertySellStartedDate in DEFAULT_PROPERTY_SELL_STARTED_DATE or UPDATED_PROPERTY_SELL_STARTED_DATE
        defaultPropertyShouldBeFound("propertySellStartedDate.in=" + DEFAULT_PROPERTY_SELL_STARTED_DATE + "," + UPDATED_PROPERTY_SELL_STARTED_DATE);

        // Get all the propertyList where propertySellStartedDate equals to UPDATED_PROPERTY_SELL_STARTED_DATE
        defaultPropertyShouldNotBeFound("propertySellStartedDate.in=" + UPDATED_PROPERTY_SELL_STARTED_DATE);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertySellStartedDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertySellStartedDate is not null
        defaultPropertyShouldBeFound("propertySellStartedDate.specified=true");

        // Get all the propertyList where propertySellStartedDate is null
        defaultPropertyShouldNotBeFound("propertySellStartedDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertySellStartedDateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertySellStartedDate greater than or equals to DEFAULT_PROPERTY_SELL_STARTED_DATE
        defaultPropertyShouldBeFound("propertySellStartedDate.greaterOrEqualThan=" + DEFAULT_PROPERTY_SELL_STARTED_DATE);

        // Get all the propertyList where propertySellStartedDate greater than or equals to UPDATED_PROPERTY_SELL_STARTED_DATE
        defaultPropertyShouldNotBeFound("propertySellStartedDate.greaterOrEqualThan=" + UPDATED_PROPERTY_SELL_STARTED_DATE);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertySellStartedDateIsLessThanSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertySellStartedDate less than or equals to DEFAULT_PROPERTY_SELL_STARTED_DATE
        defaultPropertyShouldNotBeFound("propertySellStartedDate.lessThan=" + DEFAULT_PROPERTY_SELL_STARTED_DATE);

        // Get all the propertyList where propertySellStartedDate less than or equals to UPDATED_PROPERTY_SELL_STARTED_DATE
        defaultPropertyShouldBeFound("propertySellStartedDate.lessThan=" + UPDATED_PROPERTY_SELL_STARTED_DATE);
    }


    @Test
    @Transactional
    public void getAllPropertiesByPropertySofaIsEqualToSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertySofa equals to DEFAULT_PROPERTY_SOFA
        defaultPropertyShouldBeFound("propertySofa.equals=" + DEFAULT_PROPERTY_SOFA);

        // Get all the propertyList where propertySofa equals to UPDATED_PROPERTY_SOFA
        defaultPropertyShouldNotBeFound("propertySofa.equals=" + UPDATED_PROPERTY_SOFA);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertySofaIsInShouldWork() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertySofa in DEFAULT_PROPERTY_SOFA or UPDATED_PROPERTY_SOFA
        defaultPropertyShouldBeFound("propertySofa.in=" + DEFAULT_PROPERTY_SOFA + "," + UPDATED_PROPERTY_SOFA);

        // Get all the propertyList where propertySofa equals to UPDATED_PROPERTY_SOFA
        defaultPropertyShouldNotBeFound("propertySofa.in=" + UPDATED_PROPERTY_SOFA);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertySofaIsNullOrNotNull() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertySofa is not null
        defaultPropertyShouldBeFound("propertySofa.specified=true");

        // Get all the propertyList where propertySofa is null
        defaultPropertyShouldNotBeFound("propertySofa.specified=false");
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyDiningTableIsEqualToSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyDiningTable equals to DEFAULT_PROPERTY_DINING_TABLE
        defaultPropertyShouldBeFound("propertyDiningTable.equals=" + DEFAULT_PROPERTY_DINING_TABLE);

        // Get all the propertyList where propertyDiningTable equals to UPDATED_PROPERTY_DINING_TABLE
        defaultPropertyShouldNotBeFound("propertyDiningTable.equals=" + UPDATED_PROPERTY_DINING_TABLE);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyDiningTableIsInShouldWork() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyDiningTable in DEFAULT_PROPERTY_DINING_TABLE or UPDATED_PROPERTY_DINING_TABLE
        defaultPropertyShouldBeFound("propertyDiningTable.in=" + DEFAULT_PROPERTY_DINING_TABLE + "," + UPDATED_PROPERTY_DINING_TABLE);

        // Get all the propertyList where propertyDiningTable equals to UPDATED_PROPERTY_DINING_TABLE
        defaultPropertyShouldNotBeFound("propertyDiningTable.in=" + UPDATED_PROPERTY_DINING_TABLE);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyDiningTableIsNullOrNotNull() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyDiningTable is not null
        defaultPropertyShouldBeFound("propertyDiningTable.specified=true");

        // Get all the propertyList where propertyDiningTable is null
        defaultPropertyShouldNotBeFound("propertyDiningTable.specified=false");
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyKitchenIsEqualToSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyKitchen equals to DEFAULT_PROPERTY_KITCHEN
        defaultPropertyShouldBeFound("propertyKitchen.equals=" + DEFAULT_PROPERTY_KITCHEN);

        // Get all the propertyList where propertyKitchen equals to UPDATED_PROPERTY_KITCHEN
        defaultPropertyShouldNotBeFound("propertyKitchen.equals=" + UPDATED_PROPERTY_KITCHEN);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyKitchenIsInShouldWork() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyKitchen in DEFAULT_PROPERTY_KITCHEN or UPDATED_PROPERTY_KITCHEN
        defaultPropertyShouldBeFound("propertyKitchen.in=" + DEFAULT_PROPERTY_KITCHEN + "," + UPDATED_PROPERTY_KITCHEN);

        // Get all the propertyList where propertyKitchen equals to UPDATED_PROPERTY_KITCHEN
        defaultPropertyShouldNotBeFound("propertyKitchen.in=" + UPDATED_PROPERTY_KITCHEN);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyKitchenIsNullOrNotNull() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyKitchen is not null
        defaultPropertyShouldBeFound("propertyKitchen.specified=true");

        // Get all the propertyList where propertyKitchen is null
        defaultPropertyShouldNotBeFound("propertyKitchen.specified=false");
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyCabinetKitchenIsEqualToSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyCabinetKitchen equals to DEFAULT_PROPERTY_CABINET_KITCHEN
        defaultPropertyShouldBeFound("propertyCabinetKitchen.equals=" + DEFAULT_PROPERTY_CABINET_KITCHEN);

        // Get all the propertyList where propertyCabinetKitchen equals to UPDATED_PROPERTY_CABINET_KITCHEN
        defaultPropertyShouldNotBeFound("propertyCabinetKitchen.equals=" + UPDATED_PROPERTY_CABINET_KITCHEN);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyCabinetKitchenIsInShouldWork() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyCabinetKitchen in DEFAULT_PROPERTY_CABINET_KITCHEN or UPDATED_PROPERTY_CABINET_KITCHEN
        defaultPropertyShouldBeFound("propertyCabinetKitchen.in=" + DEFAULT_PROPERTY_CABINET_KITCHEN + "," + UPDATED_PROPERTY_CABINET_KITCHEN);

        // Get all the propertyList where propertyCabinetKitchen equals to UPDATED_PROPERTY_CABINET_KITCHEN
        defaultPropertyShouldNotBeFound("propertyCabinetKitchen.in=" + UPDATED_PROPERTY_CABINET_KITCHEN);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyCabinetKitchenIsNullOrNotNull() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyCabinetKitchen is not null
        defaultPropertyShouldBeFound("propertyCabinetKitchen.specified=true");

        // Get all the propertyList where propertyCabinetKitchen is null
        defaultPropertyShouldNotBeFound("propertyCabinetKitchen.specified=false");
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyKitchenEquipmentIsEqualToSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyKitchenEquipment equals to DEFAULT_PROPERTY_KITCHEN_EQUIPMENT
        defaultPropertyShouldBeFound("propertyKitchenEquipment.equals=" + DEFAULT_PROPERTY_KITCHEN_EQUIPMENT);

        // Get all the propertyList where propertyKitchenEquipment equals to UPDATED_PROPERTY_KITCHEN_EQUIPMENT
        defaultPropertyShouldNotBeFound("propertyKitchenEquipment.equals=" + UPDATED_PROPERTY_KITCHEN_EQUIPMENT);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyKitchenEquipmentIsInShouldWork() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyKitchenEquipment in DEFAULT_PROPERTY_KITCHEN_EQUIPMENT or UPDATED_PROPERTY_KITCHEN_EQUIPMENT
        defaultPropertyShouldBeFound("propertyKitchenEquipment.in=" + DEFAULT_PROPERTY_KITCHEN_EQUIPMENT + "," + UPDATED_PROPERTY_KITCHEN_EQUIPMENT);

        // Get all the propertyList where propertyKitchenEquipment equals to UPDATED_PROPERTY_KITCHEN_EQUIPMENT
        defaultPropertyShouldNotBeFound("propertyKitchenEquipment.in=" + UPDATED_PROPERTY_KITCHEN_EQUIPMENT);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyKitchenEquipmentIsNullOrNotNull() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyKitchenEquipment is not null
        defaultPropertyShouldBeFound("propertyKitchenEquipment.specified=true");

        // Get all the propertyList where propertyKitchenEquipment is null
        defaultPropertyShouldNotBeFound("propertyKitchenEquipment.specified=false");
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyWardrobeIsEqualToSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyWardrobe equals to DEFAULT_PROPERTY_WARDROBE
        defaultPropertyShouldBeFound("propertyWardrobe.equals=" + DEFAULT_PROPERTY_WARDROBE);

        // Get all the propertyList where propertyWardrobe equals to UPDATED_PROPERTY_WARDROBE
        defaultPropertyShouldNotBeFound("propertyWardrobe.equals=" + UPDATED_PROPERTY_WARDROBE);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyWardrobeIsInShouldWork() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyWardrobe in DEFAULT_PROPERTY_WARDROBE or UPDATED_PROPERTY_WARDROBE
        defaultPropertyShouldBeFound("propertyWardrobe.in=" + DEFAULT_PROPERTY_WARDROBE + "," + UPDATED_PROPERTY_WARDROBE);

        // Get all the propertyList where propertyWardrobe equals to UPDATED_PROPERTY_WARDROBE
        defaultPropertyShouldNotBeFound("propertyWardrobe.in=" + UPDATED_PROPERTY_WARDROBE);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyWardrobeIsNullOrNotNull() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyWardrobe is not null
        defaultPropertyShouldBeFound("propertyWardrobe.specified=true");

        // Get all the propertyList where propertyWardrobe is null
        defaultPropertyShouldNotBeFound("propertyWardrobe.specified=false");
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyMakeupTableIsEqualToSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyMakeupTable equals to DEFAULT_PROPERTY_MAKEUP_TABLE
        defaultPropertyShouldBeFound("propertyMakeupTable.equals=" + DEFAULT_PROPERTY_MAKEUP_TABLE);

        // Get all the propertyList where propertyMakeupTable equals to UPDATED_PROPERTY_MAKEUP_TABLE
        defaultPropertyShouldNotBeFound("propertyMakeupTable.equals=" + UPDATED_PROPERTY_MAKEUP_TABLE);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyMakeupTableIsInShouldWork() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyMakeupTable in DEFAULT_PROPERTY_MAKEUP_TABLE or UPDATED_PROPERTY_MAKEUP_TABLE
        defaultPropertyShouldBeFound("propertyMakeupTable.in=" + DEFAULT_PROPERTY_MAKEUP_TABLE + "," + UPDATED_PROPERTY_MAKEUP_TABLE);

        // Get all the propertyList where propertyMakeupTable equals to UPDATED_PROPERTY_MAKEUP_TABLE
        defaultPropertyShouldNotBeFound("propertyMakeupTable.in=" + UPDATED_PROPERTY_MAKEUP_TABLE);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyMakeupTableIsNullOrNotNull() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyMakeupTable is not null
        defaultPropertyShouldBeFound("propertyMakeupTable.specified=true");

        // Get all the propertyList where propertyMakeupTable is null
        defaultPropertyShouldNotBeFound("propertyMakeupTable.specified=false");
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyDeskIsEqualToSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyDesk equals to DEFAULT_PROPERTY_DESK
        defaultPropertyShouldBeFound("propertyDesk.equals=" + DEFAULT_PROPERTY_DESK);

        // Get all the propertyList where propertyDesk equals to UPDATED_PROPERTY_DESK
        defaultPropertyShouldNotBeFound("propertyDesk.equals=" + UPDATED_PROPERTY_DESK);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyDeskIsInShouldWork() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyDesk in DEFAULT_PROPERTY_DESK or UPDATED_PROPERTY_DESK
        defaultPropertyShouldBeFound("propertyDesk.in=" + DEFAULT_PROPERTY_DESK + "," + UPDATED_PROPERTY_DESK);

        // Get all the propertyList where propertyDesk equals to UPDATED_PROPERTY_DESK
        defaultPropertyShouldNotBeFound("propertyDesk.in=" + UPDATED_PROPERTY_DESK);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyDeskIsNullOrNotNull() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyDesk is not null
        defaultPropertyShouldBeFound("propertyDesk.specified=true");

        // Get all the propertyList where propertyDesk is null
        defaultPropertyShouldNotBeFound("propertyDesk.specified=false");
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyTiviIsEqualToSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyTivi equals to DEFAULT_PROPERTY_TIVI
        defaultPropertyShouldBeFound("propertyTivi.equals=" + DEFAULT_PROPERTY_TIVI);

        // Get all the propertyList where propertyTivi equals to UPDATED_PROPERTY_TIVI
        defaultPropertyShouldNotBeFound("propertyTivi.equals=" + UPDATED_PROPERTY_TIVI);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyTiviIsInShouldWork() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyTivi in DEFAULT_PROPERTY_TIVI or UPDATED_PROPERTY_TIVI
        defaultPropertyShouldBeFound("propertyTivi.in=" + DEFAULT_PROPERTY_TIVI + "," + UPDATED_PROPERTY_TIVI);

        // Get all the propertyList where propertyTivi equals to UPDATED_PROPERTY_TIVI
        defaultPropertyShouldNotBeFound("propertyTivi.in=" + UPDATED_PROPERTY_TIVI);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyTiviIsNullOrNotNull() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyTivi is not null
        defaultPropertyShouldBeFound("propertyTivi.specified=true");

        // Get all the propertyList where propertyTivi is null
        defaultPropertyShouldNotBeFound("propertyTivi.specified=false");
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyWashingMachineIsEqualToSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyWashingMachine equals to DEFAULT_PROPERTY_WASHING_MACHINE
        defaultPropertyShouldBeFound("propertyWashingMachine.equals=" + DEFAULT_PROPERTY_WASHING_MACHINE);

        // Get all the propertyList where propertyWashingMachine equals to UPDATED_PROPERTY_WASHING_MACHINE
        defaultPropertyShouldNotBeFound("propertyWashingMachine.equals=" + UPDATED_PROPERTY_WASHING_MACHINE);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyWashingMachineIsInShouldWork() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyWashingMachine in DEFAULT_PROPERTY_WASHING_MACHINE or UPDATED_PROPERTY_WASHING_MACHINE
        defaultPropertyShouldBeFound("propertyWashingMachine.in=" + DEFAULT_PROPERTY_WASHING_MACHINE + "," + UPDATED_PROPERTY_WASHING_MACHINE);

        // Get all the propertyList where propertyWashingMachine equals to UPDATED_PROPERTY_WASHING_MACHINE
        defaultPropertyShouldNotBeFound("propertyWashingMachine.in=" + UPDATED_PROPERTY_WASHING_MACHINE);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyWashingMachineIsNullOrNotNull() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyWashingMachine is not null
        defaultPropertyShouldBeFound("propertyWashingMachine.specified=true");

        // Get all the propertyList where propertyWashingMachine is null
        defaultPropertyShouldNotBeFound("propertyWashingMachine.specified=false");
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyRefrigeratorIsEqualToSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyRefrigerator equals to DEFAULT_PROPERTY_REFRIGERATOR
        defaultPropertyShouldBeFound("propertyRefrigerator.equals=" + DEFAULT_PROPERTY_REFRIGERATOR);

        // Get all the propertyList where propertyRefrigerator equals to UPDATED_PROPERTY_REFRIGERATOR
        defaultPropertyShouldNotBeFound("propertyRefrigerator.equals=" + UPDATED_PROPERTY_REFRIGERATOR);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyRefrigeratorIsInShouldWork() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyRefrigerator in DEFAULT_PROPERTY_REFRIGERATOR or UPDATED_PROPERTY_REFRIGERATOR
        defaultPropertyShouldBeFound("propertyRefrigerator.in=" + DEFAULT_PROPERTY_REFRIGERATOR + "," + UPDATED_PROPERTY_REFRIGERATOR);

        // Get all the propertyList where propertyRefrigerator equals to UPDATED_PROPERTY_REFRIGERATOR
        defaultPropertyShouldNotBeFound("propertyRefrigerator.in=" + UPDATED_PROPERTY_REFRIGERATOR);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyRefrigeratorIsNullOrNotNull() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyRefrigerator is not null
        defaultPropertyShouldBeFound("propertyRefrigerator.specified=true");

        // Get all the propertyList where propertyRefrigerator is null
        defaultPropertyShouldNotBeFound("propertyRefrigerator.specified=false");
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyAirconditionIsEqualToSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyAircondition equals to DEFAULT_PROPERTY_AIRCONDITION
        defaultPropertyShouldBeFound("propertyAircondition.equals=" + DEFAULT_PROPERTY_AIRCONDITION);

        // Get all the propertyList where propertyAircondition equals to UPDATED_PROPERTY_AIRCONDITION
        defaultPropertyShouldNotBeFound("propertyAircondition.equals=" + UPDATED_PROPERTY_AIRCONDITION);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyAirconditionIsInShouldWork() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyAircondition in DEFAULT_PROPERTY_AIRCONDITION or UPDATED_PROPERTY_AIRCONDITION
        defaultPropertyShouldBeFound("propertyAircondition.in=" + DEFAULT_PROPERTY_AIRCONDITION + "," + UPDATED_PROPERTY_AIRCONDITION);

        // Get all the propertyList where propertyAircondition equals to UPDATED_PROPERTY_AIRCONDITION
        defaultPropertyShouldNotBeFound("propertyAircondition.in=" + UPDATED_PROPERTY_AIRCONDITION);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyAirconditionIsNullOrNotNull() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyAircondition is not null
        defaultPropertyShouldBeFound("propertyAircondition.specified=true");

        // Get all the propertyList where propertyAircondition is null
        defaultPropertyShouldNotBeFound("propertyAircondition.specified=false");
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyMicrowaveIsEqualToSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyMicrowave equals to DEFAULT_PROPERTY_MICROWAVE
        defaultPropertyShouldBeFound("propertyMicrowave.equals=" + DEFAULT_PROPERTY_MICROWAVE);

        // Get all the propertyList where propertyMicrowave equals to UPDATED_PROPERTY_MICROWAVE
        defaultPropertyShouldNotBeFound("propertyMicrowave.equals=" + UPDATED_PROPERTY_MICROWAVE);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyMicrowaveIsInShouldWork() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyMicrowave in DEFAULT_PROPERTY_MICROWAVE or UPDATED_PROPERTY_MICROWAVE
        defaultPropertyShouldBeFound("propertyMicrowave.in=" + DEFAULT_PROPERTY_MICROWAVE + "," + UPDATED_PROPERTY_MICROWAVE);

        // Get all the propertyList where propertyMicrowave equals to UPDATED_PROPERTY_MICROWAVE
        defaultPropertyShouldNotBeFound("propertyMicrowave.in=" + UPDATED_PROPERTY_MICROWAVE);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyMicrowaveIsNullOrNotNull() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyMicrowave is not null
        defaultPropertyShouldBeFound("propertyMicrowave.specified=true");

        // Get all the propertyList where propertyMicrowave is null
        defaultPropertyShouldNotBeFound("propertyMicrowave.specified=false");
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyWaterHeaterIsEqualToSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyWaterHeater equals to DEFAULT_PROPERTY_WATER_HEATER
        defaultPropertyShouldBeFound("propertyWaterHeater.equals=" + DEFAULT_PROPERTY_WATER_HEATER);

        // Get all the propertyList where propertyWaterHeater equals to UPDATED_PROPERTY_WATER_HEATER
        defaultPropertyShouldNotBeFound("propertyWaterHeater.equals=" + UPDATED_PROPERTY_WATER_HEATER);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyWaterHeaterIsInShouldWork() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyWaterHeater in DEFAULT_PROPERTY_WATER_HEATER or UPDATED_PROPERTY_WATER_HEATER
        defaultPropertyShouldBeFound("propertyWaterHeater.in=" + DEFAULT_PROPERTY_WATER_HEATER + "," + UPDATED_PROPERTY_WATER_HEATER);

        // Get all the propertyList where propertyWaterHeater equals to UPDATED_PROPERTY_WATER_HEATER
        defaultPropertyShouldNotBeFound("propertyWaterHeater.in=" + UPDATED_PROPERTY_WATER_HEATER);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyWaterHeaterIsNullOrNotNull() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyWaterHeater is not null
        defaultPropertyShouldBeFound("propertyWaterHeater.specified=true");

        // Get all the propertyList where propertyWaterHeater is null
        defaultPropertyShouldNotBeFound("propertyWaterHeater.specified=false");
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyBedIsEqualToSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyBed equals to DEFAULT_PROPERTY_BED
        defaultPropertyShouldBeFound("propertyBed.equals=" + DEFAULT_PROPERTY_BED);

        // Get all the propertyList where propertyBed equals to UPDATED_PROPERTY_BED
        defaultPropertyShouldNotBeFound("propertyBed.equals=" + UPDATED_PROPERTY_BED);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyBedIsInShouldWork() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyBed in DEFAULT_PROPERTY_BED or UPDATED_PROPERTY_BED
        defaultPropertyShouldBeFound("propertyBed.in=" + DEFAULT_PROPERTY_BED + "," + UPDATED_PROPERTY_BED);

        // Get all the propertyList where propertyBed equals to UPDATED_PROPERTY_BED
        defaultPropertyShouldNotBeFound("propertyBed.in=" + UPDATED_PROPERTY_BED);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyBedIsNullOrNotNull() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyBed is not null
        defaultPropertyShouldBeFound("propertyBed.specified=true");

        // Get all the propertyList where propertyBed is null
        defaultPropertyShouldNotBeFound("propertyBed.specified=false");
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyHeaterIsEqualToSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyHeater equals to DEFAULT_PROPERTY_HEATER
        defaultPropertyShouldBeFound("propertyHeater.equals=" + DEFAULT_PROPERTY_HEATER);

        // Get all the propertyList where propertyHeater equals to UPDATED_PROPERTY_HEATER
        defaultPropertyShouldNotBeFound("propertyHeater.equals=" + UPDATED_PROPERTY_HEATER);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyHeaterIsInShouldWork() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyHeater in DEFAULT_PROPERTY_HEATER or UPDATED_PROPERTY_HEATER
        defaultPropertyShouldBeFound("propertyHeater.in=" + DEFAULT_PROPERTY_HEATER + "," + UPDATED_PROPERTY_HEATER);

        // Get all the propertyList where propertyHeater equals to UPDATED_PROPERTY_HEATER
        defaultPropertyShouldNotBeFound("propertyHeater.in=" + UPDATED_PROPERTY_HEATER);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyHeaterIsNullOrNotNull() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyHeater is not null
        defaultPropertyShouldBeFound("propertyHeater.specified=true");

        // Get all the propertyList where propertyHeater is null
        defaultPropertyShouldNotBeFound("propertyHeater.specified=false");
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyAudioEquipmentIsEqualToSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyAudioEquipment equals to DEFAULT_PROPERTY_AUDIO_EQUIPMENT
        defaultPropertyShouldBeFound("propertyAudioEquipment.equals=" + DEFAULT_PROPERTY_AUDIO_EQUIPMENT);

        // Get all the propertyList where propertyAudioEquipment equals to UPDATED_PROPERTY_AUDIO_EQUIPMENT
        defaultPropertyShouldNotBeFound("propertyAudioEquipment.equals=" + UPDATED_PROPERTY_AUDIO_EQUIPMENT);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyAudioEquipmentIsInShouldWork() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyAudioEquipment in DEFAULT_PROPERTY_AUDIO_EQUIPMENT or UPDATED_PROPERTY_AUDIO_EQUIPMENT
        defaultPropertyShouldBeFound("propertyAudioEquipment.in=" + DEFAULT_PROPERTY_AUDIO_EQUIPMENT + "," + UPDATED_PROPERTY_AUDIO_EQUIPMENT);

        // Get all the propertyList where propertyAudioEquipment equals to UPDATED_PROPERTY_AUDIO_EQUIPMENT
        defaultPropertyShouldNotBeFound("propertyAudioEquipment.in=" + UPDATED_PROPERTY_AUDIO_EQUIPMENT);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyAudioEquipmentIsNullOrNotNull() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyAudioEquipment is not null
        defaultPropertyShouldBeFound("propertyAudioEquipment.specified=true");

        // Get all the propertyList where propertyAudioEquipment is null
        defaultPropertyShouldNotBeFound("propertyAudioEquipment.specified=false");
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyInternetIsEqualToSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyInternet equals to DEFAULT_PROPERTY_INTERNET
        defaultPropertyShouldBeFound("propertyInternet.equals=" + DEFAULT_PROPERTY_INTERNET);

        // Get all the propertyList where propertyInternet equals to UPDATED_PROPERTY_INTERNET
        defaultPropertyShouldNotBeFound("propertyInternet.equals=" + UPDATED_PROPERTY_INTERNET);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyInternetIsInShouldWork() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyInternet in DEFAULT_PROPERTY_INTERNET or UPDATED_PROPERTY_INTERNET
        defaultPropertyShouldBeFound("propertyInternet.in=" + DEFAULT_PROPERTY_INTERNET + "," + UPDATED_PROPERTY_INTERNET);

        // Get all the propertyList where propertyInternet equals to UPDATED_PROPERTY_INTERNET
        defaultPropertyShouldNotBeFound("propertyInternet.in=" + UPDATED_PROPERTY_INTERNET);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyInternetIsNullOrNotNull() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyInternet is not null
        defaultPropertyShouldBeFound("propertyInternet.specified=true");

        // Get all the propertyList where propertyInternet is null
        defaultPropertyShouldNotBeFound("propertyInternet.specified=false");
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyCableTiviIsEqualToSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyCableTivi equals to DEFAULT_PROPERTY_CABLE_TIVI
        defaultPropertyShouldBeFound("propertyCableTivi.equals=" + DEFAULT_PROPERTY_CABLE_TIVI);

        // Get all the propertyList where propertyCableTivi equals to UPDATED_PROPERTY_CABLE_TIVI
        defaultPropertyShouldNotBeFound("propertyCableTivi.equals=" + UPDATED_PROPERTY_CABLE_TIVI);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyCableTiviIsInShouldWork() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyCableTivi in DEFAULT_PROPERTY_CABLE_TIVI or UPDATED_PROPERTY_CABLE_TIVI
        defaultPropertyShouldBeFound("propertyCableTivi.in=" + DEFAULT_PROPERTY_CABLE_TIVI + "," + UPDATED_PROPERTY_CABLE_TIVI);

        // Get all the propertyList where propertyCableTivi equals to UPDATED_PROPERTY_CABLE_TIVI
        defaultPropertyShouldNotBeFound("propertyCableTivi.in=" + UPDATED_PROPERTY_CABLE_TIVI);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyCableTiviIsNullOrNotNull() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyCableTivi is not null
        defaultPropertyShouldBeFound("propertyCableTivi.specified=true");

        // Get all the propertyList where propertyCableTivi is null
        defaultPropertyShouldNotBeFound("propertyCableTivi.specified=false");
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyPetPermissionIsEqualToSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyPetPermission equals to DEFAULT_PROPERTY_PET_PERMISSION
        defaultPropertyShouldBeFound("propertyPetPermission.equals=" + DEFAULT_PROPERTY_PET_PERMISSION);

        // Get all the propertyList where propertyPetPermission equals to UPDATED_PROPERTY_PET_PERMISSION
        defaultPropertyShouldNotBeFound("propertyPetPermission.equals=" + UPDATED_PROPERTY_PET_PERMISSION);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyPetPermissionIsInShouldWork() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyPetPermission in DEFAULT_PROPERTY_PET_PERMISSION or UPDATED_PROPERTY_PET_PERMISSION
        defaultPropertyShouldBeFound("propertyPetPermission.in=" + DEFAULT_PROPERTY_PET_PERMISSION + "," + UPDATED_PROPERTY_PET_PERMISSION);

        // Get all the propertyList where propertyPetPermission equals to UPDATED_PROPERTY_PET_PERMISSION
        defaultPropertyShouldNotBeFound("propertyPetPermission.in=" + UPDATED_PROPERTY_PET_PERMISSION);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyPetPermissionIsNullOrNotNull() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyPetPermission is not null
        defaultPropertyShouldBeFound("propertyPetPermission.specified=true");

        // Get all the propertyList where propertyPetPermission is null
        defaultPropertyShouldNotBeFound("propertyPetPermission.specified=false");
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyElevatorIsEqualToSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyElevator equals to DEFAULT_PROPERTY_ELEVATOR
        defaultPropertyShouldBeFound("propertyElevator.equals=" + DEFAULT_PROPERTY_ELEVATOR);

        // Get all the propertyList where propertyElevator equals to UPDATED_PROPERTY_ELEVATOR
        defaultPropertyShouldNotBeFound("propertyElevator.equals=" + UPDATED_PROPERTY_ELEVATOR);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyElevatorIsInShouldWork() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyElevator in DEFAULT_PROPERTY_ELEVATOR or UPDATED_PROPERTY_ELEVATOR
        defaultPropertyShouldBeFound("propertyElevator.in=" + DEFAULT_PROPERTY_ELEVATOR + "," + UPDATED_PROPERTY_ELEVATOR);

        // Get all the propertyList where propertyElevator equals to UPDATED_PROPERTY_ELEVATOR
        defaultPropertyShouldNotBeFound("propertyElevator.in=" + UPDATED_PROPERTY_ELEVATOR);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyElevatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyElevator is not null
        defaultPropertyShouldBeFound("propertyElevator.specified=true");

        // Get all the propertyList where propertyElevator is null
        defaultPropertyShouldNotBeFound("propertyElevator.specified=false");
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertySwimmingPoolIsEqualToSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertySwimmingPool equals to DEFAULT_PROPERTY_SWIMMING_POOL
        defaultPropertyShouldBeFound("propertySwimmingPool.equals=" + DEFAULT_PROPERTY_SWIMMING_POOL);

        // Get all the propertyList where propertySwimmingPool equals to UPDATED_PROPERTY_SWIMMING_POOL
        defaultPropertyShouldNotBeFound("propertySwimmingPool.equals=" + UPDATED_PROPERTY_SWIMMING_POOL);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertySwimmingPoolIsInShouldWork() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertySwimmingPool in DEFAULT_PROPERTY_SWIMMING_POOL or UPDATED_PROPERTY_SWIMMING_POOL
        defaultPropertyShouldBeFound("propertySwimmingPool.in=" + DEFAULT_PROPERTY_SWIMMING_POOL + "," + UPDATED_PROPERTY_SWIMMING_POOL);

        // Get all the propertyList where propertySwimmingPool equals to UPDATED_PROPERTY_SWIMMING_POOL
        defaultPropertyShouldNotBeFound("propertySwimmingPool.in=" + UPDATED_PROPERTY_SWIMMING_POOL);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertySwimmingPoolIsNullOrNotNull() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertySwimmingPool is not null
        defaultPropertyShouldBeFound("propertySwimmingPool.specified=true");

        // Get all the propertyList where propertySwimmingPool is null
        defaultPropertyShouldNotBeFound("propertySwimmingPool.specified=false");
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyGymIsEqualToSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyGym equals to DEFAULT_PROPERTY_GYM
        defaultPropertyShouldBeFound("propertyGym.equals=" + DEFAULT_PROPERTY_GYM);

        // Get all the propertyList where propertyGym equals to UPDATED_PROPERTY_GYM
        defaultPropertyShouldNotBeFound("propertyGym.equals=" + UPDATED_PROPERTY_GYM);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyGymIsInShouldWork() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyGym in DEFAULT_PROPERTY_GYM or UPDATED_PROPERTY_GYM
        defaultPropertyShouldBeFound("propertyGym.in=" + DEFAULT_PROPERTY_GYM + "," + UPDATED_PROPERTY_GYM);

        // Get all the propertyList where propertyGym equals to UPDATED_PROPERTY_GYM
        defaultPropertyShouldNotBeFound("propertyGym.in=" + UPDATED_PROPERTY_GYM);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyGymIsNullOrNotNull() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyGym is not null
        defaultPropertyShouldBeFound("propertyGym.specified=true");

        // Get all the propertyList where propertyGym is null
        defaultPropertyShouldNotBeFound("propertyGym.specified=false");
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyFunctionalAreaIsEqualToSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyFunctionalArea equals to DEFAULT_PROPERTY_FUNCTIONAL_AREA
        defaultPropertyShouldBeFound("propertyFunctionalArea.equals=" + DEFAULT_PROPERTY_FUNCTIONAL_AREA);

        // Get all the propertyList where propertyFunctionalArea equals to UPDATED_PROPERTY_FUNCTIONAL_AREA
        defaultPropertyShouldNotBeFound("propertyFunctionalArea.equals=" + UPDATED_PROPERTY_FUNCTIONAL_AREA);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyFunctionalAreaIsInShouldWork() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyFunctionalArea in DEFAULT_PROPERTY_FUNCTIONAL_AREA or UPDATED_PROPERTY_FUNCTIONAL_AREA
        defaultPropertyShouldBeFound("propertyFunctionalArea.in=" + DEFAULT_PROPERTY_FUNCTIONAL_AREA + "," + UPDATED_PROPERTY_FUNCTIONAL_AREA);

        // Get all the propertyList where propertyFunctionalArea equals to UPDATED_PROPERTY_FUNCTIONAL_AREA
        defaultPropertyShouldNotBeFound("propertyFunctionalArea.in=" + UPDATED_PROPERTY_FUNCTIONAL_AREA);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyFunctionalAreaIsNullOrNotNull() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyFunctionalArea is not null
        defaultPropertyShouldBeFound("propertyFunctionalArea.specified=true");

        // Get all the propertyList where propertyFunctionalArea is null
        defaultPropertyShouldNotBeFound("propertyFunctionalArea.specified=false");
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyOpen24hIsEqualToSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyOpen24h equals to DEFAULT_PROPERTY_OPEN_24_H
        defaultPropertyShouldBeFound("propertyOpen24h.equals=" + DEFAULT_PROPERTY_OPEN_24_H);

        // Get all the propertyList where propertyOpen24h equals to UPDATED_PROPERTY_OPEN_24_H
        defaultPropertyShouldNotBeFound("propertyOpen24h.equals=" + UPDATED_PROPERTY_OPEN_24_H);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyOpen24hIsInShouldWork() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyOpen24h in DEFAULT_PROPERTY_OPEN_24_H or UPDATED_PROPERTY_OPEN_24_H
        defaultPropertyShouldBeFound("propertyOpen24h.in=" + DEFAULT_PROPERTY_OPEN_24_H + "," + UPDATED_PROPERTY_OPEN_24_H);

        // Get all the propertyList where propertyOpen24h equals to UPDATED_PROPERTY_OPEN_24_H
        defaultPropertyShouldNotBeFound("propertyOpen24h.in=" + UPDATED_PROPERTY_OPEN_24_H);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyOpen24hIsNullOrNotNull() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyOpen24h is not null
        defaultPropertyShouldBeFound("propertyOpen24h.specified=true");

        // Get all the propertyList where propertyOpen24h is null
        defaultPropertyShouldNotBeFound("propertyOpen24h.specified=false");
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyCarParkIsEqualToSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyCarPark equals to DEFAULT_PROPERTY_CAR_PARK
        defaultPropertyShouldBeFound("propertyCarPark.equals=" + DEFAULT_PROPERTY_CAR_PARK);

        // Get all the propertyList where propertyCarPark equals to UPDATED_PROPERTY_CAR_PARK
        defaultPropertyShouldNotBeFound("propertyCarPark.equals=" + UPDATED_PROPERTY_CAR_PARK);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyCarParkIsInShouldWork() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyCarPark in DEFAULT_PROPERTY_CAR_PARK or UPDATED_PROPERTY_CAR_PARK
        defaultPropertyShouldBeFound("propertyCarPark.in=" + DEFAULT_PROPERTY_CAR_PARK + "," + UPDATED_PROPERTY_CAR_PARK);

        // Get all the propertyList where propertyCarPark equals to UPDATED_PROPERTY_CAR_PARK
        defaultPropertyShouldNotBeFound("propertyCarPark.in=" + UPDATED_PROPERTY_CAR_PARK);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyCarParkIsNullOrNotNull() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyCarPark is not null
        defaultPropertyShouldBeFound("propertyCarPark.specified=true");

        // Get all the propertyList where propertyCarPark is null
        defaultPropertyShouldNotBeFound("propertyCarPark.specified=false");
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyBalconyIsEqualToSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyBalcony equals to DEFAULT_PROPERTY_BALCONY
        defaultPropertyShouldBeFound("propertyBalcony.equals=" + DEFAULT_PROPERTY_BALCONY);

        // Get all the propertyList where propertyBalcony equals to UPDATED_PROPERTY_BALCONY
        defaultPropertyShouldNotBeFound("propertyBalcony.equals=" + UPDATED_PROPERTY_BALCONY);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyBalconyIsInShouldWork() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyBalcony in DEFAULT_PROPERTY_BALCONY or UPDATED_PROPERTY_BALCONY
        defaultPropertyShouldBeFound("propertyBalcony.in=" + DEFAULT_PROPERTY_BALCONY + "," + UPDATED_PROPERTY_BALCONY);

        // Get all the propertyList where propertyBalcony equals to UPDATED_PROPERTY_BALCONY
        defaultPropertyShouldNotBeFound("propertyBalcony.in=" + UPDATED_PROPERTY_BALCONY);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyBalconyIsNullOrNotNull() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyBalcony is not null
        defaultPropertyShouldBeFound("propertyBalcony.specified=true");

        // Get all the propertyList where propertyBalcony is null
        defaultPropertyShouldNotBeFound("propertyBalcony.specified=false");
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertySaunaIsEqualToSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertySauna equals to DEFAULT_PROPERTY_SAUNA
        defaultPropertyShouldBeFound("propertySauna.equals=" + DEFAULT_PROPERTY_SAUNA);

        // Get all the propertyList where propertySauna equals to UPDATED_PROPERTY_SAUNA
        defaultPropertyShouldNotBeFound("propertySauna.equals=" + UPDATED_PROPERTY_SAUNA);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertySaunaIsInShouldWork() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertySauna in DEFAULT_PROPERTY_SAUNA or UPDATED_PROPERTY_SAUNA
        defaultPropertyShouldBeFound("propertySauna.in=" + DEFAULT_PROPERTY_SAUNA + "," + UPDATED_PROPERTY_SAUNA);

        // Get all the propertyList where propertySauna equals to UPDATED_PROPERTY_SAUNA
        defaultPropertyShouldNotBeFound("propertySauna.in=" + UPDATED_PROPERTY_SAUNA);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertySaunaIsNullOrNotNull() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertySauna is not null
        defaultPropertyShouldBeFound("propertySauna.specified=true");

        // Get all the propertyList where propertySauna is null
        defaultPropertyShouldNotBeFound("propertySauna.specified=false");
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertySteamSaunaIsEqualToSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertySteamSauna equals to DEFAULT_PROPERTY_STEAM_SAUNA
        defaultPropertyShouldBeFound("propertySteamSauna.equals=" + DEFAULT_PROPERTY_STEAM_SAUNA);

        // Get all the propertyList where propertySteamSauna equals to UPDATED_PROPERTY_STEAM_SAUNA
        defaultPropertyShouldNotBeFound("propertySteamSauna.equals=" + UPDATED_PROPERTY_STEAM_SAUNA);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertySteamSaunaIsInShouldWork() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertySteamSauna in DEFAULT_PROPERTY_STEAM_SAUNA or UPDATED_PROPERTY_STEAM_SAUNA
        defaultPropertyShouldBeFound("propertySteamSauna.in=" + DEFAULT_PROPERTY_STEAM_SAUNA + "," + UPDATED_PROPERTY_STEAM_SAUNA);

        // Get all the propertyList where propertySteamSauna equals to UPDATED_PROPERTY_STEAM_SAUNA
        defaultPropertyShouldNotBeFound("propertySteamSauna.in=" + UPDATED_PROPERTY_STEAM_SAUNA);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertySteamSaunaIsNullOrNotNull() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertySteamSauna is not null
        defaultPropertyShouldBeFound("propertySteamSauna.specified=true");

        // Get all the propertyList where propertySteamSauna is null
        defaultPropertyShouldNotBeFound("propertySteamSauna.specified=false");
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyAttractionIsEqualToSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyAttraction equals to DEFAULT_PROPERTY_ATTRACTION
        defaultPropertyShouldBeFound("propertyAttraction.equals=" + DEFAULT_PROPERTY_ATTRACTION);

        // Get all the propertyList where propertyAttraction equals to UPDATED_PROPERTY_ATTRACTION
        defaultPropertyShouldNotBeFound("propertyAttraction.equals=" + UPDATED_PROPERTY_ATTRACTION);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyAttractionIsInShouldWork() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyAttraction in DEFAULT_PROPERTY_ATTRACTION or UPDATED_PROPERTY_ATTRACTION
        defaultPropertyShouldBeFound("propertyAttraction.in=" + DEFAULT_PROPERTY_ATTRACTION + "," + UPDATED_PROPERTY_ATTRACTION);

        // Get all the propertyList where propertyAttraction equals to UPDATED_PROPERTY_ATTRACTION
        defaultPropertyShouldNotBeFound("propertyAttraction.in=" + UPDATED_PROPERTY_ATTRACTION);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyAttractionIsNullOrNotNull() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyAttraction is not null
        defaultPropertyShouldBeFound("propertyAttraction.specified=true");

        // Get all the propertyList where propertyAttraction is null
        defaultPropertyShouldNotBeFound("propertyAttraction.specified=false");
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertySpecialFeatureIsEqualToSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertySpecialFeature equals to DEFAULT_PROPERTY_SPECIAL_FEATURE
        defaultPropertyShouldBeFound("propertySpecialFeature.equals=" + DEFAULT_PROPERTY_SPECIAL_FEATURE);

        // Get all the propertyList where propertySpecialFeature equals to UPDATED_PROPERTY_SPECIAL_FEATURE
        defaultPropertyShouldNotBeFound("propertySpecialFeature.equals=" + UPDATED_PROPERTY_SPECIAL_FEATURE);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertySpecialFeatureIsInShouldWork() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertySpecialFeature in DEFAULT_PROPERTY_SPECIAL_FEATURE or UPDATED_PROPERTY_SPECIAL_FEATURE
        defaultPropertyShouldBeFound("propertySpecialFeature.in=" + DEFAULT_PROPERTY_SPECIAL_FEATURE + "," + UPDATED_PROPERTY_SPECIAL_FEATURE);

        // Get all the propertyList where propertySpecialFeature equals to UPDATED_PROPERTY_SPECIAL_FEATURE
        defaultPropertyShouldNotBeFound("propertySpecialFeature.in=" + UPDATED_PROPERTY_SPECIAL_FEATURE);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertySpecialFeatureIsNullOrNotNull() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertySpecialFeature is not null
        defaultPropertyShouldBeFound("propertySpecialFeature.specified=true");

        // Get all the propertyList where propertySpecialFeature is null
        defaultPropertyShouldNotBeFound("propertySpecialFeature.specified=false");
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyFurnitureOverviewIsEqualToSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyFurnitureOverview equals to DEFAULT_PROPERTY_FURNITURE_OVERVIEW
        defaultPropertyShouldBeFound("propertyFurnitureOverview.equals=" + DEFAULT_PROPERTY_FURNITURE_OVERVIEW);

        // Get all the propertyList where propertyFurnitureOverview equals to UPDATED_PROPERTY_FURNITURE_OVERVIEW
        defaultPropertyShouldNotBeFound("propertyFurnitureOverview.equals=" + UPDATED_PROPERTY_FURNITURE_OVERVIEW);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyFurnitureOverviewIsInShouldWork() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyFurnitureOverview in DEFAULT_PROPERTY_FURNITURE_OVERVIEW or UPDATED_PROPERTY_FURNITURE_OVERVIEW
        defaultPropertyShouldBeFound("propertyFurnitureOverview.in=" + DEFAULT_PROPERTY_FURNITURE_OVERVIEW + "," + UPDATED_PROPERTY_FURNITURE_OVERVIEW);

        // Get all the propertyList where propertyFurnitureOverview equals to UPDATED_PROPERTY_FURNITURE_OVERVIEW
        defaultPropertyShouldNotBeFound("propertyFurnitureOverview.in=" + UPDATED_PROPERTY_FURNITURE_OVERVIEW);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyFurnitureOverviewIsNullOrNotNull() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyFurnitureOverview is not null
        defaultPropertyShouldBeFound("propertyFurnitureOverview.specified=true");

        // Get all the propertyList where propertyFurnitureOverview is null
        defaultPropertyShouldNotBeFound("propertyFurnitureOverview.specified=false");
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyLocationOverviewIsEqualToSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyLocationOverview equals to DEFAULT_PROPERTY_LOCATION_OVERVIEW
        defaultPropertyShouldBeFound("propertyLocationOverview.equals=" + DEFAULT_PROPERTY_LOCATION_OVERVIEW);

        // Get all the propertyList where propertyLocationOverview equals to UPDATED_PROPERTY_LOCATION_OVERVIEW
        defaultPropertyShouldNotBeFound("propertyLocationOverview.equals=" + UPDATED_PROPERTY_LOCATION_OVERVIEW);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyLocationOverviewIsInShouldWork() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyLocationOverview in DEFAULT_PROPERTY_LOCATION_OVERVIEW or UPDATED_PROPERTY_LOCATION_OVERVIEW
        defaultPropertyShouldBeFound("propertyLocationOverview.in=" + DEFAULT_PROPERTY_LOCATION_OVERVIEW + "," + UPDATED_PROPERTY_LOCATION_OVERVIEW);

        // Get all the propertyList where propertyLocationOverview equals to UPDATED_PROPERTY_LOCATION_OVERVIEW
        defaultPropertyShouldNotBeFound("propertyLocationOverview.in=" + UPDATED_PROPERTY_LOCATION_OVERVIEW);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyLocationOverviewIsNullOrNotNull() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyLocationOverview is not null
        defaultPropertyShouldBeFound("propertyLocationOverview.specified=true");

        // Get all the propertyList where propertyLocationOverview is null
        defaultPropertyShouldNotBeFound("propertyLocationOverview.specified=false");
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyResidentialCommunityIsEqualToSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyResidentialCommunity equals to DEFAULT_PROPERTY_RESIDENTIAL_COMMUNITY
        defaultPropertyShouldBeFound("propertyResidentialCommunity.equals=" + DEFAULT_PROPERTY_RESIDENTIAL_COMMUNITY);

        // Get all the propertyList where propertyResidentialCommunity equals to UPDATED_PROPERTY_RESIDENTIAL_COMMUNITY
        defaultPropertyShouldNotBeFound("propertyResidentialCommunity.equals=" + UPDATED_PROPERTY_RESIDENTIAL_COMMUNITY);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyResidentialCommunityIsInShouldWork() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyResidentialCommunity in DEFAULT_PROPERTY_RESIDENTIAL_COMMUNITY or UPDATED_PROPERTY_RESIDENTIAL_COMMUNITY
        defaultPropertyShouldBeFound("propertyResidentialCommunity.in=" + DEFAULT_PROPERTY_RESIDENTIAL_COMMUNITY + "," + UPDATED_PROPERTY_RESIDENTIAL_COMMUNITY);

        // Get all the propertyList where propertyResidentialCommunity equals to UPDATED_PROPERTY_RESIDENTIAL_COMMUNITY
        defaultPropertyShouldNotBeFound("propertyResidentialCommunity.in=" + UPDATED_PROPERTY_RESIDENTIAL_COMMUNITY);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyResidentialCommunityIsNullOrNotNull() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyResidentialCommunity is not null
        defaultPropertyShouldBeFound("propertyResidentialCommunity.specified=true");

        // Get all the propertyList where propertyResidentialCommunity is null
        defaultPropertyShouldNotBeFound("propertyResidentialCommunity.specified=false");
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyEducationalAspectIsEqualToSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyEducationalAspect equals to DEFAULT_PROPERTY_EDUCATIONAL_ASPECT
        defaultPropertyShouldBeFound("propertyEducationalAspect.equals=" + DEFAULT_PROPERTY_EDUCATIONAL_ASPECT);

        // Get all the propertyList where propertyEducationalAspect equals to UPDATED_PROPERTY_EDUCATIONAL_ASPECT
        defaultPropertyShouldNotBeFound("propertyEducationalAspect.equals=" + UPDATED_PROPERTY_EDUCATIONAL_ASPECT);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyEducationalAspectIsInShouldWork() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyEducationalAspect in DEFAULT_PROPERTY_EDUCATIONAL_ASPECT or UPDATED_PROPERTY_EDUCATIONAL_ASPECT
        defaultPropertyShouldBeFound("propertyEducationalAspect.in=" + DEFAULT_PROPERTY_EDUCATIONAL_ASPECT + "," + UPDATED_PROPERTY_EDUCATIONAL_ASPECT);

        // Get all the propertyList where propertyEducationalAspect equals to UPDATED_PROPERTY_EDUCATIONAL_ASPECT
        defaultPropertyShouldNotBeFound("propertyEducationalAspect.in=" + UPDATED_PROPERTY_EDUCATIONAL_ASPECT);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyEducationalAspectIsNullOrNotNull() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyEducationalAspect is not null
        defaultPropertyShouldBeFound("propertyEducationalAspect.specified=true");

        // Get all the propertyList where propertyEducationalAspect is null
        defaultPropertyShouldNotBeFound("propertyEducationalAspect.specified=false");
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyExtraInfoIsEqualToSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyExtraInfo equals to DEFAULT_PROPERTY_EXTRA_INFO
        defaultPropertyShouldBeFound("propertyExtraInfo.equals=" + DEFAULT_PROPERTY_EXTRA_INFO);

        // Get all the propertyList where propertyExtraInfo equals to UPDATED_PROPERTY_EXTRA_INFO
        defaultPropertyShouldNotBeFound("propertyExtraInfo.equals=" + UPDATED_PROPERTY_EXTRA_INFO);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyExtraInfoIsInShouldWork() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyExtraInfo in DEFAULT_PROPERTY_EXTRA_INFO or UPDATED_PROPERTY_EXTRA_INFO
        defaultPropertyShouldBeFound("propertyExtraInfo.in=" + DEFAULT_PROPERTY_EXTRA_INFO + "," + UPDATED_PROPERTY_EXTRA_INFO);

        // Get all the propertyList where propertyExtraInfo equals to UPDATED_PROPERTY_EXTRA_INFO
        defaultPropertyShouldNotBeFound("propertyExtraInfo.in=" + UPDATED_PROPERTY_EXTRA_INFO);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyExtraInfoIsNullOrNotNull() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyExtraInfo is not null
        defaultPropertyShouldBeFound("propertyExtraInfo.specified=true");

        // Get all the propertyList where propertyExtraInfo is null
        defaultPropertyShouldNotBeFound("propertyExtraInfo.specified=false");
    }

    @Test
    @Transactional
    public void getAllPropertiesByLongitudeIsEqualToSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where longitude equals to DEFAULT_LONGITUDE
        defaultPropertyShouldBeFound("longitude.equals=" + DEFAULT_LONGITUDE);

        // Get all the propertyList where longitude equals to UPDATED_LONGITUDE
        defaultPropertyShouldNotBeFound("longitude.equals=" + UPDATED_LONGITUDE);
    }

    @Test
    @Transactional
    public void getAllPropertiesByLongitudeIsInShouldWork() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where longitude in DEFAULT_LONGITUDE or UPDATED_LONGITUDE
        defaultPropertyShouldBeFound("longitude.in=" + DEFAULT_LONGITUDE + "," + UPDATED_LONGITUDE);

        // Get all the propertyList where longitude equals to UPDATED_LONGITUDE
        defaultPropertyShouldNotBeFound("longitude.in=" + UPDATED_LONGITUDE);
    }

    @Test
    @Transactional
    public void getAllPropertiesByLongitudeIsNullOrNotNull() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where longitude is not null
        defaultPropertyShouldBeFound("longitude.specified=true");

        // Get all the propertyList where longitude is null
        defaultPropertyShouldNotBeFound("longitude.specified=false");
    }

    @Test
    @Transactional
    public void getAllPropertiesByLatitudeIsEqualToSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where latitude equals to DEFAULT_LATITUDE
        defaultPropertyShouldBeFound("latitude.equals=" + DEFAULT_LATITUDE);

        // Get all the propertyList where latitude equals to UPDATED_LATITUDE
        defaultPropertyShouldNotBeFound("latitude.equals=" + UPDATED_LATITUDE);
    }

    @Test
    @Transactional
    public void getAllPropertiesByLatitudeIsInShouldWork() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where latitude in DEFAULT_LATITUDE or UPDATED_LATITUDE
        defaultPropertyShouldBeFound("latitude.in=" + DEFAULT_LATITUDE + "," + UPDATED_LATITUDE);

        // Get all the propertyList where latitude equals to UPDATED_LATITUDE
        defaultPropertyShouldNotBeFound("latitude.in=" + UPDATED_LATITUDE);
    }

    @Test
    @Transactional
    public void getAllPropertiesByLatitudeIsNullOrNotNull() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where latitude is not null
        defaultPropertyShouldBeFound("latitude.specified=true");

        // Get all the propertyList where latitude is null
        defaultPropertyShouldNotBeFound("latitude.specified=false");
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyGoodPriceIsEqualToSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyGoodPrice equals to DEFAULT_PROPERTY_GOOD_PRICE
        defaultPropertyShouldBeFound("propertyGoodPrice.equals=" + DEFAULT_PROPERTY_GOOD_PRICE);

        // Get all the propertyList where propertyGoodPrice equals to UPDATED_PROPERTY_GOOD_PRICE
        defaultPropertyShouldNotBeFound("propertyGoodPrice.equals=" + UPDATED_PROPERTY_GOOD_PRICE);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyGoodPriceIsInShouldWork() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyGoodPrice in DEFAULT_PROPERTY_GOOD_PRICE or UPDATED_PROPERTY_GOOD_PRICE
        defaultPropertyShouldBeFound("propertyGoodPrice.in=" + DEFAULT_PROPERTY_GOOD_PRICE + "," + UPDATED_PROPERTY_GOOD_PRICE);

        // Get all the propertyList where propertyGoodPrice equals to UPDATED_PROPERTY_GOOD_PRICE
        defaultPropertyShouldNotBeFound("propertyGoodPrice.in=" + UPDATED_PROPERTY_GOOD_PRICE);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyGoodPriceIsNullOrNotNull() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyGoodPrice is not null
        defaultPropertyShouldBeFound("propertyGoodPrice.specified=true");

        // Get all the propertyList where propertyGoodPrice is null
        defaultPropertyShouldNotBeFound("propertyGoodPrice.specified=false");
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertySeenCountIsEqualToSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertySeenCount equals to DEFAULT_PROPERTY_SEEN_COUNT
        defaultPropertyShouldBeFound("propertySeenCount.equals=" + DEFAULT_PROPERTY_SEEN_COUNT);

        // Get all the propertyList where propertySeenCount equals to UPDATED_PROPERTY_SEEN_COUNT
        defaultPropertyShouldNotBeFound("propertySeenCount.equals=" + UPDATED_PROPERTY_SEEN_COUNT);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertySeenCountIsInShouldWork() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertySeenCount in DEFAULT_PROPERTY_SEEN_COUNT or UPDATED_PROPERTY_SEEN_COUNT
        defaultPropertyShouldBeFound("propertySeenCount.in=" + DEFAULT_PROPERTY_SEEN_COUNT + "," + UPDATED_PROPERTY_SEEN_COUNT);

        // Get all the propertyList where propertySeenCount equals to UPDATED_PROPERTY_SEEN_COUNT
        defaultPropertyShouldNotBeFound("propertySeenCount.in=" + UPDATED_PROPERTY_SEEN_COUNT);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertySeenCountIsNullOrNotNull() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertySeenCount is not null
        defaultPropertyShouldBeFound("propertySeenCount.specified=true");

        // Get all the propertyList where propertySeenCount is null
        defaultPropertyShouldNotBeFound("propertySeenCount.specified=false");
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertySeenCountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertySeenCount greater than or equals to DEFAULT_PROPERTY_SEEN_COUNT
        defaultPropertyShouldBeFound("propertySeenCount.greaterOrEqualThan=" + DEFAULT_PROPERTY_SEEN_COUNT);

        // Get all the propertyList where propertySeenCount greater than or equals to UPDATED_PROPERTY_SEEN_COUNT
        defaultPropertyShouldNotBeFound("propertySeenCount.greaterOrEqualThan=" + UPDATED_PROPERTY_SEEN_COUNT);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertySeenCountIsLessThanSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertySeenCount less than or equals to DEFAULT_PROPERTY_SEEN_COUNT
        defaultPropertyShouldNotBeFound("propertySeenCount.lessThan=" + DEFAULT_PROPERTY_SEEN_COUNT);

        // Get all the propertyList where propertySeenCount less than or equals to UPDATED_PROPERTY_SEEN_COUNT
        defaultPropertyShouldBeFound("propertySeenCount.lessThan=" + UPDATED_PROPERTY_SEEN_COUNT);
    }


    @Test
    @Transactional
    public void getAllPropertiesByPropertyIsSoldIsEqualToSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyIsSold equals to DEFAULT_PROPERTY_IS_SOLD
        defaultPropertyShouldBeFound("propertyIsSold.equals=" + DEFAULT_PROPERTY_IS_SOLD);

        // Get all the propertyList where propertyIsSold equals to UPDATED_PROPERTY_IS_SOLD
        defaultPropertyShouldNotBeFound("propertyIsSold.equals=" + UPDATED_PROPERTY_IS_SOLD);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyIsSoldIsInShouldWork() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyIsSold in DEFAULT_PROPERTY_IS_SOLD or UPDATED_PROPERTY_IS_SOLD
        defaultPropertyShouldBeFound("propertyIsSold.in=" + DEFAULT_PROPERTY_IS_SOLD + "," + UPDATED_PROPERTY_IS_SOLD);

        // Get all the propertyList where propertyIsSold equals to UPDATED_PROPERTY_IS_SOLD
        defaultPropertyShouldNotBeFound("propertyIsSold.in=" + UPDATED_PROPERTY_IS_SOLD);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyIsSoldIsNullOrNotNull() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyIsSold is not null
        defaultPropertyShouldBeFound("propertyIsSold.specified=true");

        // Get all the propertyList where propertyIsSold is null
        defaultPropertyShouldNotBeFound("propertyIsSold.specified=false");
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyIsRentIsEqualToSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyIsRent equals to DEFAULT_PROPERTY_IS_RENT
        defaultPropertyShouldBeFound("propertyIsRent.equals=" + DEFAULT_PROPERTY_IS_RENT);

        // Get all the propertyList where propertyIsRent equals to UPDATED_PROPERTY_IS_RENT
        defaultPropertyShouldNotBeFound("propertyIsRent.equals=" + UPDATED_PROPERTY_IS_RENT);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyIsRentIsInShouldWork() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyIsRent in DEFAULT_PROPERTY_IS_RENT or UPDATED_PROPERTY_IS_RENT
        defaultPropertyShouldBeFound("propertyIsRent.in=" + DEFAULT_PROPERTY_IS_RENT + "," + UPDATED_PROPERTY_IS_RENT);

        // Get all the propertyList where propertyIsRent equals to UPDATED_PROPERTY_IS_RENT
        defaultPropertyShouldNotBeFound("propertyIsRent.in=" + UPDATED_PROPERTY_IS_RENT);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyIsRentIsNullOrNotNull() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyIsRent is not null
        defaultPropertyShouldBeFound("propertyIsRent.specified=true");

        // Get all the propertyList where propertyIsRent is null
        defaultPropertyShouldNotBeFound("propertyIsRent.specified=false");
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyAvailableIsEqualToSomething() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyAvailable equals to DEFAULT_PROPERTY_AVAILABLE
        defaultPropertyShouldBeFound("propertyAvailable.equals=" + DEFAULT_PROPERTY_AVAILABLE);

        // Get all the propertyList where propertyAvailable equals to UPDATED_PROPERTY_AVAILABLE
        defaultPropertyShouldNotBeFound("propertyAvailable.equals=" + UPDATED_PROPERTY_AVAILABLE);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyAvailableIsInShouldWork() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyAvailable in DEFAULT_PROPERTY_AVAILABLE or UPDATED_PROPERTY_AVAILABLE
        defaultPropertyShouldBeFound("propertyAvailable.in=" + DEFAULT_PROPERTY_AVAILABLE + "," + UPDATED_PROPERTY_AVAILABLE);

        // Get all the propertyList where propertyAvailable equals to UPDATED_PROPERTY_AVAILABLE
        defaultPropertyShouldNotBeFound("propertyAvailable.in=" + UPDATED_PROPERTY_AVAILABLE);
    }

    @Test
    @Transactional
    public void getAllPropertiesByPropertyAvailableIsNullOrNotNull() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList where propertyAvailable is not null
        defaultPropertyShouldBeFound("propertyAvailable.specified=true");

        // Get all the propertyList where propertyAvailable is null
        defaultPropertyShouldNotBeFound("propertyAvailable.specified=false");
    }

    @Test
    @Transactional
    public void getAllPropertiesByLocationIsEqualToSomething() throws Exception {
        // Initialize the database
        Location location = LocationResourceIntTest.createEntity(em);
        em.persist(location);
        em.flush();
        property.setLocation(location);
        propertyRepository.saveAndFlush(property);
        Long locationId = location.getId();

        // Get all the propertyList where location equals to locationId
        defaultPropertyShouldBeFound("locationId.equals=" + locationId);

        // Get all the propertyList where location equals to locationId + 1
        defaultPropertyShouldNotBeFound("locationId.equals=" + (locationId + 1));
    }


    @Test
    @Transactional
    public void getAllPropertiesByResidentialAreaIsEqualToSomething() throws Exception {
        // Initialize the database
        ResidentialArea residentialArea = ResidentialAreaResourceIntTest.createEntity(em);
        em.persist(residentialArea);
        em.flush();
        property.setResidentialArea(residentialArea);
        propertyRepository.saveAndFlush(property);
        Long residentialAreaId = residentialArea.getId();

        // Get all the propertyList where residentialArea equals to residentialAreaId
        defaultPropertyShouldBeFound("residentialAreaId.equals=" + residentialAreaId);

        // Get all the propertyList where residentialArea equals to residentialAreaId + 1
        defaultPropertyShouldNotBeFound("residentialAreaId.equals=" + (residentialAreaId + 1));
    }


    @Test
    @Transactional
    public void getAllPropertiesByTagIsEqualToSomething() throws Exception {
        // Initialize the database
        Tag tag = TagResourceIntTest.createEntity(em);
        em.persist(tag);
        em.flush();
        property.addTag(tag);
        propertyRepository.saveAndFlush(property);
        Long tagId = tag.getId();

        // Get all the propertyList where tag equals to tagId
        defaultPropertyShouldBeFound("tagId.equals=" + tagId);

        // Get all the propertyList where tag equals to tagId + 1
        defaultPropertyShouldNotBeFound("tagId.equals=" + (tagId + 1));
    }


    @Test
    @Transactional
    public void getAllPropertiesByBuildingtypeIsEqualToSomething() throws Exception {
        // Initialize the database
        BuildingType buildingtype = BuildingTypeResourceIntTest.createEntity(em);
        em.persist(buildingtype);
        em.flush();
        property.addBuildingtype(buildingtype);
        propertyRepository.saveAndFlush(property);
        Long buildingtypeId = buildingtype.getId();

        // Get all the propertyList where buildingtype equals to buildingtypeId
        defaultPropertyShouldBeFound("buildingtypeId.equals=" + buildingtypeId);

        // Get all the propertyList where buildingtype equals to buildingtypeId + 1
        defaultPropertyShouldNotBeFound("buildingtypeId.equals=" + (buildingtypeId + 1));
    }


    @Test
    @Transactional
    public void getAllPropertiesByPhotoIsEqualToSomething() throws Exception {
        // Initialize the database
        Photo photo = PhotoResourceIntTest.createEntity(em);
        em.persist(photo);
        em.flush();
        property.addPhoto(photo);
        propertyRepository.saveAndFlush(property);
        Long photoId = photo.getId();

        // Get all the propertyList where photo equals to photoId
        defaultPropertyShouldBeFound("photoId.equals=" + photoId);

        // Get all the propertyList where photo equals to photoId + 1
        defaultPropertyShouldNotBeFound("photoId.equals=" + (photoId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultPropertyShouldBeFound(String filter) throws Exception {
        restPropertyMockMvc.perform(get("/api/properties?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(property.getId().intValue())))
            .andExpect(jsonPath("$.[*].propertyCode").value(hasItem(DEFAULT_PROPERTY_CODE.toString())))
            .andExpect(jsonPath("$.[*].propertyName").value(hasItem(DEFAULT_PROPERTY_NAME.toString())))
            .andExpect(jsonPath("$.[*].propertyAlias").value(hasItem(DEFAULT_PROPERTY_ALIAS.toString())))
            .andExpect(jsonPath("$.[*].propertyTransaction").value(hasItem(DEFAULT_PROPERTY_TRANSACTION.toString())))
            .andExpect(jsonPath("$.[*].propertyNumber").value(hasItem(DEFAULT_PROPERTY_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].propertyRoad").value(hasItem(DEFAULT_PROPERTY_ROAD.toString())))
            .andExpect(jsonPath("$.[*].propertyWard").value(hasItem(DEFAULT_PROPERTY_WARD.toString())))
            .andExpect(jsonPath("$.[*].propertyDistrict").value(hasItem(DEFAULT_PROPERTY_DISTRICT.toString())))
            .andExpect(jsonPath("$.[*].propertyProvince").value(hasItem(DEFAULT_PROPERTY_PROVINCE.toString())))
            .andExpect(jsonPath("$.[*].propertyDescription").value(hasItem(DEFAULT_PROPERTY_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].propertyBedRooms").value(hasItem(DEFAULT_PROPERTY_BED_ROOMS)))
            .andExpect(jsonPath("$.[*].propertyBathRooms").value(hasItem(DEFAULT_PROPERTY_BATH_ROOMS)))
            .andExpect(jsonPath("$.[*].propertySquare").value(hasItem(DEFAULT_PROPERTY_SQUARE.doubleValue())))
            .andExpect(jsonPath("$.[*].propertyUsePurpose").value(hasItem(DEFAULT_PROPERTY_USE_PURPOSE.toString())))
            .andExpect(jsonPath("$.[*].propertyOwnerType").value(hasItem(DEFAULT_PROPERTY_OWNER_TYPE.toString())))
            .andExpect(jsonPath("$.[*].propertyTower").value(hasItem(DEFAULT_PROPERTY_TOWER.toString())))
            .andExpect(jsonPath("$.[*].propertyRentPrice").value(hasItem(DEFAULT_PROPERTY_RENT_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].propertyRentUnit").value(hasItem(DEFAULT_PROPERTY_RENT_UNIT.toString())))
            .andExpect(jsonPath("$.[*].propertyRentStartedDate").value(hasItem(sameInstant(DEFAULT_PROPERTY_RENT_STARTED_DATE))))
            .andExpect(jsonPath("$.[*].propertySellPrice").value(hasItem(DEFAULT_PROPERTY_SELL_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].propertySellUnit").value(hasItem(DEFAULT_PROPERTY_SELL_UNIT.toString())))
            .andExpect(jsonPath("$.[*].propertySellStartedDate").value(hasItem(sameInstant(DEFAULT_PROPERTY_SELL_STARTED_DATE))))
            .andExpect(jsonPath("$.[*].propertySofa").value(hasItem(DEFAULT_PROPERTY_SOFA.booleanValue())))
            .andExpect(jsonPath("$.[*].propertyDiningTable").value(hasItem(DEFAULT_PROPERTY_DINING_TABLE.booleanValue())))
            .andExpect(jsonPath("$.[*].propertyKitchen").value(hasItem(DEFAULT_PROPERTY_KITCHEN.booleanValue())))
            .andExpect(jsonPath("$.[*].propertyCabinetKitchen").value(hasItem(DEFAULT_PROPERTY_CABINET_KITCHEN.booleanValue())))
            .andExpect(jsonPath("$.[*].propertyKitchenEquipment").value(hasItem(DEFAULT_PROPERTY_KITCHEN_EQUIPMENT.booleanValue())))
            .andExpect(jsonPath("$.[*].propertyWardrobe").value(hasItem(DEFAULT_PROPERTY_WARDROBE.booleanValue())))
            .andExpect(jsonPath("$.[*].propertyMakeupTable").value(hasItem(DEFAULT_PROPERTY_MAKEUP_TABLE.booleanValue())))
            .andExpect(jsonPath("$.[*].propertyDesk").value(hasItem(DEFAULT_PROPERTY_DESK.booleanValue())))
            .andExpect(jsonPath("$.[*].propertyTivi").value(hasItem(DEFAULT_PROPERTY_TIVI.booleanValue())))
            .andExpect(jsonPath("$.[*].propertyWashingMachine").value(hasItem(DEFAULT_PROPERTY_WASHING_MACHINE.booleanValue())))
            .andExpect(jsonPath("$.[*].propertyRefrigerator").value(hasItem(DEFAULT_PROPERTY_REFRIGERATOR.booleanValue())))
            .andExpect(jsonPath("$.[*].propertyAircondition").value(hasItem(DEFAULT_PROPERTY_AIRCONDITION.booleanValue())))
            .andExpect(jsonPath("$.[*].propertyMicrowave").value(hasItem(DEFAULT_PROPERTY_MICROWAVE.booleanValue())))
            .andExpect(jsonPath("$.[*].propertyWaterHeater").value(hasItem(DEFAULT_PROPERTY_WATER_HEATER.booleanValue())))
            .andExpect(jsonPath("$.[*].propertyBed").value(hasItem(DEFAULT_PROPERTY_BED.booleanValue())))
            .andExpect(jsonPath("$.[*].propertyHeater").value(hasItem(DEFAULT_PROPERTY_HEATER.booleanValue())))
            .andExpect(jsonPath("$.[*].propertyAudioEquipment").value(hasItem(DEFAULT_PROPERTY_AUDIO_EQUIPMENT.booleanValue())))
            .andExpect(jsonPath("$.[*].propertyInternet").value(hasItem(DEFAULT_PROPERTY_INTERNET.booleanValue())))
            .andExpect(jsonPath("$.[*].propertyCableTivi").value(hasItem(DEFAULT_PROPERTY_CABLE_TIVI.booleanValue())))
            .andExpect(jsonPath("$.[*].propertyPetPermission").value(hasItem(DEFAULT_PROPERTY_PET_PERMISSION.booleanValue())))
            .andExpect(jsonPath("$.[*].propertyElevator").value(hasItem(DEFAULT_PROPERTY_ELEVATOR.booleanValue())))
            .andExpect(jsonPath("$.[*].propertySwimmingPool").value(hasItem(DEFAULT_PROPERTY_SWIMMING_POOL.booleanValue())))
            .andExpect(jsonPath("$.[*].propertyGym").value(hasItem(DEFAULT_PROPERTY_GYM.booleanValue())))
            .andExpect(jsonPath("$.[*].propertyFunctionalArea").value(hasItem(DEFAULT_PROPERTY_FUNCTIONAL_AREA.booleanValue())))
            .andExpect(jsonPath("$.[*].propertyOpen24h").value(hasItem(DEFAULT_PROPERTY_OPEN_24_H.booleanValue())))
            .andExpect(jsonPath("$.[*].propertyCarPark").value(hasItem(DEFAULT_PROPERTY_CAR_PARK.booleanValue())))
            .andExpect(jsonPath("$.[*].propertyBalcony").value(hasItem(DEFAULT_PROPERTY_BALCONY.booleanValue())))
            .andExpect(jsonPath("$.[*].propertySauna").value(hasItem(DEFAULT_PROPERTY_SAUNA.booleanValue())))
            .andExpect(jsonPath("$.[*].propertySteamSauna").value(hasItem(DEFAULT_PROPERTY_STEAM_SAUNA.booleanValue())))
            .andExpect(jsonPath("$.[*].propertyAttraction").value(hasItem(DEFAULT_PROPERTY_ATTRACTION.booleanValue())))
            .andExpect(jsonPath("$.[*].propertySpecialFeature").value(hasItem(DEFAULT_PROPERTY_SPECIAL_FEATURE.toString())))
            .andExpect(jsonPath("$.[*].propertyFurnitureOverview").value(hasItem(DEFAULT_PROPERTY_FURNITURE_OVERVIEW.toString())))
            .andExpect(jsonPath("$.[*].propertyLocationOverview").value(hasItem(DEFAULT_PROPERTY_LOCATION_OVERVIEW.toString())))
            .andExpect(jsonPath("$.[*].propertyResidentialCommunity").value(hasItem(DEFAULT_PROPERTY_RESIDENTIAL_COMMUNITY.toString())))
            .andExpect(jsonPath("$.[*].propertyEducationalAspect").value(hasItem(DEFAULT_PROPERTY_EDUCATIONAL_ASPECT.toString())))
            .andExpect(jsonPath("$.[*].propertyExtraInfo").value(hasItem(DEFAULT_PROPERTY_EXTRA_INFO.toString())))
            .andExpect(jsonPath("$.[*].propertyDraftContentType").value(hasItem(DEFAULT_PROPERTY_DRAFT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].propertyDraft").value(hasItem(Base64Utils.encodeToString(DEFAULT_PROPERTY_DRAFT))))
            .andExpect(jsonPath("$.[*].longitude").value(hasItem(DEFAULT_LONGITUDE.doubleValue())))
            .andExpect(jsonPath("$.[*].latitude").value(hasItem(DEFAULT_LATITUDE.doubleValue())))
            .andExpect(jsonPath("$.[*].propertyGoodPrice").value(hasItem(DEFAULT_PROPERTY_GOOD_PRICE.booleanValue())))
            .andExpect(jsonPath("$.[*].propertySeenCount").value(hasItem(DEFAULT_PROPERTY_SEEN_COUNT.intValue())))
            .andExpect(jsonPath("$.[*].propertyIsSold").value(hasItem(DEFAULT_PROPERTY_IS_SOLD.booleanValue())))
            .andExpect(jsonPath("$.[*].propertyIsRent").value(hasItem(DEFAULT_PROPERTY_IS_RENT.booleanValue())))
            .andExpect(jsonPath("$.[*].propertyAvailable").value(hasItem(DEFAULT_PROPERTY_AVAILABLE.booleanValue())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultPropertyShouldNotBeFound(String filter) throws Exception {
        restPropertyMockMvc.perform(get("/api/properties?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @Transactional
    public void getNonExistingProperty() throws Exception {
        // Get the property
        restPropertyMockMvc.perform(get("/api/properties/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProperty() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        int databaseSizeBeforeUpdate = propertyRepository.findAll().size();

        // Update the property
        Property updatedProperty = propertyRepository.findById(property.getId()).get();
        // Disconnect from session so that the updates on updatedProperty are not directly saved in db
        em.detach(updatedProperty);
        updatedProperty
            .propertyCode(UPDATED_PROPERTY_CODE)
            .propertyName(UPDATED_PROPERTY_NAME)
            .propertyAlias(UPDATED_PROPERTY_ALIAS)
            .propertyTransaction(UPDATED_PROPERTY_TRANSACTION)
            .propertyNumber(UPDATED_PROPERTY_NUMBER)
            .propertyRoad(UPDATED_PROPERTY_ROAD)
            .propertyWard(UPDATED_PROPERTY_WARD)
            .propertyDistrict(UPDATED_PROPERTY_DISTRICT)
            .propertyProvince(UPDATED_PROPERTY_PROVINCE)
            .propertyDescription(UPDATED_PROPERTY_DESCRIPTION)
            .propertyBedRooms(UPDATED_PROPERTY_BED_ROOMS)
            .propertyBathRooms(UPDATED_PROPERTY_BATH_ROOMS)
            .propertySquare(UPDATED_PROPERTY_SQUARE)
            .propertyUsePurpose(UPDATED_PROPERTY_USE_PURPOSE)
            .propertyOwnerType(UPDATED_PROPERTY_OWNER_TYPE)
            .propertyTower(UPDATED_PROPERTY_TOWER)
            .propertyRentPrice(UPDATED_PROPERTY_RENT_PRICE)
            .propertyRentUnit(UPDATED_PROPERTY_RENT_UNIT)
            .propertyRentStartedDate(UPDATED_PROPERTY_RENT_STARTED_DATE)
            .propertySellPrice(UPDATED_PROPERTY_SELL_PRICE)
            .propertySellUnit(UPDATED_PROPERTY_SELL_UNIT)
            .propertySellStartedDate(UPDATED_PROPERTY_SELL_STARTED_DATE)
            .propertySofa(UPDATED_PROPERTY_SOFA)
            .propertyDiningTable(UPDATED_PROPERTY_DINING_TABLE)
            .propertyKitchen(UPDATED_PROPERTY_KITCHEN)
            .propertyCabinetKitchen(UPDATED_PROPERTY_CABINET_KITCHEN)
            .propertyKitchenEquipment(UPDATED_PROPERTY_KITCHEN_EQUIPMENT)
            .propertyWardrobe(UPDATED_PROPERTY_WARDROBE)
            .propertyMakeupTable(UPDATED_PROPERTY_MAKEUP_TABLE)
            .propertyDesk(UPDATED_PROPERTY_DESK)
            .propertyTivi(UPDATED_PROPERTY_TIVI)
            .propertyWashingMachine(UPDATED_PROPERTY_WASHING_MACHINE)
            .propertyRefrigerator(UPDATED_PROPERTY_REFRIGERATOR)
            .propertyAircondition(UPDATED_PROPERTY_AIRCONDITION)
            .propertyMicrowave(UPDATED_PROPERTY_MICROWAVE)
            .propertyWaterHeater(UPDATED_PROPERTY_WATER_HEATER)
            .propertyBed(UPDATED_PROPERTY_BED)
            .propertyHeater(UPDATED_PROPERTY_HEATER)
            .propertyAudioEquipment(UPDATED_PROPERTY_AUDIO_EQUIPMENT)
            .propertyInternet(UPDATED_PROPERTY_INTERNET)
            .propertyCableTivi(UPDATED_PROPERTY_CABLE_TIVI)
            .propertyPetPermission(UPDATED_PROPERTY_PET_PERMISSION)
            .propertyElevator(UPDATED_PROPERTY_ELEVATOR)
            .propertySwimmingPool(UPDATED_PROPERTY_SWIMMING_POOL)
            .propertyGym(UPDATED_PROPERTY_GYM)
            .propertyFunctionalArea(UPDATED_PROPERTY_FUNCTIONAL_AREA)
            .propertyOpen24h(UPDATED_PROPERTY_OPEN_24_H)
            .propertyCarPark(UPDATED_PROPERTY_CAR_PARK)
            .propertyBalcony(UPDATED_PROPERTY_BALCONY)
            .propertySauna(UPDATED_PROPERTY_SAUNA)
            .propertySteamSauna(UPDATED_PROPERTY_STEAM_SAUNA)
            .propertyAttraction(UPDATED_PROPERTY_ATTRACTION)
            .propertySpecialFeature(UPDATED_PROPERTY_SPECIAL_FEATURE)
            .propertyFurnitureOverview(UPDATED_PROPERTY_FURNITURE_OVERVIEW)
            .propertyLocationOverview(UPDATED_PROPERTY_LOCATION_OVERVIEW)
            .propertyResidentialCommunity(UPDATED_PROPERTY_RESIDENTIAL_COMMUNITY)
            .propertyEducationalAspect(UPDATED_PROPERTY_EDUCATIONAL_ASPECT)
            .propertyExtraInfo(UPDATED_PROPERTY_EXTRA_INFO)
            .propertyDraft(UPDATED_PROPERTY_DRAFT)
            .propertyDraftContentType(UPDATED_PROPERTY_DRAFT_CONTENT_TYPE)
            .longitude(UPDATED_LONGITUDE)
            .latitude(UPDATED_LATITUDE)
            .propertyGoodPrice(UPDATED_PROPERTY_GOOD_PRICE)
            .propertySeenCount(UPDATED_PROPERTY_SEEN_COUNT)
            .propertyIsSold(UPDATED_PROPERTY_IS_SOLD)
            .propertyIsRent(UPDATED_PROPERTY_IS_RENT)
            .propertyAvailable(UPDATED_PROPERTY_AVAILABLE);
        PropertyDTO propertyDTO = propertyMapper.toDto(updatedProperty);

        restPropertyMockMvc.perform(put("/api/properties")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(propertyDTO)))
            .andExpect(status().isOk());

        // Validate the Property in the database
        List<Property> propertyList = propertyRepository.findAll();
        assertThat(propertyList).hasSize(databaseSizeBeforeUpdate);
        Property testProperty = propertyList.get(propertyList.size() - 1);
        assertThat(testProperty.getPropertyCode()).isEqualTo(UPDATED_PROPERTY_CODE);
        assertThat(testProperty.getPropertyName()).isEqualTo(UPDATED_PROPERTY_NAME);
        assertThat(testProperty.getPropertyAlias()).isEqualTo(UPDATED_PROPERTY_ALIAS);
        assertThat(testProperty.getPropertyTransaction()).isEqualTo(UPDATED_PROPERTY_TRANSACTION);
        assertThat(testProperty.getPropertyNumber()).isEqualTo(UPDATED_PROPERTY_NUMBER);
        assertThat(testProperty.getPropertyRoad()).isEqualTo(UPDATED_PROPERTY_ROAD);
        assertThat(testProperty.getPropertyWard()).isEqualTo(UPDATED_PROPERTY_WARD);
        assertThat(testProperty.getPropertyDistrict()).isEqualTo(UPDATED_PROPERTY_DISTRICT);
        assertThat(testProperty.getPropertyProvince()).isEqualTo(UPDATED_PROPERTY_PROVINCE);
        assertThat(testProperty.getPropertyDescription()).isEqualTo(UPDATED_PROPERTY_DESCRIPTION);
        assertThat(testProperty.getPropertyBedRooms()).isEqualTo(UPDATED_PROPERTY_BED_ROOMS);
        assertThat(testProperty.getPropertyBathRooms()).isEqualTo(UPDATED_PROPERTY_BATH_ROOMS);
        assertThat(testProperty.getPropertySquare()).isEqualTo(UPDATED_PROPERTY_SQUARE);
        assertThat(testProperty.getPropertyUsePurpose()).isEqualTo(UPDATED_PROPERTY_USE_PURPOSE);
        assertThat(testProperty.getPropertyOwnerType()).isEqualTo(UPDATED_PROPERTY_OWNER_TYPE);
        assertThat(testProperty.getPropertyTower()).isEqualTo(UPDATED_PROPERTY_TOWER);
        assertThat(testProperty.getPropertyRentPrice()).isEqualTo(UPDATED_PROPERTY_RENT_PRICE);
        assertThat(testProperty.getPropertyRentUnit()).isEqualTo(UPDATED_PROPERTY_RENT_UNIT);
        assertThat(testProperty.getPropertyRentStartedDate()).isEqualTo(UPDATED_PROPERTY_RENT_STARTED_DATE);
        assertThat(testProperty.getPropertySellPrice()).isEqualTo(UPDATED_PROPERTY_SELL_PRICE);
        assertThat(testProperty.getPropertySellUnit()).isEqualTo(UPDATED_PROPERTY_SELL_UNIT);
        assertThat(testProperty.getPropertySellStartedDate()).isEqualTo(UPDATED_PROPERTY_SELL_STARTED_DATE);
        assertThat(testProperty.isPropertySofa()).isEqualTo(UPDATED_PROPERTY_SOFA);
        assertThat(testProperty.isPropertyDiningTable()).isEqualTo(UPDATED_PROPERTY_DINING_TABLE);
        assertThat(testProperty.isPropertyKitchen()).isEqualTo(UPDATED_PROPERTY_KITCHEN);
        assertThat(testProperty.isPropertyCabinetKitchen()).isEqualTo(UPDATED_PROPERTY_CABINET_KITCHEN);
        assertThat(testProperty.isPropertyKitchenEquipment()).isEqualTo(UPDATED_PROPERTY_KITCHEN_EQUIPMENT);
        assertThat(testProperty.isPropertyWardrobe()).isEqualTo(UPDATED_PROPERTY_WARDROBE);
        assertThat(testProperty.isPropertyMakeupTable()).isEqualTo(UPDATED_PROPERTY_MAKEUP_TABLE);
        assertThat(testProperty.isPropertyDesk()).isEqualTo(UPDATED_PROPERTY_DESK);
        assertThat(testProperty.isPropertyTivi()).isEqualTo(UPDATED_PROPERTY_TIVI);
        assertThat(testProperty.isPropertyWashingMachine()).isEqualTo(UPDATED_PROPERTY_WASHING_MACHINE);
        assertThat(testProperty.isPropertyRefrigerator()).isEqualTo(UPDATED_PROPERTY_REFRIGERATOR);
        assertThat(testProperty.isPropertyAircondition()).isEqualTo(UPDATED_PROPERTY_AIRCONDITION);
        assertThat(testProperty.isPropertyMicrowave()).isEqualTo(UPDATED_PROPERTY_MICROWAVE);
        assertThat(testProperty.isPropertyWaterHeater()).isEqualTo(UPDATED_PROPERTY_WATER_HEATER);
        assertThat(testProperty.isPropertyBed()).isEqualTo(UPDATED_PROPERTY_BED);
        assertThat(testProperty.isPropertyHeater()).isEqualTo(UPDATED_PROPERTY_HEATER);
        assertThat(testProperty.isPropertyAudioEquipment()).isEqualTo(UPDATED_PROPERTY_AUDIO_EQUIPMENT);
        assertThat(testProperty.isPropertyInternet()).isEqualTo(UPDATED_PROPERTY_INTERNET);
        assertThat(testProperty.isPropertyCableTivi()).isEqualTo(UPDATED_PROPERTY_CABLE_TIVI);
        assertThat(testProperty.isPropertyPetPermission()).isEqualTo(UPDATED_PROPERTY_PET_PERMISSION);
        assertThat(testProperty.isPropertyElevator()).isEqualTo(UPDATED_PROPERTY_ELEVATOR);
        assertThat(testProperty.isPropertySwimmingPool()).isEqualTo(UPDATED_PROPERTY_SWIMMING_POOL);
        assertThat(testProperty.isPropertyGym()).isEqualTo(UPDATED_PROPERTY_GYM);
        assertThat(testProperty.isPropertyFunctionalArea()).isEqualTo(UPDATED_PROPERTY_FUNCTIONAL_AREA);
        assertThat(testProperty.isPropertyOpen24h()).isEqualTo(UPDATED_PROPERTY_OPEN_24_H);
        assertThat(testProperty.isPropertyCarPark()).isEqualTo(UPDATED_PROPERTY_CAR_PARK);
        assertThat(testProperty.isPropertyBalcony()).isEqualTo(UPDATED_PROPERTY_BALCONY);
        assertThat(testProperty.isPropertySauna()).isEqualTo(UPDATED_PROPERTY_SAUNA);
        assertThat(testProperty.isPropertySteamSauna()).isEqualTo(UPDATED_PROPERTY_STEAM_SAUNA);
        assertThat(testProperty.isPropertyAttraction()).isEqualTo(UPDATED_PROPERTY_ATTRACTION);
        assertThat(testProperty.getPropertySpecialFeature()).isEqualTo(UPDATED_PROPERTY_SPECIAL_FEATURE);
        assertThat(testProperty.getPropertyFurnitureOverview()).isEqualTo(UPDATED_PROPERTY_FURNITURE_OVERVIEW);
        assertThat(testProperty.getPropertyLocationOverview()).isEqualTo(UPDATED_PROPERTY_LOCATION_OVERVIEW);
        assertThat(testProperty.getPropertyResidentialCommunity()).isEqualTo(UPDATED_PROPERTY_RESIDENTIAL_COMMUNITY);
        assertThat(testProperty.getPropertyEducationalAspect()).isEqualTo(UPDATED_PROPERTY_EDUCATIONAL_ASPECT);
        assertThat(testProperty.getPropertyExtraInfo()).isEqualTo(UPDATED_PROPERTY_EXTRA_INFO);
        assertThat(testProperty.getPropertyDraft()).isEqualTo(UPDATED_PROPERTY_DRAFT);
        assertThat(testProperty.getPropertyDraftContentType()).isEqualTo(UPDATED_PROPERTY_DRAFT_CONTENT_TYPE);
        assertThat(testProperty.getLongitude()).isEqualTo(UPDATED_LONGITUDE);
        assertThat(testProperty.getLatitude()).isEqualTo(UPDATED_LATITUDE);
        assertThat(testProperty.isPropertyGoodPrice()).isEqualTo(UPDATED_PROPERTY_GOOD_PRICE);
        assertThat(testProperty.getPropertySeenCount()).isEqualTo(UPDATED_PROPERTY_SEEN_COUNT);
        assertThat(testProperty.isPropertyIsSold()).isEqualTo(UPDATED_PROPERTY_IS_SOLD);
        assertThat(testProperty.isPropertyIsRent()).isEqualTo(UPDATED_PROPERTY_IS_RENT);
        assertThat(testProperty.isPropertyAvailable()).isEqualTo(UPDATED_PROPERTY_AVAILABLE);
    }

    @Test
    @Transactional
    public void updateNonExistingProperty() throws Exception {
        int databaseSizeBeforeUpdate = propertyRepository.findAll().size();

        // Create the Property
        PropertyDTO propertyDTO = propertyMapper.toDto(property);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restPropertyMockMvc.perform(put("/api/properties")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(propertyDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Property in the database
        List<Property> propertyList = propertyRepository.findAll();
        assertThat(propertyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProperty() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        int databaseSizeBeforeDelete = propertyRepository.findAll().size();

        // Get the property
        restPropertyMockMvc.perform(delete("/api/properties/{id}", property.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Property> propertyList = propertyRepository.findAll();
        assertThat(propertyList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Property.class);
        Property property1 = new Property();
        property1.setId(1L);
        Property property2 = new Property();
        property2.setId(property1.getId());
        assertThat(property1).isEqualTo(property2);
        property2.setId(2L);
        assertThat(property1).isNotEqualTo(property2);
        property1.setId(null);
        assertThat(property1).isNotEqualTo(property2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PropertyDTO.class);
        PropertyDTO propertyDTO1 = new PropertyDTO();
        propertyDTO1.setId(1L);
        PropertyDTO propertyDTO2 = new PropertyDTO();
        assertThat(propertyDTO1).isNotEqualTo(propertyDTO2);
        propertyDTO2.setId(propertyDTO1.getId());
        assertThat(propertyDTO1).isEqualTo(propertyDTO2);
        propertyDTO2.setId(2L);
        assertThat(propertyDTO1).isNotEqualTo(propertyDTO2);
        propertyDTO1.setId(null);
        assertThat(propertyDTO1).isNotEqualTo(propertyDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(propertyMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(propertyMapper.fromId(null)).isNull();
    }
}
