package com.tcutma.realstate.web.rest;

import com.tcutma.realstate.RiverApp;

import com.tcutma.realstate.domain.Department;
import com.tcutma.realstate.repository.DepartmentRepository;
import com.tcutma.realstate.service.DepartmentService;
import com.tcutma.realstate.service.dto.DepartmentDTO;
import com.tcutma.realstate.service.mapper.DepartmentMapper;
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
 * Test class for the DepartmentResource REST controller.
 *
 * @see DepartmentResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RiverApp.class)
public class DepartmentResourceIntTest {

    private static final String DEFAULT_DEPARTMENT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DEPARTMENT_NAME = "BBBBBBBBBB";

    private static final byte[] DEFAULT_DEPARTMENT_PHOTO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_DEPARTMENT_PHOTO = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_DEPARTMENT_PHOTO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_DEPARTMENT_PHOTO_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_DEPARTMENT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_DEPARTMENT_PHONE = "BBBBBBBBBB";

    @Autowired
    private DepartmentRepository departmentRepository;


    @Autowired
    private DepartmentMapper departmentMapper;
    

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restDepartmentMockMvc;

    private Department department;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DepartmentResource departmentResource = new DepartmentResource(departmentService);
        this.restDepartmentMockMvc = MockMvcBuilders.standaloneSetup(departmentResource)
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
    public static Department createEntity(EntityManager em) {
        Department department = new Department()
            .departmentName(DEFAULT_DEPARTMENT_NAME)
            .departmentPhoto(DEFAULT_DEPARTMENT_PHOTO)
            .departmentPhotoContentType(DEFAULT_DEPARTMENT_PHOTO_CONTENT_TYPE)
            .departmentPhone(DEFAULT_DEPARTMENT_PHONE);
        return department;
    }

    @Before
    public void initTest() {
        department = createEntity(em);
    }

