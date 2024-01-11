package com.example.portfoliobe.dto.response;

import com.example.portfoliobe.dto.data.CheckMNPResponseData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CheckMNPResponse extends ResponseAbstract{
    private CheckMNPResponseData data;
}
