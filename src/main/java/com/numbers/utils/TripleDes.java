package com.numbers.utils;


import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class TripleDes {
    private final String deSede = "DESede";
    private final String desedeCipher = "DESede/CBC/PKCS5Padding";
    private final String digestText = "HG58YZ3CR9";
    private final String md5 = "md5";
    private final String utf8 = "utf-8";

    private TripleDes(){}

    private static TripleDes instance =  new TripleDes();

    public static TripleDes getInstance(){return instance;}

    public byte[] tripleDesEncrypt(String value) throws NoSuchAlgorithmException, UnsupportedEncodingException, NoSuchPaddingException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        final MessageDigest md = MessageDigest.getInstance(md5);
        final byte[] digestOfPassword = md.digest(digestText
                .getBytes(utf8));
        final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
        for (int j = 0, k = 16; j < 8;) {
            keyBytes[k++] = keyBytes[j++];
        }

        final SecretKey key = new SecretKeySpec(keyBytes, deSede);
        final IvParameterSpec iv = new IvParameterSpec(new byte[8]);
        final Cipher cipher = Cipher.getInstance(desedeCipher);
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);

        final byte[] plainTextBytes = value.getBytes(utf8);

        return cipher.doFinal(plainTextBytes);
    }

    public String tripleDesDecrypt(byte[] value) throws NoSuchAlgorithmException, UnsupportedEncodingException, NoSuchPaddingException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        final MessageDigest md = MessageDigest.getInstance(md5);
        final byte[] digestOfPassword = md.digest(digestText
                .getBytes(utf8));
        final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
        for (int j = 0, k = 16; j < 8;) {
            keyBytes[k++] = keyBytes[j++];
        }

        final SecretKey key = new SecretKeySpec(keyBytes, deSede);
        final IvParameterSpec iv = new IvParameterSpec(new byte[8]);
        final Cipher decipher = Cipher.getInstance(desedeCipher);
        decipher.init(Cipher.DECRYPT_MODE, key, iv);

        final byte[] plainText = decipher.doFinal(value);

        return new String(plainText, utf8);
    }
}
