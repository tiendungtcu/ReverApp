package com.tcutma.realstate.web.rest;

import com.tcutma.realstate.RiverApp;

import com.tcutma.realstate.domain.ResidentialArea;
import com.tcutma.realstate.domain.Photo;
import com.tcutma.realstate.domain.Tag;
import com.tcutma.realstate.repository.ResidentialAreaRepository;
import com.tcutma.realstate.service.ResidentialAreaService;
import com.tcutma.realstate.service.dto.ResidentialAreaDTO;
import com.tcutma.realstate.service.mapper.ResidentialAreaMapper;
import com.tcutma.realstate.web.rest.errors.ExceptionTranslator;
import com.tcutma.realstate.service.ResidentialAreaQueryService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;


import static com.tcutma.realstate.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ResidentialAreaResource REST controller.
 *
 * @see ResidentialAreaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RiverApp.class)
public class ResidentialAreaResourceIntTest {

    private static final String DEFAULT_RESIDENTIAL_NAME = "AAAAAAAAAA";
    private static final String UPDATED_RESIDENTIAL_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_RESIDENTIAL_ALIAS = "AAAAAAAAAA";
    private static final String UPDATED_RESIDENTIAL_ALIAS = "BBBBBBBBBB";

    private static final String DEFAULT_RESIDENTIAL_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_RESIDENTIAL_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_RESIDENTIAL_DETAIL = "AAAAAAAAAA";
    private static final String UPDATED_RESIDENTIAL_DETAIL = "BBBBBBBBBB";

    private static final String DEFAULT_RESIDENTIAL_PROVINCE = "AAAAAAAAAA";
    private static final String UPDATED_RESIDENTIAL_PROVINCE = "BBBBBBBBBB";

    private static final String DEFAULT_RESIDENTIAL_DISTRICT = "AAAAAAAAAA";
    private static final String UPDATED_RESIDENTIAL_DISTRICT = "BBBBBBBBBB";

    private static final String DEFAULT_RESIDENTIAL_BOUNDARY = "AAAAAAAAAA";
    private static final String UPDATED_RESIDENTIAL_BOUNDARY = "BBBBBBBBBB";

    private static final String DEFAULT_RESIDENTIAL_AVATAR = "AAAAAAAAAA";
    private static final String UPDATED_RESIDENTIAL_AVATAR = "BBBBBBBBBB";

    @Autowired
    private ResidentialAreaRepository residentialAreaRepository;
    @Mock
    private ResidentialAreaRepository residentialAreaRepositoryMock;

    @Autowired
    private ResidentialAreaMapper residentialAreaMapper;

    @Mock
    private ResidentialAreaService residentialAreaServiceMock;

    @Autowired
    private ResidentialAreaService residentialAreaService;

    @Autowired
    private ResidentialAreaQueryService residentialAreaQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restResidentialAreaMockMvc;

    private ResidentialArea residentialArea;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ResidentialAreaResource residentialAreaResource = new ResidentialAreaResource(residentialAreaService, residentialAreaQueryService, fileStorageService);
        this.restResidentialAreaMockMvc = MockMvcBuilders.standaloneSetup(residentialAreaResource)
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
    public static ResidentialArea createEntity(EntityManager em) {
        ResidentialArea residentialArea = new ResidentialArea()
            .residentialName(DEFAULT_RESIDENTIAL_NAME)
            .residentialAlias(DEFAULT_RESIDENTIAL_ALIAS)
            .residentialDescription(DEFAULT_RESIDENTIAL_DESCRIPTION)
            .residentialDetail(DEFAULT_RESIDENTIAL_DETAIL)
            .residentialProvince(DEFAULT_RESIDENTIAL_PROVINCE)
            .residentialDistrict(DEFAULT_RESIDENTIAL_DISTRICT)
            .residentialBoundary(DEFAULT_RESIDENTIAL_BOUNDARY)
            .residentialAvatar(DEFAULT_RESIDENTIAL_AVATAR);
        return residentialArea;
    }

    @Before
    public void initTest() {
        residentialArea = createEntity(em);
    }

