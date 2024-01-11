package com.example.portfoliobe.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class RequestAbstract {
    @JsonProperty("request_id")
    private String requestId;
    @JsonProperty("request_time")
    private String requestTime = String.valueOf((new Date()).getTime());
    @JsonProperty("request_name")
    private String requestName;
}
