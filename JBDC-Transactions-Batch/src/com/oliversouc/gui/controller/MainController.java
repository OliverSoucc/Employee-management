package com.oliversouc.gui.controller;

import com.oliversouc.be.Employee;
import com.oliversouc.gui.model.MainModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private TextField filter;
    @FXML
    private TableView<Employee> tableView;
    @FXML
    private TableColumn<Employee,Integer> columnID;
    @FXML
    private TableColumn<Employee, String> columnName;
    @FXML
    private TableColumn<Employee, Double> columnPersonalBonus;
    @FXML
    private TableColumn<Employee, Byte> columnOnLeave;
    @FXML
    private TableColumn<Employee, String> columnPhoneNumber;
    @FXML
    private TableColumn<Employee, Double> columnSalary;

    private final MainModel mainModel;
    private int currentEmployeeInt = -1;



    public MainController() {
        this.mainModel = MainModel.getInstance();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTableView();
    }



    public void setTableView(){
        columnID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnPersonalBonus.setCellValueFactory(new PropertyValueFactory<>("personalBonus"));
        columnOnLeave.setCellValueFactory(new PropertyValueFactory<>("onLeave"));
        columnPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        columnSalary.setCellValueFactory(new PropertyValueFactory<>("realSalary"));
        tableView.setItems(mainModel.getEmployeeObservableList());
    }


    public void filterOnAction(KeyEvent keyEvent) {
        String stringInFilter = filter.getText();
        ObservableList<Employee> filteredEmployees = mainModel.getEmployeesFilterList(stringInFilter);
        tableView.setItems(filteredEmployees);
    }

    public void deletingWithBackspace(KeyEvent keyEvent) {
        Employee employeeTODelete = tableView.getSelectionModel().getSelectedItem();
        if (keyEvent.getCode() == KeyCode.BACK_SPACE){
            mainModel.deleteEmployee(employeeTODelete);
        }
    }

    public void openAddEmployeeWindow(ActionEvent actionEvent) throws IOException {
        Parent root;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/oliversouc/gui/view/AddEmployeeView.fxml"));
        root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Add Employee");
        stage.setScene(new Scene(root));
        stage.show();

    }

    public void EditButtonOnAction(ActionEvent actionEvent) throws IOException {
        if (tableView.getSelectionModel().getSelectedItem() != null) {
            Parent root;
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/oliversouc/gui/view/EditEmployeeView.fxml"));
            root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Add Employee");
            stage.setScene(new Scene(root));
            stage.show();

            EditEmployeeController editEmployeeController = loader.getController();
            editEmployeeController.setMainController(this);

            currentEmployeeInt = tableView.getSelectionModel().getSelectedItem().getID();
            System.out.println(currentEmployeeInt);
            editEmployeeController.setEmployeeToBeUpdated(mainModel.getEmployee(currentEmployeeInt));
        }else{
            System.out.println("You have to select some employee");
        }
    }
}
