package com.tcutma.realstate.service.dto;

import java.io.Serializable;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;






/**
 * Criteria class for the ResidentialArea entity. This class is used in ResidentialAreaResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /residential-areas?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ResidentialAreaCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private StringFilter residentialName;

    private StringFilter residentialAlias;

    private StringFilter residentialProvince;

    private StringFilter residentialDistrict;

    private StringFilter residentialAvatar;

    private LongFilter tagId;

    public ResidentialAreaCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getResidentialName() {
        return residentialName;
    }

    public void setResidentialName(StringFilter residentialName) {
        this.residentialName = residentialName;
    }

    public StringFilter getResidentialAlias() {
        return residentialAlias;
    }

    public void setResidentialAlias(StringFilter residentialAlias) {
        this.residentialAlias = residentialAlias;
    }

    public StringFilter getResidentialProvince() {
        return residentialProvince;
    }

    public void setResidentialProvince(StringFilter residentialProvince) {
        this.residentialProvince = residentialProvince;
    }

    public StringFilter getResidentialDistrict() {
        return residentialDistrict;
    }

    public void setResidentialDistrict(StringFilter residentialDistrict) {
        this.residentialDistrict = residentialDistrict;
    }

    public StringFilter getResidentialAvatar() {
        return residentialAvatar;
    }

    public void setResidentialAvatar(StringFilter residentialAvatar) {
        this.residentialAvatar = residentialAvatar;
    }

    public LongFilter getTagId() {
        return tagId;
    }

    public void setTagId(LongFilter tagId) {
        this.tagId = tagId;
    }

    @Override
    public String toString() {
        return "ResidentialAreaCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (residentialName != null ? "residentialName=" + residentialName + ", " : "") +
                (residentialAlias != null ? "residentialAlias=" + residentialAlias + ", " : "") +
                (residentialProvince != null ? "residentialProvince=" + residentialProvince + ", " : "") +
                (residentialDistrict != null ? "residentialDistrict=" + residentialDistrict + ", " : "") +
                (residentialAvatar != null ? "residentialAvatar=" + residentialAvatar + ", " : "") +
                (tagId != null ? "tagId=" + tagId + ", " : "") +
            "}";
    }

}
