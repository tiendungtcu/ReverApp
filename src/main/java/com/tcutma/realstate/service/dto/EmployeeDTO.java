package com.tcutma.realstate.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;
import com.tcutma.realstate.domain.enumeration.Gender;

/**
 * A DTO for the Employee entity.
 */
public class EmployeeDTO implements Serializable {

    private Long id;

    @NotNull
    private String employeeName;

    private String employeeFirstName;

    private String employeeLastName;

    private ZonedDateTime employeeDob;

    private Gender employeeSex;

    private String employeeIdentityCard;

    @NotNull
    private String employeePhone;

    @NotNull
    private String employeeEmail;

    @Lob
    private byte[] employeeAvatar;
    private String employeeAvatarContentType;

    private String employeeFacebook;

    private String employeeLinkedin;

    private String employeeInstagram;

    private String employeeGooglePlus;

    private String employeeZalo;

    private String employeeTwitter;

    private String employeeYoutube;

    private Long contactId;

    private Long photoId;

    private Long jobtitleId;

    private String jobtitleTitleName;

    private Long departmentId;

    private String departmentDepartmentName;

    private Long managerId;

    private String managerEmployeeName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
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

    public ZonedDateTime getEmployeeDob() {
        return employeeDob;
    }

    public void setEmployeeDob(ZonedDateTime employeeDob) {
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

    public byte[] getEmployeeAvatar() {
        return employeeAvatar;
    }

    public void setEmployeeAvatar(byte[] employeeAvatar) {
        this.employeeAvatar = employeeAvatar;
    }

    public String getEmployeeAvatarContentType() {
        return employeeAvatarContentType;
    }

    public void setEmployeeAvatarContentType(String employeeAvatarContentType) {
        this.employeeAvatarContentType = employeeAvatarContentType;
    }

    public String getEmployeeFacebook() {
        return employeeFacebook;
    }

    public void setEmployeeFacebook(String employeeFacebook) {
        this.employeeFacebook = employeeFacebook;
    }

    public String getEmployeeLinkedin() {
        return employeeLinkedin;
    }

    public void setEmployeeLinkedin(String employeeLinkedin) {
        this.employeeLinkedin = employeeLinkedin;
    }

    public String getEmployeeInstagram() {
        return employeeInstagram;
    }

    public void setEmployeeInstagram(String employeeInstagram) {
        this.employeeInstagram = employeeInstagram;
    }

    public String getEmployeeGooglePlus() {
        return employeeGooglePlus;
    }

    public void setEmployeeGooglePlus(String employeeGooglePlus) {
        this.employeeGooglePlus = employeeGooglePlus;
    }

    public String getEmployeeZalo() {
        return employeeZalo;
    }

    public void setEmployeeZalo(String employeeZalo) {
        this.employeeZalo = employeeZalo;
    }

    public String getEmployeeTwitter() {
        return employeeTwitter;
    }

    public void setEmployeeTwitter(String employeeTwitter) {
        this.employeeTwitter = employeeTwitter;
    }

    public String getEmployeeYoutube() {
        return employeeYoutube;
    }

    public void setEmployeeYoutube(String employeeYoutube) {
        this.employeeYoutube = employeeYoutube;
    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public Long getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Long photoId) {
        this.photoId = photoId;
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

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long employeeId) {
        this.managerId = employeeId;
    }

    public String getManagerEmployeeName() {
        return managerEmployeeName;
    }

    public void setManagerEmployeeName(String employeeEmployeeName) {
        this.managerEmployeeName = employeeEmployeeName;
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
            ", employeeName='" + getEmployeeName() + "'" +
            ", employeeFirstName='" + getEmployeeFirstName() + "'" +
            ", employeeLastName='" + getEmployeeLastName() + "'" +
            ", employeeDob='" + getEmployeeDob() + "'" +
            ", employeeSex='" + getEmployeeSex() + "'" +
            ", employeeIdentityCard='" + getEmployeeIdentityCard() + "'" +
            ", employeePhone='" + getEmployeePhone() + "'" +
            ", employeeEmail='" + getEmployeeEmail() + "'" +
            ", employeeAvatar='" + getEmployeeAvatar() + "'" +
            ", employeeFacebook='" + getEmployeeFacebook() + "'" +
            ", employeeLinkedin='" + getEmployeeLinkedin() + "'" +
            ", employeeInstagram='" + getEmployeeInstagram() + "'" +
            ", employeeGooglePlus='" + getEmployeeGooglePlus() + "'" +
            ", employeeZalo='" + getEmployeeZalo() + "'" +
            ", employeeTwitter='" + getEmployeeTwitter() + "'" +
            ", employeeYoutube='" + getEmployeeYoutube() + "'" +
            ", contact=" + getContactId() +
            ", photo=" + getPhotoId() +
            ", jobtitle=" + getJobtitleId() +
            ", jobtitle='" + getJobtitleTitleName() + "'" +
            ", department=" + getDepartmentId() +
            ", department='" + getDepartmentDepartmentName() + "'" +
            ", manager=" + getManagerId() +
            ", manager='" + getManagerEmployeeName() + "'" +
            "}";
    }
}
