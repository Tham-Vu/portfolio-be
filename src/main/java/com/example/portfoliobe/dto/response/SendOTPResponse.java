package com.example.portfoliobe.dto.response;

import com.example.portfoliobe.dto.data.SendOTPResponseData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SendOTPResponse extends ResponseAbstract{
    private SendOTPResponseData data;
}
