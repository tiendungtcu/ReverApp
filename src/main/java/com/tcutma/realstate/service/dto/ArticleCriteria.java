package com.tcutma.realstate.service.dto;

import java.io.Serializable;
import com.tcutma.realstate.domain.enumeration.BlogStatus;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

import io.github.jhipster.service.filter.InstantFilter;




/**
 * Criteria class for the Article entity. This class is used in ArticleResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /articles?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ArticleCriteria implements Serializable {
    /**
     * Class for filtering BlogStatus
     */
    public static class BlogStatusFilter extends Filter<BlogStatus> {
    }

    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private StringFilter articleTitle;

    private BlogStatusFilter articleStatus;

    private InstantFilter articleDate;

    private LongFilter articleSeenCount;

    private LongFilter authorId;

    private LongFilter categoryId;

    public ArticleCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(StringFilter articleTitle) {
        this.articleTitle = articleTitle;
    }

    public BlogStatusFilter getArticleStatus() {
        return articleStatus;
    }

    public void setArticleStatus(BlogStatusFilter articleStatus) {
        this.articleStatus = articleStatus;
    }

    public InstantFilter getArticleDate() {
        return articleDate;
    }

    public void setArticleDate(InstantFilter articleDate) {
        this.articleDate = articleDate;
    }

    public LongFilter getArticleSeenCount() {
        return articleSeenCount;
    }

    public void setArticleSeenCount(LongFilter articleSeenCount) {
        this.articleSeenCount = articleSeenCount;
    }

    public LongFilter getAuthorId() {
        return authorId;
    }

    public void setAuthorId(LongFilter authorId) {
        this.authorId = authorId;
    }

    public LongFilter getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(LongFilter categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "ArticleCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (articleTitle != null ? "articleTitle=" + articleTitle + ", " : "") +
                (articleStatus != null ? "articleStatus=" + articleStatus + ", " : "") +
                (articleDate != null ? "articleDate=" + articleDate + ", " : "") +
                (articleSeenCount != null ? "articleSeenCount=" + articleSeenCount + ", " : "") +
                (authorId != null ? "authorId=" + authorId + ", " : "") +
                (categoryId != null ? "categoryId=" + categoryId + ", " : "") +
            "}";
    }

}
