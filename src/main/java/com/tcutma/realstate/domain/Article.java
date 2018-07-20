package com.tcutma.realstate.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import com.tcutma.realstate.domain.enumeration.BlogStatus;

/**
 * A Article.
 */
@Entity
@Table(name = "article")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "article_title", nullable = false)
    private String articleTitle;

    @Enumerated(EnumType.STRING)
    @Column(name = "article_status")
    private BlogStatus articleStatus;

    @Column(name = "article_date")
    private Instant articleDate;

    @Column(name = "article_seen_count")
    private Long articleSeenCount;

    @Lob
    @Column(name = "article_content")
    private String articleContent;

    @ManyToOne
    @JsonIgnoreProperties("")
    private SupportCategory category;

    @ManyToOne
    @JsonIgnoreProperties("")
    private User user;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public Article articleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
        return this;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public BlogStatus getArticleStatus() {
        return articleStatus;
    }

    public Article articleStatus(BlogStatus articleStatus) {
        this.articleStatus = articleStatus;
        return this;
    }

    public void setArticleStatus(BlogStatus articleStatus) {
        this.articleStatus = articleStatus;
    }

    public Instant getArticleDate() {
        return articleDate;
    }

    public Article articleDate(Instant articleDate) {
        this.articleDate = articleDate;
        return this;
    }

    public void setArticleDate(Instant articleDate) {
        this.articleDate = articleDate;
    }

    public Long getArticleSeenCount() {
        return articleSeenCount;
    }

    public Article articleSeenCount(Long articleSeenCount) {
        this.articleSeenCount = articleSeenCount;
        return this;
    }

    public void setArticleSeenCount(Long articleSeenCount) {
        this.articleSeenCount = articleSeenCount;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public Article articleContent(String articleContent) {
        this.articleContent = articleContent;
        return this;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public SupportCategory getCategory() {
        return category;
    }

    public Article category(SupportCategory supportCategory) {
        this.category = supportCategory;
        return this;
    }

    public void setCategory(SupportCategory supportCategory) {
        this.category = supportCategory;
    }

    public User getUser() {
        return user;
    }

    public Article user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Article article = (Article) o;
        if (article.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), article.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Article{" +
            "id=" + getId() +
            ", articleTitle='" + getArticleTitle() + "'" +
            ", articleStatus='" + getArticleStatus() + "'" +
            ", articleDate='" + getArticleDate() + "'" +
            ", articleSeenCount=" + getArticleSeenCount() +
            ", articleContent='" + getArticleContent() + "'" +
            "}";
    }
}
