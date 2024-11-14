/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
        encryptor.setAlgorithm(data.getAlgorithm());
        if ("PBEWithHMACSHA512AndAES_256".equalsIgnoreCase(data.getAlgorithm())) {
            encryptor.setIvGenerator(new RandomIvGenerator());
        }
        return encryptor.encrypt(data.getInput());
    }

    @Override
    public String decrypt(Data data) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(data.getPassword());
        encryptor.setAlgorithm(data.getAlgorithm());
        if ("PBEWithHMACSHA512AndAES_256".equalsIgnoreCase(data.getAlgorithm())) {
            encryptor.setIvGenerator(new RandomIvGenerator());
        }
        return encryptor.decrypt(data.getInput());
    }

}
