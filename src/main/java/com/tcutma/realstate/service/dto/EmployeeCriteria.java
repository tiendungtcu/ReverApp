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


import io.github.jhipster.service.filter.LocalDateFilter;



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

    private StringFilter employeeFirstName;

    private StringFilter employeeLastName;

    private LocalDateFilter employeeDob;

    private GenderFilter employeeSex;

    private StringFilter employeeIdentityCard;

    private StringFilter employeePhone;

    private StringFilter employeeEmail;

    private LongFilter accountId;

    private LongFilter departmentId;

    private LongFilter jobtitleId;

    public EmployeeCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
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

    public LocalDateFilter getEmployeeDob() {
        return employeeDob;
    }

    public void setEmployeeDob(LocalDateFilter employeeDob) {
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

    public LongFilter getAccountId() {
        return accountId;
    }

    public void setAccountId(LongFilter accountId) {
        this.accountId = accountId;
    }

    public LongFilter getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(LongFilter departmentId) {
        this.departmentId = departmentId;
    }

    public LongFilter getJobtitleId() {
        return jobtitleId;
    }

    public void setJobtitleId(LongFilter jobtitleId) {
        this.jobtitleId = jobtitleId;
    }

    @Override
    public String toString() {
        return "EmployeeCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (employeeFirstName != null ? "employeeFirstName=" + employeeFirstName + ", " : "") +
                (employeeLastName != null ? "employeeLastName=" + employeeLastName + ", " : "") +
                (employeeDob != null ? "employeeDob=" + employeeDob + ", " : "") +
                (employeeSex != null ? "employeeSex=" + employeeSex + ", " : "") +
                (employeeIdentityCard != null ? "employeeIdentityCard=" + employeeIdentityCard + ", " : "") +
                (employeePhone != null ? "employeePhone=" + employeePhone + ", " : "") +
                (employeeEmail != null ? "employeeEmail=" + employeeEmail + ", " : "") +
                (accountId != null ? "accountId=" + accountId + ", " : "") +
                (departmentId != null ? "departmentId=" + departmentId + ", " : "") +
                (jobtitleId != null ? "jobtitleId=" + jobtitleId + ", " : "") +
            "}";
    }

}
