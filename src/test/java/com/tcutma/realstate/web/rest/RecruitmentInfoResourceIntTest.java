package com.tcutma.realstate.web.rest;

import com.tcutma.realstate.RiverApp;

import com.tcutma.realstate.domain.RecruitmentInfo;
import com.tcutma.realstate.repository.RecruitmentInfoRepository;
import com.tcutma.realstate.service.RecruitmentInfoService;
import com.tcutma.realstate.service.dto.RecruitmentInfoDTO;
import com.tcutma.realstate.service.mapper.RecruitmentInfoMapper;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;


import static com.tcutma.realstate.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the RecruitmentInfoResource REST controller.
 *
 * @see RecruitmentInfoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RiverApp.class)
public class RecruitmentInfoResourceIntTest {

    private static final String DEFAULT_RECRUITMENT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_RECRUITMENT_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_RECRUITMENT_AVATAR_URL = "AAAAAAAAAA";
    private static final String UPDATED_RECRUITMENT_AVATAR_URL = "BBBBBBBBBB";

    private static final String DEFAULT_RECRUITMENT_CONTENT = "AAAAAAAAAA";
    private static final String UPDATED_RECRUITMENT_CONTENT = "BBBBBBBBBB";

    private static final String DEFAULT_RECRUITMENT_NOTES = "AAAAAAAAAA";
    private static final String UPDATED_RECRUITMENT_NOTES = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_RECRUITMENT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_RECRUITMENT_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_RECRUITMENT_SEEN_COUNT = 1L;
    private static final Long UPDATED_RECRUITMENT_SEEN_COUNT = 2L;

    private static final Boolean DEFAULT_RECRUITMENT_STATUS = false;
    private static final Boolean UPDATED_RECRUITMENT_STATUS = true;

    @Autowired
    private RecruitmentInfoRepository recruitmentInfoRepository;


    @Autowired
    private RecruitmentInfoMapper recruitmentInfoMapper;
    

    @Autowired
    private RecruitmentInfoService recruitmentInfoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRecruitmentInfoMockMvc;

    private RecruitmentInfo recruitmentInfo;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RecruitmentInfoResource recruitmentInfoResource = new RecruitmentInfoResource(recruitmentInfoService);
        this.restRecruitmentInfoMockMvc = MockMvcBuilders.standaloneSetup(recruitmentInfoResource)
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
    public static RecruitmentInfo createEntity(EntityManager em) {
        RecruitmentInfo recruitmentInfo = new RecruitmentInfo()
            .recruitmentTitle(DEFAULT_RECRUITMENT_TITLE)
            .recruitmentAvatarUrl(DEFAULT_RECRUITMENT_AVATAR_URL)
            .recruitmentContent(DEFAULT_RECRUITMENT_CONTENT)
            .recruitmentNotes(DEFAULT_RECRUITMENT_NOTES)
            .recruitmentDate(DEFAULT_RECRUITMENT_DATE)
            .recruitmentSeenCount(DEFAULT_RECRUITMENT_SEEN_COUNT)
            .recruitmentStatus(DEFAULT_RECRUITMENT_STATUS);
        return recruitmentInfo;
    }

    @Before
    public void initTest() {
        recruitmentInfo = createEntity(em);
    }

