package com.oliversouc.dal;

import com.oliversouc.be.Employee;

import java.util.List;

public class DatabaseManager implements FacadeDAO{
    final EmployeeDAO employeeDAO;

    public DatabaseManager() {
        this.employeeDAO = new EmployeeDAO();
    }

    @Override
    public List<Employee> getAllEmployeesDAO() {
        return employeeDAO.getAllEmployees();
    }

    @Override
    public List<Employee> filterResultDAO(String valueToFilter) {
        return employeeDAO.filterResult(valueToFilter);
    }

    @Override
    public Employee createEmployeeDAO(String name, double personalBonus, byte onLeave, String phoneNumber, double realSalary) {
        return employeeDAO.createEmployee(name, personalBonus, onLeave, phoneNumber, realSalary);
    }

    @Override
    public void updateEmployeeDAO(Employee employee) {
        employeeDAO.updateEmployee(employee);
    }

    @Override
    public void deleteEmployeeDAO(Employee employee) {
        employeeDAO.deleteEmployee(employee);
    }

    @Override
    public Employee getMovieDAO(int id) {
        return employeeDAO.getMovie(id);
    }
}
