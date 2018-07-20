package com.tcutma.realstate.web.rest;

import com.tcutma.realstate.RiverApp;

import com.tcutma.realstate.domain.PropertyUser;
import com.tcutma.realstate.repository.PropertyUserRepository;
import com.tcutma.realstate.service.PropertyUserService;
import com.tcutma.realstate.service.dto.PropertyUserDTO;
import com.tcutma.realstate.service.mapper.PropertyUserMapper;
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
 * Test class for the PropertyUserResource REST controller.
 *
 * @see PropertyUserResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RiverApp.class)
public class PropertyUserResourceIntTest {

    private static final Boolean DEFAULT_LIKED = false;
    private static final Boolean UPDATED_LIKED = true;

    private static final Boolean DEFAULT_SHARED = false;
    private static final Boolean UPDATED_SHARED = true;

    private static final Instant DEFAULT_LIKED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LIKED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_SHARED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_SHARED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private PropertyUserRepository propertyUserRepository;


    @Autowired
    private PropertyUserMapper propertyUserMapper;
    

    @Autowired
    private PropertyUserService propertyUserService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPropertyUserMockMvc;

    private PropertyUser propertyUser;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PropertyUserResource propertyUserResource = new PropertyUserResource(propertyUserService);
        this.restPropertyUserMockMvc = MockMvcBuilders.standaloneSetup(propertyUserResource)
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
    public static PropertyUser createEntity(EntityManager em) {
        PropertyUser propertyUser = new PropertyUser()
            .liked(DEFAULT_LIKED)
            .shared(DEFAULT_SHARED)
            .likedDate(DEFAULT_LIKED_DATE)
            .sharedDate(DEFAULT_SHARED_DATE);
        return propertyUser;
    }

    @Before
    public void initTest() {
        propertyUser = createEntity(em);
    }

