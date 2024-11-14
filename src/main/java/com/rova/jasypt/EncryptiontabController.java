/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.rova.jasypt;

import com.google.inject.Inject;
import com.rova.jasypt.services.Data;
import com.rova.jasypt.services.JasyptAlgorithm;
import com.rova.jasypt.services.JasyptService;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author andri
 */
public class EncryptiontabController implements Initializable {

    @FXML
    private TextField inputTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private ChoiceBox<JasyptAlgorithm> algorithmChoiceBox;

    @FXML
    private TextArea resultTextArea;

    @Inject
    private JasyptService jasyptService;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<JasyptAlgorithm> items = FXCollections.observableArrayList(Arrays.asList(JasyptAlgorithm.values()));
        algorithmChoiceBox.setItems(items);
        algorithmChoiceBox.setValue(JasyptAlgorithm.PBEWithHMACSHA512AndAES_256);
    }

    @FXML
    private void onEncrypt(ActionEvent event) {
        Data data = new Data();
        data.setInput(inputTextField.getText());
        data.setPassword(passwordTextField.getText());
        data.setAlgorithm(algorithmChoiceBox.getValue().toString());
        resultTextArea.setText(jasyptService.encrypt(data));
    }

}
