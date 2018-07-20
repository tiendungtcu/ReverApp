package com.tcutma.realstate.web.rest;

import com.tcutma.realstate.RiverApp;

import com.tcutma.realstate.domain.Company;
import com.tcutma.realstate.repository.CompanyRepository;
import com.tcutma.realstate.service.dto.CompanyDTO;
import com.tcutma.realstate.service.mapper.CompanyMapper;
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
import java.util.List;


import static com.tcutma.realstate.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the CompanyResource REST controller.
 *
 * @see CompanyResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RiverApp.class)
public class CompanyResourceIntTest {

    private static final String DEFAULT_COMPANY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_COMPANY_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_COMPANY_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY_ADDRESS = "BBBBBBBBBB";

    private static final byte[] DEFAULT_COMPANY_LOGO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_COMPANY_LOGO = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_COMPANY_LOGO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_COMPANY_LOGO_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_COMPANY_WEBSITE = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY_WEBSITE = "BBBBBBBBBB";

    private static final String DEFAULT_COMPANY_FACEBOOK = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY_FACEBOOK = "BBBBBBBBBB";

    private static final String DEFAULT_COMPANY_TWITTER = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY_TWITTER = "BBBBBBBBBB";

    private static final String DEFAULT_COMPANY_INSTAGRAM = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY_INSTAGRAM = "BBBBBBBBBB";

    private static final String DEFAULT_COMPANY_LINKEDIN = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY_LINKEDIN = "BBBBBBBBBB";

    private static final String DEFAULT_COMPANY_GOOGLE_PLUS = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY_GOOGLE_PLUS = "BBBBBBBBBB";

    private static final String DEFAULT_COMPANY_YOUTUBE = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY_YOUTUBE = "BBBBBBBBBB";

    private static final String DEFAULT_COMPANY_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private CompanyRepository companyRepository;


    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCompanyMockMvc;

    private Company company;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CompanyResource companyResource = new CompanyResource(companyRepository, companyMapper);
        this.restCompanyMockMvc = MockMvcBuilders.standaloneSetup(companyResource)
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
    public static Company createEntity(EntityManager em) {
        Company company = new Company()
            .companyName(DEFAULT_COMPANY_NAME)
            .companyPhone(DEFAULT_COMPANY_PHONE)
            .companyAddress(DEFAULT_COMPANY_ADDRESS)
            .companyLogo(DEFAULT_COMPANY_LOGO)
            .companyLogoContentType(DEFAULT_COMPANY_LOGO_CONTENT_TYPE)
            .companyWebsite(DEFAULT_COMPANY_WEBSITE)
            .companyFacebook(DEFAULT_COMPANY_FACEBOOK)
            .companyTwitter(DEFAULT_COMPANY_TWITTER)
            .companyInstagram(DEFAULT_COMPANY_INSTAGRAM)
            .companyLinkedin(DEFAULT_COMPANY_LINKEDIN)
            .companyGooglePlus(DEFAULT_COMPANY_GOOGLE_PLUS)
            .companyYoutube(DEFAULT_COMPANY_YOUTUBE)
            .companyDescription(DEFAULT_COMPANY_DESCRIPTION);
        return company;
    }

    @Before
    public void initTest() {
        company = createEntity(em);
    }

