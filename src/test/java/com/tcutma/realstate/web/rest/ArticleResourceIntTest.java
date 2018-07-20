package com.tcutma.realstate.web.rest;

import com.tcutma.realstate.RiverApp;

import com.tcutma.realstate.domain.Article;
import com.tcutma.realstate.domain.SupportCategory;
import com.tcutma.realstate.domain.User;
import com.tcutma.realstate.repository.ArticleRepository;
import com.tcutma.realstate.service.ArticleService;
import com.tcutma.realstate.service.dto.ArticleDTO;
import com.tcutma.realstate.service.mapper.ArticleMapper;
import com.tcutma.realstate.web.rest.errors.ExceptionTranslator;
import com.tcutma.realstate.service.dto.ArticleCriteria;
import com.tcutma.realstate.service.ArticleQueryService;

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
 * Test class for the ArticleResource REST controller.
 *
 * @see ArticleResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RiverApp.class)
public class ArticleResourceIntTest {

    private static final String DEFAULT_ARTICLE_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_ARTICLE_TITLE = "BBBBBBBBBB";

    private static final BlogStatus DEFAULT_ARTICLE_STATUS = BlogStatus.PUBLISHED;
    private static final BlogStatus UPDATED_ARTICLE_STATUS = BlogStatus.DRAFT;

    private static final Instant DEFAULT_ARTICLE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_ARTICLE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Long DEFAULT_ARTICLE_SEEN_COUNT = 1L;
    private static final Long UPDATED_ARTICLE_SEEN_COUNT = 2L;

    private static final String DEFAULT_ARTICLE_CONTENT = "AAAAAAAAAA";
    private static final String UPDATED_ARTICLE_CONTENT = "BBBBBBBBBB";

    @Autowired
    private ArticleRepository articleRepository;


    @Autowired
    private ArticleMapper articleMapper;
    

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleQueryService articleQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restArticleMockMvc;

    private Article article;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ArticleResource articleResource = new ArticleResource(articleService, articleQueryService);
        this.restArticleMockMvc = MockMvcBuilders.standaloneSetup(articleResource)
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
    public static Article createEntity(EntityManager em) {
        Article article = new Article()
            .articleTitle(DEFAULT_ARTICLE_TITLE)
            .articleStatus(DEFAULT_ARTICLE_STATUS)
            .articleDate(DEFAULT_ARTICLE_DATE)
            .articleSeenCount(DEFAULT_ARTICLE_SEEN_COUNT)
            .articleContent(DEFAULT_ARTICLE_CONTENT);
        return article;
    }

    @Before
    public void initTest() {
        article = createEntity(em);
    }

