package com.rova.jasypt.services;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.iv.RandomIvGenerator;

/**
 *
 * @author andri
 */
public class JasyptServiceImpl implements JasyptService {

    @Override
    public String encrypt(Data data) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(data.getPassword());
        encryptor.setAlgorithm(data.getAlgorithm().toString());
        if (JasyptAlgorithm.PBEWithHMACSHA512AndAES_256 == data.getAlgorithm()) {
            encryptor.setIvGenerator(new RandomIvGenerator());
        }
        return encryptor.encrypt(data.getInput());
    }

    @Override
    public String decrypt(Data data) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(data.getPassword());
        encryptor.setAlgorithm(data.getAlgorithm().toString());
        if (JasyptAlgorithm.PBEWithHMACSHA512AndAES_256 == data.getAlgorithm()) {
            encryptor.setIvGenerator(new RandomIvGenerator());
        }
        return encryptor.decrypt(data.getInput());
    }

}
