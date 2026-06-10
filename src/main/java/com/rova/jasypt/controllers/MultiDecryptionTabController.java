package com.rova.jasypt.controllers;

import com.google.inject.Inject;
import com.rova.jasypt.services.Data;
import com.rova.jasypt.services.JasyptAlgorithm;
import com.rova.jasypt.services.JasyptService;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author andri
 */
public class MultiDecryptionTabController implements Initializable {

    @FXML
    private TextField passwordTextField;

    @FXML
    private ChoiceBox<JasyptAlgorithm> algorithmChoiceBox;

    @FXML
    private VBox fieldsContainer;

    @Inject
    private JasyptService jasyptService;

    private final JasyptAlgorithm DEFAULT_ALGORITHM = JasyptAlgorithm.PBEWithHMACSHA512AndAES_256;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<JasyptAlgorithm> items = FXCollections
                .observableArrayList(Arrays.asList(JasyptAlgorithm.values()));
        algorithmChoiceBox.setItems(items);
        algorithmChoiceBox.setValue(DEFAULT_ALGORITHM);

        passwordTextField.textProperty().addListener((obs, oldValue, newValue) -> decryptAllRows());
        algorithmChoiceBox.valueProperty().addListener((obs, oldValue, newValue) -> decryptAllRows());

        addFieldRow();
    }

    @FXML
    private void onAddField(ActionEvent event) {
        addFieldRow();
    }

    @FXML
    private void onDecryptAll(ActionEvent event) {
        decryptAllRows();
    }

    @FXML
    private void onCopyAll(ActionEvent event) {
        var allResults = fieldsContainer.getChildren().stream()
                .map(this::toResultLine)
                .filter(line -> !line.isBlank())
                .collect(Collectors.joining(System.lineSeparator()));
        copyToClipboard(allResults);
    }

    @FXML
    private void onClear(ActionEvent event) {
        fieldsContainer.getChildren().clear();
        passwordTextField.clear();
        algorithmChoiceBox.setValue(DEFAULT_ALGORITHM);
        addFieldRow();
    }

    private void addFieldRow() {
        var nameField = new TextField();
        nameField.setPromptText("Field name");
        nameField.setPrefWidth(120.0);
        nameField.setMinWidth(80.0);

        var valueField = new TextField();
        valueField.setPromptText("Value to decrypt");
        HBox.setHgrow(valueField, Priority.ALWAYS);

        var resultField = new TextField();
        resultField.setPromptText("Result");
        resultField.setEditable(false);
        HBox.setHgrow(resultField, Priority.ALWAYS);

        var copyButton = new Button("Copy");
        copyButton.setOnAction(event -> copyToClipboard(resultField.getText()));

        var removeButton = new Button("X");

        var row = new HBox(8.0, nameField, valueField, resultField, copyButton, removeButton);
        row.setAlignment(Pos.CENTER_LEFT);

        removeButton.setOnAction(event -> {
            fieldsContainer.getChildren().remove(row);
            if (fieldsContainer.getChildren().isEmpty()) {
                addFieldRow();
            }
        });

        valueField.textProperty().addListener((obs, oldValue, newValue) -> decryptRow(row));

        fieldsContainer.getChildren().add(row);
    }

    private void decryptAllRows() {
        fieldsContainer.getChildren().forEach(this::decryptRow);
    }

    private void decryptRow(Node node) {
        var row = (HBox) node;
        var valueField = (TextField) row.getChildren().get(1);
        var resultField = (TextField) row.getChildren().get(2);

        var value = valueField.getText();
        var password = passwordTextField.getText();
        if (value == null || value.isEmpty() || password == null || password.isEmpty()) {
            resultField.clear();
            return;
        }
        try {
            var data = new Data(value, password, algorithmChoiceBox.getValue());
            resultField.setText(jasyptService.decrypt(data));
        } catch (Exception e) {
            resultField.clear();
        }
    }

    private String toResultLine(Node node) {
        var row = (HBox) node;
        var nameField = (TextField) row.getChildren().get(0);
        var resultField = (TextField) row.getChildren().get(2);

        var result = resultField.getText();
        if (result == null || result.isBlank()) {
            return "";
        }
        var name = nameField.getText();
        return (name == null || name.isBlank()) ? result : name + "=" + result;
    }

    private void copyToClipboard(String text) {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();
        content.putString(text);
        clipboard.setContent(content);
    }

}
