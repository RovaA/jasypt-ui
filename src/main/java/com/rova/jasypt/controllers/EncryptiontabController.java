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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

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
    
    private final JasyptAlgorithm DEFAULT_ALGORITHM = JasyptAlgorithm.PBEWithHMACSHA512AndAES_256;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<JasyptAlgorithm> items = FXCollections.observableArrayList(Arrays.asList(JasyptAlgorithm.values()));
        algorithmChoiceBox.setItems(items);
        algorithmChoiceBox.setValue(DEFAULT_ALGORITHM);
    }

    @FXML
    private void onCopyResult(ActionEvent event) {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();
        content.putString(resultTextArea.getText());
        clipboard.setContent(content);
    }

    @FXML
    private void onEncrypt(ActionEvent event) {
        Data data = new Data();
        data.setInput(inputTextField.getText());
        data.setPassword(passwordTextField.getText());
        data.setAlgorithm(algorithmChoiceBox.getValue());
        resultTextArea.setText(jasyptService.encrypt(data));
    }

    @FXML
    private void onClear(ActionEvent event) {
        inputTextField.clear();
        passwordTextField.clear();
        resultTextArea.clear();
        algorithmChoiceBox.setValue(DEFAULT_ALGORITHM);
    }

}
