package com.tcutma.realstate.web.rest;

import com.tcutma.realstate.RiverApp;

import com.tcutma.realstate.domain.Contact;
import com.tcutma.realstate.repository.ContactRepository;
import com.tcutma.realstate.service.ContactService;
import com.tcutma.realstate.service.dto.ContactDTO;
import com.tcutma.realstate.service.mapper.ContactMapper;
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
 * Test class for the ContactResource REST controller.
 *
 * @see ContactResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RiverApp.class)
public class ContactResourceIntTest {

    private static final String DEFAULT_CONTACT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_WEBSITE = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_WEBSITE = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_AVATAR_URL = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_AVATAR_URL = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_FACEBOOK = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_FACEBOOK = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_TWITTER = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_TWITTER = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_INSTAGRAM = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_INSTAGRAM = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_LINKEDIN = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_LINKEDIN = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_GOOGLE_PLUS = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_GOOGLE_PLUS = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_YOUTUBE = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_YOUTUBE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_CONTACT_STATUS = false;
    private static final Boolean UPDATED_CONTACT_STATUS = true;

    @Autowired
    private ContactRepository contactRepository;


    @Autowired
    private ContactMapper contactMapper;
    

    @Autowired
    private ContactService contactService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restContactMockMvc;

    private Contact contact;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ContactResource contactResource = new ContactResource(contactService);
        this.restContactMockMvc = MockMvcBuilders.standaloneSetup(contactResource)
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
    public static Contact createEntity(EntityManager em) {
        Contact contact = new Contact()
            .contactName(DEFAULT_CONTACT_NAME)
            .contactPhone(DEFAULT_CONTACT_PHONE)
            .contactAddress(DEFAULT_CONTACT_ADDRESS)
            .contactWebsite(DEFAULT_CONTACT_WEBSITE)
            .contactAvatarUrl(DEFAULT_CONTACT_AVATAR_URL)
            .contactFacebook(DEFAULT_CONTACT_FACEBOOK)
            .contactTwitter(DEFAULT_CONTACT_TWITTER)
            .contactInstagram(DEFAULT_CONTACT_INSTAGRAM)
            .contactLinkedin(DEFAULT_CONTACT_LINKEDIN)
            .contactGooglePlus(DEFAULT_CONTACT_GOOGLE_PLUS)
            .contactYoutube(DEFAULT_CONTACT_YOUTUBE)
            .contactStatus(DEFAULT_CONTACT_STATUS);
        return contact;
    }

    @Before
    public void initTest() {
        contact = createEntity(em);
    }

