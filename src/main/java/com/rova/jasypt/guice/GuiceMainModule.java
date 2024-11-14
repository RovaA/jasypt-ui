/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rova.jasypt.guice;

import com.google.inject.AbstractModule;
import com.rova.jasypt.EncryptiontabController;
import com.rova.jasypt.MainController;
import com.rova.jasypt.services.JasyptService;
import com.rova.jasypt.services.JasyptServiceImpl;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

/**
 *
 * @author andri
 */
public class GuiceMainModule extends AbstractModule {
    
    @Override
    protected void configure() {
        bind(MainController.class);
        bind(EncryptiontabController.class);
        
        bind(StandardPBEStringEncryptor.class);
        
        bind(JasyptService.class).to(JasyptServiceImpl.class);
    }
    
}
