package com.tcutma.realstate.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;
import com.tcutma.realstate.domain.enumeration.BlogStatus;

/**
 * A DTO for the Article entity.
 */
public class ArticleDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 128)
    private String articleTitle;

    private BlogStatus articleStatus;

    private Instant articleDate;

    private Long articleSeenCount;

    @Lob
    private String articleContent;

    private Long authorId;

    private String authorLogin;

    private Long categoryId;

    private String categoryCategoryName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public BlogStatus getArticleStatus() {
        return articleStatus;
    }

    public void setArticleStatus(BlogStatus articleStatus) {
        this.articleStatus = articleStatus;
    }

    public Instant getArticleDate() {
        return articleDate;
    }

    public void setArticleDate(Instant articleDate) {
        this.articleDate = articleDate;
    }

    public Long getArticleSeenCount() {
        return articleSeenCount;
    }

    public void setArticleSeenCount(Long articleSeenCount) {
        this.articleSeenCount = articleSeenCount;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long userId) {
        this.authorId = userId;
    }

    public String getAuthorLogin() {
        return authorLogin;
    }

    public void setAuthorLogin(String userLogin) {
        this.authorLogin = userLogin;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long supportCategoryId) {
        this.categoryId = supportCategoryId;
    }

    public String getCategoryCategoryName() {
        return categoryCategoryName;
    }

    public void setCategoryCategoryName(String supportCategoryCategoryName) {
        this.categoryCategoryName = supportCategoryCategoryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ArticleDTO articleDTO = (ArticleDTO) o;
        if (articleDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), articleDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ArticleDTO{" +
            "id=" + getId() +
            ", articleTitle='" + getArticleTitle() + "'" +
            ", articleStatus='" + getArticleStatus() + "'" +
            ", articleDate='" + getArticleDate() + "'" +
            ", articleSeenCount=" + getArticleSeenCount() +
            ", articleContent='" + getArticleContent() + "'" +
            ", author=" + getAuthorId() +
            ", author='" + getAuthorLogin() + "'" +
            ", category=" + getCategoryId() +
            ", category='" + getCategoryCategoryName() + "'" +
            "}";
    }
}
