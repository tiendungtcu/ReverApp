package com.tcutma.realstate.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Objects;

import com.tcutma.realstate.domain.enumeration.PropertyType;

import com.tcutma.realstate.domain.enumeration.RequestType;

/**
 * Request entity
 */
@ApiModel(description = "Request entity")
@Entity
@Table(name = "request")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Request implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "request_first_name", nullable = false)
    private String requestFirstName;

    @NotNull
    @Column(name = "request_last_name", nullable = false)
    private String requestLastName;

    @NotNull
    @Column(name = "request_email", nullable = false)
    private String requestEmail;

    @NotNull
    @Column(name = "request_phone", nullable = false)
    private String requestPhone;

    @Column(name = "request_get_analysis")
    private Boolean requestGetAnalysis;

    @Column(name = "request_get_price")
    private Boolean requestGetPrice;

    @Column(name = "request_page_url")
    private String requestPageUrl;

    @Column(name = "request_page_name")
    private String requestPageName;

    @Column(name = "request_property_id")
    private Long requestPropertyId;

    @Enumerated(EnumType.STRING)
    @Column(name = "request_property_type")
    private PropertyType requestPropertyType;

    @Enumerated(EnumType.STRING)
    @Column(name = "request_type")
    private RequestType requestType;

    @Column(name = "request_meeting_date")
    private Instant requestMeetingDate;

    @Column(name = "request_question")
    private String requestQuestion;

    @Column(name = "request_price")
    private Double requestPrice;

    @Column(name = "request_created_date")
    private ZonedDateTime requestCreatedDate;

    @Column(name = "request_consultant_id")
    private Integer requestConsultantId;

    @ManyToOne
    @JsonIgnoreProperties("")
    private User user;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Property property;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Project project;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRequestFirstName() {
        return requestFirstName;
    }

    public Request requestFirstName(String requestFirstName) {
        this.requestFirstName = requestFirstName;
        return this;
    }

    public void setRequestFirstName(String requestFirstName) {
        this.requestFirstName = requestFirstName;
    }

    public String getRequestLastName() {
        return requestLastName;
    }

    public Request requestLastName(String requestLastName) {
        this.requestLastName = requestLastName;
        return this;
    }

    public void setRequestLastName(String requestLastName) {
        this.requestLastName = requestLastName;
    }

    public String getRequestEmail() {
        return requestEmail;
    }

    public Request requestEmail(String requestEmail) {
        this.requestEmail = requestEmail;
        return this;
    }

    public void setRequestEmail(String requestEmail) {
        this.requestEmail = requestEmail;
    }

    public String getRequestPhone() {
        return requestPhone;
    }

    public Request requestPhone(String requestPhone) {
        this.requestPhone = requestPhone;
        return this;
    }

    public void setRequestPhone(String requestPhone) {
        this.requestPhone = requestPhone;
    }

    public Boolean isRequestGetAnalysis() {
        return requestGetAnalysis;
    }

    public Request requestGetAnalysis(Boolean requestGetAnalysis) {
        this.requestGetAnalysis = requestGetAnalysis;
        return this;
    }

    public void setRequestGetAnalysis(Boolean requestGetAnalysis) {
        this.requestGetAnalysis = requestGetAnalysis;
    }

    public Boolean isRequestGetPrice() {
        return requestGetPrice;
    }

    public Request requestGetPrice(Boolean requestGetPrice) {
        this.requestGetPrice = requestGetPrice;
        return this;
    }

    public void setRequestGetPrice(Boolean requestGetPrice) {
        this.requestGetPrice = requestGetPrice;
    }

    public String getRequestPageUrl() {
        return requestPageUrl;
    }

    public Request requestPageUrl(String requestPageUrl) {
        this.requestPageUrl = requestPageUrl;
        return this;
    }

    public void setRequestPageUrl(String requestPageUrl) {
        this.requestPageUrl = requestPageUrl;
    }

    public String getRequestPageName() {
        return requestPageName;
    }

    public Request requestPageName(String requestPageName) {
        this.requestPageName = requestPageName;
        return this;
    }

    public void setRequestPageName(String requestPageName) {
        this.requestPageName = requestPageName;
    }

    public Long getRequestPropertyId() {
        return requestPropertyId;
    }

    public Request requestPropertyId(Long requestPropertyId) {
        this.requestPropertyId = requestPropertyId;
        return this;
    }

    public void setRequestPropertyId(Long requestPropertyId) {
        this.requestPropertyId = requestPropertyId;
    }

    public PropertyType getRequestPropertyType() {
        return requestPropertyType;
    }

    public Request requestPropertyType(PropertyType requestPropertyType) {
        this.requestPropertyType = requestPropertyType;
        return this;
    }

    public void setRequestPropertyType(PropertyType requestPropertyType) {
        this.requestPropertyType = requestPropertyType;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public Request requestType(RequestType requestType) {
        this.requestType = requestType;
        return this;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    public Instant getRequestMeetingDate() {
        return requestMeetingDate;
    }

    public Request requestMeetingDate(Instant requestMeetingDate) {
        this.requestMeetingDate = requestMeetingDate;
        return this;
    }

    public void setRequestMeetingDate(Instant requestMeetingDate) {
        this.requestMeetingDate = requestMeetingDate;
    }

    public String getRequestQuestion() {
        return requestQuestion;
    }

    public Request requestQuestion(String requestQuestion) {
        this.requestQuestion = requestQuestion;
        return this;
    }

    public void setRequestQuestion(String requestQuestion) {
        this.requestQuestion = requestQuestion;
    }

    public Double getRequestPrice() {
        return requestPrice;
    }

    public Request requestPrice(Double requestPrice) {
        this.requestPrice = requestPrice;
        return this;
    }

    public void setRequestPrice(Double requestPrice) {
        this.requestPrice = requestPrice;
    }

    public ZonedDateTime getRequestCreatedDate() {
        return requestCreatedDate;
    }

    public Request requestCreatedDate(ZonedDateTime requestCreatedDate) {
        this.requestCreatedDate = requestCreatedDate;
        return this;
    }

    public void setRequestCreatedDate(ZonedDateTime requestCreatedDate) {
        this.requestCreatedDate = requestCreatedDate;
    }

    public Integer getRequestConsultantId() {
        return requestConsultantId;
    }

    public Request requestConsultantId(Integer requestConsultantId) {
        this.requestConsultantId = requestConsultantId;
        return this;
    }

    public void setRequestConsultantId(Integer requestConsultantId) {
        this.requestConsultantId = requestConsultantId;
    }

    public User getUser() {
        return user;
    }

    public Request user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Property getProperty() {
        return property;
    }

    public Request property(Property property) {
        this.property = property;
        return this;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public Project getProject() {
        return project;
    }

    public Request project(Project project) {
        this.project = project;
        return this;
    }

    public void setProject(Project project) {
        this.project = project;
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
        Request request = (Request) o;
        if (request.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), request.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Request{" +
            "id=" + getId() +
            ", requestFirstName='" + getRequestFirstName() + "'" +
            ", requestLastName='" + getRequestLastName() + "'" +
            ", requestEmail='" + getRequestEmail() + "'" +
            ", requestPhone='" + getRequestPhone() + "'" +
            ", requestGetAnalysis='" + isRequestGetAnalysis() + "'" +
            ", requestGetPrice='" + isRequestGetPrice() + "'" +
            ", requestPageUrl='" + getRequestPageUrl() + "'" +
            ", requestPageName='" + getRequestPageName() + "'" +
            ", requestPropertyId=" + getRequestPropertyId() +
            ", requestPropertyType='" + getRequestPropertyType() + "'" +
            ", requestType='" + getRequestType() + "'" +
            ", requestMeetingDate='" + getRequestMeetingDate() + "'" +
            ", requestQuestion='" + getRequestQuestion() + "'" +
            ", requestPrice=" + getRequestPrice() +
            ", requestCreatedDate='" + getRequestCreatedDate() + "'" +
            ", requestConsultantId=" + getRequestConsultantId() +
            "}";
    }
}
