package com.rova.jasypt.services;

/**
 *
 * @author andri
 */
public class Data {
    
    private String input;
    
    private String password;
    
    private JasyptAlgorithm algorithm;

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public JasyptAlgorithm getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(JasyptAlgorithm algorithm) {
        this.algorithm = algorithm;
    }
    
}
