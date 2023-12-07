package com.microservice.membership.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.stereotype.Component;

@Component
public class EncryptionCreditCard {
    @Value("${encryptor.type}")
    private String encryptorType;

    @Value("${encryptor.key}")
    private String encryptorKey;

    public String encryptCreditCard(String creditCardNumber) {
        return Encryptors.text(encryptorKey, "deadbeef").encrypt(creditCardNumber);
    }

    public String decryptCreditCard(String encryptedCreditCard) {
        return Encryptors.text(encryptorKey, "deadbeef").decrypt(encryptedCreditCard);
    }
}
