package com.example.portfoliobe.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class TokenRequest {
    private String client_code;
    private String client_secret;
}
