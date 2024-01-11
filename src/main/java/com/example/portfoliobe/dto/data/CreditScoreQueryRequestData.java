package com.example.portfoliobe.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreditScoreQueryRequestData {
    @JsonProperty("phone_number")
    private String phoneNumber;
    private String otp;
    private String product;
    private String channel;
    @JsonProperty("national_id")
    private String nationalId;
    @JsonProperty("score_version")
    private String scoreVersion;
    @JsonProperty("consent_type")
    private String consentType;
}
