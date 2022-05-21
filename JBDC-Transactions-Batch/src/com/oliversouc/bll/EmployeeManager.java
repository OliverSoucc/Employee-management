package com.oliversouc.bll;

import com.oliversouc.be.Employee;
import com.oliversouc.dal.DatabaseManager;
import com.oliversouc.dal.FacadeDAO;

import java.util.List;

public class EmployeeManager implements FacadeBLL{
    FacadeDAO facadeDAO;

    public EmployeeManager(){
        facadeDAO = new DatabaseManager();
    }

    @Override
    public List<Employee> getAllEmployeesManager() {
        return facadeDAO.getAllEmployeesDAO();
    }

    @Override
    public List<Employee> filterResultManager(String valuesToFilter) {
        return facadeDAO.filterResultDAO(valuesToFilter);
    }

    @Override
    public Employee createEmployeeManager(String name, double salary, byte onLeave, String phoneNumber, double realSalary) {
        return facadeDAO.createEmployeeDAO(name, salary, onLeave, phoneNumber, realSalary);
    }

    @Override
    public void updateEmployeeManager(Employee employee) {
        facadeDAO.updateEmployeeDAO(employee);
    }

    @Override
    public void deleteEmployeeManager(Employee employee) {
        facadeDAO.deleteEmployeeDAO(employee);
    }

    @Override
    public Employee getMovieManager(int id) {
        return facadeDAO.getMovieDAO(id);
    }
}
