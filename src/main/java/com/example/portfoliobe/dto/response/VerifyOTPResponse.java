package com.example.portfoliobe.dto.response;

import com.example.portfoliobe.dto.data.VerifyOTPResponseData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VerifyOTPResponse extends ResponseAbstract{
    private VerifyOTPResponseData data;
}
