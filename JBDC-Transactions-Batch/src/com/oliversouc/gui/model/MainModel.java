package com.oliversouc.gui.model;

import com.oliversouc.be.Employee;
import com.oliversouc.bll.EmployeeManager;
import com.oliversouc.bll.FacadeBLL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MainModel {
    private ObservableList<Employee> employeeObservableList;
    private ObservableList<Employee> employeesFilterList;
    private final FacadeBLL facadeBLL;

    private static MainModel single_instance = null;

    public static MainModel getInstance(){
        if (single_instance == null){
            single_instance = new MainModel();
        }
        return single_instance;
    }

    public MainModel() {
        facadeBLL = new EmployeeManager();
    }

    public ObservableList<Employee> getEmployeeObservableList() {
        employeeObservableList = FXCollections.observableArrayList();
        employeeObservableList.setAll(facadeBLL.getAllEmployeesManager());
        return employeeObservableList;
    }

    public ObservableList<Employee> getEmployeesFilterList(String valuesToFilter){
        employeesFilterList = FXCollections.observableArrayList();
        employeesFilterList.setAll(facadeBLL.filterResultManager(valuesToFilter));
        return employeesFilterList;
    }

    public void deleteEmployee(Employee employee){
        facadeBLL.deleteEmployeeManager(employee);
        employeeObservableList.remove(employee);
    }

    public Employee createEmployee(String name, Double salary, Byte onLeave, String phoneNumber, double realSalary){
        Employee createdEmployee = facadeBLL.createEmployeeManager(name, salary, onLeave, phoneNumber, realSalary);
        employeeObservableList.add(createdEmployee);
        return createdEmployee;
    }

    public void updateEmployee(Employee employee){
        facadeBLL.updateEmployeeManager(employee);
    }

    public Employee getEmployee(int id){
        return facadeBLL.getMovieManager(id);
    }
}
