package com.rova.jasypt.controllers;

import com.google.inject.Inject;
import com.rova.jasypt.services.Data;
import com.rova.jasypt.services.JasyptAlgorithm;
import com.rova.jasypt.services.JasyptService;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author andri
 */
public class DecryptiontabController implements Initializable {

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
    private void onDecrypt(ActionEvent event) {
        Data data = new Data();
        data.setInput(inputTextField.getText());
        data.setPassword(passwordTextField.getText());
        data.setAlgorithm(algorithmChoiceBox.getValue().toString());
        resultTextArea.setText(jasyptService.decrypt(data));
    }

}