    @Test
    @Transactional
    public void createPropertyUser() throws Exception {
        int databaseSizeBeforeCreate = propertyUserRepository.findAll().size();

        // Create the PropertyUser
        PropertyUserDTO propertyUserDTO = propertyUserMapper.toDto(propertyUser);
        restPropertyUserMockMvc.perform(post("/api/property-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(propertyUserDTO)))
            .andExpect(status().isCreated());

        // Validate the PropertyUser in the database
        List<PropertyUser> propertyUserList = propertyUserRepository.findAll();
        assertThat(propertyUserList).hasSize(databaseSizeBeforeCreate + 1);
        PropertyUser testPropertyUser = propertyUserList.get(propertyUserList.size() - 1);
        assertThat(testPropertyUser.isLiked()).isEqualTo(DEFAULT_LIKED);
        assertThat(testPropertyUser.isShared()).isEqualTo(DEFAULT_SHARED);
        assertThat(testPropertyUser.getLikedDate()).isEqualTo(DEFAULT_LIKED_DATE);
        assertThat(testPropertyUser.getSharedDate()).isEqualTo(DEFAULT_SHARED_DATE);
    }

    @Test
    @Transactional
    public void createPropertyUserWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = propertyUserRepository.findAll().size();

        // Create the PropertyUser with an existing ID
        propertyUser.setId(1L);
        PropertyUserDTO propertyUserDTO = propertyUserMapper.toDto(propertyUser);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPropertyUserMockMvc.perform(post("/api/property-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(propertyUserDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PropertyUser in the database
        List<PropertyUser> propertyUserList = propertyUserRepository.findAll();
        assertThat(propertyUserList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllPropertyUsers() throws Exception {
        // Initialize the database
        propertyUserRepository.saveAndFlush(propertyUser);

        // Get all the propertyUserList
        restPropertyUserMockMvc.perform(get("/api/property-users?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(propertyUser.getId().intValue())))
            .andExpect(jsonPath("$.[*].liked").value(hasItem(DEFAULT_LIKED.booleanValue())))
            .andExpect(jsonPath("$.[*].shared").value(hasItem(DEFAULT_SHARED.booleanValue())))
            .andExpect(jsonPath("$.[*].likedDate").value(hasItem(DEFAULT_LIKED_DATE.toString())))
            .andExpect(jsonPath("$.[*].sharedDate").value(hasItem(DEFAULT_SHARED_DATE.toString())));
    }
    

    @Test
    @Transactional
    public void getPropertyUser() throws Exception {
        // Initialize the database
        propertyUserRepository.saveAndFlush(propertyUser);

        // Get the propertyUser
        restPropertyUserMockMvc.perform(get("/api/property-users/{id}", propertyUser.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(propertyUser.getId().intValue()))
            .andExpect(jsonPath("$.liked").value(DEFAULT_LIKED.booleanValue()))
            .andExpect(jsonPath("$.shared").value(DEFAULT_SHARED.booleanValue()))
            .andExpect(jsonPath("$.likedDate").value(DEFAULT_LIKED_DATE.toString()))
            .andExpect(jsonPath("$.sharedDate").value(DEFAULT_SHARED_DATE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingPropertyUser() throws Exception {
        // Get the propertyUser
        restPropertyUserMockMvc.perform(get("/api/property-users/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePropertyUser() throws Exception {
        // Initialize the database
        propertyUserRepository.saveAndFlush(propertyUser);

        int databaseSizeBeforeUpdate = propertyUserRepository.findAll().size();

        // Update the propertyUser
        PropertyUser updatedPropertyUser = propertyUserRepository.findById(propertyUser.getId()).get();
        // Disconnect from session so that the updates on updatedPropertyUser are not directly saved in db
        em.detach(updatedPropertyUser);
        updatedPropertyUser
            .liked(UPDATED_LIKED)
            .shared(UPDATED_SHARED)
            .likedDate(UPDATED_LIKED_DATE)
            .sharedDate(UPDATED_SHARED_DATE);
        PropertyUserDTO propertyUserDTO = propertyUserMapper.toDto(updatedPropertyUser);

        restPropertyUserMockMvc.perform(put("/api/property-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(propertyUserDTO)))
            .andExpect(status().isOk());

        // Validate the PropertyUser in the database
        List<PropertyUser> propertyUserList = propertyUserRepository.findAll();
        assertThat(propertyUserList).hasSize(databaseSizeBeforeUpdate);
        PropertyUser testPropertyUser = propertyUserList.get(propertyUserList.size() - 1);
        assertThat(testPropertyUser.isLiked()).isEqualTo(UPDATED_LIKED);
        assertThat(testPropertyUser.isShared()).isEqualTo(UPDATED_SHARED);
        assertThat(testPropertyUser.getLikedDate()).isEqualTo(UPDATED_LIKED_DATE);
        assertThat(testPropertyUser.getSharedDate()).isEqualTo(UPDATED_SHARED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingPropertyUser() throws Exception {
        int databaseSizeBeforeUpdate = propertyUserRepository.findAll().size();

        // Create the PropertyUser
        PropertyUserDTO propertyUserDTO = propertyUserMapper.toDto(propertyUser);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restPropertyUserMockMvc.perform(put("/api/property-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(propertyUserDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PropertyUser in the database
        List<PropertyUser> propertyUserList = propertyUserRepository.findAll();
        assertThat(propertyUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePropertyUser() throws Exception {
        // Initialize the database
        propertyUserRepository.saveAndFlush(propertyUser);

        int databaseSizeBeforeDelete = propertyUserRepository.findAll().size();

        // Get the propertyUser
        restPropertyUserMockMvc.perform(delete("/api/property-users/{id}", propertyUser.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PropertyUser> propertyUserList = propertyUserRepository.findAll();
        assertThat(propertyUserList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PropertyUser.class);
        PropertyUser propertyUser1 = new PropertyUser();
        propertyUser1.setId(1L);
        PropertyUser propertyUser2 = new PropertyUser();
        propertyUser2.setId(propertyUser1.getId());
        assertThat(propertyUser1).isEqualTo(propertyUser2);
        propertyUser2.setId(2L);
        assertThat(propertyUser1).isNotEqualTo(propertyUser2);
        propertyUser1.setId(null);
        assertThat(propertyUser1).isNotEqualTo(propertyUser2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PropertyUserDTO.class);
        PropertyUserDTO propertyUserDTO1 = new PropertyUserDTO();
        propertyUserDTO1.setId(1L);
        PropertyUserDTO propertyUserDTO2 = new PropertyUserDTO();
        assertThat(propertyUserDTO1).isNotEqualTo(propertyUserDTO2);
        propertyUserDTO2.setId(propertyUserDTO1.getId());
        assertThat(propertyUserDTO1).isEqualTo(propertyUserDTO2);
        propertyUserDTO2.setId(2L);
        assertThat(propertyUserDTO1).isNotEqualTo(propertyUserDTO2);
        propertyUserDTO1.setId(null);
        assertThat(propertyUserDTO1).isNotEqualTo(propertyUserDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(propertyUserMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(propertyUserMapper.fromId(null)).isNull();
    }
}
