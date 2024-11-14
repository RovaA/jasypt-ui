/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.rova.jasypt.services;

/**
 *
 * @author andri
 */
public interface JasyptService {
    
    String encrypt(Data data);

    String decrypt(Data data);
    
}
