package com.example.portfoliobe.rsa;

import org.springframework.util.Base64Utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RSAKey {
    public  static void main (String[] args) throws NoSuchAlgorithmException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance(("RSA"));
        generator.initialize(1024);
        KeyPair pair = generator.generateKeyPair();
        PrivateKey privateKey = pair.getPrivate();
        PublicKey publicKey = pair.getPublic();
        byte[] privateKeyBytes = privateKey.getEncoded();
        String privateKeyString = Base64.getMimeEncoder().encodeToString(privateKeyBytes);

        byte[] publicKeyBytes = publicKey.getEncoded();
        String publicKeyString = Base64.getMimeEncoder().encodeToString(publicKeyBytes);

        String publicKeyFile = Paths.get("public.pem").toString();
        String privateKeyFile = Paths.get("private.pem").toString();

        //Store keys in files
        saveKey(publicKeyFile, publicKeyString);
        saveKey(privateKeyFile, privateKeyString);
    }
    public static void saveKey(String folder, String key){
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(folder), StandardCharsets.UTF_8))
        {
            writer.write("-----BEGIN PUBLIC KEY-----");
            writer.write(System.lineSeparator());
            writer.write(key);
            writer.write(System.lineSeparator());
            writer.write("-----END PUBLIC KEY-----");
        }
        catch(Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }
    public void getPrivateKey() throws NoSuchAlgorithmException {
        try {
            File privateKeyFile = new File("private.pem");
            byte[] privateBytes = Files.readAllBytes(privateKeyFile.toPath());

            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }
    public void getPublicKey() throws NoSuchAlgorithmException {
        try {
            File publicKeyFile = new File("public.pem");
            byte[] publicBytes = Files.readAllBytes(publicKeyFile.toPath());

            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(keySpec);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }
}
