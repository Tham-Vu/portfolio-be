package com.example.portfoliobe.jwt;

import com.example.portfoliobe.configs.PropertiesConfiguration;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import java.io.Serializable;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Date;

@Component
@Getter
public class JwtUtils implements Serializable {
    private final PropertiesConfiguration properties;

    public JwtUtils(PropertiesConfiguration properties) {
        this.properties = properties;
    }

    public String generateToken(){
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, properties.getPrivateKey())
                .setExpiration(new Date(System.currentTimeMillis() + properties.getExpiresIn()))
                .compact();
    }
    public String generateSignature(byte[] body, String privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException {
        //hash md5
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hashMd5 = md.digest(body);
        //rsa
        byte[] privateKeyBytes = Base64Utils.decodeFromString(privateKey);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateRSAKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(privateKeyBytes));
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateRSAKey);
        signature.update(hashMd5);
        byte[] signatureResult = signature.sign();
        return Base64Utils.encodeToString(signatureResult);
    }

}
