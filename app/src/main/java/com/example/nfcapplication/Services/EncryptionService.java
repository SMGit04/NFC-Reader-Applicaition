package com.example.nfcapplication.Services;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;

public class EncryptionService {


    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String encrypt(String plainText, PrivateKey privateKey) throws Exception {

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());

        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public PublicKey getPublicKey(String publicKey) throws Exception {
        publicKey = publicKey
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s+", "");

        byte[] publicBytes = Base64.getDecoder().decode(publicKey);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicBytes);

        return keyFactory.generatePublic(keySpec);
    }
    // TODO: Implement encryption
}