    @Test
    @Transactional
    public void createArticle() throws Exception {
        int databaseSizeBeforeCreate = articleRepository.findAll().size();

        // Create the Article
        ArticleDTO articleDTO = articleMapper.toDto(article);
        restArticleMockMvc.perform(post("/api/articles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(articleDTO)))
            .andExpect(status().isCreated());

        // Validate the Article in the database
        List<Article> articleList = articleRepository.findAll();
        assertThat(articleList).hasSize(databaseSizeBeforeCreate + 1);
        Article testArticle = articleList.get(articleList.size() - 1);
        assertThat(testArticle.getArticleTitle()).isEqualTo(DEFAULT_ARTICLE_TITLE);
        assertThat(testArticle.getArticleStatus()).isEqualTo(DEFAULT_ARTICLE_STATUS);
        assertThat(testArticle.getArticleDate()).isEqualTo(DEFAULT_ARTICLE_DATE);
        assertThat(testArticle.getArticleSeenCount()).isEqualTo(DEFAULT_ARTICLE_SEEN_COUNT);
        assertThat(testArticle.getArticleContent()).isEqualTo(DEFAULT_ARTICLE_CONTENT);
    }

    @Test
    @Transactional
    public void createArticleWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = articleRepository.findAll().size();

        // Create the Article with an existing ID
        article.setId(1L);
        ArticleDTO articleDTO = articleMapper.toDto(article);

        // An entity with an existing ID cannot be created, so this API call must fail
        restArticleMockMvc.perform(post("/api/articles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(articleDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Article in the database
        List<Article> articleList = articleRepository.findAll();
        assertThat(articleList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkArticleTitleIsRequired() throws Exception {
        int databaseSizeBeforeTest = articleRepository.findAll().size();
        // set the field null
        article.setArticleTitle(null);

        // Create the Article, which fails.
        ArticleDTO articleDTO = articleMapper.toDto(article);

        restArticleMockMvc.perform(post("/api/articles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(articleDTO)))
            .andExpect(status().isBadRequest());

        List<Article> articleList = articleRepository.findAll();
        assertThat(articleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllArticles() throws Exception {
        // Initialize the database
        articleRepository.saveAndFlush(article);

        // Get all the articleList
        restArticleMockMvc.perform(get("/api/articles?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(article.getId().intValue())))
            .andExpect(jsonPath("$.[*].articleTitle").value(hasItem(DEFAULT_ARTICLE_TITLE.toString())))
            .andExpect(jsonPath("$.[*].articleStatus").value(hasItem(DEFAULT_ARTICLE_STATUS.toString())))
            .andExpect(jsonPath("$.[*].articleDate").value(hasItem(DEFAULT_ARTICLE_DATE.toString())))
            .andExpect(jsonPath("$.[*].articleSeenCount").value(hasItem(DEFAULT_ARTICLE_SEEN_COUNT.intValue())))
            .andExpect(jsonPath("$.[*].articleContent").value(hasItem(DEFAULT_ARTICLE_CONTENT.toString())));
    }
    

    @Test
    @Transactional
    public void getArticle() throws Exception {
        // Initialize the database
        articleRepository.saveAndFlush(article);

        // Get the article
        restArticleMockMvc.perform(get("/api/articles/{id}", article.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(article.getId().intValue()))
            .andExpect(jsonPath("$.articleTitle").value(DEFAULT_ARTICLE_TITLE.toString()))
            .andExpect(jsonPath("$.articleStatus").value(DEFAULT_ARTICLE_STATUS.toString()))
            .andExpect(jsonPath("$.articleDate").value(DEFAULT_ARTICLE_DATE.toString()))
            .andExpect(jsonPath("$.articleSeenCount").value(DEFAULT_ARTICLE_SEEN_COUNT.intValue()))
            .andExpect(jsonPath("$.articleContent").value(DEFAULT_ARTICLE_CONTENT.toString()));
    }

    @Test
    @Transactional
    public void getAllArticlesByArticleTitleIsEqualToSomething() throws Exception {
        // Initialize the database
        articleRepository.saveAndFlush(article);

        // Get all the articleList where articleTitle equals to DEFAULT_ARTICLE_TITLE
        defaultArticleShouldBeFound("articleTitle.equals=" + DEFAULT_ARTICLE_TITLE);

        // Get all the articleList where articleTitle equals to UPDATED_ARTICLE_TITLE
        defaultArticleShouldNotBeFound("articleTitle.equals=" + UPDATED_ARTICLE_TITLE);
    }

    @Test
    @Transactional
    public void getAllArticlesByArticleTitleIsInShouldWork() throws Exception {
        // Initialize the database
        articleRepository.saveAndFlush(article);

        // Get all the articleList where articleTitle in DEFAULT_ARTICLE_TITLE or UPDATED_ARTICLE_TITLE
        defaultArticleShouldBeFound("articleTitle.in=" + DEFAULT_ARTICLE_TITLE + "," + UPDATED_ARTICLE_TITLE);

        // Get all the articleList where articleTitle equals to UPDATED_ARTICLE_TITLE
        defaultArticleShouldNotBeFound("articleTitle.in=" + UPDATED_ARTICLE_TITLE);
    }

    @Test
    @Transactional
    public void getAllArticlesByArticleTitleIsNullOrNotNull() throws Exception {
        // Initialize the database
        articleRepository.saveAndFlush(article);

        // Get all the articleList where articleTitle is not null
        defaultArticleShouldBeFound("articleTitle.specified=true");

        // Get all the articleList where articleTitle is null
        defaultArticleShouldNotBeFound("articleTitle.specified=false");
    }

    @Test
    @Transactional
    public void getAllArticlesByArticleStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        articleRepository.saveAndFlush(article);

        // Get all the articleList where articleStatus equals to DEFAULT_ARTICLE_STATUS
        defaultArticleShouldBeFound("articleStatus.equals=" + DEFAULT_ARTICLE_STATUS);

        // Get all the articleList where articleStatus equals to UPDATED_ARTICLE_STATUS
        defaultArticleShouldNotBeFound("articleStatus.equals=" + UPDATED_ARTICLE_STATUS);
    }

    @Test
    @Transactional
    public void getAllArticlesByArticleStatusIsInShouldWork() throws Exception {
        // Initialize the database
        articleRepository.saveAndFlush(article);

        // Get all the articleList where articleStatus in DEFAULT_ARTICLE_STATUS or UPDATED_ARTICLE_STATUS
        defaultArticleShouldBeFound("articleStatus.in=" + DEFAULT_ARTICLE_STATUS + "," + UPDATED_ARTICLE_STATUS);

        // Get all the articleList where articleStatus equals to UPDATED_ARTICLE_STATUS
        defaultArticleShouldNotBeFound("articleStatus.in=" + UPDATED_ARTICLE_STATUS);
    }

    @Test
    @Transactional
    public void getAllArticlesByArticleStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        articleRepository.saveAndFlush(article);

        // Get all the articleList where articleStatus is not null
        defaultArticleShouldBeFound("articleStatus.specified=true");

        // Get all the articleList where articleStatus is null
        defaultArticleShouldNotBeFound("articleStatus.specified=false");
    }

    @Test
    @Transactional
    public void getAllArticlesByArticleDateIsEqualToSomething() throws Exception {
        // Initialize the database
        articleRepository.saveAndFlush(article);

        // Get all the articleList where articleDate equals to DEFAULT_ARTICLE_DATE
        defaultArticleShouldBeFound("articleDate.equals=" + DEFAULT_ARTICLE_DATE);

        // Get all the articleList where articleDate equals to UPDATED_ARTICLE_DATE
        defaultArticleShouldNotBeFound("articleDate.equals=" + UPDATED_ARTICLE_DATE);
    }

    @Test
    @Transactional
    public void getAllArticlesByArticleDateIsInShouldWork() throws Exception {
        // Initialize the database
        articleRepository.saveAndFlush(article);

        // Get all the articleList where articleDate in DEFAULT_ARTICLE_DATE or UPDATED_ARTICLE_DATE
        defaultArticleShouldBeFound("articleDate.in=" + DEFAULT_ARTICLE_DATE + "," + UPDATED_ARTICLE_DATE);

        // Get all the articleList where articleDate equals to UPDATED_ARTICLE_DATE
        defaultArticleShouldNotBeFound("articleDate.in=" + UPDATED_ARTICLE_DATE);
    }

    @Test
    @Transactional
    public void getAllArticlesByArticleDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        articleRepository.saveAndFlush(article);

        // Get all the articleList where articleDate is not null
        defaultArticleShouldBeFound("articleDate.specified=true");

        // Get all the articleList where articleDate is null
        defaultArticleShouldNotBeFound("articleDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllArticlesByArticleSeenCountIsEqualToSomething() throws Exception {
        // Initialize the database
        articleRepository.saveAndFlush(article);

        // Get all the articleList where articleSeenCount equals to DEFAULT_ARTICLE_SEEN_COUNT
        defaultArticleShouldBeFound("articleSeenCount.equals=" + DEFAULT_ARTICLE_SEEN_COUNT);

        // Get all the articleList where articleSeenCount equals to UPDATED_ARTICLE_SEEN_COUNT
        defaultArticleShouldNotBeFound("articleSeenCount.equals=" + UPDATED_ARTICLE_SEEN_COUNT);
    }

    @Test
    @Transactional
    public void getAllArticlesByArticleSeenCountIsInShouldWork() throws Exception {
        // Initialize the database
        articleRepository.saveAndFlush(article);

        // Get all the articleList where articleSeenCount in DEFAULT_ARTICLE_SEEN_COUNT or UPDATED_ARTICLE_SEEN_COUNT
        defaultArticleShouldBeFound("articleSeenCount.in=" + DEFAULT_ARTICLE_SEEN_COUNT + "," + UPDATED_ARTICLE_SEEN_COUNT);

        // Get all the articleList where articleSeenCount equals to UPDATED_ARTICLE_SEEN_COUNT
        defaultArticleShouldNotBeFound("articleSeenCount.in=" + UPDATED_ARTICLE_SEEN_COUNT);
    }

    @Test
    @Transactional
    public void getAllArticlesByArticleSeenCountIsNullOrNotNull() throws Exception {
        // Initialize the database
        articleRepository.saveAndFlush(article);

        // Get all the articleList where articleSeenCount is not null
        defaultArticleShouldBeFound("articleSeenCount.specified=true");

        // Get all the articleList where articleSeenCount is null
        defaultArticleShouldNotBeFound("articleSeenCount.specified=false");
    }

    @Test
    @Transactional
    public void getAllArticlesByArticleSeenCountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        articleRepository.saveAndFlush(article);

        // Get all the articleList where articleSeenCount greater than or equals to DEFAULT_ARTICLE_SEEN_COUNT
        defaultArticleShouldBeFound("articleSeenCount.greaterOrEqualThan=" + DEFAULT_ARTICLE_SEEN_COUNT);

        // Get all the articleList where articleSeenCount greater than or equals to UPDATED_ARTICLE_SEEN_COUNT
        defaultArticleShouldNotBeFound("articleSeenCount.greaterOrEqualThan=" + UPDATED_ARTICLE_SEEN_COUNT);
    }

    @Test
    @Transactional
    public void getAllArticlesByArticleSeenCountIsLessThanSomething() throws Exception {
        // Initialize the database
        articleRepository.saveAndFlush(article);

        // Get all the articleList where articleSeenCount less than or equals to DEFAULT_ARTICLE_SEEN_COUNT
        defaultArticleShouldNotBeFound("articleSeenCount.lessThan=" + DEFAULT_ARTICLE_SEEN_COUNT);

        // Get all the articleList where articleSeenCount less than or equals to UPDATED_ARTICLE_SEEN_COUNT
        defaultArticleShouldBeFound("articleSeenCount.lessThan=" + UPDATED_ARTICLE_SEEN_COUNT);
    }


    @Test
    @Transactional
    public void getAllArticlesByCategoryIsEqualToSomething() throws Exception {
        // Initialize the database
        SupportCategory category = SupportCategoryResourceIntTest.createEntity(em);
        em.persist(category);
        em.flush();
        article.setCategory(category);
        articleRepository.saveAndFlush(article);
        Long categoryId = category.getId();

        // Get all the articleList where category equals to categoryId
        defaultArticleShouldBeFound("categoryId.equals=" + categoryId);

        // Get all the articleList where category equals to categoryId + 1
        defaultArticleShouldNotBeFound("categoryId.equals=" + (categoryId + 1));
    }


    @Test
    @Transactional
    public void getAllArticlesByUserIsEqualToSomething() throws Exception {
        // Initialize the database
        User user = UserResourceIntTest.createEntity(em);
        em.persist(user);
        em.flush();
        article.setUser(user);
        articleRepository.saveAndFlush(article);
        Long userId = user.getId();

        // Get all the articleList where user equals to userId
        defaultArticleShouldBeFound("userId.equals=" + userId);

        // Get all the articleList where user equals to userId + 1
        defaultArticleShouldNotBeFound("userId.equals=" + (userId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultArticleShouldBeFound(String filter) throws Exception {
        restArticleMockMvc.perform(get("/api/articles?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(article.getId().intValue())))
            .andExpect(jsonPath("$.[*].articleTitle").value(hasItem(DEFAULT_ARTICLE_TITLE.toString())))
            .andExpect(jsonPath("$.[*].articleStatus").value(hasItem(DEFAULT_ARTICLE_STATUS.toString())))
            .andExpect(jsonPath("$.[*].articleDate").value(hasItem(DEFAULT_ARTICLE_DATE.toString())))
            .andExpect(jsonPath("$.[*].articleSeenCount").value(hasItem(DEFAULT_ARTICLE_SEEN_COUNT.intValue())))
            .andExpect(jsonPath("$.[*].articleContent").value(hasItem(DEFAULT_ARTICLE_CONTENT.toString())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultArticleShouldNotBeFound(String filter) throws Exception {
        restArticleMockMvc.perform(get("/api/articles?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @Transactional
    public void getNonExistingArticle() throws Exception {
        // Get the article
        restArticleMockMvc.perform(get("/api/articles/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateArticle() throws Exception {
        // Initialize the database
        articleRepository.saveAndFlush(article);

        int databaseSizeBeforeUpdate = articleRepository.findAll().size();

        // Update the article
        Article updatedArticle = articleRepository.findById(article.getId()).get();
        // Disconnect from session so that the updates on updatedArticle are not directly saved in db
        em.detach(updatedArticle);
        updatedArticle
            .articleTitle(UPDATED_ARTICLE_TITLE)
            .articleStatus(UPDATED_ARTICLE_STATUS)
            .articleDate(UPDATED_ARTICLE_DATE)
            .articleSeenCount(UPDATED_ARTICLE_SEEN_COUNT)
            .articleContent(UPDATED_ARTICLE_CONTENT);
        ArticleDTO articleDTO = articleMapper.toDto(updatedArticle);

        restArticleMockMvc.perform(put("/api/articles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(articleDTO)))
            .andExpect(status().isOk());

        // Validate the Article in the database
        List<Article> articleList = articleRepository.findAll();
        assertThat(articleList).hasSize(databaseSizeBeforeUpdate);
        Article testArticle = articleList.get(articleList.size() - 1);
        assertThat(testArticle.getArticleTitle()).isEqualTo(UPDATED_ARTICLE_TITLE);
        assertThat(testArticle.getArticleStatus()).isEqualTo(UPDATED_ARTICLE_STATUS);
        assertThat(testArticle.getArticleDate()).isEqualTo(UPDATED_ARTICLE_DATE);
        assertThat(testArticle.getArticleSeenCount()).isEqualTo(UPDATED_ARTICLE_SEEN_COUNT);
        assertThat(testArticle.getArticleContent()).isEqualTo(UPDATED_ARTICLE_CONTENT);
    }

    @Test
    @Transactional
    public void updateNonExistingArticle() throws Exception {
        int databaseSizeBeforeUpdate = articleRepository.findAll().size();

        // Create the Article
        ArticleDTO articleDTO = articleMapper.toDto(article);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restArticleMockMvc.perform(put("/api/articles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(articleDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Article in the database
        List<Article> articleList = articleRepository.findAll();
        assertThat(articleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteArticle() throws Exception {
        // Initialize the database
        articleRepository.saveAndFlush(article);

        int databaseSizeBeforeDelete = articleRepository.findAll().size();

        // Get the article
        restArticleMockMvc.perform(delete("/api/articles/{id}", article.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Article> articleList = articleRepository.findAll();
        assertThat(articleList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Article.class);
        Article article1 = new Article();
        article1.setId(1L);
        Article article2 = new Article();
        article2.setId(article1.getId());
        assertThat(article1).isEqualTo(article2);
        article2.setId(2L);
        assertThat(article1).isNotEqualTo(article2);
        article1.setId(null);
        assertThat(article1).isNotEqualTo(article2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ArticleDTO.class);
        ArticleDTO articleDTO1 = new ArticleDTO();
        articleDTO1.setId(1L);
        ArticleDTO articleDTO2 = new ArticleDTO();
        assertThat(articleDTO1).isNotEqualTo(articleDTO2);
        articleDTO2.setId(articleDTO1.getId());
        assertThat(articleDTO1).isEqualTo(articleDTO2);
        articleDTO2.setId(2L);
        assertThat(articleDTO1).isNotEqualTo(articleDTO2);
        articleDTO1.setId(null);
        assertThat(articleDTO1).isNotEqualTo(articleDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(articleMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(articleMapper.fromId(null)).isNull();
    }
}
