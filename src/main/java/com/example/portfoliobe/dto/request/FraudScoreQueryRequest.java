package com.example.portfoliobe.dto.request;

import com.example.portfoliobe.dto.data.FraudScoreQueryRequestData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FraudScoreQueryRequest extends RequestAbstract{
    private FraudScoreQueryRequestData data;
}
