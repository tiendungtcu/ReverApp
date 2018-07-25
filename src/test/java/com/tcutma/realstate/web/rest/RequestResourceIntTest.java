package com.tcutma.realstate.web.rest;

import com.tcutma.realstate.RiverApp;

import com.tcutma.realstate.domain.Request;
import com.tcutma.realstate.repository.RequestRepository;
import com.tcutma.realstate.service.RequestService;
import com.tcutma.realstate.service.dto.RequestDTO;
import com.tcutma.realstate.service.mapper.RequestMapper;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;


import static com.tcutma.realstate.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.tcutma.realstate.domain.enumeration.ResourceType;
import com.tcutma.realstate.domain.enumeration.RequestType;
/**
 * Test class for the RequestResource REST controller.
 *
 * @see RequestResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RiverApp.class)
public class RequestResourceIntTest {

    private static final String DEFAULT_REQUEST_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_REQUEST_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_REQUEST_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_REQUEST_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_REQUEST_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_REQUEST_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_REQUEST_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_REQUEST_PHONE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_REQUEST_GET_ANALYSIS = false;
    private static final Boolean UPDATED_REQUEST_GET_ANALYSIS = true;

    private static final Boolean DEFAULT_REQUEST_GET_PRICE = false;
    private static final Boolean UPDATED_REQUEST_GET_PRICE = true;

    private static final String DEFAULT_REQUEST_PAGE_URL = "AAAAAAAAAA";
    private static final String UPDATED_REQUEST_PAGE_URL = "BBBBBBBBBB";

    private static final Long DEFAULT_RESOURCE_ID = 1L;
    private static final Long UPDATED_RESOURCE_ID = 2L;

    private static final ResourceType DEFAULT_RESOURCE_TYPE = ResourceType.PROJECT;
    private static final ResourceType UPDATED_RESOURCE_TYPE = ResourceType.PROPERTY;

    private static final RequestType DEFAULT_REQUEST_TYPE = RequestType.MEETING;
    private static final RequestType UPDATED_REQUEST_TYPE = RequestType.PRICE;

    private static final LocalDate DEFAULT_REQUEST_MEETING_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_REQUEST_MEETING_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_REQUEST_QUESTION = "AAAAAAAAAA";
    private static final String UPDATED_REQUEST_QUESTION = "BBBBBBBBBB";

    private static final Double DEFAULT_REQUEST_PRICE = 0D;
    private static final Double UPDATED_REQUEST_PRICE = 1D;

    @Autowired
    private RequestRepository requestRepository;


    @Autowired
    private RequestMapper requestMapper;
    

    @Autowired
    private RequestService requestService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRequestMockMvc;

    private Request request;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RequestResource requestResource = new RequestResource(requestService);
        this.restRequestMockMvc = MockMvcBuilders.standaloneSetup(requestResource)
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
    public static Request createEntity(EntityManager em) {
        Request request = new Request()
            .requestFirstName(DEFAULT_REQUEST_FIRST_NAME)
            .requestLastName(DEFAULT_REQUEST_LAST_NAME)
            .requestEmail(DEFAULT_REQUEST_EMAIL)
            .requestPhone(DEFAULT_REQUEST_PHONE)
            .requestGetAnalysis(DEFAULT_REQUEST_GET_ANALYSIS)
            .requestGetPrice(DEFAULT_REQUEST_GET_PRICE)
            .requestPageUrl(DEFAULT_REQUEST_PAGE_URL)
            .resourceId(DEFAULT_RESOURCE_ID)
            .resourceType(DEFAULT_RESOURCE_TYPE)
            .requestType(DEFAULT_REQUEST_TYPE)
            .requestMeetingDate(DEFAULT_REQUEST_MEETING_DATE)
            .requestQuestion(DEFAULT_REQUEST_QUESTION)
            .requestPrice(DEFAULT_REQUEST_PRICE);
        return request;
    }

    @Before
    public void initTest() {
        request = createEntity(em);
    }

    @Test
    @Transactional
    public void createRequest() throws Exception {
        int databaseSizeBeforeCreate = requestRepository.findAll().size();

        // Create the Request
        RequestDTO requestDTO = requestMapper.toDto(request);
        restRequestMockMvc.perform(post("/api/requests")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(requestDTO)))
            .andExpect(status().isCreated());

        // Validate the Request in the database
        List<Request> requestList = requestRepository.findAll();
        assertThat(requestList).hasSize(databaseSizeBeforeCreate + 1);
        Request testRequest = requestList.get(requestList.size() - 1);
        assertThat(testRequest.getRequestFirstName()).isEqualTo(DEFAULT_REQUEST_FIRST_NAME);
        assertThat(testRequest.getRequestLastName()).isEqualTo(DEFAULT_REQUEST_LAST_NAME);
        assertThat(testRequest.getRequestEmail()).isEqualTo(DEFAULT_REQUEST_EMAIL);
        assertThat(testRequest.getRequestPhone()).isEqualTo(DEFAULT_REQUEST_PHONE);
        assertThat(testRequest.isRequestGetAnalysis()).isEqualTo(DEFAULT_REQUEST_GET_ANALYSIS);
        assertThat(testRequest.isRequestGetPrice()).isEqualTo(DEFAULT_REQUEST_GET_PRICE);
        assertThat(testRequest.getRequestPageUrl()).isEqualTo(DEFAULT_REQUEST_PAGE_URL);
        assertThat(testRequest.getResourceId()).isEqualTo(DEFAULT_RESOURCE_ID);
        assertThat(testRequest.getResourceType()).isEqualTo(DEFAULT_RESOURCE_TYPE);
        assertThat(testRequest.getRequestType()).isEqualTo(DEFAULT_REQUEST_TYPE);
        assertThat(testRequest.getRequestMeetingDate()).isEqualTo(DEFAULT_REQUEST_MEETING_DATE);
        assertThat(testRequest.getRequestQuestion()).isEqualTo(DEFAULT_REQUEST_QUESTION);
        assertThat(testRequest.getRequestPrice()).isEqualTo(DEFAULT_REQUEST_PRICE);
    }

    @Test
    @Transactional
    public void createRequestWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = requestRepository.findAll().size();

        // Create the Request with an existing ID
        request.setId(1L);
        RequestDTO requestDTO = requestMapper.toDto(request);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRequestMockMvc.perform(post("/api/requests")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(requestDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Request in the database
        List<Request> requestList = requestRepository.findAll();
        assertThat(requestList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkRequestFirstNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = requestRepository.findAll().size();
        // set the field null
        request.setRequestFirstName(null);

        // Create the Request, which fails.
        RequestDTO requestDTO = requestMapper.toDto(request);

        restRequestMockMvc.perform(post("/api/requests")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(requestDTO)))
            .andExpect(status().isBadRequest());

        List<Request> requestList = requestRepository.findAll();
        assertThat(requestList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRequestLastNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = requestRepository.findAll().size();
        // set the field null
        request.setRequestLastName(null);

        // Create the Request, which fails.
        RequestDTO requestDTO = requestMapper.toDto(request);

        restRequestMockMvc.perform(post("/api/requests")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(requestDTO)))
            .andExpect(status().isBadRequest());

        List<Request> requestList = requestRepository.findAll();
        assertThat(requestList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRequestEmailIsRequired() throws Exception {
        int databaseSizeBeforeTest = requestRepository.findAll().size();
        // set the field null
        request.setRequestEmail(null);

        // Create the Request, which fails.
        RequestDTO requestDTO = requestMapper.toDto(request);

        restRequestMockMvc.perform(post("/api/requests")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(requestDTO)))
            .andExpect(status().isBadRequest());

        List<Request> requestList = requestRepository.findAll();
        assertThat(requestList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRequestPhoneIsRequired() throws Exception {
        int databaseSizeBeforeTest = requestRepository.findAll().size();
        // set the field null
        request.setRequestPhone(null);

        // Create the Request, which fails.
        RequestDTO requestDTO = requestMapper.toDto(request);

        restRequestMockMvc.perform(post("/api/requests")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(requestDTO)))
            .andExpect(status().isBadRequest());

        List<Request> requestList = requestRepository.findAll();
        assertThat(requestList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRequests() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        // Get all the requestList
        restRequestMockMvc.perform(get("/api/requests?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(request.getId().intValue())))
            .andExpect(jsonPath("$.[*].requestFirstName").value(hasItem(DEFAULT_REQUEST_FIRST_NAME.toString())))
            .andExpect(jsonPath("$.[*].requestLastName").value(hasItem(DEFAULT_REQUEST_LAST_NAME.toString())))
            .andExpect(jsonPath("$.[*].requestEmail").value(hasItem(DEFAULT_REQUEST_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].requestPhone").value(hasItem(DEFAULT_REQUEST_PHONE.toString())))
            .andExpect(jsonPath("$.[*].requestGetAnalysis").value(hasItem(DEFAULT_REQUEST_GET_ANALYSIS.booleanValue())))
            .andExpect(jsonPath("$.[*].requestGetPrice").value(hasItem(DEFAULT_REQUEST_GET_PRICE.booleanValue())))
            .andExpect(jsonPath("$.[*].requestPageUrl").value(hasItem(DEFAULT_REQUEST_PAGE_URL.toString())))
            .andExpect(jsonPath("$.[*].resourceId").value(hasItem(DEFAULT_RESOURCE_ID.intValue())))
            .andExpect(jsonPath("$.[*].resourceType").value(hasItem(DEFAULT_RESOURCE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].requestType").value(hasItem(DEFAULT_REQUEST_TYPE.toString())))
            .andExpect(jsonPath("$.[*].requestMeetingDate").value(hasItem(DEFAULT_REQUEST_MEETING_DATE.toString())))
            .andExpect(jsonPath("$.[*].requestQuestion").value(hasItem(DEFAULT_REQUEST_QUESTION.toString())))
            .andExpect(jsonPath("$.[*].requestPrice").value(hasItem(DEFAULT_REQUEST_PRICE.doubleValue())));
    }
    

    @Test
    @Transactional
    public void getRequest() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        // Get the request
        restRequestMockMvc.perform(get("/api/requests/{id}", request.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(request.getId().intValue()))
            .andExpect(jsonPath("$.requestFirstName").value(DEFAULT_REQUEST_FIRST_NAME.toString()))
            .andExpect(jsonPath("$.requestLastName").value(DEFAULT_REQUEST_LAST_NAME.toString()))
            .andExpect(jsonPath("$.requestEmail").value(DEFAULT_REQUEST_EMAIL.toString()))
            .andExpect(jsonPath("$.requestPhone").value(DEFAULT_REQUEST_PHONE.toString()))
            .andExpect(jsonPath("$.requestGetAnalysis").value(DEFAULT_REQUEST_GET_ANALYSIS.booleanValue()))
            .andExpect(jsonPath("$.requestGetPrice").value(DEFAULT_REQUEST_GET_PRICE.booleanValue()))
            .andExpect(jsonPath("$.requestPageUrl").value(DEFAULT_REQUEST_PAGE_URL.toString()))
            .andExpect(jsonPath("$.resourceId").value(DEFAULT_RESOURCE_ID.intValue()))
            .andExpect(jsonPath("$.resourceType").value(DEFAULT_RESOURCE_TYPE.toString()))
            .andExpect(jsonPath("$.requestType").value(DEFAULT_REQUEST_TYPE.toString()))
            .andExpect(jsonPath("$.requestMeetingDate").value(DEFAULT_REQUEST_MEETING_DATE.toString()))
            .andExpect(jsonPath("$.requestQuestion").value(DEFAULT_REQUEST_QUESTION.toString()))
            .andExpect(jsonPath("$.requestPrice").value(DEFAULT_REQUEST_PRICE.doubleValue()));
    }
    @Test
    @Transactional
    public void getNonExistingRequest() throws Exception {
        // Get the request
        restRequestMockMvc.perform(get("/api/requests/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRequest() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        int databaseSizeBeforeUpdate = requestRepository.findAll().size();

        // Update the request
        Request updatedRequest = requestRepository.findById(request.getId()).get();
        // Disconnect from session so that the updates on updatedRequest are not directly saved in db
        em.detach(updatedRequest);
        updatedRequest
            .requestFirstName(UPDATED_REQUEST_FIRST_NAME)
            .requestLastName(UPDATED_REQUEST_LAST_NAME)
            .requestEmail(UPDATED_REQUEST_EMAIL)
            .requestPhone(UPDATED_REQUEST_PHONE)
            .requestGetAnalysis(UPDATED_REQUEST_GET_ANALYSIS)
            .requestGetPrice(UPDATED_REQUEST_GET_PRICE)
            .requestPageUrl(UPDATED_REQUEST_PAGE_URL)
            .resourceId(UPDATED_RESOURCE_ID)
            .resourceType(UPDATED_RESOURCE_TYPE)
            .requestType(UPDATED_REQUEST_TYPE)
            .requestMeetingDate(UPDATED_REQUEST_MEETING_DATE)
            .requestQuestion(UPDATED_REQUEST_QUESTION)
            .requestPrice(UPDATED_REQUEST_PRICE);
        RequestDTO requestDTO = requestMapper.toDto(updatedRequest);

        restRequestMockMvc.perform(put("/api/requests")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(requestDTO)))
            .andExpect(status().isOk());

        // Validate the Request in the database
        List<Request> requestList = requestRepository.findAll();
        assertThat(requestList).hasSize(databaseSizeBeforeUpdate);
        Request testRequest = requestList.get(requestList.size() - 1);
        assertThat(testRequest.getRequestFirstName()).isEqualTo(UPDATED_REQUEST_FIRST_NAME);
        assertThat(testRequest.getRequestLastName()).isEqualTo(UPDATED_REQUEST_LAST_NAME);
        assertThat(testRequest.getRequestEmail()).isEqualTo(UPDATED_REQUEST_EMAIL);
        assertThat(testRequest.getRequestPhone()).isEqualTo(UPDATED_REQUEST_PHONE);
        assertThat(testRequest.isRequestGetAnalysis()).isEqualTo(UPDATED_REQUEST_GET_ANALYSIS);
        assertThat(testRequest.isRequestGetPrice()).isEqualTo(UPDATED_REQUEST_GET_PRICE);
        assertThat(testRequest.getRequestPageUrl()).isEqualTo(UPDATED_REQUEST_PAGE_URL);
        assertThat(testRequest.getResourceId()).isEqualTo(UPDATED_RESOURCE_ID);
        assertThat(testRequest.getResourceType()).isEqualTo(UPDATED_RESOURCE_TYPE);
        assertThat(testRequest.getRequestType()).isEqualTo(UPDATED_REQUEST_TYPE);
        assertThat(testRequest.getRequestMeetingDate()).isEqualTo(UPDATED_REQUEST_MEETING_DATE);
        assertThat(testRequest.getRequestQuestion()).isEqualTo(UPDATED_REQUEST_QUESTION);
        assertThat(testRequest.getRequestPrice()).isEqualTo(UPDATED_REQUEST_PRICE);
    }

    @Test
    @Transactional
    public void updateNonExistingRequest() throws Exception {
        int databaseSizeBeforeUpdate = requestRepository.findAll().size();

        // Create the Request
        RequestDTO requestDTO = requestMapper.toDto(request);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restRequestMockMvc.perform(put("/api/requests")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(requestDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Request in the database
        List<Request> requestList = requestRepository.findAll();
        assertThat(requestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRequest() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        int databaseSizeBeforeDelete = requestRepository.findAll().size();

        // Get the request
        restRequestMockMvc.perform(delete("/api/requests/{id}", request.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Request> requestList = requestRepository.findAll();
        assertThat(requestList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Request.class);
        Request request1 = new Request();
        request1.setId(1L);
        Request request2 = new Request();
        request2.setId(request1.getId());
        assertThat(request1).isEqualTo(request2);
        request2.setId(2L);
        assertThat(request1).isNotEqualTo(request2);
        request1.setId(null);
        assertThat(request1).isNotEqualTo(request2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RequestDTO.class);
        RequestDTO requestDTO1 = new RequestDTO();
        requestDTO1.setId(1L);
        RequestDTO requestDTO2 = new RequestDTO();
        assertThat(requestDTO1).isNotEqualTo(requestDTO2);
        requestDTO2.setId(requestDTO1.getId());
        assertThat(requestDTO1).isEqualTo(requestDTO2);
        requestDTO2.setId(2L);
        assertThat(requestDTO1).isNotEqualTo(requestDTO2);
        requestDTO1.setId(null);
        assertThat(requestDTO1).isNotEqualTo(requestDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(requestMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(requestMapper.fromId(null)).isNull();
    }
}
