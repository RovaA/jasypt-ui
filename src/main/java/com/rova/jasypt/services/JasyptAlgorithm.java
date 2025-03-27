package com.rova.jasypt.services;

/**
 *
 * @author andri
 */
public enum JasyptAlgorithm {
    
    PBEWithHMACSHA512AndAES_256("PBEWithHMACSHA512AndAES_256"),
    PBEWithMD5AndTripleDES("PBEWithMD5AndTripleDES"),
    PBEWITHMD5ANDDES("PBEWITHMD5ANDDES");
    
    private final String value;
    
    JasyptAlgorithm(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
    
}
