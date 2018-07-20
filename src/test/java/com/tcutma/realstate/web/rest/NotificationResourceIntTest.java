package com.tcutma.realstate.web.rest;

import com.tcutma.realstate.RiverApp;

import com.tcutma.realstate.domain.Notification;
import com.tcutma.realstate.domain.User;
import com.tcutma.realstate.repository.NotificationRepository;
import com.tcutma.realstate.service.NotificationService;
import com.tcutma.realstate.service.dto.NotificationDTO;
import com.tcutma.realstate.service.mapper.NotificationMapper;
import com.tcutma.realstate.web.rest.errors.ExceptionTranslator;
import com.tcutma.realstate.service.dto.NotificationCriteria;
import com.tcutma.realstate.service.NotificationQueryService;

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

import com.tcutma.realstate.domain.enumeration.NotificationType;
/**
 * Test class for the NotificationResource REST controller.
 *
 * @see NotificationResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RiverApp.class)
public class NotificationResourceIntTest {

    private static final String DEFAULT_NOTIFICATION_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_NOTIFICATION_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_NOTIFICATION_CONTENT = "AAAAAAAAAA";
    private static final String UPDATED_NOTIFICATION_CONTENT = "BBBBBBBBBB";

    private static final Boolean DEFAULT_NOTIFICATION_SEEN = false;
    private static final Boolean UPDATED_NOTIFICATION_SEEN = true;

    private static final ZonedDateTime DEFAULT_NOTIFICATION_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_NOTIFICATION_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final NotificationType DEFAULT_NOTIFICATION_TYPE = NotificationType.NOTIFICATION;
    private static final NotificationType UPDATED_NOTIFICATION_TYPE = NotificationType.REQUEST;

    private static final String DEFAULT_NOTIFICATION_REFERENCE = "AAAAAAAAAA";
    private static final String UPDATED_NOTIFICATION_REFERENCE = "BBBBBBBBBB";

    @Autowired
    private NotificationRepository notificationRepository;


    @Autowired
    private NotificationMapper notificationMapper;
    

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private NotificationQueryService notificationQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restNotificationMockMvc;

    private Notification notification;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NotificationResource notificationResource = new NotificationResource(notificationService, notificationQueryService);
        this.restNotificationMockMvc = MockMvcBuilders.standaloneSetup(notificationResource)
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
    public static Notification createEntity(EntityManager em) {
        Notification notification = new Notification()
            .notificationTitle(DEFAULT_NOTIFICATION_TITLE)
            .notificationContent(DEFAULT_NOTIFICATION_CONTENT)
            .notificationSeen(DEFAULT_NOTIFICATION_SEEN)
            .notificationDate(DEFAULT_NOTIFICATION_DATE)
            .notificationType(DEFAULT_NOTIFICATION_TYPE)
            .notificationReference(DEFAULT_NOTIFICATION_REFERENCE);
        return notification;
    }

    @Before
    public void initTest() {
        notification = createEntity(em);
    }

    @Test
    @Transactional
    public void createNotification() throws Exception {
        int databaseSizeBeforeCreate = notificationRepository.findAll().size();

        // Create the Notification
        NotificationDTO notificationDTO = notificationMapper.toDto(notification);
        restNotificationMockMvc.perform(post("/api/notifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(notificationDTO)))
            .andExpect(status().isCreated());

        // Validate the Notification in the database
        List<Notification> notificationList = notificationRepository.findAll();
        assertThat(notificationList).hasSize(databaseSizeBeforeCreate + 1);
        Notification testNotification = notificationList.get(notificationList.size() - 1);
        assertThat(testNotification.getNotificationTitle()).isEqualTo(DEFAULT_NOTIFICATION_TITLE);
        assertThat(testNotification.getNotificationContent()).isEqualTo(DEFAULT_NOTIFICATION_CONTENT);
        assertThat(testNotification.isNotificationSeen()).isEqualTo(DEFAULT_NOTIFICATION_SEEN);
        assertThat(testNotification.getNotificationDate()).isEqualTo(DEFAULT_NOTIFICATION_DATE);
        assertThat(testNotification.getNotificationType()).isEqualTo(DEFAULT_NOTIFICATION_TYPE);
        assertThat(testNotification.getNotificationReference()).isEqualTo(DEFAULT_NOTIFICATION_REFERENCE);
    }

    @Test
    @Transactional
    public void createNotificationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = notificationRepository.findAll().size();

        // Create the Notification with an existing ID
        notification.setId(1L);
        NotificationDTO notificationDTO = notificationMapper.toDto(notification);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNotificationMockMvc.perform(post("/api/notifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(notificationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Notification in the database
        List<Notification> notificationList = notificationRepository.findAll();
        assertThat(notificationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNotificationTitleIsRequired() throws Exception {
        int databaseSizeBeforeTest = notificationRepository.findAll().size();
        // set the field null
        notification.setNotificationTitle(null);

        // Create the Notification, which fails.
        NotificationDTO notificationDTO = notificationMapper.toDto(notification);

        restNotificationMockMvc.perform(post("/api/notifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(notificationDTO)))
            .andExpect(status().isBadRequest());

        List<Notification> notificationList = notificationRepository.findAll();
        assertThat(notificationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllNotifications() throws Exception {
        // Initialize the database
        notificationRepository.saveAndFlush(notification);

        // Get all the notificationList
        restNotificationMockMvc.perform(get("/api/notifications?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(notification.getId().intValue())))
            .andExpect(jsonPath("$.[*].notificationTitle").value(hasItem(DEFAULT_NOTIFICATION_TITLE.toString())))
            .andExpect(jsonPath("$.[*].notificationContent").value(hasItem(DEFAULT_NOTIFICATION_CONTENT.toString())))
            .andExpect(jsonPath("$.[*].notificationSeen").value(hasItem(DEFAULT_NOTIFICATION_SEEN.booleanValue())))
            .andExpect(jsonPath("$.[*].notificationDate").value(hasItem(sameInstant(DEFAULT_NOTIFICATION_DATE))))
            .andExpect(jsonPath("$.[*].notificationType").value(hasItem(DEFAULT_NOTIFICATION_TYPE.toString())))
            .andExpect(jsonPath("$.[*].notificationReference").value(hasItem(DEFAULT_NOTIFICATION_REFERENCE.toString())));
    }
    

    @Test
    @Transactional
    public void getNotification() throws Exception {
        // Initialize the database
        notificationRepository.saveAndFlush(notification);

        // Get the notification
        restNotificationMockMvc.perform(get("/api/notifications/{id}", notification.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(notification.getId().intValue()))
            .andExpect(jsonPath("$.notificationTitle").value(DEFAULT_NOTIFICATION_TITLE.toString()))
            .andExpect(jsonPath("$.notificationContent").value(DEFAULT_NOTIFICATION_CONTENT.toString()))
            .andExpect(jsonPath("$.notificationSeen").value(DEFAULT_NOTIFICATION_SEEN.booleanValue()))
            .andExpect(jsonPath("$.notificationDate").value(sameInstant(DEFAULT_NOTIFICATION_DATE)))
            .andExpect(jsonPath("$.notificationType").value(DEFAULT_NOTIFICATION_TYPE.toString()))
            .andExpect(jsonPath("$.notificationReference").value(DEFAULT_NOTIFICATION_REFERENCE.toString()));
    }

    @Test
    @Transactional
    public void getAllNotificationsByNotificationTitleIsEqualToSomething() throws Exception {
        // Initialize the database
        notificationRepository.saveAndFlush(notification);

        // Get all the notificationList where notificationTitle equals to DEFAULT_NOTIFICATION_TITLE
        defaultNotificationShouldBeFound("notificationTitle.equals=" + DEFAULT_NOTIFICATION_TITLE);

        // Get all the notificationList where notificationTitle equals to UPDATED_NOTIFICATION_TITLE
        defaultNotificationShouldNotBeFound("notificationTitle.equals=" + UPDATED_NOTIFICATION_TITLE);
    }

    @Test
    @Transactional
    public void getAllNotificationsByNotificationTitleIsInShouldWork() throws Exception {
        // Initialize the database
        notificationRepository.saveAndFlush(notification);

        // Get all the notificationList where notificationTitle in DEFAULT_NOTIFICATION_TITLE or UPDATED_NOTIFICATION_TITLE
        defaultNotificationShouldBeFound("notificationTitle.in=" + DEFAULT_NOTIFICATION_TITLE + "," + UPDATED_NOTIFICATION_TITLE);

        // Get all the notificationList where notificationTitle equals to UPDATED_NOTIFICATION_TITLE
        defaultNotificationShouldNotBeFound("notificationTitle.in=" + UPDATED_NOTIFICATION_TITLE);
    }

    @Test
    @Transactional
    public void getAllNotificationsByNotificationTitleIsNullOrNotNull() throws Exception {
        // Initialize the database
        notificationRepository.saveAndFlush(notification);

        // Get all the notificationList where notificationTitle is not null
        defaultNotificationShouldBeFound("notificationTitle.specified=true");

        // Get all the notificationList where notificationTitle is null
        defaultNotificationShouldNotBeFound("notificationTitle.specified=false");
    }

    @Test
    @Transactional
    public void getAllNotificationsByNotificationSeenIsEqualToSomething() throws Exception {
        // Initialize the database
        notificationRepository.saveAndFlush(notification);

        // Get all the notificationList where notificationSeen equals to DEFAULT_NOTIFICATION_SEEN
        defaultNotificationShouldBeFound("notificationSeen.equals=" + DEFAULT_NOTIFICATION_SEEN);

        // Get all the notificationList where notificationSeen equals to UPDATED_NOTIFICATION_SEEN
        defaultNotificationShouldNotBeFound("notificationSeen.equals=" + UPDATED_NOTIFICATION_SEEN);
    }

    @Test
    @Transactional
    public void getAllNotificationsByNotificationSeenIsInShouldWork() throws Exception {
        // Initialize the database
        notificationRepository.saveAndFlush(notification);

        // Get all the notificationList where notificationSeen in DEFAULT_NOTIFICATION_SEEN or UPDATED_NOTIFICATION_SEEN
        defaultNotificationShouldBeFound("notificationSeen.in=" + DEFAULT_NOTIFICATION_SEEN + "," + UPDATED_NOTIFICATION_SEEN);

        // Get all the notificationList where notificationSeen equals to UPDATED_NOTIFICATION_SEEN
        defaultNotificationShouldNotBeFound("notificationSeen.in=" + UPDATED_NOTIFICATION_SEEN);
    }

    @Test
    @Transactional
    public void getAllNotificationsByNotificationSeenIsNullOrNotNull() throws Exception {
        // Initialize the database
        notificationRepository.saveAndFlush(notification);

        // Get all the notificationList where notificationSeen is not null
        defaultNotificationShouldBeFound("notificationSeen.specified=true");

        // Get all the notificationList where notificationSeen is null
        defaultNotificationShouldNotBeFound("notificationSeen.specified=false");
    }

    @Test
    @Transactional
    public void getAllNotificationsByNotificationDateIsEqualToSomething() throws Exception {
        // Initialize the database
        notificationRepository.saveAndFlush(notification);

        // Get all the notificationList where notificationDate equals to DEFAULT_NOTIFICATION_DATE
        defaultNotificationShouldBeFound("notificationDate.equals=" + DEFAULT_NOTIFICATION_DATE);

        // Get all the notificationList where notificationDate equals to UPDATED_NOTIFICATION_DATE
        defaultNotificationShouldNotBeFound("notificationDate.equals=" + UPDATED_NOTIFICATION_DATE);
    }

    @Test
    @Transactional
    public void getAllNotificationsByNotificationDateIsInShouldWork() throws Exception {
        // Initialize the database
        notificationRepository.saveAndFlush(notification);

        // Get all the notificationList where notificationDate in DEFAULT_NOTIFICATION_DATE or UPDATED_NOTIFICATION_DATE
        defaultNotificationShouldBeFound("notificationDate.in=" + DEFAULT_NOTIFICATION_DATE + "," + UPDATED_NOTIFICATION_DATE);

        // Get all the notificationList where notificationDate equals to UPDATED_NOTIFICATION_DATE
        defaultNotificationShouldNotBeFound("notificationDate.in=" + UPDATED_NOTIFICATION_DATE);
    }

    @Test
    @Transactional
    public void getAllNotificationsByNotificationDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        notificationRepository.saveAndFlush(notification);

        // Get all the notificationList where notificationDate is not null
        defaultNotificationShouldBeFound("notificationDate.specified=true");

        // Get all the notificationList where notificationDate is null
        defaultNotificationShouldNotBeFound("notificationDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllNotificationsByNotificationDateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        notificationRepository.saveAndFlush(notification);

        // Get all the notificationList where notificationDate greater than or equals to DEFAULT_NOTIFICATION_DATE
        defaultNotificationShouldBeFound("notificationDate.greaterOrEqualThan=" + DEFAULT_NOTIFICATION_DATE);

        // Get all the notificationList where notificationDate greater than or equals to UPDATED_NOTIFICATION_DATE
        defaultNotificationShouldNotBeFound("notificationDate.greaterOrEqualThan=" + UPDATED_NOTIFICATION_DATE);
    }

    @Test
    @Transactional
    public void getAllNotificationsByNotificationDateIsLessThanSomething() throws Exception {
        // Initialize the database
        notificationRepository.saveAndFlush(notification);

        // Get all the notificationList where notificationDate less than or equals to DEFAULT_NOTIFICATION_DATE
        defaultNotificationShouldNotBeFound("notificationDate.lessThan=" + DEFAULT_NOTIFICATION_DATE);

        // Get all the notificationList where notificationDate less than or equals to UPDATED_NOTIFICATION_DATE
        defaultNotificationShouldBeFound("notificationDate.lessThan=" + UPDATED_NOTIFICATION_DATE);
    }


    @Test
    @Transactional
    public void getAllNotificationsByNotificationTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        notificationRepository.saveAndFlush(notification);

        // Get all the notificationList where notificationType equals to DEFAULT_NOTIFICATION_TYPE
        defaultNotificationShouldBeFound("notificationType.equals=" + DEFAULT_NOTIFICATION_TYPE);

        // Get all the notificationList where notificationType equals to UPDATED_NOTIFICATION_TYPE
        defaultNotificationShouldNotBeFound("notificationType.equals=" + UPDATED_NOTIFICATION_TYPE);
    }

    @Test
    @Transactional
    public void getAllNotificationsByNotificationTypeIsInShouldWork() throws Exception {
        // Initialize the database
        notificationRepository.saveAndFlush(notification);

        // Get all the notificationList where notificationType in DEFAULT_NOTIFICATION_TYPE or UPDATED_NOTIFICATION_TYPE
        defaultNotificationShouldBeFound("notificationType.in=" + DEFAULT_NOTIFICATION_TYPE + "," + UPDATED_NOTIFICATION_TYPE);

        // Get all the notificationList where notificationType equals to UPDATED_NOTIFICATION_TYPE
        defaultNotificationShouldNotBeFound("notificationType.in=" + UPDATED_NOTIFICATION_TYPE);
    }

    @Test
    @Transactional
    public void getAllNotificationsByNotificationTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        notificationRepository.saveAndFlush(notification);

        // Get all the notificationList where notificationType is not null
        defaultNotificationShouldBeFound("notificationType.specified=true");

        // Get all the notificationList where notificationType is null
        defaultNotificationShouldNotBeFound("notificationType.specified=false");
    }

    @Test
    @Transactional
    public void getAllNotificationsByNotificationReferenceIsEqualToSomething() throws Exception {
        // Initialize the database
        notificationRepository.saveAndFlush(notification);

        // Get all the notificationList where notificationReference equals to DEFAULT_NOTIFICATION_REFERENCE
        defaultNotificationShouldBeFound("notificationReference.equals=" + DEFAULT_NOTIFICATION_REFERENCE);

        // Get all the notificationList where notificationReference equals to UPDATED_NOTIFICATION_REFERENCE
        defaultNotificationShouldNotBeFound("notificationReference.equals=" + UPDATED_NOTIFICATION_REFERENCE);
    }

    @Test
    @Transactional
    public void getAllNotificationsByNotificationReferenceIsInShouldWork() throws Exception {
        // Initialize the database
        notificationRepository.saveAndFlush(notification);

        // Get all the notificationList where notificationReference in DEFAULT_NOTIFICATION_REFERENCE or UPDATED_NOTIFICATION_REFERENCE
        defaultNotificationShouldBeFound("notificationReference.in=" + DEFAULT_NOTIFICATION_REFERENCE + "," + UPDATED_NOTIFICATION_REFERENCE);

        // Get all the notificationList where notificationReference equals to UPDATED_NOTIFICATION_REFERENCE
        defaultNotificationShouldNotBeFound("notificationReference.in=" + UPDATED_NOTIFICATION_REFERENCE);
    }

    @Test
    @Transactional
    public void getAllNotificationsByNotificationReferenceIsNullOrNotNull() throws Exception {
        // Initialize the database
        notificationRepository.saveAndFlush(notification);

        // Get all the notificationList where notificationReference is not null
        defaultNotificationShouldBeFound("notificationReference.specified=true");

        // Get all the notificationList where notificationReference is null
        defaultNotificationShouldNotBeFound("notificationReference.specified=false");
    }

    @Test
    @Transactional
    public void getAllNotificationsByUserIsEqualToSomething() throws Exception {
        // Initialize the database
        User user = UserResourceIntTest.createEntity(em);
        em.persist(user);
        em.flush();
        notification.setUser(user);
        notificationRepository.saveAndFlush(notification);
        Long userId = user.getId();

        // Get all the notificationList where user equals to userId
        defaultNotificationShouldBeFound("userId.equals=" + userId);

        // Get all the notificationList where user equals to userId + 1
        defaultNotificationShouldNotBeFound("userId.equals=" + (userId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultNotificationShouldBeFound(String filter) throws Exception {
        restNotificationMockMvc.perform(get("/api/notifications?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(notification.getId().intValue())))
            .andExpect(jsonPath("$.[*].notificationTitle").value(hasItem(DEFAULT_NOTIFICATION_TITLE.toString())))
            .andExpect(jsonPath("$.[*].notificationContent").value(hasItem(DEFAULT_NOTIFICATION_CONTENT.toString())))
            .andExpect(jsonPath("$.[*].notificationSeen").value(hasItem(DEFAULT_NOTIFICATION_SEEN.booleanValue())))
            .andExpect(jsonPath("$.[*].notificationDate").value(hasItem(sameInstant(DEFAULT_NOTIFICATION_DATE))))
            .andExpect(jsonPath("$.[*].notificationType").value(hasItem(DEFAULT_NOTIFICATION_TYPE.toString())))
            .andExpect(jsonPath("$.[*].notificationReference").value(hasItem(DEFAULT_NOTIFICATION_REFERENCE.toString())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultNotificationShouldNotBeFound(String filter) throws Exception {
        restNotificationMockMvc.perform(get("/api/notifications?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @Transactional
    public void getNonExistingNotification() throws Exception {
        // Get the notification
        restNotificationMockMvc.perform(get("/api/notifications/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNotification() throws Exception {
        // Initialize the database
        notificationRepository.saveAndFlush(notification);

        int databaseSizeBeforeUpdate = notificationRepository.findAll().size();

        // Update the notification
        Notification updatedNotification = notificationRepository.findById(notification.getId()).get();
        // Disconnect from session so that the updates on updatedNotification are not directly saved in db
        em.detach(updatedNotification);
        updatedNotification
            .notificationTitle(UPDATED_NOTIFICATION_TITLE)
            .notificationContent(UPDATED_NOTIFICATION_CONTENT)
            .notificationSeen(UPDATED_NOTIFICATION_SEEN)
            .notificationDate(UPDATED_NOTIFICATION_DATE)
            .notificationType(UPDATED_NOTIFICATION_TYPE)
            .notificationReference(UPDATED_NOTIFICATION_REFERENCE);
        NotificationDTO notificationDTO = notificationMapper.toDto(updatedNotification);

        restNotificationMockMvc.perform(put("/api/notifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(notificationDTO)))
            .andExpect(status().isOk());

        // Validate the Notification in the database
        List<Notification> notificationList = notificationRepository.findAll();
        assertThat(notificationList).hasSize(databaseSizeBeforeUpdate);
        Notification testNotification = notificationList.get(notificationList.size() - 1);
        assertThat(testNotification.getNotificationTitle()).isEqualTo(UPDATED_NOTIFICATION_TITLE);
        assertThat(testNotification.getNotificationContent()).isEqualTo(UPDATED_NOTIFICATION_CONTENT);
        assertThat(testNotification.isNotificationSeen()).isEqualTo(UPDATED_NOTIFICATION_SEEN);
        assertThat(testNotification.getNotificationDate()).isEqualTo(UPDATED_NOTIFICATION_DATE);
        assertThat(testNotification.getNotificationType()).isEqualTo(UPDATED_NOTIFICATION_TYPE);
        assertThat(testNotification.getNotificationReference()).isEqualTo(UPDATED_NOTIFICATION_REFERENCE);
    }

    @Test
    @Transactional
    public void updateNonExistingNotification() throws Exception {
        int databaseSizeBeforeUpdate = notificationRepository.findAll().size();

        // Create the Notification
        NotificationDTO notificationDTO = notificationMapper.toDto(notification);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restNotificationMockMvc.perform(put("/api/notifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(notificationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Notification in the database
        List<Notification> notificationList = notificationRepository.findAll();
        assertThat(notificationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNotification() throws Exception {
        // Initialize the database
        notificationRepository.saveAndFlush(notification);

        int databaseSizeBeforeDelete = notificationRepository.findAll().size();

        // Get the notification
        restNotificationMockMvc.perform(delete("/api/notifications/{id}", notification.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Notification> notificationList = notificationRepository.findAll();
        assertThat(notificationList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Notification.class);
        Notification notification1 = new Notification();
        notification1.setId(1L);
        Notification notification2 = new Notification();
        notification2.setId(notification1.getId());
        assertThat(notification1).isEqualTo(notification2);
        notification2.setId(2L);
        assertThat(notification1).isNotEqualTo(notification2);
        notification1.setId(null);
        assertThat(notification1).isNotEqualTo(notification2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NotificationDTO.class);
        NotificationDTO notificationDTO1 = new NotificationDTO();
        notificationDTO1.setId(1L);
        NotificationDTO notificationDTO2 = new NotificationDTO();
        assertThat(notificationDTO1).isNotEqualTo(notificationDTO2);
        notificationDTO2.setId(notificationDTO1.getId());
        assertThat(notificationDTO1).isEqualTo(notificationDTO2);
        notificationDTO2.setId(2L);
        assertThat(notificationDTO1).isNotEqualTo(notificationDTO2);
        notificationDTO1.setId(null);
        assertThat(notificationDTO1).isNotEqualTo(notificationDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(notificationMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(notificationMapper.fromId(null)).isNull();
    }
}
