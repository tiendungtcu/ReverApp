package com.tcutma.realstate.web.rest;

import com.tcutma.realstate.RiverApp;

import com.tcutma.realstate.domain.Photo;
import com.tcutma.realstate.repository.PhotoRepository;
import com.tcutma.realstate.service.PhotoService;
import com.tcutma.realstate.service.dto.PhotoDTO;
import com.tcutma.realstate.service.mapper.PhotoMapper;
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
 * Test class for the PhotoResource REST controller.
 *
 * @see PhotoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RiverApp.class)
public class PhotoResourceIntTest {

    private static final String DEFAULT_PHOTO_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PHOTO_NAME = "BBBBBBBBBB";

    private static final byte[] DEFAULT_PHOTO_IMAGE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_PHOTO_IMAGE = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_PHOTO_IMAGE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_PHOTO_IMAGE_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_PHOTO_EXTENSION = "AAAAAAAAAA";
    private static final String UPDATED_PHOTO_EXTENSION = "BBBBBBBBBB";

    private static final String DEFAULT_PHOTO_URL = "AAAAAAAAAA";
    private static final String UPDATED_PHOTO_URL = "BBBBBBBBBB";

    private static final String DEFAULT_PHOTO_THUMBNAIL_URL = "AAAAAAAAAA";
    private static final String UPDATED_PHOTO_THUMBNAIL_URL = "BBBBBBBBBB";

    @Autowired
    private PhotoRepository photoRepository;


    @Autowired
    private PhotoMapper photoMapper;
    

    @Autowired
    private PhotoService photoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPhotoMockMvc;

    private Photo photo;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PhotoResource photoResource = new PhotoResource(photoService);
        this.restPhotoMockMvc = MockMvcBuilders.standaloneSetup(photoResource)
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
    public static Photo createEntity(EntityManager em) {
        Photo photo = new Photo()
            .photoName(DEFAULT_PHOTO_NAME)
            .photoImage(DEFAULT_PHOTO_IMAGE)
            .photoImageContentType(DEFAULT_PHOTO_IMAGE_CONTENT_TYPE)
            .photoExtension(DEFAULT_PHOTO_EXTENSION)
            .photoUrl(DEFAULT_PHOTO_URL)
            .photoThumbnailUrl(DEFAULT_PHOTO_THUMBNAIL_URL);
        return photo;
    }

    @Before
    public void initTest() {
        photo = createEntity(em);
    }

