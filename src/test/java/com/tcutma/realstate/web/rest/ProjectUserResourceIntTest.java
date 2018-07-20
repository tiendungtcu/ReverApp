package com.tcutma.realstate.web.rest;

import com.tcutma.realstate.RiverApp;

import com.tcutma.realstate.domain.ProjectUser;
import com.tcutma.realstate.repository.ProjectUserRepository;
import com.tcutma.realstate.service.ProjectUserService;
import com.tcutma.realstate.service.dto.ProjectUserDTO;
import com.tcutma.realstate.service.mapper.ProjectUserMapper;
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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;


import static com.tcutma.realstate.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ProjectUserResource REST controller.
 *
 * @see ProjectUserResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RiverApp.class)
public class ProjectUserResourceIntTest {

    private static final Boolean DEFAULT_LIKED = false;
    private static final Boolean UPDATED_LIKED = true;

    private static final Boolean DEFAULT_SHARED = false;
    private static final Boolean UPDATED_SHARED = true;

    private static final Instant DEFAULT_LIKED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LIKED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_SHARED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_SHARED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private ProjectUserRepository projectUserRepository;


    @Autowired
    private ProjectUserMapper projectUserMapper;
    

    @Autowired
    private ProjectUserService projectUserService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restProjectUserMockMvc;

    private ProjectUser projectUser;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProjectUserResource projectUserResource = new ProjectUserResource(projectUserService);
        this.restProjectUserMockMvc = MockMvcBuilders.standaloneSetup(projectUserResource)
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
    public static ProjectUser createEntity(EntityManager em) {
        ProjectUser projectUser = new ProjectUser()
            .liked(DEFAULT_LIKED)
            .shared(DEFAULT_SHARED)
            .likedDate(DEFAULT_LIKED_DATE)
            .sharedDate(DEFAULT_SHARED_DATE);
        return projectUser;
    }

    @Before
    public void initTest() {
        projectUser = createEntity(em);
    }

