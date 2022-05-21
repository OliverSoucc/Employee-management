package com.oliversouc.gui.controller;

import com.oliversouc.be.Employee;
import com.oliversouc.gui.model.MainModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditEmployeeController {
    @FXML
    private Button cancelButton;
    @FXML
    private TextField nameFieldEdit, salaryFieldEdit, onLeaveFieldEdit, phoneNumberFieldEdit, realSalaryFieldEdit;

    MainController mainController;
    Employee employee;
    MainModel mainModel;

    public EditEmployeeController() {
        this.mainModel = new MainModel();
    }

    public void EditButtonOnAction(ActionEvent actionEvent) {
        employee.setName(nameFieldEdit.getText());
        employee.setPersonalBonus(Double.parseDouble(salaryFieldEdit.getText()));
        employee.setOnLeave(Byte.parseByte(onLeaveFieldEdit.getText()));
        employee.setPhoneNumber(phoneNumberFieldEdit.getText());
        employee.setRealSalary(Double.parseDouble(realSalaryFieldEdit.getText()));
        mainModel.updateEmployee(employee);
    }

    public void CancelButtonInEditOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setEmployeeToBeUpdated(Employee employee) {
        this.employee = employee;
        nameFieldEdit.setText(employee.getName());
        salaryFieldEdit.setText(String.valueOf(employee.getPersonalBonus()));
        onLeaveFieldEdit.setText(String.valueOf(employee.getOnLeave()));
        phoneNumberFieldEdit.setText(employee.getPhoneNumber());
        realSalaryFieldEdit.setText(String.valueOf(employee.getRealSalary()));
    }
}
