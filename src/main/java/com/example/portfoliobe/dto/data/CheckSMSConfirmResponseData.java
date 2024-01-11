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
public class CheckSMSConfirmResponseData {
    private String id;
    private int status;
    @JsonProperty("expire_time")
    private String expireTime;
}
