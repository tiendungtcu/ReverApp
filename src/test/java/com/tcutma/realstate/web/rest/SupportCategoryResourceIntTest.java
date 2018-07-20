package com.tcutma.realstate.web.rest;

import com.tcutma.realstate.RiverApp;

import com.tcutma.realstate.domain.SupportCategory;
import com.tcutma.realstate.repository.SupportCategoryRepository;
import com.tcutma.realstate.service.SupportCategoryService;
import com.tcutma.realstate.service.dto.SupportCategoryDTO;
import com.tcutma.realstate.service.mapper.SupportCategoryMapper;
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

/**
 * Test class for the SupportCategoryResource REST controller.
 *
 * @see SupportCategoryResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RiverApp.class)
public class SupportCategoryResourceIntTest {

    private static final String DEFAULT_CATEGORY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CATEGORY_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORY_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private SupportCategoryRepository supportCategoryRepository;


    @Autowired
    private SupportCategoryMapper supportCategoryMapper;
    

    @Autowired
    private SupportCategoryService supportCategoryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSupportCategoryMockMvc;

    private SupportCategory supportCategory;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SupportCategoryResource supportCategoryResource = new SupportCategoryResource(supportCategoryService);
        this.restSupportCategoryMockMvc = MockMvcBuilders.standaloneSetup(supportCategoryResource)
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
    public static SupportCategory createEntity(EntityManager em) {
        SupportCategory supportCategory = new SupportCategory()
            .categoryName(DEFAULT_CATEGORY_NAME)
            .categoryDescription(DEFAULT_CATEGORY_DESCRIPTION);
        return supportCategory;
    }

    @Before
    public void initTest() {
        supportCategory = createEntity(em);
    }

    @Test
    @Transactional
    public void createSupportCategory() throws Exception {
        int databaseSizeBeforeCreate = supportCategoryRepository.findAll().size();

        // Create the SupportCategory
        SupportCategoryDTO supportCategoryDTO = supportCategoryMapper.toDto(supportCategory);
        restSupportCategoryMockMvc.perform(post("/api/support-categories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(supportCategoryDTO)))
            .andExpect(status().isCreated());

        // Validate the SupportCategory in the database
        List<SupportCategory> supportCategoryList = supportCategoryRepository.findAll();
        assertThat(supportCategoryList).hasSize(databaseSizeBeforeCreate + 1);
        SupportCategory testSupportCategory = supportCategoryList.get(supportCategoryList.size() - 1);
        assertThat(testSupportCategory.getCategoryName()).isEqualTo(DEFAULT_CATEGORY_NAME);
        assertThat(testSupportCategory.getCategoryDescription()).isEqualTo(DEFAULT_CATEGORY_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createSupportCategoryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = supportCategoryRepository.findAll().size();

        // Create the SupportCategory with an existing ID
        supportCategory.setId(1L);
        SupportCategoryDTO supportCategoryDTO = supportCategoryMapper.toDto(supportCategory);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSupportCategoryMockMvc.perform(post("/api/support-categories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(supportCategoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SupportCategory in the database
        List<SupportCategory> supportCategoryList = supportCategoryRepository.findAll();
        assertThat(supportCategoryList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCategoryNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = supportCategoryRepository.findAll().size();
        // set the field null
        supportCategory.setCategoryName(null);

        // Create the SupportCategory, which fails.
        SupportCategoryDTO supportCategoryDTO = supportCategoryMapper.toDto(supportCategory);

        restSupportCategoryMockMvc.perform(post("/api/support-categories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(supportCategoryDTO)))
            .andExpect(status().isBadRequest());

        List<SupportCategory> supportCategoryList = supportCategoryRepository.findAll();
        assertThat(supportCategoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSupportCategories() throws Exception {
        // Initialize the database
        supportCategoryRepository.saveAndFlush(supportCategory);

        // Get all the supportCategoryList
        restSupportCategoryMockMvc.perform(get("/api/support-categories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(supportCategory.getId().intValue())))
            .andExpect(jsonPath("$.[*].categoryName").value(hasItem(DEFAULT_CATEGORY_NAME.toString())))
            .andExpect(jsonPath("$.[*].categoryDescription").value(hasItem(DEFAULT_CATEGORY_DESCRIPTION.toString())));
    }
    

    @Test
    @Transactional
    public void getSupportCategory() throws Exception {
        // Initialize the database
        supportCategoryRepository.saveAndFlush(supportCategory);

        // Get the supportCategory
        restSupportCategoryMockMvc.perform(get("/api/support-categories/{id}", supportCategory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(supportCategory.getId().intValue()))
            .andExpect(jsonPath("$.categoryName").value(DEFAULT_CATEGORY_NAME.toString()))
            .andExpect(jsonPath("$.categoryDescription").value(DEFAULT_CATEGORY_DESCRIPTION.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingSupportCategory() throws Exception {
        // Get the supportCategory
        restSupportCategoryMockMvc.perform(get("/api/support-categories/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSupportCategory() throws Exception {
        // Initialize the database
        supportCategoryRepository.saveAndFlush(supportCategory);

        int databaseSizeBeforeUpdate = supportCategoryRepository.findAll().size();

        // Update the supportCategory
        SupportCategory updatedSupportCategory = supportCategoryRepository.findById(supportCategory.getId()).get();
        // Disconnect from session so that the updates on updatedSupportCategory are not directly saved in db
        em.detach(updatedSupportCategory);
        updatedSupportCategory
            .categoryName(UPDATED_CATEGORY_NAME)
            .categoryDescription(UPDATED_CATEGORY_DESCRIPTION);
        SupportCategoryDTO supportCategoryDTO = supportCategoryMapper.toDto(updatedSupportCategory);

        restSupportCategoryMockMvc.perform(put("/api/support-categories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(supportCategoryDTO)))
            .andExpect(status().isOk());

        // Validate the SupportCategory in the database
        List<SupportCategory> supportCategoryList = supportCategoryRepository.findAll();
        assertThat(supportCategoryList).hasSize(databaseSizeBeforeUpdate);
        SupportCategory testSupportCategory = supportCategoryList.get(supportCategoryList.size() - 1);
        assertThat(testSupportCategory.getCategoryName()).isEqualTo(UPDATED_CATEGORY_NAME);
        assertThat(testSupportCategory.getCategoryDescription()).isEqualTo(UPDATED_CATEGORY_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingSupportCategory() throws Exception {
        int databaseSizeBeforeUpdate = supportCategoryRepository.findAll().size();

        // Create the SupportCategory
        SupportCategoryDTO supportCategoryDTO = supportCategoryMapper.toDto(supportCategory);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restSupportCategoryMockMvc.perform(put("/api/support-categories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(supportCategoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SupportCategory in the database
        List<SupportCategory> supportCategoryList = supportCategoryRepository.findAll();
        assertThat(supportCategoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSupportCategory() throws Exception {
        // Initialize the database
        supportCategoryRepository.saveAndFlush(supportCategory);

        int databaseSizeBeforeDelete = supportCategoryRepository.findAll().size();

        // Get the supportCategory
        restSupportCategoryMockMvc.perform(delete("/api/support-categories/{id}", supportCategory.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SupportCategory> supportCategoryList = supportCategoryRepository.findAll();
        assertThat(supportCategoryList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SupportCategory.class);
        SupportCategory supportCategory1 = new SupportCategory();
        supportCategory1.setId(1L);
        SupportCategory supportCategory2 = new SupportCategory();
        supportCategory2.setId(supportCategory1.getId());
        assertThat(supportCategory1).isEqualTo(supportCategory2);
        supportCategory2.setId(2L);
        assertThat(supportCategory1).isNotEqualTo(supportCategory2);
        supportCategory1.setId(null);
        assertThat(supportCategory1).isNotEqualTo(supportCategory2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SupportCategoryDTO.class);
        SupportCategoryDTO supportCategoryDTO1 = new SupportCategoryDTO();
        supportCategoryDTO1.setId(1L);
        SupportCategoryDTO supportCategoryDTO2 = new SupportCategoryDTO();
        assertThat(supportCategoryDTO1).isNotEqualTo(supportCategoryDTO2);
        supportCategoryDTO2.setId(supportCategoryDTO1.getId());
        assertThat(supportCategoryDTO1).isEqualTo(supportCategoryDTO2);
        supportCategoryDTO2.setId(2L);
        assertThat(supportCategoryDTO1).isNotEqualTo(supportCategoryDTO2);
        supportCategoryDTO1.setId(null);
        assertThat(supportCategoryDTO1).isNotEqualTo(supportCategoryDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(supportCategoryMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(supportCategoryMapper.fromId(null)).isNull();
    }
}
