package com.tcutma.realstate.web.rest;

import com.tcutma.realstate.RiverApp;

import com.tcutma.realstate.domain.Employee;
import com.tcutma.realstate.domain.User;
import com.tcutma.realstate.domain.Department;
import com.tcutma.realstate.domain.JobTitle;
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

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;


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

    private static final String DEFAULT_EMPLOYEE_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_EMPLOYEE_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMPLOYEE_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_EMPLOYEE_LAST_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_EMPLOYEE_DOB = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_EMPLOYEE_DOB = LocalDate.now(ZoneId.systemDefault());

    private static final Gender DEFAULT_EMPLOYEE_SEX = Gender.MALE;
    private static final Gender UPDATED_EMPLOYEE_SEX = Gender.FEMALE;

    private static final String DEFAULT_EMPLOYEE_IDENTITY_CARD = "AAAAAAAAAA";
    private static final String UPDATED_EMPLOYEE_IDENTITY_CARD = "BBBBBBBBBB";

    private static final String DEFAULT_EMPLOYEE_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_EMPLOYEE_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_EMPLOYEE_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMPLOYEE_EMAIL = "BBBBBBBBBB";

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
            .employeeFirstName(DEFAULT_EMPLOYEE_FIRST_NAME)
            .employeeLastName(DEFAULT_EMPLOYEE_LAST_NAME)
            .employeeDob(DEFAULT_EMPLOYEE_DOB)
            .employeeSex(DEFAULT_EMPLOYEE_SEX)
            .employeeIdentityCard(DEFAULT_EMPLOYEE_IDENTITY_CARD)
            .employeePhone(DEFAULT_EMPLOYEE_PHONE)
            .employeeEmail(DEFAULT_EMPLOYEE_EMAIL);
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
        assertThat(testEmployee.getEmployeeFirstName()).isEqualTo(DEFAULT_EMPLOYEE_FIRST_NAME);
        assertThat(testEmployee.getEmployeeLastName()).isEqualTo(DEFAULT_EMPLOYEE_LAST_NAME);
        assertThat(testEmployee.getEmployeeDob()).isEqualTo(DEFAULT_EMPLOYEE_DOB);
        assertThat(testEmployee.getEmployeeSex()).isEqualTo(DEFAULT_EMPLOYEE_SEX);
        assertThat(testEmployee.getEmployeeIdentityCard()).isEqualTo(DEFAULT_EMPLOYEE_IDENTITY_CARD);
        assertThat(testEmployee.getEmployeePhone()).isEqualTo(DEFAULT_EMPLOYEE_PHONE);
        assertThat(testEmployee.getEmployeeEmail()).isEqualTo(DEFAULT_EMPLOYEE_EMAIL);
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
    public void checkEmployeeFirstNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = employeeRepository.findAll().size();
        // set the field null
        employee.setEmployeeFirstName(null);

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
    public void checkEmployeeLastNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = employeeRepository.findAll().size();
        // set the field null
        employee.setEmployeeLastName(null);

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
            .andExpect(jsonPath("$.[*].employeeFirstName").value(hasItem(DEFAULT_EMPLOYEE_FIRST_NAME.toString())))
            .andExpect(jsonPath("$.[*].employeeLastName").value(hasItem(DEFAULT_EMPLOYEE_LAST_NAME.toString())))
            .andExpect(jsonPath("$.[*].employeeDob").value(hasItem(DEFAULT_EMPLOYEE_DOB.toString())))
            .andExpect(jsonPath("$.[*].employeeSex").value(hasItem(DEFAULT_EMPLOYEE_SEX.toString())))
            .andExpect(jsonPath("$.[*].employeeIdentityCard").value(hasItem(DEFAULT_EMPLOYEE_IDENTITY_CARD.toString())))
            .andExpect(jsonPath("$.[*].employeePhone").value(hasItem(DEFAULT_EMPLOYEE_PHONE.toString())))
            .andExpect(jsonPath("$.[*].employeeEmail").value(hasItem(DEFAULT_EMPLOYEE_EMAIL.toString())));
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
            .andExpect(jsonPath("$.employeeFirstName").value(DEFAULT_EMPLOYEE_FIRST_NAME.toString()))
            .andExpect(jsonPath("$.employeeLastName").value(DEFAULT_EMPLOYEE_LAST_NAME.toString()))
            .andExpect(jsonPath("$.employeeDob").value(DEFAULT_EMPLOYEE_DOB.toString()))
            .andExpect(jsonPath("$.employeeSex").value(DEFAULT_EMPLOYEE_SEX.toString()))
            .andExpect(jsonPath("$.employeeIdentityCard").value(DEFAULT_EMPLOYEE_IDENTITY_CARD.toString()))
            .andExpect(jsonPath("$.employeePhone").value(DEFAULT_EMPLOYEE_PHONE.toString()))
            .andExpect(jsonPath("$.employeeEmail").value(DEFAULT_EMPLOYEE_EMAIL.toString()));
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
    public void getAllEmployeesByAccountIsEqualToSomething() throws Exception {
        // Initialize the database
        User account = UserResourceIntTest.createEntity(em);
        em.persist(account);
        em.flush();
        employee.setAccount(account);
        employeeRepository.saveAndFlush(employee);
        Long accountId = account.getId();

        // Get all the employeeList where account equals to accountId
        defaultEmployeeShouldBeFound("accountId.equals=" + accountId);

        // Get all the employeeList where account equals to accountId + 1
        defaultEmployeeShouldNotBeFound("accountId.equals=" + (accountId + 1));
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

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultEmployeeShouldBeFound(String filter) throws Exception {
        restEmployeeMockMvc.perform(get("/api/employees?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(employee.getId().intValue())))
            .andExpect(jsonPath("$.[*].employeeFirstName").value(hasItem(DEFAULT_EMPLOYEE_FIRST_NAME.toString())))
            .andExpect(jsonPath("$.[*].employeeLastName").value(hasItem(DEFAULT_EMPLOYEE_LAST_NAME.toString())))
            .andExpect(jsonPath("$.[*].employeeDob").value(hasItem(DEFAULT_EMPLOYEE_DOB.toString())))
            .andExpect(jsonPath("$.[*].employeeSex").value(hasItem(DEFAULT_EMPLOYEE_SEX.toString())))
            .andExpect(jsonPath("$.[*].employeeIdentityCard").value(hasItem(DEFAULT_EMPLOYEE_IDENTITY_CARD.toString())))
            .andExpect(jsonPath("$.[*].employeePhone").value(hasItem(DEFAULT_EMPLOYEE_PHONE.toString())))
            .andExpect(jsonPath("$.[*].employeeEmail").value(hasItem(DEFAULT_EMPLOYEE_EMAIL.toString())));
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
            .employeeFirstName(UPDATED_EMPLOYEE_FIRST_NAME)
            .employeeLastName(UPDATED_EMPLOYEE_LAST_NAME)
            .employeeDob(UPDATED_EMPLOYEE_DOB)
            .employeeSex(UPDATED_EMPLOYEE_SEX)
            .employeeIdentityCard(UPDATED_EMPLOYEE_IDENTITY_CARD)
            .employeePhone(UPDATED_EMPLOYEE_PHONE)
            .employeeEmail(UPDATED_EMPLOYEE_EMAIL);
        EmployeeDTO employeeDTO = employeeMapper.toDto(updatedEmployee);

        restEmployeeMockMvc.perform(put("/api/employees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(employeeDTO)))
            .andExpect(status().isOk());

        // Validate the Employee in the database
        List<Employee> employeeList = employeeRepository.findAll();
        assertThat(employeeList).hasSize(databaseSizeBeforeUpdate);
        Employee testEmployee = employeeList.get(employeeList.size() - 1);
        assertThat(testEmployee.getEmployeeFirstName()).isEqualTo(UPDATED_EMPLOYEE_FIRST_NAME);
        assertThat(testEmployee.getEmployeeLastName()).isEqualTo(UPDATED_EMPLOYEE_LAST_NAME);
        assertThat(testEmployee.getEmployeeDob()).isEqualTo(UPDATED_EMPLOYEE_DOB);
        assertThat(testEmployee.getEmployeeSex()).isEqualTo(UPDATED_EMPLOYEE_SEX);
        assertThat(testEmployee.getEmployeeIdentityCard()).isEqualTo(UPDATED_EMPLOYEE_IDENTITY_CARD);
        assertThat(testEmployee.getEmployeePhone()).isEqualTo(UPDATED_EMPLOYEE_PHONE);
        assertThat(testEmployee.getEmployeeEmail()).isEqualTo(UPDATED_EMPLOYEE_EMAIL);
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
