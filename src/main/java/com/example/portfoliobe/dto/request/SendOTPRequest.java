package com.example.portfoliobe.dto.request;

import com.example.portfoliobe.dto.data.SendOTPRequestData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SendOTPRequest extends RequestAbstract{
    private SendOTPRequestData data;
}
