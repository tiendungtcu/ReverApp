package com.tcutma.realstate.web.rest;

import com.tcutma.realstate.RiverApp;

import com.tcutma.realstate.domain.BuildingType;
import com.tcutma.realstate.repository.BuildingTypeRepository;
import com.tcutma.realstate.service.dto.BuildingTypeDTO;
import com.tcutma.realstate.service.mapper.BuildingTypeMapper;
import com.tcutma.realstate.web.rest.errors.ExceptionTranslator;

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

import com.tcutma.realstate.domain.enumeration.PropertyType;
/**
 * Test class for the BuildingTypeResource REST controller.
 *
 * @see BuildingTypeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RiverApp.class)
public class BuildingTypeResourceIntTest {

    private static final String DEFAULT_TYPE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_NAME = "BBBBBBBBBB";

    private static final PropertyType DEFAULT_TYPE_SELECT = PropertyType.PROJECT;
    private static final PropertyType UPDATED_TYPE_SELECT = PropertyType.PROPERTY;

    @Autowired
    private BuildingTypeRepository buildingTypeRepository;


    @Autowired
    private BuildingTypeMapper buildingTypeMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restBuildingTypeMockMvc;

    private BuildingType buildingType;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BuildingTypeResource buildingTypeResource = new BuildingTypeResource(buildingTypeRepository, buildingTypeMapper);
        this.restBuildingTypeMockMvc = MockMvcBuilders.standaloneSetup(buildingTypeResource)
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
    public static BuildingType createEntity(EntityManager em) {
        BuildingType buildingType = new BuildingType()
            .typeName(DEFAULT_TYPE_NAME)
            .typeSelect(DEFAULT_TYPE_SELECT);
        return buildingType;
    }

    @Before
    public void initTest() {
        buildingType = createEntity(em);
    }

    @Test
    @Transactional
    public void createBuildingType() throws Exception {
        int databaseSizeBeforeCreate = buildingTypeRepository.findAll().size();

        // Create the BuildingType
        BuildingTypeDTO buildingTypeDTO = buildingTypeMapper.toDto(buildingType);
        restBuildingTypeMockMvc.perform(post("/api/building-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(buildingTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the BuildingType in the database
        List<BuildingType> buildingTypeList = buildingTypeRepository.findAll();
        assertThat(buildingTypeList).hasSize(databaseSizeBeforeCreate + 1);
        BuildingType testBuildingType = buildingTypeList.get(buildingTypeList.size() - 1);
        assertThat(testBuildingType.getTypeName()).isEqualTo(DEFAULT_TYPE_NAME);
        assertThat(testBuildingType.getTypeSelect()).isEqualTo(DEFAULT_TYPE_SELECT);
    }

    @Test
    @Transactional
    public void createBuildingTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = buildingTypeRepository.findAll().size();

        // Create the BuildingType with an existing ID
        buildingType.setId(1L);
        BuildingTypeDTO buildingTypeDTO = buildingTypeMapper.toDto(buildingType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBuildingTypeMockMvc.perform(post("/api/building-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(buildingTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BuildingType in the database
        List<BuildingType> buildingTypeList = buildingTypeRepository.findAll();
        assertThat(buildingTypeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkTypeNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = buildingTypeRepository.findAll().size();
        // set the field null
        buildingType.setTypeName(null);

        // Create the BuildingType, which fails.
        BuildingTypeDTO buildingTypeDTO = buildingTypeMapper.toDto(buildingType);

        restBuildingTypeMockMvc.perform(post("/api/building-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(buildingTypeDTO)))
            .andExpect(status().isBadRequest());

        List<BuildingType> buildingTypeList = buildingTypeRepository.findAll();
        assertThat(buildingTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBuildingTypes() throws Exception {
        // Initialize the database
        buildingTypeRepository.saveAndFlush(buildingType);

        // Get all the buildingTypeList
        restBuildingTypeMockMvc.perform(get("/api/building-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(buildingType.getId().intValue())))
            .andExpect(jsonPath("$.[*].typeName").value(hasItem(DEFAULT_TYPE_NAME.toString())))
            .andExpect(jsonPath("$.[*].typeSelect").value(hasItem(DEFAULT_TYPE_SELECT.toString())));
    }
    

    @Test
    @Transactional
    public void getBuildingType() throws Exception {
        // Initialize the database
        buildingTypeRepository.saveAndFlush(buildingType);

        // Get the buildingType
        restBuildingTypeMockMvc.perform(get("/api/building-types/{id}", buildingType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(buildingType.getId().intValue()))
            .andExpect(jsonPath("$.typeName").value(DEFAULT_TYPE_NAME.toString()))
            .andExpect(jsonPath("$.typeSelect").value(DEFAULT_TYPE_SELECT.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingBuildingType() throws Exception {
        // Get the buildingType
        restBuildingTypeMockMvc.perform(get("/api/building-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBuildingType() throws Exception {
        // Initialize the database
        buildingTypeRepository.saveAndFlush(buildingType);

        int databaseSizeBeforeUpdate = buildingTypeRepository.findAll().size();

        // Update the buildingType
        BuildingType updatedBuildingType = buildingTypeRepository.findById(buildingType.getId()).get();
        // Disconnect from session so that the updates on updatedBuildingType are not directly saved in db
        em.detach(updatedBuildingType);
        updatedBuildingType
            .typeName(UPDATED_TYPE_NAME)
            .typeSelect(UPDATED_TYPE_SELECT);
        BuildingTypeDTO buildingTypeDTO = buildingTypeMapper.toDto(updatedBuildingType);

        restBuildingTypeMockMvc.perform(put("/api/building-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(buildingTypeDTO)))
            .andExpect(status().isOk());

        // Validate the BuildingType in the database
        List<BuildingType> buildingTypeList = buildingTypeRepository.findAll();
        assertThat(buildingTypeList).hasSize(databaseSizeBeforeUpdate);
        BuildingType testBuildingType = buildingTypeList.get(buildingTypeList.size() - 1);
        assertThat(testBuildingType.getTypeName()).isEqualTo(UPDATED_TYPE_NAME);
        assertThat(testBuildingType.getTypeSelect()).isEqualTo(UPDATED_TYPE_SELECT);
    }

    @Test
    @Transactional
    public void updateNonExistingBuildingType() throws Exception {
        int databaseSizeBeforeUpdate = buildingTypeRepository.findAll().size();

        // Create the BuildingType
        BuildingTypeDTO buildingTypeDTO = buildingTypeMapper.toDto(buildingType);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restBuildingTypeMockMvc.perform(put("/api/building-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(buildingTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BuildingType in the database
        List<BuildingType> buildingTypeList = buildingTypeRepository.findAll();
        assertThat(buildingTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBuildingType() throws Exception {
        // Initialize the database
        buildingTypeRepository.saveAndFlush(buildingType);

        int databaseSizeBeforeDelete = buildingTypeRepository.findAll().size();

        // Get the buildingType
        restBuildingTypeMockMvc.perform(delete("/api/building-types/{id}", buildingType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<BuildingType> buildingTypeList = buildingTypeRepository.findAll();
        assertThat(buildingTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BuildingType.class);
        BuildingType buildingType1 = new BuildingType();
        buildingType1.setId(1L);
        BuildingType buildingType2 = new BuildingType();
        buildingType2.setId(buildingType1.getId());
        assertThat(buildingType1).isEqualTo(buildingType2);
        buildingType2.setId(2L);
        assertThat(buildingType1).isNotEqualTo(buildingType2);
        buildingType1.setId(null);
        assertThat(buildingType1).isNotEqualTo(buildingType2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BuildingTypeDTO.class);
        BuildingTypeDTO buildingTypeDTO1 = new BuildingTypeDTO();
        buildingTypeDTO1.setId(1L);
        BuildingTypeDTO buildingTypeDTO2 = new BuildingTypeDTO();
        assertThat(buildingTypeDTO1).isNotEqualTo(buildingTypeDTO2);
        buildingTypeDTO2.setId(buildingTypeDTO1.getId());
        assertThat(buildingTypeDTO1).isEqualTo(buildingTypeDTO2);
        buildingTypeDTO2.setId(2L);
        assertThat(buildingTypeDTO1).isNotEqualTo(buildingTypeDTO2);
        buildingTypeDTO1.setId(null);
        assertThat(buildingTypeDTO1).isNotEqualTo(buildingTypeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(buildingTypeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(buildingTypeMapper.fromId(null)).isNull();
    }
}
