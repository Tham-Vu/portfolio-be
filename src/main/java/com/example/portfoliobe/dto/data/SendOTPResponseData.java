package com.example.portfoliobe.dto.data;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SendOTPResponseData {
    private String id;
    private String telco;
}
