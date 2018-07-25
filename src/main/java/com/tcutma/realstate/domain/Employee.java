package com.tcutma.realstate.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
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
    @Size(max = 128)
    @Column(name = "employee_first_name", length = 128, nullable = false)
    private String employeeFirstName;

    @NotNull
    @Size(max = 128)
    @Column(name = "employee_last_name", length = 128, nullable = false)
    private String employeeLastName;

    @Column(name = "employee_dob")
    private LocalDate employeeDob;

    @Enumerated(EnumType.STRING)
    @Column(name = "employee_sex")
    private Gender employeeSex;

    @Size(max = 16)
    @Column(name = "employee_identity_card", length = 16)
    private String employeeIdentityCard;

    @NotNull
    @Size(max = 16)
    @Column(name = "employee_phone", length = 16, nullable = false)
    private String employeePhone;

    @NotNull
    @Column(name = "employee_email", nullable = false)
    private String employeeEmail;

    @OneToOne
    @JoinColumn(unique = true)
    private User account;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Department department;

    @ManyToOne
    @JsonIgnoreProperties("")
    private JobTitle jobtitle;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDate getEmployeeDob() {
        return employeeDob;
    }

    public Employee employeeDob(LocalDate employeeDob) {
        this.employeeDob = employeeDob;
        return this;
    }

    public void setEmployeeDob(LocalDate employeeDob) {
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

    public User getAccount() {
        return account;
    }

    public Employee account(User user) {
        this.account = user;
        return this;
    }

    public void setAccount(User user) {
        this.account = user;
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
            ", employeeFirstName='" + getEmployeeFirstName() + "'" +
            ", employeeLastName='" + getEmployeeLastName() + "'" +
            ", employeeDob='" + getEmployeeDob() + "'" +
            ", employeeSex='" + getEmployeeSex() + "'" +
            ", employeeIdentityCard='" + getEmployeeIdentityCard() + "'" +
            ", employeePhone='" + getEmployeePhone() + "'" +
            ", employeeEmail='" + getEmployeeEmail() + "'" +
            "}";
    }
}
