package com.example.newsfeed.domain.user.security;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Convert
public class PasswordConverter implements AttributeConverter<String, String> {

    private static final String SECRET_KEY = "1234567890123456"; // 반드시 16자리 (AES-128용)
    private static final String ALGORITHM = "AES";

    @Override
    public String convertToDatabaseColumn(String attribute) {
        return CryptoUtil.encrypt(attribute);
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return CryptoUtil.decrypt(dbData);
    }

    private String encrypt(String input) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptedBytes = cipher.doFinal(input.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("암호화 중 오류 발생", e);
        }
    }

    private String decrypt(String input) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(input));
            return new String(decryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("복호화 중 오류 발생", e);
        }
    }
}
