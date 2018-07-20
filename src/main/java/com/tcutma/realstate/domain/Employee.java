package com.tcutma.realstate.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

import com.tcutma.realstate.domain.enumeration.Gender;

/**
 * Employee - entity
 */
@ApiModel(description = "Employee - entity")
@Entity
@Table(name = "employee")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "employee_name", nullable = false)
    private String employeeName;

    @Column(name = "employee_first_name")
    private String employeeFirstName;

    @Column(name = "employee_last_name")
    private String employeeLastName;

    @Column(name = "employee_dob")
    private ZonedDateTime employeeDob;

    @Enumerated(EnumType.STRING)
    @Column(name = "employee_sex")
    private Gender employeeSex;

    @Column(name = "employee_identity_card")
    private String employeeIdentityCard;

    @NotNull
    @Column(name = "employee_phone", nullable = false)
    private String employeePhone;

    @NotNull
    @Column(name = "employee_email", nullable = false)
    private String employeeEmail;

    @Lob
    @Column(name = "employee_avatar")
    private byte[] employeeAvatar;

    @Column(name = "employee_avatar_content_type")
    private String employeeAvatarContentType;

    @Column(name = "employee_facebook")
    private String employeeFacebook;

    @Column(name = "employee_linkedin")
    private String employeeLinkedin;

    @Column(name = "employee_instagram")
    private String employeeInstagram;

    @Column(name = "employee_google_plus")
    private String employeeGooglePlus;

    @Column(name = "employee_zalo")
    private String employeeZalo;

    @Column(name = "employee_twitter")
    private String employeeTwitter;

    @Column(name = "employee_youtube")
    private String employeeYoutube;

    @OneToOne
    @JoinColumn(unique = true)
    private Contact contact;

    @OneToOne
    @JoinColumn(unique = true)
    private Photo photo;

    @ManyToOne
    @JsonIgnoreProperties("")
    private JobTitle jobtitle;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Department department;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Employee manager;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public Employee employeeName(String employeeName) {
        this.employeeName = employeeName;
        return this;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeFirstName() {
        return employeeFirstName;
    }

    public Employee employeeFirstName(String employeeFirstName) {
        this.employeeFirstName = employeeFirstName;
        return this;
    }

    public void setEmployeeFirstName(String employeeFirstName) {
        this.employeeFirstName = employeeFirstName;
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    public Employee employeeLastName(String employeeLastName) {
        this.employeeLastName = employeeLastName;
        return this;
    }

    public void setEmployeeLastName(String employeeLastName) {
        this.employeeLastName = employeeLastName;
    }

    public ZonedDateTime getEmployeeDob() {
        return employeeDob;
    }

    public Employee employeeDob(ZonedDateTime employeeDob) {
        this.employeeDob = employeeDob;
        return this;
    }

    public void setEmployeeDob(ZonedDateTime employeeDob) {
        this.employeeDob = employeeDob;
    }

    public Gender getEmployeeSex() {
        return employeeSex;
    }

    public Employee employeeSex(Gender employeeSex) {
        this.employeeSex = employeeSex;
        return this;
    }

    public void setEmployeeSex(Gender employeeSex) {
        this.employeeSex = employeeSex;
    }

    public String getEmployeeIdentityCard() {
        return employeeIdentityCard;
    }

    public Employee employeeIdentityCard(String employeeIdentityCard) {
        this.employeeIdentityCard = employeeIdentityCard;
        return this;
    }

    public void setEmployeeIdentityCard(String employeeIdentityCard) {
        this.employeeIdentityCard = employeeIdentityCard;
    }

    public String getEmployeePhone() {
        return employeePhone;
    }

    public Employee employeePhone(String employeePhone) {
        this.employeePhone = employeePhone;
        return this;
    }

    public void setEmployeePhone(String employeePhone) {
        this.employeePhone = employeePhone;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public Employee employeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
        return this;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public byte[] getEmployeeAvatar() {
        return employeeAvatar;
    }

    public Employee employeeAvatar(byte[] employeeAvatar) {
        this.employeeAvatar = employeeAvatar;
        return this;
    }

    public void setEmployeeAvatar(byte[] employeeAvatar) {
        this.employeeAvatar = employeeAvatar;
    }

    public String getEmployeeAvatarContentType() {
        return employeeAvatarContentType;
    }

    public Employee employeeAvatarContentType(String employeeAvatarContentType) {
        this.employeeAvatarContentType = employeeAvatarContentType;
        return this;
    }

    public void setEmployeeAvatarContentType(String employeeAvatarContentType) {
        this.employeeAvatarContentType = employeeAvatarContentType;
    }

    public String getEmployeeFacebook() {
        return employeeFacebook;
    }

    public Employee employeeFacebook(String employeeFacebook) {
        this.employeeFacebook = employeeFacebook;
        return this;
    }

    public void setEmployeeFacebook(String employeeFacebook) {
        this.employeeFacebook = employeeFacebook;
    }

    public String getEmployeeLinkedin() {
        return employeeLinkedin;
    }

    public Employee employeeLinkedin(String employeeLinkedin) {
        this.employeeLinkedin = employeeLinkedin;
        return this;
    }

    public void setEmployeeLinkedin(String employeeLinkedin) {
        this.employeeLinkedin = employeeLinkedin;
    }

    public String getEmployeeInstagram() {
        return employeeInstagram;
    }

    public Employee employeeInstagram(String employeeInstagram) {
        this.employeeInstagram = employeeInstagram;
        return this;
    }

    public void setEmployeeInstagram(String employeeInstagram) {
        this.employeeInstagram = employeeInstagram;
    }

    public String getEmployeeGooglePlus() {
        return employeeGooglePlus;
    }

    public Employee employeeGooglePlus(String employeeGooglePlus) {
        this.employeeGooglePlus = employeeGooglePlus;
        return this;
    }

    public void setEmployeeGooglePlus(String employeeGooglePlus) {
        this.employeeGooglePlus = employeeGooglePlus;
    }

    public String getEmployeeZalo() {
        return employeeZalo;
    }

    public Employee employeeZalo(String employeeZalo) {
        this.employeeZalo = employeeZalo;
        return this;
    }

    public void setEmployeeZalo(String employeeZalo) {
        this.employeeZalo = employeeZalo;
    }

    public String getEmployeeTwitter() {
        return employeeTwitter;
    }

    public Employee employeeTwitter(String employeeTwitter) {
        this.employeeTwitter = employeeTwitter;
        return this;
    }

    public void setEmployeeTwitter(String employeeTwitter) {
        this.employeeTwitter = employeeTwitter;
    }

    public String getEmployeeYoutube() {
        return employeeYoutube;
    }

    public Employee employeeYoutube(String employeeYoutube) {
        this.employeeYoutube = employeeYoutube;
        return this;
    }

    public void setEmployeeYoutube(String employeeYoutube) {
        this.employeeYoutube = employeeYoutube;
    }

    public Contact getContact() {
        return contact;
    }

    public Employee contact(Contact contact) {
        this.contact = contact;
        return this;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Photo getPhoto() {
        return photo;
    }

    public Employee photo(Photo photo) {
        this.photo = photo;
        return this;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public JobTitle getJobtitle() {
        return jobtitle;
    }

    public Employee jobtitle(JobTitle jobTitle) {
        this.jobtitle = jobTitle;
        return this;
    }

    public void setJobtitle(JobTitle jobTitle) {
        this.jobtitle = jobTitle;
    }

    public Department getDepartment() {
        return department;
    }

    public Employee department(Department department) {
        this.department = department;
        return this;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Employee getManager() {
        return manager;
    }

    public Employee manager(Employee employee) {
        this.manager = employee;
        return this;
    }

    public void setManager(Employee employee) {
        this.manager = employee;
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
        Employee employee = (Employee) o;
        if (employee.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), employee.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Employee{" +
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
            ", employeeAvatarContentType='" + getEmployeeAvatarContentType() + "'" +
            ", employeeFacebook='" + getEmployeeFacebook() + "'" +
            ", employeeLinkedin='" + getEmployeeLinkedin() + "'" +
            ", employeeInstagram='" + getEmployeeInstagram() + "'" +
            ", employeeGooglePlus='" + getEmployeeGooglePlus() + "'" +
            ", employeeZalo='" + getEmployeeZalo() + "'" +
            ", employeeTwitter='" + getEmployeeTwitter() + "'" +
            ", employeeYoutube='" + getEmployeeYoutube() + "'" +
            "}";
    }
}
