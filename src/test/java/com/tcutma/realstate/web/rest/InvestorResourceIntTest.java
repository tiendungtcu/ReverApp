package com.tcutma.realstate.web.rest;

import com.tcutma.realstate.RiverApp;

import com.tcutma.realstate.domain.Investor;
import com.tcutma.realstate.repository.InvestorRepository;
import com.tcutma.realstate.service.InvestorService;
import com.tcutma.realstate.service.dto.InvestorDTO;
import com.tcutma.realstate.service.mapper.InvestorMapper;
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
 * Test class for the InvestorResource REST controller.
 *
 * @see InvestorResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RiverApp.class)
public class InvestorResourceIntTest {

    private static final String DEFAULT_INVESTOR_NAME = "AAAAAAAAAA";
    private static final String UPDATED_INVESTOR_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_INVESTOR_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_INVESTOR_TITLE = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_INVESTOR_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_INVESTOR_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_INVESTOR_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_INVESTOR_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_INVESTOR_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_INVESTOR_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_INVESTOR_WEBSITE = "AAAAAAAAAA";
    private static final String UPDATED_INVESTOR_WEBSITE = "BBBBBBBBBB";

    private static final String DEFAULT_INVESTOR_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_INVESTOR_PHONE = "BBBBBBBBBB";

    private static final byte[] DEFAULT_INVESTOR_PHOTO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_INVESTOR_PHOTO = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_INVESTOR_PHOTO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_INVESTOR_PHOTO_CONTENT_TYPE = "image/png";

    @Autowired
    private InvestorRepository investorRepository;


    @Autowired
    private InvestorMapper investorMapper;
    

    @Autowired
    private InvestorService investorService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restInvestorMockMvc;

    private Investor investor;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final InvestorResource investorResource = new InvestorResource(investorService);
        this.restInvestorMockMvc = MockMvcBuilders.standaloneSetup(investorResource)
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
    public static Investor createEntity(EntityManager em) {
        Investor investor = new Investor()
            .investorName(DEFAULT_INVESTOR_NAME)
            .investorTitle(DEFAULT_INVESTOR_TITLE)
            .investorDate(DEFAULT_INVESTOR_DATE)
            .investorDescription(DEFAULT_INVESTOR_DESCRIPTION)
            .investorAddress(DEFAULT_INVESTOR_ADDRESS)
            .investorWebsite(DEFAULT_INVESTOR_WEBSITE)
            .investorPhone(DEFAULT_INVESTOR_PHONE)
            .investorPhoto(DEFAULT_INVESTOR_PHOTO)
            .investorPhotoContentType(DEFAULT_INVESTOR_PHOTO_CONTENT_TYPE);
        return investor;
    }

    @Before
    public void initTest() {
        investor = createEntity(em);
    }

