package com.tcutma.realstate.service.dto;

import java.time.Instant;
import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.tcutma.realstate.domain.enumeration.PropertyType;
import com.tcutma.realstate.domain.enumeration.RequestType;

/**
 * A DTO for the Request entity.
 */
public class RequestDTO implements Serializable {

    private Long id;

    @NotNull
    private String requestFirstName;

    @NotNull
    private String requestLastName;

    @NotNull
    private String requestEmail;

    @NotNull
    private String requestPhone;

    private Boolean requestGetAnalysis;

    private Boolean requestGetPrice;

    private String requestPageUrl;

    private String requestPageName;

    private Long requestPropertyId;

    private PropertyType requestPropertyType;

    private RequestType requestType;

    private Instant requestMeetingDate;

    private String requestQuestion;

    private Double requestPrice;

    private ZonedDateTime requestCreatedDate;

    private Integer requestConsultantId;

    private Long userId;

    private String userLogin;

    private Long propertyId;

    private String propertyPropertyName;

    private Long projectId;

    private String projectProjectName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRequestFirstName() {
        return requestFirstName;
    }

    public void setRequestFirstName(String requestFirstName) {
        this.requestFirstName = requestFirstName;
    }

    public String getRequestLastName() {
        return requestLastName;
    }

    public void setRequestLastName(String requestLastName) {
        this.requestLastName = requestLastName;
    }

    public String getRequestEmail() {
        return requestEmail;
    }

    public void setRequestEmail(String requestEmail) {
        this.requestEmail = requestEmail;
    }

    public String getRequestPhone() {
        return requestPhone;
    }

    public void setRequestPhone(String requestPhone) {
        this.requestPhone = requestPhone;
    }

    public Boolean isRequestGetAnalysis() {
        return requestGetAnalysis;
    }

    public void setRequestGetAnalysis(Boolean requestGetAnalysis) {
        this.requestGetAnalysis = requestGetAnalysis;
    }

    public Boolean isRequestGetPrice() {
        return requestGetPrice;
    }

    public void setRequestGetPrice(Boolean requestGetPrice) {
        this.requestGetPrice = requestGetPrice;
    }

    public String getRequestPageUrl() {
        return requestPageUrl;
    }

    public void setRequestPageUrl(String requestPageUrl) {
        this.requestPageUrl = requestPageUrl;
    }

    public String getRequestPageName() {
        return requestPageName;
    }

    public void setRequestPageName(String requestPageName) {
        this.requestPageName = requestPageName;
    }

    public Long getRequestPropertyId() {
        return requestPropertyId;
    }

    public void setRequestPropertyId(Long requestPropertyId) {
        this.requestPropertyId = requestPropertyId;
    }

    public PropertyType getRequestPropertyType() {
        return requestPropertyType;
    }

    public void setRequestPropertyType(PropertyType requestPropertyType) {
        this.requestPropertyType = requestPropertyType;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    public Instant getRequestMeetingDate() {
        return requestMeetingDate;
    }

    public void setRequestMeetingDate(Instant requestMeetingDate) {
        this.requestMeetingDate = requestMeetingDate;
    }

    public String getRequestQuestion() {
        return requestQuestion;
    }

    public void setRequestQuestion(String requestQuestion) {
        this.requestQuestion = requestQuestion;
    }

    public Double getRequestPrice() {
        return requestPrice;
    }

    public void setRequestPrice(Double requestPrice) {
        this.requestPrice = requestPrice;
    }

    public ZonedDateTime getRequestCreatedDate() {
        return requestCreatedDate;
    }

    public void setRequestCreatedDate(ZonedDateTime requestCreatedDate) {
        this.requestCreatedDate = requestCreatedDate;
    }

    public Integer getRequestConsultantId() {
        return requestConsultantId;
    }

    public void setRequestConsultantId(Integer requestConsultantId) {
        this.requestConsultantId = requestConsultantId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }

    public String getPropertyPropertyName() {
        return propertyPropertyName;
    }

    public void setPropertyPropertyName(String propertyPropertyName) {
        this.propertyPropertyName = propertyPropertyName;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getProjectProjectName() {
        return projectProjectName;
    }

    public void setProjectProjectName(String projectProjectName) {
        this.projectProjectName = projectProjectName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RequestDTO requestDTO = (RequestDTO) o;
        if (requestDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), requestDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RequestDTO{" +
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
            ", user=" + getUserId() +
            ", user='" + getUserLogin() + "'" +
            ", property=" + getPropertyId() +
            ", property='" + getPropertyPropertyName() + "'" +
            ", project=" + getProjectId() +
            ", project='" + getProjectProjectName() + "'" +
            "}";
    }
}
