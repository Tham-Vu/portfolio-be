package com.example.portfoliobe.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FraudScoreQueryRequestData {
    @JsonProperty("phone_number")
    private String phoneNumber;
    @JsonProperty("reference_number")
    private Arrays  referenceNumber;
    private String province;
    private String district;
    @JsonProperty("national_id")
    private String nationalId;
    @JsonProperty("consent_type")
    private String consentType;
}
