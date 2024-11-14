package com.rova.jasypt.services;

/**
 *
 * @author andri
 */
public interface JasyptService {
    
    String encrypt(Data data);

    String decrypt(Data data);
    
}
