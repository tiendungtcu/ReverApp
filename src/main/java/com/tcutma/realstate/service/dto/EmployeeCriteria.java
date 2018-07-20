package com.tcutma.realstate.service.dto;

import java.io.Serializable;
import com.tcutma.realstate.domain.enumeration.Gender;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;



import io.github.jhipster.service.filter.ZonedDateTimeFilter;


/**
 * Criteria class for the Employee entity. This class is used in EmployeeResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /employees?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class EmployeeCriteria implements Serializable {
    /**
     * Class for filtering Gender
     */
    public static class GenderFilter extends Filter<Gender> {
    }

    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private StringFilter employeeName;

    private StringFilter employeeFirstName;

    private StringFilter employeeLastName;

    private ZonedDateTimeFilter employeeDob;

    private GenderFilter employeeSex;

    private StringFilter employeeIdentityCard;

    private StringFilter employeePhone;

    private StringFilter employeeEmail;

    private StringFilter employeeFacebook;

    private StringFilter employeeLinkedin;

    private StringFilter employeeInstagram;

    private StringFilter employeeGooglePlus;

    private StringFilter employeeZalo;

    private StringFilter employeeTwitter;

    private StringFilter employeeYoutube;

    private LongFilter contactId;

    private LongFilter photoId;

    private LongFilter jobtitleId;

    private LongFilter departmentId;

    private LongFilter managerId;

    public EmployeeCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(StringFilter employeeName) {
        this.employeeName = employeeName;
    }

    public StringFilter getEmployeeFirstName() {
        return employeeFirstName;
    }

    public void setEmployeeFirstName(StringFilter employeeFirstName) {
        this.employeeFirstName = employeeFirstName;
    }

    public StringFilter getEmployeeLastName() {
        return employeeLastName;
    }

    public void setEmployeeLastName(StringFilter employeeLastName) {
        this.employeeLastName = employeeLastName;
    }

    public ZonedDateTimeFilter getEmployeeDob() {
        return employeeDob;
    }

    public void setEmployeeDob(ZonedDateTimeFilter employeeDob) {
        this.employeeDob = employeeDob;
    }

    public GenderFilter getEmployeeSex() {
        return employeeSex;
    }

    public void setEmployeeSex(GenderFilter employeeSex) {
        this.employeeSex = employeeSex;
    }

    public StringFilter getEmployeeIdentityCard() {
        return employeeIdentityCard;
    }

    public void setEmployeeIdentityCard(StringFilter employeeIdentityCard) {
        this.employeeIdentityCard = employeeIdentityCard;
    }

    public StringFilter getEmployeePhone() {
        return employeePhone;
    }

    public void setEmployeePhone(StringFilter employeePhone) {
        this.employeePhone = employeePhone;
    }

    public StringFilter getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(StringFilter employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public StringFilter getEmployeeFacebook() {
        return employeeFacebook;
    }

    public void setEmployeeFacebook(StringFilter employeeFacebook) {
        this.employeeFacebook = employeeFacebook;
    }

    public StringFilter getEmployeeLinkedin() {
        return employeeLinkedin;
    }

    public void setEmployeeLinkedin(StringFilter employeeLinkedin) {
        this.employeeLinkedin = employeeLinkedin;
    }

    public StringFilter getEmployeeInstagram() {
        return employeeInstagram;
    }

    public void setEmployeeInstagram(StringFilter employeeInstagram) {
        this.employeeInstagram = employeeInstagram;
    }

    public StringFilter getEmployeeGooglePlus() {
        return employeeGooglePlus;
    }

    public void setEmployeeGooglePlus(StringFilter employeeGooglePlus) {
        this.employeeGooglePlus = employeeGooglePlus;
    }

    public StringFilter getEmployeeZalo() {
        return employeeZalo;
    }

    public void setEmployeeZalo(StringFilter employeeZalo) {
        this.employeeZalo = employeeZalo;
    }

    public StringFilter getEmployeeTwitter() {
        return employeeTwitter;
    }

    public void setEmployeeTwitter(StringFilter employeeTwitter) {
        this.employeeTwitter = employeeTwitter;
    }

    public StringFilter getEmployeeYoutube() {
        return employeeYoutube;
    }

    public void setEmployeeYoutube(StringFilter employeeYoutube) {
        this.employeeYoutube = employeeYoutube;
    }

    public LongFilter getContactId() {
        return contactId;
    }

    public void setContactId(LongFilter contactId) {
        this.contactId = contactId;
    }

    public LongFilter getPhotoId() {
        return photoId;
    }

    public void setPhotoId(LongFilter photoId) {
        this.photoId = photoId;
    }

    public LongFilter getJobtitleId() {
        return jobtitleId;
    }

    public void setJobtitleId(LongFilter jobtitleId) {
        this.jobtitleId = jobtitleId;
    }

    public LongFilter getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(LongFilter departmentId) {
        this.departmentId = departmentId;
    }

    public LongFilter getManagerId() {
        return managerId;
    }

    public void setManagerId(LongFilter managerId) {
        this.managerId = managerId;
    }

    @Override
    public String toString() {
        return "EmployeeCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (employeeName != null ? "employeeName=" + employeeName + ", " : "") +
                (employeeFirstName != null ? "employeeFirstName=" + employeeFirstName + ", " : "") +
                (employeeLastName != null ? "employeeLastName=" + employeeLastName + ", " : "") +
                (employeeDob != null ? "employeeDob=" + employeeDob + ", " : "") +
                (employeeSex != null ? "employeeSex=" + employeeSex + ", " : "") +
                (employeeIdentityCard != null ? "employeeIdentityCard=" + employeeIdentityCard + ", " : "") +
                (employeePhone != null ? "employeePhone=" + employeePhone + ", " : "") +
                (employeeEmail != null ? "employeeEmail=" + employeeEmail + ", " : "") +
                (employeeFacebook != null ? "employeeFacebook=" + employeeFacebook + ", " : "") +
                (employeeLinkedin != null ? "employeeLinkedin=" + employeeLinkedin + ", " : "") +
                (employeeInstagram != null ? "employeeInstagram=" + employeeInstagram + ", " : "") +
                (employeeGooglePlus != null ? "employeeGooglePlus=" + employeeGooglePlus + ", " : "") +
                (employeeZalo != null ? "employeeZalo=" + employeeZalo + ", " : "") +
                (employeeTwitter != null ? "employeeTwitter=" + employeeTwitter + ", " : "") +
                (employeeYoutube != null ? "employeeYoutube=" + employeeYoutube + ", " : "") +
                (contactId != null ? "contactId=" + contactId + ", " : "") +
                (photoId != null ? "photoId=" + photoId + ", " : "") +
                (jobtitleId != null ? "jobtitleId=" + jobtitleId + ", " : "") +
                (departmentId != null ? "departmentId=" + departmentId + ", " : "") +
                (managerId != null ? "managerId=" + managerId + ", " : "") +
            "}";
    }

}