    @Test
    @Transactional
    public void createInvestor() throws Exception {
        int databaseSizeBeforeCreate = investorRepository.findAll().size();

        // Create the Investor
        InvestorDTO investorDTO = investorMapper.toDto(investor);
        restInvestorMockMvc.perform(post("/api/investors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(investorDTO)))
            .andExpect(status().isCreated());

        // Validate the Investor in the database
        List<Investor> investorList = investorRepository.findAll();
        assertThat(investorList).hasSize(databaseSizeBeforeCreate + 1);
        Investor testInvestor = investorList.get(investorList.size() - 1);
        assertThat(testInvestor.getInvestorName()).isEqualTo(DEFAULT_INVESTOR_NAME);
        assertThat(testInvestor.getInvestorTitle()).isEqualTo(DEFAULT_INVESTOR_TITLE);
        assertThat(testInvestor.getInvestorDate()).isEqualTo(DEFAULT_INVESTOR_DATE);
        assertThat(testInvestor.getInvestorDescription()).isEqualTo(DEFAULT_INVESTOR_DESCRIPTION);
        assertThat(testInvestor.getInvestorAddress()).isEqualTo(DEFAULT_INVESTOR_ADDRESS);
        assertThat(testInvestor.getInvestorWebsite()).isEqualTo(DEFAULT_INVESTOR_WEBSITE);
        assertThat(testInvestor.getInvestorPhone()).isEqualTo(DEFAULT_INVESTOR_PHONE);
        assertThat(testInvestor.getInvestorPhoto()).isEqualTo(DEFAULT_INVESTOR_PHOTO);
        assertThat(testInvestor.getInvestorPhotoContentType()).isEqualTo(DEFAULT_INVESTOR_PHOTO_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createInvestorWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = investorRepository.findAll().size();

        // Create the Investor with an existing ID
        investor.setId(1L);
        InvestorDTO investorDTO = investorMapper.toDto(investor);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInvestorMockMvc.perform(post("/api/investors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(investorDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Investor in the database
        List<Investor> investorList = investorRepository.findAll();
        assertThat(investorList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkInvestorNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = investorRepository.findAll().size();
        // set the field null
        investor.setInvestorName(null);

        // Create the Investor, which fails.
        InvestorDTO investorDTO = investorMapper.toDto(investor);

        restInvestorMockMvc.perform(post("/api/investors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(investorDTO)))
            .andExpect(status().isBadRequest());

        List<Investor> investorList = investorRepository.findAll();
        assertThat(investorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllInvestors() throws Exception {
        // Initialize the database
        investorRepository.saveAndFlush(investor);

        // Get all the investorList
        restInvestorMockMvc.perform(get("/api/investors?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(investor.getId().intValue())))
            .andExpect(jsonPath("$.[*].investorName").value(hasItem(DEFAULT_INVESTOR_NAME.toString())))
            .andExpect(jsonPath("$.[*].investorTitle").value(hasItem(DEFAULT_INVESTOR_TITLE.toString())))
            .andExpect(jsonPath("$.[*].investorDate").value(hasItem(sameInstant(DEFAULT_INVESTOR_DATE))))
            .andExpect(jsonPath("$.[*].investorDescription").value(hasItem(DEFAULT_INVESTOR_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].investorAddress").value(hasItem(DEFAULT_INVESTOR_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].investorWebsite").value(hasItem(DEFAULT_INVESTOR_WEBSITE.toString())))
            .andExpect(jsonPath("$.[*].investorPhone").value(hasItem(DEFAULT_INVESTOR_PHONE.toString())))
            .andExpect(jsonPath("$.[*].investorPhotoContentType").value(hasItem(DEFAULT_INVESTOR_PHOTO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].investorPhoto").value(hasItem(Base64Utils.encodeToString(DEFAULT_INVESTOR_PHOTO))));
    }
    

    @Test
    @Transactional
    public void getInvestor() throws Exception {
        // Initialize the database
        investorRepository.saveAndFlush(investor);

        // Get the investor
        restInvestorMockMvc.perform(get("/api/investors/{id}", investor.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(investor.getId().intValue()))
            .andExpect(jsonPath("$.investorName").value(DEFAULT_INVESTOR_NAME.toString()))
            .andExpect(jsonPath("$.investorTitle").value(DEFAULT_INVESTOR_TITLE.toString()))
            .andExpect(jsonPath("$.investorDate").value(sameInstant(DEFAULT_INVESTOR_DATE)))
            .andExpect(jsonPath("$.investorDescription").value(DEFAULT_INVESTOR_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.investorAddress").value(DEFAULT_INVESTOR_ADDRESS.toString()))
            .andExpect(jsonPath("$.investorWebsite").value(DEFAULT_INVESTOR_WEBSITE.toString()))
            .andExpect(jsonPath("$.investorPhone").value(DEFAULT_INVESTOR_PHONE.toString()))
            .andExpect(jsonPath("$.investorPhotoContentType").value(DEFAULT_INVESTOR_PHOTO_CONTENT_TYPE))
            .andExpect(jsonPath("$.investorPhoto").value(Base64Utils.encodeToString(DEFAULT_INVESTOR_PHOTO)));
    }
    @Test
    @Transactional
    public void getNonExistingInvestor() throws Exception {
        // Get the investor
        restInvestorMockMvc.perform(get("/api/investors/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInvestor() throws Exception {
        // Initialize the database
        investorRepository.saveAndFlush(investor);

        int databaseSizeBeforeUpdate = investorRepository.findAll().size();

        // Update the investor
        Investor updatedInvestor = investorRepository.findById(investor.getId()).get();
        // Disconnect from session so that the updates on updatedInvestor are not directly saved in db
        em.detach(updatedInvestor);
        updatedInvestor
            .investorName(UPDATED_INVESTOR_NAME)
            .investorTitle(UPDATED_INVESTOR_TITLE)
            .investorDate(UPDATED_INVESTOR_DATE)
            .investorDescription(UPDATED_INVESTOR_DESCRIPTION)
            .investorAddress(UPDATED_INVESTOR_ADDRESS)
            .investorWebsite(UPDATED_INVESTOR_WEBSITE)
            .investorPhone(UPDATED_INVESTOR_PHONE)
            .investorPhoto(UPDATED_INVESTOR_PHOTO)
            .investorPhotoContentType(UPDATED_INVESTOR_PHOTO_CONTENT_TYPE);
        InvestorDTO investorDTO = investorMapper.toDto(updatedInvestor);

        restInvestorMockMvc.perform(put("/api/investors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(investorDTO)))
            .andExpect(status().isOk());

        // Validate the Investor in the database
        List<Investor> investorList = investorRepository.findAll();
        assertThat(investorList).hasSize(databaseSizeBeforeUpdate);
        Investor testInvestor = investorList.get(investorList.size() - 1);
        assertThat(testInvestor.getInvestorName()).isEqualTo(UPDATED_INVESTOR_NAME);
        assertThat(testInvestor.getInvestorTitle()).isEqualTo(UPDATED_INVESTOR_TITLE);
        assertThat(testInvestor.getInvestorDate()).isEqualTo(UPDATED_INVESTOR_DATE);
        assertThat(testInvestor.getInvestorDescription()).isEqualTo(UPDATED_INVESTOR_DESCRIPTION);
        assertThat(testInvestor.getInvestorAddress()).isEqualTo(UPDATED_INVESTOR_ADDRESS);
        assertThat(testInvestor.getInvestorWebsite()).isEqualTo(UPDATED_INVESTOR_WEBSITE);
        assertThat(testInvestor.getInvestorPhone()).isEqualTo(UPDATED_INVESTOR_PHONE);
        assertThat(testInvestor.getInvestorPhoto()).isEqualTo(UPDATED_INVESTOR_PHOTO);
        assertThat(testInvestor.getInvestorPhotoContentType()).isEqualTo(UPDATED_INVESTOR_PHOTO_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingInvestor() throws Exception {
        int databaseSizeBeforeUpdate = investorRepository.findAll().size();

        // Create the Investor
        InvestorDTO investorDTO = investorMapper.toDto(investor);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restInvestorMockMvc.perform(put("/api/investors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(investorDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Investor in the database
        List<Investor> investorList = investorRepository.findAll();
        assertThat(investorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInvestor() throws Exception {
        // Initialize the database
        investorRepository.saveAndFlush(investor);

        int databaseSizeBeforeDelete = investorRepository.findAll().size();

        // Get the investor
        restInvestorMockMvc.perform(delete("/api/investors/{id}", investor.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Investor> investorList = investorRepository.findAll();
        assertThat(investorList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Investor.class);
        Investor investor1 = new Investor();
        investor1.setId(1L);
        Investor investor2 = new Investor();
        investor2.setId(investor1.getId());
        assertThat(investor1).isEqualTo(investor2);
        investor2.setId(2L);
        assertThat(investor1).isNotEqualTo(investor2);
        investor1.setId(null);
        assertThat(investor1).isNotEqualTo(investor2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InvestorDTO.class);
        InvestorDTO investorDTO1 = new InvestorDTO();
        investorDTO1.setId(1L);
        InvestorDTO investorDTO2 = new InvestorDTO();
        assertThat(investorDTO1).isNotEqualTo(investorDTO2);
        investorDTO2.setId(investorDTO1.getId());
        assertThat(investorDTO1).isEqualTo(investorDTO2);
        investorDTO2.setId(2L);
        assertThat(investorDTO1).isNotEqualTo(investorDTO2);
        investorDTO1.setId(null);
        assertThat(investorDTO1).isNotEqualTo(investorDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(investorMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(investorMapper.fromId(null)).isNull();
    }
}
