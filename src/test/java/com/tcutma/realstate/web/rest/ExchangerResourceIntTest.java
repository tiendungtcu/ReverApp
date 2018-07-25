package com.tcutma.realstate.web.rest;

import com.tcutma.realstate.RiverApp;

import com.tcutma.realstate.domain.Exchanger;
import com.tcutma.realstate.repository.ExchangerRepository;
import com.tcutma.realstate.service.ExchangerService;
import com.tcutma.realstate.service.dto.ExchangerDTO;
import com.tcutma.realstate.service.mapper.ExchangerMapper;
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
 * Test class for the ExchangerResource REST controller.
 *
 * @see ExchangerResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RiverApp.class)
public class ExchangerResourceIntTest {

    private static final String DEFAULT_EXCHANGER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_EXCHANGER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EXCHANGER_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_EXCHANGER_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_EXCHANGER_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_EXCHANGER_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_EXCHANGER_AVATAR_URL = "AAAAAAAAAA";
    private static final String UPDATED_EXCHANGER_AVATAR_URL = "BBBBBBBBBB";

    @Autowired
    private ExchangerRepository exchangerRepository;


    @Autowired
    private ExchangerMapper exchangerMapper;
    

    @Autowired
    private ExchangerService exchangerService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restExchangerMockMvc;

    private Exchanger exchanger;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ExchangerResource exchangerResource = new ExchangerResource(exchangerService);
        this.restExchangerMockMvc = MockMvcBuilders.standaloneSetup(exchangerResource)
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
    public static Exchanger createEntity(EntityManager em) {
        Exchanger exchanger = new Exchanger()
            .exchangerName(DEFAULT_EXCHANGER_NAME)
            .exchangerAddress(DEFAULT_EXCHANGER_ADDRESS)
            .exchangerPhone(DEFAULT_EXCHANGER_PHONE)
            .exchangerAvatarUrl(DEFAULT_EXCHANGER_AVATAR_URL);
        return exchanger;
    }

    @Before
    public void initTest() {
        exchanger = createEntity(em);
    }

