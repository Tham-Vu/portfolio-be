package com.example.portfoliobe.dto.request;

import com.example.portfoliobe.dto.data.SendSMSConfirmRequestData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SendSMSConfirmRequest extends RequestAbstract{
    private SendSMSConfirmRequestData data;
}
