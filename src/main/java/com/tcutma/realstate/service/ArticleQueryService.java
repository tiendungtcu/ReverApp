package com.tcutma.realstate.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.tcutma.realstate.domain.Article;
import com.tcutma.realstate.domain.*; // for static metamodels
import com.tcutma.realstate.repository.ArticleRepository;
import com.tcutma.realstate.service.dto.ArticleCriteria;

import com.tcutma.realstate.service.dto.ArticleDTO;
import com.tcutma.realstate.service.mapper.ArticleMapper;

/**
 * Service for executing complex queries for Article entities in the database.
 * The main input is a {@link ArticleCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ArticleDTO} or a {@link Page} of {@link ArticleDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ArticleQueryService extends QueryService<Article> {

    private final Logger log = LoggerFactory.getLogger(ArticleQueryService.class);

    private final ArticleRepository articleRepository;

    private final ArticleMapper articleMapper;

    public ArticleQueryService(ArticleRepository articleRepository, ArticleMapper articleMapper) {
        this.articleRepository = articleRepository;
        this.articleMapper = articleMapper;
    }

    /**
     * Return a {@link List} of {@link ArticleDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ArticleDTO> findByCriteria(ArticleCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Article> specification = createSpecification(criteria);
        return articleMapper.toDto(articleRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ArticleDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ArticleDTO> findByCriteria(ArticleCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Article> specification = createSpecification(criteria);
        return articleRepository.findAll(specification, page)
            .map(articleMapper::toDto);
    }

    /**
     * Function to convert ArticleCriteria to a {@link Specification}
     */
    private Specification<Article> createSpecification(ArticleCriteria criteria) {
        Specification<Article> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Article_.id));
            }
            if (criteria.getArticleTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getArticleTitle(), Article_.articleTitle));
            }
            if (criteria.getArticleStatus() != null) {
                specification = specification.and(buildSpecification(criteria.getArticleStatus(), Article_.articleStatus));
            }
            if (criteria.getArticleDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getArticleDate(), Article_.articleDate));
            }
            if (criteria.getArticleSeenCount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getArticleSeenCount(), Article_.articleSeenCount));
            }
            if (criteria.getAuthorId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getAuthorId(), Article_.author, User_.id));
            }
            if (criteria.getCategoryId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCategoryId(), Article_.category, SupportCategory_.id));
            }
        }
        return specification;
    }

}
