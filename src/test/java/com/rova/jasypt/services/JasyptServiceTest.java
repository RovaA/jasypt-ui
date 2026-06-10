package com.rova.jasypt.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JasyptServiceTest {

    @Test
    public void testEncryptionDecryption() {
        JasyptServiceImpl service = new JasyptServiceImpl();
        String password = "testPassword";
        String originalInput = "testInput";

        for (JasyptAlgorithm algorithm : JasyptAlgorithm.values()) {
            System.out.println("Testing algorithm: " + algorithm);
            Data data = new Data();
            data.setPassword(password);
            data.setAlgorithm(algorithm);
            data.setInput(originalInput);

            String encrypted = service.encrypt(data);
            Assertions.assertNotNull(encrypted);
            Assertions.assertNotEquals(originalInput, encrypted);

            data.setInput(encrypted);
            String decrypted = service.decrypt(data);
            Assertions.assertEquals(originalInput, decrypted);
        }
    }
}
