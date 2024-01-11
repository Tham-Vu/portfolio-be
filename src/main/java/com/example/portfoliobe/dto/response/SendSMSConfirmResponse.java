package com.example.portfoliobe.dto.response;

import com.example.portfoliobe.dto.data.SendSMSConfirmResponseData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SendSMSConfirmResponse extends ResponseAbstract{
    private SendSMSConfirmResponseData data;
}