    @Test
    @Transactional
    public void createResidentialArea() throws Exception {
        int databaseSizeBeforeCreate = residentialAreaRepository.findAll().size();

        // Create the ResidentialArea
        ResidentialAreaDTO residentialAreaDTO = residentialAreaMapper.toDto(residentialArea);
        restResidentialAreaMockMvc.perform(post("/api/residential-areas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(residentialAreaDTO)))
            .andExpect(status().isCreated());

        // Validate the ResidentialArea in the database
        List<ResidentialArea> residentialAreaList = residentialAreaRepository.findAll();
        assertThat(residentialAreaList).hasSize(databaseSizeBeforeCreate + 1);
        ResidentialArea testResidentialArea = residentialAreaList.get(residentialAreaList.size() - 1);
        assertThat(testResidentialArea.getResidentialName()).isEqualTo(DEFAULT_RESIDENTIAL_NAME);
        assertThat(testResidentialArea.getResidentialAlias()).isEqualTo(DEFAULT_RESIDENTIAL_ALIAS);
        assertThat(testResidentialArea.getResidentialDescription()).isEqualTo(DEFAULT_RESIDENTIAL_DESCRIPTION);
        assertThat(testResidentialArea.getResidentialDetail()).isEqualTo(DEFAULT_RESIDENTIAL_DETAIL);
        assertThat(testResidentialArea.getResidentialProvince()).isEqualTo(DEFAULT_RESIDENTIAL_PROVINCE);
        assertThat(testResidentialArea.getResidentialDistrict()).isEqualTo(DEFAULT_RESIDENTIAL_DISTRICT);
        assertThat(testResidentialArea.getResidentialBoundary()).isEqualTo(DEFAULT_RESIDENTIAL_BOUNDARY);
        assertThat(testResidentialArea.getResidentialAvatar()).isEqualTo(DEFAULT_RESIDENTIAL_AVATAR);
    }

    @Test
    @Transactional
    public void createResidentialAreaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = residentialAreaRepository.findAll().size();

        // Create the ResidentialArea with an existing ID
        residentialArea.setId(1L);
        ResidentialAreaDTO residentialAreaDTO = residentialAreaMapper.toDto(residentialArea);

        // An entity with an existing ID cannot be created, so this API call must fail
        restResidentialAreaMockMvc.perform(post("/api/residential-areas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(residentialAreaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ResidentialArea in the database
        List<ResidentialArea> residentialAreaList = residentialAreaRepository.findAll();
        assertThat(residentialAreaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkResidentialNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = residentialAreaRepository.findAll().size();
        // set the field null
        residentialArea.setResidentialName(null);

        // Create the ResidentialArea, which fails.
        ResidentialAreaDTO residentialAreaDTO = residentialAreaMapper.toDto(residentialArea);

        restResidentialAreaMockMvc.perform(post("/api/residential-areas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(residentialAreaDTO)))
            .andExpect(status().isBadRequest());

        List<ResidentialArea> residentialAreaList = residentialAreaRepository.findAll();
        assertThat(residentialAreaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkResidentialAliasIsRequired() throws Exception {
        int databaseSizeBeforeTest = residentialAreaRepository.findAll().size();
        // set the field null
        residentialArea.setResidentialAlias(null);

        // Create the ResidentialArea, which fails.
        ResidentialAreaDTO residentialAreaDTO = residentialAreaMapper.toDto(residentialArea);

        restResidentialAreaMockMvc.perform(post("/api/residential-areas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(residentialAreaDTO)))
            .andExpect(status().isBadRequest());

        List<ResidentialArea> residentialAreaList = residentialAreaRepository.findAll();
        assertThat(residentialAreaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllResidentialAreas() throws Exception {
        // Initialize the database
        residentialAreaRepository.saveAndFlush(residentialArea);

        // Get all the residentialAreaList
        restResidentialAreaMockMvc.perform(get("/api/residential-areas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(residentialArea.getId().intValue())))
            .andExpect(jsonPath("$.[*].residentialName").value(hasItem(DEFAULT_RESIDENTIAL_NAME.toString())))
            .andExpect(jsonPath("$.[*].residentialAlias").value(hasItem(DEFAULT_RESIDENTIAL_ALIAS.toString())))
            .andExpect(jsonPath("$.[*].residentialDescription").value(hasItem(DEFAULT_RESIDENTIAL_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].residentialDetail").value(hasItem(DEFAULT_RESIDENTIAL_DETAIL.toString())))
            .andExpect(jsonPath("$.[*].residentialProvince").value(hasItem(DEFAULT_RESIDENTIAL_PROVINCE.toString())))
            .andExpect(jsonPath("$.[*].residentialDistrict").value(hasItem(DEFAULT_RESIDENTIAL_DISTRICT.toString())))
            .andExpect(jsonPath("$.[*].residentialBoundary").value(hasItem(DEFAULT_RESIDENTIAL_BOUNDARY.toString())))
            .andExpect(jsonPath("$.[*].residentialAvatar").value(hasItem(DEFAULT_RESIDENTIAL_AVATAR.toString())));
    }

    public void getAllResidentialAreasWithEagerRelationshipsIsEnabled() throws Exception {
        ResidentialAreaResource residentialAreaResource = new ResidentialAreaResource(residentialAreaServiceMock, residentialAreaQueryService, fileStorageService);
        when(residentialAreaServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restResidentialAreaMockMvc = MockMvcBuilders.standaloneSetup(residentialAreaResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restResidentialAreaMockMvc.perform(get("/api/residential-areas?eagerload=true"))
        .andExpect(status().isOk());

        verify(residentialAreaServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    public void getAllResidentialAreasWithEagerRelationshipsIsNotEnabled() throws Exception {
        ResidentialAreaResource residentialAreaResource = new ResidentialAreaResource(residentialAreaServiceMock, residentialAreaQueryService, fileStorageService);
            when(residentialAreaServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restResidentialAreaMockMvc = MockMvcBuilders.standaloneSetup(residentialAreaResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restResidentialAreaMockMvc.perform(get("/api/residential-areas?eagerload=true"))
        .andExpect(status().isOk());

            verify(residentialAreaServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getResidentialArea() throws Exception {
        // Initialize the database
        residentialAreaRepository.saveAndFlush(residentialArea);

        // Get the residentialArea
        restResidentialAreaMockMvc.perform(get("/api/residential-areas/{id}", residentialArea.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(residentialArea.getId().intValue()))
            .andExpect(jsonPath("$.residentialName").value(DEFAULT_RESIDENTIAL_NAME.toString()))
            .andExpect(jsonPath("$.residentialAlias").value(DEFAULT_RESIDENTIAL_ALIAS.toString()))
            .andExpect(jsonPath("$.residentialDescription").value(DEFAULT_RESIDENTIAL_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.residentialDetail").value(DEFAULT_RESIDENTIAL_DETAIL.toString()))
            .andExpect(jsonPath("$.residentialProvince").value(DEFAULT_RESIDENTIAL_PROVINCE.toString()))
            .andExpect(jsonPath("$.residentialDistrict").value(DEFAULT_RESIDENTIAL_DISTRICT.toString()))
            .andExpect(jsonPath("$.residentialBoundary").value(DEFAULT_RESIDENTIAL_BOUNDARY.toString()))
            .andExpect(jsonPath("$.residentialAvatar").value(DEFAULT_RESIDENTIAL_AVATAR.toString()));
    }

    @Test
    @Transactional
    public void getAllResidentialAreasByResidentialNameIsEqualToSomething() throws Exception {
        // Initialize the database
        residentialAreaRepository.saveAndFlush(residentialArea);

        // Get all the residentialAreaList where residentialName equals to DEFAULT_RESIDENTIAL_NAME
        defaultResidentialAreaShouldBeFound("residentialName.equals=" + DEFAULT_RESIDENTIAL_NAME);

        // Get all the residentialAreaList where residentialName equals to UPDATED_RESIDENTIAL_NAME
        defaultResidentialAreaShouldNotBeFound("residentialName.equals=" + UPDATED_RESIDENTIAL_NAME);
    }

    @Test
    @Transactional
    public void getAllResidentialAreasByResidentialNameIsInShouldWork() throws Exception {
        // Initialize the database
        residentialAreaRepository.saveAndFlush(residentialArea);

        // Get all the residentialAreaList where residentialName in DEFAULT_RESIDENTIAL_NAME or UPDATED_RESIDENTIAL_NAME
        defaultResidentialAreaShouldBeFound("residentialName.in=" + DEFAULT_RESIDENTIAL_NAME + "," + UPDATED_RESIDENTIAL_NAME);

        // Get all the residentialAreaList where residentialName equals to UPDATED_RESIDENTIAL_NAME
        defaultResidentialAreaShouldNotBeFound("residentialName.in=" + UPDATED_RESIDENTIAL_NAME);
    }

    @Test
    @Transactional
    public void getAllResidentialAreasByResidentialNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        residentialAreaRepository.saveAndFlush(residentialArea);

        // Get all the residentialAreaList where residentialName is not null
        defaultResidentialAreaShouldBeFound("residentialName.specified=true");

        // Get all the residentialAreaList where residentialName is null
        defaultResidentialAreaShouldNotBeFound("residentialName.specified=false");
    }

    @Test
    @Transactional
    public void getAllResidentialAreasByResidentialAliasIsEqualToSomething() throws Exception {
        // Initialize the database
        residentialAreaRepository.saveAndFlush(residentialArea);

        // Get all the residentialAreaList where residentialAlias equals to DEFAULT_RESIDENTIAL_ALIAS
        defaultResidentialAreaShouldBeFound("residentialAlias.equals=" + DEFAULT_RESIDENTIAL_ALIAS);

        // Get all the residentialAreaList where residentialAlias equals to UPDATED_RESIDENTIAL_ALIAS
        defaultResidentialAreaShouldNotBeFound("residentialAlias.equals=" + UPDATED_RESIDENTIAL_ALIAS);
    }

    @Test
    @Transactional
    public void getAllResidentialAreasByResidentialAliasIsInShouldWork() throws Exception {
        // Initialize the database
        residentialAreaRepository.saveAndFlush(residentialArea);

        // Get all the residentialAreaList where residentialAlias in DEFAULT_RESIDENTIAL_ALIAS or UPDATED_RESIDENTIAL_ALIAS
        defaultResidentialAreaShouldBeFound("residentialAlias.in=" + DEFAULT_RESIDENTIAL_ALIAS + "," + UPDATED_RESIDENTIAL_ALIAS);

        // Get all the residentialAreaList where residentialAlias equals to UPDATED_RESIDENTIAL_ALIAS
        defaultResidentialAreaShouldNotBeFound("residentialAlias.in=" + UPDATED_RESIDENTIAL_ALIAS);
    }

    @Test
    @Transactional
    public void getAllResidentialAreasByResidentialAliasIsNullOrNotNull() throws Exception {
        // Initialize the database
        residentialAreaRepository.saveAndFlush(residentialArea);

        // Get all the residentialAreaList where residentialAlias is not null
        defaultResidentialAreaShouldBeFound("residentialAlias.specified=true");

        // Get all the residentialAreaList where residentialAlias is null
        defaultResidentialAreaShouldNotBeFound("residentialAlias.specified=false");
    }

    @Test
    @Transactional
    public void getAllResidentialAreasByResidentialProvinceIsEqualToSomething() throws Exception {
        // Initialize the database
        residentialAreaRepository.saveAndFlush(residentialArea);

        // Get all the residentialAreaList where residentialProvince equals to DEFAULT_RESIDENTIAL_PROVINCE
        defaultResidentialAreaShouldBeFound("residentialProvince.equals=" + DEFAULT_RESIDENTIAL_PROVINCE);

        // Get all the residentialAreaList where residentialProvince equals to UPDATED_RESIDENTIAL_PROVINCE
        defaultResidentialAreaShouldNotBeFound("residentialProvince.equals=" + UPDATED_RESIDENTIAL_PROVINCE);
    }

    @Test
    @Transactional
    public void getAllResidentialAreasByResidentialProvinceIsInShouldWork() throws Exception {
        // Initialize the database
        residentialAreaRepository.saveAndFlush(residentialArea);

        // Get all the residentialAreaList where residentialProvince in DEFAULT_RESIDENTIAL_PROVINCE or UPDATED_RESIDENTIAL_PROVINCE
        defaultResidentialAreaShouldBeFound("residentialProvince.in=" + DEFAULT_RESIDENTIAL_PROVINCE + "," + UPDATED_RESIDENTIAL_PROVINCE);

        // Get all the residentialAreaList where residentialProvince equals to UPDATED_RESIDENTIAL_PROVINCE
        defaultResidentialAreaShouldNotBeFound("residentialProvince.in=" + UPDATED_RESIDENTIAL_PROVINCE);
    }

    @Test
    @Transactional
    public void getAllResidentialAreasByResidentialProvinceIsNullOrNotNull() throws Exception {
        // Initialize the database
        residentialAreaRepository.saveAndFlush(residentialArea);

        // Get all the residentialAreaList where residentialProvince is not null
        defaultResidentialAreaShouldBeFound("residentialProvince.specified=true");

        // Get all the residentialAreaList where residentialProvince is null
        defaultResidentialAreaShouldNotBeFound("residentialProvince.specified=false");
    }

    @Test
    @Transactional
    public void getAllResidentialAreasByResidentialDistrictIsEqualToSomething() throws Exception {
        // Initialize the database
        residentialAreaRepository.saveAndFlush(residentialArea);

        // Get all the residentialAreaList where residentialDistrict equals to DEFAULT_RESIDENTIAL_DISTRICT
        defaultResidentialAreaShouldBeFound("residentialDistrict.equals=" + DEFAULT_RESIDENTIAL_DISTRICT);

        // Get all the residentialAreaList where residentialDistrict equals to UPDATED_RESIDENTIAL_DISTRICT
        defaultResidentialAreaShouldNotBeFound("residentialDistrict.equals=" + UPDATED_RESIDENTIAL_DISTRICT);
    }

    @Test
    @Transactional
    public void getAllResidentialAreasByResidentialDistrictIsInShouldWork() throws Exception {
        // Initialize the database
        residentialAreaRepository.saveAndFlush(residentialArea);

        // Get all the residentialAreaList where residentialDistrict in DEFAULT_RESIDENTIAL_DISTRICT or UPDATED_RESIDENTIAL_DISTRICT
        defaultResidentialAreaShouldBeFound("residentialDistrict.in=" + DEFAULT_RESIDENTIAL_DISTRICT + "," + UPDATED_RESIDENTIAL_DISTRICT);

        // Get all the residentialAreaList where residentialDistrict equals to UPDATED_RESIDENTIAL_DISTRICT
        defaultResidentialAreaShouldNotBeFound("residentialDistrict.in=" + UPDATED_RESIDENTIAL_DISTRICT);
    }

    @Test
    @Transactional
    public void getAllResidentialAreasByResidentialDistrictIsNullOrNotNull() throws Exception {
        // Initialize the database
        residentialAreaRepository.saveAndFlush(residentialArea);

        // Get all the residentialAreaList where residentialDistrict is not null
        defaultResidentialAreaShouldBeFound("residentialDistrict.specified=true");

        // Get all the residentialAreaList where residentialDistrict is null
        defaultResidentialAreaShouldNotBeFound("residentialDistrict.specified=false");
    }

    @Test
    @Transactional
    public void getAllResidentialAreasByResidentialAvatarIsEqualToSomething() throws Exception {
        // Initialize the database
        residentialAreaRepository.saveAndFlush(residentialArea);

        // Get all the residentialAreaList where residentialAvatar equals to DEFAULT_RESIDENTIAL_AVATAR
        defaultResidentialAreaShouldBeFound("residentialAvatar.equals=" + DEFAULT_RESIDENTIAL_AVATAR);

        // Get all the residentialAreaList where residentialAvatar equals to UPDATED_RESIDENTIAL_AVATAR
        defaultResidentialAreaShouldNotBeFound("residentialAvatar.equals=" + UPDATED_RESIDENTIAL_AVATAR);
    }

    @Test
    @Transactional
    public void getAllResidentialAreasByResidentialAvatarIsInShouldWork() throws Exception {
        // Initialize the database
        residentialAreaRepository.saveAndFlush(residentialArea);

        // Get all the residentialAreaList where residentialAvatar in DEFAULT_RESIDENTIAL_AVATAR or UPDATED_RESIDENTIAL_AVATAR
        defaultResidentialAreaShouldBeFound("residentialAvatar.in=" + DEFAULT_RESIDENTIAL_AVATAR + "," + UPDATED_RESIDENTIAL_AVATAR);

        // Get all the residentialAreaList where residentialAvatar equals to UPDATED_RESIDENTIAL_AVATAR
        defaultResidentialAreaShouldNotBeFound("residentialAvatar.in=" + UPDATED_RESIDENTIAL_AVATAR);
    }

    @Test
    @Transactional
    public void getAllResidentialAreasByResidentialAvatarIsNullOrNotNull() throws Exception {
        // Initialize the database
        residentialAreaRepository.saveAndFlush(residentialArea);

        // Get all the residentialAreaList where residentialAvatar is not null
        defaultResidentialAreaShouldBeFound("residentialAvatar.specified=true");

        // Get all the residentialAreaList where residentialAvatar is null
        defaultResidentialAreaShouldNotBeFound("residentialAvatar.specified=false");
    }

    @Test
    @Transactional
    public void getAllResidentialAreasByPhotoIsEqualToSomething() throws Exception {
        // Initialize the database
        Photo photo = PhotoResourceIntTest.createEntity(em);
        em.persist(photo);
        em.flush();
        residentialArea.setPhoto(photo);
        residentialAreaRepository.saveAndFlush(residentialArea);
        Long photoId = photo.getId();

        // Get all the residentialAreaList where photo equals to photoId
        defaultResidentialAreaShouldBeFound("photoId.equals=" + photoId);

        // Get all the residentialAreaList where photo equals to photoId + 1
        defaultResidentialAreaShouldNotBeFound("photoId.equals=" + (photoId + 1));
    }


    @Test
    @Transactional
    public void getAllResidentialAreasByTagIsEqualToSomething() throws Exception {
        // Initialize the database
        Tag tag = TagResourceIntTest.createEntity(em);
        em.persist(tag);
        em.flush();
        residentialArea.addTag(tag);
        residentialAreaRepository.saveAndFlush(residentialArea);
        Long tagId = tag.getId();

        // Get all the residentialAreaList where tag equals to tagId
        defaultResidentialAreaShouldBeFound("tagId.equals=" + tagId);

        // Get all the residentialAreaList where tag equals to tagId + 1
        defaultResidentialAreaShouldNotBeFound("tagId.equals=" + (tagId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultResidentialAreaShouldBeFound(String filter) throws Exception {
        restResidentialAreaMockMvc.perform(get("/api/residential-areas?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(residentialArea.getId().intValue())))
            .andExpect(jsonPath("$.[*].residentialName").value(hasItem(DEFAULT_RESIDENTIAL_NAME.toString())))
            .andExpect(jsonPath("$.[*].residentialAlias").value(hasItem(DEFAULT_RESIDENTIAL_ALIAS.toString())))
            .andExpect(jsonPath("$.[*].residentialDescription").value(hasItem(DEFAULT_RESIDENTIAL_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].residentialDetail").value(hasItem(DEFAULT_RESIDENTIAL_DETAIL.toString())))
            .andExpect(jsonPath("$.[*].residentialProvince").value(hasItem(DEFAULT_RESIDENTIAL_PROVINCE.toString())))
            .andExpect(jsonPath("$.[*].residentialDistrict").value(hasItem(DEFAULT_RESIDENTIAL_DISTRICT.toString())))
            .andExpect(jsonPath("$.[*].residentialBoundary").value(hasItem(DEFAULT_RESIDENTIAL_BOUNDARY.toString())))
            .andExpect(jsonPath("$.[*].residentialAvatar").value(hasItem(DEFAULT_RESIDENTIAL_AVATAR.toString())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultResidentialAreaShouldNotBeFound(String filter) throws Exception {
        restResidentialAreaMockMvc.perform(get("/api/residential-areas?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @Transactional
    public void getNonExistingResidentialArea() throws Exception {
        // Get the residentialArea
        restResidentialAreaMockMvc.perform(get("/api/residential-areas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateResidentialArea() throws Exception {
        // Initialize the database
        residentialAreaRepository.saveAndFlush(residentialArea);

        int databaseSizeBeforeUpdate = residentialAreaRepository.findAll().size();

        // Update the residentialArea
        ResidentialArea updatedResidentialArea = residentialAreaRepository.findById(residentialArea.getId()).get();
        // Disconnect from session so that the updates on updatedResidentialArea are not directly saved in db
        em.detach(updatedResidentialArea);
        updatedResidentialArea
            .residentialName(UPDATED_RESIDENTIAL_NAME)
            .residentialAlias(UPDATED_RESIDENTIAL_ALIAS)
            .residentialDescription(UPDATED_RESIDENTIAL_DESCRIPTION)
            .residentialDetail(UPDATED_RESIDENTIAL_DETAIL)
            .residentialProvince(UPDATED_RESIDENTIAL_PROVINCE)
            .residentialDistrict(UPDATED_RESIDENTIAL_DISTRICT)
            .residentialBoundary(UPDATED_RESIDENTIAL_BOUNDARY)
            .residentialAvatar(UPDATED_RESIDENTIAL_AVATAR);
        ResidentialAreaDTO residentialAreaDTO = residentialAreaMapper.toDto(updatedResidentialArea);

        restResidentialAreaMockMvc.perform(put("/api/residential-areas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(residentialAreaDTO)))
            .andExpect(status().isOk());

        // Validate the ResidentialArea in the database
        List<ResidentialArea> residentialAreaList = residentialAreaRepository.findAll();
        assertThat(residentialAreaList).hasSize(databaseSizeBeforeUpdate);
        ResidentialArea testResidentialArea = residentialAreaList.get(residentialAreaList.size() - 1);
        assertThat(testResidentialArea.getResidentialName()).isEqualTo(UPDATED_RESIDENTIAL_NAME);
        assertThat(testResidentialArea.getResidentialAlias()).isEqualTo(UPDATED_RESIDENTIAL_ALIAS);
        assertThat(testResidentialArea.getResidentialDescription()).isEqualTo(UPDATED_RESIDENTIAL_DESCRIPTION);
        assertThat(testResidentialArea.getResidentialDetail()).isEqualTo(UPDATED_RESIDENTIAL_DETAIL);
        assertThat(testResidentialArea.getResidentialProvince()).isEqualTo(UPDATED_RESIDENTIAL_PROVINCE);
        assertThat(testResidentialArea.getResidentialDistrict()).isEqualTo(UPDATED_RESIDENTIAL_DISTRICT);
        assertThat(testResidentialArea.getResidentialBoundary()).isEqualTo(UPDATED_RESIDENTIAL_BOUNDARY);
        assertThat(testResidentialArea.getResidentialAvatar()).isEqualTo(UPDATED_RESIDENTIAL_AVATAR);
    }

    @Test
    @Transactional
    public void updateNonExistingResidentialArea() throws Exception {
        int databaseSizeBeforeUpdate = residentialAreaRepository.findAll().size();

        // Create the ResidentialArea
        ResidentialAreaDTO residentialAreaDTO = residentialAreaMapper.toDto(residentialArea);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restResidentialAreaMockMvc.perform(put("/api/residential-areas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(residentialAreaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ResidentialArea in the database
        List<ResidentialArea> residentialAreaList = residentialAreaRepository.findAll();
        assertThat(residentialAreaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteResidentialArea() throws Exception {
        // Initialize the database
        residentialAreaRepository.saveAndFlush(residentialArea);

        int databaseSizeBeforeDelete = residentialAreaRepository.findAll().size();

        // Get the residentialArea
        restResidentialAreaMockMvc.perform(delete("/api/residential-areas/{id}", residentialArea.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ResidentialArea> residentialAreaList = residentialAreaRepository.findAll();
        assertThat(residentialAreaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ResidentialArea.class);
        ResidentialArea residentialArea1 = new ResidentialArea();
        residentialArea1.setId(1L);
        ResidentialArea residentialArea2 = new ResidentialArea();
        residentialArea2.setId(residentialArea1.getId());
        assertThat(residentialArea1).isEqualTo(residentialArea2);
        residentialArea2.setId(2L);
        assertThat(residentialArea1).isNotEqualTo(residentialArea2);
        residentialArea1.setId(null);
        assertThat(residentialArea1).isNotEqualTo(residentialArea2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ResidentialAreaDTO.class);
        ResidentialAreaDTO residentialAreaDTO1 = new ResidentialAreaDTO();
        residentialAreaDTO1.setId(1L);
        ResidentialAreaDTO residentialAreaDTO2 = new ResidentialAreaDTO();
        assertThat(residentialAreaDTO1).isNotEqualTo(residentialAreaDTO2);
        residentialAreaDTO2.setId(residentialAreaDTO1.getId());
        assertThat(residentialAreaDTO1).isEqualTo(residentialAreaDTO2);
        residentialAreaDTO2.setId(2L);
        assertThat(residentialAreaDTO1).isNotEqualTo(residentialAreaDTO2);
        residentialAreaDTO1.setId(null);
        assertThat(residentialAreaDTO1).isNotEqualTo(residentialAreaDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(residentialAreaMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(residentialAreaMapper.fromId(null)).isNull();
    }
}
