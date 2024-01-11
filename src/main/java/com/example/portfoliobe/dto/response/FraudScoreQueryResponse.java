package com.example.portfoliobe.dto.response;

import com.example.portfoliobe.dto.data.FraudScoreQueryResponseData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FraudScoreQueryResponse extends FraudScoreQueryResponseData {
    private FraudScoreQueryResponseData data;
}
