package com.example.portfoliobe.rsa;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class GenerateSignature {
    public static PrivateKey getPrivateKey(String filename) throws NoSuchAlgorithmException {
        try {
            File file = new File(filename);

            String key = new String(Files.readAllBytes(file.toPath()), Charset.defaultCharset());

            String privateKeyPEM = key
                    .replace("-----BEGIN PRIVATE KEY-----", "")
                    .replaceAll(System.lineSeparator(), "")
                    .replace("-----END PRIVATE KEY-----", "");

            byte[] encoded = Base64.decodeBase64(privateKeyPEM);

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encoded);
            RSAPrivateKey privateKey = (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
            System.out.println(privateKey);
            return privateKey;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (InvalidKeySpecException ex) {
            throw new RuntimeException(ex);
        }
    }
    public static PrivateKey getPrivateKeyFromString(String key) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String privateKeyPEM = key.replace("-----BEGIN PRIVATE KEY-----", "")
                .replaceAll(System.lineSeparator(), "")
                .replace("-----END PRIVATE KEY-----", "");
        byte[] encoded = java.util.Base64.getMimeDecoder().decode(privateKeyPEM);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encoded);
        return (PrivateKey) keyFactory.generatePublic(keySpec);

    }
    public static RSAPublicKey getPublicKey(String filename) throws NoSuchAlgorithmException {
        try {
            File file = new File(filename);
            String key = new String(Files.readAllBytes(file.toPath()), Charset.defaultCharset());

            String publicKeyPEM = key
                    .replace("-----BEGIN PUBLIC KEY-----", "")
                    .replaceAll(System.lineSeparator(), "")
                    .replace("-----END PUBLIC KEY-----", "");

            byte[] encoded = Base64.decodeBase64(publicKeyPEM);

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(encoded);
            RSAPublicKey publicKey = (RSAPublicKey) keyFactory.generatePublic(keySpec);
            System.out.println(publicKey.getEncoded());
            return publicKey;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }
    public static PublicKey getPublicKeyFromString(String key) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String publicKeyPEM = key.replace("-----BEGIN PRIVATE KEY-----", "")
                .replaceAll(System.lineSeparator(), "")
                .replace("-----END PRIVATE KEY-----", "");
        byte[] encoded = java.util.Base64.getMimeDecoder().decode(publicKeyPEM);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(encoded);
        System.out.println("From String" + keyFactory.generatePublic(keySpec).getEncoded());
        return keyFactory.generatePublic(keySpec);
    }
    public static String generateSignature(String message, PrivateKey privateKey) throws NoSuchAlgorithmException {
        try {
            //hash message
//            byte[] messageBytes = Files.readAllBytes(Paths.get("message.txt"));
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageHash = md.digest(message.getBytes(Charset.forName("UTF-8")));

            //sign
            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initSign(privateKey);
            signature.update(messageHash);
            return new String(Base64.encodeBase64(signature.sign()), Charset.forName("UTF-8"));
        } catch (SignatureException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }
    public static String encryptFile(PublicKey publicKey, byte[] messageHash) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] encryptedMessageHash = cipher.doFinal(messageHash);
            return Base64.encodeBase64String(encryptedMessageHash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        }
    }
    public static String decrypt(PrivateKey privateKey, byte[] messageHash){
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] decryptedMessageHash = cipher.doFinal(messageHash);
            return Base64.encodeBase64String(decryptedMessageHash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean verify(PublicKey publicKey, String message, byte[] signedData) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(publicKey);
        byte[] messageBytes = java.util.Base64.getMimeDecoder().decode(message);
        signature.update(messageBytes);
        boolean isCorrect = signature.verify(signedData);
        return isCorrect;
    }

    public static void main(String[] args) {
        try {
            String sPublicKey = "-----BEGIN PUBLIC KEY-----\n" +
                    "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmC+VpPYvD/6/VT9dEcDPdDLlV9txZO/N\n" +
                    "dV1hIzMThsXJpw0SlBpEo2SreC2FyeDWYGg8z/V3DpTXehJkyDzZ7raV79Z9y4tte60HnZ5+qN/Y\n" +
                    "EBFmnNQq5wkrNv2viUte8rHRth9PMd4CbviQi1tCUuqmHeKGpD8Pzq6/W9jPen5e9X2g4U6W9cF5\n" +
                    "JKoJRdv73hfDyKgZGPtA3W6qNNynJ4pzQzC3aCDJl2crMtV62MxNGnmWmqyeUikCzi9w/8lrfAYo\n" +
                    "r/MMXjuMx9IAnz2BPQLmyeRRSu6uEQYjXOg16ZRUMA8R0Ojy90HVFULD7QUVS3rNJhQvvh0G7Agl\n" +
                    "v+j9VwIDAQAB\n" +
                    "-----END PUBLIC KEY-----";
//            PublicKey pubKey = getPublicKeyFromString(sPublicKey);
            //get key
            PublicKey publicKey = getPublicKey("public.pem");
            PrivateKey privateKey = getPrivateKey("private.pem");
            //encrypt data
            String msisdn = "0965645160";
            byte[] msisdn_byte = msisdn.getBytes(Charset.defaultCharset());
            String msisdn_encrypt = encryptFile(publicKey, msisdn_byte);
            //decrypt
            String msisdn_decrypt = decrypt(privateKey, msisdn_encrypt.getBytes(Charset.defaultCharset()));
            String sign = generateSignature(msisdn, privateKey);
            if( verify(publicKey, msisdn, sign.getBytes(Charset.forName("UTF-8"))))
                System.out.println("Đúng chữ ký");
            else
                System.out.println("Không đúng chữ ký");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
