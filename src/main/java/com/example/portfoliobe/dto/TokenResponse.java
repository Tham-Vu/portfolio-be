package com.example.portfoliobe.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class TokenResponse {
    private String token_type;
    private String access_token;
    private String expires_in;
}
