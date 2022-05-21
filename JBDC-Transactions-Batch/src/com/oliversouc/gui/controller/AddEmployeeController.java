package com.oliversouc.gui.controller;

import com.oliversouc.gui.model.MainModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddEmployeeController {
    @FXML
    private TextField realSalaryField;
    @FXML
    private Button cancelButton;
    @FXML
    private TextField nameField;
    @FXML
    private TextField salaryField;
    @FXML
    private TextField onLeaveField;
    @FXML
    private TextField phoneNumberField;

    private final MainModel mainModel;

    public AddEmployeeController() {
        this.mainModel = MainModel.getInstance();
    }

    public void AddButtonOnAction(ActionEvent actionEvent) {
        String name = nameField.getText();
        double personalBonus = Double.parseDouble(salaryField.getText());
        byte onLeave = Byte.parseByte(onLeaveField.getText());
        String phoneNumber = phoneNumberField.getText();
        double realSalary = Double.parseDouble(realSalaryField.getText());

        mainModel.createEmployee(name, personalBonus, onLeave, phoneNumber, realSalary);
    }

    public void CancelButtonOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
