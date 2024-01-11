package com.example.portfoliobe.dto.request;

import com.example.portfoliobe.dto.data.VerifyOTPRequestData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VerifyOTPRequest extends RequestAbstract{
    private VerifyOTPRequestData data;
}
