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
        var encryptor = getEncryptor(data);
        return encryptor.encrypt(data.input());
    }

    @Override
    public String decrypt(Data data) {
        var encryptor = getEncryptor(data);
        return encryptor.decrypt(data.input());
    }

    private StandardPBEStringEncryptor getEncryptor(Data data) {
        var encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(data.password());
        encryptor.setAlgorithm(data.algorithm().toString());
        if (JasyptAlgorithm.PBEWithHMACSHA512AndAES_256 == data.algorithm()) {
            encryptor.setIvGenerator(new RandomIvGenerator());
        }
        return encryptor;
    }

}
