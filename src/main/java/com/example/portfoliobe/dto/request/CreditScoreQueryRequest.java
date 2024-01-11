package com.example.portfoliobe.dto.request;

import com.example.portfoliobe.dto.data.CreditScoreQueryRequestData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreditScoreQueryRequest extends RequestAbstract{
    private CreditScoreQueryRequestData data;
}
