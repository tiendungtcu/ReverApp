package com.tcutma.realstate.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.tcutma.realstate.domain.enumeration.ResourceType;
import com.tcutma.realstate.domain.enumeration.RequestType;

/**
 * A DTO for the Request entity.
 */
public class RequestDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 128)
    private String requestFirstName;

    @NotNull
    @Size(max = 128)
    private String requestLastName;

    @NotNull
    private String requestEmail;

    @NotNull
    @Size(max = 16)
    private String requestPhone;

    private Boolean requestGetAnalysis;

    private Boolean requestGetPrice;

    private String requestPageUrl;

    private Long resourceId;

    private ResourceType resourceType;

    private RequestType requestType;

    private LocalDate requestMeetingDate;

    private String requestQuestion;

    @DecimalMin(value = "0")
    private Double requestPrice;

    private Long senderId;

    private String senderLogin;

    private Long receiverId;

    private String receiverLogin;

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

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    public LocalDate getRequestMeetingDate() {
        return requestMeetingDate;
    }

    public void setRequestMeetingDate(LocalDate requestMeetingDate) {
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

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long userId) {
        this.senderId = userId;
    }

    public String getSenderLogin() {
        return senderLogin;
    }

    public void setSenderLogin(String userLogin) {
        this.senderLogin = userLogin;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long userId) {
        this.receiverId = userId;
    }

    public String getReceiverLogin() {
        return receiverLogin;
    }

    public void setReceiverLogin(String userLogin) {
        this.receiverLogin = userLogin;
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
            ", resourceId=" + getResourceId() +
            ", resourceType='" + getResourceType() + "'" +
            ", requestType='" + getRequestType() + "'" +
            ", requestMeetingDate='" + getRequestMeetingDate() + "'" +
            ", requestQuestion='" + getRequestQuestion() + "'" +
            ", requestPrice=" + getRequestPrice() +
            ", sender=" + getSenderId() +
            ", sender='" + getSenderLogin() + "'" +
            ", receiver=" + getReceiverId() +
            ", receiver='" + getReceiverLogin() + "'" +
            "}";
    }
}
