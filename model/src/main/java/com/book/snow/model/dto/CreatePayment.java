package com.book.snow.model.dto;

import com.sun.istack.internal.NotNull;


public class CreatePayment {

    @NotNull
    private Integer amount;

    @NotNull
    private String featureRequest;

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
}