    @Test
    @Transactional
    public void createRecruitmentInfo() throws Exception {
        int databaseSizeBeforeCreate = recruitmentInfoRepository.findAll().size();

        // Create the RecruitmentInfo
        RecruitmentInfoDTO recruitmentInfoDTO = recruitmentInfoMapper.toDto(recruitmentInfo);
        restRecruitmentInfoMockMvc.perform(post("/api/recruitment-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(recruitmentInfoDTO)))
            .andExpect(status().isCreated());

        // Validate the RecruitmentInfo in the database
        List<RecruitmentInfo> recruitmentInfoList = recruitmentInfoRepository.findAll();
        assertThat(recruitmentInfoList).hasSize(databaseSizeBeforeCreate + 1);
        RecruitmentInfo testRecruitmentInfo = recruitmentInfoList.get(recruitmentInfoList.size() - 1);
        assertThat(testRecruitmentInfo.getRecruitmentTitle()).isEqualTo(DEFAULT_RECRUITMENT_TITLE);
        assertThat(testRecruitmentInfo.getRecruitmentAvatarUrl()).isEqualTo(DEFAULT_RECRUITMENT_AVATAR_URL);
        assertThat(testRecruitmentInfo.getRecruitmentContent()).isEqualTo(DEFAULT_RECRUITMENT_CONTENT);
        assertThat(testRecruitmentInfo.getRecruitmentNotes()).isEqualTo(DEFAULT_RECRUITMENT_NOTES);
        assertThat(testRecruitmentInfo.getRecruitmentDate()).isEqualTo(DEFAULT_RECRUITMENT_DATE);
        assertThat(testRecruitmentInfo.getRecruitmentSeenCount()).isEqualTo(DEFAULT_RECRUITMENT_SEEN_COUNT);
        assertThat(testRecruitmentInfo.isRecruitmentStatus()).isEqualTo(DEFAULT_RECRUITMENT_STATUS);
    }

    @Test
    @Transactional
    public void createRecruitmentInfoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = recruitmentInfoRepository.findAll().size();

        // Create the RecruitmentInfo with an existing ID
        recruitmentInfo.setId(1L);
        RecruitmentInfoDTO recruitmentInfoDTO = recruitmentInfoMapper.toDto(recruitmentInfo);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRecruitmentInfoMockMvc.perform(post("/api/recruitment-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(recruitmentInfoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RecruitmentInfo in the database
        List<RecruitmentInfo> recruitmentInfoList = recruitmentInfoRepository.findAll();
        assertThat(recruitmentInfoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkRecruitmentTitleIsRequired() throws Exception {
        int databaseSizeBeforeTest = recruitmentInfoRepository.findAll().size();
        // set the field null
        recruitmentInfo.setRecruitmentTitle(null);

        // Create the RecruitmentInfo, which fails.
        RecruitmentInfoDTO recruitmentInfoDTO = recruitmentInfoMapper.toDto(recruitmentInfo);

        restRecruitmentInfoMockMvc.perform(post("/api/recruitment-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(recruitmentInfoDTO)))
            .andExpect(status().isBadRequest());

        List<RecruitmentInfo> recruitmentInfoList = recruitmentInfoRepository.findAll();
        assertThat(recruitmentInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRecruitmentInfos() throws Exception {
        // Initialize the database
        recruitmentInfoRepository.saveAndFlush(recruitmentInfo);

        // Get all the recruitmentInfoList
        restRecruitmentInfoMockMvc.perform(get("/api/recruitment-infos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(recruitmentInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].recruitmentTitle").value(hasItem(DEFAULT_RECRUITMENT_TITLE.toString())))
            .andExpect(jsonPath("$.[*].recruitmentAvatarUrl").value(hasItem(DEFAULT_RECRUITMENT_AVATAR_URL.toString())))
            .andExpect(jsonPath("$.[*].recruitmentContent").value(hasItem(DEFAULT_RECRUITMENT_CONTENT.toString())))
            .andExpect(jsonPath("$.[*].recruitmentNotes").value(hasItem(DEFAULT_RECRUITMENT_NOTES.toString())))
            .andExpect(jsonPath("$.[*].recruitmentDate").value(hasItem(DEFAULT_RECRUITMENT_DATE.toString())))
            .andExpect(jsonPath("$.[*].recruitmentSeenCount").value(hasItem(DEFAULT_RECRUITMENT_SEEN_COUNT.intValue())))
            .andExpect(jsonPath("$.[*].recruitmentStatus").value(hasItem(DEFAULT_RECRUITMENT_STATUS.booleanValue())));
    }
    

    @Test
    @Transactional
    public void getRecruitmentInfo() throws Exception {
        // Initialize the database
        recruitmentInfoRepository.saveAndFlush(recruitmentInfo);

        // Get the recruitmentInfo
        restRecruitmentInfoMockMvc.perform(get("/api/recruitment-infos/{id}", recruitmentInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(recruitmentInfo.getId().intValue()))
            .andExpect(jsonPath("$.recruitmentTitle").value(DEFAULT_RECRUITMENT_TITLE.toString()))
            .andExpect(jsonPath("$.recruitmentAvatarUrl").value(DEFAULT_RECRUITMENT_AVATAR_URL.toString()))
            .andExpect(jsonPath("$.recruitmentContent").value(DEFAULT_RECRUITMENT_CONTENT.toString()))
            .andExpect(jsonPath("$.recruitmentNotes").value(DEFAULT_RECRUITMENT_NOTES.toString()))
            .andExpect(jsonPath("$.recruitmentDate").value(DEFAULT_RECRUITMENT_DATE.toString()))
            .andExpect(jsonPath("$.recruitmentSeenCount").value(DEFAULT_RECRUITMENT_SEEN_COUNT.intValue()))
            .andExpect(jsonPath("$.recruitmentStatus").value(DEFAULT_RECRUITMENT_STATUS.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingRecruitmentInfo() throws Exception {
        // Get the recruitmentInfo
        restRecruitmentInfoMockMvc.perform(get("/api/recruitment-infos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRecruitmentInfo() throws Exception {
        // Initialize the database
        recruitmentInfoRepository.saveAndFlush(recruitmentInfo);

        int databaseSizeBeforeUpdate = recruitmentInfoRepository.findAll().size();

        // Update the recruitmentInfo
        RecruitmentInfo updatedRecruitmentInfo = recruitmentInfoRepository.findById(recruitmentInfo.getId()).get();
        // Disconnect from session so that the updates on updatedRecruitmentInfo are not directly saved in db
        em.detach(updatedRecruitmentInfo);
        updatedRecruitmentInfo
            .recruitmentTitle(UPDATED_RECRUITMENT_TITLE)
            .recruitmentAvatarUrl(UPDATED_RECRUITMENT_AVATAR_URL)
            .recruitmentContent(UPDATED_RECRUITMENT_CONTENT)
            .recruitmentNotes(UPDATED_RECRUITMENT_NOTES)
            .recruitmentDate(UPDATED_RECRUITMENT_DATE)
            .recruitmentSeenCount(UPDATED_RECRUITMENT_SEEN_COUNT)
            .recruitmentStatus(UPDATED_RECRUITMENT_STATUS);
        RecruitmentInfoDTO recruitmentInfoDTO = recruitmentInfoMapper.toDto(updatedRecruitmentInfo);

        restRecruitmentInfoMockMvc.perform(put("/api/recruitment-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(recruitmentInfoDTO)))
            .andExpect(status().isOk());

        // Validate the RecruitmentInfo in the database
        List<RecruitmentInfo> recruitmentInfoList = recruitmentInfoRepository.findAll();
        assertThat(recruitmentInfoList).hasSize(databaseSizeBeforeUpdate);
        RecruitmentInfo testRecruitmentInfo = recruitmentInfoList.get(recruitmentInfoList.size() - 1);
        assertThat(testRecruitmentInfo.getRecruitmentTitle()).isEqualTo(UPDATED_RECRUITMENT_TITLE);
        assertThat(testRecruitmentInfo.getRecruitmentAvatarUrl()).isEqualTo(UPDATED_RECRUITMENT_AVATAR_URL);
        assertThat(testRecruitmentInfo.getRecruitmentContent()).isEqualTo(UPDATED_RECRUITMENT_CONTENT);
        assertThat(testRecruitmentInfo.getRecruitmentNotes()).isEqualTo(UPDATED_RECRUITMENT_NOTES);
        assertThat(testRecruitmentInfo.getRecruitmentDate()).isEqualTo(UPDATED_RECRUITMENT_DATE);
        assertThat(testRecruitmentInfo.getRecruitmentSeenCount()).isEqualTo(UPDATED_RECRUITMENT_SEEN_COUNT);
        assertThat(testRecruitmentInfo.isRecruitmentStatus()).isEqualTo(UPDATED_RECRUITMENT_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingRecruitmentInfo() throws Exception {
        int databaseSizeBeforeUpdate = recruitmentInfoRepository.findAll().size();

        // Create the RecruitmentInfo
        RecruitmentInfoDTO recruitmentInfoDTO = recruitmentInfoMapper.toDto(recruitmentInfo);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restRecruitmentInfoMockMvc.perform(put("/api/recruitment-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(recruitmentInfoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RecruitmentInfo in the database
        List<RecruitmentInfo> recruitmentInfoList = recruitmentInfoRepository.findAll();
        assertThat(recruitmentInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRecruitmentInfo() throws Exception {
        // Initialize the database
        recruitmentInfoRepository.saveAndFlush(recruitmentInfo);

        int databaseSizeBeforeDelete = recruitmentInfoRepository.findAll().size();

        // Get the recruitmentInfo
        restRecruitmentInfoMockMvc.perform(delete("/api/recruitment-infos/{id}", recruitmentInfo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RecruitmentInfo> recruitmentInfoList = recruitmentInfoRepository.findAll();
        assertThat(recruitmentInfoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RecruitmentInfo.class);
        RecruitmentInfo recruitmentInfo1 = new RecruitmentInfo();
        recruitmentInfo1.setId(1L);
        RecruitmentInfo recruitmentInfo2 = new RecruitmentInfo();
        recruitmentInfo2.setId(recruitmentInfo1.getId());
        assertThat(recruitmentInfo1).isEqualTo(recruitmentInfo2);
        recruitmentInfo2.setId(2L);
        assertThat(recruitmentInfo1).isNotEqualTo(recruitmentInfo2);
        recruitmentInfo1.setId(null);
        assertThat(recruitmentInfo1).isNotEqualTo(recruitmentInfo2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RecruitmentInfoDTO.class);
        RecruitmentInfoDTO recruitmentInfoDTO1 = new RecruitmentInfoDTO();
        recruitmentInfoDTO1.setId(1L);
        RecruitmentInfoDTO recruitmentInfoDTO2 = new RecruitmentInfoDTO();
        assertThat(recruitmentInfoDTO1).isNotEqualTo(recruitmentInfoDTO2);
        recruitmentInfoDTO2.setId(recruitmentInfoDTO1.getId());
        assertThat(recruitmentInfoDTO1).isEqualTo(recruitmentInfoDTO2);
        recruitmentInfoDTO2.setId(2L);
        assertThat(recruitmentInfoDTO1).isNotEqualTo(recruitmentInfoDTO2);
        recruitmentInfoDTO1.setId(null);
        assertThat(recruitmentInfoDTO1).isNotEqualTo(recruitmentInfoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(recruitmentInfoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(recruitmentInfoMapper.fromId(null)).isNull();
    }
}
