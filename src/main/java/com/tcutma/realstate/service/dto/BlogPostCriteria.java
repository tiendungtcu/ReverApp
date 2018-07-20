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
 * Criteria class for the BlogPost entity. This class is used in BlogPostResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /blog-posts?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class BlogPostCriteria implements Serializable {
    /**
     * Class for filtering BlogStatus
     */
    public static class BlogStatusFilter extends Filter<BlogStatus> {
    }

    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private StringFilter postTitle;

    private BlogStatusFilter postStatus;

    private InstantFilter postCreatedDate;

    private InstantFilter postPublishDate;

    private InstantFilter postUpdateDate;

    private LongFilter postSeenCount;

    private LongFilter commentId;

    private LongFilter categoryId;

    private LongFilter userId;

    private LongFilter projectId;

    public BlogPostCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(StringFilter postTitle) {
        this.postTitle = postTitle;
    }

    public BlogStatusFilter getPostStatus() {
        return postStatus;
    }

    public void setPostStatus(BlogStatusFilter postStatus) {
        this.postStatus = postStatus;
    }

    public InstantFilter getPostCreatedDate() {
        return postCreatedDate;
    }

    public void setPostCreatedDate(InstantFilter postCreatedDate) {
        this.postCreatedDate = postCreatedDate;
    }

    public InstantFilter getPostPublishDate() {
        return postPublishDate;
    }

    public void setPostPublishDate(InstantFilter postPublishDate) {
        this.postPublishDate = postPublishDate;
    }

    public InstantFilter getPostUpdateDate() {
        return postUpdateDate;
    }

    public void setPostUpdateDate(InstantFilter postUpdateDate) {
        this.postUpdateDate = postUpdateDate;
    }

    public LongFilter getPostSeenCount() {
        return postSeenCount;
    }

    public void setPostSeenCount(LongFilter postSeenCount) {
        this.postSeenCount = postSeenCount;
    }

    public LongFilter getCommentId() {
        return commentId;
    }

    public void setCommentId(LongFilter commentId) {
        this.commentId = commentId;
    }

    public LongFilter getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(LongFilter categoryId) {
        this.categoryId = categoryId;
    }

    public LongFilter getUserId() {
        return userId;
    }

    public void setUserId(LongFilter userId) {
        this.userId = userId;
    }

    public LongFilter getProjectId() {
        return projectId;
    }

    public void setProjectId(LongFilter projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "BlogPostCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (postTitle != null ? "postTitle=" + postTitle + ", " : "") +
                (postStatus != null ? "postStatus=" + postStatus + ", " : "") +
                (postCreatedDate != null ? "postCreatedDate=" + postCreatedDate + ", " : "") +
                (postPublishDate != null ? "postPublishDate=" + postPublishDate + ", " : "") +
                (postUpdateDate != null ? "postUpdateDate=" + postUpdateDate + ", " : "") +
                (postSeenCount != null ? "postSeenCount=" + postSeenCount + ", " : "") +
                (commentId != null ? "commentId=" + commentId + ", " : "") +
                (categoryId != null ? "categoryId=" + categoryId + ", " : "") +
                (userId != null ? "userId=" + userId + ", " : "") +
                (projectId != null ? "projectId=" + projectId + ", " : "") +
            "}";
    }

}
