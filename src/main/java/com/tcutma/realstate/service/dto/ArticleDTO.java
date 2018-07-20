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
    private String articleTitle;

    private BlogStatus articleStatus;

    private Instant articleDate;

    private Long articleSeenCount;

    @Lob
    private String articleContent;

    private Long categoryId;

    private Long userId;

    private String userLogin;

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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long supportCategoryId) {
        this.categoryId = supportCategoryId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
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
            ", category=" + getCategoryId() +
            ", user=" + getUserId() +
            ", user='" + getUserLogin() + "'" +
            "}";
    }
}
