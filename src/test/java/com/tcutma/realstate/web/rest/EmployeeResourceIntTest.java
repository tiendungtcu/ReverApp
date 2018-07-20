package com.tcutma.realstate.web.rest;

import com.tcutma.realstate.RiverApp;

import com.tcutma.realstate.domain.Employee;
import com.tcutma.realstate.domain.Contact;
import com.tcutma.realstate.domain.Photo;
import com.tcutma.realstate.domain.JobTitle;
import com.tcutma.realstate.domain.Department;
import com.tcutma.realstate.domain.Employee;
import com.tcutma.realstate.repository.EmployeeRepository;
import com.tcutma.realstate.service.EmployeeService;
import com.tcutma.realstate.service.dto.EmployeeDTO;
import com.tcutma.realstate.service.mapper.EmployeeMapper;
import com.tcutma.realstate.web.rest.errors.ExceptionTranslator;
import com.tcutma.realstate.service.dto.EmployeeCriteria;
import com.tcutma.realstate.service.EmployeeQueryService;

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

import com.tcutma.realstate.domain.enumeration.Gender;
/**
 * Test class for the EmployeeResource REST controller.
 *
 * @see EmployeeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RiverApp.class)
public class EmployeeResourceIntTest {

    private static final String DEFAULT_EMPLOYEE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_EMPLOYEE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMPLOYEE_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_EMPLOYEE_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMPLOYEE_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_EMPLOYEE_LAST_NAME = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_EMPLOYEE_DOB = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_EMPLOYEE_DOB = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Gender DEFAULT_EMPLOYEE_SEX = Gender.MALE;
    private static final Gender UPDATED_EMPLOYEE_SEX = Gender.FEMALE;

    private static final String DEFAULT_EMPLOYEE_IDENTITY_CARD = "AAAAAAAAAA";
    private static final String UPDATED_EMPLOYEE_IDENTITY_CARD = "BBBBBBBBBB";

    private static final String DEFAULT_EMPLOYEE_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_EMPLOYEE_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_EMPLOYEE_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMPLOYEE_EMAIL = "BBBBBBBBBB";

    private static final byte[] DEFAULT_EMPLOYEE_AVATAR = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_EMPLOYEE_AVATAR = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_EMPLOYEE_AVATAR_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_EMPLOYEE_AVATAR_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_EMPLOYEE_FACEBOOK = "AAAAAAAAAA";
    private static final String UPDATED_EMPLOYEE_FACEBOOK = "BBBBBBBBBB";

    private static final String DEFAULT_EMPLOYEE_LINKEDIN = "AAAAAAAAAA";
    private static final String UPDATED_EMPLOYEE_LINKEDIN = "BBBBBBBBBB";

    private static final String DEFAULT_EMPLOYEE_INSTAGRAM = "AAAAAAAAAA";
    private static final String UPDATED_EMPLOYEE_INSTAGRAM = "BBBBBBBBBB";

    private static final String DEFAULT_EMPLOYEE_GOOGLE_PLUS = "AAAAAAAAAA";
    private static final String UPDATED_EMPLOYEE_GOOGLE_PLUS = "BBBBBBBBBB";

    private static final String DEFAULT_EMPLOYEE_ZALO = "AAAAAAAAAA";
    private static final String UPDATED_EMPLOYEE_ZALO = "BBBBBBBBBB";

    private static final String DEFAULT_EMPLOYEE_TWITTER = "AAAAAAAAAA";
    private static final String UPDATED_EMPLOYEE_TWITTER = "BBBBBBBBBB";

    private static final String DEFAULT_EMPLOYEE_YOUTUBE = "AAAAAAAAAA";
    private static final String UPDATED_EMPLOYEE_YOUTUBE = "BBBBBBBBBB";

    @Autowired
    private EmployeeRepository employeeRepository;


    @Autowired
    private EmployeeMapper employeeMapper;
    

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeQueryService employeeQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restEmployeeMockMvc;

    private Employee employee;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EmployeeResource employeeResource = new EmployeeResource(employeeService, employeeQueryService);
        this.restEmployeeMockMvc = MockMvcBuilders.standaloneSetup(employeeResource)
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
    public static Employee createEntity(EntityManager em) {
        Employee employee = new Employee()
            .employeeName(DEFAULT_EMPLOYEE_NAME)
            .employeeFirstName(DEFAULT_EMPLOYEE_FIRST_NAME)
            .employeeLastName(DEFAULT_EMPLOYEE_LAST_NAME)
            .employeeDob(DEFAULT_EMPLOYEE_DOB)
            .employeeSex(DEFAULT_EMPLOYEE_SEX)
            .employeeIdentityCard(DEFAULT_EMPLOYEE_IDENTITY_CARD)
            .employeePhone(DEFAULT_EMPLOYEE_PHONE)
            .employeeEmail(DEFAULT_EMPLOYEE_EMAIL)
            .employeeAvatar(DEFAULT_EMPLOYEE_AVATAR)
            .employeeAvatarContentType(DEFAULT_EMPLOYEE_AVATAR_CONTENT_TYPE)
            .employeeFacebook(DEFAULT_EMPLOYEE_FACEBOOK)
            .employeeLinkedin(DEFAULT_EMPLOYEE_LINKEDIN)
            .employeeInstagram(DEFAULT_EMPLOYEE_INSTAGRAM)
            .employeeGooglePlus(DEFAULT_EMPLOYEE_GOOGLE_PLUS)
            .employeeZalo(DEFAULT_EMPLOYEE_ZALO)
            .employeeTwitter(DEFAULT_EMPLOYEE_TWITTER)
            .employeeYoutube(DEFAULT_EMPLOYEE_YOUTUBE);
        return employee;
    }

    @Before
    public void initTest() {
        employee = createEntity(em);
    }

    @Test
    @Transactional
    public void createEmployee() throws Exception {
        int databaseSizeBeforeCreate = employeeRepository.findAll().size();

        // Create the Employee
        EmployeeDTO employeeDTO = employeeMapper.toDto(employee);
        restEmployeeMockMvc.perform(post("/api/employees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(employeeDTO)))
            .andExpect(status().isCreated());

        // Validate the Employee in the database
        List<Employee> employeeList = employeeRepository.findAll();
        assertThat(employeeList).hasSize(databaseSizeBeforeCreate + 1);
        Employee testEmployee = employeeList.get(employeeList.size() - 1);
        assertThat(testEmployee.getEmployeeName()).isEqualTo(DEFAULT_EMPLOYEE_NAME);
        assertThat(testEmployee.getEmployeeFirstName()).isEqualTo(DEFAULT_EMPLOYEE_FIRST_NAME);
        assertThat(testEmployee.getEmployeeLastName()).isEqualTo(DEFAULT_EMPLOYEE_LAST_NAME);
        assertThat(testEmployee.getEmployeeDob()).isEqualTo(DEFAULT_EMPLOYEE_DOB);
        assertThat(testEmployee.getEmployeeSex()).isEqualTo(DEFAULT_EMPLOYEE_SEX);
        assertThat(testEmployee.getEmployeeIdentityCard()).isEqualTo(DEFAULT_EMPLOYEE_IDENTITY_CARD);
        assertThat(testEmployee.getEmployeePhone()).isEqualTo(DEFAULT_EMPLOYEE_PHONE);
        assertThat(testEmployee.getEmployeeEmail()).isEqualTo(DEFAULT_EMPLOYEE_EMAIL);
        assertThat(testEmployee.getEmployeeAvatar()).isEqualTo(DEFAULT_EMPLOYEE_AVATAR);
        assertThat(testEmployee.getEmployeeAvatarContentType()).isEqualTo(DEFAULT_EMPLOYEE_AVATAR_CONTENT_TYPE);
        assertThat(testEmployee.getEmployeeFacebook()).isEqualTo(DEFAULT_EMPLOYEE_FACEBOOK);
        assertThat(testEmployee.getEmployeeLinkedin()).isEqualTo(DEFAULT_EMPLOYEE_LINKEDIN);
        assertThat(testEmployee.getEmployeeInstagram()).isEqualTo(DEFAULT_EMPLOYEE_INSTAGRAM);
        assertThat(testEmployee.getEmployeeGooglePlus()).isEqualTo(DEFAULT_EMPLOYEE_GOOGLE_PLUS);
        assertThat(testEmployee.getEmployeeZalo()).isEqualTo(DEFAULT_EMPLOYEE_ZALO);
        assertThat(testEmployee.getEmployeeTwitter()).isEqualTo(DEFAULT_EMPLOYEE_TWITTER);
        assertThat(testEmployee.getEmployeeYoutube()).isEqualTo(DEFAULT_EMPLOYEE_YOUTUBE);
    }

    @Test
    @Transactional
    public void createEmployeeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = employeeRepository.findAll().size();

        // Create the Employee with an existing ID
        employee.setId(1L);
        EmployeeDTO employeeDTO = employeeMapper.toDto(employee);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEmployeeMockMvc.perform(post("/api/employees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(employeeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Employee in the database
        List<Employee> employeeList = employeeRepository.findAll();
        assertThat(employeeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkEmployeeNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = employeeRepository.findAll().size();
        // set the field null
        employee.setEmployeeName(null);

        // Create the Employee, which fails.
        EmployeeDTO employeeDTO = employeeMapper.toDto(employee);

        restEmployeeMockMvc.perform(post("/api/employees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(employeeDTO)))
            .andExpect(status().isBadRequest());

        List<Employee> employeeList = employeeRepository.findAll();
        assertThat(employeeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEmployeePhoneIsRequired() throws Exception {
        int databaseSizeBeforeTest = employeeRepository.findAll().size();
        // set the field null
        employee.setEmployeePhone(null);

        // Create the Employee, which fails.
        EmployeeDTO employeeDTO = employeeMapper.toDto(employee);

        restEmployeeMockMvc.perform(post("/api/employees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(employeeDTO)))
            .andExpect(status().isBadRequest());

        List<Employee> employeeList = employeeRepository.findAll();
        assertThat(employeeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEmployeeEmailIsRequired() throws Exception {
        int databaseSizeBeforeTest = employeeRepository.findAll().size();
        // set the field null
        employee.setEmployeeEmail(null);

        // Create the Employee, which fails.
        EmployeeDTO employeeDTO = employeeMapper.toDto(employee);

        restEmployeeMockMvc.perform(post("/api/employees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(employeeDTO)))
            .andExpect(status().isBadRequest());

        List<Employee> employeeList = employeeRepository.findAll();
        assertThat(employeeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEmployees() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList
        restEmployeeMockMvc.perform(get("/api/employees?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(employee.getId().intValue())))
            .andExpect(jsonPath("$.[*].employeeName").value(hasItem(DEFAULT_EMPLOYEE_NAME.toString())))
            .andExpect(jsonPath("$.[*].employeeFirstName").value(hasItem(DEFAULT_EMPLOYEE_FIRST_NAME.toString())))
            .andExpect(jsonPath("$.[*].employeeLastName").value(hasItem(DEFAULT_EMPLOYEE_LAST_NAME.toString())))
            .andExpect(jsonPath("$.[*].employeeDob").value(hasItem(sameInstant(DEFAULT_EMPLOYEE_DOB))))
            .andExpect(jsonPath("$.[*].employeeSex").value(hasItem(DEFAULT_EMPLOYEE_SEX.toString())))
            .andExpect(jsonPath("$.[*].employeeIdentityCard").value(hasItem(DEFAULT_EMPLOYEE_IDENTITY_CARD.toString())))
            .andExpect(jsonPath("$.[*].employeePhone").value(hasItem(DEFAULT_EMPLOYEE_PHONE.toString())))
            .andExpect(jsonPath("$.[*].employeeEmail").value(hasItem(DEFAULT_EMPLOYEE_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].employeeAvatarContentType").value(hasItem(DEFAULT_EMPLOYEE_AVATAR_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].employeeAvatar").value(hasItem(Base64Utils.encodeToString(DEFAULT_EMPLOYEE_AVATAR))))
            .andExpect(jsonPath("$.[*].employeeFacebook").value(hasItem(DEFAULT_EMPLOYEE_FACEBOOK.toString())))
            .andExpect(jsonPath("$.[*].employeeLinkedin").value(hasItem(DEFAULT_EMPLOYEE_LINKEDIN.toString())))
            .andExpect(jsonPath("$.[*].employeeInstagram").value(hasItem(DEFAULT_EMPLOYEE_INSTAGRAM.toString())))
            .andExpect(jsonPath("$.[*].employeeGooglePlus").value(hasItem(DEFAULT_EMPLOYEE_GOOGLE_PLUS.toString())))
            .andExpect(jsonPath("$.[*].employeeZalo").value(hasItem(DEFAULT_EMPLOYEE_ZALO.toString())))
            .andExpect(jsonPath("$.[*].employeeTwitter").value(hasItem(DEFAULT_EMPLOYEE_TWITTER.toString())))
            .andExpect(jsonPath("$.[*].employeeYoutube").value(hasItem(DEFAULT_EMPLOYEE_YOUTUBE.toString())));
    }
    

    @Test
    @Transactional
    public void getEmployee() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get the employee
        restEmployeeMockMvc.perform(get("/api/employees/{id}", employee.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(employee.getId().intValue()))
            .andExpect(jsonPath("$.employeeName").value(DEFAULT_EMPLOYEE_NAME.toString()))
            .andExpect(jsonPath("$.employeeFirstName").value(DEFAULT_EMPLOYEE_FIRST_NAME.toString()))
            .andExpect(jsonPath("$.employeeLastName").value(DEFAULT_EMPLOYEE_LAST_NAME.toString()))
            .andExpect(jsonPath("$.employeeDob").value(sameInstant(DEFAULT_EMPLOYEE_DOB)))
            .andExpect(jsonPath("$.employeeSex").value(DEFAULT_EMPLOYEE_SEX.toString()))
            .andExpect(jsonPath("$.employeeIdentityCard").value(DEFAULT_EMPLOYEE_IDENTITY_CARD.toString()))
            .andExpect(jsonPath("$.employeePhone").value(DEFAULT_EMPLOYEE_PHONE.toString()))
            .andExpect(jsonPath("$.employeeEmail").value(DEFAULT_EMPLOYEE_EMAIL.toString()))
            .andExpect(jsonPath("$.employeeAvatarContentType").value(DEFAULT_EMPLOYEE_AVATAR_CONTENT_TYPE))
            .andExpect(jsonPath("$.employeeAvatar").value(Base64Utils.encodeToString(DEFAULT_EMPLOYEE_AVATAR)))
            .andExpect(jsonPath("$.employeeFacebook").value(DEFAULT_EMPLOYEE_FACEBOOK.toString()))
            .andExpect(jsonPath("$.employeeLinkedin").value(DEFAULT_EMPLOYEE_LINKEDIN.toString()))
            .andExpect(jsonPath("$.employeeInstagram").value(DEFAULT_EMPLOYEE_INSTAGRAM.toString()))
            .andExpect(jsonPath("$.employeeGooglePlus").value(DEFAULT_EMPLOYEE_GOOGLE_PLUS.toString()))
            .andExpect(jsonPath("$.employeeZalo").value(DEFAULT_EMPLOYEE_ZALO.toString()))
            .andExpect(jsonPath("$.employeeTwitter").value(DEFAULT_EMPLOYEE_TWITTER.toString()))
            .andExpect(jsonPath("$.employeeYoutube").value(DEFAULT_EMPLOYEE_YOUTUBE.toString()));
    }

    @Test
    @Transactional
    public void getAllEmployeesByEmployeeNameIsEqualToSomething() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where employeeName equals to DEFAULT_EMPLOYEE_NAME
        defaultEmployeeShouldBeFound("employeeName.equals=" + DEFAULT_EMPLOYEE_NAME);

        // Get all the employeeList where employeeName equals to UPDATED_EMPLOYEE_NAME
        defaultEmployeeShouldNotBeFound("employeeName.equals=" + UPDATED_EMPLOYEE_NAME);
    }

    @Test
    @Transactional
    public void getAllEmployeesByEmployeeNameIsInShouldWork() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where employeeName in DEFAULT_EMPLOYEE_NAME or UPDATED_EMPLOYEE_NAME
        defaultEmployeeShouldBeFound("employeeName.in=" + DEFAULT_EMPLOYEE_NAME + "," + UPDATED_EMPLOYEE_NAME);

        // Get all the employeeList where employeeName equals to UPDATED_EMPLOYEE_NAME
        defaultEmployeeShouldNotBeFound("employeeName.in=" + UPDATED_EMPLOYEE_NAME);
    }

    @Test
    @Transactional
    public void getAllEmployeesByEmployeeNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where employeeName is not null
        defaultEmployeeShouldBeFound("employeeName.specified=true");

        // Get all the employeeList where employeeName is null
        defaultEmployeeShouldNotBeFound("employeeName.specified=false");
    }

    @Test
    @Transactional
    public void getAllEmployeesByEmployeeFirstNameIsEqualToSomething() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where employeeFirstName equals to DEFAULT_EMPLOYEE_FIRST_NAME
        defaultEmployeeShouldBeFound("employeeFirstName.equals=" + DEFAULT_EMPLOYEE_FIRST_NAME);

        // Get all the employeeList where employeeFirstName equals to UPDATED_EMPLOYEE_FIRST_NAME
        defaultEmployeeShouldNotBeFound("employeeFirstName.equals=" + UPDATED_EMPLOYEE_FIRST_NAME);
    }

    @Test
    @Transactional
    public void getAllEmployeesByEmployeeFirstNameIsInShouldWork() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where employeeFirstName in DEFAULT_EMPLOYEE_FIRST_NAME or UPDATED_EMPLOYEE_FIRST_NAME
        defaultEmployeeShouldBeFound("employeeFirstName.in=" + DEFAULT_EMPLOYEE_FIRST_NAME + "," + UPDATED_EMPLOYEE_FIRST_NAME);

        // Get all the employeeList where employeeFirstName equals to UPDATED_EMPLOYEE_FIRST_NAME
        defaultEmployeeShouldNotBeFound("employeeFirstName.in=" + UPDATED_EMPLOYEE_FIRST_NAME);
    }

    @Test
    @Transactional
    public void getAllEmployeesByEmployeeFirstNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where employeeFirstName is not null
        defaultEmployeeShouldBeFound("employeeFirstName.specified=true");

        // Get all the employeeList where employeeFirstName is null
        defaultEmployeeShouldNotBeFound("employeeFirstName.specified=false");
    }

    @Test
    @Transactional
    public void getAllEmployeesByEmployeeLastNameIsEqualToSomething() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where employeeLastName equals to DEFAULT_EMPLOYEE_LAST_NAME
        defaultEmployeeShouldBeFound("employeeLastName.equals=" + DEFAULT_EMPLOYEE_LAST_NAME);

        // Get all the employeeList where employeeLastName equals to UPDATED_EMPLOYEE_LAST_NAME
        defaultEmployeeShouldNotBeFound("employeeLastName.equals=" + UPDATED_EMPLOYEE_LAST_NAME);
    }

    @Test
    @Transactional
    public void getAllEmployeesByEmployeeLastNameIsInShouldWork() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where employeeLastName in DEFAULT_EMPLOYEE_LAST_NAME or UPDATED_EMPLOYEE_LAST_NAME
        defaultEmployeeShouldBeFound("employeeLastName.in=" + DEFAULT_EMPLOYEE_LAST_NAME + "," + UPDATED_EMPLOYEE_LAST_NAME);

        // Get all the employeeList where employeeLastName equals to UPDATED_EMPLOYEE_LAST_NAME
        defaultEmployeeShouldNotBeFound("employeeLastName.in=" + UPDATED_EMPLOYEE_LAST_NAME);
    }

    @Test
    @Transactional
    public void getAllEmployeesByEmployeeLastNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where employeeLastName is not null
        defaultEmployeeShouldBeFound("employeeLastName.specified=true");

        // Get all the employeeList where employeeLastName is null
        defaultEmployeeShouldNotBeFound("employeeLastName.specified=false");
    }

    @Test
    @Transactional
    public void getAllEmployeesByEmployeeDobIsEqualToSomething() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where employeeDob equals to DEFAULT_EMPLOYEE_DOB
        defaultEmployeeShouldBeFound("employeeDob.equals=" + DEFAULT_EMPLOYEE_DOB);

        // Get all the employeeList where employeeDob equals to UPDATED_EMPLOYEE_DOB
        defaultEmployeeShouldNotBeFound("employeeDob.equals=" + UPDATED_EMPLOYEE_DOB);
    }

    @Test
    @Transactional
    public void getAllEmployeesByEmployeeDobIsInShouldWork() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where employeeDob in DEFAULT_EMPLOYEE_DOB or UPDATED_EMPLOYEE_DOB
        defaultEmployeeShouldBeFound("employeeDob.in=" + DEFAULT_EMPLOYEE_DOB + "," + UPDATED_EMPLOYEE_DOB);

        // Get all the employeeList where employeeDob equals to UPDATED_EMPLOYEE_DOB
        defaultEmployeeShouldNotBeFound("employeeDob.in=" + UPDATED_EMPLOYEE_DOB);
    }

    @Test
    @Transactional
    public void getAllEmployeesByEmployeeDobIsNullOrNotNull() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where employeeDob is not null
        defaultEmployeeShouldBeFound("employeeDob.specified=true");

        // Get all the employeeList where employeeDob is null
        defaultEmployeeShouldNotBeFound("employeeDob.specified=false");
    }

    @Test
    @Transactional
    public void getAllEmployeesByEmployeeDobIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where employeeDob greater than or equals to DEFAULT_EMPLOYEE_DOB
        defaultEmployeeShouldBeFound("employeeDob.greaterOrEqualThan=" + DEFAULT_EMPLOYEE_DOB);

        // Get all the employeeList where employeeDob greater than or equals to UPDATED_EMPLOYEE_DOB
        defaultEmployeeShouldNotBeFound("employeeDob.greaterOrEqualThan=" + UPDATED_EMPLOYEE_DOB);
    }

    @Test
    @Transactional
    public void getAllEmployeesByEmployeeDobIsLessThanSomething() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where employeeDob less than or equals to DEFAULT_EMPLOYEE_DOB
        defaultEmployeeShouldNotBeFound("employeeDob.lessThan=" + DEFAULT_EMPLOYEE_DOB);

        // Get all the employeeList where employeeDob less than or equals to UPDATED_EMPLOYEE_DOB
        defaultEmployeeShouldBeFound("employeeDob.lessThan=" + UPDATED_EMPLOYEE_DOB);
    }


    @Test
    @Transactional
    public void getAllEmployeesByEmployeeSexIsEqualToSomething() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where employeeSex equals to DEFAULT_EMPLOYEE_SEX
        defaultEmployeeShouldBeFound("employeeSex.equals=" + DEFAULT_EMPLOYEE_SEX);

        // Get all the employeeList where employeeSex equals to UPDATED_EMPLOYEE_SEX
        defaultEmployeeShouldNotBeFound("employeeSex.equals=" + UPDATED_EMPLOYEE_SEX);
    }

    @Test
    @Transactional
    public void getAllEmployeesByEmployeeSexIsInShouldWork() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where employeeSex in DEFAULT_EMPLOYEE_SEX or UPDATED_EMPLOYEE_SEX
        defaultEmployeeShouldBeFound("employeeSex.in=" + DEFAULT_EMPLOYEE_SEX + "," + UPDATED_EMPLOYEE_SEX);

        // Get all the employeeList where employeeSex equals to UPDATED_EMPLOYEE_SEX
        defaultEmployeeShouldNotBeFound("employeeSex.in=" + UPDATED_EMPLOYEE_SEX);
    }

    @Test
    @Transactional
    public void getAllEmployeesByEmployeeSexIsNullOrNotNull() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where employeeSex is not null
        defaultEmployeeShouldBeFound("employeeSex.specified=true");

        // Get all the employeeList where employeeSex is null
        defaultEmployeeShouldNotBeFound("employeeSex.specified=false");
    }

    @Test
    @Transactional
    public void getAllEmployeesByEmployeeIdentityCardIsEqualToSomething() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where employeeIdentityCard equals to DEFAULT_EMPLOYEE_IDENTITY_CARD
        defaultEmployeeShouldBeFound("employeeIdentityCard.equals=" + DEFAULT_EMPLOYEE_IDENTITY_CARD);

        // Get all the employeeList where employeeIdentityCard equals to UPDATED_EMPLOYEE_IDENTITY_CARD
        defaultEmployeeShouldNotBeFound("employeeIdentityCard.equals=" + UPDATED_EMPLOYEE_IDENTITY_CARD);
    }

    @Test
    @Transactional
    public void getAllEmployeesByEmployeeIdentityCardIsInShouldWork() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where employeeIdentityCard in DEFAULT_EMPLOYEE_IDENTITY_CARD or UPDATED_EMPLOYEE_IDENTITY_CARD
        defaultEmployeeShouldBeFound("employeeIdentityCard.in=" + DEFAULT_EMPLOYEE_IDENTITY_CARD + "," + UPDATED_EMPLOYEE_IDENTITY_CARD);

        // Get all the employeeList where employeeIdentityCard equals to UPDATED_EMPLOYEE_IDENTITY_CARD
        defaultEmployeeShouldNotBeFound("employeeIdentityCard.in=" + UPDATED_EMPLOYEE_IDENTITY_CARD);
    }

    @Test
    @Transactional
    public void getAllEmployeesByEmployeeIdentityCardIsNullOrNotNull() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where employeeIdentityCard is not null
        defaultEmployeeShouldBeFound("employeeIdentityCard.specified=true");

        // Get all the employeeList where employeeIdentityCard is null
        defaultEmployeeShouldNotBeFound("employeeIdentityCard.specified=false");
    }

    @Test
    @Transactional
    public void getAllEmployeesByEmployeePhoneIsEqualToSomething() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where employeePhone equals to DEFAULT_EMPLOYEE_PHONE
        defaultEmployeeShouldBeFound("employeePhone.equals=" + DEFAULT_EMPLOYEE_PHONE);

        // Get all the employeeList where employeePhone equals to UPDATED_EMPLOYEE_PHONE
        defaultEmployeeShouldNotBeFound("employeePhone.equals=" + UPDATED_EMPLOYEE_PHONE);
    }

    @Test
    @Transactional
    public void getAllEmployeesByEmployeePhoneIsInShouldWork() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where employeePhone in DEFAULT_EMPLOYEE_PHONE or UPDATED_EMPLOYEE_PHONE
        defaultEmployeeShouldBeFound("employeePhone.in=" + DEFAULT_EMPLOYEE_PHONE + "," + UPDATED_EMPLOYEE_PHONE);

        // Get all the employeeList where employeePhone equals to UPDATED_EMPLOYEE_PHONE
        defaultEmployeeShouldNotBeFound("employeePhone.in=" + UPDATED_EMPLOYEE_PHONE);
    }

    @Test
    @Transactional
    public void getAllEmployeesByEmployeePhoneIsNullOrNotNull() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where employeePhone is not null
        defaultEmployeeShouldBeFound("employeePhone.specified=true");

        // Get all the employeeList where employeePhone is null
        defaultEmployeeShouldNotBeFound("employeePhone.specified=false");
    }

    @Test
    @Transactional
    public void getAllEmployeesByEmployeeEmailIsEqualToSomething() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where employeeEmail equals to DEFAULT_EMPLOYEE_EMAIL
        defaultEmployeeShouldBeFound("employeeEmail.equals=" + DEFAULT_EMPLOYEE_EMAIL);

        // Get all the employeeList where employeeEmail equals to UPDATED_EMPLOYEE_EMAIL
        defaultEmployeeShouldNotBeFound("employeeEmail.equals=" + UPDATED_EMPLOYEE_EMAIL);
    }

    @Test
    @Transactional
    public void getAllEmployeesByEmployeeEmailIsInShouldWork() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where employeeEmail in DEFAULT_EMPLOYEE_EMAIL or UPDATED_EMPLOYEE_EMAIL
        defaultEmployeeShouldBeFound("employeeEmail.in=" + DEFAULT_EMPLOYEE_EMAIL + "," + UPDATED_EMPLOYEE_EMAIL);

        // Get all the employeeList where employeeEmail equals to UPDATED_EMPLOYEE_EMAIL
        defaultEmployeeShouldNotBeFound("employeeEmail.in=" + UPDATED_EMPLOYEE_EMAIL);
    }

    @Test
    @Transactional
    public void getAllEmployeesByEmployeeEmailIsNullOrNotNull() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where employeeEmail is not null
        defaultEmployeeShouldBeFound("employeeEmail.specified=true");

        // Get all the employeeList where employeeEmail is null
        defaultEmployeeShouldNotBeFound("employeeEmail.specified=false");
    }

    @Test
    @Transactional
    public void getAllEmployeesByEmployeeFacebookIsEqualToSomething() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where employeeFacebook equals to DEFAULT_EMPLOYEE_FACEBOOK
        defaultEmployeeShouldBeFound("employeeFacebook.equals=" + DEFAULT_EMPLOYEE_FACEBOOK);

        // Get all the employeeList where employeeFacebook equals to UPDATED_EMPLOYEE_FACEBOOK
        defaultEmployeeShouldNotBeFound("employeeFacebook.equals=" + UPDATED_EMPLOYEE_FACEBOOK);
    }

    @Test
    @Transactional
    public void getAllEmployeesByEmployeeFacebookIsInShouldWork() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where employeeFacebook in DEFAULT_EMPLOYEE_FACEBOOK or UPDATED_EMPLOYEE_FACEBOOK
        defaultEmployeeShouldBeFound("employeeFacebook.in=" + DEFAULT_EMPLOYEE_FACEBOOK + "," + UPDATED_EMPLOYEE_FACEBOOK);

        // Get all the employeeList where employeeFacebook equals to UPDATED_EMPLOYEE_FACEBOOK
        defaultEmployeeShouldNotBeFound("employeeFacebook.in=" + UPDATED_EMPLOYEE_FACEBOOK);
    }

    @Test
    @Transactional
    public void getAllEmployeesByEmployeeFacebookIsNullOrNotNull() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where employeeFacebook is not null
        defaultEmployeeShouldBeFound("employeeFacebook.specified=true");

        // Get all the employeeList where employeeFacebook is null
        defaultEmployeeShouldNotBeFound("employeeFacebook.specified=false");
    }

    @Test
    @Transactional
    public void getAllEmployeesByEmployeeLinkedinIsEqualToSomething() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where employeeLinkedin equals to DEFAULT_EMPLOYEE_LINKEDIN
        defaultEmployeeShouldBeFound("employeeLinkedin.equals=" + DEFAULT_EMPLOYEE_LINKEDIN);

        // Get all the employeeList where employeeLinkedin equals to UPDATED_EMPLOYEE_LINKEDIN
        defaultEmployeeShouldNotBeFound("employeeLinkedin.equals=" + UPDATED_EMPLOYEE_LINKEDIN);
    }

    @Test
    @Transactional
    public void getAllEmployeesByEmployeeLinkedinIsInShouldWork() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where employeeLinkedin in DEFAULT_EMPLOYEE_LINKEDIN or UPDATED_EMPLOYEE_LINKEDIN
        defaultEmployeeShouldBeFound("employeeLinkedin.in=" + DEFAULT_EMPLOYEE_LINKEDIN + "," + UPDATED_EMPLOYEE_LINKEDIN);

        // Get all the employeeList where employeeLinkedin equals to UPDATED_EMPLOYEE_LINKEDIN
        defaultEmployeeShouldNotBeFound("employeeLinkedin.in=" + UPDATED_EMPLOYEE_LINKEDIN);
    }

    @Test
    @Transactional
    public void getAllEmployeesByEmployeeLinkedinIsNullOrNotNull() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where employeeLinkedin is not null
        defaultEmployeeShouldBeFound("employeeLinkedin.specified=true");

        // Get all the employeeList where employeeLinkedin is null
        defaultEmployeeShouldNotBeFound("employeeLinkedin.specified=false");
    }

    @Test
    @Transactional
    public void getAllEmployeesByEmployeeInstagramIsEqualToSomething() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where employeeInstagram equals to DEFAULT_EMPLOYEE_INSTAGRAM
        defaultEmployeeShouldBeFound("employeeInstagram.equals=" + DEFAULT_EMPLOYEE_INSTAGRAM);

        // Get all the employeeList where employeeInstagram equals to UPDATED_EMPLOYEE_INSTAGRAM
        defaultEmployeeShouldNotBeFound("employeeInstagram.equals=" + UPDATED_EMPLOYEE_INSTAGRAM);
    }

    @Test
    @Transactional
    public void getAllEmployeesByEmployeeInstagramIsInShouldWork() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where employeeInstagram in DEFAULT_EMPLOYEE_INSTAGRAM or UPDATED_EMPLOYEE_INSTAGRAM
        defaultEmployeeShouldBeFound("employeeInstagram.in=" + DEFAULT_EMPLOYEE_INSTAGRAM + "," + UPDATED_EMPLOYEE_INSTAGRAM);

        // Get all the employeeList where employeeInstagram equals to UPDATED_EMPLOYEE_INSTAGRAM
        defaultEmployeeShouldNotBeFound("employeeInstagram.in=" + UPDATED_EMPLOYEE_INSTAGRAM);
    }

    @Test
    @Transactional
    public void getAllEmployeesByEmployeeInstagramIsNullOrNotNull() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where employeeInstagram is not null
        defaultEmployeeShouldBeFound("employeeInstagram.specified=true");

        // Get all the employeeList where employeeInstagram is null
        defaultEmployeeShouldNotBeFound("employeeInstagram.specified=false");
    }

    @Test
    @Transactional
    public void getAllEmployeesByEmployeeGooglePlusIsEqualToSomething() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where employeeGooglePlus equals to DEFAULT_EMPLOYEE_GOOGLE_PLUS
        defaultEmployeeShouldBeFound("employeeGooglePlus.equals=" + DEFAULT_EMPLOYEE_GOOGLE_PLUS);

        // Get all the employeeList where employeeGooglePlus equals to UPDATED_EMPLOYEE_GOOGLE_PLUS
        defaultEmployeeShouldNotBeFound("employeeGooglePlus.equals=" + UPDATED_EMPLOYEE_GOOGLE_PLUS);
    }

    @Test
    @Transactional
    public void getAllEmployeesByEmployeeGooglePlusIsInShouldWork() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where employeeGooglePlus in DEFAULT_EMPLOYEE_GOOGLE_PLUS or UPDATED_EMPLOYEE_GOOGLE_PLUS
        defaultEmployeeShouldBeFound("employeeGooglePlus.in=" + DEFAULT_EMPLOYEE_GOOGLE_PLUS + "," + UPDATED_EMPLOYEE_GOOGLE_PLUS);

        // Get all the employeeList where employeeGooglePlus equals to UPDATED_EMPLOYEE_GOOGLE_PLUS
        defaultEmployeeShouldNotBeFound("employeeGooglePlus.in=" + UPDATED_EMPLOYEE_GOOGLE_PLUS);
    }

    @Test
    @Transactional
    public void getAllEmployeesByEmployeeGooglePlusIsNullOrNotNull() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where employeeGooglePlus is not null
        defaultEmployeeShouldBeFound("employeeGooglePlus.specified=true");

        // Get all the employeeList where employeeGooglePlus is null
        defaultEmployeeShouldNotBeFound("employeeGooglePlus.specified=false");
    }

    @Test
    @Transactional
    public void getAllEmployeesByEmployeeZaloIsEqualToSomething() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where employeeZalo equals to DEFAULT_EMPLOYEE_ZALO
        defaultEmployeeShouldBeFound("employeeZalo.equals=" + DEFAULT_EMPLOYEE_ZALO);

        // Get all the employeeList where employeeZalo equals to UPDATED_EMPLOYEE_ZALO
        defaultEmployeeShouldNotBeFound("employeeZalo.equals=" + UPDATED_EMPLOYEE_ZALO);
    }

    @Test
    @Transactional
    public void getAllEmployeesByEmployeeZaloIsInShouldWork() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where employeeZalo in DEFAULT_EMPLOYEE_ZALO or UPDATED_EMPLOYEE_ZALO
        defaultEmployeeShouldBeFound("employeeZalo.in=" + DEFAULT_EMPLOYEE_ZALO + "," + UPDATED_EMPLOYEE_ZALO);

        // Get all the employeeList where employeeZalo equals to UPDATED_EMPLOYEE_ZALO
        defaultEmployeeShouldNotBeFound("employeeZalo.in=" + UPDATED_EMPLOYEE_ZALO);
    }

    @Test
    @Transactional
    public void getAllEmployeesByEmployeeZaloIsNullOrNotNull() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where employeeZalo is not null
        defaultEmployeeShouldBeFound("employeeZalo.specified=true");

        // Get all the employeeList where employeeZalo is null
        defaultEmployeeShouldNotBeFound("employeeZalo.specified=false");
    }

    @Test
    @Transactional
    public void getAllEmployeesByEmployeeTwitterIsEqualToSomething() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where employeeTwitter equals to DEFAULT_EMPLOYEE_TWITTER
        defaultEmployeeShouldBeFound("employeeTwitter.equals=" + DEFAULT_EMPLOYEE_TWITTER);

        // Get all the employeeList where employeeTwitter equals to UPDATED_EMPLOYEE_TWITTER
        defaultEmployeeShouldNotBeFound("employeeTwitter.equals=" + UPDATED_EMPLOYEE_TWITTER);
    }

    @Test
    @Transactional
    public void getAllEmployeesByEmployeeTwitterIsInShouldWork() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where employeeTwitter in DEFAULT_EMPLOYEE_TWITTER or UPDATED_EMPLOYEE_TWITTER
        defaultEmployeeShouldBeFound("employeeTwitter.in=" + DEFAULT_EMPLOYEE_TWITTER + "," + UPDATED_EMPLOYEE_TWITTER);

        // Get all the employeeList where employeeTwitter equals to UPDATED_EMPLOYEE_TWITTER
        defaultEmployeeShouldNotBeFound("employeeTwitter.in=" + UPDATED_EMPLOYEE_TWITTER);
    }

    @Test
    @Transactional
    public void getAllEmployeesByEmployeeTwitterIsNullOrNotNull() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where employeeTwitter is not null
        defaultEmployeeShouldBeFound("employeeTwitter.specified=true");

        // Get all the employeeList where employeeTwitter is null
        defaultEmployeeShouldNotBeFound("employeeTwitter.specified=false");
    }

    @Test
    @Transactional
    public void getAllEmployeesByEmployeeYoutubeIsEqualToSomething() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where employeeYoutube equals to DEFAULT_EMPLOYEE_YOUTUBE
        defaultEmployeeShouldBeFound("employeeYoutube.equals=" + DEFAULT_EMPLOYEE_YOUTUBE);

        // Get all the employeeList where employeeYoutube equals to UPDATED_EMPLOYEE_YOUTUBE
        defaultEmployeeShouldNotBeFound("employeeYoutube.equals=" + UPDATED_EMPLOYEE_YOUTUBE);
    }

    @Test
    @Transactional
    public void getAllEmployeesByEmployeeYoutubeIsInShouldWork() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where employeeYoutube in DEFAULT_EMPLOYEE_YOUTUBE or UPDATED_EMPLOYEE_YOUTUBE
        defaultEmployeeShouldBeFound("employeeYoutube.in=" + DEFAULT_EMPLOYEE_YOUTUBE + "," + UPDATED_EMPLOYEE_YOUTUBE);

        // Get all the employeeList where employeeYoutube equals to UPDATED_EMPLOYEE_YOUTUBE
        defaultEmployeeShouldNotBeFound("employeeYoutube.in=" + UPDATED_EMPLOYEE_YOUTUBE);
    }

    @Test
    @Transactional
    public void getAllEmployeesByEmployeeYoutubeIsNullOrNotNull() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList where employeeYoutube is not null
        defaultEmployeeShouldBeFound("employeeYoutube.specified=true");

        // Get all the employeeList where employeeYoutube is null
        defaultEmployeeShouldNotBeFound("employeeYoutube.specified=false");
    }

    @Test
    @Transactional
    public void getAllEmployeesByContactIsEqualToSomething() throws Exception {
        // Initialize the database
        Contact contact = ContactResourceIntTest.createEntity(em);
        em.persist(contact);
        em.flush();
        employee.setContact(contact);
        employeeRepository.saveAndFlush(employee);
        Long contactId = contact.getId();

        // Get all the employeeList where contact equals to contactId
        defaultEmployeeShouldBeFound("contactId.equals=" + contactId);

        // Get all the employeeList where contact equals to contactId + 1
        defaultEmployeeShouldNotBeFound("contactId.equals=" + (contactId + 1));
    }


    @Test
    @Transactional
    public void getAllEmployeesByPhotoIsEqualToSomething() throws Exception {
        // Initialize the database
        Photo photo = PhotoResourceIntTest.createEntity(em);
        em.persist(photo);
        em.flush();
        employee.setPhoto(photo);
        employeeRepository.saveAndFlush(employee);
        Long photoId = photo.getId();

        // Get all the employeeList where photo equals to photoId
        defaultEmployeeShouldBeFound("photoId.equals=" + photoId);

        // Get all the employeeList where photo equals to photoId + 1
        defaultEmployeeShouldNotBeFound("photoId.equals=" + (photoId + 1));
    }


    @Test
    @Transactional
    public void getAllEmployeesByJobtitleIsEqualToSomething() throws Exception {
        // Initialize the database
        JobTitle jobtitle = JobTitleResourceIntTest.createEntity(em);
        em.persist(jobtitle);
        em.flush();
        employee.setJobtitle(jobtitle);
        employeeRepository.saveAndFlush(employee);
        Long jobtitleId = jobtitle.getId();

        // Get all the employeeList where jobtitle equals to jobtitleId
        defaultEmployeeShouldBeFound("jobtitleId.equals=" + jobtitleId);

        // Get all the employeeList where jobtitle equals to jobtitleId + 1
        defaultEmployeeShouldNotBeFound("jobtitleId.equals=" + (jobtitleId + 1));
    }


    @Test
    @Transactional
    public void getAllEmployeesByDepartmentIsEqualToSomething() throws Exception {
        // Initialize the database
        Department department = DepartmentResourceIntTest.createEntity(em);
        em.persist(department);
        em.flush();
        employee.setDepartment(department);
        employeeRepository.saveAndFlush(employee);
        Long departmentId = department.getId();

        // Get all the employeeList where department equals to departmentId
        defaultEmployeeShouldBeFound("departmentId.equals=" + departmentId);

        // Get all the employeeList where department equals to departmentId + 1
        defaultEmployeeShouldNotBeFound("departmentId.equals=" + (departmentId + 1));
    }


    @Test
    @Transactional
    public void getAllEmployeesByManagerIsEqualToSomething() throws Exception {
        // Initialize the database
        Employee manager = EmployeeResourceIntTest.createEntity(em);
        em.persist(manager);
        em.flush();
        employee.setManager(manager);
        employeeRepository.saveAndFlush(employee);
        Long managerId = manager.getId();

        // Get all the employeeList where manager equals to managerId
        defaultEmployeeShouldBeFound("managerId.equals=" + managerId);

        // Get all the employeeList where manager equals to managerId + 1
        defaultEmployeeShouldNotBeFound("managerId.equals=" + (managerId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultEmployeeShouldBeFound(String filter) throws Exception {
        restEmployeeMockMvc.perform(get("/api/employees?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(employee.getId().intValue())))
            .andExpect(jsonPath("$.[*].employeeName").value(hasItem(DEFAULT_EMPLOYEE_NAME.toString())))
            .andExpect(jsonPath("$.[*].employeeFirstName").value(hasItem(DEFAULT_EMPLOYEE_FIRST_NAME.toString())))
            .andExpect(jsonPath("$.[*].employeeLastName").value(hasItem(DEFAULT_EMPLOYEE_LAST_NAME.toString())))
            .andExpect(jsonPath("$.[*].employeeDob").value(hasItem(sameInstant(DEFAULT_EMPLOYEE_DOB))))
            .andExpect(jsonPath("$.[*].employeeSex").value(hasItem(DEFAULT_EMPLOYEE_SEX.toString())))
            .andExpect(jsonPath("$.[*].employeeIdentityCard").value(hasItem(DEFAULT_EMPLOYEE_IDENTITY_CARD.toString())))
            .andExpect(jsonPath("$.[*].employeePhone").value(hasItem(DEFAULT_EMPLOYEE_PHONE.toString())))
            .andExpect(jsonPath("$.[*].employeeEmail").value(hasItem(DEFAULT_EMPLOYEE_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].employeeAvatarContentType").value(hasItem(DEFAULT_EMPLOYEE_AVATAR_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].employeeAvatar").value(hasItem(Base64Utils.encodeToString(DEFAULT_EMPLOYEE_AVATAR))))
            .andExpect(jsonPath("$.[*].employeeFacebook").value(hasItem(DEFAULT_EMPLOYEE_FACEBOOK.toString())))
            .andExpect(jsonPath("$.[*].employeeLinkedin").value(hasItem(DEFAULT_EMPLOYEE_LINKEDIN.toString())))
            .andExpect(jsonPath("$.[*].employeeInstagram").value(hasItem(DEFAULT_EMPLOYEE_INSTAGRAM.toString())))
            .andExpect(jsonPath("$.[*].employeeGooglePlus").value(hasItem(DEFAULT_EMPLOYEE_GOOGLE_PLUS.toString())))
            .andExpect(jsonPath("$.[*].employeeZalo").value(hasItem(DEFAULT_EMPLOYEE_ZALO.toString())))
            .andExpect(jsonPath("$.[*].employeeTwitter").value(hasItem(DEFAULT_EMPLOYEE_TWITTER.toString())))
            .andExpect(jsonPath("$.[*].employeeYoutube").value(hasItem(DEFAULT_EMPLOYEE_YOUTUBE.toString())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultEmployeeShouldNotBeFound(String filter) throws Exception {
        restEmployeeMockMvc.perform(get("/api/employees?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @Transactional
    public void getNonExistingEmployee() throws Exception {
        // Get the employee
        restEmployeeMockMvc.perform(get("/api/employees/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEmployee() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        int databaseSizeBeforeUpdate = employeeRepository.findAll().size();

        // Update the employee
        Employee updatedEmployee = employeeRepository.findById(employee.getId()).get();
        // Disconnect from session so that the updates on updatedEmployee are not directly saved in db
        em.detach(updatedEmployee);
        updatedEmployee
            .employeeName(UPDATED_EMPLOYEE_NAME)
            .employeeFirstName(UPDATED_EMPLOYEE_FIRST_NAME)
            .employeeLastName(UPDATED_EMPLOYEE_LAST_NAME)
            .employeeDob(UPDATED_EMPLOYEE_DOB)
            .employeeSex(UPDATED_EMPLOYEE_SEX)
            .employeeIdentityCard(UPDATED_EMPLOYEE_IDENTITY_CARD)
            .employeePhone(UPDATED_EMPLOYEE_PHONE)
            .employeeEmail(UPDATED_EMPLOYEE_EMAIL)
            .employeeAvatar(UPDATED_EMPLOYEE_AVATAR)
            .employeeAvatarContentType(UPDATED_EMPLOYEE_AVATAR_CONTENT_TYPE)
            .employeeFacebook(UPDATED_EMPLOYEE_FACEBOOK)
            .employeeLinkedin(UPDATED_EMPLOYEE_LINKEDIN)
            .employeeInstagram(UPDATED_EMPLOYEE_INSTAGRAM)
            .employeeGooglePlus(UPDATED_EMPLOYEE_GOOGLE_PLUS)
            .employeeZalo(UPDATED_EMPLOYEE_ZALO)
            .employeeTwitter(UPDATED_EMPLOYEE_TWITTER)
            .employeeYoutube(UPDATED_EMPLOYEE_YOUTUBE);
        EmployeeDTO employeeDTO = employeeMapper.toDto(updatedEmployee);

        restEmployeeMockMvc.perform(put("/api/employees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(employeeDTO)))
            .andExpect(status().isOk());

        // Validate the Employee in the database
        List<Employee> employeeList = employeeRepository.findAll();
        assertThat(employeeList).hasSize(databaseSizeBeforeUpdate);
        Employee testEmployee = employeeList.get(employeeList.size() - 1);
        assertThat(testEmployee.getEmployeeName()).isEqualTo(UPDATED_EMPLOYEE_NAME);
        assertThat(testEmployee.getEmployeeFirstName()).isEqualTo(UPDATED_EMPLOYEE_FIRST_NAME);
        assertThat(testEmployee.getEmployeeLastName()).isEqualTo(UPDATED_EMPLOYEE_LAST_NAME);
        assertThat(testEmployee.getEmployeeDob()).isEqualTo(UPDATED_EMPLOYEE_DOB);
        assertThat(testEmployee.getEmployeeSex()).isEqualTo(UPDATED_EMPLOYEE_SEX);
        assertThat(testEmployee.getEmployeeIdentityCard()).isEqualTo(UPDATED_EMPLOYEE_IDENTITY_CARD);
        assertThat(testEmployee.getEmployeePhone()).isEqualTo(UPDATED_EMPLOYEE_PHONE);
        assertThat(testEmployee.getEmployeeEmail()).isEqualTo(UPDATED_EMPLOYEE_EMAIL);
        assertThat(testEmployee.getEmployeeAvatar()).isEqualTo(UPDATED_EMPLOYEE_AVATAR);
        assertThat(testEmployee.getEmployeeAvatarContentType()).isEqualTo(UPDATED_EMPLOYEE_AVATAR_CONTENT_TYPE);
        assertThat(testEmployee.getEmployeeFacebook()).isEqualTo(UPDATED_EMPLOYEE_FACEBOOK);
        assertThat(testEmployee.getEmployeeLinkedin()).isEqualTo(UPDATED_EMPLOYEE_LINKEDIN);
        assertThat(testEmployee.getEmployeeInstagram()).isEqualTo(UPDATED_EMPLOYEE_INSTAGRAM);
        assertThat(testEmployee.getEmployeeGooglePlus()).isEqualTo(UPDATED_EMPLOYEE_GOOGLE_PLUS);
        assertThat(testEmployee.getEmployeeZalo()).isEqualTo(UPDATED_EMPLOYEE_ZALO);
        assertThat(testEmployee.getEmployeeTwitter()).isEqualTo(UPDATED_EMPLOYEE_TWITTER);
        assertThat(testEmployee.getEmployeeYoutube()).isEqualTo(UPDATED_EMPLOYEE_YOUTUBE);
    }

    @Test
    @Transactional
    public void updateNonExistingEmployee() throws Exception {
        int databaseSizeBeforeUpdate = employeeRepository.findAll().size();

        // Create the Employee
        EmployeeDTO employeeDTO = employeeMapper.toDto(employee);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restEmployeeMockMvc.perform(put("/api/employees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(employeeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Employee in the database
        List<Employee> employeeList = employeeRepository.findAll();
        assertThat(employeeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEmployee() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        int databaseSizeBeforeDelete = employeeRepository.findAll().size();

        // Get the employee
        restEmployeeMockMvc.perform(delete("/api/employees/{id}", employee.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Employee> employeeList = employeeRepository.findAll();
        assertThat(employeeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Employee.class);
        Employee employee1 = new Employee();
        employee1.setId(1L);
        Employee employee2 = new Employee();
        employee2.setId(employee1.getId());
        assertThat(employee1).isEqualTo(employee2);
        employee2.setId(2L);
        assertThat(employee1).isNotEqualTo(employee2);
        employee1.setId(null);
        assertThat(employee1).isNotEqualTo(employee2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EmployeeDTO.class);
        EmployeeDTO employeeDTO1 = new EmployeeDTO();
        employeeDTO1.setId(1L);
        EmployeeDTO employeeDTO2 = new EmployeeDTO();
        assertThat(employeeDTO1).isNotEqualTo(employeeDTO2);
        employeeDTO2.setId(employeeDTO1.getId());
        assertThat(employeeDTO1).isEqualTo(employeeDTO2);
        employeeDTO2.setId(2L);
        assertThat(employeeDTO1).isNotEqualTo(employeeDTO2);
        employeeDTO1.setId(null);
        assertThat(employeeDTO1).isNotEqualTo(employeeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(employeeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(employeeMapper.fromId(null)).isNull();
    }
}
