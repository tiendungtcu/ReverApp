package com.tcutma.realstate.web.rest;

import com.tcutma.realstate.RiverApp;

import com.tcutma.realstate.domain.BlogPost;
import com.tcutma.realstate.domain.Comment;
import com.tcutma.realstate.domain.Category;
import com.tcutma.realstate.domain.User;
import com.tcutma.realstate.domain.Project;
import com.tcutma.realstate.repository.BlogPostRepository;
import com.tcutma.realstate.service.BlogPostService;
import com.tcutma.realstate.service.dto.BlogPostDTO;
import com.tcutma.realstate.service.mapper.BlogPostMapper;
import com.tcutma.realstate.web.rest.errors.ExceptionTranslator;
import com.tcutma.realstate.service.dto.BlogPostCriteria;
import com.tcutma.realstate.service.BlogPostQueryService;

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
import java.time.temporal.ChronoUnit;
import java.util.List;


import static com.tcutma.realstate.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.tcutma.realstate.domain.enumeration.BlogStatus;
/**
 * Test class for the BlogPostResource REST controller.
 *
 * @see BlogPostResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RiverApp.class)
public class BlogPostResourceIntTest {

    private static final String DEFAULT_POST_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_POST_TITLE = "BBBBBBBBBB";

    private static final BlogStatus DEFAULT_POST_STATUS = BlogStatus.PUBLISHED;
    private static final BlogStatus UPDATED_POST_STATUS = BlogStatus.DRAFT;

    private static final Instant DEFAULT_POST_CREATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_POST_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_POST_PUBLISH_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_POST_PUBLISH_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_POST_UPDATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_POST_UPDATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Long DEFAULT_POST_SEEN_COUNT = 1L;
    private static final Long UPDATED_POST_SEEN_COUNT = 2L;

    private static final String DEFAULT_POST_CONTENT = "AAAAAAAAAA";
    private static final String UPDATED_POST_CONTENT = "BBBBBBBBBB";

    @Autowired
    private BlogPostRepository blogPostRepository;


    @Autowired
    private BlogPostMapper blogPostMapper;
    

    @Autowired
    private BlogPostService blogPostService;

    @Autowired
    private BlogPostQueryService blogPostQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restBlogPostMockMvc;

    private BlogPost blogPost;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BlogPostResource blogPostResource = new BlogPostResource(blogPostService, blogPostQueryService);
        this.restBlogPostMockMvc = MockMvcBuilders.standaloneSetup(blogPostResource)
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
    public static BlogPost createEntity(EntityManager em) {
        BlogPost blogPost = new BlogPost()
            .postTitle(DEFAULT_POST_TITLE)
            .postStatus(DEFAULT_POST_STATUS)
            .postCreatedDate(DEFAULT_POST_CREATED_DATE)
            .postPublishDate(DEFAULT_POST_PUBLISH_DATE)
            .postUpdateDate(DEFAULT_POST_UPDATE_DATE)
            .postSeenCount(DEFAULT_POST_SEEN_COUNT)
            .postContent(DEFAULT_POST_CONTENT);
        return blogPost;
    }

    @Before
    public void initTest() {
        blogPost = createEntity(em);
    }

    @Test
    @Transactional
    public void createBlogPost() throws Exception {
        int databaseSizeBeforeCreate = blogPostRepository.findAll().size();

        // Create the BlogPost
        BlogPostDTO blogPostDTO = blogPostMapper.toDto(blogPost);
        restBlogPostMockMvc.perform(post("/api/blog-posts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(blogPostDTO)))
            .andExpect(status().isCreated());

        // Validate the BlogPost in the database
        List<BlogPost> blogPostList = blogPostRepository.findAll();
        assertThat(blogPostList).hasSize(databaseSizeBeforeCreate + 1);
        BlogPost testBlogPost = blogPostList.get(blogPostList.size() - 1);
        assertThat(testBlogPost.getPostTitle()).isEqualTo(DEFAULT_POST_TITLE);
        assertThat(testBlogPost.getPostStatus()).isEqualTo(DEFAULT_POST_STATUS);
        assertThat(testBlogPost.getPostCreatedDate()).isEqualTo(DEFAULT_POST_CREATED_DATE);
        assertThat(testBlogPost.getPostPublishDate()).isEqualTo(DEFAULT_POST_PUBLISH_DATE);
        assertThat(testBlogPost.getPostUpdateDate()).isEqualTo(DEFAULT_POST_UPDATE_DATE);
        assertThat(testBlogPost.getPostSeenCount()).isEqualTo(DEFAULT_POST_SEEN_COUNT);
        assertThat(testBlogPost.getPostContent()).isEqualTo(DEFAULT_POST_CONTENT);
    }

    @Test
    @Transactional
    public void createBlogPostWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = blogPostRepository.findAll().size();

        // Create the BlogPost with an existing ID
        blogPost.setId(1L);
        BlogPostDTO blogPostDTO = blogPostMapper.toDto(blogPost);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBlogPostMockMvc.perform(post("/api/blog-posts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(blogPostDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BlogPost in the database
        List<BlogPost> blogPostList = blogPostRepository.findAll();
        assertThat(blogPostList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkPostTitleIsRequired() throws Exception {
        int databaseSizeBeforeTest = blogPostRepository.findAll().size();
        // set the field null
        blogPost.setPostTitle(null);

        // Create the BlogPost, which fails.
        BlogPostDTO blogPostDTO = blogPostMapper.toDto(blogPost);

        restBlogPostMockMvc.perform(post("/api/blog-posts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(blogPostDTO)))
            .andExpect(status().isBadRequest());

        List<BlogPost> blogPostList = blogPostRepository.findAll();
        assertThat(blogPostList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBlogPosts() throws Exception {
        // Initialize the database
        blogPostRepository.saveAndFlush(blogPost);

        // Get all the blogPostList
        restBlogPostMockMvc.perform(get("/api/blog-posts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(blogPost.getId().intValue())))
            .andExpect(jsonPath("$.[*].postTitle").value(hasItem(DEFAULT_POST_TITLE.toString())))
            .andExpect(jsonPath("$.[*].postStatus").value(hasItem(DEFAULT_POST_STATUS.toString())))
            .andExpect(jsonPath("$.[*].postCreatedDate").value(hasItem(DEFAULT_POST_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].postPublishDate").value(hasItem(DEFAULT_POST_PUBLISH_DATE.toString())))
            .andExpect(jsonPath("$.[*].postUpdateDate").value(hasItem(DEFAULT_POST_UPDATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].postSeenCount").value(hasItem(DEFAULT_POST_SEEN_COUNT.intValue())))
            .andExpect(jsonPath("$.[*].postContent").value(hasItem(DEFAULT_POST_CONTENT.toString())));
    }
    

    @Test
    @Transactional
    public void getBlogPost() throws Exception {
        // Initialize the database
        blogPostRepository.saveAndFlush(blogPost);

        // Get the blogPost
        restBlogPostMockMvc.perform(get("/api/blog-posts/{id}", blogPost.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(blogPost.getId().intValue()))
            .andExpect(jsonPath("$.postTitle").value(DEFAULT_POST_TITLE.toString()))
            .andExpect(jsonPath("$.postStatus").value(DEFAULT_POST_STATUS.toString()))
            .andExpect(jsonPath("$.postCreatedDate").value(DEFAULT_POST_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.postPublishDate").value(DEFAULT_POST_PUBLISH_DATE.toString()))
            .andExpect(jsonPath("$.postUpdateDate").value(DEFAULT_POST_UPDATE_DATE.toString()))
            .andExpect(jsonPath("$.postSeenCount").value(DEFAULT_POST_SEEN_COUNT.intValue()))
            .andExpect(jsonPath("$.postContent").value(DEFAULT_POST_CONTENT.toString()));
    }

    @Test
    @Transactional
    public void getAllBlogPostsByPostTitleIsEqualToSomething() throws Exception {
        // Initialize the database
        blogPostRepository.saveAndFlush(blogPost);

        // Get all the blogPostList where postTitle equals to DEFAULT_POST_TITLE
        defaultBlogPostShouldBeFound("postTitle.equals=" + DEFAULT_POST_TITLE);

        // Get all the blogPostList where postTitle equals to UPDATED_POST_TITLE
        defaultBlogPostShouldNotBeFound("postTitle.equals=" + UPDATED_POST_TITLE);
    }

    @Test
    @Transactional
    public void getAllBlogPostsByPostTitleIsInShouldWork() throws Exception {
        // Initialize the database
        blogPostRepository.saveAndFlush(blogPost);

        // Get all the blogPostList where postTitle in DEFAULT_POST_TITLE or UPDATED_POST_TITLE
        defaultBlogPostShouldBeFound("postTitle.in=" + DEFAULT_POST_TITLE + "," + UPDATED_POST_TITLE);

        // Get all the blogPostList where postTitle equals to UPDATED_POST_TITLE
        defaultBlogPostShouldNotBeFound("postTitle.in=" + UPDATED_POST_TITLE);
    }

    @Test
    @Transactional
    public void getAllBlogPostsByPostTitleIsNullOrNotNull() throws Exception {
        // Initialize the database
        blogPostRepository.saveAndFlush(blogPost);

        // Get all the blogPostList where postTitle is not null
        defaultBlogPostShouldBeFound("postTitle.specified=true");

        // Get all the blogPostList where postTitle is null
        defaultBlogPostShouldNotBeFound("postTitle.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlogPostsByPostStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        blogPostRepository.saveAndFlush(blogPost);

        // Get all the blogPostList where postStatus equals to DEFAULT_POST_STATUS
        defaultBlogPostShouldBeFound("postStatus.equals=" + DEFAULT_POST_STATUS);

        // Get all the blogPostList where postStatus equals to UPDATED_POST_STATUS
        defaultBlogPostShouldNotBeFound("postStatus.equals=" + UPDATED_POST_STATUS);
    }

    @Test
    @Transactional
    public void getAllBlogPostsByPostStatusIsInShouldWork() throws Exception {
        // Initialize the database
        blogPostRepository.saveAndFlush(blogPost);

        // Get all the blogPostList where postStatus in DEFAULT_POST_STATUS or UPDATED_POST_STATUS
        defaultBlogPostShouldBeFound("postStatus.in=" + DEFAULT_POST_STATUS + "," + UPDATED_POST_STATUS);

        // Get all the blogPostList where postStatus equals to UPDATED_POST_STATUS
        defaultBlogPostShouldNotBeFound("postStatus.in=" + UPDATED_POST_STATUS);
    }

    @Test
    @Transactional
    public void getAllBlogPostsByPostStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        blogPostRepository.saveAndFlush(blogPost);

        // Get all the blogPostList where postStatus is not null
        defaultBlogPostShouldBeFound("postStatus.specified=true");

        // Get all the blogPostList where postStatus is null
        defaultBlogPostShouldNotBeFound("postStatus.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlogPostsByPostCreatedDateIsEqualToSomething() throws Exception {
        // Initialize the database
        blogPostRepository.saveAndFlush(blogPost);

        // Get all the blogPostList where postCreatedDate equals to DEFAULT_POST_CREATED_DATE
        defaultBlogPostShouldBeFound("postCreatedDate.equals=" + DEFAULT_POST_CREATED_DATE);

        // Get all the blogPostList where postCreatedDate equals to UPDATED_POST_CREATED_DATE
        defaultBlogPostShouldNotBeFound("postCreatedDate.equals=" + UPDATED_POST_CREATED_DATE);
    }

    @Test
    @Transactional
    public void getAllBlogPostsByPostCreatedDateIsInShouldWork() throws Exception {
        // Initialize the database
        blogPostRepository.saveAndFlush(blogPost);

        // Get all the blogPostList where postCreatedDate in DEFAULT_POST_CREATED_DATE or UPDATED_POST_CREATED_DATE
        defaultBlogPostShouldBeFound("postCreatedDate.in=" + DEFAULT_POST_CREATED_DATE + "," + UPDATED_POST_CREATED_DATE);

        // Get all the blogPostList where postCreatedDate equals to UPDATED_POST_CREATED_DATE
        defaultBlogPostShouldNotBeFound("postCreatedDate.in=" + UPDATED_POST_CREATED_DATE);
    }

    @Test
    @Transactional
    public void getAllBlogPostsByPostCreatedDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        blogPostRepository.saveAndFlush(blogPost);

        // Get all the blogPostList where postCreatedDate is not null
        defaultBlogPostShouldBeFound("postCreatedDate.specified=true");

        // Get all the blogPostList where postCreatedDate is null
        defaultBlogPostShouldNotBeFound("postCreatedDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlogPostsByPostPublishDateIsEqualToSomething() throws Exception {
        // Initialize the database
        blogPostRepository.saveAndFlush(blogPost);

        // Get all the blogPostList where postPublishDate equals to DEFAULT_POST_PUBLISH_DATE
        defaultBlogPostShouldBeFound("postPublishDate.equals=" + DEFAULT_POST_PUBLISH_DATE);

        // Get all the blogPostList where postPublishDate equals to UPDATED_POST_PUBLISH_DATE
        defaultBlogPostShouldNotBeFound("postPublishDate.equals=" + UPDATED_POST_PUBLISH_DATE);
    }

    @Test
    @Transactional
    public void getAllBlogPostsByPostPublishDateIsInShouldWork() throws Exception {
        // Initialize the database
        blogPostRepository.saveAndFlush(blogPost);

        // Get all the blogPostList where postPublishDate in DEFAULT_POST_PUBLISH_DATE or UPDATED_POST_PUBLISH_DATE
        defaultBlogPostShouldBeFound("postPublishDate.in=" + DEFAULT_POST_PUBLISH_DATE + "," + UPDATED_POST_PUBLISH_DATE);

        // Get all the blogPostList where postPublishDate equals to UPDATED_POST_PUBLISH_DATE
        defaultBlogPostShouldNotBeFound("postPublishDate.in=" + UPDATED_POST_PUBLISH_DATE);
    }

    @Test
    @Transactional
    public void getAllBlogPostsByPostPublishDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        blogPostRepository.saveAndFlush(blogPost);

        // Get all the blogPostList where postPublishDate is not null
        defaultBlogPostShouldBeFound("postPublishDate.specified=true");

        // Get all the blogPostList where postPublishDate is null
        defaultBlogPostShouldNotBeFound("postPublishDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlogPostsByPostUpdateDateIsEqualToSomething() throws Exception {
        // Initialize the database
        blogPostRepository.saveAndFlush(blogPost);

        // Get all the blogPostList where postUpdateDate equals to DEFAULT_POST_UPDATE_DATE
        defaultBlogPostShouldBeFound("postUpdateDate.equals=" + DEFAULT_POST_UPDATE_DATE);

        // Get all the blogPostList where postUpdateDate equals to UPDATED_POST_UPDATE_DATE
        defaultBlogPostShouldNotBeFound("postUpdateDate.equals=" + UPDATED_POST_UPDATE_DATE);
    }

    @Test
    @Transactional
    public void getAllBlogPostsByPostUpdateDateIsInShouldWork() throws Exception {
        // Initialize the database
        blogPostRepository.saveAndFlush(blogPost);

        // Get all the blogPostList where postUpdateDate in DEFAULT_POST_UPDATE_DATE or UPDATED_POST_UPDATE_DATE
        defaultBlogPostShouldBeFound("postUpdateDate.in=" + DEFAULT_POST_UPDATE_DATE + "," + UPDATED_POST_UPDATE_DATE);

        // Get all the blogPostList where postUpdateDate equals to UPDATED_POST_UPDATE_DATE
        defaultBlogPostShouldNotBeFound("postUpdateDate.in=" + UPDATED_POST_UPDATE_DATE);
    }

    @Test
    @Transactional
    public void getAllBlogPostsByPostUpdateDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        blogPostRepository.saveAndFlush(blogPost);

        // Get all the blogPostList where postUpdateDate is not null
        defaultBlogPostShouldBeFound("postUpdateDate.specified=true");

        // Get all the blogPostList where postUpdateDate is null
        defaultBlogPostShouldNotBeFound("postUpdateDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlogPostsByPostSeenCountIsEqualToSomething() throws Exception {
        // Initialize the database
        blogPostRepository.saveAndFlush(blogPost);

        // Get all the blogPostList where postSeenCount equals to DEFAULT_POST_SEEN_COUNT
        defaultBlogPostShouldBeFound("postSeenCount.equals=" + DEFAULT_POST_SEEN_COUNT);

        // Get all the blogPostList where postSeenCount equals to UPDATED_POST_SEEN_COUNT
        defaultBlogPostShouldNotBeFound("postSeenCount.equals=" + UPDATED_POST_SEEN_COUNT);
    }

    @Test
    @Transactional
    public void getAllBlogPostsByPostSeenCountIsInShouldWork() throws Exception {
        // Initialize the database
        blogPostRepository.saveAndFlush(blogPost);

        // Get all the blogPostList where postSeenCount in DEFAULT_POST_SEEN_COUNT or UPDATED_POST_SEEN_COUNT
        defaultBlogPostShouldBeFound("postSeenCount.in=" + DEFAULT_POST_SEEN_COUNT + "," + UPDATED_POST_SEEN_COUNT);

        // Get all the blogPostList where postSeenCount equals to UPDATED_POST_SEEN_COUNT
        defaultBlogPostShouldNotBeFound("postSeenCount.in=" + UPDATED_POST_SEEN_COUNT);
    }

    @Test
    @Transactional
    public void getAllBlogPostsByPostSeenCountIsNullOrNotNull() throws Exception {
        // Initialize the database
        blogPostRepository.saveAndFlush(blogPost);

        // Get all the blogPostList where postSeenCount is not null
        defaultBlogPostShouldBeFound("postSeenCount.specified=true");

        // Get all the blogPostList where postSeenCount is null
        defaultBlogPostShouldNotBeFound("postSeenCount.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlogPostsByPostSeenCountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        blogPostRepository.saveAndFlush(blogPost);

        // Get all the blogPostList where postSeenCount greater than or equals to DEFAULT_POST_SEEN_COUNT
        defaultBlogPostShouldBeFound("postSeenCount.greaterOrEqualThan=" + DEFAULT_POST_SEEN_COUNT);

        // Get all the blogPostList where postSeenCount greater than or equals to UPDATED_POST_SEEN_COUNT
        defaultBlogPostShouldNotBeFound("postSeenCount.greaterOrEqualThan=" + UPDATED_POST_SEEN_COUNT);
    }

    @Test
    @Transactional
    public void getAllBlogPostsByPostSeenCountIsLessThanSomething() throws Exception {
        // Initialize the database
        blogPostRepository.saveAndFlush(blogPost);

        // Get all the blogPostList where postSeenCount less than or equals to DEFAULT_POST_SEEN_COUNT
        defaultBlogPostShouldNotBeFound("postSeenCount.lessThan=" + DEFAULT_POST_SEEN_COUNT);

        // Get all the blogPostList where postSeenCount less than or equals to UPDATED_POST_SEEN_COUNT
        defaultBlogPostShouldBeFound("postSeenCount.lessThan=" + UPDATED_POST_SEEN_COUNT);
    }


    @Test
    @Transactional
    public void getAllBlogPostsByCommentIsEqualToSomething() throws Exception {
        // Initialize the database
        Comment comment = CommentResourceIntTest.createEntity(em);
        em.persist(comment);
        em.flush();
        blogPost.addComment(comment);
        blogPostRepository.saveAndFlush(blogPost);
        Long commentId = comment.getId();

        // Get all the blogPostList where comment equals to commentId
        defaultBlogPostShouldBeFound("commentId.equals=" + commentId);

        // Get all the blogPostList where comment equals to commentId + 1
        defaultBlogPostShouldNotBeFound("commentId.equals=" + (commentId + 1));
    }


    @Test
    @Transactional
    public void getAllBlogPostsByCategoryIsEqualToSomething() throws Exception {
        // Initialize the database
        Category category = CategoryResourceIntTest.createEntity(em);
        em.persist(category);
        em.flush();
        blogPost.setCategory(category);
        blogPostRepository.saveAndFlush(blogPost);
        Long categoryId = category.getId();

        // Get all the blogPostList where category equals to categoryId
        defaultBlogPostShouldBeFound("categoryId.equals=" + categoryId);

        // Get all the blogPostList where category equals to categoryId + 1
        defaultBlogPostShouldNotBeFound("categoryId.equals=" + (categoryId + 1));
    }


    @Test
    @Transactional
    public void getAllBlogPostsByUserIsEqualToSomething() throws Exception {
        // Initialize the database
        User user = UserResourceIntTest.createEntity(em);
        em.persist(user);
        em.flush();
        blogPost.setUser(user);
        blogPostRepository.saveAndFlush(blogPost);
        Long userId = user.getId();

        // Get all the blogPostList where user equals to userId
        defaultBlogPostShouldBeFound("userId.equals=" + userId);

        // Get all the blogPostList where user equals to userId + 1
        defaultBlogPostShouldNotBeFound("userId.equals=" + (userId + 1));
    }


    @Test
    @Transactional
    public void getAllBlogPostsByProjectIsEqualToSomething() throws Exception {
        // Initialize the database
        Project project = ProjectResourceIntTest.createEntity(em);
        em.persist(project);
        em.flush();
        blogPost.setProject(project);
        blogPostRepository.saveAndFlush(blogPost);
        Long projectId = project.getId();

        // Get all the blogPostList where project equals to projectId
        defaultBlogPostShouldBeFound("projectId.equals=" + projectId);

        // Get all the blogPostList where project equals to projectId + 1
        defaultBlogPostShouldNotBeFound("projectId.equals=" + (projectId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultBlogPostShouldBeFound(String filter) throws Exception {
        restBlogPostMockMvc.perform(get("/api/blog-posts?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(blogPost.getId().intValue())))
            .andExpect(jsonPath("$.[*].postTitle").value(hasItem(DEFAULT_POST_TITLE.toString())))
            .andExpect(jsonPath("$.[*].postStatus").value(hasItem(DEFAULT_POST_STATUS.toString())))
            .andExpect(jsonPath("$.[*].postCreatedDate").value(hasItem(DEFAULT_POST_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].postPublishDate").value(hasItem(DEFAULT_POST_PUBLISH_DATE.toString())))
            .andExpect(jsonPath("$.[*].postUpdateDate").value(hasItem(DEFAULT_POST_UPDATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].postSeenCount").value(hasItem(DEFAULT_POST_SEEN_COUNT.intValue())))
            .andExpect(jsonPath("$.[*].postContent").value(hasItem(DEFAULT_POST_CONTENT.toString())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultBlogPostShouldNotBeFound(String filter) throws Exception {
        restBlogPostMockMvc.perform(get("/api/blog-posts?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @Transactional
    public void getNonExistingBlogPost() throws Exception {
        // Get the blogPost
        restBlogPostMockMvc.perform(get("/api/blog-posts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBlogPost() throws Exception {
        // Initialize the database
        blogPostRepository.saveAndFlush(blogPost);

        int databaseSizeBeforeUpdate = blogPostRepository.findAll().size();

        // Update the blogPost
        BlogPost updatedBlogPost = blogPostRepository.findById(blogPost.getId()).get();
        // Disconnect from session so that the updates on updatedBlogPost are not directly saved in db
        em.detach(updatedBlogPost);
        updatedBlogPost
            .postTitle(UPDATED_POST_TITLE)
            .postStatus(UPDATED_POST_STATUS)
            .postCreatedDate(UPDATED_POST_CREATED_DATE)
            .postPublishDate(UPDATED_POST_PUBLISH_DATE)
            .postUpdateDate(UPDATED_POST_UPDATE_DATE)
            .postSeenCount(UPDATED_POST_SEEN_COUNT)
            .postContent(UPDATED_POST_CONTENT);
        BlogPostDTO blogPostDTO = blogPostMapper.toDto(updatedBlogPost);

        restBlogPostMockMvc.perform(put("/api/blog-posts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(blogPostDTO)))
            .andExpect(status().isOk());

        // Validate the BlogPost in the database
        List<BlogPost> blogPostList = blogPostRepository.findAll();
        assertThat(blogPostList).hasSize(databaseSizeBeforeUpdate);
        BlogPost testBlogPost = blogPostList.get(blogPostList.size() - 1);
        assertThat(testBlogPost.getPostTitle()).isEqualTo(UPDATED_POST_TITLE);
        assertThat(testBlogPost.getPostStatus()).isEqualTo(UPDATED_POST_STATUS);
        assertThat(testBlogPost.getPostCreatedDate()).isEqualTo(UPDATED_POST_CREATED_DATE);
        assertThat(testBlogPost.getPostPublishDate()).isEqualTo(UPDATED_POST_PUBLISH_DATE);
        assertThat(testBlogPost.getPostUpdateDate()).isEqualTo(UPDATED_POST_UPDATE_DATE);
        assertThat(testBlogPost.getPostSeenCount()).isEqualTo(UPDATED_POST_SEEN_COUNT);
        assertThat(testBlogPost.getPostContent()).isEqualTo(UPDATED_POST_CONTENT);
    }

    @Test
    @Transactional
    public void updateNonExistingBlogPost() throws Exception {
        int databaseSizeBeforeUpdate = blogPostRepository.findAll().size();

        // Create the BlogPost
        BlogPostDTO blogPostDTO = blogPostMapper.toDto(blogPost);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restBlogPostMockMvc.perform(put("/api/blog-posts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(blogPostDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BlogPost in the database
        List<BlogPost> blogPostList = blogPostRepository.findAll();
        assertThat(blogPostList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBlogPost() throws Exception {
        // Initialize the database
        blogPostRepository.saveAndFlush(blogPost);

        int databaseSizeBeforeDelete = blogPostRepository.findAll().size();

        // Get the blogPost
        restBlogPostMockMvc.perform(delete("/api/blog-posts/{id}", blogPost.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<BlogPost> blogPostList = blogPostRepository.findAll();
        assertThat(blogPostList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BlogPost.class);
        BlogPost blogPost1 = new BlogPost();
        blogPost1.setId(1L);
        BlogPost blogPost2 = new BlogPost();
        blogPost2.setId(blogPost1.getId());
        assertThat(blogPost1).isEqualTo(blogPost2);
        blogPost2.setId(2L);
        assertThat(blogPost1).isNotEqualTo(blogPost2);
        blogPost1.setId(null);
        assertThat(blogPost1).isNotEqualTo(blogPost2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BlogPostDTO.class);
        BlogPostDTO blogPostDTO1 = new BlogPostDTO();
        blogPostDTO1.setId(1L);
        BlogPostDTO blogPostDTO2 = new BlogPostDTO();
        assertThat(blogPostDTO1).isNotEqualTo(blogPostDTO2);
        blogPostDTO2.setId(blogPostDTO1.getId());
        assertThat(blogPostDTO1).isEqualTo(blogPostDTO2);
        blogPostDTO2.setId(2L);
        assertThat(blogPostDTO1).isNotEqualTo(blogPostDTO2);
        blogPostDTO1.setId(null);
        assertThat(blogPostDTO1).isNotEqualTo(blogPostDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(blogPostMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(blogPostMapper.fromId(null)).isNull();
    }
}
