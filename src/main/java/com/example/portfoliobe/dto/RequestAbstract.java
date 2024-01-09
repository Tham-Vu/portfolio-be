package com.example.portfoliobe.dto;

import lombok.*;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class RequestAbstract {
    private String request_id;
    private String request_time = String.valueOf((new Date()).getTime());
    private String request_name;
}
