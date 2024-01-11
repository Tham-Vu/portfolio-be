package com.example.portfoliobe.dto.data;
import com.fasterxml.jackson.annotation.JsonProperty;

public class VerifyOTPResponseData {
    private String id;
    private String telco;
    @JsonProperty("expire_time")
    private String expireTime;
    @JsonProperty("available_product")
    private String availableProduct;
}
