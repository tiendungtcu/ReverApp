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

import com.tcutma.realstate.domain.enumeration.ResourceType;

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
    @Size(max = 128)
    @Column(name = "request_first_name", length = 128, nullable = false)
    private String requestFirstName;

    @NotNull
    @Size(max = 128)
    @Column(name = "request_last_name", length = 128, nullable = false)
    private String requestLastName;

    @NotNull
    @Column(name = "request_email", nullable = false)
    private String requestEmail;

    @NotNull
    @Size(max = 16)
    @Column(name = "request_phone", length = 16, nullable = false)
    private String requestPhone;

    @Column(name = "request_get_analysis")
    private Boolean requestGetAnalysis;

    @Column(name = "request_get_price")
    private Boolean requestGetPrice;

    @Column(name = "request_page_url")
    private String requestPageUrl;

    @Column(name = "resource_id")
    private Long resourceId;

    @Enumerated(EnumType.STRING)
    @Column(name = "resource_type")
    private ResourceType resourceType;

    @Enumerated(EnumType.STRING)
    @Column(name = "request_type")
    private RequestType requestType;

    @Column(name = "request_meeting_date")
    private LocalDate requestMeetingDate;

    @Column(name = "request_question")
    private String requestQuestion;

    @DecimalMin(value = "0")
    @Column(name = "request_price")
    private Double requestPrice;

    @ManyToOne
    @JsonIgnoreProperties("")
    private User sender;

    @ManyToOne
    @JsonIgnoreProperties("")
    private User receiver;

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

    public Long getResourceId() {
        return resourceId;
    }

    public Request resourceId(Long resourceId) {
        this.resourceId = resourceId;
        return this;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public Request resourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
        return this;
    }

    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
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

    public LocalDate getRequestMeetingDate() {
        return requestMeetingDate;
    }

    public Request requestMeetingDate(LocalDate requestMeetingDate) {
        this.requestMeetingDate = requestMeetingDate;
        return this;
    }

    public void setRequestMeetingDate(LocalDate requestMeetingDate) {
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

    public User getSender() {
        return sender;
    }

    public Request sender(User user) {
        this.sender = user;
        return this;
    }

    public void setSender(User user) {
        this.sender = user;
    }

    public User getReceiver() {
        return receiver;
    }

    public Request receiver(User user) {
        this.receiver = user;
        return this;
    }

    public void setReceiver(User user) {
        this.receiver = user;
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
            ", resourceId=" + getResourceId() +
            ", resourceType='" + getResourceType() + "'" +
            ", requestType='" + getRequestType() + "'" +
            ", requestMeetingDate='" + getRequestMeetingDate() + "'" +
            ", requestQuestion='" + getRequestQuestion() + "'" +
            ", requestPrice=" + getRequestPrice() +
            "}";
    }
}
