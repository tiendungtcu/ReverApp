package com.tcutma.realstate.web.rest;

import com.tcutma.realstate.RiverApp;

import com.tcutma.realstate.domain.ProjectBuilder;
import com.tcutma.realstate.repository.ProjectBuilderRepository;
import com.tcutma.realstate.service.ProjectBuilderService;
import com.tcutma.realstate.service.dto.ProjectBuilderDTO;
import com.tcutma.realstate.service.mapper.ProjectBuilderMapper;
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
import org.springframework.util.Base64Utils;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;


import static com.tcutma.realstate.web.rest.TestUtil.sameInstant;
import static com.tcutma.realstate.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ProjectBuilderResource REST controller.
 *
 * @see ProjectBuilderResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RiverApp.class)
public class ProjectBuilderResourceIntTest {

    private static final String DEFAULT_BUILDER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BUILDER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BUILDER_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_BUILDER_TITLE = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_BUILDER_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_BUILDER_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_BUILDER_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_BUILDER_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_BUILDER_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_BUILDER_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_BUILDER_WEBSITE = "AAAAAAAAAA";
    private static final String UPDATED_BUILDER_WEBSITE = "BBBBBBBBBB";

    private static final String DEFAULT_BUILDER_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_BUILDER_PHONE = "BBBBBBBBBB";

    private static final byte[] DEFAULT_BUILDER_PHOTO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_BUILDER_PHOTO = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_BUILDER_PHOTO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BUILDER_PHOTO_CONTENT_TYPE = "image/png";

    @Autowired
    private ProjectBuilderRepository projectBuilderRepository;


    @Autowired
    private ProjectBuilderMapper projectBuilderMapper;
    

    @Autowired
    private ProjectBuilderService projectBuilderService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restProjectBuilderMockMvc;

    private ProjectBuilder projectBuilder;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProjectBuilderResource projectBuilderResource = new ProjectBuilderResource(projectBuilderService);
        this.restProjectBuilderMockMvc = MockMvcBuilders.standaloneSetup(projectBuilderResource)
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
    public static ProjectBuilder createEntity(EntityManager em) {
        ProjectBuilder projectBuilder = new ProjectBuilder()
            .builderName(DEFAULT_BUILDER_NAME)
            .builderTitle(DEFAULT_BUILDER_TITLE)
            .builderDate(DEFAULT_BUILDER_DATE)
            .builderDescription(DEFAULT_BUILDER_DESCRIPTION)
            .builderAddress(DEFAULT_BUILDER_ADDRESS)
            .builderWebsite(DEFAULT_BUILDER_WEBSITE)
            .builderPhone(DEFAULT_BUILDER_PHONE)
            .builderPhoto(DEFAULT_BUILDER_PHOTO)
            .builderPhotoContentType(DEFAULT_BUILDER_PHOTO_CONTENT_TYPE);
        return projectBuilder;
    }

    @Before
    public void initTest() {
        projectBuilder = createEntity(em);
    }