    @Test
    @Transactional
    public void createContact() throws Exception {
        int databaseSizeBeforeCreate = contactRepository.findAll().size();

        // Create the Contact
        ContactDTO contactDTO = contactMapper.toDto(contact);
        restContactMockMvc.perform(post("/api/contacts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contactDTO)))
            .andExpect(status().isCreated());

        // Validate the Contact in the database
        List<Contact> contactList = contactRepository.findAll();
        assertThat(contactList).hasSize(databaseSizeBeforeCreate + 1);
        Contact testContact = contactList.get(contactList.size() - 1);
        assertThat(testContact.getContactName()).isEqualTo(DEFAULT_CONTACT_NAME);
        assertThat(testContact.getContactPhone()).isEqualTo(DEFAULT_CONTACT_PHONE);
        assertThat(testContact.getContactAddress()).isEqualTo(DEFAULT_CONTACT_ADDRESS);
        assertThat(testContact.getContactWebsite()).isEqualTo(DEFAULT_CONTACT_WEBSITE);
        assertThat(testContact.getContactAvatarUrl()).isEqualTo(DEFAULT_CONTACT_AVATAR_URL);
        assertThat(testContact.getContactFacebook()).isEqualTo(DEFAULT_CONTACT_FACEBOOK);
        assertThat(testContact.getContactTwitter()).isEqualTo(DEFAULT_CONTACT_TWITTER);
        assertThat(testContact.getContactInstagram()).isEqualTo(DEFAULT_CONTACT_INSTAGRAM);
        assertThat(testContact.getContactLinkedin()).isEqualTo(DEFAULT_CONTACT_LINKEDIN);
        assertThat(testContact.getContactGooglePlus()).isEqualTo(DEFAULT_CONTACT_GOOGLE_PLUS);
        assertThat(testContact.getContactYoutube()).isEqualTo(DEFAULT_CONTACT_YOUTUBE);
        assertThat(testContact.isContactStatus()).isEqualTo(DEFAULT_CONTACT_STATUS);
    }

    @Test
    @Transactional
    public void createContactWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = contactRepository.findAll().size();

        // Create the Contact with an existing ID
        contact.setId(1L);
        ContactDTO contactDTO = contactMapper.toDto(contact);

        // An entity with an existing ID cannot be created, so this API call must fail
        restContactMockMvc.perform(post("/api/contacts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contactDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Contact in the database
        List<Contact> contactList = contactRepository.findAll();
        assertThat(contactList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkContactNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = contactRepository.findAll().size();
        // set the field null
        contact.setContactName(null);

        // Create the Contact, which fails.
        ContactDTO contactDTO = contactMapper.toDto(contact);

        restContactMockMvc.perform(post("/api/contacts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contactDTO)))
            .andExpect(status().isBadRequest());

        List<Contact> contactList = contactRepository.findAll();
        assertThat(contactList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContactPhoneIsRequired() throws Exception {
        int databaseSizeBeforeTest = contactRepository.findAll().size();
        // set the field null
        contact.setContactPhone(null);

        // Create the Contact, which fails.
        ContactDTO contactDTO = contactMapper.toDto(contact);

        restContactMockMvc.perform(post("/api/contacts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contactDTO)))
            .andExpect(status().isBadRequest());

        List<Contact> contactList = contactRepository.findAll();
        assertThat(contactList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllContacts() throws Exception {
        // Initialize the database
        contactRepository.saveAndFlush(contact);

        // Get all the contactList
        restContactMockMvc.perform(get("/api/contacts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(contact.getId().intValue())))
            .andExpect(jsonPath("$.[*].contactName").value(hasItem(DEFAULT_CONTACT_NAME.toString())))
            .andExpect(jsonPath("$.[*].contactPhone").value(hasItem(DEFAULT_CONTACT_PHONE.toString())))
            .andExpect(jsonPath("$.[*].contactAddress").value(hasItem(DEFAULT_CONTACT_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].contactWebsite").value(hasItem(DEFAULT_CONTACT_WEBSITE.toString())))
            .andExpect(jsonPath("$.[*].contactAvatarUrl").value(hasItem(DEFAULT_CONTACT_AVATAR_URL.toString())))
            .andExpect(jsonPath("$.[*].contactFacebook").value(hasItem(DEFAULT_CONTACT_FACEBOOK.toString())))
            .andExpect(jsonPath("$.[*].contactTwitter").value(hasItem(DEFAULT_CONTACT_TWITTER.toString())))
            .andExpect(jsonPath("$.[*].contactInstagram").value(hasItem(DEFAULT_CONTACT_INSTAGRAM.toString())))
            .andExpect(jsonPath("$.[*].contactLinkedin").value(hasItem(DEFAULT_CONTACT_LINKEDIN.toString())))
            .andExpect(jsonPath("$.[*].contactGooglePlus").value(hasItem(DEFAULT_CONTACT_GOOGLE_PLUS.toString())))
            .andExpect(jsonPath("$.[*].contactYoutube").value(hasItem(DEFAULT_CONTACT_YOUTUBE.toString())))
            .andExpect(jsonPath("$.[*].contactStatus").value(hasItem(DEFAULT_CONTACT_STATUS.booleanValue())));
    }
    

    @Test
    @Transactional
    public void getContact() throws Exception {
        // Initialize the database
        contactRepository.saveAndFlush(contact);

        // Get the contact
        restContactMockMvc.perform(get("/api/contacts/{id}", contact.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(contact.getId().intValue()))
            .andExpect(jsonPath("$.contactName").value(DEFAULT_CONTACT_NAME.toString()))
            .andExpect(jsonPath("$.contactPhone").value(DEFAULT_CONTACT_PHONE.toString()))
            .andExpect(jsonPath("$.contactAddress").value(DEFAULT_CONTACT_ADDRESS.toString()))
            .andExpect(jsonPath("$.contactWebsite").value(DEFAULT_CONTACT_WEBSITE.toString()))
            .andExpect(jsonPath("$.contactAvatarUrl").value(DEFAULT_CONTACT_AVATAR_URL.toString()))
            .andExpect(jsonPath("$.contactFacebook").value(DEFAULT_CONTACT_FACEBOOK.toString()))
            .andExpect(jsonPath("$.contactTwitter").value(DEFAULT_CONTACT_TWITTER.toString()))
            .andExpect(jsonPath("$.contactInstagram").value(DEFAULT_CONTACT_INSTAGRAM.toString()))
            .andExpect(jsonPath("$.contactLinkedin").value(DEFAULT_CONTACT_LINKEDIN.toString()))
            .andExpect(jsonPath("$.contactGooglePlus").value(DEFAULT_CONTACT_GOOGLE_PLUS.toString()))
            .andExpect(jsonPath("$.contactYoutube").value(DEFAULT_CONTACT_YOUTUBE.toString()))
            .andExpect(jsonPath("$.contactStatus").value(DEFAULT_CONTACT_STATUS.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingContact() throws Exception {
        // Get the contact
        restContactMockMvc.perform(get("/api/contacts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateContact() throws Exception {
        // Initialize the database
        contactRepository.saveAndFlush(contact);

        int databaseSizeBeforeUpdate = contactRepository.findAll().size();

        // Update the contact
        Contact updatedContact = contactRepository.findById(contact.getId()).get();
        // Disconnect from session so that the updates on updatedContact are not directly saved in db
        em.detach(updatedContact);
        updatedContact
            .contactName(UPDATED_CONTACT_NAME)
            .contactPhone(UPDATED_CONTACT_PHONE)
            .contactAddress(UPDATED_CONTACT_ADDRESS)
            .contactWebsite(UPDATED_CONTACT_WEBSITE)
            .contactAvatarUrl(UPDATED_CONTACT_AVATAR_URL)
            .contactFacebook(UPDATED_CONTACT_FACEBOOK)
            .contactTwitter(UPDATED_CONTACT_TWITTER)
            .contactInstagram(UPDATED_CONTACT_INSTAGRAM)
            .contactLinkedin(UPDATED_CONTACT_LINKEDIN)
            .contactGooglePlus(UPDATED_CONTACT_GOOGLE_PLUS)
            .contactYoutube(UPDATED_CONTACT_YOUTUBE)
            .contactStatus(UPDATED_CONTACT_STATUS);
        ContactDTO contactDTO = contactMapper.toDto(updatedContact);

        restContactMockMvc.perform(put("/api/contacts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contactDTO)))
            .andExpect(status().isOk());

        // Validate the Contact in the database
        List<Contact> contactList = contactRepository.findAll();
        assertThat(contactList).hasSize(databaseSizeBeforeUpdate);
        Contact testContact = contactList.get(contactList.size() - 1);
        assertThat(testContact.getContactName()).isEqualTo(UPDATED_CONTACT_NAME);
        assertThat(testContact.getContactPhone()).isEqualTo(UPDATED_CONTACT_PHONE);
        assertThat(testContact.getContactAddress()).isEqualTo(UPDATED_CONTACT_ADDRESS);
        assertThat(testContact.getContactWebsite()).isEqualTo(UPDATED_CONTACT_WEBSITE);
        assertThat(testContact.getContactAvatarUrl()).isEqualTo(UPDATED_CONTACT_AVATAR_URL);
        assertThat(testContact.getContactFacebook()).isEqualTo(UPDATED_CONTACT_FACEBOOK);
        assertThat(testContact.getContactTwitter()).isEqualTo(UPDATED_CONTACT_TWITTER);
        assertThat(testContact.getContactInstagram()).isEqualTo(UPDATED_CONTACT_INSTAGRAM);
        assertThat(testContact.getContactLinkedin()).isEqualTo(UPDATED_CONTACT_LINKEDIN);
        assertThat(testContact.getContactGooglePlus()).isEqualTo(UPDATED_CONTACT_GOOGLE_PLUS);
        assertThat(testContact.getContactYoutube()).isEqualTo(UPDATED_CONTACT_YOUTUBE);
        assertThat(testContact.isContactStatus()).isEqualTo(UPDATED_CONTACT_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingContact() throws Exception {
        int databaseSizeBeforeUpdate = contactRepository.findAll().size();

        // Create the Contact
        ContactDTO contactDTO = contactMapper.toDto(contact);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restContactMockMvc.perform(put("/api/contacts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contactDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Contact in the database
        List<Contact> contactList = contactRepository.findAll();
        assertThat(contactList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteContact() throws Exception {
        // Initialize the database
        contactRepository.saveAndFlush(contact);

        int databaseSizeBeforeDelete = contactRepository.findAll().size();

        // Get the contact
        restContactMockMvc.perform(delete("/api/contacts/{id}", contact.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Contact> contactList = contactRepository.findAll();
        assertThat(contactList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Contact.class);
        Contact contact1 = new Contact();
        contact1.setId(1L);
        Contact contact2 = new Contact();
        contact2.setId(contact1.getId());
        assertThat(contact1).isEqualTo(contact2);
        contact2.setId(2L);
        assertThat(contact1).isNotEqualTo(contact2);
        contact1.setId(null);
        assertThat(contact1).isNotEqualTo(contact2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ContactDTO.class);
        ContactDTO contactDTO1 = new ContactDTO();
        contactDTO1.setId(1L);
        ContactDTO contactDTO2 = new ContactDTO();
        assertThat(contactDTO1).isNotEqualTo(contactDTO2);
        contactDTO2.setId(contactDTO1.getId());
        assertThat(contactDTO1).isEqualTo(contactDTO2);
        contactDTO2.setId(2L);
        assertThat(contactDTO1).isNotEqualTo(contactDTO2);
        contactDTO1.setId(null);
        assertThat(contactDTO1).isNotEqualTo(contactDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(contactMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(contactMapper.fromId(null)).isNull();
    }
}