    @Test
    @Transactional
    public void createExchanger() throws Exception {
        int databaseSizeBeforeCreate = exchangerRepository.findAll().size();

        // Create the Exchanger
        ExchangerDTO exchangerDTO = exchangerMapper.toDto(exchanger);
        restExchangerMockMvc.perform(post("/api/exchangers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(exchangerDTO)))
            .andExpect(status().isCreated());

        // Validate the Exchanger in the database
        List<Exchanger> exchangerList = exchangerRepository.findAll();
        assertThat(exchangerList).hasSize(databaseSizeBeforeCreate + 1);
        Exchanger testExchanger = exchangerList.get(exchangerList.size() - 1);
        assertThat(testExchanger.getExchangerName()).isEqualTo(DEFAULT_EXCHANGER_NAME);
        assertThat(testExchanger.getExchangerAddress()).isEqualTo(DEFAULT_EXCHANGER_ADDRESS);
        assertThat(testExchanger.getExchangerPhone()).isEqualTo(DEFAULT_EXCHANGER_PHONE);
        assertThat(testExchanger.getExchangerAvatarUrl()).isEqualTo(DEFAULT_EXCHANGER_AVATAR_URL);
    }

    @Test
    @Transactional
    public void createExchangerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = exchangerRepository.findAll().size();

        // Create the Exchanger with an existing ID
        exchanger.setId(1L);
        ExchangerDTO exchangerDTO = exchangerMapper.toDto(exchanger);

        // An entity with an existing ID cannot be created, so this API call must fail
        restExchangerMockMvc.perform(post("/api/exchangers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(exchangerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Exchanger in the database
        List<Exchanger> exchangerList = exchangerRepository.findAll();
        assertThat(exchangerList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkExchangerNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = exchangerRepository.findAll().size();
        // set the field null
        exchanger.setExchangerName(null);

        // Create the Exchanger, which fails.
        ExchangerDTO exchangerDTO = exchangerMapper.toDto(exchanger);

        restExchangerMockMvc.perform(post("/api/exchangers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(exchangerDTO)))
            .andExpect(status().isBadRequest());

        List<Exchanger> exchangerList = exchangerRepository.findAll();
        assertThat(exchangerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkExchangerPhoneIsRequired() throws Exception {
        int databaseSizeBeforeTest = exchangerRepository.findAll().size();
        // set the field null
        exchanger.setExchangerPhone(null);

        // Create the Exchanger, which fails.
        ExchangerDTO exchangerDTO = exchangerMapper.toDto(exchanger);

        restExchangerMockMvc.perform(post("/api/exchangers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(exchangerDTO)))
            .andExpect(status().isBadRequest());

        List<Exchanger> exchangerList = exchangerRepository.findAll();
        assertThat(exchangerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllExchangers() throws Exception {
        // Initialize the database
        exchangerRepository.saveAndFlush(exchanger);

        // Get all the exchangerList
        restExchangerMockMvc.perform(get("/api/exchangers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(exchanger.getId().intValue())))
            .andExpect(jsonPath("$.[*].exchangerName").value(hasItem(DEFAULT_EXCHANGER_NAME.toString())))
            .andExpect(jsonPath("$.[*].exchangerAddress").value(hasItem(DEFAULT_EXCHANGER_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].exchangerPhone").value(hasItem(DEFAULT_EXCHANGER_PHONE.toString())))
            .andExpect(jsonPath("$.[*].exchangerAvatarUrl").value(hasItem(DEFAULT_EXCHANGER_AVATAR_URL.toString())));
    }
    

    @Test
    @Transactional
    public void getExchanger() throws Exception {
        // Initialize the database
        exchangerRepository.saveAndFlush(exchanger);

        // Get the exchanger
        restExchangerMockMvc.perform(get("/api/exchangers/{id}", exchanger.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(exchanger.getId().intValue()))
            .andExpect(jsonPath("$.exchangerName").value(DEFAULT_EXCHANGER_NAME.toString()))
            .andExpect(jsonPath("$.exchangerAddress").value(DEFAULT_EXCHANGER_ADDRESS.toString()))
            .andExpect(jsonPath("$.exchangerPhone").value(DEFAULT_EXCHANGER_PHONE.toString()))
            .andExpect(jsonPath("$.exchangerAvatarUrl").value(DEFAULT_EXCHANGER_AVATAR_URL.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingExchanger() throws Exception {
        // Get the exchanger
        restExchangerMockMvc.perform(get("/api/exchangers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateExchanger() throws Exception {
        // Initialize the database
        exchangerRepository.saveAndFlush(exchanger);

        int databaseSizeBeforeUpdate = exchangerRepository.findAll().size();

        // Update the exchanger
        Exchanger updatedExchanger = exchangerRepository.findById(exchanger.getId()).get();
        // Disconnect from session so that the updates on updatedExchanger are not directly saved in db
        em.detach(updatedExchanger);
        updatedExchanger
            .exchangerName(UPDATED_EXCHANGER_NAME)
            .exchangerAddress(UPDATED_EXCHANGER_ADDRESS)
            .exchangerPhone(UPDATED_EXCHANGER_PHONE)
            .exchangerAvatarUrl(UPDATED_EXCHANGER_AVATAR_URL);
        ExchangerDTO exchangerDTO = exchangerMapper.toDto(updatedExchanger);

        restExchangerMockMvc.perform(put("/api/exchangers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(exchangerDTO)))
            .andExpect(status().isOk());

        // Validate the Exchanger in the database
        List<Exchanger> exchangerList = exchangerRepository.findAll();
        assertThat(exchangerList).hasSize(databaseSizeBeforeUpdate);
        Exchanger testExchanger = exchangerList.get(exchangerList.size() - 1);
        assertThat(testExchanger.getExchangerName()).isEqualTo(UPDATED_EXCHANGER_NAME);
        assertThat(testExchanger.getExchangerAddress()).isEqualTo(UPDATED_EXCHANGER_ADDRESS);
        assertThat(testExchanger.getExchangerPhone()).isEqualTo(UPDATED_EXCHANGER_PHONE);
        assertThat(testExchanger.getExchangerAvatarUrl()).isEqualTo(UPDATED_EXCHANGER_AVATAR_URL);
    }

    @Test
    @Transactional
    public void updateNonExistingExchanger() throws Exception {
        int databaseSizeBeforeUpdate = exchangerRepository.findAll().size();

        // Create the Exchanger
        ExchangerDTO exchangerDTO = exchangerMapper.toDto(exchanger);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restExchangerMockMvc.perform(put("/api/exchangers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(exchangerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Exchanger in the database
        List<Exchanger> exchangerList = exchangerRepository.findAll();
        assertThat(exchangerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteExchanger() throws Exception {
        // Initialize the database
        exchangerRepository.saveAndFlush(exchanger);

        int databaseSizeBeforeDelete = exchangerRepository.findAll().size();

        // Get the exchanger
        restExchangerMockMvc.perform(delete("/api/exchangers/{id}", exchanger.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Exchanger> exchangerList = exchangerRepository.findAll();
        assertThat(exchangerList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Exchanger.class);
        Exchanger exchanger1 = new Exchanger();
        exchanger1.setId(1L);
        Exchanger exchanger2 = new Exchanger();
        exchanger2.setId(exchanger1.getId());
        assertThat(exchanger1).isEqualTo(exchanger2);
        exchanger2.setId(2L);
        assertThat(exchanger1).isNotEqualTo(exchanger2);
        exchanger1.setId(null);
        assertThat(exchanger1).isNotEqualTo(exchanger2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ExchangerDTO.class);
        ExchangerDTO exchangerDTO1 = new ExchangerDTO();
        exchangerDTO1.setId(1L);
        ExchangerDTO exchangerDTO2 = new ExchangerDTO();
        assertThat(exchangerDTO1).isNotEqualTo(exchangerDTO2);
        exchangerDTO2.setId(exchangerDTO1.getId());
        assertThat(exchangerDTO1).isEqualTo(exchangerDTO2);
        exchangerDTO2.setId(2L);
        assertThat(exchangerDTO1).isNotEqualTo(exchangerDTO2);
        exchangerDTO1.setId(null);
        assertThat(exchangerDTO1).isNotEqualTo(exchangerDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(exchangerMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(exchangerMapper.fromId(null)).isNull();
    }
}
