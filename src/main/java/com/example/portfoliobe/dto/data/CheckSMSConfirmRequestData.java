package com.example.portfoliobe.dto.data;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CheckSMSConfirmRequestData {
    @JsonProperty("phone_number")
    private String phoneNumber;
}
