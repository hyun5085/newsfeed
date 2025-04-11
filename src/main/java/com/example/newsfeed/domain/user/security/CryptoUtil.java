package com.example.newsfeed.domain.user.security;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class CryptoUtil {

    private static final String SECRET_KEY = "1234567890123456";

    private static final String ALGORITHM = "AES";

    /**
     * 문자 암호화
     *
     * @param input the input
     * @return the string
     */
    public static String encrypt(String input) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptedBytes = cipher.doFinal(input.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("암호화 실패", e);
        }
    }

    /**
     * 문자 복호화
     *
     * @param encrypted the encrypted
     * @return the string
     */
    public static String decrypt(String encrypted) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encrypted));
            return new String(decryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("복호화 실패", e);
        }
    }
}
