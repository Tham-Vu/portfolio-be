package com.example.portfoliobe.dto.data;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SendOTPRequestData {
    @JsonProperty("phone_number")
    private String phoneNumber;
    @JsonProperty("client_otp")
    private String clientOtp;
    private String template;
}