    @Test
    @Transactional
    public void createCompany() throws Exception {
        int databaseSizeBeforeCreate = companyRepository.findAll().size();

        // Create the Company
        CompanyDTO companyDTO = companyMapper.toDto(company);
        restCompanyMockMvc.perform(post("/api/companies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(companyDTO)))
            .andExpect(status().isCreated());

        // Validate the Company in the database
        List<Company> companyList = companyRepository.findAll();
        assertThat(companyList).hasSize(databaseSizeBeforeCreate + 1);
        Company testCompany = companyList.get(companyList.size() - 1);
        assertThat(testCompany.getCompanyName()).isEqualTo(DEFAULT_COMPANY_NAME);
        assertThat(testCompany.getCompanyPhone()).isEqualTo(DEFAULT_COMPANY_PHONE);
        assertThat(testCompany.getCompanyAddress()).isEqualTo(DEFAULT_COMPANY_ADDRESS);
        assertThat(testCompany.getCompanyLogo()).isEqualTo(DEFAULT_COMPANY_LOGO);
        assertThat(testCompany.getCompanyLogoContentType()).isEqualTo(DEFAULT_COMPANY_LOGO_CONTENT_TYPE);
        assertThat(testCompany.getCompanyWebsite()).isEqualTo(DEFAULT_COMPANY_WEBSITE);
        assertThat(testCompany.getCompanyFacebook()).isEqualTo(DEFAULT_COMPANY_FACEBOOK);
        assertThat(testCompany.getCompanyTwitter()).isEqualTo(DEFAULT_COMPANY_TWITTER);
        assertThat(testCompany.getCompanyInstagram()).isEqualTo(DEFAULT_COMPANY_INSTAGRAM);
        assertThat(testCompany.getCompanyLinkedin()).isEqualTo(DEFAULT_COMPANY_LINKEDIN);
        assertThat(testCompany.getCompanyGooglePlus()).isEqualTo(DEFAULT_COMPANY_GOOGLE_PLUS);
        assertThat(testCompany.getCompanyYoutube()).isEqualTo(DEFAULT_COMPANY_YOUTUBE);
        assertThat(testCompany.getCompanyDescription()).isEqualTo(DEFAULT_COMPANY_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createCompanyWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = companyRepository.findAll().size();

        // Create the Company with an existing ID
        company.setId(1L);
        CompanyDTO companyDTO = companyMapper.toDto(company);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCompanyMockMvc.perform(post("/api/companies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(companyDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Company in the database
        List<Company> companyList = companyRepository.findAll();
        assertThat(companyList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCompanyNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = companyRepository.findAll().size();
        // set the field null
        company.setCompanyName(null);

        // Create the Company, which fails.
        CompanyDTO companyDTO = companyMapper.toDto(company);

        restCompanyMockMvc.perform(post("/api/companies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(companyDTO)))
            .andExpect(status().isBadRequest());

        List<Company> companyList = companyRepository.findAll();
        assertThat(companyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCompanies() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList
        restCompanyMockMvc.perform(get("/api/companies?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(company.getId().intValue())))
            .andExpect(jsonPath("$.[*].companyName").value(hasItem(DEFAULT_COMPANY_NAME.toString())))
            .andExpect(jsonPath("$.[*].companyPhone").value(hasItem(DEFAULT_COMPANY_PHONE.toString())))
            .andExpect(jsonPath("$.[*].companyAddress").value(hasItem(DEFAULT_COMPANY_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].companyLogoContentType").value(hasItem(DEFAULT_COMPANY_LOGO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].companyLogo").value(hasItem(Base64Utils.encodeToString(DEFAULT_COMPANY_LOGO))))
            .andExpect(jsonPath("$.[*].companyWebsite").value(hasItem(DEFAULT_COMPANY_WEBSITE.toString())))
            .andExpect(jsonPath("$.[*].companyFacebook").value(hasItem(DEFAULT_COMPANY_FACEBOOK.toString())))
            .andExpect(jsonPath("$.[*].companyTwitter").value(hasItem(DEFAULT_COMPANY_TWITTER.toString())))
            .andExpect(jsonPath("$.[*].companyInstagram").value(hasItem(DEFAULT_COMPANY_INSTAGRAM.toString())))
            .andExpect(jsonPath("$.[*].companyLinkedin").value(hasItem(DEFAULT_COMPANY_LINKEDIN.toString())))
            .andExpect(jsonPath("$.[*].companyGooglePlus").value(hasItem(DEFAULT_COMPANY_GOOGLE_PLUS.toString())))
            .andExpect(jsonPath("$.[*].companyYoutube").value(hasItem(DEFAULT_COMPANY_YOUTUBE.toString())))
            .andExpect(jsonPath("$.[*].companyDescription").value(hasItem(DEFAULT_COMPANY_DESCRIPTION.toString())));
    }
    

    @Test
    @Transactional
    public void getCompany() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get the company
        restCompanyMockMvc.perform(get("/api/companies/{id}", company.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(company.getId().intValue()))
            .andExpect(jsonPath("$.companyName").value(DEFAULT_COMPANY_NAME.toString()))
            .andExpect(jsonPath("$.companyPhone").value(DEFAULT_COMPANY_PHONE.toString()))
            .andExpect(jsonPath("$.companyAddress").value(DEFAULT_COMPANY_ADDRESS.toString()))
            .andExpect(jsonPath("$.companyLogoContentType").value(DEFAULT_COMPANY_LOGO_CONTENT_TYPE))
            .andExpect(jsonPath("$.companyLogo").value(Base64Utils.encodeToString(DEFAULT_COMPANY_LOGO)))
            .andExpect(jsonPath("$.companyWebsite").value(DEFAULT_COMPANY_WEBSITE.toString()))
            .andExpect(jsonPath("$.companyFacebook").value(DEFAULT_COMPANY_FACEBOOK.toString()))
            .andExpect(jsonPath("$.companyTwitter").value(DEFAULT_COMPANY_TWITTER.toString()))
            .andExpect(jsonPath("$.companyInstagram").value(DEFAULT_COMPANY_INSTAGRAM.toString()))
            .andExpect(jsonPath("$.companyLinkedin").value(DEFAULT_COMPANY_LINKEDIN.toString()))
            .andExpect(jsonPath("$.companyGooglePlus").value(DEFAULT_COMPANY_GOOGLE_PLUS.toString()))
            .andExpect(jsonPath("$.companyYoutube").value(DEFAULT_COMPANY_YOUTUBE.toString()))
            .andExpect(jsonPath("$.companyDescription").value(DEFAULT_COMPANY_DESCRIPTION.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingCompany() throws Exception {
        // Get the company
        restCompanyMockMvc.perform(get("/api/companies/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCompany() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        int databaseSizeBeforeUpdate = companyRepository.findAll().size();

        // Update the company
        Company updatedCompany = companyRepository.findById(company.getId()).get();
        // Disconnect from session so that the updates on updatedCompany are not directly saved in db
        em.detach(updatedCompany);
        updatedCompany
            .companyName(UPDATED_COMPANY_NAME)
            .companyPhone(UPDATED_COMPANY_PHONE)
            .companyAddress(UPDATED_COMPANY_ADDRESS)
            .companyLogo(UPDATED_COMPANY_LOGO)
            .companyLogoContentType(UPDATED_COMPANY_LOGO_CONTENT_TYPE)
            .companyWebsite(UPDATED_COMPANY_WEBSITE)
            .companyFacebook(UPDATED_COMPANY_FACEBOOK)
            .companyTwitter(UPDATED_COMPANY_TWITTER)
            .companyInstagram(UPDATED_COMPANY_INSTAGRAM)
            .companyLinkedin(UPDATED_COMPANY_LINKEDIN)
            .companyGooglePlus(UPDATED_COMPANY_GOOGLE_PLUS)
            .companyYoutube(UPDATED_COMPANY_YOUTUBE)
            .companyDescription(UPDATED_COMPANY_DESCRIPTION);
        CompanyDTO companyDTO = companyMapper.toDto(updatedCompany);

        restCompanyMockMvc.perform(put("/api/companies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(companyDTO)))
            .andExpect(status().isOk());

        // Validate the Company in the database
        List<Company> companyList = companyRepository.findAll();
        assertThat(companyList).hasSize(databaseSizeBeforeUpdate);
        Company testCompany = companyList.get(companyList.size() - 1);
        assertThat(testCompany.getCompanyName()).isEqualTo(UPDATED_COMPANY_NAME);
        assertThat(testCompany.getCompanyPhone()).isEqualTo(UPDATED_COMPANY_PHONE);
        assertThat(testCompany.getCompanyAddress()).isEqualTo(UPDATED_COMPANY_ADDRESS);
        assertThat(testCompany.getCompanyLogo()).isEqualTo(UPDATED_COMPANY_LOGO);
        assertThat(testCompany.getCompanyLogoContentType()).isEqualTo(UPDATED_COMPANY_LOGO_CONTENT_TYPE);
        assertThat(testCompany.getCompanyWebsite()).isEqualTo(UPDATED_COMPANY_WEBSITE);
        assertThat(testCompany.getCompanyFacebook()).isEqualTo(UPDATED_COMPANY_FACEBOOK);
        assertThat(testCompany.getCompanyTwitter()).isEqualTo(UPDATED_COMPANY_TWITTER);
        assertThat(testCompany.getCompanyInstagram()).isEqualTo(UPDATED_COMPANY_INSTAGRAM);
        assertThat(testCompany.getCompanyLinkedin()).isEqualTo(UPDATED_COMPANY_LINKEDIN);
        assertThat(testCompany.getCompanyGooglePlus()).isEqualTo(UPDATED_COMPANY_GOOGLE_PLUS);
        assertThat(testCompany.getCompanyYoutube()).isEqualTo(UPDATED_COMPANY_YOUTUBE);
        assertThat(testCompany.getCompanyDescription()).isEqualTo(UPDATED_COMPANY_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingCompany() throws Exception {
        int databaseSizeBeforeUpdate = companyRepository.findAll().size();

        // Create the Company
        CompanyDTO companyDTO = companyMapper.toDto(company);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCompanyMockMvc.perform(put("/api/companies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(companyDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Company in the database
        List<Company> companyList = companyRepository.findAll();
        assertThat(companyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCompany() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        int databaseSizeBeforeDelete = companyRepository.findAll().size();

        // Get the company
        restCompanyMockMvc.perform(delete("/api/companies/{id}", company.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Company> companyList = companyRepository.findAll();
        assertThat(companyList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Company.class);
        Company company1 = new Company();
        company1.setId(1L);
        Company company2 = new Company();
        company2.setId(company1.getId());
        assertThat(company1).isEqualTo(company2);
        company2.setId(2L);
        assertThat(company1).isNotEqualTo(company2);
        company1.setId(null);
        assertThat(company1).isNotEqualTo(company2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CompanyDTO.class);
        CompanyDTO companyDTO1 = new CompanyDTO();
        companyDTO1.setId(1L);
        CompanyDTO companyDTO2 = new CompanyDTO();
        assertThat(companyDTO1).isNotEqualTo(companyDTO2);
        companyDTO2.setId(companyDTO1.getId());
        assertThat(companyDTO1).isEqualTo(companyDTO2);
        companyDTO2.setId(2L);
        assertThat(companyDTO1).isNotEqualTo(companyDTO2);
        companyDTO1.setId(null);
        assertThat(companyDTO1).isNotEqualTo(companyDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(companyMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(companyMapper.fromId(null)).isNull();
    }
}