    @Test
    @Transactional
    public void createDepartment() throws Exception {
        int databaseSizeBeforeCreate = departmentRepository.findAll().size();

        // Create the Department
        DepartmentDTO departmentDTO = departmentMapper.toDto(department);
        restDepartmentMockMvc.perform(post("/api/departments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(departmentDTO)))
            .andExpect(status().isCreated());

        // Validate the Department in the database
        List<Department> departmentList = departmentRepository.findAll();
        assertThat(departmentList).hasSize(databaseSizeBeforeCreate + 1);
        Department testDepartment = departmentList.get(departmentList.size() - 1);
        assertThat(testDepartment.getDepartmentName()).isEqualTo(DEFAULT_DEPARTMENT_NAME);
        assertThat(testDepartment.getDepartmentPhoto()).isEqualTo(DEFAULT_DEPARTMENT_PHOTO);
        assertThat(testDepartment.getDepartmentPhotoContentType()).isEqualTo(DEFAULT_DEPARTMENT_PHOTO_CONTENT_TYPE);
        assertThat(testDepartment.getDepartmentPhone()).isEqualTo(DEFAULT_DEPARTMENT_PHONE);
    }

    @Test
    @Transactional
    public void createDepartmentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = departmentRepository.findAll().size();

        // Create the Department with an existing ID
        department.setId(1L);
        DepartmentDTO departmentDTO = departmentMapper.toDto(department);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDepartmentMockMvc.perform(post("/api/departments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(departmentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Department in the database
        List<Department> departmentList = departmentRepository.findAll();
        assertThat(departmentList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkDepartmentNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = departmentRepository.findAll().size();
        // set the field null
        department.setDepartmentName(null);

        // Create the Department, which fails.
        DepartmentDTO departmentDTO = departmentMapper.toDto(department);

        restDepartmentMockMvc.perform(post("/api/departments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(departmentDTO)))
            .andExpect(status().isBadRequest());

        List<Department> departmentList = departmentRepository.findAll();
        assertThat(departmentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDepartments() throws Exception {
        // Initialize the database
        departmentRepository.saveAndFlush(department);

        // Get all the departmentList
        restDepartmentMockMvc.perform(get("/api/departments?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(department.getId().intValue())))
            .andExpect(jsonPath("$.[*].departmentName").value(hasItem(DEFAULT_DEPARTMENT_NAME.toString())))
            .andExpect(jsonPath("$.[*].departmentPhotoContentType").value(hasItem(DEFAULT_DEPARTMENT_PHOTO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].departmentPhoto").value(hasItem(Base64Utils.encodeToString(DEFAULT_DEPARTMENT_PHOTO))))
            .andExpect(jsonPath("$.[*].departmentPhone").value(hasItem(DEFAULT_DEPARTMENT_PHONE.toString())));
    }
    

    @Test
    @Transactional
    public void getDepartment() throws Exception {
        // Initialize the database
        departmentRepository.saveAndFlush(department);

        // Get the department
        restDepartmentMockMvc.perform(get("/api/departments/{id}", department.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(department.getId().intValue()))
            .andExpect(jsonPath("$.departmentName").value(DEFAULT_DEPARTMENT_NAME.toString()))
            .andExpect(jsonPath("$.departmentPhotoContentType").value(DEFAULT_DEPARTMENT_PHOTO_CONTENT_TYPE))
            .andExpect(jsonPath("$.departmentPhoto").value(Base64Utils.encodeToString(DEFAULT_DEPARTMENT_PHOTO)))
            .andExpect(jsonPath("$.departmentPhone").value(DEFAULT_DEPARTMENT_PHONE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingDepartment() throws Exception {
        // Get the department
        restDepartmentMockMvc.perform(get("/api/departments/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDepartment() throws Exception {
        // Initialize the database
        departmentRepository.saveAndFlush(department);

        int databaseSizeBeforeUpdate = departmentRepository.findAll().size();

        // Update the department
        Department updatedDepartment = departmentRepository.findById(department.getId()).get();
        // Disconnect from session so that the updates on updatedDepartment are not directly saved in db
        em.detach(updatedDepartment);
        updatedDepartment
            .departmentName(UPDATED_DEPARTMENT_NAME)
            .departmentPhoto(UPDATED_DEPARTMENT_PHOTO)
            .departmentPhotoContentType(UPDATED_DEPARTMENT_PHOTO_CONTENT_TYPE)
            .departmentPhone(UPDATED_DEPARTMENT_PHONE);
        DepartmentDTO departmentDTO = departmentMapper.toDto(updatedDepartment);

        restDepartmentMockMvc.perform(put("/api/departments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(departmentDTO)))
            .andExpect(status().isOk());

        // Validate the Department in the database
        List<Department> departmentList = departmentRepository.findAll();
        assertThat(departmentList).hasSize(databaseSizeBeforeUpdate);
        Department testDepartment = departmentList.get(departmentList.size() - 1);
        assertThat(testDepartment.getDepartmentName()).isEqualTo(UPDATED_DEPARTMENT_NAME);
        assertThat(testDepartment.getDepartmentPhoto()).isEqualTo(UPDATED_DEPARTMENT_PHOTO);
        assertThat(testDepartment.getDepartmentPhotoContentType()).isEqualTo(UPDATED_DEPARTMENT_PHOTO_CONTENT_TYPE);
        assertThat(testDepartment.getDepartmentPhone()).isEqualTo(UPDATED_DEPARTMENT_PHONE);
    }

    @Test
    @Transactional
    public void updateNonExistingDepartment() throws Exception {
        int databaseSizeBeforeUpdate = departmentRepository.findAll().size();

        // Create the Department
        DepartmentDTO departmentDTO = departmentMapper.toDto(department);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restDepartmentMockMvc.perform(put("/api/departments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(departmentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Department in the database
        List<Department> departmentList = departmentRepository.findAll();
        assertThat(departmentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDepartment() throws Exception {
        // Initialize the database
        departmentRepository.saveAndFlush(department);

        int databaseSizeBeforeDelete = departmentRepository.findAll().size();

        // Get the department
        restDepartmentMockMvc.perform(delete("/api/departments/{id}", department.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Department> departmentList = departmentRepository.findAll();
        assertThat(departmentList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Department.class);
        Department department1 = new Department();
        department1.setId(1L);
        Department department2 = new Department();
        department2.setId(department1.getId());
        assertThat(department1).isEqualTo(department2);
        department2.setId(2L);
        assertThat(department1).isNotEqualTo(department2);
        department1.setId(null);
        assertThat(department1).isNotEqualTo(department2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DepartmentDTO.class);
        DepartmentDTO departmentDTO1 = new DepartmentDTO();
        departmentDTO1.setId(1L);
        DepartmentDTO departmentDTO2 = new DepartmentDTO();
        assertThat(departmentDTO1).isNotEqualTo(departmentDTO2);
        departmentDTO2.setId(departmentDTO1.getId());
        assertThat(departmentDTO1).isEqualTo(departmentDTO2);
        departmentDTO2.setId(2L);
        assertThat(departmentDTO1).isNotEqualTo(departmentDTO2);
        departmentDTO1.setId(null);
        assertThat(departmentDTO1).isNotEqualTo(departmentDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(departmentMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(departmentMapper.fromId(null)).isNull();
    }
}