    @Test
    @Transactional
    public void createPhoto() throws Exception {
        int databaseSizeBeforeCreate = photoRepository.findAll().size();

        // Create the Photo
        PhotoDTO photoDTO = photoMapper.toDto(photo);
        restPhotoMockMvc.perform(post("/api/photos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(photoDTO)))
            .andExpect(status().isCreated());

        // Validate the Photo in the database
        List<Photo> photoList = photoRepository.findAll();
        assertThat(photoList).hasSize(databaseSizeBeforeCreate + 1);
        Photo testPhoto = photoList.get(photoList.size() - 1);
        assertThat(testPhoto.getPhotoName()).isEqualTo(DEFAULT_PHOTO_NAME);
        assertThat(testPhoto.getPhotoImage()).isEqualTo(DEFAULT_PHOTO_IMAGE);
        assertThat(testPhoto.getPhotoImageContentType()).isEqualTo(DEFAULT_PHOTO_IMAGE_CONTENT_TYPE);
        assertThat(testPhoto.getPhotoExtension()).isEqualTo(DEFAULT_PHOTO_EXTENSION);
        assertThat(testPhoto.getPhotoUrl()).isEqualTo(DEFAULT_PHOTO_URL);
        assertThat(testPhoto.getPhotoThumbnailUrl()).isEqualTo(DEFAULT_PHOTO_THUMBNAIL_URL);
    }

    @Test
    @Transactional
    public void createPhotoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = photoRepository.findAll().size();

        // Create the Photo with an existing ID
        photo.setId(1L);
        PhotoDTO photoDTO = photoMapper.toDto(photo);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPhotoMockMvc.perform(post("/api/photos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(photoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Photo in the database
        List<Photo> photoList = photoRepository.findAll();
        assertThat(photoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkPhotoNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = photoRepository.findAll().size();
        // set the field null
        photo.setPhotoName(null);

        // Create the Photo, which fails.
        PhotoDTO photoDTO = photoMapper.toDto(photo);

        restPhotoMockMvc.perform(post("/api/photos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(photoDTO)))
            .andExpect(status().isBadRequest());

        List<Photo> photoList = photoRepository.findAll();
        assertThat(photoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPhotos() throws Exception {
        // Initialize the database
        photoRepository.saveAndFlush(photo);

        // Get all the photoList
        restPhotoMockMvc.perform(get("/api/photos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(photo.getId().intValue())))
            .andExpect(jsonPath("$.[*].photoName").value(hasItem(DEFAULT_PHOTO_NAME.toString())))
            .andExpect(jsonPath("$.[*].photoImageContentType").value(hasItem(DEFAULT_PHOTO_IMAGE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].photoImage").value(hasItem(Base64Utils.encodeToString(DEFAULT_PHOTO_IMAGE))))
            .andExpect(jsonPath("$.[*].photoExtension").value(hasItem(DEFAULT_PHOTO_EXTENSION.toString())))
            .andExpect(jsonPath("$.[*].photoUrl").value(hasItem(DEFAULT_PHOTO_URL.toString())))
            .andExpect(jsonPath("$.[*].photoThumbnailUrl").value(hasItem(DEFAULT_PHOTO_THUMBNAIL_URL.toString())));
    }
    

    @Test
    @Transactional
    public void getPhoto() throws Exception {
        // Initialize the database
        photoRepository.saveAndFlush(photo);

        // Get the photo
        restPhotoMockMvc.perform(get("/api/photos/{id}", photo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(photo.getId().intValue()))
            .andExpect(jsonPath("$.photoName").value(DEFAULT_PHOTO_NAME.toString()))
            .andExpect(jsonPath("$.photoImageContentType").value(DEFAULT_PHOTO_IMAGE_CONTENT_TYPE))
            .andExpect(jsonPath("$.photoImage").value(Base64Utils.encodeToString(DEFAULT_PHOTO_IMAGE)))
            .andExpect(jsonPath("$.photoExtension").value(DEFAULT_PHOTO_EXTENSION.toString()))
            .andExpect(jsonPath("$.photoUrl").value(DEFAULT_PHOTO_URL.toString()))
            .andExpect(jsonPath("$.photoThumbnailUrl").value(DEFAULT_PHOTO_THUMBNAIL_URL.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingPhoto() throws Exception {
        // Get the photo
        restPhotoMockMvc.perform(get("/api/photos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePhoto() throws Exception {
        // Initialize the database
        photoRepository.saveAndFlush(photo);

        int databaseSizeBeforeUpdate = photoRepository.findAll().size();

        // Update the photo
        Photo updatedPhoto = photoRepository.findById(photo.getId()).get();
        // Disconnect from session so that the updates on updatedPhoto are not directly saved in db
        em.detach(updatedPhoto);
        updatedPhoto
            .photoName(UPDATED_PHOTO_NAME)
            .photoImage(UPDATED_PHOTO_IMAGE)
            .photoImageContentType(UPDATED_PHOTO_IMAGE_CONTENT_TYPE)
            .photoExtension(UPDATED_PHOTO_EXTENSION)
            .photoUrl(UPDATED_PHOTO_URL)
            .photoThumbnailUrl(UPDATED_PHOTO_THUMBNAIL_URL);
        PhotoDTO photoDTO = photoMapper.toDto(updatedPhoto);

        restPhotoMockMvc.perform(put("/api/photos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(photoDTO)))
            .andExpect(status().isOk());

        // Validate the Photo in the database
        List<Photo> photoList = photoRepository.findAll();
        assertThat(photoList).hasSize(databaseSizeBeforeUpdate);
        Photo testPhoto = photoList.get(photoList.size() - 1);
        assertThat(testPhoto.getPhotoName()).isEqualTo(UPDATED_PHOTO_NAME);
        assertThat(testPhoto.getPhotoImage()).isEqualTo(UPDATED_PHOTO_IMAGE);
        assertThat(testPhoto.getPhotoImageContentType()).isEqualTo(UPDATED_PHOTO_IMAGE_CONTENT_TYPE);
        assertThat(testPhoto.getPhotoExtension()).isEqualTo(UPDATED_PHOTO_EXTENSION);
        assertThat(testPhoto.getPhotoUrl()).isEqualTo(UPDATED_PHOTO_URL);
        assertThat(testPhoto.getPhotoThumbnailUrl()).isEqualTo(UPDATED_PHOTO_THUMBNAIL_URL);
    }

    @Test
    @Transactional
    public void updateNonExistingPhoto() throws Exception {
        int databaseSizeBeforeUpdate = photoRepository.findAll().size();

        // Create the Photo
        PhotoDTO photoDTO = photoMapper.toDto(photo);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restPhotoMockMvc.perform(put("/api/photos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(photoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Photo in the database
        List<Photo> photoList = photoRepository.findAll();
        assertThat(photoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePhoto() throws Exception {
        // Initialize the database
        photoRepository.saveAndFlush(photo);

        int databaseSizeBeforeDelete = photoRepository.findAll().size();

        // Get the photo
        restPhotoMockMvc.perform(delete("/api/photos/{id}", photo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Photo> photoList = photoRepository.findAll();
        assertThat(photoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Photo.class);
        Photo photo1 = new Photo();
        photo1.setId(1L);
        Photo photo2 = new Photo();
        photo2.setId(photo1.getId());
        assertThat(photo1).isEqualTo(photo2);
        photo2.setId(2L);
        assertThat(photo1).isNotEqualTo(photo2);
        photo1.setId(null);
        assertThat(photo1).isNotEqualTo(photo2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PhotoDTO.class);
        PhotoDTO photoDTO1 = new PhotoDTO();
        photoDTO1.setId(1L);
        PhotoDTO photoDTO2 = new PhotoDTO();
        assertThat(photoDTO1).isNotEqualTo(photoDTO2);
        photoDTO2.setId(photoDTO1.getId());
        assertThat(photoDTO1).isEqualTo(photoDTO2);
        photoDTO2.setId(2L);
        assertThat(photoDTO1).isNotEqualTo(photoDTO2);
        photoDTO1.setId(null);
        assertThat(photoDTO1).isNotEqualTo(photoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(photoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(photoMapper.fromId(null)).isNull();
    }
}
