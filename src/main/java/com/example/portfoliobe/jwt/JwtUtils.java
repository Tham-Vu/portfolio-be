package com.example.portfoliobe.jwt;

import com.example.portfoliobe.configs.PropertiesConfiguration;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

@Component
@Getter
public class JwtUtils implements Serializable {
    private final PropertiesConfiguration properties;

    public JwtUtils(PropertiesConfiguration properties) {
        this.properties = properties;
    }

    public void display(){
        System.out.println(properties.getPrivateKey());
        System.out.println(properties.getPublicKey());
        System.out.println(properties.getExpiresIn());
    }

    public String generateToken(){
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, properties.getPrivateKey())
                .setExpiration(new Date(System.currentTimeMillis() + properties.getExpiresIn()))
                .compact();
    }

}
