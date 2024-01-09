package com.example.portfoliobe.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertiesConfiguration {
    @Value("${security.authentication.jwt.private-key}")
    private String privateKey;

    @Value("${security.authentication.jwt.public-key}")
    private String publicKey;

    @Value("${security.authentication.jwt.expires-in}")
    private long expiresIn;

    // Tạo các getter để truy cập các giá trị của bạn
    public String getPrivateKey() {
        return privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public long getExpiresIn() {
        return expiresIn;
    }
}
