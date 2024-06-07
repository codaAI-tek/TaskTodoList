package com.book.snow.model.dto;

import javax.validation.constraints.NotNull;

public class CheckoutForm {

    @NotNull
    private Integer amount;

    @NotNull
    private String featureRequest;

    private String email;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getFeatureRequest() {
        return featureRequest;
    }

    public void setFeatureRequest(String featureRequest) {
        this.featureRequest = featureRequest;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}