    @Test
    @Transactional
    public void createProjectUser() throws Exception {
        int databaseSizeBeforeCreate = projectUserRepository.findAll().size();

        // Create the ProjectUser
        ProjectUserDTO projectUserDTO = projectUserMapper.toDto(projectUser);
        restProjectUserMockMvc.perform(post("/api/project-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectUserDTO)))
            .andExpect(status().isCreated());

        // Validate the ProjectUser in the database
        List<ProjectUser> projectUserList = projectUserRepository.findAll();
        assertThat(projectUserList).hasSize(databaseSizeBeforeCreate + 1);
        ProjectUser testProjectUser = projectUserList.get(projectUserList.size() - 1);
        assertThat(testProjectUser.isLiked()).isEqualTo(DEFAULT_LIKED);
        assertThat(testProjectUser.isShared()).isEqualTo(DEFAULT_SHARED);
        assertThat(testProjectUser.getLikedDate()).isEqualTo(DEFAULT_LIKED_DATE);
        assertThat(testProjectUser.getSharedDate()).isEqualTo(DEFAULT_SHARED_DATE);
    }

    @Test
    @Transactional
    public void createProjectUserWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = projectUserRepository.findAll().size();

        // Create the ProjectUser with an existing ID
        projectUser.setId(1L);
        ProjectUserDTO projectUserDTO = projectUserMapper.toDto(projectUser);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProjectUserMockMvc.perform(post("/api/project-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectUserDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProjectUser in the database
        List<ProjectUser> projectUserList = projectUserRepository.findAll();
        assertThat(projectUserList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllProjectUsers() throws Exception {
        // Initialize the database
        projectUserRepository.saveAndFlush(projectUser);

        // Get all the projectUserList
        restProjectUserMockMvc.perform(get("/api/project-users?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(projectUser.getId().intValue())))
            .andExpect(jsonPath("$.[*].liked").value(hasItem(DEFAULT_LIKED.booleanValue())))
            .andExpect(jsonPath("$.[*].shared").value(hasItem(DEFAULT_SHARED.booleanValue())))
            .andExpect(jsonPath("$.[*].likedDate").value(hasItem(DEFAULT_LIKED_DATE.toString())))
            .andExpect(jsonPath("$.[*].sharedDate").value(hasItem(DEFAULT_SHARED_DATE.toString())));
    }
    

    @Test
    @Transactional
    public void getProjectUser() throws Exception {
        // Initialize the database
        projectUserRepository.saveAndFlush(projectUser);

        // Get the projectUser
        restProjectUserMockMvc.perform(get("/api/project-users/{id}", projectUser.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(projectUser.getId().intValue()))
            .andExpect(jsonPath("$.liked").value(DEFAULT_LIKED.booleanValue()))
            .andExpect(jsonPath("$.shared").value(DEFAULT_SHARED.booleanValue()))
            .andExpect(jsonPath("$.likedDate").value(DEFAULT_LIKED_DATE.toString()))
            .andExpect(jsonPath("$.sharedDate").value(DEFAULT_SHARED_DATE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingProjectUser() throws Exception {
        // Get the projectUser
        restProjectUserMockMvc.perform(get("/api/project-users/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProjectUser() throws Exception {
        // Initialize the database
        projectUserRepository.saveAndFlush(projectUser);

        int databaseSizeBeforeUpdate = projectUserRepository.findAll().size();

        // Update the projectUser
        ProjectUser updatedProjectUser = projectUserRepository.findById(projectUser.getId()).get();
        // Disconnect from session so that the updates on updatedProjectUser are not directly saved in db
        em.detach(updatedProjectUser);
        updatedProjectUser
            .liked(UPDATED_LIKED)
            .shared(UPDATED_SHARED)
            .likedDate(UPDATED_LIKED_DATE)
            .sharedDate(UPDATED_SHARED_DATE);
        ProjectUserDTO projectUserDTO = projectUserMapper.toDto(updatedProjectUser);

        restProjectUserMockMvc.perform(put("/api/project-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectUserDTO)))
            .andExpect(status().isOk());

        // Validate the ProjectUser in the database
        List<ProjectUser> projectUserList = projectUserRepository.findAll();
        assertThat(projectUserList).hasSize(databaseSizeBeforeUpdate);
        ProjectUser testProjectUser = projectUserList.get(projectUserList.size() - 1);
        assertThat(testProjectUser.isLiked()).isEqualTo(UPDATED_LIKED);
        assertThat(testProjectUser.isShared()).isEqualTo(UPDATED_SHARED);
        assertThat(testProjectUser.getLikedDate()).isEqualTo(UPDATED_LIKED_DATE);
        assertThat(testProjectUser.getSharedDate()).isEqualTo(UPDATED_SHARED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingProjectUser() throws Exception {
        int databaseSizeBeforeUpdate = projectUserRepository.findAll().size();

        // Create the ProjectUser
        ProjectUserDTO projectUserDTO = projectUserMapper.toDto(projectUser);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restProjectUserMockMvc.perform(put("/api/project-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectUserDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProjectUser in the database
        List<ProjectUser> projectUserList = projectUserRepository.findAll();
        assertThat(projectUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProjectUser() throws Exception {
        // Initialize the database
        projectUserRepository.saveAndFlush(projectUser);

        int databaseSizeBeforeDelete = projectUserRepository.findAll().size();

        // Get the projectUser
        restProjectUserMockMvc.perform(delete("/api/project-users/{id}", projectUser.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ProjectUser> projectUserList = projectUserRepository.findAll();
        assertThat(projectUserList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProjectUser.class);
        ProjectUser projectUser1 = new ProjectUser();
        projectUser1.setId(1L);
        ProjectUser projectUser2 = new ProjectUser();
        projectUser2.setId(projectUser1.getId());
        assertThat(projectUser1).isEqualTo(projectUser2);
        projectUser2.setId(2L);
        assertThat(projectUser1).isNotEqualTo(projectUser2);
        projectUser1.setId(null);
        assertThat(projectUser1).isNotEqualTo(projectUser2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProjectUserDTO.class);
        ProjectUserDTO projectUserDTO1 = new ProjectUserDTO();
        projectUserDTO1.setId(1L);
        ProjectUserDTO projectUserDTO2 = new ProjectUserDTO();
        assertThat(projectUserDTO1).isNotEqualTo(projectUserDTO2);
        projectUserDTO2.setId(projectUserDTO1.getId());
        assertThat(projectUserDTO1).isEqualTo(projectUserDTO2);
        projectUserDTO2.setId(2L);
        assertThat(projectUserDTO1).isNotEqualTo(projectUserDTO2);
        projectUserDTO1.setId(null);
        assertThat(projectUserDTO1).isNotEqualTo(projectUserDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(projectUserMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(projectUserMapper.fromId(null)).isNull();
    }
}
