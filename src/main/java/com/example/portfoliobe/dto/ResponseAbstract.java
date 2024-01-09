package com.example.portfoliobe.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class ResponseAbstract {
    private String request_id;
    private String request_time;
    private String request_name;
    private String code;
    private String message;
}