    @Test
    @Transactional
    public void createProjectBuilder() throws Exception {
        int databaseSizeBeforeCreate = projectBuilderRepository.findAll().size();

        // Create the ProjectBuilder
        ProjectBuilderDTO projectBuilderDTO = projectBuilderMapper.toDto(projectBuilder);
        restProjectBuilderMockMvc.perform(post("/api/project-builders")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectBuilderDTO)))
            .andExpect(status().isCreated());

        // Validate the ProjectBuilder in the database
        List<ProjectBuilder> projectBuilderList = projectBuilderRepository.findAll();
        assertThat(projectBuilderList).hasSize(databaseSizeBeforeCreate + 1);
        ProjectBuilder testProjectBuilder = projectBuilderList.get(projectBuilderList.size() - 1);
        assertThat(testProjectBuilder.getBuilderName()).isEqualTo(DEFAULT_BUILDER_NAME);
        assertThat(testProjectBuilder.getBuilderTitle()).isEqualTo(DEFAULT_BUILDER_TITLE);
        assertThat(testProjectBuilder.getBuilderDate()).isEqualTo(DEFAULT_BUILDER_DATE);
        assertThat(testProjectBuilder.getBuilderDescription()).isEqualTo(DEFAULT_BUILDER_DESCRIPTION);
        assertThat(testProjectBuilder.getBuilderAddress()).isEqualTo(DEFAULT_BUILDER_ADDRESS);
        assertThat(testProjectBuilder.getBuilderWebsite()).isEqualTo(DEFAULT_BUILDER_WEBSITE);
        assertThat(testProjectBuilder.getBuilderPhone()).isEqualTo(DEFAULT_BUILDER_PHONE);
        assertThat(testProjectBuilder.getBuilderPhoto()).isEqualTo(DEFAULT_BUILDER_PHOTO);
        assertThat(testProjectBuilder.getBuilderPhotoContentType()).isEqualTo(DEFAULT_BUILDER_PHOTO_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createProjectBuilderWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = projectBuilderRepository.findAll().size();

        // Create the ProjectBuilder with an existing ID
        projectBuilder.setId(1L);
        ProjectBuilderDTO projectBuilderDTO = projectBuilderMapper.toDto(projectBuilder);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProjectBuilderMockMvc.perform(post("/api/project-builders")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectBuilderDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProjectBuilder in the database
        List<ProjectBuilder> projectBuilderList = projectBuilderRepository.findAll();
        assertThat(projectBuilderList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkBuilderNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = projectBuilderRepository.findAll().size();
        // set the field null
        projectBuilder.setBuilderName(null);

        // Create the ProjectBuilder, which fails.
        ProjectBuilderDTO projectBuilderDTO = projectBuilderMapper.toDto(projectBuilder);

        restProjectBuilderMockMvc.perform(post("/api/project-builders")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectBuilderDTO)))
            .andExpect(status().isBadRequest());

        List<ProjectBuilder> projectBuilderList = projectBuilderRepository.findAll();
        assertThat(projectBuilderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllProjectBuilders() throws Exception {
        // Initialize the database
        projectBuilderRepository.saveAndFlush(projectBuilder);

        // Get all the projectBuilderList
        restProjectBuilderMockMvc.perform(get("/api/project-builders?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(projectBuilder.getId().intValue())))
            .andExpect(jsonPath("$.[*].builderName").value(hasItem(DEFAULT_BUILDER_NAME.toString())))
            .andExpect(jsonPath("$.[*].builderTitle").value(hasItem(DEFAULT_BUILDER_TITLE.toString())))
            .andExpect(jsonPath("$.[*].builderDate").value(hasItem(sameInstant(DEFAULT_BUILDER_DATE))))
            .andExpect(jsonPath("$.[*].builderDescription").value(hasItem(DEFAULT_BUILDER_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].builderAddress").value(hasItem(DEFAULT_BUILDER_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].builderWebsite").value(hasItem(DEFAULT_BUILDER_WEBSITE.toString())))
            .andExpect(jsonPath("$.[*].builderPhone").value(hasItem(DEFAULT_BUILDER_PHONE.toString())))
            .andExpect(jsonPath("$.[*].builderPhotoContentType").value(hasItem(DEFAULT_BUILDER_PHOTO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].builderPhoto").value(hasItem(Base64Utils.encodeToString(DEFAULT_BUILDER_PHOTO))));
    }
    

    @Test
    @Transactional
    public void getProjectBuilder() throws Exception {
        // Initialize the database
        projectBuilderRepository.saveAndFlush(projectBuilder);

        // Get the projectBuilder
        restProjectBuilderMockMvc.perform(get("/api/project-builders/{id}", projectBuilder.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(projectBuilder.getId().intValue()))
            .andExpect(jsonPath("$.builderName").value(DEFAULT_BUILDER_NAME.toString()))
            .andExpect(jsonPath("$.builderTitle").value(DEFAULT_BUILDER_TITLE.toString()))
            .andExpect(jsonPath("$.builderDate").value(sameInstant(DEFAULT_BUILDER_DATE)))
            .andExpect(jsonPath("$.builderDescription").value(DEFAULT_BUILDER_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.builderAddress").value(DEFAULT_BUILDER_ADDRESS.toString()))
            .andExpect(jsonPath("$.builderWebsite").value(DEFAULT_BUILDER_WEBSITE.toString()))
            .andExpect(jsonPath("$.builderPhone").value(DEFAULT_BUILDER_PHONE.toString()))
            .andExpect(jsonPath("$.builderPhotoContentType").value(DEFAULT_BUILDER_PHOTO_CONTENT_TYPE))
            .andExpect(jsonPath("$.builderPhoto").value(Base64Utils.encodeToString(DEFAULT_BUILDER_PHOTO)));
    }
    @Test
    @Transactional
    public void getNonExistingProjectBuilder() throws Exception {
        // Get the projectBuilder
        restProjectBuilderMockMvc.perform(get("/api/project-builders/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProjectBuilder() throws Exception {
        // Initialize the database
        projectBuilderRepository.saveAndFlush(projectBuilder);

        int databaseSizeBeforeUpdate = projectBuilderRepository.findAll().size();

        // Update the projectBuilder
        ProjectBuilder updatedProjectBuilder = projectBuilderRepository.findById(projectBuilder.getId()).get();
        // Disconnect from session so that the updates on updatedProjectBuilder are not directly saved in db
        em.detach(updatedProjectBuilder);
        updatedProjectBuilder
            .builderName(UPDATED_BUILDER_NAME)
            .builderTitle(UPDATED_BUILDER_TITLE)
            .builderDate(UPDATED_BUILDER_DATE)
            .builderDescription(UPDATED_BUILDER_DESCRIPTION)
            .builderAddress(UPDATED_BUILDER_ADDRESS)
            .builderWebsite(UPDATED_BUILDER_WEBSITE)
            .builderPhone(UPDATED_BUILDER_PHONE)
            .builderPhoto(UPDATED_BUILDER_PHOTO)
            .builderPhotoContentType(UPDATED_BUILDER_PHOTO_CONTENT_TYPE);
        ProjectBuilderDTO projectBuilderDTO = projectBuilderMapper.toDto(updatedProjectBuilder);

        restProjectBuilderMockMvc.perform(put("/api/project-builders")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectBuilderDTO)))
            .andExpect(status().isOk());

        // Validate the ProjectBuilder in the database
        List<ProjectBuilder> projectBuilderList = projectBuilderRepository.findAll();
        assertThat(projectBuilderList).hasSize(databaseSizeBeforeUpdate);
        ProjectBuilder testProjectBuilder = projectBuilderList.get(projectBuilderList.size() - 1);
        assertThat(testProjectBuilder.getBuilderName()).isEqualTo(UPDATED_BUILDER_NAME);
        assertThat(testProjectBuilder.getBuilderTitle()).isEqualTo(UPDATED_BUILDER_TITLE);
        assertThat(testProjectBuilder.getBuilderDate()).isEqualTo(UPDATED_BUILDER_DATE);
        assertThat(testProjectBuilder.getBuilderDescription()).isEqualTo(UPDATED_BUILDER_DESCRIPTION);
        assertThat(testProjectBuilder.getBuilderAddress()).isEqualTo(UPDATED_BUILDER_ADDRESS);
        assertThat(testProjectBuilder.getBuilderWebsite()).isEqualTo(UPDATED_BUILDER_WEBSITE);
        assertThat(testProjectBuilder.getBuilderPhone()).isEqualTo(UPDATED_BUILDER_PHONE);
        assertThat(testProjectBuilder.getBuilderPhoto()).isEqualTo(UPDATED_BUILDER_PHOTO);
        assertThat(testProjectBuilder.getBuilderPhotoContentType()).isEqualTo(UPDATED_BUILDER_PHOTO_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingProjectBuilder() throws Exception {
        int databaseSizeBeforeUpdate = projectBuilderRepository.findAll().size();

        // Create the ProjectBuilder
        ProjectBuilderDTO projectBuilderDTO = projectBuilderMapper.toDto(projectBuilder);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restProjectBuilderMockMvc.perform(put("/api/project-builders")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectBuilderDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProjectBuilder in the database
        List<ProjectBuilder> projectBuilderList = projectBuilderRepository.findAll();
        assertThat(projectBuilderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProjectBuilder() throws Exception {
        // Initialize the database
        projectBuilderRepository.saveAndFlush(projectBuilder);

        int databaseSizeBeforeDelete = projectBuilderRepository.findAll().size();

        // Get the projectBuilder
        restProjectBuilderMockMvc.perform(delete("/api/project-builders/{id}", projectBuilder.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ProjectBuilder> projectBuilderList = projectBuilderRepository.findAll();
        assertThat(projectBuilderList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProjectBuilder.class);
        ProjectBuilder projectBuilder1 = new ProjectBuilder();
        projectBuilder1.setId(1L);
        ProjectBuilder projectBuilder2 = new ProjectBuilder();
        projectBuilder2.setId(projectBuilder1.getId());
        assertThat(projectBuilder1).isEqualTo(projectBuilder2);
        projectBuilder2.setId(2L);
        assertThat(projectBuilder1).isNotEqualTo(projectBuilder2);
        projectBuilder1.setId(null);
        assertThat(projectBuilder1).isNotEqualTo(projectBuilder2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProjectBuilderDTO.class);
        ProjectBuilderDTO projectBuilderDTO1 = new ProjectBuilderDTO();
        projectBuilderDTO1.setId(1L);
        ProjectBuilderDTO projectBuilderDTO2 = new ProjectBuilderDTO();
        assertThat(projectBuilderDTO1).isNotEqualTo(projectBuilderDTO2);
        projectBuilderDTO2.setId(projectBuilderDTO1.getId());
        assertThat(projectBuilderDTO1).isEqualTo(projectBuilderDTO2);
        projectBuilderDTO2.setId(2L);
        assertThat(projectBuilderDTO1).isNotEqualTo(projectBuilderDTO2);
        projectBuilderDTO1.setId(null);
        assertThat(projectBuilderDTO1).isNotEqualTo(projectBuilderDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(projectBuilderMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(projectBuilderMapper.fromId(null)).isNull();
    }
}
