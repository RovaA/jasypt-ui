/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.rova.jasypt.services;

/**
 *
 * @author andri
 */
public enum JasyptAlgorithm {
    
    PBEWithHMACSHA512AndAES_256("PBEWithHMACSHA512AndAES_256"),
    PBEWithMD5AndTripleDES("PBEWithMD5AndTripleDES");
    
    private final String value;
    
    JasyptAlgorithm(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
    
}
