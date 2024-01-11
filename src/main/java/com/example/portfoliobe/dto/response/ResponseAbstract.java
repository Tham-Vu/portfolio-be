package com.example.portfoliobe.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class ResponseAbstract {
    @JsonProperty("request_id")
    private String requestId;
    @JsonProperty("request_time")
    private String requestTime;
    @JsonProperty("request_name")
    private String requestName;
    private String code;
    private String message;
}
