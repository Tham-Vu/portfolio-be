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
public class FraudScoreQueryResponseData {
    private String id;
    @JsonProperty("fraud_score")
    private String fraudScore;
    @JsonProperty("fraud_level")
    private int fraudLevel;
    private String telco;
}
