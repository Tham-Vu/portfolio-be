package com.example.portfoliobe.dto.request;

import com.example.portfoliobe.dto.data.CheckMNPRequestData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CheckMNPRequest extends RequestAbstract{
    private CheckMNPRequestData data;
}
