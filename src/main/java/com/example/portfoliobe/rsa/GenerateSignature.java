package com.example.portfoliobe.rsa;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import sun.security.rsa.RSAPrivateCrtKeyImpl;

/**
 *
 * @author MSI
 */
public class GenerateSignature {

    private static String getKey(String filename) throws IOException {
        // Read key from file
        String strKeyPEM = "";
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = br.readLine()) != null) {
            strKeyPEM += line + "n";
        }
        br.close();
        return strKeyPEM;
    }

    /**
     * Constructs a private key (RSA) from the given file
     *
     * @param filename PEM Private Key
     * @return RSA Private Key
     * @throws IOException
     * @throws GeneralSecurityException
     */
    public static RSAPrivateKey getPrivateKey(String filename) throws IOException, GeneralSecurityException {
        String privateKeyPEM = getKey(filename);
        return getPrivateKeyFromString(privateKeyPEM);
    }

    /**
     * Constructs a private key (RSA) from the given string
     *
     * @param key PEM Private Key
     * @return RSA Private Key
     * @throws IOException
     * @throws GeneralSecurityException
     */
    public static RSAPrivateKey getPrivateKeyFromString(String key) throws IOException, GeneralSecurityException {
        String privateKeyPEM = key;

        // Remove the first and last lines
//        privateKeyPEM = privateKeyPEM.replace(System.lineSeparator(), "");
//        privateKeyPEM = privateKeyPEM.replace("-----BEGIN PRIVATE KEY-----", "");
//        privateKeyPEM = privateKeyPEM.replace("-----END PRIVATE KEY-----", "");
//        privateKeyPEM = privateKeyPEM.replace("\r\n", "");
//        privateKeyPEM = privateKeyPEM.replace("\n", "");

        privateKeyPEM = privateKeyPEM.replaceAll("(-+BEGIN PRIVATE KEY-+\\r?\\n|-+END PRIVATE KEY-+\\r?\\n?)", "");

        // Base64 decode data
        byte[] encoded = Base64.decodeBase64(privateKeyPEM);

        KeyFactory kf = KeyFactory.getInstance("RSA");
        RSAPrivateKey privKey = (RSAPrivateKey) kf.generatePrivate(new PKCS8EncodedKeySpec(encoded));
        return privKey;
    }

    /**
     * Constructs a public key (RSA) from the given file
     *
     * @param filename PEM Public Key
     * @return RSA Public Key
     * @throws IOException
     * @throws GeneralSecurityException
     */
    public static RSAPublicKey getPublicKey(String filename) throws IOException, GeneralSecurityException {
        String publicKeyPEM = getKey(filename);
        return getPublicKeyFromString(publicKeyPEM);
    }

    public static RSAPublicKey generatePublicKey(PrivateKey privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        //you need to know the Implementation. the interfaces doesn't have all informations or parse it out of the privateKey.toString()
        RSAPrivateCrtKeyImpl rsaPrivateKey = (RSAPrivateCrtKeyImpl)privateKey;

        //create a KeySpec and let the Factory due the Rest. You could also create the KeyImpl by your own.
        PublicKey publicKey = KeyFactory.getInstance("RSA").generatePublic(new RSAPublicKeySpec(rsaPrivateKey.getModulus(), rsaPrivateKey.getPublicExponent()));
        System.out.println(publicKey.getEncoded()); //store it - that's it
        return  (RSAPublicKey)publicKey;
    }

    /**
     * Constructs a public key (RSA) from the given string
     *
     * @param key PEM Public Key
     * @return RSA Public Key
     * @throws IOException
     * @throws GeneralSecurityException
     */
    public static RSAPublicKey getPublicKeyFromString(String key) throws IOException, GeneralSecurityException {
        String publicKeyPEM = key;

        // Remove the first and last lines
        publicKeyPEM = publicKeyPEM.replace(System.lineSeparator(), "");
        publicKeyPEM = publicKeyPEM.replace("-----BEGIN PUBLIC KEY-----", "");
        publicKeyPEM = publicKeyPEM.replace("-----END PUBLIC KEY-----", "");

        publicKeyPEM = publicKeyPEM.replace("\r\n", "");
        publicKeyPEM = publicKeyPEM.replace("\n", "");

        // Base64 decode data
        byte[] encoded = Base64.decodeBase64(publicKeyPEM);
        //byte[] encoded = DatatypeConverter.parseHexBinary(key);

        KeyFactory kf = KeyFactory.getInstance("RSA");
        RSAPublicKey pubKey = (RSAPublicKey) kf.generatePublic(new X509EncodedKeySpec(encoded));
        return pubKey;
    }

    /**
     * @param privateKey
     * @param message
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws SignatureException
     * @throws UnsupportedEncodingException
     */
    public static String sign(PrivateKey privateKey, String message) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, UnsupportedEncodingException {
        Signature sign = Signature.getInstance("SHA1withRSA");
        sign.initSign(privateKey);
        //sign.update(message.getBytes(Constant.SYSTEM_CHARSET ));
        sign.update(DigestUtils.md5(message));
        return new String(Base64.encodeBase64(sign.sign()), Charset.defaultCharset());
    }

    public static byte[] sign(PrivateKey privateKey, byte[] message) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, UnsupportedEncodingException {
        Signature sign = Signature.getInstance("SHA1withRSA");
        sign.initSign(privateKey);
        sign.update(DigestUtils.md5(message));
        return Base64.encodeBase64(sign.sign());
    }


    /**
     * @param publicKey
     * @param message
     * @param signature
     * @return
     * @throws SignatureException
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     * @throws InvalidKeyException
     */
    public static boolean verify(PublicKey publicKey, String message, String signature) throws SignatureException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException {
        Signature sign = Signature.getInstance("SHA1withRSA");
        sign.initVerify(publicKey);
        //sign.update(message.getBytes(Constant.SYSTEM_CHARSET ));
        sign.update(DigestUtils.md5(message));
        return sign.verify(Base64.decodeBase64(signature.getBytes(Charset.defaultCharset())));
    }

    public static boolean verify(PublicKey publicKey, byte[] message, byte[] signature) {
        try {
            Signature sign = Signature.getInstance("SHA1withRSA");
            sign.initVerify(publicKey);
            sign.update(DigestUtils.md5(message));
            return sign.verify(Base64.decodeBase64(signature));
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Encrypts the text with the public key (RSA)
     *
     * @param rawText Text to be encrypted
     * @param publicKey
     * @return
     * @throws IOException
     * @throws GeneralSecurityException
     */
    public static String encrypt(String rawText, PublicKey publicKey) throws IOException, GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return Base64.encodeBase64String(cipher.doFinal(rawText.getBytes(Charset.defaultCharset())));
    }

    /**
     * Decrypts the text with the private key (RSA)
     *
     * @param cipherText Text to be decrypted
     * @param privateKey
     * @return Decrypted text (Base64 encoded)
     * @throws IOException
     * @throws GeneralSecurityException
     */
    public static String decrypt(String cipherText, PrivateKey privateKey) throws IOException, GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return new String(cipher.doFinal(Base64.decodeBase64(cipherText)), Charset.defaultCharset() );
    }

    public static void main(String[] args) throws IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, BadPaddingException {
        try {
            String privateKeyPath = "private.pem";
            String publicKeyPath = "public.pem";
            //
            String sPublicKey = "-----BEGIN PUBLIC KEY-----\n" +
                    "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmC+VpPYvD/6/VT9dEcDPdDLlV9txZO/N\n" +
                    "dV1hIzMThsXJpw0SlBpEo2SreC2FyeDWYGg8z/V3DpTXehJkyDzZ7raV79Z9y4tte60HnZ5+qN/Y\n" +
                    "EBFmnNQq5wkrNv2viUte8rHRth9PMd4CbviQi1tCUuqmHeKGpD8Pzq6/W9jPen5e9X2g4U6W9cF5\n" +
                    "JKoJRdv73hfDyKgZGPtA3W6qNNynJ4pzQzC3aCDJl2crMtV62MxNGnmWmqyeUikCzi9w/8lrfAYo\n" +
                    "r/MMXjuMx9IAnz2BPQLmyeRRSu6uEQYjXOg16ZRUMA8R0Ojy90HVFULD7QUVS3rNJhQvvh0G7Agl\n" +
                    "v+j9VwIDAQAB\n" +
                    "-----END PUBLIC KEY-----";

            String sPrivateKey = "-----BEGIN PRIVATE KEY-----\n" +
                    "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCYL5Wk9i8P/r9VP10RwM90MuVX\n" +
                    "23Fk7811XWEjMxOGxcmnDRKUGkSjZKt4LYXJ4NZgaDzP9XcOlNd6EmTIPNnutpXv1n3Li217rQed\n" +
                    "nn6o39gQEWac1CrnCSs2/a+JS17ysdG2H08x3gJu+JCLW0JS6qYd4oakPw/Orr9b2M96fl71faDh\n" +
                    "Tpb1wXkkqglF2/veF8PIqBkY+0Ddbqo03KcninNDMLdoIMmXZysy1XrYzE0aeZaarJ5SKQLOL3D/\n" +
                    "yWt8Biiv8wxeO4zH0gCfPYE9AubJ5FFK7q4RBiNc6DXplFQwDxHQ6PL3QdUVQsPtBRVLes0mFC++\n" +
                    "HQbsCCW/6P1XAgMBAAECggEAavgfdhj4kYSdFh14nI7rAqD3pV6AgNMxlkoxid1P305M5FeC9IjW\n" +
                    "MAcFo25T+MdJx+/ctsuxiJVEM/CT2E81W94I29DnENgK94Bytu591tOn+ftRJfDXyxcdwgRkitto\n" +
                    "fKxSXHH7Z3DGQxwB9YIKx19Zre3Yd8A/qyYXKUbm5xCwy0aUgkySaL1nkiHHrJ6M2FTnt0fqE1U3\n" +
                    "5ue1Z5+XJL1jMAaJwJapBevCq54IS3uAB2/1oZP8ZP8zfT89JatGIERCi/8BiMvIpk//qZT1aDHH\n" +
                    "KMDYY6feFbLXRp52AEDNWWH/xFMEPhg1hpSGsB2BOwrzQA9HBnHbn25V1XCdEQKBgQDIH2KYEAow\n" +
                    "BRu4bs2OLVX5V8+zIcwK3ZFSPg4S7teOmkBnf8bUkZTlcHKMlv1Imvvd7iMWsgv4Yma3QLE9zoUY\n" +
                    "xwKmYyPgGinj4b73Lzhf3bIKypoBZTKWIaVTwWikcLZ0jVXCLV//W92O3OD+QPPf9/HAT1zglveo\n" +
                    "7E8djlHSjwKBgQDCrbfZCTDFAf+kNCUVVSoIa+szgxmZ9w/oNWw574wecknim9dVhOaiT5NVqSOu\n" +
                    "s4rkYkiFwfNNkxYrc2zFFyfyxG19LLqliMRxeX8cb1ryfr9N8UZLF9JkHiqBKaP3LdZ6m3bJ4eJd\n" +
                    "zsAW8eXHwhWdBhCPLl0mzth8eVFyYpTsuQKBgFT9h0jfXwQ/1yL/48qjFGU91tbT3q0qpkIQWrfN\n" +
                    "ubw1ZG7QRGgYn+zFUdDFezIvqBH7xekomo7Vc5wWHHAMWlhIhKaXajANzIacZBbkdgO4yplsq/U1\n" +
                    "vkMmbOliNYfI2qQrNiWuUZvIrxKyWdeBTFFtIr2yttYnJ20VVxBNVhCfAoGAQIUCYVPCbghziSAf\n" +
                    "Cirne1MI6FGSsoxHIVZTQAA8F/sYsZmkjBs3tKNa2RDtWoD2jypjjrDwycztlAj1C3zZKIQhJVMl\n" +
                    "qKbjNBhoICxnn+aR0GseONT0TWoR/CyowsrWQlwzykSqmRxkXTZeSQ3xy9CuvEVJagszhE1QyPAT\n" +
                    "G0ECgYBL8MRSVjKwcNcq6R4jYaZpibTy2kbFQZb7azF0VGu+n0DGAmEuztSeDUXtEE6sTjDpGAhg\n" +
                    "78G6XYxRvH/bEOQqw32ffLGu/s+Xb89rsXvSjzV83n55MH73Cu7iZE9vYrwBmsHV2jeLZtcmk027\n" +
                    "6/GFXVqmuAmsDRKBoa9x11LUrw==\n" +
                    "-----END PRIVATE KEY-----";

            RSAPublicKey pubKey = getPublicKeyFromString(sPublicKey);
            RSAPrivateKey privateKey = getPrivateKeyFromString(sPrivateKey);
            //
            String msisdn = "0936764433";
            String msisdn_encrypt = encrypt(msisdn, pubKey);
            //
            System.out.println("msisdn_encrypt: " + msisdn_encrypt);
            String msisdn_decrypt = decrypt(msisdn_encrypt, privateKey);
            System.out.println("msisdn_decrypt: " + msisdn_decrypt);
            //
            String sSign  =  sign(privateKey, msisdn);
            System.out.println("Sign data: " + sSign);

            if( verify(pubKey, msisdn, sSign))
                System.out.println("Đúng chữ ký");
            else
                System.out.println("Không đúng chữ ký");

            //
            System.out.println("################Thử với Public key được sinh ra mới");
            //
            RSAPublicKey newPubKey = generatePublicKey(privateKey);
            msisdn = "0936764433";
            msisdn_encrypt = encrypt(msisdn, pubKey);
            //
            System.out.println("msisdn_encrypt: " + msisdn_encrypt);
            msisdn_decrypt = decrypt(msisdn_encrypt, privateKey);
            System.out.println("msisdn_decrypt: " + msisdn_decrypt);
            //
            sSign  =  sign(privateKey, msisdn);
            System.out.println("Sign data: " + sSign);
            RSAPublicKey publicKey = getPublicKey(publicKeyPath);
            if( verify(publicKey, msisdn, sSign))
                System.out.println("Đúng chữ ký");
            else
                System.out.println("Không đúng chữ ký");

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }
}
