package com.tcutma.realstate.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.tcutma.realstate.domain.enumeration.Gender;

/**
 * A DTO for the Employee entity.
 */
public class EmployeeDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 128)
    private String employeeFirstName;

    @NotNull
    @Size(max = 128)
    private String employeeLastName;

    private LocalDate employeeDob;

    private Gender employeeSex;

    @Size(max = 16)
    private String employeeIdentityCard;

    @NotNull
    @Size(max = 16)
    private String employeePhone;

    @NotNull
    private String employeeEmail;

    private Long accountId;

    private String accountLogin;

    private Long departmentId;

    private String departmentDepartmentName;

    private Long jobtitleId;

    private String jobtitleTitleName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeFirstName() {
        return employeeFirstName;
    }

    public void setEmployeeFirstName(String employeeFirstName) {
        this.employeeFirstName = employeeFirstName;
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    public void setEmployeeLastName(String employeeLastName) {
        this.employeeLastName = employeeLastName;
    }

    public LocalDate getEmployeeDob() {
        return employeeDob;
    }

    public void setEmployeeDob(LocalDate employeeDob) {
        this.employeeDob = employeeDob;
    }

    public Gender getEmployeeSex() {
        return employeeSex;
    }

    public void setEmployeeSex(Gender employeeSex) {
        this.employeeSex = employeeSex;
    }

    public String getEmployeeIdentityCard() {
        return employeeIdentityCard;
    }

    public void setEmployeeIdentityCard(String employeeIdentityCard) {
        this.employeeIdentityCard = employeeIdentityCard;
    }

    public String getEmployeePhone() {
        return employeePhone;
    }

    public void setEmployeePhone(String employeePhone) {
        this.employeePhone = employeePhone;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long userId) {
        this.accountId = userId;
    }

    public String getAccountLogin() {
        return accountLogin;
    }

    public void setAccountLogin(String userLogin) {
        this.accountLogin = userLogin;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentDepartmentName() {
        return departmentDepartmentName;
    }

    public void setDepartmentDepartmentName(String departmentDepartmentName) {
        this.departmentDepartmentName = departmentDepartmentName;
    }

    public Long getJobtitleId() {
        return jobtitleId;
    }

    public void setJobtitleId(Long jobTitleId) {
        this.jobtitleId = jobTitleId;
    }

    public String getJobtitleTitleName() {
        return jobtitleTitleName;
    }

    public void setJobtitleTitleName(String jobTitleTitleName) {
        this.jobtitleTitleName = jobTitleTitleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EmployeeDTO employeeDTO = (EmployeeDTO) o;
        if (employeeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), employeeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
            "id=" + getId() +
            ", employeeFirstName='" + getEmployeeFirstName() + "'" +
            ", employeeLastName='" + getEmployeeLastName() + "'" +
            ", employeeDob='" + getEmployeeDob() + "'" +
            ", employeeSex='" + getEmployeeSex() + "'" +
            ", employeeIdentityCard='" + getEmployeeIdentityCard() + "'" +
            ", employeePhone='" + getEmployeePhone() + "'" +
            ", employeeEmail='" + getEmployeeEmail() + "'" +
            ", account=" + getAccountId() +
            ", account='" + getAccountLogin() + "'" +
            ", department=" + getDepartmentId() +
            ", department='" + getDepartmentDepartmentName() + "'" +
            ", jobtitle=" + getJobtitleId() +
            ", jobtitle='" + getJobtitleTitleName() + "'" +
            "}";
    }
}
